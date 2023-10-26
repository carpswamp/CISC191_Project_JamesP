import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	
    private Model model;
    private ElectricPotentialView view;
    private List<ChargedParticle> particles;

    //constructors
    public Controller(Model model, ElectricPotentialView view){
        this.model = model;
        this.view = view;
        
        // Attach an ActionListener to a button in the View
        view.addButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the button click event
                handleButtonClicked();
            }
        });
    }
    

	public void handleButtonClicked() {
    // This method is called when the button in the View is clicked
    // You can perform actions here, such as updating the Model or the View
	
	//create new Coordinate object
	double x = view.getX(); 
	double y = view.getY();	
	Point newPoint = new Point(x,y); 

	double chargeStrength = view.getChargeStrength();

    // Update the Model and View based on user input
    model.createChargedParticle(chargeStrength, newPoint);
    model.calculateElectricPotential();
    
    // Notify the View to update
    view.updateView(); 
	}

    // Method to handle user input and calculate electric potential
    public void calculateElectricPotential(List<ChargedParticle> particles, Point point) {
       // double electricPotential = model.calculateElectricPotential.getSuperPosition(particles, point);
       //view.displayElectricPotential(electricPotential);
    }
    
    //notify the view to update itself
    public void modelChanged() {
    	view.updateView();
    }
    
    public void initializeView() {
        view.addButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle button click event
                handleUserInput(view.getX(), view.getY(), view.getChargeStrength());
            }

			private void handleUserInput(double x, double y,
					double chargeStrength)
			{
				// TODO Auto-generated method stub
				
			}
        });
    }
    
    public void userMakesParticle(double chargeStrength, Point coordinate) {
    	model.createChargedParticle(chargeStrength, coordinate);
    	modelChanged();
    }
    
    public void userCalculate(Point point) {
    	double voltageResult = model.calculateElectricPotential(point);
    	view.displayElectricPotential(voltageResult);
    	modelChanged();
    }

//    public void userInputReceived(String input) {
//        // Validate and process input, possibly updating the model
//        double value = validateAndParse(input);
//        double result = model.calculatePotential(value);
//        view.updateResult(result);
//    }

    private double validateAndParse(String input) {
        // Some basic input validation and parsing, throwing errors if necessary
        return Double.parseDouble(input); // simplified, add error handling!
    }
}