import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class View
{
		private static JLabel voltageOutputLabel;
		private static JLabel thetaOutput;
		private static JLabel magnitudeOutput;
		private static JLabel xLengthOutput;
		private static JLabel yLengthOutput;
		private static JLabel dataStatusLabel;
		private static Model model;

        private static String filePath;  //file path where the CSV will be saved
	
	    public static void main(String[] args) {
	    	
	    	//instantiate the model
	    	model = new Model();
	    	
	    	//create and set up the window
	        JFrame frame = new JFrame("Electric Potential Calculator");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(400, 350);

	        //main panel with BoxLayout to hold other panels vertically
	        JPanel mainPanel = new JPanel();
	        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

	        /**
	         * The following section covers user input and voltage output
	         */
	        
	        //panel for the coordinates (X and Y)
	        JPanel coordinatesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        JLabel coordinatesLabel = new JLabel("Point Charge Coordinates");
	        JLabel xLabel = new JLabel("X:");
	        JTextField xField = new JTextField(5);
	        JLabel yLabel = new JLabel("Y:");
	        JTextField yField = new JTextField(5);
	        coordinatesPanel.add(coordinatesLabel);
	        coordinatesPanel.add(xLabel);
	        coordinatesPanel.add(xField);
	        coordinatesPanel.add(yLabel);
	        coordinatesPanel.add(yField);

	        //add the coordinates panel to the main panel
	        mainPanel.add(coordinatesPanel);

	        //panel for the charge
	        JPanel chargePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        JLabel chargeLabel = new JLabel("Charge in Coulombs");
	        JTextField chargeField = new JTextField(5);
	        chargePanel.add(chargeLabel);
	        chargePanel.add(chargeField);
	        
	        //add the charge panel to the main panel
	        mainPanel.add(chargePanel);

	        //panel for 'create' button
	        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        JButton createButton = new JButton("Create Point Charge");
	        buttonPanel.add(createButton);
	        
	        //add the 'create' button panel to the main panel
	        mainPanel.add(buttonPanel);
	               
	        //panel for the Electric Potential output
	        JPanel potentialPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        JLabel potentialLabel = new JLabel("Electric Potential at 0,0 : ");
	        JLabel voltageOutputLabel = new JLabel(" "); // Initially empty
	        voltageOutputLabel.setPreferredSize(new Dimension(100, voltageOutputLabel.getPreferredSize().height));
	        JLabel unitLabel = new JLabel("Volts");

	        potentialPanel.add(potentialLabel);
	        potentialPanel.add(voltageOutputLabel);
	        potentialPanel.add(unitLabel);
     
	        //add the potential panel to the main panel
	        mainPanel.add(potentialPanel);
	        
	        /**
	         * The following section covers the E-Vector output
	         */
	        
	        //panel for E-Vector Title
	        JLabel titleLabel = new JLabel("E Vector at 0,0:");
	        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        titlePanel.add(titleLabel);
	        
	        //add the E-Vector Title to the main panel
	        mainPanel.add(titlePanel);

	        //panel for Theta and Magnitude
	        JPanel thetaMagnitudePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	                
	        JLabel thetaLabel = new JLabel("θ (rad): ");
	        JLabel thetaOutput = new JLabel(" "); // Placeholder for theta value
	        thetaOutput.setPreferredSize(new Dimension(80, thetaOutput.getPreferredSize().height));
	        JLabel magnitudeLabel = new JLabel("Magnitude (V/m) : ");
	        JLabel magnitudeOutput = new JLabel(" "); // Placeholder for magnitude value
	        magnitudeOutput.setPreferredSize(new Dimension(80, magnitudeOutput.getPreferredSize().height));

	        thetaMagnitudePanel.add(thetaLabel);
	        thetaMagnitudePanel.add(thetaOutput);
	        thetaMagnitudePanel.add(magnitudeLabel);
	        thetaMagnitudePanel.add(magnitudeOutput);

	        //add the theta-magnitude panel to the main panel
	        mainPanel.add(thetaMagnitudePanel);

	        //panel for xLength and yLength
	        JPanel xyLengthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        JLabel xLengthLabel = new JLabel("xLength : ");
	        JLabel xLengthOutput = new JLabel(" "); // Placeholder for xLength value
	        xLengthOutput.setPreferredSize(new Dimension(80, xLengthOutput.getPreferredSize().height));
	        JLabel yLengthLabel = new JLabel("yLength: ");
	        JLabel yLengthOutput = new JLabel(" "); // Placeholder for yLength value
	        yLengthOutput.setPreferredSize(new Dimension(80, yLengthOutput.getPreferredSize().height));

	        xyLengthPanel.add(xLengthLabel);
	        xyLengthPanel.add(xLengthOutput);
	        xyLengthPanel.add(yLengthLabel);
	        xyLengthPanel.add(yLengthOutput);

	        //add the xyLength panel to the main panel
	        mainPanel.add(xyLengthPanel);
	        
	        //panel for 'clear' button
	        JPanel clearButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        JButton clearButton = new JButton("Clear");
	        clearButtonPanel.add(clearButton);
        
	        //add the 'clear' button to main panel
	        mainPanel.add(clearButtonPanel);
	        
	        /**
	         * The following section refers to I/O buttons/Panels
	         */
	        
	        filePath = new String("output.csv");
	        JLabel dataStatusLabel = new JLabel (" "); // Placeholder for dataStatus value
	        
	        //panel for 'Export Data' button
	        JPanel exportButtonPanel = new JPanel (new FlowLayout(FlowLayout.LEFT));
	        JButton exportButton = new JButton("Export Data");
	        exportButtonPanel.add(exportButton);
	        
	        //add the 'export' button to the main panel
	        mainPanel.add(exportButtonPanel);
  
	        //panel for 'Export Data' button
	        JPanel clearDataButtonPanel = new JPanel (new FlowLayout(FlowLayout.LEFT));
	        JButton clearDataButton = new JButton("Clear Data");
	        exportButtonPanel.add(clearDataButton);
	                
	        //add the 'clear data' button to the main panel
	        mainPanel.add(clearDataButtonPanel);
        
	        //add the main panel to the frame
	        frame.add(mainPanel, BorderLayout.NORTH);

	    	//create and attach the action listener for 'add particle' button
	    	PointChargeActionListener pointChargeListener = new PointChargeActionListener(
	    	    model, xField, yField, chargeField, voltageOutputLabel, thetaOutput, magnitudeOutput, xLengthOutput, yLengthOutput);
	    	createButton.addActionListener(pointChargeListener);
	    	
	    	//create and attach the action listener for 'clear' button
	    	ClearActionListener clearListener = new ClearActionListener(model, xField, yField, chargeField,
	    			voltageOutputLabel, thetaOutput, magnitudeOutput, xLengthOutput, yLengthOutput);
	    	clearButton.addActionListener(clearListener);
	    	
	    	//create and attach the action listener for the 'export' button
	    	ExportDataListener exportListener = new ExportDataListener(model, voltageOutputLabel, thetaOutput, magnitudeOutput, xLengthOutput, yLengthOutput);
	        exportButton.addActionListener(exportListener);
	        
	        //create and attach the action listener for the 'export' button
	    	ClearDataListener clearDataListener = new ClearDataListener(filePath, dataStatusLabel);
	    	clearDataButton.addActionListener(clearDataListener);
	        
	        //display the window
	        frame.setVisible(true);
	    }
    
	    //helper method to parse input to double
	    private double parseInput(String input) {
	        try {
	            return Double.parseDouble(input);
	        } catch (NumberFormatException e) {
	        	System.err.println("parseDouble threw an exception");
	        	//placeholder return value
	            return 0.0; 
	        }
	    }
	    
	    /**
	     * exports data to a csv file in the working directory of this java project.
	     * Data is collected by pulling from the GUI's output JLabels, stored in a String array,
	     * then passed to the DataExporter method exportToCSV to write to file.
	     */
	    private void exportData() {
	    	
	        List<ChargedParticle> particles = model.getChargedParticle();

	        //collect output values from GUI labels
	        String[] outputValues = new String[] {	
	        	voltageOutputLabel.getText(),
	            thetaOutput.getText(),
	            magnitudeOutput.getText(),
	            xLengthOutput.getText(),
	            yLengthOutput.getText()
	        };

	        try {
	        	//feeds the list of particles to the DataExporter class,
	        	//plus whatever is stored in the output JLabels
	            DataExporter.exportToCSV(particles, outputValues, filePath);
	            System.out.println("Data exported successfully to " + filePath);	            
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.err.println("Failed to export data to CSV. IO Exception.");
	        }
	    }
}
