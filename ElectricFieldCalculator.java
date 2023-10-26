import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ElectricFieldCalculator
{ 
	    private static final double ELECTROSTATIC_CONSTANT = 8.988e9; // constant (k), in Nm²/C²
	    private static final double ELECTRON_CHARGE = 1.602e-19; // Charge of one electron, in C
	    private static Vector result;

	    /**
	     * Calculate the electric field vector (E) at a point due to a point charge.
	     * Note this is a two dimension vector, given in N/C or V/m
	     *
	     * @param chargeStrength The strength of the charge in number of electrons.
	     * @param position       The position vector (x, y) of the point where the electric field is calculated (in meters).
	     * @return The electric field vector (E) in N/C or V/m at the specified point as a 2D array [Ex, Ey].
	     */
	    public static double[] calculateElectricField(int chargeStrength, double[] position) {
	        // Convert the charge strength from the number of electrons to Coulombs
	        double chargeInCoulombs = chargeStrength * ELECTRON_CHARGE; // Charge of one electron in Coulombs
	        double x = position[0];
	        double y = position[1];

	        // Calculate the distance from the charge to the point
	        double distance = Math.sqrt( (x*x) + (y*y));

	        // Calculate the electric field vector components
	        double electricFieldX = ELECTROSTATIC_CONSTANT * (chargeInCoulombs / (distance * distance)) * (x / distance);
	        double electricFieldY = ELECTROSTATIC_CONSTANT * (chargeInCoulombs / (distance * distance)) * (y / distance);

	        return new double[]{electricFieldX, electricFieldY};
	    }
	    
	    
	    /**
	     * Calculates the electric field vector at a particular x,y position, given a list of charged particles,
	     * and a Point object with the x,y coordinates of interest.
	     * Uses the formula |E| = k * (q/r²)
	     * Does all the calculations right here in the for-loop.
	     * @param particles
	     * @return Vector object representing the vector E
	     */
	    public static Vector calcElecField(List<ChargedParticle> particles, Point point) {
	    	
	    	
	    	double r = 0; //radius, in meters
	    	double q = 0; //charge, in Coloumbs
	    	
	    	float theta = 0; 		//inner angle enclosed by x,y for resultant vector, in radians
	    	double magnitude = 0; 	//magnitude |E| for resultant vector, in Volts per meter
	    	
	    	double elecFieldX = 0; // collector variable for the resultant E-vector
	    	double elecFieldY = 0; // collector variable for the resultant E-vector
    	    	
	    	System.out.println("invoked calcElecField");
	    	
	        // Calculate the electric potential contribution from each charged particle
	        for (int i = 0; i < particles.size(); i++) {
	        	
	            ChargedParticle particle = particles.get(i);
	            
	            //calculate distance to the particle from the point of interest
	        	r = particle.calculateDistance(point);
	        	
	        	//retrieve the charge value from the particle
	            q = particle.getQ();
	            
	            //find the magnitude of the resultant E vector, using the formula
	            //recall that magnitude |E| = k * (q/r²)
	            magnitude += ELECTROSTATIC_CONSTANT * ( q / (r*r));
	            
	            //retrieve the direction of the particle
	            theta = particle.getAngle();
	            
	            //use principle of super position for the x and y components
	            elecFieldX += magnitude * Math.cos(theta);
	            elecFieldY += magnitude * Math.sin(theta);          
	        }
	        
	        Point vectorHead = new Point(elecFieldX,elecFieldY);
	        
	    	return result = new Vector(vectorHead);
	    }
	    
	    /**
	     * Calculates the electric field vector at the origin, given a list of charged particles.
	     * Uses the formula |E| = k * (q/r²)
	     * Invokes 'add' from the Vector class, and 
	     * @param particles
	     * @return Vector object representing the vector E.
	     */
	    public static Vector calcElecField(List<ChargedParticle> particles) {
	
	    	Point origin = new Point();
	    	
	    	double r = 0; //radius, in meters
	    	double q = 0; //charge, in Coloumbs
	    	
	    	float theta = 0; 		//inner angle enclosed by x,y for resultant vector, in radians
	    	double magnitude = 0; 	//magnitude |E| for resultant vector, in Volts per meter
	    	
	    	double eX = 0; // collector variable for the resultant E-vector
	    	double eY = 0; // collector variable for the resultant E-vector
  	
	    	//creates a zero vector at the origin
	    	Vector result = new Vector();
	    	    	
	    	System.out.println("invoked calcElecField");
	    	
	        // Calculate the electric potential contribution from each charged particle
	        for (int i = 0; i < particles.size(); i++) {
	        	
	            ChargedParticle particle = particles.get(i);
	            
	            //calc distance and charge
	        	r = particle.calculateDistance(origin);
	        	
	        	// Converts from number of electrons into Coloumbs
	            q = particle.getQ();
	            
	            //find the magnitude of the resultant E vector
	            magnitude += ELECTROSTATIC_CONSTANT * ( q / (r*r));
	            theta = particle.getAngle();
	            
	            eX = magnitude * Math.cos(particle.getAngle());
	            eY = magnitude * Math.sin(particle.getAngle());
            
	            //create a Vector from the above x and y components, then add to a collector vector object, 'result'.
	            Vector eVectorForThisParticle = new Vector(eX, eY);
	            result.add(eVectorForThisParticle);
	        }
	    	return result;
	    }
	    
	    /**
	     * Calculate the electric field vector (E) at a point due to a point charge.
	     *
	     * @param chargeStrength The strength of the charge in number of electrons.
	     * @param position       The position vector of the point where the electric field is calculated (in meters).
	     * @return The electric field vector (E) in N/C or V/m at the specified point.
	     */
	    public static Point2D.Double calculateElectricField(int chargeStrength, Point2D.Double position) {
	        // Convert the charge strength from the number of electrons to Coulombs
	        double chargeInCoulombs = chargeStrength * ELECTRON_CHARGE; 
	        
	        System.out.println("Invoked calculateElectricField using Point2D.Double param");
	        
	        // Calculate the direction vector (unit vector) from the charge to the point
	        double distance = position.distance(new Point2D.Double(0, 0)); // Distance to the charge
	        double directionX = position.getX() / distance;
	        double directionY = position.getY() / distance;

	        // Calculate the electric field vector components
	        double electricFieldX = ELECTROSTATIC_CONSTANT * (chargeInCoulombs / (distance * distance)) * directionX;
	        double electricFieldY = ELECTROSTATIC_CONSTANT * (chargeInCoulombs / (distance * distance)) * directionY;

	        System.out.println("Invoked calculateElectricField using Point2D.Doubles");
	        return new Point2D.Double(electricFieldX, electricFieldY);
	    }

	    
	    public static void main(String[] args) {
	    	//Test the calculateElectricField method using a 2D Array
	        int chargeStrength = 1; // Number of electrons
	        double[] position = {0.1, 0.0}; // Position vector (x, y) in meters

	        double[] electricField = calculateElectricField(chargeStrength, position);

	        System.out.println("Electric Field at (" + position[0] + "m, " + position[1] + 
	        		"m) from the point charge with " + chargeStrength + 
	        		" electrons: (" + electricField[0] + 
	        		" N/C, " + electricField[1] + 
	        		" N/C)");
     
	        // Create a list of ChargedParticle objects
	        List<ChargedParticle> particles = new ArrayList<>();
	        particles.add(new ChargedParticle(1e10, 2, 3, 'Q'));
	        particles.add(new ChargedParticle(1e10, -2, -3, 'E'));
	        
	        Point origin = new Point();
	        
	        ElectricFieldCalculator calc = new ElectricFieldCalculator();
	        
	        Vector result = ElectricFieldCalculator.calcElecField(particles, origin);
	        System.out.println("Electric Field at (" + result.toString() + " )");
	        
	        Vector result1 = ElectricFieldCalculator.calcElecField(particles);
	        System.out.println("Electric Field at (" + result1.toString() + " )");
	    }
	}
