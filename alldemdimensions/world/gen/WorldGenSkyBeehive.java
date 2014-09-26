package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.entity.EntityBee;

public class WorldGenSkyBeehive extends WorldGenerator
{

	public WorldGenSkyBeehive()
	{
		this(false);
	}

    public WorldGenSkyBeehive(boolean flag)
    {
		inTree = flag;
    }

        @Override
	public boolean generate(World world, Random random, int i, int j, int k)
    {
		if(!inTree && world.getBlock(i, j - 1, k) != AllDemDimensions.waxGrass)
		{
			return true;
		}
		for(int width = -3; width < 3; width++)
		{
			for(int height = 0; height < 6; height++)
			{
				for(int depth = -3; depth < 3; depth++)
				{
					func_150515_a(world, i + width, j + height, k + depth, Blocks.air);
				}
			}
		}
		addWall(world, random, i, j, k, 4, 1, 4, AllDemDimensions.beehive.HONEYCOMB_SIDE_1);
		addWall(world, random, i + 2, j + 1, k, 1, 4, 4, AllDemDimensions.beehive.HONEYCOMB_SIDE_4);
		addWall(world, random, i - 2, j + 1, k, 1, 4, 4, AllDemDimensions.beehive.HONEYCOMB_SIDE_5);
		addWall(world, random, i, j + 1, k + 2, 4, 4, 1, AllDemDimensions.beehive.HONEYCOMB_SIDE_2);
		addWall(world, random, i, j + 1, k - 2, 4, 4, 1, AllDemDimensions.beehive.HONEYCOMB_SIDE_3);
		addWall(world, random, i, j + 5, k, 4, 1, 4, AllDemDimensions.beehive.HONEYCOMB_SIDE_0);
		setBlockAndNotifyAdequately(world, i, j + 6, k, AllDemDimensions.beehive, AllDemDimensions.beehive.HONEYCOMB_SIDE_0);
		int l = random.nextInt(4);
		if(l == 0)
		{
			func_150515_a(world, i - 2, j + 2, k, Blocks.air);
		}
		if(l == 1)
		{
			func_150515_a(world, i + 2, j + 2, k, Blocks.air);
		}
		if(l == 2)
		{
			func_150515_a(world, i, j + 2, k - 2, Blocks.air);
		}
		if(l == 3)
		{
			func_150515_a(world, i, j + 2, k + 2, Blocks.air);
		}
		spawnBees(world, random, i, j, k);
		return true;
	}
	
	private void addWall(World world, Random random, int i, int j, int k, int sizeX, int sizeY, int sizeZ, int metadata)
	{
		int minX;
		int maxX;
		int minZ;
		int maxZ;
		if(sizeX == 1)
		{
			minX = 0;
			maxX = 1;
		} else
		{
			minX = (-sizeX / 2) + 1;
			maxX = sizeX / 2;
		}
		if(sizeZ == 1)
		{
			minZ = 0;
			maxZ = 1;
		} else
		{
			minZ = (-sizeZ / 2) + 1;
			maxZ = sizeZ / 2;
		}
		for(int x = minX; x < maxX; x++)
		{
			for(int y = 0; y < sizeY; y++)
			{
				for(int z = minZ; z < maxZ; z++)
				{
					setBlockAndNotifyAdequately(world, i + x, j + y, k + z, AllDemDimensions.beehive, metadata);
				}
			}
		}
	}
	/*
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		if(world.getBlock(i, j - 4, k) != AllDemDimensions.waxGrass)
		{
			return false;
		}
        for(int l = -3; l < 3; l++)
		{
			for(int i1 = -3; i1 < 3; i1++)
			{
				for(int j1 = -3; j1 < 3; j1++)
				{
					if((av(l) < 2 && av(i1) < 2) || (av(i1) < 2 && av(j1) < 2) || (av(j1) < 2 && av(l) < 2))
					{
						func_150515_a(world, i + l, j + i1, k + j1, 0);
					} else
					if((av(l) == 3 && av(i1) == 3) || (av(i1) == 3 && av(j1) == 3) || (av(j1) == 3 && av(l) == 3))
					{
					} else
					{
						func_150515_a(world, i + l, j + i1, k + j1, AllDemDimensions.beehive);
					}
				}
			}
		}
		func_150515_a(world, i, j + 3, k, AllDemDimensions.beehive);
        return true;
    }
	
	public int av(int i)
	{
		return Math.abs(i);
	}*/
	
	private void spawnBees(World world, Random random, int i, int j, int k)
	{
		for(int x = -8; x < 8; x++)
		{
			for(int y = -8; y < 8; y++)
			{
				for(int z = -8; z < 8; z++)
				{
					if(random.nextInt(100) == 0 && world.isAirBlock(i + x, j + y, k + z))
					{
						EntityBee bee = new EntityBee(world);
						bee.setBeeType(EntityBee.TYPE_WORKER_HIVE);
						bee.setHomeLocation(i + x, j + y, k + z);
						bee.setLocationAndAngles(i + x + 0.5D, j + y + 0.5D, k + z + 0.5D, random.nextFloat() * 360F, 0F);
						world.spawnEntityInWorld(bee);
					}
				}
			}
		}
	}
	
	private boolean inTree;
	
}
