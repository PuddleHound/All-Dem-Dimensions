package alldemdimensions.world.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.block.Plant;

public class WorldGenSkyLotus extends WorldGenerator
{
    
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        if(!world.isAirBlock(i, j, k))
        {
            return true;
        }
        int radius = random.nextInt(5) + 3;
        int indent = random.nextInt(4);
        for(int width = -radius + 1; width < radius; width++)
        {
            for(int depth = -radius + 1; depth < radius; depth++)
            {
                int i1 = (int)Math.round(Math.sqrt(Math.pow(width, 2) + Math.pow(width, 2) + Math.pow(depth, 2)));
                if(i1 < radius && world.isAirBlock(i+width, j, k+depth))
                {
                    setBlockAndNotifyAdequately(world, i+width, j, k+depth, AllDemDimensions.cloud, AllDemDimensions.cloud.LOTUS_PAD);
                }
            }
        }
        for(int length = -radius; length <= -radius / 2; length++)
        {
            if(indent == 0)
            {
                func_150515_a(world, i + length, j, k, Blocks.air);
            } else
            if(indent == 1)
            {
                func_150515_a(world, i - length, j, k, Blocks.air);
            } else
            if(indent == 2)
            {
                func_150515_a(world, i, j, k + length, Blocks.air);
            } else
            if(indent == 3)
            {
                func_150515_a(world, i, j, k - length, Blocks.air);
            }
        }
        setBlockAndNotifyAdequately(world, i, j + 1, k, Plant.zenith_lotus.plantBlock, Plant.zenith_lotus.plantMeta);
        return true;
    }
}
