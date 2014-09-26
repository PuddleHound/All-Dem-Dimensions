package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.world.World;
import alldemdimensions.world.Dimension;

public class WorldGenSkyBonsaiTree extends WorldGenTreeBase
{

	public WorldGenSkyBonsaiTree()
    {
    	super();
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		if(!Dimension.zenith.isValidForWorldGen(world, i, j, k))// || !world.canBlockSeeTheSky(i, j - 1, k))
		{
			//System.out.println("invalid position");
			return true;
		}
		//System.out.println("generating");
		/*
		int trunkWidth = random.nextInt(2) + 4;
		int offsetX = 0;
		int offsetZ = 0;
		int counter = 0;
		int l = (random.nextInt(2) + 3);
		int i1 = (random.nextInt(2) + 3);
		while(trunkWidth > 0)
		{
			System.out.println("looping");
			generateCircle(world, random, i + offsetX, j + counter, k + offsetZ, trunkWidth, Blocks.wood, 0);
			counter++;
			if(counter % l == 0)
			{
				l = (random.nextInt(2) + 3);
				offsetX = random.nextInt(3) - 1;
				offsetZ = random.nextInt(3) - 1;
			}
			if(counter % i1 == 0)
			{
				i1 = (random.nextInt(2) + 3);
				trunkWidth--;
			}
		}*/
		int height = random.nextInt(8) + 8;
		int counter = 0;
		int offsetX = 0;
		int offsetZ = 0;
		while(counter <= height)
		{
			setBlockAndNotifyAdequately(world, i, j + counter, k, logBlock, logMeta);
			if(/*random.nextInt(2) == 0 && */counter > (height / 4))
			{
				generateBranch(world, random, i, j + counter, k);
			}
			if(random.nextInt(4) == 0 && counter > (height / 3))
			{
				generateLeaves(world, random, i + random.nextInt(2), j + counter, k + random.nextInt(2), random.nextInt(3) + 3, random.nextInt(2) + 2, random.nextInt(3) + 3);
			}
			if(random.nextInt(3) == 0)
			{
				offsetX = random.nextInt(3) - 1;
				offsetZ = random.nextInt(3) - 1;
				i += offsetX;
				k += offsetZ;
				setBlockAndNotifyAdequately(world, i, j + counter, k, logBlock, logMeta);
			}
			counter++;
		}
		generateLeaves(world, random, i, j + counter, k, 4, 2, 4);
		//System.out.println("finished generating");
        return true;
    }
	
	private void generateBranch(World world, Random random, int i, int j, int k)
	{
		int length = random.nextInt(4) + 4;
		int counter = 0;
		int dirX = random.nextInt(3) - 1;
		int dirY = random.nextInt(2);
		int dirZ = random.nextInt(3) - 1;
		while(counter <= length)
		{
			i += dirX;
			j += dirY;
			k += dirZ;
			setBlockAndNotifyAdequately(world, i, j, k, logBlock, logMeta);
			if(dirY > 0)
			{
				setBlockAndNotifyAdequately(world, i, j - 1, k, logBlock, logMeta);
			}
			if(random.nextInt(3) == 0)
			{
				dirX = random.nextInt(3) - 1;
				//world.setBlockWithNotify(i, j, k, Blocks.wood);
			}
			if(random.nextInt(3) == 0)
			{
				dirY = random.nextInt(2);
			}
			if(random.nextInt(3) == 0)
			{
				dirZ = random.nextInt(3) - 1;
			}
			counter++;
		}
		generateLeaves(world, random, i, j, k, random.nextInt(3) + 3, random.nextInt(2) + 2, random.nextInt(3) + 3);
		//world.setBlockWithNotify(i, j + counter, k, Blocks.wood);
	}
	
	private void generateLeaves(World world, Random random, int i, int j, int k, int sizeX, int sizeY, int sizeZ)
	{
		int l = (int)Math.ceil(-sizeX * 0.5D);
		int i1 = (int)Math.ceil(sizeX * 0.5D);
		int j1 = (int)Math.ceil(-sizeZ * 0.5D);
		int k1 = (int)Math.ceil(sizeZ * 0.5D);
		int l1;
		int i2;
		int diffX = sizeX - sizeY - 2;
		int diffZ = sizeZ - sizeY - 2;
		if(diffX < 0)
		{
			diffX = 0;
		}
		if(diffZ < 0)
		{
			diffZ = 0;
		}
		//System.out.println(l + "," + i1);
		for(int width = l; width < i1; width++)
		{
			for(int height = 0; height < sizeY + 1; height++)
			{
				for(int depth = j1; depth < k1; depth++)
				{
					if(sizeX % 2 == 0 && width < -diffX)
					{
						l1 = Math.abs(width + 1) - diffX;//add however many the limit requires
					} else
					{
						l1 = Math.abs(width) - diffX;//do same to negative, minus one
					}
					if(sizeZ % 2 == 0 && depth < -diffZ)
					{
						i2 = Math.abs(depth + 1) - diffZ;
					} else
					{
						i2 = Math.abs(depth) - diffZ;
					}
					if(l1 + Math.abs(height) + i2 <= sizeY)
					{
						if(!world.getBlock(i + width, j + height, k + depth).getMaterial().isSolid())
						{
							setBlockAndNotifyAdequately(world, i + width, j + height, k + depth, leavesBlock, leavesMeta);
						}
					}
				}
			}
		}
	}
	/*
	private void generateCircle(World world, Random random, int i, int j, int k, int diameter, int id, int meta)
	{
		int l = (int)Math.floor(-diameter * 0.5D);
		int i1 = (int)Math.floor(diameter * 0.5D);
		System.out.println(l + "," + i1);
		for(int width = l; width < i1; width++)
		{
			for(int height = 0; height < 1; height++)
			{
				for(int depth = l; depth < i1; depth++)
				{
					int j1 = Math.abs(width) + Math.abs(depth);
					//int j1 = (int)Math.round(Math.sqrt(Math.pow(width, 2) + Math.pow(width, 2) + Math.pow(depth, 2)));
					if(j1 < diameter - 1)
					{
						//System.out.println("Setting block");
						world.setBlockAndMetadataWithNotify(i + width, j + height, k + depth, id, meta);
					}
				}
			}
		}
	}*/
}
