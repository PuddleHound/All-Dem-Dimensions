package alldemdimensions.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNetherLavaLake extends WorldGenerator
{
    
    public WorldGenNetherLavaLake(Block i, int j, Block k, int l)
    {
        liquidId = i;
        liquidMeta = j;
        replacedId = k;
        replacedMeta = l;
    }
 
    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        int radiusX = random.nextInt(5) + 3;
        int radiusZ = radiusX;//random.nextInt(3) + 3;
        Block l;
        Block l1;
        int l2;
        byte b = 0;
        for(int offsetX = -radiusX; offsetX <= radiusX; offsetX += radiusX)
        {
            for(int offsetZ = -radiusZ; offsetZ <= radiusZ; offsetZ += radiusZ)
            {
                l = world.getBlock(i + offsetX, j, k + offsetZ);
                l1 = world.getBlock(i + offsetX, j + 1, k + offsetZ);
                l2 = world.getBlockMetadata(i + offsetX, j, k + offsetZ);
                //System.out.println(l);
                if((replacedId != null && l != replacedId) || (replacedMeta != -1 && l2 != replacedMeta) || !world.getBlock(i + offsetX, j, k + offsetZ).getMaterial().isSolid())//(l != Blocks.netherrack && l != AllDemDimensions.netherStone)
                {
                    //System.out.println("a");
                    return true;
                }
                if(l1 != Blocks.air)
                {
                    b++;
                }
                if(b > 4)
                {
                    //System.out.println("b");
                    return true;
                }
            }
        }
        addLayer(world, random, i, j, k, 0, radiusX, radiusZ);
        
        return true;
    }
    
    private void addLayer(World world, Random random, int i, int j, int k, int layer, int radiusX, int radiusZ)
    {
        Block blockID = Blocks.air;
        int blockMeta = 0;
        if(layer > 0)
        {
            blockID = liquidId;
            blockMeta = liquidMeta;
        }
        for(int width = -radiusX; width < radiusX; width++)
        {
            for(int height = 0; height < 1; height++)
            {
                for(int depth = -radiusZ; depth < radiusZ; depth++)
                {
                    int l = (int)Math.round(Math.sqrt(Math.pow(width, 2) + Math.pow(depth, 2)));
                    if(l < radiusX || (l < radiusX + 1 && random.nextBoolean()))
                    {
                    	setBlockAndNotifyAdequately(world, i + width, j + height, k + depth, blockID, blockMeta);
                    }
                }
            }
        }
        
        
        layer++;
        if(radiusX > 0 && radiusZ > 0)
        {
            addLayer(world, random, i, j - 1, k, layer, radiusX - 1, radiusZ - 1);
        }
    }
    
    private Block liquidId;
    private int liquidMeta;
    private Block replacedId = null;
    private int replacedMeta = -1;
}
