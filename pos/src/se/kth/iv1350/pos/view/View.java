package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;

public class View 
{
	private Controller controller;
	
	/**
	 * Creates a controller so the entire sale process can begin. A controller is necessary, since we're
	 * using MVC (Model, View, Controller).
	 * @param controller
	 */
	public View(Controller controller)
	{
		this.controller = controller;
		
	}
	
	/**
	 * A testing void for starting a test sale, which will tell the controller to start it's sale process.
	 */
	public void startATestSale()
	{
		System.out.println("\nStarting a test sale...");
		controller.startSale();
		
		System.out.println("\nAdding items to shoppinglist...");
		controller.scanItem("0001", 1);
		controller.scanItem("0007", 2);
		controller.scanItem("0003", 1);
		controller.scanItem("0001", 1);
		controller.scanItem("0002", 3);
		controller.scanItem("0004", 1);
		
		System.out.println("\nEnding sale...");
		controller.endSale();
		
		System.out.println("\nCustomer paid x amount. completeing sale...");
		double x = 100;
		controller.completedSale(x);
	}
}
