import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataExporter {

    /**
     * Exports the charged particles and output values to a CSV file.
     * 
     * @param particles List of ChargedParticle objects.
     * @param outputValues Array of output values from the GUI.
     * @param filePath The path of the CSV file to write.
     * @throws IOException If an I/O error occurs.
     */
	public static void exportToCSV(List<ChargedParticle> particles, String[] outputValues, String filePath) throws IOException {
	    
	    System.out.println("Attempting to export to CSV at " + filePath);
	    
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	        
	        //write header
	        writer.write("X Coordinate,Y Coordinate,Charge\n");
	        
	        //write particle details in separate rows
	        for (ChargedParticle particle : particles) {
	        	
	            writer.write(particle.getX() + "," +
	                         particle.getY() + "," +
	                         particle.getQ());
	            
	            writer.newLine();
	        }

	        //write header for outputValues dump
	        writer.write("|E| Vector Data\n");
	        writer.write("voltageOutputLabel,thetaOutput,magnitudeOutput,xLengthOutput,yLengthOutput\n");
	        
	        //dump outputValues to end of file
	        for (int i = 0; i < outputValues.length; i++) {
	        	
	            writer.write(outputValues[i]);
	            if (i < outputValues.length - 1) {
	                writer.write(",");
	            }
	        }

	        writer.newLine();
	    }
	    System.out.println("Finished writing CSV at " + filePath);
	}
	
	
	
	/**
     * Loads data from a CSV file into the Model and updates the View.
     * 
     * @param filePath The path of the CSV file to read.
     * @param model The model to update with loaded data.
     * @param view The view to update with loaded data.
     * @throws IOException If an I/O error occurs.
     */
    public static void loadFromCSV(String filePath, Model model, View view) throws IOException {
    	
        List<ChargedParticle> particles = new ArrayList<>();
        List<String> outputValues = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isOutputValueSection = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("|E| Vector Data")) {
                    isOutputValueSection = true;
                    //skip the header
                    continue; 
                }

                if (!isOutputValueSection) {
                	
                    //parse and add particle data to the list
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        ChargedParticle particle = new ChargedParticle(
                            Double.parseDouble(parts[0]),
                            Double.parseDouble(parts[1]),
                            Double.parseDouble(parts[2]));
                        particles.add(particle);
                    }
                } else {
                    // Add output value to the list
                    outputValues.add(line);
                }
            }
        }

        //update model and view with the loaded data
//        model.setParticles(particles);
//        model.setOutputValues(outputValues);
//        view.updateWithData(particles, outputValues);
    }
    
	/**
	 * clears the data in the CSV file, leaving only the header
	 * @param filePath
	 * @throws IOException
	 */
	public static void clearCSV(String filePath) throws IOException {
	    
	    System.out.println("Attempting to clear CSV at " + filePath);
	    
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	        
	        //write only the header line
	        writer.write("X Coordinate,Y Coordinate,Charge\n");
	    }

	    System.out.println("Finished clearing CSV at " + filePath);
	}


}