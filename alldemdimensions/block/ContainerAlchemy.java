package alldemdimensions.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;

public class ContainerAlchemy extends Container
{
    
    public ContainerAlchemy(InventoryPlayer inventoryplayer, World world, int i, int j, int k)
    {
		inventory = new InventoryAlchemy(world);
        worldObj = world;
        posX = i;
        posY = j;
        posZ = k;
		yOffset = 22;
		EntityPlayer player = inventoryplayer.player;
		addSlotToContainer(new SlotAlchemy(inventory, worldObj, player, InventoryAlchemy.SLOT_CRAFT_START + 0, 18, 26));//matrix
		addSlotToContainer(new SlotAlchemy(inventory, worldObj, player, InventoryAlchemy.SLOT_CRAFT_START + 1, 36, 17));
		addSlotToContainer(new SlotAlchemy(inventory, worldObj, player, InventoryAlchemy.SLOT_CRAFT_START + 2, 54, 26));
		addSlotToContainer(new SlotAlchemy(inventory, worldObj, player, InventoryAlchemy.SLOT_CRAFT_START + 3, 9, 44));
		addSlotToContainer(new SlotAlchemy(inventory, worldObj, player, InventoryAlchemy.SLOT_CRAFT_START + 4, 36, 44));
		addSlotToContainer(new SlotAlchemy(inventory, worldObj, player, InventoryAlchemy.SLOT_CRAFT_START + 5, 63, 44));
		addSlotToContainer(new SlotAlchemy(inventory, worldObj, player, InventoryAlchemy.SLOT_CRAFT_START + 6, 18, 62));
		addSlotToContainer(new SlotAlchemy(inventory, worldObj, player, InventoryAlchemy.SLOT_CRAFT_START + 7, 36, 71));
		addSlotToContainer(new SlotAlchemy(inventory, worldObj, player, InventoryAlchemy.SLOT_CRAFT_START + 8, 54, 62));
		addSlotToContainer(new SlotAlchemy(inventory, worldObj, player, InventoryAlchemy.SLOT_CATALYST, 95, 22));//catalyst
		addSlotToContainer(new SlotAlchemy(inventory, worldObj, player, InventoryAlchemy.SLOT_MODIFIER, 95, 44));//modifier
		addSlotToContainer(new SlotAlchemy(inventory, worldObj, player, InventoryAlchemy.SLOT_BASE_ITEM, 95, 66));//base item
		
		//result
		for(byte b = 0; b < 2; b++)
		{
			for(byte b1 = 0; b1 < 4; b1++)
			{
				addSlotToContainer(new SlotAlchemy(inventory, worldObj, player, (b1 + b * 4) + InventoryAlchemy.SLOT_RESULT_START, (b * 18) + 129, (b1 * 18) + 17));
			}
		}
		
        for (int i1 = 0; i1 < 3; i1++)
        {
            for (int l1 = 0; l1 < 9; l1++)
            {
                addSlotToContainer(new Slot(inventoryplayer, l1 + i1 * 9 + 9, 8 + l1 * 18, (84 + i1 * 18) + 38 - yOffset));
            }
        }

        for (int j1 = 0; j1 < 9; j1++)
        {
            addSlotToContainer(new Slot(inventoryplayer, j1, 8 + j1 * 18, 142 + 38 - yOffset));
        }

        //onInventoryChanged(inventory);
    }
    
    @Override
    public void onContainerClosed(EntityPlayer entityplayer)//onCraftGuiClosed
    {
        super.onContainerClosed(entityplayer);
        if (worldObj.isRemote)
        {
            return;
        }
        for (int i = InventoryAlchemy.SLOT_CRAFT_START; i < InventoryAlchemy.SLOT_RESULT_START; i++)
        {
            ItemStack itemstack = inventory.getStackInSlot(i);
            if (itemstack != null)
            {
				if(itemstack.getItemDamage() > 0 && (i == InventoryAlchemy.SLOT_CATALYST || i == InventoryAlchemy.SLOT_MODIFIER))
				{
					itemstack.setItemDamage(0);
					itemstack.stackSize--;
					if(itemstack.stackSize < 0)
					{
						itemstack.stackSize = 0;
					}
				}
                entityplayer.func_146097_a(itemstack, true, false);//1.7.2 - dropPlayerItem - booleans?
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        if (worldObj.getBlock(posX, posY, posZ) != AllDemDimensions.alchemyTable)
        {
            return false;
        }
        return entityplayer.getDistanceSq((double)posX + 0.5D, (double)posY + 0.5D, (double)posZ + 0.5D) <= 64D;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityplayer, int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(i);
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i == 0)
            {
                if (!mergeItemStack(itemstack1, 10, 46, true))
                {
                    return null;
                }
            }
            else if (i >= 10 && i < 37)
            {
                if (!mergeItemStack(itemstack1, 37, 46, false))
                {
                    return null;
                }
            }
            else if (i >= 37 && i < 46)
            {
                if (!mergeItemStack(itemstack1, 10, 37, false))
                {
                    return null;
                }
            }
            else if (!mergeItemStack(itemstack1, 10, 46, false))
            {
                return null;
            }
            if (itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
            if (itemstack1.stackSize != itemstack.stackSize)
            {
                slot.onPickupFromSlot(entityplayer, itemstack1);
            }
            else
            {
                return null;
            }
        }
        return itemstack;
    }
    
    public IInventory inventory;
    public IInventory craftResult;
    private World worldObj;
    private int posX;
    private int posY;
    private int posZ;
	private int yOffset;
}
