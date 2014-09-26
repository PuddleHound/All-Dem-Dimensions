package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNetherFactory extends WorldGenerator
{

    public WorldGenNetherFactory()
    {
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		//if(world.getBlock(i, j, k) == Blocks.netherrack)
		//{
			int sizeX = 32;
			int sizeZ = 32;
			addRoom(world, random, i, j, k, sizeX / 2, 20, sizeZ / 2, Blocks.nether_brick, false);
			addFurnace(world, random, i, j - 1, k - 8, 0);
			addFurnace(world, random, i + 12, j - 1, k - 8, 0);
			addFurnace(world, random, i - 12, j - 1, k - 8, 0);
			addFurnace(world, random, i, j - 1, k + 8, 1);
			addFurnace(world, random, i + 12, j - 1, k + 8, 1);
			addFurnace(world, random, i - 12, j - 1, k + 8, 1);
			for(int lightCount = 0; lightCount < random.nextInt(8) + 12; lightCount++)
			{
				int i1 = random.nextInt(32) - 16;
				int k1 = random.nextInt(32) - 16;
				if(world.getBlock(i + i1, j + 19, k + k1) == Blocks.nether_brick)
				{
					func_150515_a(world, i + i1, j + 19, k + k1, Blocks.glowstone);
				}
			}
			addSupport(world, random, i, j - 1, k + 13);
			addSupport(world, random, i, j - 1, k - 13);
			addSupport(world, random, i + 13, j - 1, k);
			addSupport(world, random, i - 13, j - 1, k);
			addSupport(world, random, i + 13, j - 1, k + 13);
			addSupport(world, random, i + 13, j - 1, k - 13);
			addSupport(world, random, i - 13, j - 1, k + 13);
			addSupport(world, random, i - 13, j - 1, k - 13);
			int l = -14;
			while(l <= 14)
			{
				addWindow(world, random, i + l, j + 5, k - 16, 4);
				addWindow(world, random, i + l, j + 5, k + 15, 4);
				addWindow(world, random, i - 16, j + 5, k + l, 4);
				addWindow(world, random, i + 15, j + 5, k + l, 4);
				
				addWindow(world, random, i + l, j + 14, k - 16, 2);
				addWindow(world, random, i + l, j + 14, k + 15, 2);
				addWindow(world, random, i - 16, j + 14, k + l, 2);
				addWindow(world, random, i + 15, j + 14, k + l, 2);
				l += 2;
			}
			
			/*
			for(int l = 0; l < random.nextInt(8) + 2; l++)
			{
				int i1 = random.nextInt(15) - random.nextInt(30);
				int j1 = random.nextInt(15) - random.nextInt(30);
				if(world.getBlock(i + i1, j - 1, k + j1) == Blocks.stoneBrick && world.isAirBlock(i + i1, j, k + j1))
				{
					func_150515_a(world, i + i1, j, k + j1, Blocks.chest);
					TileEntityChest tileentitychest = (TileEntityChest)world.getBlockTileEntity(i + i1, j, k + j1);
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
			}*/
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
		addBox(world, random, i, j, k, l - 1, l1 - 1, l2 - 1, 0, Blocks.air, false);//1.7.2
	}
	
	private void addFurnace(World world, Random random, int i, int j, int k, int l)
	{
		addBox(world, random, i, j, k, 4, 8, 4, 0, Blocks.nether_brick, false);
		addBox(world, random, i, j, k, 3, 7, 3, 1, Blocks.air, false);
		addBox(world, random, i, j, k, 3, 10, 3, 8, Blocks.nether_brick, false);
		int i1 = random.nextInt(12);
		addBox(world, random, i, j, k, 2, 24 + i1, 2, 10, Blocks.nether_brick, false);
		addBox(world, random, i, j, k, 1, 25 + i1, 1, 7, Blocks.air, false);
		addBox(world, random, i, j, k, 3, 1, 3, 0, Blocks.netherrack, false);
		addBox(world, random, i, j, k, 3, 2, 3, 1, Blocks.fire, false);
		if(l == 0)
		{
			func_150515_a(world, i, j + 2, k + 3, Blocks.air);
			func_150515_a(world, i - 1, j + 2, k + 3, Blocks.air);
			func_150515_a(world, i, j + 3, k + 3, Blocks.air);
			func_150515_a(world, i - 1, j + 3, k + 3, Blocks.air);
		} else
		{
			func_150515_a(world, i, j + 2, k - 4, Blocks.air);
			func_150515_a(world, i - 1, j + 2, k - 4, Blocks.air);
			func_150515_a(world, i, j + 3, k - 4, Blocks.air);
			func_150515_a(world, i - 1, j + 3, k - 4, Blocks.air);
		}
	}
	
	private void addSupport(World world, Random random, int i, int j, int k)
	{
		int l = 1;
		while(j - l > 0)
		{
			if(!world.getBlock(i, j - l, k).getMaterial().isSolid())
			{
				addBox(world, random, i, j - l, k, 3, 1, 3, 0, Blocks.nether_brick, false);
			} else
			{
				addBox(world, random, i, j - l, k, 3, 1, 3, -5, Blocks.nether_brick, false);
				break;
			}
			l++;
		}
	}
	
	private void addWindow(World world, Random random, int i, int j, int k, int l)
	{
		int i1 = 0;
		while(i1 < l)
		{
			func_150515_a(world, i, j + i1, k, Blocks.nether_brick_fence);
			i1++;
		}
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
	/*
	private ItemStack getRandomLoot(Random random)
	{
		int i = random.nextInt(8);
		if(i == 0)
		{
			return new ItemStack(AllDemDimensions.platinum, random.nextInt(2) + 1);
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
			return new ItemStack(Item.bucketEmpty);
		}
		if(i == 5)
		{
			return new ItemStack(AllDemDimensions.platinumSword);
		}
		if(i == 6)
		{
			return new ItemStack(AllDemDimensions.emeraldPickaxe);
		} else
		{
			return new ItemStack(AllDemDimensions.mimicstone, random.nextInt(7) + 3);
		}
	}*/
}
