package alldemdimensions.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;

public class ItemBlockSkyCloud extends ItemBlock
{
    public ItemBlockSkyCloud(Block i)
    {
        super(AllDemDimensions.cloud);
        setMaxDamage(0);
        setHasSubtypes(true);
    }
	
    @Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		if(world.isRemote)
		{
			return super.onItemRightClick(itemstack, world, entityplayer);
		}
		int metadata = itemstack.getItemDamage();
		int direction = MathHelper.floor_double((double)(entityplayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int x = (int)MathHelper.floor_double(entityplayer.posX);
		int y = (int)MathHelper.floor_double(entityplayer.posY);
		int z = (int)MathHelper.floor_double(entityplayer.posZ);
		Block blockReplaced;
		//world.editingBlocks = true;
		y++;
		if(entityplayer.rotationPitch < -45F)
		{
			y++;
			blockReplaced = world.getBlock(x, y + 1, z);
			if(blockReplaced == null || blockReplaced.getMaterial().isReplaceable() || blockReplaced.isReplaceable(world, x, y + 1, z))
			{
				world.setBlock(x, y + 1, z, field_150939_a, metadata, 2);
			}
		} else
		if(entityplayer.rotationPitch > 45F)
		{
			y--;
			blockReplaced = world.getBlock(x, y - 1, z);
			if(blockReplaced == null || blockReplaced.getMaterial().isReplaceable() || blockReplaced.isReplaceable(world, x, y - 1, z))
			{
				world.setBlock(x, y - 1, z, field_150939_a, metadata, 2);
			}
		} else
        if(direction == 0)
        {
			blockReplaced = world.getBlock(x, y, z + 1);
			if(blockReplaced == null || blockReplaced.getMaterial().isReplaceable() || blockReplaced.isReplaceable(world, x, y, z + 1))
			{
				world.setBlock(x, y, z + 1, field_150939_a, metadata, 2);
			}
		} else
        if(direction == 1)
        {
			blockReplaced = world.getBlock(x - 1, y, z);
			if(blockReplaced == null || blockReplaced.getMaterial().isReplaceable() || blockReplaced.isReplaceable(world, x - 1, y, z))
			{
				world.setBlock(x - 1, y, z, field_150939_a, metadata, 2);
			}
		} else
        if(direction == 2)
        {
			blockReplaced = world.getBlock(x, y, z - 1);
			if(blockReplaced == null || blockReplaced.getMaterial().isReplaceable() || blockReplaced.isReplaceable(world, x, y, z - 1))
			{
				world.setBlock(x, y, z - 1, field_150939_a, metadata, 2);
			}
		} else
        if(direction == 3)
        {
			blockReplaced = world.getBlock(x + 1, y, z);
			if(blockReplaced == null || blockReplaced.getMaterial().isReplaceable() || blockReplaced.isReplaceable(world, x + 1, y, z))
			{
				world.setBlock(x + 1, y, z, field_150939_a, metadata, 2);
			}
		}
		//world.editingBlocks = false;
		return super.onItemRightClick(itemstack, world, entityplayer);
	}

    @Override
    public int getMetadata(int i)
    {
        return i;
    }

    @Override
    public IIcon getIconFromDamage(int i)
    {
        return AllDemDimensions.cloud.getIcon(2, i);
    }
	
	public String getItemDisplayName(ItemStack itemstack)
    {
		int metadata = itemstack.getItemDamage();
		if(metadata == AllDemDimensions.cloud.RAIN)
		{
			return "Rain Cloud";
		} else
		if(metadata == AllDemDimensions.cloud.ICE)
		{
			return "Ice Cloud";
		} else
		if(metadata == AllDemDimensions.cloud.STORM)
		{
			return "Storm Cloud";
		}
		return "Cloud";
	}
}
