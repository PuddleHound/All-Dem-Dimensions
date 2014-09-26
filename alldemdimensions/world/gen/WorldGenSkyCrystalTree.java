package alldemdimensions.world.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.world.Dimension;

public class WorldGenSkyCrystalTree extends WorldGenTreeBase
{
    
    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        if(!Dimension.zenith.isValidForWorldGen(world, i, j, k))
        {
            return true;
        }
        int trunkHeight = random.nextInt(16) + 24;
        for(int l = 0; l < trunkHeight; l++)
        {
            setBlockAndNotifyAdequately(world, i, j + l, k, logBlock, logMeta);
            setBlockAndNotifyAdequately(world, i + 1, j + l, k, logBlock, logMeta);
            setBlockAndNotifyAdequately(world, i - 1, j + l, k, logBlock, logMeta);
            setBlockAndNotifyAdequately(world, i, j + l, k + 1, logBlock, logMeta);
            setBlockAndNotifyAdequately(world, i, j + l, k - 1, logBlock, logMeta);
        }
        int leafSize = 6;
        for(int x = -leafSize / 2; x < leafSize / 2; x++)
        {
            for(int y = -leafSize / 2; y < leafSize / 2; y++)
            {
                for(int z = -leafSize / 2; z < leafSize / 2; z++)
                {
                    if(Math.abs(x) + Math.abs(y) + Math.abs(z) < leafSize * 2 / 3 && world.isAirBlock(i + x, j + y + trunkHeight, k + z))
                    {
                        setBlockAndNotifyAdequately(world, i + x, j + y + trunkHeight, k + z, leavesBlock, leavesMeta);
                    }
                }
            }
        }
        for(int i1 = trunkHeight / 3; i1 < trunkHeight; i1 += trunkHeight / 6)
        {
            addBranches(world, random, i, j + i1, k, (trunkHeight / 8) + ((trunkHeight - i1) / 6));//(trunkHeight - i1) / ((trunkHeight - i1 + 2) / 3));
        }
        return true;
    }
    
    public void addBranches(World world, Random random, int i, int j, int k, int branchLength)
    {
        if(branchLength < 4)
        {
            return;
        }
        for(int dirX = -1; dirX < 2; dirX++)
        {
            for(int dirZ = -1; dirZ < 2; dirZ++)
            {
                if(dirX == 0 && dirZ == 0)
                {
                    continue;
                }
                int height = addBranch(world, random, i, j, k, branchLength, dirX, dirZ);
                addBranches(world, random, i + (branchLength * dirX), j + height, k + (branchLength * dirZ), branchLength / 2);
            }
        }
    }
    
    public int addBranch(World world, Random random, int i, int j, int k, int branchLength, int dirX, int dirZ)
    {
        int width = 0, height = 0, depth = 0;
        for(int counter = 0; counter < branchLength; counter++)
        {
            if(counter == branchLength / 2 || counter == branchLength * 3 / 4 || counter == branchLength * 7 / 8)
            {
                height++;
            }
            width = counter * dirX;
            depth = counter * dirZ;
            setBlockAndNotifyAdequately(world, i + width, j + height, k + depth, logBlock, logMeta);
            if(random.nextInt(16) == 0)
            {
                setBlockAndNotifyAdequately(world, i + width, j + height - 1, k + depth, AllDemDimensions.crystal, 2);
            }
            if(random.nextInt(32) == 0)
            {
                setBlockAndNotifyAdequately(world, i + width + 1, j + height + 1, k + depth, Blocks.glass, 0);
                setBlockAndNotifyAdequately(world, i + width - 1, j + height + 1, k + depth, Blocks.glass, 0);
                setBlockAndNotifyAdequately(world, i + width, j + height + 1, k + depth + 1, Blocks.glass, 0);
                setBlockAndNotifyAdequately(world, i + width, j + height + 1, k + depth - 1, Blocks.glass, 0);
                setBlockAndNotifyAdequately(world, i + width, j + height + 2, k + depth, Blocks.glass, 0);
                setBlockAndNotifyAdequately(world, i + width, j + height + 1, k + depth, Blocks.torch, 0);
            }
        }
        for(int x = -branchLength / 2; x < branchLength / 2; x++)
        {
            for(int y = -branchLength / 2; y < branchLength / 2; y++)
            {
                for(int z = -branchLength / 2; z < branchLength / 2; z++)
                {
                    if(Math.abs(x) + Math.abs(y) + Math.abs(z) < branchLength * 2 / 3 && world.isAirBlock(i + x + width, j + y + height, k + z + depth))
                    {
                        setBlockAndNotifyAdequately(world, i + x + width, j + y + height, k + z + depth, leavesBlock, leavesMeta);
                    }
                }
            }
        }
        return height;
    }
    
    public void oldBranchGeneration(World world, Random random, int i, int j, int k)
    {
        int trunkHeight = random.nextInt(12) + 12;
        for(int l = 0; l < trunkHeight; l++)
        {
            setBlockAndNotifyAdequately(world, i, j + l, k, AllDemDimensions.crystal, AllDemDimensions.crystal.CUBE);
        }
        int runX, runZ, rise, length;
        for(int i1 = trunkHeight / 2; i1 < trunkHeight; i1++)
        {
            for(int k1 = 0; k1 < 2; k1++)
            {
            rise = (int)(((float)i1 / (float)trunkHeight) * 6F - 4F);
            runX = random.nextInt(6) - 3;
            runZ = random.nextInt(6) - 3;
            length = random.nextInt(trunkHeight / 3) + ((trunkHeight - i1) / 2);
            int x = 0, y = 0, z = 0;
            for(int j1 = 0; j1 < length; j1++)
            {
                if(runX != 0 && j1 % runX == 0)
                {
                    if(runX > 0)
                    {
                        x++;
                    } else
                    if(runX < 0)
                    {
                        x--;
                    }
                }
                if(rise != 0 && j1 % rise == 0)
                {
                    if(rise > 0)
                    {
                        y++;
                    } else
                    if(rise < 0)
                    {
                        y--;
                    }
                }
                if(runZ != 0 && j1 % runZ == 0)
                {
                    if(runZ > 0)
                    {
                        z++;
                    } else
                    if(runZ < 0)
                    {
                        z--;
                    }
                }
                setBlockAndNotifyAdequately(world, i + x, j + y + i1, k + z, AllDemDimensions.crystal, AllDemDimensions.crystal.CUBE);
            }
            }
        }
        
    }
}
