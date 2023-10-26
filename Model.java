import java.util.ArrayList;
import java.util.List;

public class Model
{
	private ElectricPotentialCalculator voltageCaluclator;
	private Point coordinate;
    private List<ChargedParticle> particles;
    private double electricPotential;
    
    //constructor
    public Model() {
    	ElectricPotentialCalculator voltageCaluclator = new ElectricPotentialCalculator();
    	coordinate = new Point(0,0);
        particles = new ArrayList<>();
    }
    
    /**
     * @return list of particles in the Model
     */
    public List<ChargedParticle> getChargedParticle(){
    	return particles;
    }
   
    /**
     * Create a charged particle, 
     * @param chargeStrength
     * @param coordinate
     */
    public void createChargedParticle(double chargeStrength, Point coordinate) {
    	ChargedParticle newChargedParticle = new ChargedParticle(chargeStrength, coordinate);
    	System.out.println("Created new particle " + newChargedParticle.toString() );
    	particles.add(newChargedParticle);
    	System.out.println("Model added " + newChargedParticle.toString() + " to the list.");
    }
    
    /**
     * Helper class, meant to add a charged particle to the model's list of particles
     * To be passed to the calculator.
     * @param particle
     */
    public void addChargedParticle(ChargedParticle particle) {
        particles.add(particle);
        System.out.println("Model added " + particle.toString() + " to the list.");
    }
    
    /**
     * Helper class.  Removes particles from the model.
     * @param particle
     */
    public void removeChargedParticle(ChargedParticle particle) {
        System.out.println("Removed from model " + particle.toString());
    	particles.remove(particle);
    }
    
    /**
     * Modify the model's coordinate object
     * @param x
     * @param y
     * @return the newly altered coordinate object
     */
    public Point setCoordinate(double x, double y) {
    	coordinate.setCoordinates(x, y);
    	return coordinate;
    }
    
    /**
     * Change the model's coordinate object to the origin
     */
    public void setToOrigin() {
    	coordinate.setCoordinates(0,0);
    }

    /**
     * Call the model's calculator object, and provide a Coordinates object of interest
     * @param point
     * @return Voltage associated with a point, given the particles in listed in the model
     */
    public double calculateElectricPotential(Point point) {
        double electricPotential = 0.0;

        //the following is for testing purpose
        System.out.println("invoked the calculator in the the model");
        System.out.println("Voltage at (" + point.getX() + "," + point.getY() + ") " );
        for (ChargedParticle particle : particles) {
            System.out.println(particle.toString());
        }
        
        //electricPotential = ((Object) voltageCaluclator).getSuperPosition(particles, point);
        
        return electricPotential;
    }

    /**
     * Call the model's calculator object, and calculate voltage the origin
     * @return Voltage associated with (0,0), given the particles in listed in the model
     */
    public double calculateElectricPotential() {
        double electricPotential = 0.0;
        coordinate.setCoordinates();

        //the following is for testing purpose
        System.out.println("invoked the calculator in the the model");
        System.out.println("Voltage at (" + coordinate.getX() + "," + coordinate.getY() + ") " );
        for (ChargedParticle particle : particles) {
            System.out.println(particle.toString());
        }
        
       // electricPotential = voltageCaluclator.getSuperPosition(particles, coordinate);
        
        return electricPotential;
    }
	
}
