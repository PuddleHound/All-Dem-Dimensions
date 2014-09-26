package alldemdimensions.block; 

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.util.TextureLibrary;

public class BlockMimicstone extends Block implements IBlockRenderer
{
    public BlockMimicstone()
    {
        super(Material.circuits);
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconregister)
    {
        blockIcon = TextureLibrary.getBlockTexture("nether/mimicstone");
    }
	
    @Override
	public IIcon getIcon(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
		Block i1 = iblockaccess.getBlock(i + 1, j, k);
		Block j1 = iblockaccess.getBlock(i, j, k + 1);
		Block k1 = iblockaccess.getBlock(i - 1, j, k);
		Block l1 = iblockaccess.getBlock(i, j, k - 1);
		Block i2 = iblockaccess.getBlock(i, j + 1, k);
		Block j2 = iblockaccess.getBlock(i, j - 1, k);
		if(i1 != Blocks.air && i1 != this && i1.getMaterial().isSolid())
		{
			return i1.getIcon(l, iblockaccess.getBlockMetadata(i + 1, j, k));
		} else
		if(j1 != Blocks.air && j1 != this && j1.getMaterial().isSolid())
		{
			return j1.getIcon(l, iblockaccess.getBlockMetadata(i, j, k + 1));
		} else
		if(k1 != Blocks.air && k1 != this && k1.getMaterial().isSolid())
		{
			return k1.getIcon(l, iblockaccess.getBlockMetadata(i - 1, j, k));
		} else
		if(l1 != Blocks.air && l1 != this && l1.getMaterial().isSolid())
		{
			return l1.getIcon(l, iblockaccess.getBlockMetadata(i, j, k - 1));
		} else
		if(i2 != Blocks.air && i2 != this && i2.getMaterial().isSolid())
		{
			return i2.getIcon(l, iblockaccess.getBlockMetadata(i, j + 1, k));
		} else
		if(j2 != Blocks.air && j2 != this && j2.getMaterial().isSolid())
		{
			return j2.getIcon(l, iblockaccess.getBlockMetadata(i, j - 1, k));
		}
		return blockIcon;
	}
	
    @Override
	public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
    {
		Block i1 = iblockaccess.getBlock(i + 1, j, k);
		Block j1 = iblockaccess.getBlock(i, j, k + 1);
		Block k1 = iblockaccess.getBlock(i - 1, j, k);
		Block l1 = iblockaccess.getBlock(i, j, k - 1);
		Block i2 = iblockaccess.getBlock(i, j + 1, k);
		Block j2 = iblockaccess.getBlock(i, j - 1, k);
		if(i1 != Blocks.air && i1 != this && i1.getMaterial().isSolid())
		{
			return i1.colorMultiplier(iblockaccess, i + 1, j, k);
		} else
		if(j1 != Blocks.air && j1 != this && j1.getMaterial().isSolid())
		{
			return j1.colorMultiplier(iblockaccess, i, j, k + 1);
		} else
		if(k1 != Blocks.air && k1 != this && k1.getMaterial().isSolid())
		{
			return k1.colorMultiplier(iblockaccess, i - 1, j, k);
		} else
		if(l1 != Blocks.air && l1 != this && l1.getMaterial().isSolid())
		{
			return l1.colorMultiplier(iblockaccess, i, j, k - 1);
		} else
		if(i2 != Blocks.air && i2 != this && i2.getMaterial().isSolid())
		{
			return i2.colorMultiplier(iblockaccess, i, j + 1, k);
		} else
		if(j2 != Blocks.air && j2 != this && j2.getMaterial().isSolid())
		{
			return j2.colorMultiplier(iblockaccess, i, j - 1, k);
		}
        return 0xffffff;
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
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }
	
    @Override
	public int getRenderType()
	{
		return AllDemDimensions.blockRenderId;
	}

    @Override
    public void render(BlockRenderer br)
    {
        if(br.isItem()){return;}
        RenderBlocks renderblocks = br.getRenderBlocks();
        IBlockAccess iblockaccess = br.getIBlockAccess();
        byte metadata = br.getMetadata();
        int i = br.getX();
        int j = br.getY();
        int k = br.getZ();
        Block i1 = iblockaccess.getBlock(i + 1, j, k);
        Block j1 = iblockaccess.getBlock(i, j, k + 1);
        Block k1 = iblockaccess.getBlock(i - 1, j, k);
        Block l1 = iblockaccess.getBlock(i, j, k - 1);
        Block i2 = iblockaccess.getBlock(i, j + 1, k);
        Block j2 = iblockaccess.getBlock(i, j - 1, k);
        if(i1 != Blocks.air && i1 != this)
        {
            renderblocks.renderBlockByRenderType(i1, i, j, k);
        } else
        if(j1 != Blocks.air && j1 != this)
        {
            renderblocks.renderBlockByRenderType(j1, i, j, k);
        } else
        if(k1 != Blocks.air && k1 != this)
        {
            renderblocks.renderBlockByRenderType(k1, i, j, k);
        } else
        if(l1 != Blocks.air && l1 != this)
        {
            renderblocks.renderBlockByRenderType(l1, i, j, k);
        } else
        if(i2 != Blocks.air && i2 != this)
        {
            renderblocks.renderBlockByRenderType(i2, i, j, k);
        } else
        if(j2 != Blocks.air && j2 != this)
        {
            renderblocks.renderBlockByRenderType(j2, i, j, k);
        }
        
    }
    
}
