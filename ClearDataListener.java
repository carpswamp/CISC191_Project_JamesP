import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import java.util.List;

public class ClearDataListener implements ActionListener {

    private Model model; 
    private String filePath;
    private JLabel dataStatusLabel;

    public ClearDataListener(String filePath, JLabel dataStatusLabel) {
    	this.dataStatusLabel = dataStatusLabel;
        this.filePath = filePath;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
        	//invoke clearCSV and report
        	DataExporter.clearCSV(filePath); 
        	dataStatusLabel.setText("CSV data cleared");
           
            System.out.println("CSV data has been cleared.");
            
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("ClearData ActionListener threw an IO exception");
        }
    }
}