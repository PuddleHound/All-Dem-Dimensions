package alldemdimensions.block; 

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.util.TextureLibrary;

public class BlockRedstoneValve extends Block implements IBlockRenderer
{
    public BlockRedstoneValve()
    {
        super(Material.iron);
    }
	
    @Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
    @Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
		return true;
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
    public void registerBlockIcons(IIconRegister iconregister)
    {
        blockIcon = TextureLibrary.getBlockTexture("zenith/marble");
    }
	
    @Override
	public boolean hasTileEntity(int metadata)
    {
        return true;
    }
	
    @Override
	public TileEntity createTileEntity(World world, int metadata)
    {
		return new TileEntityRedstoneValve();
	}
	
    @Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float f, float f1, float f2)
    {
		TileEntityRedstoneValve valve = (TileEntityRedstoneValve)world.getTileEntity(i, j, k);
		if(valve == null)
		{
			return false;
		}
		if(valve.state[l] == TileEntityRedstoneValve.OPEN)
		{
			valve.state[l] = TileEntityRedstoneValve.CLOSED;
		} else
		{
			valve.state[l] = TileEntityRedstoneValve.OPEN;
		}
		world.markBlockForUpdate(i, j, k);
        return true;
    }
	
    @Override
	public void addCollisionBoxesToList(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List list, Entity entity)
    {
		TileEntityRedstoneValve valve = (TileEntityRedstoneValve)world.getTileEntity(i, j, k);
		if(valve == null)
		{
			return;
		}
		if(valve.state[0] == valve.CLOSED)
		{
			setBlockBounds(0.0F, 0.125F, 0.0F, 1.0F, 0.25F, 1.0F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, list, entity);
		}
		if(valve.state[1] == valve.CLOSED)
		{
			setBlockBounds(0.0F, 0.75F, 0.0F, 1.0F, 0.875F, 1.0F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, list, entity);
		}
		if(valve.state[3] == valve.CLOSED)
		{
			setBlockBounds(0.125F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, list, entity);
		}
		if(valve.state[2] == valve.CLOSED)
		{
			setBlockBounds(0.75F, 0.0F, 0.0F, 0.875F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, list, entity);
		}
		if(valve.state[5] == valve.CLOSED)
		{
			setBlockBounds(0.0F, 0.0F, 0.125F, 1.0F, 1.0F, 0.25F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, list, entity);
		}
		if(valve.state[4] == valve.CLOSED)
		{
			setBlockBounds(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 0.875F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, list, entity);
		}
		//setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
	
    @Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k)
    {
		return AxisAlignedBB.getAABBPool().getAABB(i, j, k, i + 1, j + 1, k + 1);
	}
        
    @Override
    public void render(BlockRenderer br)
    {
        TileEntity tileentity = br.getIBlockAccess().getTileEntity(br.getX(), br.getY(), br.getZ());
        if(!(tileentity instanceof TileEntityRedstoneValve))
        {
            return;
        }
        TileEntityRedstoneValve valve = (TileEntityRedstoneValve)tileentity;
        br.setUseRenderBlocks(true);
        br.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 0.125F);
        br.cuboid();
        br.setBlockBounds(0.0F, 0.0F, 0.875F, 1.0F, 0.125F, 1.0F);
        br.cuboid();
        br.setBlockBounds(0.0F, 0.875F, 0.0F, 1.0F, 1.0F, 0.125F);
        br.cuboid();
        br.setBlockBounds(0.0F, 0.875F, 0.875F, 1.0F, 1.0F, 1.0F);
        br.cuboid();

        br.setBlockBounds(0.0F, 0.0F, 0.125F, 0.125F, 0.125F, 0.875F);
        br.cuboid();
        br.setBlockBounds(0.875F, 0.0F, 0.125F, 1.0F, 0.125F, 0.875F);
        br.cuboid();
        br.setBlockBounds(0.0F, 0.875F, 0.125F, 0.125F, 1.0F, 0.875F);
        br.cuboid();
        br.setBlockBounds(0.875F, 0.875F, 0.125F, 1.0F, 1.0F, 0.875F);
        br.cuboid();

        br.setBlockBounds(0.0F, 0.125F, 0.0F, 0.125F, 0.875F, 0.125F);
        br.cuboid();
        br.setBlockBounds(0.0F, 0.125F, 0.875F, 0.125F, 0.875F, 1.0F);
        br.cuboid();
        br.setBlockBounds(0.875F, 0.125F, 0.0F, 1.0F, 0.875F, 0.125F);
        br.cuboid();
        br.setBlockBounds(0.875F, 0.125F, 0.875F, 1.0F, 0.875F, 1.0F);
        br.cuboid();

        float offset = 0.001F;
        if(valve.state[1] == valve.CLOSED)
        {
                br.setBlockBounds(0.125F, 0.75F, 0.125F, 0.875F, 0.875F, 0.875F);
                br.cuboid();
        }
        if(valve.state[0] == valve.CLOSED)
        {
                br.setBlockBounds(0.125F, 0.125F, 0.125F, 0.875F, 0.25F, 0.875F);
                br.cuboid();
        }
        if(valve.state[5] == valve.CLOSED)
        {
                br.setBlockBounds(0.75F + offset, 0.125F + offset, 0.125F, 0.875F - offset, 0.875F - offset, 0.875F);
                br.cuboid();
        }
        if(valve.state[4] == valve.CLOSED)
        {
                br.setBlockBounds(0.125F + offset, 0.125F + offset, 0.125F, 0.25F - offset, 0.875F - offset, 0.875F);
                br.cuboid();
        }
        if(valve.state[3] == valve.CLOSED)
        {
                br.setBlockBounds(0.125F + offset, 0.125F + offset, 0.75F, 0.875F - offset, 0.875F - offset, 0.875F);
                br.cuboid();
        }
        if(valve.state[2] == valve.CLOSED)
        {
                br.setBlockBounds(0.125F + offset, 0.125F + offset, 0.125F, 0.875F - offset, 0.875F - offset, 0.25F);
                br.cuboid();
        }
    }
}
