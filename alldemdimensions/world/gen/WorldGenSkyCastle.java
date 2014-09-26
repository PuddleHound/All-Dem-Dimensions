package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;

public class WorldGenSkyCastle extends WorldGenerator
{

    public WorldGenSkyCastle()
    {
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        //if(j > 20 && j < 100)
		//{
			addIsland(world, random, i, j, k, false);
			addIsland(world, random, i, j - 20, k, true);
			/*addRoom(world, random, i + 11, j, k + 11, 4, 20, 4, Blocks.stonebrick, true);
			addTeeth(world, random, i + 11, j + 20, k + 11, Blocks.stonebrick);
			addRoom(world, random, i + 11, j, k - 11, 4, 20, 4, Blocks.stonebrick, true);
			addTeeth(world, random, i + 11, j + 20, k - 11, Blocks.stonebrick);
			addRoom(world, random, i - 11, j, k + 11, 4, 20, 4, Blocks.stonebrick, true);
			addTeeth(world, random, i - 11, j + 20, k + 11, Blocks.stonebrick);
			addRoom(world, random, i - 11, j, k - 11, 4, 20, 4, Blocks.stonebrick, true);
			addTeeth(world, random, i - 11, j + 20, k - 11, Blocks.stonebrick);*/
			addRoom(world, random, i, j, k + 11, 14, 14, 3, Blocks.stonebrick, true);
			addRoom(world, random, i + 11, j, k, 3, 14, 14, Blocks.stonebrick, true);
			addRoom(world, random, i, j, k - 11, 14, 14, 3, Blocks.stonebrick, true);
			addRoom(world, random, i - 11, j, k, 3, 14, 14, Blocks.stonebrick, true);
			addRoom(world, random, i + 11, j, k + 11, 4, 20, 4, Blocks.stonebrick, true);
			addTeeth(world, random, i + 11, j + 20, k + 11, Blocks.stonebrick);
			addRoom(world, random, i + 11, j, k - 11, 4, 20, 4, Blocks.stonebrick, true);
			addTeeth(world, random, i + 11, j + 20, k - 11, Blocks.stonebrick);
			addRoom(world, random, i - 11, j, k + 11, 4, 20, 4, Blocks.stonebrick, true);
			addTeeth(world, random, i - 11, j + 20, k + 11, Blocks.stonebrick);
			addRoom(world, random, i - 11, j, k - 11, 4, 20, 4, Blocks.stonebrick, true);
			addTeeth(world, random, i - 11, j + 20, k - 11, Blocks.stonebrick);
			while(random.nextInt(20) != 0)
			{
				deteriorate(world, random, i, j, k);
			}
			for(int l = 0; l < random.nextInt(8) + 2; l++)
			{
				int i1 = random.nextInt(15) - random.nextInt(30);
				int j1 = random.nextInt(15) - random.nextInt(30);
				if(world.getBlock(i + i1, j - 1, k + j1) == Blocks.stonebrick && world.isAirBlock(i + i1, j, k + j1))
				{
					func_150515_a(world, i + i1, j, k + j1, Blocks.chest);
					TileEntityChest tileentitychest = (TileEntityChest)world.getTileEntity(i + i1, j, k + j1);
					int k1 = 0;
					while(k1 < 5)
					{
						ItemStack itemstack = getRandomLoot(random);
						if(itemstack != null && tileentitychest != null)
						{
							tileentitychest.setInventorySlotContents(random.nextInt(tileentitychest.getSizeInventory()), itemstack);
						}
						k1++;
					}
				}
			}
			//addBox(world, random, i, j - 1, k, 3, 1, 3, Blocks.waterStill);
		//}
        return true;
    }
	
	private void addBox(World world, Random random, int i, int j, int k, int l, int l1, int l2, int l3, Block blockID, boolean flag)
	{
		for(int i1 = -l; i1 < l; i1++)
		{
			for(int j1 = l3; j1 < l1; j1++)
			{
				for(int k1 = -l2; k1 < l2; k1++)
				{
					if(flag)
					{
						setBlockAndNotifyAdequately(world, i + i1, j + j1, k + k1, blockID, getRandomBlock(random));
					} else
					{
						func_150515_a(world, i + i1, j + j1, k + k1, blockID);
					}
				}
			}
		}
	}
	
	private void addRoom(World world, Random random, int i, int j, int k, int l, int l1, int l2, Block blockID, boolean flag)
	{
		addBox(world, random, i, j, k, l, l1, l2, -1, blockID, flag);
		addBox(world, random, i, j, k, l - 1, l1 - 1, l2 - 1, 0, Blocks.air, false);
	}
	
	private void addIsland(World world, Random random, int i, int j, int k, boolean flag)
	{
		for(int width = -20; width < 20; width++)
		{
			for(int height = -20; height < 20; height++)
			{
				for(int depth = -20; depth < 20; depth++)
				{
					int i1 = (int)Math.round(Math.sqrt(Math.pow(width,2) + Math.pow(height,2) + Math.pow(depth,2)));
					if(i1 <= 20)
					{
						if(flag)
						{
							if(world.isAirBlock(i + width, j + height, k + depth) && random.nextInt(3) == 0)
							{
								func_150515_a(world, i + width, j + height, k + depth, AllDemDimensions.limestone);
							}
							return;
						}
						if(height < 0 && height >= -1)
						{
							func_150515_a(world, i + width, j + height, k + depth, Blocks.grass);
						} else
						if(height < -1 && height >= -6)
						{
							func_150515_a(world, i + width, j + height, k + depth, Blocks.dirt);
						} else
						if(height < -6)
						{
							func_150515_a(world, i + width, j + height, k + depth, AllDemDimensions.limestone);
						} else
						{
							func_150515_a(world, i + width, j + height, k + depth, Blocks.air);
						}
					}
				}
			}
		}
	}
	
	private void addTeeth(World world, Random random, int i, int j, int k, Block blockID)
	{
		setBlockAndNotifyAdequately(world, i + 3, j, k + 3, blockID, getRandomBlock(random));
		setBlockAndNotifyAdequately(world, i + 3, j, k - 4, blockID, getRandomBlock(random));
		setBlockAndNotifyAdequately(world, i - 4, j, k + 3, blockID, getRandomBlock(random));
		setBlockAndNotifyAdequately(world, i - 4, j, k - 4, blockID, getRandomBlock(random));
		
		setBlockAndNotifyAdequately(world, i + 1, j, k + 3, blockID, getRandomBlock(random));
		setBlockAndNotifyAdequately(world, i - 2, j, k + 3, blockID, getRandomBlock(random));
		setBlockAndNotifyAdequately(world, i + 1, j, k - 4, blockID, getRandomBlock(random));
		setBlockAndNotifyAdequately(world, i - 2, j, k - 4, blockID, getRandomBlock(random));
		
		setBlockAndNotifyAdequately(world, i + 3, j, k + 1, blockID, getRandomBlock(random));
		setBlockAndNotifyAdequately(world, i - 4, j, k + 1, blockID, getRandomBlock(random));
		setBlockAndNotifyAdequately(world, i + 3, j, k - 2, blockID, getRandomBlock(random));
		setBlockAndNotifyAdequately(world, i - 4, j, k - 2, blockID, getRandomBlock(random));
	}
	
	private int getRandomBlock(Random random)
	{
		int i = random.nextInt(4);
		if(i == 0)
		{
			return 1;
		} else
		if(i == 1)
		{
			return 2;
		} else
		{
			return 0;
		}
	}
	
	private void deteriorate(World world, Random random, int i, int j, int k)
	{
		int l = random.nextInt(3) + 1;
		int j1 = random.nextInt(20) - random.nextInt(40);
		int k1 = random.nextInt(20);
		int l1 = random.nextInt(20) - random.nextInt(40);
		for(int width = -l; width < l; width++)
		{
			for(int height = -l; height < l; height++)
			{
				for(int depth = -l; depth < l; depth++)
				{
					int i1 = (int)Math.round(Math.sqrt(Math.pow(width,2) + Math.pow(height,2) + Math.pow(depth,2)));
					if(i1 <= 4)
					{
						if(world.getBlock(i + j1 + width, j + k1 + height, k + l1 + depth) == Blocks.stonebrick && random.nextInt(5) != 0)
						{
							func_150515_a(world, i + j1 + width, j + k1 + height, k + l1 + depth, Blocks.air);
						}
					}
				}
			}
		}
	}
	
	private ItemStack getRandomLoot(Random random)
	{
		int i = random.nextInt(8);
		if(i == 0)
		{
			return new ItemStack(AllDemDimensions.silver, random.nextInt(2) + 1);
		}
		if(i == 1)
		{
			return new ItemStack(AllDemDimensions.emerald, random.nextInt(2) + 1);
		}
		if(i == 2)
		{
			return new ItemStack(AllDemDimensions.phosphorus, random.nextInt(5) + 1);
		}
		if(i == 3)
		{
			return new ItemStack(AllDemDimensions.sugarplum, random.nextInt(3) + 1);
		}
		if(i == 4)
		{
			return new ItemStack(Items.bucket);
		}
		if(i == 5)
		{
			return new ItemStack(AllDemDimensions.silverSword);
		}
		if(i == 6)
		{
			return new ItemStack(AllDemDimensions.emeraldPickaxe);
		} else
		{
			return new ItemStack(AllDemDimensions.mimicstone, random.nextInt(7) + 3);
		}
	}
}
