import java.text.DecimalFormat;

public class ChargedParticle extends Point
{
	 	private double chargeStrength; 	//charge in Coloumbs, commonly 'q'
	    private Point coordinates; 		//handles location
	    private static int counter = 0; //particle counter
	    private int idNum = 0;			//unique ID number for each particle

	    //constructors
	    public ChargedParticle(double chargeStrength, double x, double y) {
	    	
	    	super(x,y);
	        this.chargeStrength = chargeStrength;
	        this.coordinates = new Point(x,y);
	        
	        //id number below
	        counter += 1;
	        this.idNum = counter;
	    }
	    
	    //constructor makes a chargedParticle out of a charge and a Point
	    public ChargedParticle(double chargeStrength, Point coordinate) {
	    	
	    	super(coordinate.getX(),coordinate.getY());
	        this.chargeStrength = chargeStrength;
	        this.coordinates = new Point(coordinate.getX(),coordinate.getY());
	        
	        //id number below
	        counter += 1;
	        this.idNum = counter;
	    }
	    
	    //copy constructor
	    public ChargedParticle(ChargedParticle source) {
	    	super(source.getX(), source.getY());
	        this.chargeStrength = source.getQ();
	        this.coordinates = new Point(source.coordinates);

	        // Increment the counter for the new particle
	        counter += 1;
	        this.idNum = counter;
	    }

	    //calculate the distance between this charged particle and another charged particle
	    public double calculateDistance(ChargedParticle otherParticle) {
	        double dx = this.coordinates.getX() - otherParticle.getX();
	        double dy = this.coordinates.getY() - otherParticle.getY();
	        double distance = Math.sqrt( (dx * dx) + (dy * dy) );
	        return distance;
	    }

	    //calculate the distance between this charged particle and a point in space (x, y)
	    public double calculateDistance(double x, double y) {
	        double dx = this.coordinates.getX() - x;
	        double dy = this.coordinates.getY() - y;
	        double distance = Math.sqrt( (dx * dx) + (dy * dy) );
	        return distance;
	    }
	    
	    //calculate the distance between this charged particle and a Coordinates object
	    public double calculateDistance(Point other) {
	    	double distance = coordinates.calculateDistance(other);
	        return distance;
	    }
	    	   
	    
	    //standard getters and setters
	    public double getQ() {
	    	return chargeStrength;
	    }  
	    
	    public void setQ(double newQ) {
	    	this.chargeStrength = newQ;
	    }
		
	    public void setCounter(int newCounter) {
	    	this.counter = newCounter;
	    }
	    
	    public void setID(int newID) {
	    	this.idNum = newID;
	    }
	    
	    //toString prints something like this:
	    // 	"PointCharge 1 @ (-1.00e+00, 1.00e+00), Q: -1.00e-19, r: 0.00e+00"
		public String toString() {
			
			String formattedChargeStrength = String.format("%.2e", chargeStrength);
			String formattedDistance = String.format("%.2e", coordinates.calculateDistance());
			
			String s = new String();	
			s = "PointCharge " + idNum + " @ (" 
			+ String.format("%.2e", coordinates.getX()) + ", " 
			+ String.format("%.2e", coordinates.getY()) + "), Q: "
			+ formattedChargeStrength + ", r: " 
			+ formattedDistance;
			
			return s;
		}

}
