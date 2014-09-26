package alldemdimensions.world.gen;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.world.Dimension;
import cpw.mods.fml.common.IWorldGenerator;


public class WorldGenOverworldCrystals implements IWorldGenerator
{

    public WorldGenOverworldCrystals()
    {
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world,
		IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
    	if(world.provider.dimensionId != Dimension.overworld.dimensionId && world.getWorldInfo().getTerrainType() != WorldType.FLAT)
		{
			return;
		}
		for(byte b = 0; b < 3; b++)
		{
			int i = (chunkX * 16) + random.nextInt(16);
			int j = random.nextInt(40);
			int k = (chunkZ * 16) + random.nextInt(16);
			int x;
			int y;
			int z;
			boolean flag = false;
			label0:
			for(int l = -16; l < 16; l++)
			{
				for(int i1 = -8; i1 < 8; i1++)
				{
					for(int j1 = -16; j1 < 16; j1++)
					{
						x = i + l;
						y = j + i1;
						z = k + j1;
						if(world.getBlock(x, y - 1, z).getMaterial() == Material.rock && world.getBlock(x, y, z) == Blocks.air &&
							!world.canBlockSeeTheSky(x, y - 1, z) && world.getBlock(x, y - 1, z) != AllDemDimensions.crystal)
						{
							for(int k1 = 0; k1 < random.nextInt(6); k1++)
							{
								world.setBlock(x, y + k1, z, AllDemDimensions.crystal, 0, 2);
							}
							flag = true;
						}
						if(!flag)
						{
							b++;
							if(b > 16)
							{
								return;
							}
							i = (chunkX * 16) + random.nextInt(16);
							j = random.nextInt(40);
							k = (chunkZ * 16) + random.nextInt(16);
							break label0;
						}
					}
				}
			}
			if(flag)
			{
				System.out.println("[ADD] Crystals generated at " + i + "," + j + "," + k);
			}
		}
		
    }
}
