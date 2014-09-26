package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenKytherCanopyTree extends WorldGenerator
{

    public WorldGenKytherCanopyTree()
    {
    }
    
    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        if(world.getBlock(i, j - 1, k) != Blocks.grass)
        {
            return true;
        }
        int type = random.nextInt(3);
        int height = random.nextInt(12) + 24;
        int l = 0;
        while(l < height)
        {
            func_150515_a(world, i, j + l, k, Blocks.log);
            func_150515_a(world, i + 1, j + l, k, Blocks.log);
            func_150515_a(world, i - 1, j + l, k, Blocks.log);
            func_150515_a(world, i, j + l, k + 1, Blocks.log);
            func_150515_a(world, i, j + l, k - 1, Blocks.log);
            if(l > height / 4 && random.nextInt(4) == 0)
            {
                addBranch(world, random, i, j + l, k, random.nextInt(3) - 1, random.nextInt(3) - 1, -1, TYPE_DEFAULT);
            }
            if(random.nextBoolean())
            {
                addVine(world, random, i + random.nextInt(3) - 1, j + l, k + random.nextInt(3) - 1);
            }
            l++;
        }
        int branchHeight;
        for(int counter = 0; counter < random.nextInt(8) + 12; counter++)
        {
            branchHeight = random.nextInt(height / 4);
            addBranch(world, random, i, j + ((height * 3) / 4) + branchHeight, k, random.nextInt(3) - 1, random.nextInt(3) - 1, 0, type);
        }
        return true;
    }

    public void addBranch(World world, Random random, int i, int j, int k, int dirX, int dirZ, int count, int type)
    {
        if(count > 3)
        {
            return;
        }
        int rise = random.nextInt(6) - 3;
        if(rise == 0)
        {
            rise = 1;
        }
        //int run = random.nextInt(3);
        int length;
        if(type == TYPE_WIDE_COVER)
        {
            length = random.nextInt(6) + rise + 2;
        } else
        if(type == TYPE_HORIZONTAL)
        {
            length = random.nextInt(4) + rise;
        } else
        if(type == TYPE_VERTICAL)
        {
            length = random.nextInt(4) - rise;
        } else
        {
            length = random.nextInt(4) + 1;
        }
        
        int xzOffset = 0;
        int yOffset = 0;
        for(int l = 0; l < length; l++)
        {
            if(rise > 0)
            {
                xzOffset++;
                if(l % rise == 0)
                {
                    yOffset++;
                }
            } else
            if(rise < 0)
            {
                yOffset++;
                if(l % rise == 0)
                {
                    xzOffset++;
                }
            }
            func_150515_a(world, i + (xzOffset * dirX), j + yOffset, k + (xzOffset * dirZ), Blocks.log);
        }
        if(random.nextInt(3) == 0 || count == 3 || count == -1)
        {
            addLeaves(world, random, i + (xzOffset * dirX), j + yOffset, k + (xzOffset * dirZ));
        }
        
        if(count < 0)
        {
            return;
        }
        int newDirX;
        int newDirZ;
        for(int counter = 0; counter < random.nextInt(3); counter++)
        {
            if(dirX > 0)
            {
                newDirX = random.nextInt(2);
            } else
            if(dirX < 0)
            {
                newDirX = random.nextInt(2) - 1;
            } else
            {
                newDirX = random.nextInt(3) - 1;
            }
            if(dirZ > 0)
            {
                newDirZ = random.nextInt(2);
            } else
            if(dirZ < 0)
            {
                newDirZ = random.nextInt(2) - 1;
            } else
            {
                newDirZ = random.nextInt(3) - 1;
            }
            addBranch(world, random, i + (xzOffset * dirX), j + yOffset, k + (xzOffset * dirZ), newDirX, newDirZ, count + 1, type);
        }
    }
    
    public void addLeaves(World world, Random random, int i, int j, int k)
    {
        for(int i1 = -2; i1 < 3; i1++)
        {
            for(int j1 = 0; j1 < 2; j1++)
            {
                for(int k1 = -2; k1 < 3; k1++)
                {
                    if(Math.abs(i1) + Math.abs(j1) + Math.abs(k1) < 4 && world.isAirBlock(i + i1, j + j1, k + k1))
                    {
                        func_150515_a(world, i + i1, j + j1, k + k1, Blocks.leaves);
                        if(random.nextBoolean() && Math.abs(i1) + Math.abs(j1) + Math.abs(k1) == 3)
                        {
                            addVine(world, random, i + i1, j + j1, k + k1);
                        }
                    }
                }
            }
        }        
    }
    
    public void addVine(World world, Random random, int i, int j, int k)
    {
        int metadata;
        int sideX;
        int sideZ;
        if(random.nextBoolean())
        {
            sideZ = 0;
            if(random.nextBoolean())
            {
                sideX = 1;
                metadata = 2;
            } else
            {
                sideX = -1;
                metadata = 8;
            }
        } else
        {
            sideX = 0;
            if(random.nextBoolean())
            {
                sideZ = 1;
                metadata = 4;
            } else
            {
                sideZ = -1;
                metadata = 1;
            }
        }
        if(world.isAirBlock(i - sideX, j, k - sideZ))
        {
            return;
        }
        int length = random.nextInt(6) + 1;
        byte counter = 0;
        while(counter < length)
        {
            if(!world.isAirBlock(i + sideX, j - counter, k + sideZ))
            {
                break;
            }
            setBlockAndNotifyAdequately(world, i + sideX, j - counter, k + sideZ, Blocks.vine, metadata);
            counter++;
        }
    }
    
    public static final byte TYPE_DEFAULT = 0;
    public static final byte TYPE_HORIZONTAL = 1;
    public static final byte TYPE_VERTICAL = 2;
    public static final byte TYPE_WIDE_COVER = 3;
    
}
