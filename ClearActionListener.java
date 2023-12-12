import javax.swing.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class ClearActionListener implements ActionListener {

    private JTextField xField, yField, chargeField;
    private JLabel outputLabel, thetaOutput, magnitudeOutput, xLengthOutput, yLengthOutput;
    private Model model;
    
    /**
     * Clears all particles out of the model, then restores all output labels
     * to their placeholder 'bank' values
     * 
     * @param model
     * @param xField
     * @param yField
     * @param chargeField
     * @param outputLabel
     * @param thetaOutput
     * @param magnitudeOutput
     * @param xLengthOutput
     * @param yLengthOutput
     */
    public ClearActionListener(Model model, JTextField xField, JTextField yField, JTextField chargeField,
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
    	
    		model.clearAllParticles();
    	
            //update GUI components with blank place holders
            outputLabel.setText(" ");
            thetaOutput.setText(" ");
            magnitudeOutput.setText(" ");
            xLengthOutput.setText(" ");
            yLengthOutput.setText(" ");

    }
}
