package alldemdimensions.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;

public class ItemFloatingRedstone extends Item
{
    public ItemFloatingRedstone()
    {
        super();
    }
	
    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (world.getBlock(x, y, z) != Blocks.snow_layer)
        {
            if (side == 0)
            {
                --y;
            }

            if (side == 1)
            {
                ++y;
            }

            if (side == 2)
            {
                --z;
            }

            if (side == 3)
            {
                ++z;
            }

            if (side == 4)
            {
                --x;
            }

            if (side == 5)
            {
                ++x;
            }

            if (!world.isAirBlock(x, y, z))
            {
                return false;
            }
        }

        if (!entityplayer.canPlayerEdit(x, y, z, side, itemstack))
        {
            return false;
        }
        else
        {
            if (AllDemDimensions.floatingWire.canPlaceBlockAt(world, x, y, z))
            {
                itemstack.stackSize--;
                world.setBlock(x, y, z, AllDemDimensions.floatingWire, 0, 3);
            }

            return true;
        }
    }
}
