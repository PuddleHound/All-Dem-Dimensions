package alldemdimensions.world;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.block.Plant;
import alldemdimensions.edit.ReflectionManager;
import alldemdimensions.entity.EntityAurora;
import alldemdimensions.entity.EntityTornado;
import alldemdimensions.util.IDataHandler;
import alldemdimensions.util.NBTDataHandler;
import alldemdimensions.world.biome.BiomeGenSkyBase;
import alldemdimensions.world.environment.EnumZenithDay;
import alldemdimensions.world.environment.EnumZenithWeather;
import alldemdimensions.world.environment.Planet;
import alldemdimensions.world.environment.Rainbow;

public class DimensionZenith extends Dimension implements IDataHandler
{

	public DimensionZenith(int i, Block j, Block k, int l, int i1, String s, byte b)
	{
		super(i, j, k, l, i1, s, b);
		terrainLayers.add(earthLayer);
		terrainLayers.add(cloudLayer);
		defaultBiome = BiomeGenSkyBase.skylands;
		NBTDataHandler.register(this);
	}
	
    @Override
	public void onWorldTick(WorldServer world)
	{
		Random random = world.rand;
	
		for(Planet planet : Planet.allPlanets)
		{
			planet.onUpdate(world, random);
		}
		//Planet.sendInfoToClient(world);
		//PacketHandlerServer.sendPlanetInfo();
		alldemdimensions.network.ADDPacket.server_planetInfo.send();
		
		Set set = (Set)ReflectionManager.getFieldValue(world, field_World_activeChunkSet);
		Iterator iterator = set.iterator();
        while(iterator.hasNext())
        {
            ChunkCoordIntPair chunkCoordXZ = (ChunkCoordIntPair)iterator.next();
            int chunkX = chunkCoordXZ.chunkXPos * 16;
            int chunkZ = chunkCoordXZ.chunkZPos * 16;
            Chunk chunk = world.getChunkFromChunkCoords(chunkCoordXZ.chunkXPos, chunkCoordXZ.chunkZPos);
            int randomOffset;
            int blockX;
            int blockZ;
            int blockY;
            if(world.rand.nextInt(16) == 0)
            {
				randomBlockPlacement = randomBlockPlacement * 3 + 1013904223;
				randomOffset = randomBlockPlacement >> 2;
				blockX = randomOffset & 15;
				blockZ = randomOffset >> 8 & 15;
				blockY = world.getPrecipitationHeight(blockX + chunkX, blockZ + chunkZ);
				BiomeGenBase biome = world.getBiomeGenForCoords(blockX + chunkX, blockZ + chunkZ);
				if(biome instanceof BiomeGenSkyBase)
				{
					EnumZenithWeather weatherType = ((BiomeGenSkyBase)biome).getWeatherType();
					if(world.isRaining() && world.isAirBlock(blockX + chunkX, blockY, blockZ + chunkZ) && world.getBlock(blockX + chunkX, blockY, blockZ + chunkZ) != AllDemDimensions.cloud/* && world.canSnowAt(blockX + chunkX, blockY, blockZ + chunkZ)*/)
					{
						if(weatherType == EnumZenithWeather.FLOWERS)
						{
							world.setBlock(blockX + chunkX, blockY, blockZ + chunkZ, Plant.zenith_flowerCover.plantBlock);
						} else
						if(weatherType == EnumZenithWeather.SLEET)
						{
							world.setBlock(blockX + chunkX, blockY, blockZ + chunkZ, Blocks.snow);
						}
					}
					if(world.isRaining() && weatherType == EnumZenithWeather.RAIN)
					{
						Block id = world.getBlock(blockX + chunkX, blockY - 1, blockZ + chunkZ);
						if(!id.isAir(world, blockX + chunkX, blockY - 1, blockZ + chunkZ))
						{
							id.fillWithRain(world, blockX + chunkX, blockY - 1, blockZ + chunkZ);
						}
					}
				}
            }
			int spawnX;
			int spawnY;
			int spawnZ;
			if(windStrength > 0F)
			{
				spawnX = chunkX + world.rand.nextInt(16);
				spawnY = world.rand.nextInt(128);
				spawnZ = chunkZ + world.rand.nextInt(16);
				if(windStrength > 0.75F && random.nextInt(200000) == 0)
				{
					EntityTornado tornado = new EntityTornado(world);
					tornado.setLocationAndAngles(spawnX, spawnY, spawnZ, 0F, 0F);
					world.spawnEntityInWorld(tornado);
				} else
				if(world.rand.nextInt((int)((2.0F - windStrength) * 16)) == 0 && world.isAirBlock(spawnX, spawnY, spawnZ))
				{/*
					EntityGust gust = new EntityGust(world);
					gust.setLocationAndAngles(spawnX, spawnY, spawnZ, 0F, 0F);
					world.spawnEntityInWorld(gust);*/
				}
			}
			if(random.nextInt(1000000) == 0 && EnumZenithDay.getCurrentTime(world) == EnumZenithDay.NIGHT)
			{
				spawnX = chunkX + world.rand.nextInt(16);
				spawnY = world.rand.nextInt(128);
				spawnZ = chunkZ + world.rand.nextInt(16);
				EntityAurora aurora = new EntityAurora(world);
				aurora.setLocationAndAngles(spawnX, spawnY, spawnZ, 0F, 0F);
				world.spawnEntityInWorld(aurora);
			}
		}
		
		if(random.nextInt(10000) == 0)
		{
			if(random.nextInt(10) == 0)
			{
				windStrength = random.nextFloat();
			} else
			{
				windStrength = 0F;
			}
		}
		
		if(random.nextInt(2000) == 0 && world.isRaining() && EnumZenithDay.getCurrentTime(world) == EnumZenithDay.DAY && currentRainbow == null)
		{
			Rainbow rainbow = new Rainbow();
			rainbow.maxAge = random.nextInt(1000) + 1000;
			rainbow.posX = random.nextInt(360);
			rainbow.posZ = random.nextInt(360);
			rainbow.sizeX = random.nextInt(128);
			rainbow.sizeZ = random.nextInt(128);
			currentRainbow = rainbow;
		}
		if(currentRainbow != null)
		{
			currentRainbow.onUpdate(world);
		}
	}
	
    @Override
	public void saveData(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setInteger("RandomBlockPlacement_" + name, randomBlockPlacement);
		nbttagcompound.setFloat("WindStrength_" + name, windStrength);
		boolean flag = currentRainbow != null;
		nbttagcompound.setBoolean("HasRainbow", flag);
		if(flag)
		{
			nbttagcompound.setInteger("Rainbow_Age", currentRainbow.age);
			nbttagcompound.setInteger("Rainbow_MaxAge", currentRainbow.maxAge);
			nbttagcompound.setInteger("Rainbow_PosX", currentRainbow.posX);
			nbttagcompound.setInteger("Rainbow_PosZ", currentRainbow.posZ);
			nbttagcompound.setInteger("Rainbow_SizeX", currentRainbow.sizeX);
			nbttagcompound.setInteger("Rainbow_SizeZ", currentRainbow.sizeZ);
		}
	}
	
    @Override
	public void loadData(NBTTagCompound nbttagcompound)
	{
		randomBlockPlacement = nbttagcompound.getInteger("RandomBlockPlacement_" + name);
		windStrength = nbttagcompound.getFloat("WindStrength_" + name);
		boolean flag = nbttagcompound.getBoolean("HasRainbow");
		if(flag)
		{
			currentRainbow = new Rainbow();
			currentRainbow.age = nbttagcompound.getInteger("Rainbow_Age");
			currentRainbow.maxAge = nbttagcompound.getInteger("Rainbow_MaxAge");
			currentRainbow.posX = nbttagcompound.getInteger("Rainbow_PosX");
			currentRainbow.posZ = nbttagcompound.getInteger("Rainbow_PosZ");
			currentRainbow.sizeX = nbttagcompound.getInteger("Rainbow_SizeX");
			currentRainbow.sizeZ = nbttagcompound.getInteger("Rainbow_SizeZ");
		}
	}
	
    @Override
    public boolean isValidForWorldGen(World world, int i, int j, int k)
    {
        Block l = world.getBlock(i, j - 1, k);
        if(world.isAirBlock(i, j, k) && (l == Blocks.grass || l == AllDemDimensions.cloud))
        {
            return true;
        }
        return false;
    }
        
    public static final TerrainLayer earthLayer = new TerrainLayer().setDefaultStoneBlock(AllDemDimensions.limestone).setSeaLevel(0).setGenerateBedrock(false).setTerrainValue(-4F);//.setNoiseOffset(32);//.setLowerLimit(-32);
	public static final TerrainLayer cloudLayer = new TerrainLayer().setDefaultStoneBlock(AllDemDimensions.cloud).setSeaLevel(0).setGenerateBedrock(false).setTerrainValue(-1F).setUseOnlyStoneBlock(true);
    private static int randomBlockPlacement = (new Random()).nextInt();
	private static float windStrength = 0F;
	public static Rainbow currentRainbow;
	private static final Field field_World_activeChunkSet = ReflectionManager.accessField(World.class, "activeChunkSet", "activeChunkSet");//1.7.2
}
