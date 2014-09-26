package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;

public class WorldGenSkyRuins extends WorldGenerator
{

    public WorldGenSkyRuins()
    {
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		if(world.getBlock(i, j, k) != Blocks.grass)
		{
			return true;
		}
		System.out.println("Ruins generating at " + i + ", " + j + ", " + k + ".");
		int floorX;
		int floorZ;
		boolean roomGenerated = false;
		for(int count = 0; count < random.nextInt(6) + 2; count++)
		{
			floorX = random.nextInt(4) + 4;
			floorZ = random.nextInt(4) + 4;
			if(addRoom(world, random, i + random.nextInt(16) - random.nextInt(16), j - 1 + random.nextInt(2) - random.nextInt(2), k + random.nextInt(16) - random.nextInt(16), floorX, floorZ, false))
			{
				roomGenerated = true;
			}
		}
		//if(random.nextInt(2) == 0)//tower
		{
			floorX = random.nextInt(3) + 2;
			floorZ = random.nextInt(3) + 2;
			if(addRoom(world, random, i + random.nextInt(16) - random.nextInt(16), j - 1 + random.nextInt(2) - random.nextInt(2), k + random.nextInt(16) - random.nextInt(16), floorX, floorZ, true))
			{
				roomGenerated = true;
			}
		}
		if(!roomGenerated)
		{
			return true;
		}
		for(int count1 = 0; count1 < random.nextInt(4); count1++)
		{
			int roadX = i + random.nextInt(16) - random.nextInt(16);
			int roadY = j + random.nextInt(2) - random.nextInt(2);
			int roadZ = k + random.nextInt(16) - random.nextInt(16);
			int dirX = random.nextInt(3) - 1;
			int dirZ = random.nextInt(3) - 1;
			if(dirX != 0 && dirZ != 0)
			{
				if(random.nextInt(2) == 0)
				{
					dirX = 0;
				} else
				{
					dirZ = 0;
				}
			}
			int counter = 0;
			int yOffset = 0;
			while(counter < random.nextInt(32) + 32)
			{
				if(world.getBlock(i + (dirX * counter), j + yOffset, k + (dirZ * counter)).getMaterial() != Material.grass)
				{
					yOffset++;
				}
				if(world.getBlock(i + (dirX * counter), j + yOffset, k + (dirZ * counter)).getMaterial() != Material.grass)
				{
					yOffset -= 2;
				}
				if(world.getBlock(i + (dirX * counter), j + yOffset, k + (dirZ * counter)).getMaterial() != Material.grass)
				{
					break;
				}
				setBlockAndNotifyAdequately(world, i + (dirX * counter), j + yOffset, k + (dirZ * counter), AllDemDimensions.limestone, random.nextInt(3) + AllDemDimensions.limestone.LIMESTONE_BRICK);
				if(dirX == 0)
				{
					setBlockAndNotifyAdequately(world, i + (dirX * counter) + 1, j + yOffset, k + (dirZ * counter), AllDemDimensions.limestone, random.nextInt(3) + AllDemDimensions.limestone.LIMESTONE_BRICK);
					setBlockAndNotifyAdequately(world, i + (dirX * counter) - 1, j + yOffset, k + (dirZ * counter), AllDemDimensions.limestone, random.nextInt(3) + AllDemDimensions.limestone.LIMESTONE_BRICK);
				}
				if(dirZ == 0)
				{
					setBlockAndNotifyAdequately(world, i + (dirX * counter), j + yOffset, k + (dirZ * counter) + 1, AllDemDimensions.limestone, random.nextInt(3) + AllDemDimensions.limestone.LIMESTONE_BRICK);
					setBlockAndNotifyAdequately(world, i + (dirX * counter), j + yOffset, k + (dirZ * counter) - 1, AllDemDimensions.limestone, random.nextInt(3) + AllDemDimensions.limestone.LIMESTONE_BRICK);
				}
				counter++;
			}
		}
		return true;
    }
	
	private boolean addRoom(World world, Random random, int i, int j, int k, int floorX, int floorZ, boolean tower)
	{
		Material material = world.getBlock(i + floorX, j, k + floorZ).getMaterial();
		Material material1 = world.getBlock(i - floorX, j, k - floorZ).getMaterial();
		if((material == Material.grass || material == Material.ground) && 
			(material1 == Material.grass || material1 == Material.ground))
		{
			int height;
			if(random.nextInt(3) == 0)//basement
			{
				height = random.nextInt(4) + 4;
				addFloor(world, random, i, j - height - 1, k, floorX, floorZ);
				addWall(world, random, i + floorX, j - height, k, 0, floorZ, height);
				addWall(world, random, i - floorX, j - height, k, 0, floorZ, height);
				addWall(world, random, i, j - height, k + floorZ, floorX, 0, height);
				addWall(world, random, i, j - height, k - floorZ, floorX, 0, height);
				addChest(world, random, i + random.nextInt(floorX - 1) - random.nextInt(floorX - 1), j - height, k + random.nextInt(floorZ - 1) - random.nextInt(floorZ - 1));
				//addChest
			}
			if(tower)
			{
				height = random.nextInt(8) + 6;
			} else
			{
				height = 0;
			}
			addFloor(world, random, i, j, k, floorX, floorZ);
			addWall(world, random, i + floorX, j + 1, k, 0, floorZ, height);
			addWall(world, random, i - floorX, j + 1, k, 0, floorZ, height);
			addWall(world, random, i, j + 1, k + floorZ, floorX, 0, height);
			addWall(world, random, i, j + 1, k - floorZ, floorX, 0, height);
			if(tower)
			{
				addFloor(world, random, i, j + height, k, floorX + 1, floorZ + 1);
				addWall(world, random, i + floorX + 1, j + height + 1, k, 0, floorZ + 1, 0);
				addWall(world, random, i - floorX - 1, j + height + 1, k, 0, floorZ + 1, 0);
				addWall(world, random, i, j + height + 1, k + floorZ + 1, floorX + 1, 0, 0);
				addWall(world, random, i, j + height + 1, k - floorZ - 1, floorX + 1, 0, 0);
			}
			return true;
		}
		return false;
	}
	
	private void addFloor(World world, Random random, int i, int j, int k, int sizeX, int sizeZ)
	{
		Block blockID = AllDemDimensions.limestone;
		if(random.nextInt(8) == 0)
		{
			blockID = Blocks.mossy_cobblestone;
		}
		byte metadata = AllDemDimensions.limestone.LIMESTONE_BRICK;
		int counter;
		for(int x = i - sizeX; x < i + sizeX; x++)
		{
			for(int y = j; y < j + 1; y++)
			{
				for(int z = k - sizeZ; z < k + sizeZ; z++)
				{
					metadata = AllDemDimensions.limestone.LIMESTONE_BRICK;
					if(random.nextInt(4) == 0)
					{
						metadata = AllDemDimensions.limestone.LIMESTONE_BRICK_IVIED;
					} else
					if(random.nextInt(4) == 0)
					{
						metadata = AllDemDimensions.limestone.LIMESTONE_BRICK_CRACKED;
					}
					setBlockAndNotifyAdequately(world, x, y, z, blockID, metadata);
					counter = 1;
					while(world.getBlock(x, y + counter, z) != Blocks.air)
					{
						func_150515_a(world, x, y + counter, z, Blocks.air);
						counter++;
					}
				}
			}
		}
	}
	
	private void addWall(World world, Random random, int i, int j, int k, int sizeX, int sizeZ, int height)
	{
		Block blockID = AllDemDimensions.limestone;
		byte metadata = AllDemDimensions.limestone.LIMESTONE_BRICK;
		int maxX = sizeX;
		int maxZ = sizeZ;
		if(sizeX == 0)
		{
			maxX = 1;
		}
		if(sizeZ == 0)
		{
			maxZ = 1;
		}
		int sizeY;
		for(int x = i - sizeX; x < i + maxX; x++)
		{
			for(int z = k - sizeZ; z < k + maxZ; z++)
			{
				if(height > 0)
				{
					sizeY = height;
				} else
				{
					sizeY = random.nextInt(4);
				}
				for(int y = j; y < j + sizeY; y++)
				{
					metadata = AllDemDimensions.limestone.LIMESTONE_BRICK;
					if(random.nextInt(4) == 0)
					{
						metadata = AllDemDimensions.limestone.LIMESTONE_BRICK_IVIED;
					} else
					if(random.nextInt(4) == 0)
					{
						metadata = AllDemDimensions.limestone.LIMESTONE_BRICK_CRACKED;
					}
					if(height > 0 && random.nextInt(10) == 0)
					{
						func_150515_a(world, x, y, z, Blocks.air);
					} else
					{
						setBlockAndNotifyAdequately(world, x, y, z, blockID, metadata);
					}
				}
			}
		}
	
	}
	
	private void addChest(World world, Random random, int i, int j, int k)
	{
		func_150515_a(world, i, j, k, Blocks.chest);
        TileEntityChest tileentity = (TileEntityChest)world.getTileEntity(i, j, k);
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
			return new ItemStack(AllDemDimensions.amber, random.nextInt(4) + 1, 0);
		}
		if(i == 1 && random.nextInt(10) == 0)
		{
			return new ItemStack(AllDemDimensions.royalJelly, random.nextInt(2) + 1, 0);
		}
		if(i == 2)
		{
			return new ItemStack(AllDemDimensions.cotton, random.nextInt(8) + 2, 0);
		}
		if(i == 3)
		{
			return new ItemStack(AllDemDimensions.phosphorus, random.nextInt(4) + 1, 0);
		}
		if(i == 4)
		{
			return new ItemStack(AllDemDimensions.silverAxe, 1, 0);
		}
		if(i == 5)
		{
			return new ItemStack(AllDemDimensions.match, random.nextInt(8) + 4, 0);
		}
		if(i == 6)
		{
			return new ItemStack(AllDemDimensions.floatingRedstone, random.nextInt(4) + 2, 0);
		}
		if(i == 7)
		{
			return new ItemStack(AllDemDimensions.canvas, random.nextInt(2) + 1, 0);
		}
		return new ItemStack(AllDemDimensions.wax, random.nextInt(6) + 2, 0);
	}
	
}
