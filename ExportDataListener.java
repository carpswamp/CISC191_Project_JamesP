import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import java.util.List;

public class ExportDataListener implements ActionListener {

    private Model model;
    private JLabel voltageOutputLabel;
    private JLabel thetaOutput;
    private JLabel magnitudeOutput;
    private JLabel xLengthOutput;
    private JLabel yLengthOutput;

    public ExportDataListener(Model model, JLabel voltageOutputLabel, JLabel thetaOutput, JLabel magnitudeOutput, JLabel xLengthOutput, JLabel yLengthOutput) {
        this.model = model;
        this.voltageOutputLabel = voltageOutputLabel;
        this.thetaOutput = thetaOutput;
        this.magnitudeOutput = magnitudeOutput;
        this.xLengthOutput = xLengthOutput;
        this.yLengthOutput = yLengthOutput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
        //collect output values from GUI labels
        String[] outputValues = new String[] {
        		voltageOutputLabel.getText(),
        		thetaOutput.getText(),
        		magnitudeOutput.getText(),
        		xLengthOutput.getText(),
        		yLengthOutput.getText()
        };

        // File path where the CSV will be saved
        String filePath = "output.csv";

        try {
        	//grab particles from the model
            List<ChargedParticle> particles = model.getChargedParticle();
            
            //call exportToCSV
            DataExporter.exportToCSV(particles, outputValues, filePath);
            System.out.println("Data exported to " + filePath);
            
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Failed to export data to CSV.");
        }
    }
}