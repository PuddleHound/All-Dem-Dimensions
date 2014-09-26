package alldemdimensions.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.world.gen.WorldGenSkyCrystalTree;

public class ItemHammerAndChisel extends Item
{
    public ItemHammerAndChisel()
    {
        super();
		setMaxDamage(500);
		setMaxStackSize(1);
    }
	
    @Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float f, float f1, float f2)
    {
		if(world.isRemote)
		{
			return false;
		}
		Block id = world.getBlock(i, j, k);
		int meta = world.getBlockMetadata(i, j, k);
		boolean flag = false;
		if(id == Blocks.stone)
		{
			world.setBlock(i, j, k, Blocks.stonebrick, 3, 3);
			flag = true;
		}
		if(id == AllDemDimensions.limestone)
		{
			if(meta == AllDemDimensions.limestone.LIMESTONE)
			{
				world.setBlock(i, j, k, AllDemDimensions.limestone, AllDemDimensions.limestone.LIMESTONE_CHISELED, 3);
				flag = true;
			} else
			if(meta == AllDemDimensions.limestone.MARBLE)
			{
				world.setBlock(i, j, k, AllDemDimensions.limestone, AllDemDimensions.limestone.MARBLE_CHISELED, 3);
				flag = true;
			}
		}
		if(id == Blocks.mossy_cobblestone)
		{
			AllDemDimensions.earthPortal.activatePortal(world, i, j + 1, k);
		}
		if(flag)
		{
			itemstack.damageItem(1, entityplayer);
		}                
		return flag;
	}
}
