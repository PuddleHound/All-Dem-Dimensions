package alldemdimensions.world.gen; 

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSkyCastleVillage extends WorldGenerator
{

    public WorldGenSkyCastleVillage()
    {
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		generateTower(world, random, i, j, k);
		return true;
	}
	
	private void generateTower(World world, Random random, int i, int j, int k)
	{
		int height = 32;
		for(byte b = 0; b < 4; b++)
		{
			generateTowerWall(world, random, i, j, k, height, b);
		}
	
	}
	
	private void generateTowerWall(World world, Random random, int i, int j, int k, int height, byte side)
	{
		byte offsetX = 0;
		byte offsetZ = 0;
		if(side == SIDE_POS_X)
		{
			offsetX = 5;
		} else
		if(side == SIDE_NEG_X)
		{
			offsetX = -5;
		} else
		if(side == SIDE_POS_Z)
		{
			offsetZ = 5;
		} else
		if(side == SIDE_NEG_Z)
		{
			offsetZ = -5;
		}
		for(int y = 0; y < height; y++)
		{
			if(side == SIDE_POS_X || side == SIDE_NEG_X)
			{
				if(y < 29)
				{
					setBlockAndNotifyAdequately(world, i + offsetX - (offsetX / 3), j + y, k - 3, Blocks.stonebrick, 0);
					setBlockAndNotifyAdequately(world, i + offsetX, j + y, k - 2, Blocks.cobblestone, 0);
					setBlockAndNotifyAdequately(world, i + offsetX, j + y, k - 1, Blocks.planks, 0);
					setBlockAndNotifyAdequately(world, i + offsetX, j + y, k, Blocks.stonebrick, 0);
					setBlockAndNotifyAdequately(world, i + offsetX, j + y, k + 1, Blocks.planks, 0);
					setBlockAndNotifyAdequately(world, i + offsetX, j + y, k + 2, Blocks.cobblestone, 0);
					setBlockAndNotifyAdequately(world, i + offsetX - (offsetX / 3), j + y, k + 3, Blocks.stonebrick, 0);
				}
				if(y > 22)
				{
					if(y <= 27)
					{
						setBlockAndNotifyAdequately(world, i + offsetX + (offsetX / 3), j + y, k - 2, Blocks.cobblestone, 0);
						if(y > 23)
						{
							setBlockAndNotifyAdequately(world, i + offsetX + (offsetX / 3), j + y, k, Blocks.stonebrick, 0);
						}
						setBlockAndNotifyAdequately(world, i + offsetX + (offsetX / 3), j + y, k + 2, Blocks.cobblestone, 0);
					} else
					{
						for(int z = -2; z <= 2; z++)
						{
							if(y == 31 && (z == 1 || z == -1))
							{
							} else
							{
								setBlockAndNotifyAdequately(world, i + offsetX + (offsetX / 3), j + y, k + z, Blocks.stonebrick, 0);
							}
						}
					}
					if(y > 23 && y < 31)
					{
						setBlockAndNotifyAdequately(world, i + offsetX, j + y, k + 3, Blocks.stonebrick, 0);
						setBlockAndNotifyAdequately(world, i + offsetX, j + y, k - 3, Blocks.stonebrick, 0);
						setBlockAndNotifyAdequately(world, i + offsetX - (offsetX / 3), j + y + 1, k + 4, Blocks.stonebrick, 0);
						setBlockAndNotifyAdequately(world, i + offsetX - (offsetX / 3), j + y + 1, k - 4, Blocks.stonebrick, 0);
					}
				}
			} else
			{
				if(y < 29)
				{
					setBlockAndNotifyAdequately(world, i - 3, j + y, k + offsetZ - (offsetZ / 3), Blocks.stonebrick, 0);
					setBlockAndNotifyAdequately(world, i - 2, j + y, k + offsetZ, Blocks.cobblestone, 0);
					setBlockAndNotifyAdequately(world, i - 1, j + y, k + offsetZ, Blocks.planks, 0);
					setBlockAndNotifyAdequately(world, i, j + y, k + offsetZ, Blocks.stonebrick, 0);
					setBlockAndNotifyAdequately(world, i + 1, j + y, k + offsetZ, Blocks.planks, 0);
					setBlockAndNotifyAdequately(world, i + 2, j + y, k + offsetZ, Blocks.cobblestone, 0);
					setBlockAndNotifyAdequately(world, i + 3, j + y, k + offsetZ - (offsetZ / 3), Blocks.stonebrick, 0);
				}
				if(y > 22)
				{
					if(y <= 27)
					{
						setBlockAndNotifyAdequately(world, i - 2, j + y, k + offsetZ + (offsetZ / 3), Blocks.cobblestone, 0);
						if(y > 23)
						{
							setBlockAndNotifyAdequately(world, i, j + y, k + offsetZ + (offsetZ / 3), Blocks.stonebrick, 0);
						}
						setBlockAndNotifyAdequately(world, i + 2, j + y, k + offsetZ + (offsetZ / 3), Blocks.cobblestone, 0);
					} else
					{
						for(int x = -2; x <= 2; x++)
						{
							if(y == 31 && (x == 1 || x == -1))
							{
							} else
							{
								setBlockAndNotifyAdequately(world, i + x, j + y, k + offsetZ + (offsetZ / 3), Blocks.stonebrick, 0);
							}
						}
					}
					if(y > 23 && y < 31)
					{
						setBlockAndNotifyAdequately(world, i + 3, j + y, k + offsetZ, Blocks.stonebrick, 0);
						setBlockAndNotifyAdequately(world, i - 3, j + y, k + offsetZ, Blocks.stonebrick, 0);
						setBlockAndNotifyAdequately(world, i + 4, j + y + 1, k + offsetZ - (offsetZ / 3), Blocks.stonebrick, 0);
						setBlockAndNotifyAdequately(world, i - 4, j + y + 1, k + offsetZ - (offsetZ / 3), Blocks.stonebrick, 0);
					}
				}
			}
		}
	}
	
	private static final byte SIDE_POS_X = 0;
	private static final byte SIDE_NEG_X = 1;
	private static final byte SIDE_POS_Z = 2;
	private static final byte SIDE_NEG_Z = 3;
}
