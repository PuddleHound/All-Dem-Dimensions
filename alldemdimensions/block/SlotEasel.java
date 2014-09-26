package alldemdimensions.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import alldemdimensions.AllDemDimensions;

public class SlotEasel extends Slot
{

    public SlotEasel(IInventory iinventory, byte b, int i, int j, int k)
    {
		super(iinventory, i, j, k);
		metadata = b;
    }

    @Override
    public void onSlotChange(ItemStack itemstack, ItemStack itemstack1)
    {
        super.onSlotChange(itemstack, itemstack1);
		((ContainerEasel)inventory).tileEntityEasel.checkForColorMix();
    }

    @Override
    protected void onCrafting(ItemStack itemstack, int i) {}

    @Override
    protected void onCrafting(ItemStack itemstack) {}

    @Override
    public void onPickupFromSlot(EntityPlayer entityplayer, ItemStack itemstack)
    {
        super.onPickupFromSlot(entityplayer, itemstack);
		if(getSlotIndex() == 21)
		{
			ItemStack itemstack1;
			byte paints = 0;
			for(byte b = 17; b < 21; b++)
			{
				itemstack1 = inventory.getStackInSlot(b);
				if(itemstack1 != null)
				{
					if(itemstack1.getItem() == AllDemDimensions.paintBottle)
					{
						paints++;
					} else
					{
						inventory.decrStackSize(b, 1);
					}
				}
			}
			if(paints > 0)
			{
				for(byte b = 17; b < 21; b++)
				{
					itemstack1 = inventory.getStackInSlot(b);
					if(itemstack1 != null)
					{
						((TileEntityEasel)inventory).paintBottles[b].setItemDamage(itemstack1.getItemDamage() + (itemstack1.getMaxDamage() / paints));
					}
				}
			}
		}
    }

    @Override
    public boolean isItemValid(ItemStack itemstack)
    {
		if(metadata == BOTTLE && itemstack.getItem() != AllDemDimensions.paintBottle)
		{
			return false;
		}
		if(getSlotIndex() == TileEntityEasel.PAINTING_SLOT && itemstack.getItem() != AllDemDimensions.canvas)
		{
			return false;
		}
        return true;
    }

    @Override
    public void onSlotChanged()
    {
        super.onSlotChanged();
		((TileEntityEasel)inventory).checkForColorMix();
    }

    @Override
    public int getSlotStackLimit()
    {
        return 1;
    }
	
	public byte metadata;
	public static final byte BOTTLE = 0;
	public static final byte COLOR_SELECTOR = 1;
	public static final byte COLOR_MIX = 2;
	public static final byte COLOR_MIX_RESULT = 3;
}
