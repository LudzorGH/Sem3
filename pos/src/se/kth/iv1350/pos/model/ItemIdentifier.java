package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.model.Item;
import se.kth.iv1350.pos.integration.ExternalInventorySystem;

public class ItemIdentifier 
{
	private ExternalInventorySystem inventorySys;
	
	public ItemIdentifier()
	{
		inventorySys = new ExternalInventorySystem();
	}
	
	/**
	 * This method takes a barcode and sends it to the External Inventory System. The system should
	 * return either an item, or that the code is invalid. The method then sends that information to the ItemAddManager
	 * @param barcode 
	 * @return the item with the corresponding barcode
	 */
	public Item getItemWithIdentifier(String barcode)
	{
		Item item = inventorySys.getItem(barcode);
		return item;
	}
}
