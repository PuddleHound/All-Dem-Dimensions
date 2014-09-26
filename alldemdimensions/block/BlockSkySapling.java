package alldemdimensions.block;

import java.util.Random;

import net.minecraft.block.BlockSapling;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSkySapling extends BlockSapling
{
    public BlockSkySapling(Tree tree)
    {
        super();
        treeType = tree;
    }
	
    @Override
    public IIcon getIcon(int i, int j)
    {
    	return treeType.saplingTexture;
    }

    @Override
    public void func_149878_d(World world, int x, int y, int z, Random random)//growTree
    {
    	treeType.growTreeFromSapling(world, x, y, z, random);
    }
    
    public Tree treeType;
}
