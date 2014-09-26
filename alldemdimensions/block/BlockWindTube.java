package alldemdimensions.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.util.TextureLibrary;

public class BlockWindTube extends Block implements IBlockRenderer
{
    public BlockWindTube()
    {
        super(Material.rock);
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconregister)
    {
        blockIcon = TextureLibrary.getBlockTexture("zenith/marble");
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
	public boolean hasTileEntity(int metadata)
    {
        return true;
    }
	
    @Override
	public TileEntity createTileEntity(World world, int metadata)
    {
		return new TileEntityWindTube();
	}
	
    @Override
	public void addCollisionBoxesToList(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List list, Entity entity)
    {/*
		int metadata = world.getBlockMetadata(i, j, k);
		if(metadata == NEG_Z || metadata == POS_Z)
		{
			setBlockBounds(0.125F, 0.125F, 0.0F, 0.25F, 0.875F, 1.0F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, list, entity);
			setBlockBounds(0.75F, 0.125F, 0.0F, 0.875F, 0.875F, 1.0F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, list, entity);
			setBlockBounds(0.25F, 0.75F, 0.0F, 0.75F, 0.875F, 1.0F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, list, entity);
			setBlockBounds(0.25F, 0.125F, 0.0F, 0.75F, 0.25F, 1.0F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, list, entity);
		} else
		if(metadata == NEG_X || metadata == POS_X)
		{
			setBlockBounds(0.0F, 0.125F, 0.125F, 1.0F, 0.875F, 0.25F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, list, entity);
			setBlockBounds(0.0F, 0.125F, 0.75F, 1.0F, 0.875F, 0.875F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, list, entity);
			setBlockBounds(0.0F, 0.75F, 0.25F, 1.0F, 0.875F, 0.75F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, list, entity);
			setBlockBounds(0.0F, 0.125F, 0.25F, 1.0F, 0.25F, 0.75F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, list, entity);
		} else
		if(metadata == NEG_Y || metadata == POS_Y)
		{
			setBlockBounds(0.125F, 0.0F, 0.125F, 0.25F, 1.0F, 0.875F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, list, entity);
			setBlockBounds(0.75F, 0.0F, 0.125F, 0.875F, 1.0F, 0.875F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, list, entity);
			setBlockBounds(0.25F, 0.0F, 0.125F, 0.75F, 1.0F, 0.25F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, list, entity);
			setBlockBounds(0.25F, 0.0F, 0.75F, 0.75F, 1.0F, 0.875F);
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, list, entity);
		}*/
	}
	
    @Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k)
    {
		/*int metadata = world.getBlockMetadata(i, j, k);
		if(metadata == NEG_X || metadata == POS_X)
		{
			return AxisAlignedBB.getAABBPool().getAABB(i + 0.0D, j + 0.125D, k + 0.125D, i + 1.0D, j + 0.875D, k + 0.875D);
		} else
		if(metadata == NEG_Z || metadata == POS_Z)
		{
			return AxisAlignedBB.getAABBPool().getAABB(i + 0.125D, j + 0.125D, k + 0.0D, i + 0.875D, j + 0.875D, k + 1.0D);
		} else
		if(metadata == NEG_Y || metadata == POS_Y)
		{
			return AxisAlignedBB.getAABBPool().getAABB(i + 0.125D, j + 0.0D, k + 0.125D, i + 0.875D, j + 1.0D, k + 0.875D);
		}*/
		return super.getSelectedBoundingBoxFromPool(world, i, j, k);
    }
	
    @Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemstack)
    {
		int metadata = 0;
		int l = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        if(l == 0)
		{
			metadata = 2;
		}
		if(l == 1)
		{
			metadata = 5;
		}
		if(l == 2)
		{
			metadata = 3;
		}
		if(l == 3)
		{
			metadata = 4;
		}
		if(MathHelper.abs((float)entityliving.posX - (float)i) < 2.0F && MathHelper.abs((float)entityliving.posZ - (float)k) < 2.0F)
        {
            double d = entityliving.posY + 1.82D - (double)entityliving.yOffset;
            if (d - (double)j > 2.0D)
            {
                metadata = 0;//opposite
            } else
            if ((double)j - d > 0.0D)
            {
                metadata = 1;
            }
        }
		world.setBlockMetadataWithNotify(i, j, k, metadata, 2);
    }
        
    @Override
    public void render(BlockRenderer br)
    {
        byte metadata = br.getMetadata();
        if(metadata == AllDemDimensions.windTube.NEG_Z || metadata == AllDemDimensions.windTube.POS_Z)
        {
            br.setBlockBounds(0.125F, 0.125F, 0.0F, 0.25F, 0.875F, 1.0F);
            br.cuboid();
            br.setBlockBounds(0.75F, 0.125F, 0.0F, 0.875F, 0.875F, 1.0F);
            br.cuboid();
            br.setBlockBounds(0.25F, 0.75F, 0.0F, 0.75F, 0.875F, 1.0F);
            br.cuboid();
            br.setBlockBounds(0.25F, 0.125F, 0.0F, 0.75F, 0.25F, 1.0F);
            br.cuboid();
        } else
        if(metadata == AllDemDimensions.windTube.NEG_X || metadata == AllDemDimensions.windTube.POS_X)
        {
            br.setBlockBounds(0.0F, 0.125F, 0.125F, 1.0F, 0.875F, 0.25F);
            br.cuboid();
            br.setBlockBounds(0.0F, 0.125F, 0.75F, 1.0F, 0.875F, 0.875F);
            br.cuboid();
            br.setBlockBounds(0.0F, 0.75F, 0.25F, 1.0F, 0.875F, 0.75F);
            br.cuboid();
            br.setBlockBounds(0.0F, 0.125F, 0.25F, 1.0F, 0.25F, 0.75F);
            br.cuboid();
        } else
        if(metadata == AllDemDimensions.windTube.NEG_Y || metadata == AllDemDimensions.windTube.POS_Y)
        {
            br.setBlockBounds(0.125F, 0.0F, 0.125F, 0.25F, 1.0F, 0.875F);
            br.cuboid();
            br.setBlockBounds(0.75F, 0.0F, 0.125F, 0.875F, 1.0F, 0.875F);
            br.cuboid();
            br.setBlockBounds(0.25F, 0.0F, 0.125F, 0.75F, 1.0F, 0.25F);
            br.cuboid();
            br.setBlockBounds(0.25F, 0.0F, 0.75F, 0.75F, 1.0F, 0.875F);
            br.cuboid();
        }
    }
	
	public static final byte NEG_Y = 0;//direction blowing toward
	public static final byte POS_Y = 1;
	public static final byte NEG_X = 5;
	public static final byte NEG_Z = 3;
	public static final byte POS_X = 4;
	public static final byte POS_Z = 2;
    
}
