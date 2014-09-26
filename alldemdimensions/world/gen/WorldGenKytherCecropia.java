package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenKytherCecropia extends WorldGenKytherCanopyTree
{
    
    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        if(world.getBlock(i, j - 1, k) != Blocks.grass)
        {
            return true;
        }
        int height = random.nextInt(6) + 10;
        int l = 0;
        while(l < height)
        {
            func_150515_a(world, i, j + l, k, Blocks.log);
            if(l > height / 2)
            {
                 addBranch(world, random, i, j + l, k, random.nextInt(3) - 1, random.nextInt(3) - 1, 0, TYPE_HORIZONTAL);
            }
            l++;
        }
        return true;
    }
    
    @Override
    public void addLeaves(World world, Random random, int i, int j, int k)
    {
        int absX;
        int absZ;
        int width = random.nextInt(2) + 2;
        for(int i1 = -width; i1 < width + 1; i1++)
        {
            absX = Math.abs(i1);
            for(int j1 = 0; j1 < 2; j1++)
            {
                for(int k1 = -width; k1 < width + 1; k1++)
                {
                    //if(Math.abs(i1 * 2) + Math.abs(j1) + Math.abs(k1 * 2) < 10 && world.isAirBlock(i + i1, j + j1, k + k1))
                    absZ = Math.abs(k1);
                    if((j1 == 0 && absX == width && absZ == width) || (j1 == 1 && (absX == width || absZ == width)))
                    {
                    } else
                    {
                        func_150515_a(world, i + i1, j + j1, k + k1, Blocks.leaves);
                        /*if(random.nextBoolean() && Math.abs(i1) + Math.abs(j1 / 2) + Math.abs(k1) == 4)
                        {
                            addVine(world, random, i + i1, j + j1, k + k1);
                        }*/
                    }
                }
            }
        }        
    }
    
}
