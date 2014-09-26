package alldemdimensions.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNetherMushroom extends WorldGenerator
{
    
    public WorldGenNetherMushroom(Block block)
    {
        blockID = block;
    }
 
    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        int height = random.nextInt(25) + 20;
        if(world.getBlock(i, j - 1, k).getMaterial().isSolid() && world.isAirBlock(i, j, k) && world.isAirBlock(i, j + (height / 2), k) && world.isAirBlock(i, j + height, k))
        {
            int i1;
            int j1 = height / 6;
            for(int l = 0; l < height; l++)
            {
                if(l > height / 2)
                {
                    i1 = (l / j1);
                } else
                {
                    i1 = (((height / 2) - l) / j1) + 2;
                }
                addStalkLayer(world, random, i, j + l, k, i1);
            }
            addCap(world, random, i, j + height - (height / 3), k, height / 2);
        }
        return true;
    }
    
    private void addCap(World world, Random random, int i, int j, int k, int radius)
    {
        for(int width = -radius; width < radius; width++)
        {
            for(int height = 0; height < radius; height++)
            {
                for(int depth = -radius; depth < radius; depth++)
                {
                    int l = (int)Math.round(Math.sqrt(Math.pow(width,2) + Math.pow(height,2) + Math.pow(depth,2)));
                    if(l >= (radius * 2) / 3 && l <= radius)
                    {
                    	setBlockAndNotifyAdequately(world, i + width, j + height, k + depth, blockID, 14);
                    }
                }
            }
        }
    }
    
    private void addStalkLayer(World world, Random random, int i, int j, int k, int radius)
    {
        for(int width = -radius; width < radius + 1; width++)
        {
            for(int height = 0; height < 1; height++)
            {
                for(int depth = -radius; depth < radius + 1; depth++)
                {
                    int l = (int)Math.round(Math.sqrt(Math.pow(width,2) + Math.pow(depth,2)));
                    if(l < radius)
                    {
                    	setBlockAndNotifyAdequately(world, i + width, j + height, k + depth, blockID, 10);
                    }
                }
            }
        }
    }
    
    public Block blockID;
}