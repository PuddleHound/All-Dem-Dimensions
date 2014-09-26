package alldemdimensions.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;

public class ItemMatch extends Item
{
    public ItemMatch()
    {
        super();
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float f, float f1, float f2)
    {
		if(world.getBlock(i, j, k) == AllDemDimensions.candle &&
			world.getBlockMetadata(i, j, k) == AllDemDimensions.candle.UNLIGHTED)
		{
			world.setBlock(i, j, k, AllDemDimensions.candle, AllDemDimensions.candle.LIGHTED, 3);
			itemstack.stackSize--;
			return true;
		}
		if (l == 0)
        {
            --j;
        }
        if (l == 1)
        {
            ++j;
        }
        if (l == 2)
        {
            --k;
        }
        if (l == 3)
        {
            ++k;
        }
        if (l == 4)
        {
            --i;
        }
        if (l == 5)
        {
            ++i;
        }
        if(!entityplayer.canPlayerEdit(i, j, k, l, itemstack))
        {
            return false;
        }
        Block id = world.getBlock(i, j, k);
        if (id.isAir(world, i, j, k))
        {
			world.playSoundEffect((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			world.setBlock(i, j, k, Blocks.fire, 0, 3);
        }
        itemstack.stackSize--;
        return true;
    }

}
