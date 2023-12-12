import java.util.ArrayList;
import java.util.List;

public class Model
{
	private ElectricPotentialCalculator electricPotentialCaclulator;
	private ElectricFieldCalculator electricFieldCalculator;
	private Point coordinate;
    private List<ChargedParticle> particles;
    
    //constructor
    public Model() {
    	this.electricPotentialCaclulator = new ElectricPotentialCalculator();
    	this.electricFieldCalculator = new ElectricFieldCalculator();
    	this.coordinate = new Point(0,0);
    	this.particles = new ArrayList<>();
    }
    
    /**
     * @return a copy of a list of particles in the Model
     */
    public List<ChargedParticle> getChargedParticle(){
    	return new ArrayList<>(particles);
    }
   
	public void setParticles(List<ChargedParticle> newParticles)
	{
		this.particles.clear();
		
		//make a deep copy
		 for (int i = 0; i < newParticles.size(); i++) {
		        ChargedParticle originalParticle = newParticles.get(i);
		        ChargedParticle copiedParticle = new ChargedParticle(
		            originalParticle.getX(), originalParticle.getY(), originalParticle.getQ());
		        this.particles.add(copiedParticle);
		    }
	}
    
    /**
     * Create a charged particle, 
     * @param chargeStrength
     * @param coordinate
     */
    public void createChargedParticle(double chargeStrength, Point coordinate) {
        ChargedParticle newParticle = new ChargedParticle(chargeStrength, coordinate);
        particles.add(newParticle);

        System.out.println("Created new particle: " + newParticle);
        System.out.println("Model added " + newParticle + " to the list.");
    }
    
    /**
     * Helper method, meant to add a charged particle to the model's list of particles
     * To be passed to the calculator.
     * @param particle
     */
    public void addChargedParticle(ChargedParticle particle) {
        particles.add(particle);
        System.out.println("Model added " + particle + " to the list.");
    }
    
    /**
     * Helper method.  Removes particles from the model.
     * @param particle
     */
    public void removeChargedParticle(ChargedParticle particle) {
        System.out.println("Removed from model " + particle);
    	particles.remove(particle);
    }
    
    /**
     * Helper method.  Clears all particles from the model.
     * Invoked by the 'clear' button action listener.
     * @param particle
     */
    public void clearAllParticles() {
    	particles.clear();
        System.out.println("All particles removed from the model");
    }
    
    /**
     * Modify the model's coordinate object
     * @param x
     * @param y
     * @return the newly altered coordinate object
     */
    public Point setCoordinate(double x, double y) {
        coordinate.setCoordinates(x, y);
        return new Point(coordinate);
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
    public double calculateVoltage(Point point) {
    	//only accepts points located at 0,0 for now
    	double voltage = electricPotentialCaclulator.calculate(particles);
        return voltage;
    }

    /**
     * Call the model's calculator object, and calculate voltage the origin
     * @return Voltage associated with (0,0), given the particles in listed in the model
     */
    public double calculateElectricPotential() {
        double electricPotential = 0.0;
        coordinate.setCoordinates();

        //the following is for testing purpose
        System.out.println("Invoked the calculator in the the model");
        System.out.println("Voltage at (" + coordinate.getX() + "," + coordinate.getY() + ") " );
        
        //do the calculation
        for (ChargedParticle particle : particles) {
            System.out.println(particle.toString());
        }
        
        return electricPotential;
    }

    /**
     * Call the model's E-Vector calculator, then calculate the vector at the origin
     * @param point, for now, this will always be changed to (0,0)
     * @return Vector object representing the direction and magnitude of the electric field
     */
	public Vector calculateElectricFieldVector(Point point){
		
		Vector result = new Vector(0,0);
		coordinate = new Point(point);
		
        //the following is for testing purpose
        System.out.println("Invoked the E-Vector calculator in the the model");
        System.out.println("Voltage at (" + coordinate.getX() + "," + coordinate.getY() + ") " );
        
        //print our the list of particles to the console, for testing
        for (ChargedParticle particle : particles) {
            System.out.println(particle.toString());
        }
		
		//for now, this method only works at the origin
        if (point.getX() == 0 && point.getY() == 0) {
            return ElectricFieldCalculator.calculate(particles, point);
        } else {
        	return new Vector(0,0);
        }
	}


	
	
}
