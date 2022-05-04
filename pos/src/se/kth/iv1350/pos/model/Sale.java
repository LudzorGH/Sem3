package se.kth.iv1350.pos.model;

import java.time.LocalTime;

public class Sale 
{
	private Item[] addedItems = new Item[0];
	private LocalTime starttimeOfSale;
	
	private double runningTotal;
	
	/**
	 * This is the sale function. It is called at the start of a new sale, and saves the time
	 * for use later in the receipt and Sale log.
	 */
	public Sale() 
	{
		starttimeOfSale = LocalTime.now();
	}
	
	/**
	 * This method is called for adding an item to the current sales list of items.
	 * this method only manages adding an item to the list, through other methods. 
	 * first checks if the item is already on the list. 
	 * if true, increase the number of that item. otherwise, increase the list size and then add the item.
	 * then lastly, update the running total and display the changes.
	 * @param item
	 */
	public void addItemToSale(Item item)
	{
		int arraySlot = isItemAlreadyInList(item);
		if (arraySlot == -1)
		{
			addedItems = copyArrayAndAddSlots(addedItems, 1);
			addItemToLastSlot(item);
		}
		else
		{
			increaseNumberOfItem(arraySlot);
		}
		updateRunningTotal(item);
		displayItemAdded(item);
	}
	
	
	private void addItemToLastSlot(Item item)
	{
		addedItems[addedItems.length-1] = item;
		item.upAmountByOne();
	}
	
	
	private void updateRunningTotal(Item item)
	{
		runningTotal += getItemPriceWithVAT(item);
	}
	
	
	private double getItemPriceWithVAT(Item item)
	{
		return item.getPrice()*(1+item.getVATRate()/100);
	}
	
	
	private void increaseNumberOfItem(int arraySlot)
	{
		addedItems[arraySlot].upAmountByOne();
	}
	
	
	private int isItemAlreadyInList(Item item)
	{
		String identifier = item.getIdentifier();
		int differentProducts = addedItems.length;
		for(int i = 0; i < differentProducts; i++)
		{
			if (addedItems[i].getIdentifier().equals(identifier))
			{
				return i;
			}
		}
		return -1; //The return can't be -1 unless the item is not in the list.
	}
	
	
	private Item[] copyArrayAndAddSlots(Item[] originalArray, int slots)
	{
		int originalArrayLength = originalArray.length;
		Item[] tempArray = new Item[originalArrayLength + slots];
		
		for (int i = 0; i < originalArrayLength; i++)
		{
			tempArray[i] = originalArray[i];
		}
		return tempArray;
	}
	
	
	private void displayItemAdded(Item item)
	{
		System.out.println("\nAdded " + item.getName() + " to cart." +
				"\n  VAT: " + item.getVATRate() + "%" +
				"\n  Description: '" + item.getItemDescription() + "'" +
				"\n  Price: $" + item.getPrice() +
				"\n  Amount: " + item.getAmount() + 
				"\n\nRunning Total: $" + runningTotal);
		
	}
	
	/**
	 * method returns the running total of the sale
	 * @return the running total
	 */
	public double getRunningTotal()
	{
		return runningTotal;
	}
	
	/**
	 * To make the receipt printing n accounting logging easier. This only
	 * gives out the time of day, items(toString) and total price. NOT amountPaid or change.
	 */
	public String toString()
	{
		String itemString = "";
		for (int i = 0; i < addedItems.length; i++)
		{
			itemString = itemString + addedItems[i].toString() + "\n\n";
		}
		return "Time of day: " + starttimeOfSale.toString() + 
				"\n\nItems purchased: \n" + itemString + 
				"\nTotal price: $" + getRunningTotal();
	}
}
