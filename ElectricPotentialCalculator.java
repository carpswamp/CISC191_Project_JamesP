import java.util.ArrayList;
import java.util.List;

public class ElectricPotentialCalculator extends Calculator
{
    private List<ChargedParticle> particles; 
     
    /**
     * Calculate the electric potential at a given point due to charged particles on the coordinate system.
     *
     * @param particles  List of ChargedParticle objects representing effecting the e-field.
     * @param point		 Coordinate object representing x,y location of interest
     * @return The electric potential in volts at the specified x,y location
     */
    public double calculate(List<ChargedParticle> particles) {
    	
    	Point point = new Point();			//always calculates at the origin
        double electricPotential = 0.0;		//voltage, in Coloumbs per meter
        double r = 0.0;						//distance from particle of point of interest, in meters
        double q = 0.0;						//charge, in Coloumbs
        
        System.out.println("invoked Coordinate Object version of SuperPostion Voltage calc at (" + point.getX() + ", " + point.getY() + ")");

        // Calculate the electric potential contribution from each charged particle
        for (int i = 0; i < particles.size(); i++) {
            ChargedParticle particle = particles.get(i);
            
            //calc distance and charge
        	r = particle.calculateDistance(point);
        	
        	//grab the charge at the point
            q = particle.getQ(); 
            
            if (r != 0 ) {
                //use principle of super position
                electricPotential += Calculator.ELECTROSTATIC_CONSTANT * ( q / r);
            } else {
            	System.err.println("Electric Potential Calculate attempted to divide by zero.  Aborted Calculation");
            	electricPotential = 0;
            }
        }
    
        return electricPotential;
    }
    
}

