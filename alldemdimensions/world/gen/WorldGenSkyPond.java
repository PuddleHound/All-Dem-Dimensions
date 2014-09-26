package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;

public class WorldGenSkyPond extends WorldGenerator
{

    public WorldGenSkyPond(Block i, Block j, Block k)
    {
		grassBlock = i;
		stillLiquidBlock = j;
		movingLiquidBlock = k;
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		/*BiomeGenBase biomegenbase = world.getWorldChunkManager().getBiomeGenAt(i, k);
		if(biomegenbase == AllDemDimensions.skyIce)
		{
			stillLiquidBlock = Blocks.ice;
			movingLiquidBlock = Blocks.ice;
		} else
		{
			stillLiquidBlock = Blocks.waterStill;
			movingLiquidBlock = Blocks.waterMoving;
		}*/
		/**int l = 6;//random.nextInt(4) + 2;
		//addBlocks(world, random, i, j, k, l, false);
		//addBlocks(world, random, i, j, k, l - 1, true);
		int i1 = l / 2;
		int j1 = 0;
		int blockID = Blocks.blockDiamond;//Blocks.grass;
		int blockID1 = Blocks.blockDiamond;//blockID;
		while(i1 > 0)
		{**/
			/*if(j1 == 1)
			{
				blockID = Blocks.dirt;
				blockID1 = AllDemDimensions.skySand;
			}
			if(j1 > 1)
			{
				blockID = blockID1;
			}*/
			/**addCircle(world, random, i, j - j1, k, l - j1, blockID, blockID1);
			i1--;
			j1++;
		}**/
		//addLake(world, random, i, j, k, 0);
		
		addLake_new(world, random, i, j, k, 0);
		
        return true;
    }
	
	private void addLake(World world, Random random, int i, int j, int k, int lakeCount)
	{
		addCircle(world, random, i, j, k, 6, grassBlock);
		addCircle(world, random, i, j, k, 4, stillLiquidBlock);
		addCircle(world, random, i, j - 1, k, 4, Blocks.dirt);//AllDemDimensions.skySand);
		addCircle(world, random, i, j - 2, k, 2, Blocks.dirt);//AllDemDimensions.skySand);
		lakeCount++;
		if(random.nextInt(3) == 0 || lakeCount >= 4)
		{
			return;
		}
		int l = random.nextInt(4);
		int yOffset = -6 - random.nextInt(10);
		if(l == 0)
		{
			func_150515_a(world, i + 4, j, k, movingLiquidBlock);
			func_150515_a(world, i + 4, j, k - 1, movingLiquidBlock);
			func_150515_a(world, i + 5, j, k, movingLiquidBlock);
			func_150515_a(world, i + 5, j, k - 1, movingLiquidBlock);
			if(movingLiquidBlock == Blocks.ice)
			{
				int counter = 0;
				while(counter >= yOffset)
				{
					counter--;
					func_150515_a(world, i + 5, j + counter, k, movingLiquidBlock);
					func_150515_a(world, i + 5, j + counter, k - 1, movingLiquidBlock);
				}
			}
			addLake(world, random, i + 4 + random.nextInt(2), j + yOffset, k - 1 + random.nextInt(2), lakeCount);
		}
		if(l == 1)
		{
			func_150515_a(world, i - 4, j, k, movingLiquidBlock);
			func_150515_a(world, i - 4, j, k - 1, movingLiquidBlock);
			func_150515_a(world, i - 5, j, k, movingLiquidBlock);
			func_150515_a(world, i - 5, j, k - 1, movingLiquidBlock);
			if(movingLiquidBlock == Blocks.ice)
			{
				int counter = 0;
				while(counter >= yOffset)
				{
					counter--;
					func_150515_a(world, i - 5, j + counter, k, movingLiquidBlock);
					func_150515_a(world, i - 5, j + counter, k - 1, movingLiquidBlock);
				}
			}
			addLake(world, random, i - 4 - random.nextInt(2), j + yOffset, k - 1 + random.nextInt(2), lakeCount);
		}
		if(l == 2)
		{
			func_150515_a(world, i, j, k + 4, movingLiquidBlock);
			func_150515_a(world, i - 1, j, k + 4, movingLiquidBlock);
			func_150515_a(world, i, j, k + 5, movingLiquidBlock);
			func_150515_a(world, i - 1, j, k + 5, movingLiquidBlock);
			if(movingLiquidBlock == Blocks.ice)
			{
				int counter = 0;
				while(counter > yOffset)
				{
					counter--;
					func_150515_a(world, i, j + counter, k + 5, movingLiquidBlock);
					func_150515_a(world, i - 1, j + counter, k + 5, movingLiquidBlock);
				}
			}
			addLake(world, random, i - 1 + random.nextInt(2), j + yOffset, k + 4 + random.nextInt(2), lakeCount);
		}
		if(l == 3)
		{
			func_150515_a(world, i, j, k - 4, movingLiquidBlock);
			func_150515_a(world, i - 1, j, k - 4, movingLiquidBlock);
			func_150515_a(world, i, j, k - 5, movingLiquidBlock);
			func_150515_a(world, i - 1, j, k - 5, movingLiquidBlock);
			if(movingLiquidBlock == Blocks.ice)
			{
				int counter = 0;
				while(counter > yOffset)
				{
					counter--;
					func_150515_a(world, i, j + counter, k - 5, movingLiquidBlock);
					func_150515_a(world, i - 1, j + counter, k - 5, movingLiquidBlock);
				}
			}
			addLake(world, random, i - 1 + random.nextInt(2), j + yOffset, k - 4 - random.nextInt(2), lakeCount);
		}
	}
	
	private void addCircle(World world, Random random, int i, int j, int k, int l, Block blockID)
	{
		for(int width = -l; width < l; width++)
		{
			for(int height = 0; height < 1; height++)
			{
				for(int depth = -l; depth < l; depth++)
				{
					int l1 = (int)Math.round(Math.sqrt(Math.pow(width,2) + Math.pow(width,2) + Math.pow(depth,2)));
					if(l1 <= l)
					{
						/*if(l - 1 != 0 && l1 <= 2)
						{
							func_150515_a(world, i + width, j + height, k + depth, stillLiquidBlock);
						} else*/
						/**if(random.nextInt(2) == 0)
						{
							func_150515_a(world, i + width, j + height, k + depth, blockID1);
						} else
						{**/
							func_150515_a(world, i + width, j + height, k + depth, blockID);
						//}
					}
				}
			}
		}
	/*
		for(int width = -l; width < l; width++)
		{
			for(int height = -l; height < l; height++)
			{
				for(int depth = -l; depth < l; depth++)
				{
					int i1 = (int)Math.round(Math.sqrt(Math.pow(width,2) + Math.pow(height,2) + Math.pow(depth,2)));
					if(i1 <= 4)
					{
						if(flag)
						{
							if(height < -l + 6)
							{
								func_150515_a(world, i + width, j + height, k + depth, stillLiquidBlock);
							}
							continue;
						}
						if(height < -l + 6 && height >= -l + 5)
						{
							if(random.nextInt(10) == 0)
							{
								func_150515_a(world, i + width, j + height, k + depth, movingLiquidBlock);
							} else
							{
								func_150515_a(world, i + width, j + height, k + depth, Blocks.grass);
							}
						} else
						if(height < -l + 5 && height >= -l + 4 && random.nextInt(2) == 0)
						{
							func_150515_a(world, i + width, j + height, k + depth, Blocks.dirt);
						} else
						if(height < -l + 4)
						{
							func_150515_a(world, i + width, j + height, k + depth, AllDemDimensions.skySand);
						}
					}
				}
			}
		}*/
	}
	
	////////////////////////////NEW///////////////////////////////////////////
	
	public void addLake_new(World world, Random random, int i, int j, int k, int lakeCount)
	{
		int radius = random.nextInt(lakeCount + 1) + 3;
		addHemisphere(world, random, i, j, k, radius);
		lakeCount++;
		if(random.nextInt(3) != 0 && lakeCount < 5)
		{
			int dirX = random.nextInt(3) - 1;
			int dirZ = random.nextInt(3) - 1;
			int newX = radius * dirX;
			int newZ = radius * dirZ;
			if(newX == 0 && newZ == 0)
			{
				return;
			}
			if(newX != 0 && newZ != 0)
			{
				newX /= 2;
				newZ /= 2;
				func_150515_a(world, i + newX - dirX, j - 1, k + newZ, stillLiquidBlock);
				func_150515_a(world, i + newX, j - 1, k + newZ, stillLiquidBlock);
				func_150515_a(world, i + newX, j - 1, k + newZ - dirZ, stillLiquidBlock);
			} else
			{
				func_150515_a(world, i + newX - dirX, j - 1, k + newZ - dirZ, stillLiquidBlock);
				func_150515_a(world, i + newX, j - 1, k + newZ, stillLiquidBlock);
				func_150515_a(world, i + newX + dirX, j - 1, k + newZ + dirZ, stillLiquidBlock);
			}
			int newY = random.nextInt(4) + (radius * 2);
			if(j - newY < 0)
			{
				return;
			}
			//
			addLake_new(world, random, i + newX, j - newY, k + newZ, lakeCount);
		}
	}
	
	public void addHemisphere(World world, Random random, int i, int j, int k, int radius)
	{
		for(int width = -radius; width < radius + 1; width++)
		{
			for(int height = -radius; height < 0; height++)
			{
				for(int depth = -radius; depth < radius + 1; depth++)
				{
					int i1 = (int)Math.round(Math.sqrt(Math.pow(width,2) + Math.pow(height,2) + Math.pow(depth,2)));
					if(i1 <= radius)
					{
						if(i1 < radius)
						{
							func_150515_a(world, i + width, j + height, k + depth, stillLiquidBlock);
						} else
						if(height == -1)
						{
							func_150515_a(world, i + width, j + height, k + depth, Blocks.grass);
						} else
						if(height < -1 && height > -7 + random.nextInt(3))
						{
							func_150515_a(world, i + width, j + height, k + depth, Blocks.dirt);
						} else
						{
							func_150515_a(world, i + width, j + height, k + depth, AllDemDimensions.limestone);
						}
					}
				}
			}
		}
	}
	
	//public int lakeCount = 0;
	public Block grassBlock;
	public Block stillLiquidBlock;
	public Block movingLiquidBlock;
	
	public static final byte POS_X = 0;
	public static final byte NEG_X = 1;
	public static final byte POS_Z = 2;
	public static final byte NEG_Z = 3;
	public static final byte POS_X_POS_Z = 4;
	public static final byte POS_X_NEG_Z = 5;
	public static final byte NEG_X_POS_Z = 6;
	public static final byte NEG_X_NEG_Z = 7;
	
}
