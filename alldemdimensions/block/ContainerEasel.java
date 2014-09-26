package alldemdimensions.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerEasel extends Container
{
    
    public ContainerEasel(IInventory iinventory, TileEntityEasel easel)
    {
        this.tileEntityEasel = easel;
        int slotX;
        int slotY;

		int offsetX = -9;
		int offsetY = -44;
        for (slotX = 0; slotX < 2; ++slotX)
        {
            for (slotY = 0; slotY < 8; ++slotY)
            {
				if(slotX > 0)
				{
					offsetX = 13;
				} else
				{
					offsetX = -9;
				}
                this.addSlotToContainer(new SlotEasel(easel, SlotEasel.BOTTLE, slotY + slotX * 8, (17 + slotX * 18) + offsetX, (62 + slotY * 18) + offsetY));
            }
        }

		offsetX = 245;
		offsetY = 10;
        for (slotX = 0; slotX < 3; ++slotX)
        {
            for (slotY = 0; slotY < 9; ++slotY)
            {
                this.addSlotToContainer(new Slot(iinventory, slotY + slotX * 9 + 9, (84 + slotX * 18) + offsetX, (8 + slotY * 18) + offsetY));
            }
        }

		offsetY += 166;
        for (slotX = 0; slotX < 3; ++slotX)
        {
			for(slotY = 0; slotY < 3; ++slotY)
			{
				this.addSlotToContainer(new Slot(iinventory, (slotX * 3) + slotY,  (84 + slotX * 18) + offsetX, (8 + slotY * 18) + offsetY));
			}
		}
		
		this.addSlotToContainer(new Slot(easel, TileEntityEasel.PAINTING_SLOT, 222, 6));
    
		this.addSlotToContainer(new SlotEasel(easel, SlotEasel.COLOR_MIX, 17, 8, 186));
		this.addSlotToContainer(new SlotEasel(easel, SlotEasel.COLOR_MIX, 18, 26, 186));
		this.addSlotToContainer(new SlotEasel(easel, SlotEasel.COLOR_MIX, 19, 8, 204));
		this.addSlotToContainer(new SlotEasel(easel, SlotEasel.COLOR_MIX, 20, 26, 204));
		
		this.addSlotToContainer(new SlotEasel(easel, SlotEasel.COLOR_MIX_RESULT, 21, 66, 195));
	}

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return this.tileEntityEasel.isUseableByPlayer(entityplayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityplayer, int slotIndex)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotIndex < 9)
            {
                if (!this.mergeItemStack(itemstack1, 9, 45, true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 9, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(entityplayer, itemstack1);
        }

        return itemstack;
    }
    
    public TileEntityEasel tileEntityEasel;
}
