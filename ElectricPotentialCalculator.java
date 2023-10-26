import java.util.ArrayList;
import java.util.List;

public class ElectricPotentialCalculator
{
	private static final double ELECTROSTATIC_CONSTANT = 8.998e9;  // electrostatic constant (k) in Nm²/C²
    private static final double ELECTRON_CHARGE = 1.602e-19; // Charge of one electron in Coulombs
    private List<ChargedParticle> particles; 
          
    /**
     * Calculate the electric potential at a given point due to charged particles on the coordinate system.
     *
     * @param particles  List of ChargedParticle objects representing the charged particles on the coordinate system.
     * @param pointX     The x-coordinate of the point where the electric potential is calculated (in meters).
     * @param pointY     The y-coordinate of the point where the electric potential is calculated (in meters).
     * @return The electric potential in volts at the specified point.
     */
    public double getVoltage(List<ChargedParticle> particles, double pointX, double pointY) {
    	
        double electricPotential = 0.0;
        double distance = 0.0;
        double chargeInCoulombs = 0.0;
        
        System.out.println("invoked x-y version of Super Postion Voltage calc at point " + pointX + ", " + pointY );

        // Calculate the electric potential contribution from each charged particle
        for (int i = 0; i < particles.size(); i++) {
            ChargedParticle particle = particles.get(i);
            
            //calc distance and charge
        	distance = particle.calculateDistance(pointX, pointY);
            chargeInCoulombs = particle.getNumElectrons() * ELECTRON_CHARGE; // Converts from number of electrons into coloumbs
            
            //use principle of super position
            electricPotential += ELECTROSTATIC_CONSTANT * (chargeInCoulombs / distance);
        }

        return electricPotential;
    }
    
    /**
     * Calculate the electric potential at a given point due to charged particles on the coordinate system.
     *
     * @param particles  List of ChargedParticle objects representing effecting the e-field.
     * @param point		 Coordinate object representing x,y location of interest
     * @return The electric potential in volts at the specified x,y location
     */
    public double getVoltage(List<ChargedParticle> particles, Point point) {
    	
        double electricPotential = 0.0;
        double r = 0.0;		//distance from particle of point of interest
        double q = 0.0;		//charge, in Coloumbs
        
        System.out.println("invoked Coordinate Object version of SuperPostion Voltage calc at (" + point.getX() + ", " + point.getY() + ")");

        // Calculate the electric potential contribution from each charged particle
        for (int i = 0; i < particles.size(); i++) {
            ChargedParticle particle = particles.get(i);
            
            //calc distance and charge
        	r = particle.calculateDistance(point);
        	
        	// Converts from number of electrons into Coloumbs
            q = particle.getNumElectrons() * ELECTRON_CHARGE; 
            
            //use principle of super position
            electricPotential += ELECTROSTATIC_CONSTANT * ( q / r);
        }

        return electricPotential;
    }
    
    //for testing purposes
    public static void main(String[] args) {
        
        // Create a list of ChargedParticle objects
        List<ChargedParticle> particles = new ArrayList<>();
        particles.add(new ChargedParticle(1, 2, 3, 'Q'));
        particles.add(new ChargedParticle(-1, -2, -3, 'E'));
        
        //printout for testing.
        for (ChargedParticle particle : particles) {
            System.out.println(particle.toString());
        

        // Point at which to calculate electric potential
        double pointX = 0.5; 
        double pointY = -0.5;

        // Calculate electric potential at the specified point
//        double electricPotential1 = calcVoltageSuperPosition(particles, pointX, pointY);
//
//        System.out.println("Electric Potential at (" + pointX + "m, " + pointY + "m): " + electricPotential1 + " volts");
    }
}
}

