package se.kth.iv1350.pos.startup;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.view.View;



/**
 * This starts the application. Has the main method.
 */
public class Main 
{
	
	/**
	 * The main method that starts the application.
	 * 
	 * @param args The application does not take any command line parameters.
	 */
	public static void main(String[] args)
	{
		Controller controller = new Controller();
		View view = new View(controller);
		view.startATestSale();
	}
}
