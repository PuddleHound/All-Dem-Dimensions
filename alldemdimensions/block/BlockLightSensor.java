package alldemdimensions.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.util.TextureLibrary;

public class BlockLightSensor extends Block implements IBlockRenderer
{
    public BlockLightSensor()
    {
        super(Material.rock);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
    }
	
    @Override
	public boolean isOpaqueCube()
	{
		return false;
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
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float f, float f1, float f2)
	{
		int metadata = world.getBlockMetadata(i, j, k);
		if(metadata == 15)
		{
			metadata = 0;
		} else
		{
			metadata++;
		}
		world.setBlockMetadataWithNotify(i, j, k, metadata, 2);
		return true;
	}
    
	/*
	public void updateTick(World world, int i, int j, int k, Random random)
    {
		checkForLightChange(world, i, j, k);
    }
	
	public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
		checkForLightChange(world, i, j, k);
    }
	
	public void checkForLightChange(World world, int i, int j, int k)
	{
		int l = world.getBlockMetadata(i, j, k);
		if(l <= 7 && world.getFullBlockLightValue(i, j, k) > l * 2)
		{
			world.setBlockMetadataWithNotify(i, j, k, l + 8);
		} else
		if(l >= 8 && world.getFullBlockLightValue(i, j, k) < (l - 8) * 2)
		{
			world.setBlockMetadataWithNotify(i, j, k, l - 8);
		}
	}*/
	
    @Override
	public boolean canProvidePower()
    {
        return true;
    }
	
	//public boolean isIndirectlyPoweringTo(World world, int i, int j, int k, int l)
    @Override
    public int isProvidingWeakPower(IBlockAccess world, int i, int j, int k, int l)
    {
		TileEntityLightSensor entity = (TileEntityLightSensor)world.getTileEntity(i, j, k);
		if(entity != null)
		{
			System.out.println("Tile entity is not null.");
			return entity.activated ? 15 : 0;
		}
		System.out.println("Tile entity is null.");
		return 0;
		//return ((TileEntityLightSensor)world.getBlockTileEntity(i, j, k)).activated;//world.getBlockMetadata(i, j, k) >= 8;
	}
	
	//public boolean isPoweringTo(IBlockAccess iblockaccess, int i, int j, int k, int l)
    @Override
    public int isProvidingStrongPower(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
		TileEntityLightSensor entity = (TileEntityLightSensor)iblockaccess.getTileEntity(i, j, k);
		if(entity != null)
		{
			System.out.println("Tile entity is not null.");
			return entity.activated ? 15 : 0;
		}
		System.out.println("Tile entity is null.");
		return 0;
		//return ((TileEntityLightSensor)iblockaccess.getBlockTileEntity(i, j, k)).activated;//iblockaccess.getBlockMetadata(i, j, k) >= 8;
	}
	
    @Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
    @Override
	public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityLightSensor();
    } 
	
    @Override
	public IIcon getIcon(int i, int j)
    {
		if(i == 1)
		{
			return TextureLibrary.getBlockTexture("zenith/lightSensorTop");
		}
		if(i == 0)
		{
			return TextureLibrary.getBlockTexture("zenith/lightSensorBottom");
		}
		return TextureLibrary.getBlockTexture("zenith/lightSensorSide");
    }

    @Override
    public void render(BlockRenderer br)
    {
        br.setUseRenderBlocks(true);
        br.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
        br.cuboid();
        if(!br.isItem())
        {
            byte metadata = br.getMetadata();
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/lightSensorTip"));
            br.setBlockBounds(metadata * 0.0625F, 0.75F, 0.4375F, (metadata * 0.0625F) + 0.0625F, 1.0F, 0.5625F);
            br.cuboid();
            br.setBlockTexture(null);
        }
    }
}
