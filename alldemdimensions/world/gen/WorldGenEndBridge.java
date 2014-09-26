package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenEndBridge extends WorldGenerator
{

    public WorldGenEndBridge()
    {
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        if(world.isAirBlock(i, j, k))
		{
			if(world.getBlock(i + 1, j, k) == Blocks.end_stone && world.isAirBlock(i + 1, j + 1, k))
			{
				addSegments(world, random, i, j, k, 0, 0);//-i
			} else
			if(world.getBlock(i, j, k + 1) == Blocks.end_stone && world.isAirBlock(i, j + 1, k + 1))
			{
				addSegments(world, random, i, j, k, 1, 0);//-k
			} else
			if(world.getBlock(i - 1, j, k) == Blocks.end_stone && world.isAirBlock(i - 1, j + 1, k))
			{
				addSegments(world, random, i, j, k, 2, 0);//+i
			} else
			if(world.getBlock(i, j, k - 1) == Blocks.end_stone && world.isAirBlock(i, j + 1, k - 1))
			{
				addSegments(world, random, i, j, k, 3, 0);//+k
			}
		}
        return true;
    }
	
	private boolean addSegments(World world, Random random, int i, int j, int k, int l, int i2)
	{
		i2++;
		if(i2 > 5)
		{
			return false;
		}
                int segmentCount = 0;
                byte corner = 0;
		if(l == 0)
		{
			int i1 = 0;
			while(i1 < 64)
			{
				segmentCount = generateSegment(world, random, i - i1, j, k, l, segmentCount);
				i1++;
				if(world.getBlock(i - i1, j, k) == Blocks.end_stone)
				{
					break;
				}
				if(i1 == 61)
				{
					if(random.nextInt(2) == 0)
					{
						if(addSegments(world, random, i - i1, j, k + 3, 1, i2))
                                                {
                                                    corner = -1;
                                                }
					} else
					{
						if(addSegments(world, random, i - i1, j, k - 3, 3, i2))
                                                {
                                                    corner = 1;
                                                }
					}
				}
			}
                        if(corner != 0)
                        {
                            addBox(world, random, i - i1, j + 1, k, 1, 8, 0, 3, -2, 3, Blocks.air);
                            if(corner < 0)
                            {
                                addBox(world, random, i - i1, j + 1, k, 1, 6, 0, 3, -4, -2, Blocks.air);
                            } else
                            {
                                addBox(world, random, i - i1, j + 1, k, 1, 6, 0, 3, 3, 5, Blocks.air);
                            }
                        }
		} else
		if(l == 1)
		{
			int j1 = 0;
			while(j1 < 64)
			{
				segmentCount = generateSegment(world, random, i, j, k - j1, l, segmentCount);
				j1++;
				if(world.getBlock(i, j, k - j1) == Blocks.end_stone)
				{
					break;
				}
				if(j1 == 61)
				{
					if(random.nextInt(2) == 0)
					{
						if(addSegments(world, random, i + 3, j, k - j1, 0, i2))
                                                {
                                                    corner = -1;
                                                }
					} else
					{
						if(addSegments(world, random, i - 3, j, k - j1, 2, i2))
                                                {
                                                    corner = 1;
                                                }
					}
				}
                                
			}
                        if(corner != 0)
                        {
                            addBox(world, random, i, j + 1, k - j1, -2, 3, 0, 3, 1, 8, Blocks.air);
                            if(corner < 0)
                            {
                                addBox(world, random, i, j + 1, k - j1, -4, -2, 0, 3, 1, 6, Blocks.air);
                            } else
                            {
                                addBox(world, random, i, j + 1, k - j1, 3, 5, 0, 3, 1, 6, Blocks.air);
                            }
                        }
		} else
		if(l == 2)
		{
			int k1 = 0;
			while(k1 < 64)
			{
				segmentCount = generateSegment(world, random, i + k1, j, k, l, segmentCount);
				k1++;
				if(world.getBlock(i + k1, j, k) == Blocks.end_stone)
				{
					break;
				}
				if(k1 == 61)
				{
					if(random.nextInt(2) == 0)
					{
						if(addSegments(world, random, i + k1, j, k + 3, 1, i2))
                                                {
                                                    corner = -1;
                                                }
					} else
					{
						if(addSegments(world, random, i + k1, j, k - 3, 3, i2))
                                                {
                                                    corner = 1;
                                                }
					}
				}
			}
                        if(corner != 0)
                        {
                            addBox(world, random, i + k1, j + 1, k, -7, 0, 0, 3, -2, 3, Blocks.air);
                            if(corner < 0)
                            {
                                addBox(world, random, i + k1, j + 1, k, -5, 0, 0, 3, -4, -2, Blocks.air);
                            } else
                            {
                                addBox(world, random, i + k1, j + 1, k, -5, 0, 0, 3, 3, 5, Blocks.air);
                            }
                        }
		} else
		if(l == 3)
		{
			int l1 = 0;
			while(l1 < 64)
			{
				segmentCount = generateSegment(world, random, i, j, k + l1, l, segmentCount);
				l1++;
				if(world.getBlock(i, j, k + l1) == Blocks.end_stone)
				{
					break;
				}
				if(l1 == 61)
				{
					if(random.nextInt(2) == 0)
					{
						if(addSegments(world, random, i + 3, j, k + l1, 0, i2))
                                                {
                                                    corner = -1;
                                                }
					} else
					{
						if(addSegments(world, random, i - 3, j, k + l1, 2, i2))
                                                {
                                                    corner = 1;
                                                }
					}
				}
			}
                        if(corner != 0)
                        {
                            addBox(world, random, i, j + 1, k + l1, -2, 3, 0, 3, -7, 0, Blocks.air);
                            if(corner < 0)
                            {
                                addBox(world, random, i, j + 1, k + l1, -4, -2, 0, 3, -5, 0, Blocks.air);
                            } else
                            {
                                addBox(world, random, i, j + 1, k + l1, 3, 5, 0, 3, -5, 0, Blocks.air);
                            }
                        }
		}
                return true;
	}
	
	private int generateSegment(World world, Random random, int i, int j, int k, int l, int segmentCount)
	{
		if(segmentCount > 3)
		{
			segmentCount = 0;
		}
		int i1 = -3;//-2
		int j1 = 4;//3
		int k1 = 0;
		int l1 = 1;
		if(l == 0 || l == 2)
		{
			i1 = 0;
			j1 = 1;
			k1 = -3;//-2
			l1 = 4;//3
		}
		addBox(world, random, i, j, k, i1, j1, 0, 1, k1, l1, Blocks.obsidian);
                if(l == 0 || l == 2)
                {
                    k1++;
                    l1--;
                } else
                {
                    i1++;
                    j1--;
                }
		addBox(world, random, i, j + 1, k, i1, j1, 0, 3, k1, l1, Blocks.air);
		if(l == 1 || l == 3)
		{
			func_150515_a(world, i - 3, j + 1, k, Blocks.obsidian);
			func_150515_a(world, i + 3, j + 1, k, Blocks.obsidian);
		} else
		if(l == 0 || l == 2)
		{
			func_150515_a(world, i, j + 1, k - 3, Blocks.obsidian);
			func_150515_a(world, i, j + 1, k + 3, Blocks.obsidian);
		}
		if(segmentCount == 2 && (l == 1 || l == 3))
		{
			addBox(world, random, i - 4, j + 1, k, 0, 1, 0, 3, 0, 1, Blocks.obsidian);
			addBox(world, random, i + 4, j + 1, k, 0, 1, 0, 3, 0, 1, Blocks.obsidian);
			func_150515_a(world, i - 3, j - 1, k, Blocks.obsidian);
			func_150515_a(world, i + 3, j - 1, k, Blocks.obsidian);
		} else
		if(segmentCount == 2 && (l == 0 || l == 2))
		{
			addBox(world, random, i, j + 1, k - 4, 0, 1, 0, 3, 0, 1, Blocks.obsidian);
			addBox(world, random, i, j + 1, k + 4, 0, 1, 0, 3, 0, 1, Blocks.obsidian);
			func_150515_a(world, i, j - 1, k - 3, Blocks.obsidian);
			func_150515_a(world, i, j - 1, k + 3, Blocks.obsidian);
		}
		segmentCount++;
                return segmentCount;
	}
	
	private void addBox(World world, Random random, int i, int j, int k, int i1, int i2, int j1, int j2, int k1, int k2, Block blockID)
	{
		for(int width = i1; width < i2; width++)
		{
			for(int height = j1; height < j2; height++)
			{
				for(int depth = k1; depth < k2; depth++)
				{
					func_150515_a(world, i + width, j + height, k + depth, blockID);
				}
			}
		}
	}
	
}
