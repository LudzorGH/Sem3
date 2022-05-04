package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.model.ItemIdentifier;
import se.kth.iv1350.pos.model.Sale;


public class ItemAddManager 
{
	private Sale sale;
	private ItemIdentifier itemIdentifier;
	
	/**
	 * The method takes both the amount of goods, as well as it's barcode/item identifier 
	 * and adds those goods to the Sale by first identifying the item, and then adding the amount.
	 * @param sale
	 */
	public ItemAddManager(Sale sale)
	{
		this.sale = sale;
		itemIdentifier = new ItemIdentifier();
		
	}
	
	/**
	 * Passes the barcode to ItemIdentifier for evaluation (either gets an item back, or invalid item)
	 * An invalid item is represented by null. then checks for the same item in the current sale
	 * and adds the amount if there's none, otherwise it ups the number already in the sale by the amount.
	 * @param barcode
	 * @param amount
	 */
	public void evaluateScannedItem(String barcode, int amount) 
	{
		Item requestedItem = itemIdentifier.getItemWithIdentifier(barcode);
		if (requestedItem == null)
		{
			//INVALID IDENTIFIER
			System.out.println("\n####################################################\n"
					+ "    Itemidentifier invalid. No such item found!"
					+ "\n####################################################");
		}
		else
		{
			addAmountOfItem(requestedItem, amount);
		}
	}
	
	private void addAmountOfItem(Item item, int amount)
	{
		for(int i = 0; i < amount; i++)
		{
			sale.addItemToSale(item);
		}
			
	}
	
}
