package alldemdimensions.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;

public class BlockPlantBase extends Block implements IBlockRenderer
{
	
	public BlockPlantBase()
	{
		super(Material.plants);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs creativetabs, List list)
    {
		for(int meta = 0; meta < plants.length; meta++)
		{
			if(plants[meta].creativeInventory)
			{
				list.add(new ItemStack(item, 1, meta));		
			}
		}
    }
	
    @Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
		return true;
	}
        
    @Override
    public IIcon getIcon(int side, int meta)//(IBlockAccess iblockaccess, int x, int y, int z, int side)
    {
		return plants[meta].getIcon(side, meta);
    }
    
    @Override
    public void render(BlockRenderer blockrenderer)
    {
    	plants[blockrenderer.getMetadata()].render(blockrenderer);
    }
	
    @Override
	public ArrayList<ItemStack> getDrops(World world, int i, int j, int k, int metadata, int fortune)
    {
    	return plants[metadata].getDrops(world, i, j, k, metadata, fortune);
	}
	
    @Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
    @Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
	{
		return null;
	}
	
    @Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
    @Override
	public int getRenderType()
	{
		return AllDemDimensions.blockRenderId;
	}

    @Override
    public boolean canBlockStay(World world, int x, int y, int z)//called by onNeighborBlockChange->dropBlockAsItem, canPlaceBlockAt
    {
    	return plants[world.getBlockMetadata(x, y, z)].getCanStay(world, x, y, z);
    }
    
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
    	return super.canPlaceBlockAt(world, x, y, z);// && canBlockStay(world, x, y, z);
    }
    
    @Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block l)
    {
		if(!canBlockStay(world, i, j, k))
		{
			dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k), 0);
			world.setBlockToAir(i, j, k);
		}
    }
	
    @Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k)
    {
        setBlockBoundsBasedOnState(world, i, j, k);
        return super.getSelectedBoundingBoxFromPool(world, i, j, k);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k)
    {
    	plants[iblockaccess.getBlockMetadata(i, j, k)].getBoundingBox(iblockaccess, i, j, k);
		//setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
    }
	
    @Override
	public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
	{
    	return plants[iblockaccess.getBlockMetadata(i, j, k)].getColorInWorld(iblockaccess, i, j, k);
	}
    
    @Override
    public int getRenderColor(int metadata)
    {
    	return plants[metadata].getColorInInventory(metadata);
    }
    
    @Override
    public int getLightValue(IBlockAccess iblockaccess, int i, int j, int k)
    {
        return plants[iblockaccess.getBlockMetadata(i, j, k)].getLightLevel(iblockaccess, i, j, k);
    }
    
    @Override
    public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
        plants[world.getBlockMetadata(i, j, k)].renderParticles(world, i, j, k, random);
    }
	
	@Override
	public boolean isReplaceable(IBlockAccess iblockaccess, int x, int y, int z)
	{
		return plants[iblockaccess.getBlockMetadata(x, y, z)].getIsReplaceable(iblockaccess, x, y, z);
	}
    
	public Plant[] plants = new Plant[16];
}
