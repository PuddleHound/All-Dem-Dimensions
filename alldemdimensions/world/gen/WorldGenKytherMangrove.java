package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenKytherMangrove extends WorldGenerator
{
    
    public WorldGenKytherMangrove()
    {
    }
    
    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        if((world.getBlock(i, j - 1, k) == Blocks.dirt && world.getBlock(i, j, k).getMaterial() == Material.water && world.isAirBlock(i, j + 3, k) && world.canBlockSeeTheSky(i, j + 3, k)) ||
                world.getBlock(i, j - 1, k) == Blocks.grass)
        {
            int height = random.nextInt(6) + 9;
            int l = 0;
            while(l < height)
            {
                func_150515_a(world, i, j + l, k, Blocks.log);
                l++;
            }
            int branchHeight;
            for(int counter = 0; counter < random.nextInt(8) + 12; counter++)
            {
                branchHeight = random.nextInt(height / 2);
                addBranch(world, random, i, j + (height / 2) + branchHeight, k, random.nextInt(3) - 1, random.nextInt(3) - 1, branchHeight / 3);
            }
            
        }
        return true;
    }
    
    private void addBranch(World world, Random random, int i, int j, int k, int dirX, int dirZ, int count)
    {
        if(count > 3)
        {
            return;
        }
        if(count < 0)
        {
            count = 0;
        }
        int length = random.nextInt(3) + 3 - (count * 2);
        if(length <= 0)
        {
            return;
        }
        for(int l = 0; l < length; l++)
        {
            func_150515_a(world, i + (l * dirX), j, k + (l * dirZ), Blocks.log);
            if(random.nextInt(3) == 0)
            {
                j++;
            }
        }
        addLeaves(world, random, i + (length * dirX), j, k + (length * dirZ));
        int newDirX = dirX;
        int newDirZ = dirZ;
        for(int counter = 0; counter < random.nextInt(3); counter++)
        {
            if(random.nextBoolean())
            {
                newDirX = 0;
            }
            if(random.nextBoolean())
            {
                newDirZ = 0;
            }
            addBranch(world, random, i + (length * dirX), j, k + (length * dirZ), newDirX, newDirZ, count + 1);
        }
    }
    
    private void addLeaves(World world, Random random, int i, int j, int k)
    {
        for(int i1 = -2; i1 < 3; i1++)
        {
            for(int j1 = 0; j1 < 2; j1++)
            {
                for(int k1 = -2; k1 < 3; k1++)
                {
                    if(Math.abs(i1) + Math.abs(j1 * 2) + Math.abs(k1) < 4 && world.isAirBlock(i + i1, j + j1, k + k1))
                    {
                        func_150515_a(world, i + i1, j + j1, k + k1, Blocks.leaves);
                    }
                }
            }
        }
        
    }
    
}
