package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.world.World;
import alldemdimensions.world.Dimension;

public class WorldGenSkyTree extends WorldGenTreeBase
{

	public WorldGenSkyTree()
    {
    	super();
    }
 
    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
	{
		if(world.isAirBlock(i, j + 12, k) && world.isAirBlock(i, j + 24, k) && Dimension.zenith.isValidForWorldGen(world, i, j, k))
		{
			addTrunk(world, random, i, j, k);
		}
		return true;
	}
	
	private void addTrunk(World world, Random random, int i, int j, int k)
	{
		//tier 1
		int l = random.nextInt(12) + 12;
		addVerticalLine(world, random, i, j, k, l);
		addVerticalLine(world, random, i + 1, j, k, l);
		addVerticalLine(world, random, i, j, k + 1, l);
		addVerticalLine(world, random, i - 1, j, k, l);
		addVerticalLine(world, random, i, j, k - 1, l);
		addLeaves(world, random, i, j + l - 1, k, random.nextInt(3) + 4);
		
		//tier 2
		l = 5;
		addVerticalLine(world, random, i + 2, j - 1, k, l);
		addVerticalLine(world, random, i, j - 1, k + 2, l);
		addVerticalLine(world, random, i - 2, j - 1, k, l);
		addVerticalLine(world, random, i, j - 1, k - 2, l);
		
		addVerticalLine(world, random, i + 1, j - 1, k + 1, l);
		addVerticalLine(world, random, i - 1, j - 1, k + 1, l);
		addVerticalLine(world, random, i - 1, j - 1, k - 1, l);
		addVerticalLine(world, random, i + 1, j - 1, k - 1, l);
		
		//tier 3
		addVerticalLine(world, random, i + 3, j - 3, k, l);
		addVerticalLine(world, random, i, j - 3, k + 3, l);
		addVerticalLine(world, random, i - 3, j - 3, k, l);
		addVerticalLine(world, random, i, j - 3, k - 3, l);
		
		addVerticalLine(world, random, i + 1, j - 3, k + 2, l);
		addVerticalLine(world, random, i - 1, j - 3, k + 2, l);
		addVerticalLine(world, random, i - 1, j - 3, k - 2, l);
		addVerticalLine(world, random, i + 1, j - 3, k - 2, l);
		
		addVerticalLine(world, random, i + 2, j - 3, k + 1, l);
		addVerticalLine(world, random, i - 2, j - 3, k + 1, l);
		addVerticalLine(world, random, i - 2, j - 3, k - 1, l);
		addVerticalLine(world, random, i + 2, j - 3, k - 1, l);
	}
	
	private void addVerticalLine(World world, Random random, int i, int j, int k, int l)
	{
		for(int width = 0; width < 1; width++) 
		{  
			for(int height = 0; height < l; height++) 
			{ 
				for(int length = 0; length < 1; length++) 
				{
					setBlockAndNotifyAdequately(world, i+width, j+height, k+length, logBlock, logMeta);
				}                                              
			}
		}
	}
	
	private void addLeaves(World world, Random random, int i, int j, int k, int layers)
	{
		int yOffset = 0;
		for(int l = 0; l < layers; l++)
		{
			for(int i1 = 1; i1 < 6; i1++)
			{
				yOffset = (5 - i1) + (l * 4);
				addLayer(world, random, i, j + yOffset, k, i1, false);
			}
		}
		
		/*addLayer(world, random, i, j + 3, k, 5, false);
		addLayer(world, random, i, j + 4, k, 4, false);
		addLayer(world, random, i, j + 5, k, 3, false);
		addLayer(world, random, i, j + 6, k, 2, false);
		addLayer(world, random, i, j + 7, k, 1, false);*/
	}
	
	private void addLayer(World world, Random random, int i, int j, int k, int l, boolean flag)
	{
		int i1 = l - 1;
		for(int width = -i1; width < l; width++) 
		{  
			for(int height = 0; height < 1; height++) 
			{ 
				for(int length = -i1; length < l; length++) 
				{
					boolean flag1 = world.isAirBlock(i+width, j+height, k+length);
					if(!flag && flag1)
					{
						setBlockAndNotifyAdequately(world, i+width, j+height, k+length, leavesBlock, leavesMeta);
					} else
					if(flag1)
					{
						if(random.nextInt(2) == 0)
						{
							setBlockAndNotifyAdequately(world, i+width, j+height, k+length, leavesBlock, leavesMeta);
						}
					}
				}                                              
			}
		}
	}
	
}
