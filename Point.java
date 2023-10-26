
public class Point
{
	private double[] point = new double[2];		// represents x,y coordinates
	float theta = 0;							// angle between the x-axis (y=0) and the hypotenuse
	
    // Constructor to set initial coordinates
    public Point(double x, double y) {
        setCoordinates(x, y);
        calculateDirection();
    }
    
    // No-arg Constructor, sets point to the origin
    public Point() {
        setCoordinates(0, 0);
        calculateDirection();
    }
    
    // Copy constructor
    public Point(Point other) {
    	point[0] = other.getX();
    	point[1] = other.getY();
    }
    
    /**
     * Calculates interior angle created by drawing a right triangle
     * from the origin to the (x,y) coordinates.
     * Updates value of theta.
     * @return theta
     */
    public float calculateDirection() {
    	
    	// deal with cases where the angle is zero
    	if(getY() == 0.0) {
    		theta = 0;
    		return theta;
    	}
    	
    	// find the angle in radians
        theta = (float) Math.atan2(getX(), getY());

        return theta;
    }
    
    /**
     * Calculate interior angle created by drawing a right triangle
     * from this point to the other point.
     * Does not update the value of theta.
     * @param other
     * @return theta
     */
    public float calculateDirection(Point other) {
    	
        double dx = getX() - other.getX();
        double dy = getY() - other.getY();
        
        // find the angle in radians
        theta = (float) Math.atan2(dy, dx);

        return theta;
    }

    // Calculate distance between two coordinates
    public double calculateDistance(Point other) {
    	
        double dx = getX() - other.getX();
        double dy = getY() - other.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        
        return distance;
    }
      
    // Move the coordinates by a specified delta
    public void move(double deltaX, double deltaY) {
        point[0] += deltaX;
        point[1] += deltaY;
        
        calculateDirection();
    }
    
    // Add method, just for readability.
    public void add(double deltaX, double deltaY) {
        point[0] += deltaX;
        point[1] += deltaY;
        
        calculateDirection();
    }
    
    // move a Point object by specified delta without creating new object
    public void translate(double deltaX, double deltaY) {
        move(deltaX, deltaY);
        
        calculateDirection();
    }
    
    // Getters and Setters
    public void setCoordinates(double x, double y) {
        point[0] = x;
        point[1] = y;
        
        calculateDirection();
    }
     
    public void setCoordinates() {
        point[0] = 0;
        point[1] = 0;
        
        calculateDirection();
    }
    
    public double[] getCoordinates() {
    	return point;
    }

    public double getX() {
        return point[0];
    }

    public double getY() {
        return point[1];
    }
    
    public void setX(double x) {
    	point[0]=x;
    }
    
    public void setY(double y) {
    	point[1]=y;
    }
    
    /**
     * Moves the point object, given a value for theta in radians, while
     * maintaining the same distance from (0,0) to (x,y).
     * @param the new Angle you want to rotate the point to
     */
    public void setDirection(float newAngle) {
    	Point origin = new Point();
    	double hypotenuse = calculateDistance(origin);
    	
    	this.theta = newAngle;
    	
    	setX(hypotenuse * Math.cos(theta));
    	setY(hypotenuse * Math.sin(theta));  	
    }

    //make a deep copy of Coordinates object
    public Point clone() {
    	return new Point(getX(), getY());
    }
    
    /**
     * @param other
     * @return true if two coordinate objects have same x,y value
     */
    public boolean isTheSameAs(Point other) {
    	return getX() == other.getX() && getY() == other.getY();
    }
    
    /**
     * @return true if two coordinates are the same reference, or they have the same x,y location
     */
    @Override 
    public boolean equals(Object obj) {
    	
    		// If the objects are the same reference, they are equal
    		if (this == obj) {
    			System.out.println("Coordinates point to same reference");
    	        return true; 
    	    }

    		// If the objects are of different classes, they are not equal
    	    if (obj == null || getClass() != obj.getClass()) {
    	        return false; 
    	    }

    	    // Cast the object to Coordinates
    	    Point other = (Point) obj;
    	    
    	    // Check if all coordinates are equal
    	    return getX() == other.getX() && getY() == other.getY();
    }

    // Displays x,y coordinates as a string
    @Override
    public String toString() {
    	String s = new String("(" + point[0] + ", " + point[1] + ")");
        return s;
    }

    // Example usage
    public static void main(String[] args) {
        Point coords = new Point(3.0, 4.0);
        System.out.println("Initial coordinates: " + coords);

        coords.move(1.5, -2.0);
        System.out.println("Coordinates after moving: " + coords);

        double x = coords.getX();
        double y = coords.getY();
        System.out.println("X coordinate: " + x);
        System.out.println("Y coordinate: " + y);
    }
}


