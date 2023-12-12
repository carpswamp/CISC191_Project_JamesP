import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ElectricFieldCalculator extends Calculator
{ 
	    private Vector result;		//the most recent resultant vector calculated
 
	    /**
	     * Calculates the electric field vector at the origin, given a list of charged particles.
	     * Uses the formula |E| = k * (q/r²)
	     * Invokes 'add' from the Vector class, and 
	     * @param particles
	     * @return Vector object representing the vector E.
	     */
	    public static Vector calculate(List<ChargedParticle> particles, Point point) {
	    	//zero vector at the origin to accumulate
	        Vector result = new Vector(); 

	        System.out.println("invoked calcElecField");

	        for (int i = 0; i < particles.size(); i++) {
	        	
	            ChargedParticle particle = particles.get(i);
	            
	            //retrieve distance and charge
	            double r = particle.calculateDistance(new Point(0, 0)); // Distance from particle to the origin
	            double q = particle.getQ(); // Charge of the particle

	            //check if radius is zero
	            if (r != 0) {
	            	
	            	//calculate megnitude
	                double magnitude = Calculator.ELECTROSTATIC_CONSTANT * (q / (r * r));
	                float theta = particle.calculateDirection(new Point(0, 0));
	                
	                //adjust direction based on sign of the charge
	                if(q < 0) {
	                	//add pi (180 deg) to theta
	                	theta += Math.PI;
	                }

	                //calculate components of the |E| Vector
	                double eX = magnitude * Math.cos(theta);
	                double eY = magnitude * Math.sin(theta);

	                //add vector to the result
	                Vector eVectorForThisParticle = new Vector(eX, eY);
	                result.add(eVectorForThisParticle);
	                
	            } else {
	            	//handle radius of zero
	                System.err.println("Particle located at the origin, skipping this particle.");
	            }
	        }
	        //out of the for-loop
	        return result;
	    }

	        
}
