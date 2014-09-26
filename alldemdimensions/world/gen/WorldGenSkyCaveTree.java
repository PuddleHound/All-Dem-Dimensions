package alldemdimensions.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;

public class WorldGenSkyCaveTree extends WorldGenerator
{
    
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        if(world.getBlock(i, j + 1, k) != AllDemDimensions.limestone || !world.isAirBlock(i, j, k))
        {
            return true;
        }
        int caveDepth = 1;
        for(; j - caveDepth >= 0 && world.isAirBlock(i, j - caveDepth, k); caveDepth++)
        {
        }
        if(caveDepth < 10)
        {
            return true;
        }
        int height = (caveDepth / 3) + random.nextInt(caveDepth / 4);
        int trunkHeight = j;
        for(; trunkHeight > j - (height / 2); trunkHeight--)
        {
            setBlockAndNotifyAdequately(world, i, j, k, logId, logMeta);
        }
        int dirXStart = 0, dirZStart = 0, dirX = 0, dirY, dirZ = 0;
        while(dirXStart == 0 && dirZStart == 0)
        {
            dirXStart = random.nextInt(3) - 1;
            dirZStart = random.nextInt(3) - 1;
        }
        dirX = dirXStart;
        dirY = -1;
        dirZ = dirZStart;
        int x = 0, y = 0, z = 0;
        for(int components = 0; components < 6; components++)
        {
            int l = 0;
            if(components == 0 || components == 2 || components == 5)
            {
                l = height / 8 > 1 ? height / 8 : 1;
            } else
            if(components == 1 || components == 3 || components == 4)
            {
                l = height / 4 > 1 ? height / 4 : 1;
            }
            
            if(components == 1)
            {
                dirX = 0;
                dirZ = 0;
            } else
            if(components == 2)
            {
                dirX = -dirXStart;
                dirZ = -dirZStart;
            } else
            if(components == 3)
            {
                dirY = 0;
            } else
            if(components == 4)
            {
                dirX = 0;
                dirY = 1;
                dirY = 0;
            } else
            if(components == 5)
            {
                dirX = dirXStart;
                dirZ = dirZStart;
            }
            
            for(int i1 = 0; i1 < l + i1; i1++)
            {
                x = (i1 * dirX);
                y = (i1 * dirY);
                z = (i1 * dirZ);
                setBlockAndNotifyAdequately(world, i + x, j + y, k + z, logId, logMeta);
            }
        }
        return true;
    }
    
    private Block logId = Blocks.log;
    private int logMeta = 0;
}
