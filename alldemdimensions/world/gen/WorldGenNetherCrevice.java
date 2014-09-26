package alldemdimensions.world.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNetherCrevice extends WorldGenerator
{
 
    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        if(j <= 5 || j > 32 || !world.getBlock(i, j, k).getMaterial().isSolid())
        {
            return true;
        }
        addSection(world, random, i, j, k, 0, random.nextInt(3) - 1, random.nextInt(3) - 1);
        
        return true;
    }
    
    private void addSection(World world, Random random, int i, int j, int k, int count, int dirX, int dirZ)
    {
        if(random.nextInt(3) == 0 || (dirX == 0 && random.nextBoolean()))
        {
            dirX = random.nextInt(3) - 1;
        }
        if(random.nextInt(3) == 0 || (dirZ == 0 && random.nextBoolean()))
        {
            dirZ = random.nextInt(3) - 1;
        }
        if((dirX == 0 && dirZ == 0) || count > 64)
        {
            return;
        }
        int l = 0;
        while(l < 32)
        {
            if(world.isAirBlock(i, j + l, k))
            {
                break;
            }
            func_150515_a(world, i, j + l, k, Blocks.air);
            func_150515_a(world, i + 1, j + l, k, Blocks.air);
            l++;
        }
        addSection(world, random, i + dirX, j + random.nextInt(3) - 1, k + dirZ, count + 1, dirX, dirZ);
    }
}