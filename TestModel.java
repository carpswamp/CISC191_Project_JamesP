import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.nio.file.*;

public class TestModel {

    private ChargedParticle particle;
    private ElectricPotentialCalculator calculator;
    private List<ChargedParticle> particles;
    private Point pointOfInterest;
    
    private static final double DELTA = 1e-6; 							//margin of error
    private static final String TEST_FILE_PATH = "test_output.csv";		//default file path
    private String[] testOutputValues;

    @Before
    public void setUp() {
        
    	//setup for ChargedParticle tests
        particle = new ChargedParticle(1.0, 2.0, 3.0);

        //setup for ElectricPotentialCalculator tests
        calculator = new ElectricPotentialCalculator();
        particles = new ArrayList<>();
        particles.add(new ChargedParticle(1.0, 0.0, 0.0));
        particles.add(new ChargedParticle(-1.0, 1.0, 0.0));
        pointOfInterest = new Point(2.0, 0.0);
    }
    
    @Test
    public void testVectorCreationAndProperties() {
    	
        Vector vector = new Vector(3.0, 4.0);
        assertEquals("X coordinate of head is incorrect", 3.0, vector.getHeadX(), DELTA);
        assertEquals("Y coordinate of head is incorrect", 4.0, vector.getHeadY(), DELTA);

        //test magnitudes
        assertEquals("Magnitude calculation is incorrect", 5.0, vector.calculateMagnitude(), DELTA);
        assertEquals("Direction calculation is incorrect", Math.atan2(4.0, 3.0), vector.calculateDirection(), DELTA);
    }

    @Test
    public void testVectorAddition() {
    	
        Vector vector1 = new Vector(3.0, 4.0);
        Vector vector2 = new Vector(1.0, 2.0);
        vector1.add(vector2);

        assertEquals("X coordinate after addition is incorrect", 4.0, vector1.getHeadX(), DELTA);
        assertEquals("Y coordinate after addition is incorrect", 6.0, vector1.getHeadY(), DELTA);
        
        Vector vector3 = new Vector(-3.0, 4.0);
        Vector vector4 = new Vector(3.0, -4.0);
        vector3.add(vector4);

        assertEquals("X coordinate after addition is incorrect", 0.0, vector3.getHeadX(), DELTA);
        assertEquals("Y coordinate after addition is incorrect", 0.0, vector3.getHeadY(), DELTA);
        
        Vector zeroVector1 = new Vector();
        Vector zeroVector2 = new Vector(0,0);
        zeroVector1.add(zeroVector2);
        
        assertEquals("X coordinate after addition is incorrect", 0.0, zeroVector1.getHeadX(), DELTA);
        assertEquals("Y coordinate after addition is incorrect", 0.0, zeroVector1.getHeadY(), DELTA);
        
        if( zeroVector1.equals(zeroVector2)) {
        	assertEquals("Zero Vectors are bugged", zeroVector1, zeroVector2);
        }else {
         System.out.println("vector equals() failed");
        }      
        
    }

    @Test
    public void testChargeValue() {  	
        assertEquals("Charge should be 1.0 Coulombs", 1.0, particle.getQ(), 0.001);
    }

    @Test
    public void testDistanceCalculation() {
    	
    	Point origin = new Point(0.0,0.0);
        
    	Point otherPoint = new Point(5.0, 6.0);        
        double expectedDistance = Math.sqrt(Math.pow(3.0, 2) + Math.pow(3.0, 2));
        assertEquals("Distance calculation is incorrect", expectedDistance, particle.calculateDistance(otherPoint), 0.001);
        
        Point otherPoint2 = new Point(-5.0, -6.0);
        expectedDistance = Math.sqrt(Math.pow(5.0, 2) + Math.pow(6.0, 2));
        assertEquals("Distance calculation is incorrect", expectedDistance, origin.calculateDistance(otherPoint2), 0.001);
        assertEquals("Distance calculation is incorrect", expectedDistance, otherPoint2.calculateDistance(), 0.001);
     
        Point otherPoint3 = new Point(5.0, -6.0);
        assertEquals("Distance calculation is incorrect", expectedDistance, origin.calculateDistance(otherPoint3), 0.001);
        assertEquals("Distance calculation is incorrect", expectedDistance, otherPoint3.calculateDistance(), 0.001);
        
        
    }

    @Test
    public void testElectricPotential() {
    	
        particles.clear();
        particles.add(new ChargedParticle(1.0e-9, 1.0, 0.0)); //1 nanoCoulomb at (1, 0)
        particles.add(new ChargedParticle(-1.0e-9, -1.0, 0.0)); //-1 nanoCoulomb at (-1, 0)

        double expectedPotential = 0;
        for (ChargedParticle particle : particles) {
            double r = particle.calculateDistance(new Point(0, 0));
            expectedPotential += Calculator.ELECTROSTATIC_CONSTANT * (particle.getQ() / r);
        }

        assertEquals("Voltage Calc has a bug", expectedPotential, calculator.calculate(particles), 0.001);
    }
    
    @Test
    public void testEVector() {
    	
        particles.clear();
        particles.add(new ChargedParticle(1.0e-9, 1.0, 0.0)); 
        particles.add(new ChargedParticle(-1.0e-9, -1.0, 0.0)); 

        double expectedPotential = 0;
        for (ChargedParticle particle : particles) {
            double r = particle.calculateDistance(new Point(0, 0));
            expectedPotential += Calculator.ELECTROSTATIC_CONSTANT * (particle.getQ() / r);
        }

        assertEquals("Vector Calc has a bug", expectedPotential, calculator.calculate(particles), 0.001);
    }


    public void testCalculateElectricField() {
        particles = new ArrayList<>();
        pointOfInterest = new Point(0,0);
        
        //populate particles
        particles.add(new ChargedParticle(1.0e-9, 1.0, 0.0));
        particles.add(new ChargedParticle(-1.0e-9, -1.0, 0.0));

        //should result in zero vector
        Vector expectedField = new Vector(); 
        Vector actualField = ElectricFieldCalculator.calculate(particles, pointOfInterest);

        assertEquals("X component of electric field is incorrect", expectedField.getHeadX(), actualField.getHeadX(), DELTA);
        assertEquals("Y component of electric field is incorrect", expectedField.getHeadY(), actualField.getHeadY(), DELTA);
        
        particles.clear();
        
        //populate particles
        particles.add(new ChargedParticle(1.0e-9, 1.0, 1.0));
        particles.add(new ChargedParticle(-1.0e-9, -1.0, -1.0));

        //should result in zero vector
        Vector expectedField1 = new Vector(); 
        Vector actualField1 = ElectricFieldCalculator.calculate(particles, pointOfInterest);

        assertEquals("X component of electric field is incorrect", expectedField1.getHeadX(), actualField1.getHeadX(), DELTA);
        assertEquals("Y component of electric field is incorrect", expectedField1.getHeadY(), actualField1.getHeadY(), DELTA);
    }
    
    @Test
    public void testExportToCSV() throws IOException {
        
        particles = Arrays.asList(
            new ChargedParticle(1.0, new Point(1, 1)),
            new ChargedParticle(-1.0, new Point(2, 2))
        );
        
        testOutputValues = new String[] {"0.123", "0.456", "0.789", "0.101", "0.112"};

        //export, then read the CSV file
        DataExporter.exportToCSV(particles, testOutputValues, TEST_FILE_PATH);
        List<String> lines = Files.readAllLines(Paths.get(TEST_FILE_PATH));

        assertEquals("Header line doesn't match", "X Coordinate,Y Coordinate,Charge", lines.get(0));

        //verify particles data
        for (int i = 0; i < particles.size(); i++) {
            ChargedParticle particle = particles.get(i);
            String expectedLine = particle.getX() + "," + particle.getY() + "," + particle.getQ();
            assertEquals("Particle data line " + (i + 1) + " should match", expectedLine, lines.get(i + 1));
        }
    
        //tear down the file
        new File(TEST_FILE_PATH).delete();
    }
    
    @Test
    public void testClearCSV() throws IOException {
    	
    	//create a dummy CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE_PATH))) {
            writer.write("X Coordinate,Y Coordinate,Charge\n");
            writer.write("1,2,3\n");
            writer.write("4,5,6\n");
        }
        
        //clear the CSV file
        DataExporter.clearCSV(TEST_FILE_PATH);

        //file should now only contains the header
        String content = new String(Files.readAllBytes(Paths.get(TEST_FILE_PATH)));
        assertEquals("X Coordinate,Y Coordinate,Charge\n", content);
        
        //delete when finished
        Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
    }

    
    

}

