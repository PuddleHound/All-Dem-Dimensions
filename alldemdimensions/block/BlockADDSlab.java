package alldemdimensions.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.util.TextureLibrary;

public class BlockADDSlab extends BlockSlab
{
    public static final String[] blockStepTypes = new String[] {"limestoneBrick", "marbleBrick", "soulstone", "blazestoneBrick", "basaltBrick", "claystoneBrick", "obsidianBrick"};

    public BlockADDSlab(boolean flag)
    {
        super(flag, Material.rock);
    }

    @Override
    public IIcon getIcon(int i, int j)
    {
        int metadata = j & 7;
		if(metadata == LIMESTONE_BRICK)
		{
			return TextureLibrary.getBlockTexture("zenith/limestoneBrick");
		} else
		if(metadata == MARBLE_BRICK)
		{
			return TextureLibrary.getBlockTexture("zenith/marbleBrick");
		}
		return null;
    }

    @Override
    public Item getItemDropped(int i, Random random, int j)
    {
        return Item.getItemFromBlock(AllDemDimensions.stoneSlab);
    }
	
    @Override
    protected ItemStack createStackedBlock(int i)
    {
        return new ItemStack(AllDemDimensions.stoneSlab, 2, i & 7);
    }

    @Override
    public String func_150002_b(int i)//getFullSlabName
    {
        if (i < 0 || i >= blockStepTypes.length)
        {
            i = 0;
        }

        return super.getUnlocalizedName() + "." + blockStepTypes[i];
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs creativetabs, List list)
    {
        if (item != Item.getItemFromBlock(AllDemDimensions.stoneSlabDouble))
        {
            for (int i = 0; i < 2; ++i)
            {
            	list.add(new ItemStack(item, 1, i));
            }
        }
    }
	
	public static final byte LIMESTONE_BRICK = 0;
	public static final byte MARBLE_BRICK = 1;
}
