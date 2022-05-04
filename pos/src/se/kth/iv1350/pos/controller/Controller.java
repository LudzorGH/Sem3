package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.ItemAddManager;

import se.kth.iv1350.pos.integration.ExternalAccountingSystem;
import se.kth.iv1350.pos.integration.ExternalInventorySystem;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.integration.Register;
/*
import se.kth.iv1350.pos.model.
import se.kth.iv1350.pos.model.
import se.kth.iv1350.pos.model.
*/

public class Controller 
{
	private ExternalAccountingSystem extAccountingSys;
	private ExternalInventorySystem extInventorySys;
	private Printer printer;
	private Register register;
	
	public Controller()
	{
		extAccountingSys = new ExternalAccountingSystem();
		//extInventorySys = new ExternalInventorySystem();
		printer = new Printer();
		register = new Register();
	}
	
	private Sale sale;
	private ItemAddManager itemAddManager;
	
	/**
	 * Creates a new sale. This method must be called before doing anything else during a sale. 
	 * It also creates an ItemAddManager, and lets it create its (the ItemAddManager)
	 * own itemIdentifier through the ItemAddManager() method.
	 * 
	 * Simplification: 
	 * Controller -> itemAdder = ItemAddManager
	 * ItemAddManager -> itemIdentifier = ItemIdentifier
	 */
	public void startSale()
	{
		sale = new Sale();
		itemAddManager = new ItemAddManager(sale);
	}
	
	/**
	 * Gets a barcode/item-identifier from View to then pass on 
	 * for identifying of the item so it can be added to the sale.
	 * 
	 * @param args The method takes an item-identifier (a barcode) as parameter, 
	 * as well as an integer of the amount.
	 */
	public void scanItem(String barcode, int amount)
	{
		itemAddManager.evaluateScannedItem(barcode, amount);
	}
	
	
	public void endSale()
	{
		double totalPrice = sale.getRunningTotal();
		
		System.out.println("\nSale has ended.\nTotal price incl. VAT is: $" + totalPrice);
		
	}
	public void completedSale(double amountPaid)
	{
		extAccountingSys.logSale(sale.toString());
		double change = register.addPaymentToRegister(sale.getRunningTotal(), amountPaid);
		printer.printReceipt(sale, amountPaid, change);
	}
}
