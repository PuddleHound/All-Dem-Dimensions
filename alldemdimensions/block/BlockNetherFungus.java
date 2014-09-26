package alldemdimensions.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.util.TextureLibrary;

public class BlockNetherFungus extends Block
{
    public BlockNetherFungus()
    {
        super(Material.plants);
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
    public IIcon getIcon(int i, int j)
    {
        return TextureLibrary.getBlockTexture("nether/soulstone");//temporary
    }
    
    @Override
    public Item getItemDropped(int i, Random random, int j)
    {
        return null;
    }
    
    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        return 0;
    }
    
    @Override
    public int getLightValue(IBlockAccess iblockaccess, int i, int j, int k)
    {
        int l = iblockaccess.getBlockMetadata(i, j, k);
        if(l == MUSHROOM_WHITE || l == MUSHROOM_CYAN)
        {
            return 8;
        }
        return 0;
    }
    
    public void render(BlockRenderer br)
    {
        byte metadata = br.getMetadata();
        if(metadata == AllDemDimensions.netherFungus.SHELF_BROWN || metadata == AllDemDimensions.netherFungus.SHELF_RED || metadata == AllDemDimensions.netherFungus.SHELF_YELLOW)
        {
            br.setBlockBounds(0F, 0.4375F, 0F, 1F, 0.5625F, 1F);
            br.cuboid();
        }
    }
    
    //some of my ideas - want to discuss
    public static final byte SHELF_BROWN = 0;//single flat layer of fungi suspended halfway from the ground
    public static final byte SHELF_RED = 1;
    public static final byte SHELF_YELLOW = 2;
    public static final byte MUSHROOM_WHITE = 3;
    public static final byte MUSHROOM_CYAN = 4;
    /*public static final byte MUSHROOM_BLACK;
    public static final byte MUSHROOM_GRAY;
    public static final byte MUSHROOM_BURGUNDY;
    public static final byte MOLD_BLUE;//small blue, green, gray, or black cubes atop light gray stems -- several per block
    public static final byte MOLD_GREEN;
    public static final byte MOLD_GRAY;
    public static final byte MOLD_BLACK;
    public static final byte PUFFBALL;*/
    
}