package alldemdimensions.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import alldemdimensions.block.BlockPlantBase;

public class ItemBlockSkyPlant extends ItemBlock
{
    public ItemBlockSkyPlant(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
	
    @Override
    public int getMetadata(int metadata)
    {
		return metadata;
    }

    @Override
    public IIcon getIconFromDamage(int metadata)
    {
        return field_150939_a.getIcon(2, metadata);
    }
    
    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
    	return ((BlockPlantBase)field_150939_a).plants[itemstack.getItemDamage()].getCanStay(world, x, y, z) ? super.onItemUse(itemstack, entityplayer, world, x, y, z, side, hitX, hitY, hitZ) : false;
    }

}
