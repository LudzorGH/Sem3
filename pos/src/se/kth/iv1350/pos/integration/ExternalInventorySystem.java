package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.Item;

public class ExternalInventorySystem 
{
	private final Item[] inventory = new Item[5];
	
	/**
	 * The inventory and it's contents this instance.
	 */
	public ExternalInventorySystem()
	{
		/*
		 * #################---IMPORTANT---#################
		 * The Item() method takes the following input:
		 * int identifier, String name, String itemDescription, float price, int amount, float vatRate
		 */
		this.inventory[0] = new Item("0001", "McDonkel's Fries", "The tastiest fries around", 2.99f, 0, 25f);
		this.inventory[1] = new Item("0002", "Konka Kola", "Real Magic is only a sip away", 3.49f, 0, 25f);
		this.inventory[2] = new Item("0003", "Casio Saxophone", "Does Casio even make saxophones?", 11.95f, 0, 12f);
		this.inventory[3] = new Item("0004", "KTH Merch", "The coolest merch out there", 32f, 0, 6f);
		this.inventory[4] = new Item("0005", "Amazon Gift Cards", "Totally legit", 49.99f, 0, 12f);
	}
	
	/**
	 * This method takes an identifier/barcode and finds the respective item in the inventory. 
	 * Tried to throw exception but couldn't figure out how, so it just returns null.
	 * @param identifier: the barcode that identifies each item.
	 * @return the item with the inputed barcode, or null if no such item exists.
	 */
	public Item getItem(String identifier) //throws InvalidItemIdentifierException
	{
		
		int amountOfProducts = inventory.length;
		for (int i = 0; i < amountOfProducts ; i++)
		{
			if (inventory[i].getIdentifier().equals(identifier))
			{
				
				return inventory[i];
			}
		}
		
		return null;
		//throw new InvalidItemIdentifierException("An item with the submitted identifier (" + identifier + ") was not found");
		
	}
}
