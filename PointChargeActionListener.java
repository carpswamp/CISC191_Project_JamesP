import javax.swing.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class PointChargeActionListener implements ActionListener {

    private JTextField xField, yField, chargeField;
    private JLabel outputLabel, thetaOutput, magnitudeOutput, xLengthOutput, yLengthOutput;
    private Model model;
    
    public PointChargeActionListener(Model model, JTextField xField, JTextField yField, JTextField chargeField,
                                     JLabel outputLabel, JLabel thetaOutput, JLabel magnitudeOutput,
                                     JLabel xLengthOutput, JLabel yLengthOutput) {
        this.model = model;
    	this.xField = xField;
        this.yField = yField;
        this.chargeField = chargeField;
        this.outputLabel = outputLabel;
        this.thetaOutput = thetaOutput;
        this.magnitudeOutput = magnitudeOutput;
        this.xLengthOutput = xLengthOutput;
        this.yLengthOutput = yLengthOutput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            //retrieve and parse input values
            double xCoord = Double.parseDouble(xField.getText());
            double yCoord = Double.parseDouble(yField.getText());
            double charge = Double.parseDouble(chargeField.getText());
            Point point = new Point(xCoord, yCoord);

            //create a charged particle and add it to the model
            model.createChargedParticle(charge, point);

            //calculate the electric potential at the origin
            double electricPotential = model.calculateVoltage(new Point(0, 0));

            //update the GUI with the electric potential
            outputLabel.setText(String.format("%.8e", electricPotential));

            //retrieve the electric field vector details
            Vector eVector = model.calculateElectricFieldVector(new Point(0, 0));
            double eVectorTheta = eVector.calculateDirection();
            double eVectorMagnitude = eVector.calculateMagnitude();
            double eVectorXLength = eVector.getXLength();
            double eVectorYLength = eVector.getYLength();

            //update GUI components with E-Vector details
            thetaOutput.setText(String.format("%.2f", eVectorTheta));
            magnitudeOutput.setText(String.format("%.2e", eVectorMagnitude));
            xLengthOutput.setText(String.format("%.4e", eVectorXLength));
            yLengthOutput.setText(String.format("%.4e", eVectorYLength));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers for coordinates and charge.");
        }
    }
}

