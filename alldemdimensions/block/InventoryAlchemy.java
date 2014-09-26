package alldemdimensions.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;

public class InventoryAlchemy implements IInventory
{
    public ItemStack[] items = new ItemStack[20];
	public World worldObj;
	public static final byte SLOT_CRAFT_START = 0;
	public static final byte SLOT_CRAFT_END = 8;
	public static final byte SLOT_CATALYST = 9;
	public static final byte SLOT_MODIFIER = 10;
	public static final byte SLOT_BASE_ITEM = 11;
	public static final byte SLOT_RESULT_START = 12;
	public static final byte SLOT_RESULT_END = 19;
	
	public InventoryAlchemy(World world)
	{
		worldObj = world;
	}
	//make sure item quantity does not exceed 64
	public void checkForResult(Random random)
	{
		//get rid of previous results
		for(byte b = SLOT_RESULT_START; b <= SLOT_RESULT_END; b++)
		{
			setInventorySlotContents(b, null);
		}
		System.out.println("New results: ");
		if(getStackInSlot(SLOT_CATALYST) != null && getStackInSlot(SLOT_CATALYST).getItem() == AllDemDimensions.phosphorus)//transmutation
		{
			Item modifier = null;
			ItemStack baseItem = null;
			AlchemyEntry baseItemEntry = null;
			if(getStackInSlot(SLOT_MODIFIER) != null)
			{
				modifier = getStackInSlot(SLOT_MODIFIER).getItem();
			}
			if(getStackInSlot(SLOT_BASE_ITEM) != null)
			{
				baseItem = getStackInSlot(SLOT_BASE_ITEM);
				for(AlchemyEntry baseItemLookup : AlchemyManager.getAllTransmutation())
				{
					if(baseItem.getItem() == baseItemLookup.itemId && (baseItemLookup.itemMeta == -1 || baseItem.getItemDamage() == baseItemLookup.itemMeta))
					{
						baseItemEntry = baseItemLookup;
                        break;
					}
				}
			}
			ItemStack itemstack;
			ArrayList<AlchemyEntry> ingredients = new ArrayList<AlchemyEntry>();
			ArrayList<Byte> dimension = new ArrayList<Byte>();
			ArrayList<Boolean> isBlock = new ArrayList<Boolean>();
			ArrayList<Boolean> isOrganic = new ArrayList<Boolean>();
			int totalValue = 0;
            boolean variedIngredients = false;
            AlchemyEntry lastEntry = null;
			for(byte b = SLOT_CRAFT_START; b <= SLOT_CRAFT_END; b++)
			{
				itemstack = getStackInSlot(b);
				if(itemstack != null)
				{
					AlchemyEntry entry = AlchemyManager.getTransmutationFromIdAndMetadata(itemstack.getItem(), itemstack.getItemDamage());
					if(entry != null)
					{
	                    if(lastEntry != null && entry != lastEntry)
	                    {
	                        variedIngredients = true;
	                    }
	                    lastEntry = entry;
						ingredients.add(entry);
						dimension.add(entry.dimension);
						isBlock.add(entry.isBlock);
						isOrganic.add(entry.isOrganic);
						totalValue += entry.value * itemstack.stackSize;
					}
				}
			}
			if(ingredients.size() <= 0)
			{
				return;
			}
			int tries = 0;
			byte resultsFilled = 0;
			AlchemyEntry randomEntry;
			ArrayList<AlchemyEntry> list = AlchemyManager.getAllTransmutation();
			ArrayList<Item> resultIds = new ArrayList<Item>();
			ArrayList<Integer> resultMetas = new ArrayList<Integer>();
			while(resultsFilled < 8)
			{
				float chance = 1.0F;
				float quantity = 1.0F;
				if(baseItemEntry != null && tries == 0)
				{
					randomEntry = baseItemEntry;
				} else
				{
					randomEntry = list.get(random.nextInt(list.size() - 1));
				}
				if((!ingredients.contains(randomEntry) || variedIngredients) && (!randomEntry.exclusive || randomEntry == baseItemEntry) && (!resultIds.contains(randomEntry.itemId) && !resultMetas.contains(randomEntry.itemMeta)) && randomEntry.value <= totalValue && totalValue / randomEntry.value <= 64)
				{
					chance *= randomEntry.rarity;

					for(byte b = 0; b < dimension.size(); b++)
					{
						if(dimension.get(b) == randomEntry.dimension)
						{
							chance *= 0.6F;//-= (9 - b);
							quantity *= 1.2F;
						}
					}
					for(byte b1 = 0; b1 < isBlock.size(); b1++)
					{
						if(isBlock.get(b1) == randomEntry.isBlock)
						{
							chance *= 0.95F;//-= (9 - b1);
							quantity *= 1.04F;
						}
					}
					for(byte b2 = 0; b2 < isOrganic.size(); b2++)
					{
						if(isOrganic.get(b2) == randomEntry.isOrganic)
						{
							chance *= 0.9F;//-= (9 - b2);
							quantity *= 1.07F;
						}
					}
					if(modifier == AllDemDimensions.royalJelly)
					{
						chance *= 0.5F;
						quantity *= 1.25F;
					}
					System.out.println(randomEntry.itemId + ": " + chance);
					int chanceInteger = (int)(5 * chance);
					if(chanceInteger <= 0)
					{
						chanceInteger = 1;
					}
					if(baseItemEntry != null && tries == 0)
					{
						chanceInteger = 1;
						quantity *= 1.35D;
					}
					quantity += (random.nextFloat() - 0.5F) * 0.2F * quantity;
					int quantityInteger = (int)((totalValue / randomEntry.value) * quantity);
					if(random.nextInt(chanceInteger) == 0 && quantityInteger <= 64 && ((getStackInSlot(SLOT_CATALYST).stackSize - 1) * getStackInSlot(SLOT_CATALYST).getMaxDamage() * -1) + getStackInSlot(SLOT_CATALYST).getItemDamage() + quantityInteger <= getStackInSlot(SLOT_CATALYST).getMaxDamage())
					{
						if(quantityInteger <= 0)
						{
							quantityInteger = 1;
						}
						if(quantityInteger > 64)
						{
							quantityInteger = 64;
						}
						Item item = null;
						if(randomEntry.itemId instanceof Block)
						{
							item = Item.getItemFromBlock((Block)randomEntry.itemId);
						} else
						if(randomEntry.itemId instanceof Item)
						{
							item = (Item)randomEntry.itemId;
						}
						ItemStack result = new ItemStack((Block)randomEntry.itemId, quantityInteger, randomEntry.itemMeta);
						resultIds.add(item);
						resultMetas.add(randomEntry.itemMeta);
						setInventorySlotContents(SLOT_RESULT_START + resultsFilled, result);
						System.out.println("    " + result.getDisplayName());
						resultsFilled++;
					}
				}
				tries++;
				if(tries > 256)
				{
					break;
				}
			}
		}
		
		if(getStackInSlot(SLOT_CATALYST) != null && getStackInSlot(SLOT_CATALYST).getItem() == AllDemDimensions.amber)//reconstruction
		{
			ItemStack result = matchRecipe(AlchemyManager.getAllReconstruction());
			if(result != null)
			{
				setInventorySlotContents(SLOT_RESULT_START, result.copy());
			}
		}
		
		if(getStackInSlot(SLOT_CATALYST) != null && getStackInSlot(SLOT_CATALYST).getItem() == AllDemDimensions.ghostPowder)//deconstruction
		{
			ItemStack ingredient = null;
			ItemStack[] result = null;
			for(byte b = SLOT_CRAFT_START; b <= SLOT_CRAFT_END; b++)
			{
				if(ingredient != null && getStackInSlot(b) != null)
				{
					return;
				} else
				if(ingredient == null)
				{
					ingredient = getStackInSlot(b);
				}
			}
			if(ingredient == null)
			{
				return;
			}
			for(ItemStack recipe : AlchemyManager.getAllDeconstruction().keySet())
			{
				if(recipe.getItem() == ingredient.getItem() && recipe.getItemDamage() == ingredient.getItemDamage())
				{
					result = AlchemyManager.getAllDeconstruction().get(recipe);
					if(result != null)
					{
						for(byte b1 = 0; b1 < result.length; b1++)
						{
							setInventorySlotContents(SLOT_RESULT_START + b1, result[b1].copy());
						}
						return;
					}
				}
			}
		}
		
		if(getStackInSlot(SLOT_CATALYST) != null && getStackInSlot(SLOT_CATALYST).getItem() == AllDemDimensions.bucketNectar)//combination
		{
			ItemStack result = matchRecipe(AlchemyManager.getAllCombination());
			if(result != null)
			{
				setInventorySlotContents(SLOT_RESULT_START, result.copy());
			}
		}
	}
	
	public void onResultTaken(ItemStack itemTaken)
	{
		for(byte b = SLOT_CRAFT_START; b <= SLOT_CRAFT_END; b++)
		{
			setInventorySlotContents(b, null);
		}
		for(byte b1 = SLOT_RESULT_START; b1 <= SLOT_RESULT_END; b1++)
		{
			setInventorySlotContents(b1, null);
		}
                ItemStack itemstack;
		int damage;
		int sizeTaken = itemTaken.stackSize;
        for(byte b = SLOT_CATALYST; b <= SLOT_MODIFIER; b++)
        {
            itemstack = getStackInSlot(b);
            if(itemstack != null)
            {
                damage = itemstack.getItemDamage();
                if(sizeTaken + damage >= itemstack.getMaxDamage())
                {
                	itemstack.stackSize--;
                	itemstack.setItemDamage(sizeTaken + damage - itemstack.getMaxDamage());
                } else
                {
                	itemstack.setItemDamage(damage + sizeTaken);
                }
                if(itemstack.stackSize <= 0)
                {
                	setInventorySlotContents(b, null);
                }
            }
        }
	}
	
	public void onCatalystOrModifierTaken(ItemStack itemstack, Slot slot)
	{
		if(itemstack != null && itemstack.getItemDamage() > 0)
		{
			itemstack.setItemDamage(0);
			itemstack.stackSize--;
			if(itemstack.stackSize <= 0)
			{
				itemstack.stackSize = 0;
				slot.putStack(null);
			}
		}
	}
	
	public ItemStack matchRecipe(HashMap<LinkedList<ItemStack>, ItemStack> recipeMap)
	{
		ItemStack[] ais = new ItemStack[SLOT_CRAFT_END + 1];
		for(byte b = SLOT_CRAFT_START; b <= SLOT_CRAFT_END; b++)
		{
			ais[b] = items[b];
		}
		LinkedList<ItemStack> ingredients = AlchemyManager.sortRecipe(ais);
		boolean matches = false;
		int rate = 1;
		for(LinkedList<ItemStack> recipe : recipeMap.keySet())
		{
			if(ingredients.size() > 0 && (recipe.size() == ingredients.size() || ingredients.size() % recipe.size() == 0))//check whether ingredients size is a multiple of recipe size
			{
				///////
				if(recipe.size() != ingredients.size() && ingredients.get(0).getItem() == recipe.get(0).getItem() && ingredients.get(0).getItemDamage() == recipe.get(0).getItemDamage())
				{
					rate = ingredients.size() / recipe.size();
					int offset = 0;
					ItemStack prevStack = null;
					ItemStack curStack = null;
					LinkedList<ItemStack> condensedList = new LinkedList<ItemStack>();
					boolean isCondensible = false;
					if(rate <= 64)
					{
						for(int b = 0; b < recipe.size(); b++)//rate
						{
							offset = b * rate;//+
							for(int b1 = offset; b1 < offset + rate; b1++)
							{
								curStack = ingredients.get(b1);
								if(b1 == offset)
								{
									prevStack = curStack;
								} else
								if(prevStack.getItem() == curStack.getItem() && prevStack.getItemDamage() == curStack.getItemDamage())
								{
									prevStack = curStack;
									isCondensible = true;
								} else
								{
									isCondensible = false;
									b1 = offset + rate;
									//exit from loop
								}
							}
							if(isCondensible && curStack != null)
							{
								condensedList.add(curStack);
							} else
							{
								b = rate;
							}
						}
					}
					ingredients = condensedList;
				}
				///////
				byte b = 0;
				if(ingredients.size() == recipe.size())
				{
					while(b < recipe.size())
					{
						if(recipe.get(b).getItem() == ingredients.get(b).getItem() && recipe.get(b).getItemDamage() == ingredients.get(b).getItemDamage())
						{
							b++;
						} else
						{
							b = 100;//break;
						}
					}
					if(b == recipe.size())
					{
						matches = true;
						ItemStack result = recipeMap.get(recipe).copy();
						result.stackSize = rate;
						return result;//recipes.get(recipe);
					}
				}
			}
		}
		return null;
	}

    @Override
    public int getSizeInventory()
    {
        return 20;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return this.items[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int amount)
    {
        if (this.items[index] != null)
        {
            ItemStack itemstack = this.items[index];
            this.items[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (this.items[index] != null)
        {
            ItemStack itemstack = this.items[index];
            this.items[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack itemstack)
    {
        this.items[index] = itemstack;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack)
    {
        return true;
    }
    
	@Override
	public String getInventoryName() 
	{
		return "Alchemy";
	}
	
	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}
	
	@Override
	public void markDirty()
	{	
	}
	
	@Override
	public void openInventory()
	{	
	}
	
	@Override
	public void closeInventory()
	{	
	}
    
}
