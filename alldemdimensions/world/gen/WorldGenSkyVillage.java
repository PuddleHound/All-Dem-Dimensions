package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.entity.EntitySkyknight;
import alldemdimensions.entity.SkyknightProfession;

public class WorldGenSkyVillage extends WorldGenerator
{

    public WorldGenSkyVillage()
    {
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		int sizeX = random.nextInt(2) + 3;
		//int sizeY = random.nextInt(4) + 6;
		int sizeZ = random.nextInt(2) + 3;
		int sizeY = sizeX + sizeZ;
		if(world.getBlock(i + sizeX, j, k + sizeZ).getMaterial() != Material.grass ||
			world.getBlock(i - sizeX, j, k - sizeZ).getMaterial() != Material.grass)
		{
			return false;
		}
		boolean concaveCorners = random.nextBoolean();
		boolean fullRoof = random.nextBoolean();
		boolean roofCrosses = random.nextBoolean();
		boolean skylightX = random.nextBoolean();
		boolean skylightZ = random.nextBoolean();
		boolean secondaryWallMaterial = random.nextBoolean();
		boolean mainEntranceSet = false;
		boolean dentilRoof = random.nextBoolean();
		boolean windowOutline = random.nextBoolean();
		boolean tower = random.nextInt(10) == 0;
		int[] materials = new int[5];
		for(byte b = 0; b < materials.length; b++)
		{
			materials[b] = getRandomMaterial(random);
		}
		if(tower)
		{
			sizeY = random.nextInt(8) + 12;
			dentilRoof = true;
			fullRoof = false;
		}
		if(concaveCorners)
		{
			windowOutline = false;
		}
		Block replacedId;
		boolean wideWindow;
		for(int x = -sizeX; x < sizeX; x++)
		{
			for(int y = 0; y < sizeY; y++)
			{
				for(int z = -sizeZ; z < sizeZ; z++)
				{
					/*if(y >= sizeY - sizeX && sizeX < sizeZ)
					{
						if(x == -sizeX + (y - sizeX) || x == sizeX - 1 - (y - sizeX))
						{
							setBlockAndNotifyAdequately(world, i + x, j + y, k + z, AllDemDimensions.limestone, materials[ROOF]);
						} else
						if((x > -sizeX + (y - sizeX) && x < sizeX - 1 - (y - sizeX)) && (z == -sizeZ || z == sizeZ - 1))
						{
							setBlockAndNotifyAdequately(world, i + x, j + y, k + z, AllDemDimensions.limestone, materials[WALLS]);
						}
					} else
					if(y >= sizeY - sizeZ)
					{
						if(z == -sizeZ + (y - sizeZ) || z == sizeZ - 1 - (y - sizeZ))
						{
							setBlockAndNotifyAdequately(world, i + x, j + y, k + z, AllDemDimensions.limestone, materials[ROOF]);
						} else
						if((z > -sizeZ + (y - sizeZ) && z < sizeZ - 1 - (y - sizeZ)) && (x == -sizeX || x == sizeX - 1))
						{
							setBlockAndNotifyAdequately(world, i + x, j + y, k + z, AllDemDimensions.limestone, materials[WALLS]);
						}
					} else*/
					replacedId = world.getBlock(i + x, j + y, k + z);
					if(y == sizeY - 1 && !fullRoof && !dentilRoof)
					{
						if(x >= -sizeX + 2 && x <= sizeX - 3 && z >= -sizeZ + 2 && z <= sizeZ - 3)
						{
							if((skylightX && (x == -1 || x == 0)) || (skylightZ && (z == -1 || z == 0)))
							{
								func_150515_a(world, i + x, j + y, k + z, Blocks.glass);
							} else
							{
								setBlockAndNotifyAdequately(world, i + x, j + y, k + z, AllDemDimensions.limestone, materials[ROOF]);
							}
						}
					} else
					if(y == sizeY - 2 && !fullRoof && !dentilRoof)
					{
						if(x == -sizeX + 1 || x == sizeX - 2 || z == -sizeZ + 1 || z == sizeZ - 2)
						{
							if(roofCrosses || (x > -sizeX && x < sizeX - 1 && z > -sizeZ && z < sizeZ - 1))
							{
								setBlockAndNotifyAdequately(world, i + x, j + y, k + z, AllDemDimensions.limestone, materials[ROOF]);
							}
						}
					} else
					if(x == -sizeX || x == sizeX - 1 || z == -sizeZ || z == sizeZ - 1)
					{
						if(concaveCorners && ((x == -sizeX && z == -sizeZ) || (x == sizeX - 1 && z == -sizeZ) ||
							(x == sizeX - 1 && z == sizeZ - 1) || (x == -sizeX && z == sizeZ - 1)))
						{
						} else
						{
							if(y == 0)
							{
								setBlockAndNotifyAdequately(world, i + x, j + y, k + z, AllDemDimensions.limestone, materials[FLOOR]);
							} else
							{
								/*if(!mainEntranceSet && replacedId != Blocks.glass && random.nextInt(6) == 0 && y == 1 && ((x > -sizeX / 2 && x < sizeX / 2) || (z > -sizeZ / 2 && z < sizeZ / 2)))
								{
									ItemDoor.placeDoorBlock(world, i + x, j + y, k + z, 0, Blocks.doorWood);
									//func_150515_a(world, i + x, j + y, k + z, Blocks.doorWood);
									//func_150515_a(world, i + x, j + y + 1, k + z, Blocks.doorWood);
									if(x == -sizeX || x == sizeX - 1)
									{
										ItemDoor.placeDoorBlock(world, i + x, j + y, k + z - 1, 0, Blocks.doorWood);
										//func_150515_a(world, i + x - 1, j + y, k + z, Blocks.doorWood);
										//func_150515_a(world, i + x - 1, j + y + 1, k + z, Blocks.doorWood);
									} else
									{
										ItemDoor.placeDoorBlock(world, i + x - 1, j + y, k + z, 0, Blocks.doorWood);
										//func_150515_a(world, i + x, j + y, k + z - 1, Blocks.doorWood);
										//func_150515_a(world, i + x, j + y + 1, k + z - 1, Blocks.doorWood);
									}
									System.out.println("Generated door");
									mainEntranceSet = true;
								} else
								if(random.nextInt(6) == 0 && replacedId != Blocks.doorWood && (y >= 2 && y <= sizeY / 3) && ((x >= -sizeX / 2 && x <= sizeX / 2) || (z >= -sizeZ / 2 && z <= sizeZ / 2)))
								{
									{
										wideWindow = random.nextInt(3) == 0;
										func_150515_a(world, i + x, j + y, k + z, Blocks.glass);
										if(random.nextBoolean())
										{
											func_150515_a(world, i + x, j + y + 1, k + z, Blocks.glass);
										}
										System.out.println("Generated glass");
									}
								} else*/
								if(replacedId != Blocks.wooden_door && replacedId != Blocks.glass)
								{
									if(secondaryWallMaterial && (y > 1 && y < sizeY - 1) && ((x > -sizeX + 1 &&
										x < sizeX - 2) || (z > -sizeZ + 1 && z < sizeZ - 2)))
									{
										if(!fullRoof && !dentilRoof && y > sizeY - 3)
										{
										} else
										{
											setBlockAndNotifyAdequately(world, i + x, j + y, k + z, AllDemDimensions.limestone, materials[WALLS_2]);
										}
									} else
									{
										setBlockAndNotifyAdequately(world, i + x, j + y, k + z, AllDemDimensions.limestone, materials[WALLS]);
									}
								}
							}
						}
					} else
					if(y == 0)
					{
						setBlockAndNotifyAdequately(world, i + x, j + y, k + z, AllDemDimensions.limestone, materials[FLOOR]);
					}
					else
					{
						func_150515_a(world, i + x, j + y, k + z, Blocks.air);
					}
				}
			}
		}
		if(fullRoof)
		{
			int height = sizeY;
			int x1 = sizeX - 1;
			int z1 = sizeZ - 1;
			while(x1 > 0 && z1 > 0)
			{
				for(int i1 = -x1; i1 < x1; i1++)
				{
					for(int j1 = -z1; j1 < z1; j1++)
					{
						setBlockAndNotifyAdequately(world, i + i1, j + height, k + j1, AllDemDimensions.limestone, materials[ROOF]);
					}
				}
				x1--;
				z1--;
				height++;
			}
		}
		if(dentilRoof)
		{
			int l1 = 0;
			if(random.nextBoolean())
			{
				l1 = 1;
			}
			for(int i2 = -sizeX - l1; i2 < sizeX + l1; i2++)
			{
				for(int j2 = -sizeZ - l1; j2 < sizeZ + l1; j2++)
				{
					setBlockAndNotifyAdequately(world, i + i2, j + sizeY, k + j2, AllDemDimensions.limestone, materials[ROOF]);
				}
			}
			int dentilSizeX = sizeX / 3;
			int dentilSizeZ = sizeZ / 3;
			for(int i3 = -sizeX - l1; i3 < 0; i3 += dentilSizeX + 1)
			{
				for(int i4 = 0; i4 < dentilSizeX; i4++)
				{
					setBlockAndNotifyAdequately(world, i + i3 + i4, j + sizeY + 1, k + sizeZ + l1 - 1, AllDemDimensions.limestone, materials[ROOF]);
					setBlockAndNotifyAdequately(world, i + i3 + i4, j + sizeY + 1, k - sizeZ - l1, AllDemDimensions.limestone, materials[ROOF]);
				}
			}
			for(int i3 = sizeX + l1; i3 >= 0; i3 -= dentilSizeX + 1)
			{
				for(int i4 = dentilSizeX; i4 > 0; i4--)
				{
					setBlockAndNotifyAdequately(world, i + i3 - i4, j + sizeY + 1, k + sizeZ + l1 - 1, AllDemDimensions.limestone, materials[ROOF]);
					setBlockAndNotifyAdequately(world, i + i3 - i4, j + sizeY + 1, k - sizeZ - l1, AllDemDimensions.limestone, materials[ROOF]);
				}
			}
			for(int i3 = -sizeZ - l1; i3 < 0; i3 += dentilSizeZ + 1)
			{
				for(int i4 = 0; i4 < dentilSizeZ; i4++)
				{
					setBlockAndNotifyAdequately(world, i + sizeX + l1 - 1, j + sizeY + 1, k + i3 + i4, AllDemDimensions.limestone, materials[ROOF]);
					setBlockAndNotifyAdequately(world, i - sizeX - l1, j + sizeY + 1, k + i3 + i4, AllDemDimensions.limestone, materials[ROOF]);
				}
			}
			for(int i3 = sizeZ + l1; i3 >= 0; i3 -= dentilSizeZ + 1)
			{
				for(int i4 = dentilSizeZ; i4 > 0; i4--)
				{
					setBlockAndNotifyAdequately(world, i + sizeX + l1 - 1, j + sizeY + 1, k + i3 - i4, AllDemDimensions.limestone, materials[ROOF]);
					setBlockAndNotifyAdequately(world, i - sizeX - l1, j + sizeY + 1, k + i3 - i4, AllDemDimensions.limestone, materials[ROOF]);
				}
			}
		}
		
		//for(int x2 = -sizeX; x2 < sizeX; x2 += (sizeX * 2) - 1)
		int counter = 0;
		int l;
		int offsetX;
		int offsetZ;
		int offsetX1;
		int offsetZ1;
		int dirX;
		int dirZ;
		boolean two;
		while(counter < 4)
		{
			offsetX = 0;
			offsetZ = 0;
			offsetX1 = 0;
			offsetZ1 = 0;
			dirX = 0;
			dirZ = 0;
			two = random.nextBoolean();
			if(counter == 0)
			{
				offsetZ = sizeZ - 1;
				offsetZ1 = offsetZ;
				dirX = 1;
				if(two)
				{
					offsetX = (-sizeX / 2);
					offsetX1 = (sizeX/* - 1*/) / 2;
				}
			} else
			if(counter == 1)
			{
				offsetZ = -sizeZ;
				offsetZ1 = offsetZ;
				dirX = 1;
				if(two)
				{
					offsetX = (-sizeX / 2);
					offsetX1 = (sizeX/* - 1*/) / 2;
				}
			} else
			if(counter == 2)
			{
				offsetX = sizeX - 1;
				offsetX1 = offsetX;
				dirZ = 1;
				if(two)
				{
					offsetZ = (-sizeZ / 2);
					offsetZ1 = (sizeZ/* - 1*/) / 2;
				}
			} else
			{
				offsetX = -sizeX;
				offsetX1 = offsetX;
				dirZ = 1;
				if(two)
				{
					offsetZ = (-sizeZ / 2);
					offsetZ1 = (sizeZ/* - 1*/) / 2;
				}
			}
			boolean wide = random.nextBoolean();
			addWindow(world, random, i + offsetX, j + 2, k + offsetZ, sizeY / 4, wide, dirX, dirZ, windowOutline, materials);
			if(two)
			{
				addWindow(world, random, i + offsetX1, j + 2, k + offsetZ1, sizeY / 4, wide, dirX, dirZ, windowOutline, materials);
			}
			/*
			l = random.nextInt(3);
			if(l == 0)
			{
				addWindow(world, random, i + offsetX, j + 2, k + offsetZ, sizeY / 5, random.nextBoolean(), dirX, dirZ);
			} else
			if(l == 1)
			{
				addWindow(world, random, i - ((sizeX / 3) * 2), j + 2, k + sizeZ - 1, sizeY / 5, random.nextBoolean(), dirX, dirZ);
				addWindow(world, random, i + (sizeX / 3), j + 2, k + sizeZ - 1, sizeY / 5, random.nextBoolean(), dirX, dirZ);
			}*/
			counter++;
		}
		
		boolean doorAdded = false;
		int doorX;
		int doorZ;
		int tries = 0;
		int doorMeta = 0;
		while(!doorAdded)
		{
			doorX = random.nextInt(sizeX * 2) - sizeX;
			doorZ = random.nextInt(sizeZ * 2) - sizeZ;
			if((doorX > -sizeX / 2 && doorX < sizeX / 2 && (doorZ == -sizeZ || doorZ == sizeZ - 1)) ||
				doorZ > -sizeZ / 2 && doorZ < sizeZ / 2 && (doorX == -sizeX || doorX == sizeX - 1))
			{
				func_150515_a(world, i + doorX, j + 1, k + doorZ, Blocks.air);
				func_150515_a(world, i + doorX, j + 2, k + doorZ, Blocks.air);
				if(doorX == -sizeX)
				{
					doorMeta = 0;
				} else
				if(doorX == sizeX - 1)
				{
					doorMeta = 2;
				} else
				if(doorZ == -sizeZ)
				{
					doorMeta = 1;
				} else
				if(doorZ == sizeZ - 1)
				{
					doorMeta = 3;
				}
				ItemDoor.placeDoorBlock(world, i + doorX, j + 1, k + doorZ, doorMeta, Blocks.wooden_door);
				doorAdded = true;
			}
			if(tries > 64)
			{
				break;
			}
			tries++;
		}
		
		/*****/
		EntitySkyknight skyknight = new EntitySkyknight(world);
		skyknight.setLocationAndAngles(i, j + 2, k, random.nextFloat() * 360F, 0F);
		skyknight.setProfession(SkyknightProfession.allProfessions[(byte)random.nextInt(6)]);
		world.spawnEntityInWorld(skyknight);
		/*****/
		
		return true;
	}
	
	private void addWindow(World world, Random random, int i, int j, int k, int height, boolean wide, int dirX, int dirZ, boolean outline, int[] materials)
	{
		for(int y = 0; y < height; y++)
		{
			func_150515_a(world, i, j + y, k, Blocks.glass);
			if(wide)
			{
				func_150515_a(world, i - dirX, j + y, k - dirZ, Blocks.glass);
				if(outline)
				{
					if(world.getBlock(i - (dirX * 2), j + y, k - (dirZ * 2)) != Blocks.glass)
					{
						setBlockAndNotifyAdequately(world, i - (dirX * 2), j + y, k - (dirZ * 2), AllDemDimensions.limestone, materials[WINDOW_OUTLINE]);
					}
					if(world.getBlock(i + dirX, j + y, k + dirZ) != Blocks.glass)
					{
						setBlockAndNotifyAdequately(world, i + dirX, j + y, k + dirZ, AllDemDimensions.limestone, materials[WINDOW_OUTLINE]);
					}
				}
			} else
			if(outline)
			{
				if(world.getBlock(i - dirX, j + y, k - dirZ) != Blocks.glass)
				{
					setBlockAndNotifyAdequately(world, i - dirX, j + y, k - dirZ, AllDemDimensions.limestone, materials[WINDOW_OUTLINE]);
				}
				if(world.getBlock(i + dirX, j + y, k + dirZ) != Blocks.glass)
				{
					setBlockAndNotifyAdequately(world, i + dirX, j + y, k + dirZ, AllDemDimensions.limestone, materials[WINDOW_OUTLINE]);
				}
			}
		}
	}
	
	private int getRandomMaterial(Random random)
	{
		int i = random.nextInt(12);
		if(i == 0 || i == 11)
		{
			return AllDemDimensions.limestone.MARBLE;
		}
		if(i == 1 || i == 2 || i == 3)
		{
			return AllDemDimensions.limestone.MARBLE_BRICK;
		}
		if(i == 4)
		{
			return AllDemDimensions.limestone.LIMESTONE;
		}
		if(i == 5 || i == 6 || i == 7)
		{
			return AllDemDimensions.limestone.LIMESTONE_BRICK;
		}
		if(i == 8)
		{
			return AllDemDimensions.limestone.MARBLE_CHISELED;
		}
		if(i == 9 || i == 10)
		{
			return AllDemDimensions.limestone.LIMESTONE_CHISELED;
		}
		return AllDemDimensions.limestone.MARBLE;
	}
	
	private static final byte WALLS = 0;
	private static final byte FLOOR = 1;
	private static final byte ROOF = 2;
	private static final byte WALLS_2 = 3;
	private static final byte WINDOW_OUTLINE = 4;
}
