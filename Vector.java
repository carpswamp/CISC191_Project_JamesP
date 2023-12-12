import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Vector {

	    private double[][] vector = new double[2][2];
	    
	    double magnitude;	//length of the vector
	    float theta;		//angle of the vector
	    Point point;		//a vector has-a point (or rather, a location)

	    //constructs vector at the origin
	    public Vector(Point head) {
	    	
	    	vector = new double[][] {{0,0},{head.getX(),head.getY()}};
    	
	        calculateMagnitude();
	        calculateDirection();
	    }
	    
	    public Vector(double hX, double hY) {
	    	
	    	vector = new double[][] {{0,0},{hX,hY}};
    	
	        calculateMagnitude();
	        calculateDirection();
	    }
	    
	    //constructs vector at the origin using magnitude and angle (in radians)
	    public Vector(double magnitude, float theta) {
	
	    	calculateMagnitude();
	        calculateDirection();
	    	
	    	vector = new double[][] {{0,0},										//tx, ty
	    		{magnitude * Math.cos(theta),magnitude * Math.sin(theta)}};  	//hx, hy
	    }
	    
	    //constructs the zero vector
	    public Vector() {
	    	
	    	vector = new double[][] {{0,0},{0,0}};
	    	
	    	calculateMagnitude();
	        calculateDirection();
	    }
	    
	    //constructor to initialize the vector with tail and head coordinates
	    public Vector(Point tail, Point head) {
	    	
	        this.vector = new double[][] {{tail.getX(),tail.getY()},
	        						{head.getX(), head.getY()}};
	        						
	        calculateMagnitude();
	        calculateDirection();
	    }
	    
	    /**
	     * Constructs vector beginning with tail (origin) x,y coordinates, and
	     * then accepting the head x,y coordinates.
	     * @param tailX
	     * @param tailY
	     * @param headX
	     * @param headY
	     */
	    public Vector(double tailX, double tailY, double headX, double headY) {
	    	
	    	vector = new double[][] {{tailX, tailY},{headX, headY}};
	    	
	    	calculateMagnitude();
	        calculateDirection();
	        
	        System.out.println("Created new Vector Object " + toString());
	    }   

	    /**
	     * Calculates the direction of the vector as a unit vector
	     * Updates value of theta
	     * @return theta in radians
	     */
	    public float calculateDirection() {
	    	
	        double dx = vector[1][0] - vector[0][0];
	        double dy = vector[1][1] - vector[0][1];

	        //handle division by zero or zero vector case
	        if (calculateMagnitude() == 0) {
	            System.out.println("Detected direction of 0");
	        	theta = 0;
	        	return theta;
	        }

	        //calculate the angle in radians
	        theta = (float) Math.atan2(dy, dx);
	        
	        return theta;
	    }

	    /**
	     * updates this.magnitude
	     * @return magnitude variable
	     */
	    public double calculateMagnitude() {
	    	
	        double dx = vector[1][0] - vector[0][0];	//x2 - x1
	        double dy = vector[1][1] - vector[0][1];	//y2 - y1
	        
	        this.magnitude = Math.sqrt(dx * dx + dy * dy );

	        return this.magnitude;
	    }

	    /**
	     * helper method for the (magnitude, direction) constructor
	     * updates values in vector[][], given that magnitude and theta have been written.
	     */
	    public void updateArray() {
	    	setTail(0,0);  	
	    	setHeadX(magnitude * Math.cos(theta));
	    	setHeadY(magnitude * Math.sin(theta));
	    }
	    
	    /**
	     * Sets the vector to the given coordinates
	     * @param newTailX
	     * @param newTailY
	     * @param newHeadX
	     * @param newHeadY
	     */
	    public void setVector(double newTailX, double newTailY, double newHeadX, double newHeadY) {
	    this.vector[0][0] = newTailX;
	    this.vector[0][1] = newTailY;
	    this.vector[1][0] = newHeadX;
	    this.vector[1][1] = newHeadY;
	    }
	    
	    /**
	     * Sets the vector to the given values, placed at the origin
	     * @param newTailX
	     * @param newTailY
	     * @param newHeadX
	     * @param newHeadY
	     */
	    public void setVector(double newHeadX, double newHeadY) {
	    this.vector[0][0] = 0;
	    this.vector[0][1] = 0;
	    this.vector[1][0] = newHeadX;
	    this.vector[1][1] = newHeadY;
	    }
	    
	    /**
	     * Sets the vector to the given values, placed at the origin
	     * @param newTailX
	     * @param newTailY
	     * @param newHeadX
	     * @param newHeadY
	     */
	    public void setVector(Point head) {
	    this.vector[0][0] = 0;
	    this.vector[0][1] = 0;
	    this.vector[1][0] = head.getX();
	    this.vector[1][1] = head.getY();
	    }
	    

	    /**
	     * Adds the other vector to this vector
	     * Completing this method alters this vector
	     * @param other
	     */
	    public void add(Vector other) {
	    	
	    	System.out.println("Adding Other Vector " + other.toString() +
	    						" to this vector " + toString());
	    	
	    	this.vector[0][0] += other.getTailX();
	    	this.vector[0][1] += other.getTailY();
	    	this.vector[1][0] += other.getHeadX();
	    	this.vector[1][1] += other.getHeadY();
	    	
	    	System.out.println("NewHeadX is " + vector[1][0]);
	    	System.out.println("NewHeadY is " + vector[1][1]);

	    	System.out.println("passing back sum vector: " + toString());
	    	
	    }
	    
	    //vector[0] represents the tail
	    //vector[0][0] represents tx
	    //vector[0][1] represents ty
	    //vector[1] represents the head
	    //vector[1][0] represents hx
	    //vector[1][1] represents hy
	    
	    /**
	     * subtracts this.vector - other vector
	     * @param other
	     * @return new subtracted single vector
	     */
	    public Vector subtract(Vector other) {
	    	double newTailX = vector[0][0]-other.getTailX();
	    	double newTailY = vector[0][1]-other.getTailY();
	    	double newHeadX = vector[1][0]-other.getHeadX();
	    	double newHeadY = vector[1][1]-other.getHeadY();
	    	
	    	return new Vector(newTailX, newTailY, newHeadX, newHeadY);
	    }
	    	    
	    public Vector scalarMultiply(double scalar) {
	    	double newTailX = this.getTailX()*scalar;
	    	double newTailY = this.getTailY()*scalar;
	    	double newHeadX = this.getHeadX()*scalar;
	    	double newHeadY = this.getHeadY()*scalar;
	    	
	    	return new Vector(newTailX, newTailY, newHeadX, newHeadY);
	    }
	    
	    /**
	     * uses trig and magnitude value to find X
	     * @return the length of the vector along the x-axis
	     */
	    public double getXLength() {
	    	return magnitude * Math.cos(theta);
	    }
	    
	    /**
	     * uses trig and magnitude value to find Y
	     * @return the length of the vector along the y-axis
	     */
	    public double getYLength() {
	    	return magnitude * Math.sin(theta);		
	    }
	    
	    public double[] getHead() {
	    	return vector[1];
	    }
	    
	    public double getHeadX() {
	    	return vector[1][0];
	    }
	    
	    public double getHeadY() {
	    	return vector[1][1];
	    }
	    
	    public void setHead(double x, double y) {
	    	vector[1][0] = x;
	    	vector[1][1] = y;
	    }
	    
	    public void setHeadX(double x) {
	    	vector[1][0]=x;
	    }
	    
	    public void setHeadY(double y) {
	    	vector[1][1]=y;
	    }
	    
	    public double[] getTail() {
	    	return vector[0];
	    }
	    
	    public double getTailX() {
	    	return vector[0][0];
	    }
	        
	    public double getTailY() {
	    	return vector[0][1];
	    }
	    
	    public void setTail(double x, double y) {
	    	vector[0][0]=x;
	    	vector[0][1]=y;
	    }
	    
	    public void setTailX(double x) {
	    	vector[0][0]=x;
	    }
	    
	    public void setTailY(double y) {
	    	vector[0][1]=y;
	    }
	    
	    
	    @Override
	    public String toString() {
	        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	        DecimalFormat df = new DecimalFormat("0.00E0", symbols);

	        String tailX = df.format(vector[0][0]);
	        String tailY = df.format(vector[0][1]);
	        String headX = df.format(vector[1][0]);
	        String headY = df.format(vector[1][1]);
	        
	        String s = new String();
	        s = "Head: (" + headX + ", " + headY + ")";
	        //s = "Tail: (" + tailX + ", " + tailY + ") " + "Head: (" + headX + ", " + headY + ")";
	        
	        return s;
	    }
	    


}