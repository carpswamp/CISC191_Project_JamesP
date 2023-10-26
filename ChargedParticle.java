import java.text.DecimalFormat;

public class ChargedParticle
{
	 	private int numElectrons; // Number of electrons
	 	private double chargeStrength; //Charge in Coloumbs, commonly 'q'
	    private char symbol; //Symbol or variable to represent the particle
	    private Point coordinates; //handles location
	    private final int serial; // particle unique ID
	    
	    private static int counter = 0; // particle counter
	    private static final double ELECTROSTATIC_CONSTANT = 8.998e9;  // electrostatic constant (k) in Nm²/C²
	    private static final double ELECTRON_CHARGE = 1.602e-19; // Charge of one electron in Coulombs

	    //constructors
	    public ChargedParticle(int numElectrons, double x, double y) {
	        this.numElectrons = numElectrons;
	        this.chargeStrength = numElectrons * ELECTRON_CHARGE;
	        this.coordinates = new Point(x,y);
	        this.symbol = (char) 'x';
	        counter += 1;
	        this.serial = counter;
	    }
	    
	    public ChargedParticle(int numElectrons, double x, double y, char symbol) {
	        this.numElectrons = numElectrons;
	        this.chargeStrength = numElectrons * ELECTRON_CHARGE;
	        this.coordinates = new Point(x,y);
	        this.symbol = symbol;
	        counter += 1;
	        this.serial = counter;
	    }
	    
	    public ChargedParticle(double chargeStrength, double x, double y, char symbol) {
	        this.numElectrons = (int)( chargeStrength / ELECTRON_CHARGE ); //truncates number of electrons
	        this.chargeStrength = chargeStrength;
	        this.coordinates = new Point(x,y);
	        this.symbol = symbol;
	        counter += 1;
	        this.serial = counter;
	    }
	    
	    //constructor makes a deep copy when passed coordinate object
	    public ChargedParticle(double chargeStrength, Point coordinate) {
	        this.numElectrons = (int)( chargeStrength / ELECTRON_CHARGE ); //truncates number of electrons
	        this.chargeStrength = chargeStrength;
	        this.coordinates = new Point(coordinate.getX(),coordinate.getY());
	        this.symbol = (char)'q';
	        counter += 1;
	        this.serial = counter;
	    }

	    // Calculate the distance between this charged particle and another charged particle
	    public double calculateDistance(ChargedParticle otherParticle) {
	        double dx = this.coordinates.getX() - otherParticle.getX();
	        double dy = this.coordinates.getY() - otherParticle.getY();
	        double distance = Math.sqrt( (dx * dx) + (dy * dy) );
	        return distance;
	    }

	    // Calculate the distance between this charged particle and a point in space (x, y)
	    public double calculateDistance(double x, double y) {
	        double dx = this.coordinates.getX() - x;
	        double dy = this.coordinates.getY() - y;
	        double distance = Math.sqrt( (dx * dx) + (dy * dy) );
	        return distance;
	    }
	    
	    // Calculate the distance between this charged particle and a Coordinates object
	    public double calculateDistance(Point other) {
	    	double distance = coordinates.calculateDistance(other);
	        return distance;
	    }
	    	    
	    //standard getters and setters
	    public int getNumElectrons() {
	        return numElectrons;
	    }
	    
	    public double getQ() {
	    	return chargeStrength;
	    }

	    public double getX() {
	        return coordinates.getX();
	    }
	    
	    public void setX(double x) {
	    	coordinates.setX(x);
	    }

	    public double getY() {
	        return coordinates.getY();       
	    }

	    public void setY(double y) {
	    	coordinates.setY(y);
	    }
	    
	    public Point getCoord() {
	    	return coordinates;
	    }
	    
	    /**
	     * points to Point object's angle-from-origin method
	     * @return angle in radians
	     */
	    public float getAngle() {
	    	return coordinates.calculateDirection();
	    }
	    
	    public float getAngle(Point point) {
	    	return coordinates.calculateDirection(point);
	    }
	    
	    
	    
		public char getSymbol()
		{
			return symbol;
		}
		
		public void setSymbol(char symbol) {
			this.symbol = symbol;
		}
		
		public int getSerial() {
			return this.serial;
		}
		
		public String toString() {
			
			// Format the chargeStrength to display with 2 decimal places and avoid scientific notation
	        DecimalFormat decimalFormat = new DecimalFormat("#.##");
	        String formattedChargeStrength = decimalFormat.format(chargeStrength);

			String s = new String();	
			s = "ChargedParticle " + symbol + " (" + coordinates.getX() + ", " + coordinates.getY() + "), q:" + formattedChargeStrength + ", ID: " + serial;
			
			return s;
		}

}
