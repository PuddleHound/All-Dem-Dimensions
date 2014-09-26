package alldemdimensions.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.entity.EntityEnderwraith;
import alldemdimensions.util.TextureLibrary;
import alldemdimensions.world.Dimension;

public class BlockEnderCrystal extends Block
{
	
    public BlockEnderCrystal()
    {
        super(Material.rock);
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconregister)
    {
        blockIcon = TextureLibrary.getBlockTexture("ender/enderCrystal");
    }
	
    @Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
    @Override
	public int getRenderBlockPass()
    {
        return 1;
    }
	
    @Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        Block i1 = iblockaccess.getBlock(i, j, k);
        if(i1 == AllDemDimensions.enderCrystal)
        {
            return false;
        } else
		{
			return super.shouldSideBeRendered(iblockaccess, i, j, k, l);
		}
	}
	
    @Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int j1, float f, float f1, float f2)
    {
		if(world.isRemote || entityplayer.getCurrentEquippedItem() == null)
		{
			return false;
		}
		int i1 = world.provider.dimensionId;
		if(entityplayer.getCurrentEquippedItem().getItem() == Items.ender_pearl && 
			(i1 == Dimension.nether.dimensionId || i1 == Dimension.overworld.dimensionId || i1 == Dimension.zenith.dimensionId || i1 == Dimension.kyther.dimensionId))
		{
			AllDemDimensions.enderPortal.activatePortal(world, i, j + 1, k);
			return true;
		}
		return false;
	}
	
    @Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
	{
		int i1 = world.provider.dimensionId;
		if((entity instanceof EntityEnderman || entity instanceof EntityEnderwraith) && (i1 == Dimension.nether.dimensionId || i1 == Dimension.overworld.dimensionId || i1 == Dimension.zenith.dimensionId || i1 == Dimension.kyther.dimensionId))
		{
			AllDemDimensions.enderPortal.activatePortal(world, i, j + 1, k);
		}
	}
    
}
