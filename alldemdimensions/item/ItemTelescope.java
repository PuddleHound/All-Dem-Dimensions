package alldemdimensions.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;

public class ItemTelescope extends Item
{
    public ItemTelescope(boolean flag)
    {
        super();
		active = flag;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
		if(active)
		{
			return new ItemStack(AllDemDimensions.telescope);
		} else
		{
			return new ItemStack(AllDemDimensions.telescopeActive);
		}
	}
	
	private boolean active;
}
