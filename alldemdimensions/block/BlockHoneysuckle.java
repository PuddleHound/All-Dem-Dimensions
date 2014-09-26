package alldemdimensions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;

public class BlockHoneysuckle extends BlockVine
{
    public BlockHoneysuckle()
    {
        super();
    }
	
    @Override
	public int getRenderType()
    {
		return 20;
	}
	
    @Override
    public void registerBlockIcons(IIconRegister iconregister)
    {
        blockIcon = iconregister.registerIcon("alldemdimensions:zenith/honeysuckle");//TextureLibrary.getBlockTexture("zenith/honeysuckle");
    }

    @Override
    public int getBlockColor()
    {
        return 0xffffff;
    }

    @Override
    public int getRenderColor(int i)
    {
        return 0xffffff;
    }

    @Override
    public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
    {
        return 0xffffff;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer entityplayer, int i, int j, int k, int l)
    {
		Block block = AllDemDimensions.honeysuckle;
        if (!world.isRemote && entityplayer.getCurrentEquippedItem() != null && entityplayer.getCurrentEquippedItem().getItem() == Items.shears)
        {
            //entityplayer.addStat(StatList.mineBlockStatArray[blockID], 1);//1.7.2
            dropBlockAsItem(world, i, j, k, new ItemStack(block, 1, 0));
        }
        else
        {
            super.harvestBlock(world, entityplayer, i, j, k, l);
        }
    }
	
}
