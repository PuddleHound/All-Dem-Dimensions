package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.block.TileEntityAmberChest;

public class WorldGenSkyDungeon extends WorldGenerator
{

    public WorldGenSkyDungeon()//for the interiors of mountains
    {
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		int sizeX = random.nextInt(4) + 4;
		int sizeY = random.nextInt(8) + 8;
		int sizeZ = random.nextInt(4) + 4;
		if(world.getBlock(i + sizeX, j + sizeY, k + sizeZ).getMaterial() == Material.rock &&
			world.getBlock(i - sizeX, j, k - sizeZ).getMaterial() == Material.rock)
		{
			addRoom(world, random, i, j, k, sizeX, sizeY, sizeZ);
			if(random.nextInt(2) == 0)
			{
				addTunnel(world, random, i + sizeX - 1, j + 1, k, 1, 0);
			}
			if(random.nextInt(2) == 0)
			{
				addTunnel(world, random, i - sizeX + 1, j + 1, k, -1, 0);
			}
			if(random.nextInt(2) == 0)
			{
				addTunnel(world, random, i, j + 1, k + sizeZ - 1, 0, 1);
			}
			if(random.nextInt(2) == 0)
			{
				addTunnel(world, random, i, j + 1, k - sizeZ + 1, 0, -1);
			}
		}
		return true;
	}
	
	private void addRoom(World world, Random random, int i, int j, int k, int sizeX, int sizeY, int sizeZ)
	{
		for(int x = -sizeX; x < sizeX; x++)
		{
			for(int y = 0; y < sizeY; y++)
			{
				for(int z = -sizeZ; z < sizeZ; z++)
				{
					if(world.getBlock(i + x, j + y, k + z).getMaterial().isSolid())
					{
						if(x == -sizeX || x == sizeX - 1 || y == 0 || y == sizeY - 1 || z == -sizeZ || z == sizeZ - 1)
						{
							setBlockAndNotifyAdequately(world, i + x, j + y, k + z, AllDemDimensions.limestone, AllDemDimensions.limestone.LIMESTONE_CHISELED);
						} else
						if(y == 1 && x >= -2 && x < 2 && z >= -2 && z < 2)
						{
							if((x == -1 || x == 0) && (z == -1 || z == 0))
							{
								func_150515_a(world, i + x, j + y, k + z, Blocks.water);
							} else
							{
								setBlockAndNotifyAdequately(world, i + x, j + y, k + z, AllDemDimensions.limestone, AllDemDimensions.limestone.LIMESTONE_CHISELED);
							}
						} else
						if(random.nextInt(32) == 0 && y == 1 && (x == -sizeX + 1 || x == sizeX - 2 || z == -sizeZ + 1 || z == sizeZ - 2))
						{
							addChest(world, random, i + x, j + y, k + z);
						} else
						{
							func_150515_a(world, i + x, j + y, k + z, Blocks.air);
						}
					}
				}
			}
		}
	}
	
	private void addTunnel(World world, Random random, int i, int j, int k, int dirX, int dirZ)
	{
		int counter = 0;
		int minX = 0;
		int maxX = 1;
		int minZ = 0;
		int maxZ = 1;
		if(dirX == 0)
		{
			minX = -3;
			maxX = 3;
		} else
		{
			minZ = -3;
			maxZ = 3;
		}
		while(!world.isAirBlock(i + (counter * dirX), j, k + (counter * dirZ)))
		{
			for(int x = minX + (counter * dirX); x < maxX + (counter * dirX); x++)
			{
				for(int y = 0; y < 6; y++)
				{
					for(int z = minZ + (counter * dirZ); z < maxZ + (counter * dirZ); z++)
					{
						if(world.getBlock(i + x, j + y, k + z).getMaterial().isSolid())
						{
							if((dirX == 0 && (x == minX || x == maxX - 1)) || y == 0 || 
								y == 5 || (dirZ == 0 && (z == minZ || z == maxZ - 1)))
							{
								setBlockAndNotifyAdequately(world, i + x, j + y, k + z, AllDemDimensions.limestone, AllDemDimensions.limestone.LIMESTONE_CHISELED);
							} else
							{
								func_150515_a(world, i + x, j + y, k + z, Blocks.air);
							}
						}
					}
				}
			}
			if(counter > 32)
			{
				break;
			}
			counter++;
		}//generate new room at end if not successful
	}
	
	private void addChest(World world, Random random, int i, int j, int k)
	{
		func_150515_a(world, i, j, k, AllDemDimensions.amberChest);
        TileEntityChest tileentity = (TileEntityAmberChest)world.getTileEntity(i, j, k);
		if(tileentity != null)
		{
			for(int count = 0; count < random.nextInt(8) + 2; count++)
			{
                ItemStack itemstack = getRandomLoot(random);
				if(itemstack != null)
                {
                    tileentity.setInventorySlotContents(random.nextInt(tileentity.getSizeInventory()), itemstack);
                }						
			}
		}
	}
	
	private ItemStack getRandomLoot(Random random)
	{
		int i = random.nextInt(8);
		if(i == 0)
		{
			return new ItemStack(AllDemDimensions.amber, random.nextInt(5) + 2, 0);
		}
		if(i == 1 && random.nextInt(4) == 0)
		{
			return new ItemStack(AllDemDimensions.royalJelly, random.nextInt(3) + 1, 0);
		}
		if(i == 2)
		{
			return new ItemStack(AllDemDimensions.airSac, random.nextInt(4) + 1, 0);
		}
		if(i == 3)
		{
			return new ItemStack(AllDemDimensions.ghostPowder, random.nextInt(4) + 1, 0);
		}
		if(i == 4)
		{
			return new ItemStack(Items.ender_pearl, 1, 0);
		}
		if(i == 5)
		{
			return new ItemStack(AllDemDimensions.silver, random.nextInt(4) + 1, 0);
		}
		if(i == 6 && random.nextInt(4) == 0)
		{
			return new ItemStack(AllDemDimensions.emerald, random.nextInt(2) + 1, 0);
		}
		if(i == 7)
		{
			return new ItemStack(AllDemDimensions.floatingRedstone, random.nextInt(6) + 1, 0);
		}
		return new ItemStack(AllDemDimensions.wax, random.nextInt(6) + 2, 0);
	}
}
