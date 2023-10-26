import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ElectricPotentialView
{
	private JButton button;
	private JTextField xField;
	private JTextField yField;
	private JTextField chargeStrengthField;
	
    public ElectricPotentialView() {
        	// Initialize your JButton
        	button = new JButton("Click Me");
    	
            // Initialize your JTextFields
            xField = new JTextField(10); // Replace "10" with your desired width
            yField = new JTextField(10);
            chargeStrengthField = new JTextField(10);

            // Create and configure your UI elements (e.g., labels) as needed
            JLabel xLabel = new JLabel("X Coordinate:");
            JLabel yLabel = new JLabel("Y Coordinate:");
            JLabel chargeStrengthLabel = new JLabel("Charge Strength:");

            // Create a panel to organize your components
            JPanel inputPanel = new JPanel(new GridLayout(3, 2)); // Adjust rows and columns as needed
            inputPanel.add(xLabel);
            inputPanel.add(xField);
            inputPanel.add(yLabel);
            inputPanel.add(yField);
            inputPanel.add(chargeStrengthLabel);
            inputPanel.add(chargeStrengthField);

            // Add the inputPanel to  main frame or panel
            // ...

            // Add other UI components and configure layout
            // ...
        }
 
	public void displayElectricPotential(double electricPotential)
	{
		// TODO Auto-generated method stub
		
	}

	public void updateView()
	{
		// electricPotentialLabel.setText("Electric Potential: " + electricPotential);
        // particleInfoTextArea.setText(particleInfo);

        // Repaint or revalidate the components if needed
        // frame.repaint();
        // panel.revalidate();
	}

    // Method to add an ActionListener to the button
    public void addButtonActionListener(ActionListener listener) {
        button.addActionListener(listener);
    }

	public double getX()
	{
		String xText = xField.getText();
        try {
            return Double.parseDouble(xText);
        } catch (NumberFormatException e) {
            // Handle parsing error or return a default value
            return 0.0;
        }
	}

	public double getY()
	{
        String yText = yField.getText();
        try {
            return Double.parseDouble(yText);
        } catch (NumberFormatException e) {
            // Handle parsing error or return a default value
            return 0.0;
        }
	}

    public double getChargeStrength() {
    	String chargeStrengthText = chargeStrengthField.getText();
    	try {
            return Double.parseDouble(chargeStrengthText);
        } catch (NumberFormatException e) {
            // Handle parsing error or return a default value
            return 0.0;
        }
    }

}
