package alldemdimensions.block;

import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSkyLeaves extends BlockLeaves
{

    public BlockSkyLeaves(Tree tree)
    {
        super();
        treeType = tree;
    }
	
    @Override
    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
    {
        return treeType.getLeavesColorInWorld(world, x, y, z);
    }
    
    @Override
    public int getBlockColor()
    {
        return 16777215;
    }

    @Override
    public int getRenderColor(int metadata)
    {
		return treeType.getLeavesColorInInventory(metadata);
    }

    @Override
    public int quantityDropped(Random random)
    {
        return treeType.leaves_quantityDropped(random);
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune)
    {
		return treeType.leaves_itemDropped(metadata, random, fortune);
    }
	
    @Override
	public int damageDropped(int i)
    {
        return i & 3;
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float f, int fortune)
    {
        if (!world.isRemote)
        {
            byte b = 20;

            if ((metadata & 3) == 3)
            {
                b = 40;
            }

            if (world.rand.nextInt(b) == 0)
            {
                Item item = getItemDropped(metadata, world.rand, fortune);
                dropBlockAsItem(world, x, y, z, new ItemStack(item, 1, damageDropped(metadata)));
            }
        }
    }
	
    @Override
	public IIcon getIcon(int i, int j)
    {
    	if(field_150127_b == 1)
		{
    		return treeType.leavesFastTexture;
		} else
        {
            return treeType.leavesFancyTexture;
        }
    }

	@Override
	public String[] func_150125_e()
	{
		return null;//1.7.2
	}

	
	public Tree treeType;
	public static boolean fastGraphics;
	
}
