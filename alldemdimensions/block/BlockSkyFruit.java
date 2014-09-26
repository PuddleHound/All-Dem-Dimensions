package alldemdimensions.block; import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.util.TextureLibrary;

public class BlockSkyFruit extends Block implements IBlockRenderer
{
    public BlockSkyFruit()
    {
        super(Material.plants);
		//setBlockBounds(0.25F, 0.5F, 0.25F, 0.75F, 1.0F, 0.75F);
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
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
	{
		return null;
	}
	
    @Override
	public Item getItemDropped(int i, Random random, int j)
	{
		if(i == CHERRY)
		{
			return AllDemDimensions.cherry;
		} else
		if(i == POD_FRUIT)
		{
			return AllDemDimensions.podFruit;
		} else
	    if(i == CHESTNUT)
	    {
	        return AllDemDimensions.chestnut;
	    }
		return AllDemDimensions.sugarplum;
	}
	
    @Override
	public int quantityDropped(int metadata, int fortune, Random random)
    {
		if(metadata == CHERRY)
		{
			return 2;
		}
        return 1;
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
    public IIcon getIcon(int i, int j)
    {
        if(j == CHERRY)
        {
            return TextureLibrary.getBlockTexture("zenith/flower2");
        }
        if(j == POD_FRUIT)
        {
            return TextureLibrary.getBlockTexture("zenith/podFruit");
        }
        if(j == CHESTNUT)
        {
            return TextureLibrary.getBlockTexture("zenith/stem");
        }
        return TextureLibrary.getBlockTexture("zenith/plum");
    }
        
    @Override
    public void render(BlockRenderer br)
    {
        int metadata = br.getMetadata();
        br.setUseRenderBlocks(true);
        if(metadata == AllDemDimensions.skyFruit.SUGARPLUM || metadata == AllDemDimensions.skyFruit.CHESTNUT)
        {
                br.setBlockBounds(0.375F, 0.5F, 0.375F, 0.625F, 0.75F, 0.625F);
                br.cuboid();
                br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/stem"));
                br.setBlockBounds(0.46875F, 0.75F, 0.46875F, 0.53125F, 1.0F, 0.53125F);
                br.cuboid();
                br.setBlockTexture(null);
        } else
        if(metadata == AllDemDimensions.skyFruit.CHERRY)
        {
                br.setBlockBounds(0.1875F, 0.25F, 0.375F, 0.4375F, 0.5F, 0.625F);
                br.cuboid();
                br.setBlockBounds(0.5625F, 0.25F, 0.375F, 0.8125F, 0.5F, 0.625F);
                br.cuboid();
                br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/cherryStems"));
                br.setBlockBounds(0.25F, 0.5F, 0.4999F, 0.75F, 1.0F, 0.5001F);
                br.cuboid();
                br.setBlockTexture(null);
        } else
        if(metadata == AllDemDimensions.skyFruit.POD_FRUIT)
        {
                br.setBlockBounds(0.3125F, 0.625F, 0.3125F, 0.6875F, 1.0F, 0.6875F);
                br.cuboid();
                br.setBlockBounds(0.375F, 0.375F, 0.375F, 0.625F, 0.625F, 0.625F);
                br.cuboid();
                br.setBlockBounds(0.4375F, 0.25F, 0.4375F, 0.5625F, 0.375F, 0.5625F);
                br.cuboid();
                br.setBlockBounds(0.46875F, 0.1875F, 0.46875F, 0.53125F, 0.25F, 0.53125F);
                br.cuboid();
                br.setBlockTexture(null);
        }
    }
        
	public static final byte SUGARPLUM = 0;
	public static final byte CHERRY = 1;
	public static final byte POD_FRUIT = 2;
    public static final byte CHESTNUT = 3;
}
