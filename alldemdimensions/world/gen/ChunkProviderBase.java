package alldemdimensions.world.gen;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.NETHER_BRIDGE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenNetherBridge;
import net.minecraftforge.event.terraingen.TerrainGen;
import alldemdimensions.world.Dimension;
import alldemdimensions.world.TerrainLayer;
import alldemdimensions.world.biome.BiomeGenMainDimension;

public class ChunkProviderBase implements IChunkProvider
{	
	
	public ChunkProviderBase(World world, long seed, Dimension dim)
	{
		dimension = dim;
	    stoneNoise = new double[256];
	    ///caveGenerator = new MapGenKytherCaves();
	    ///ravineGenerator = new MapGenKytherTrench();
	    worldObj = world;
	    rand = new Random(seed);
	    noiseGen1 = new NoiseGeneratorOctaves(rand, 16);
	    noiseGen2 = new NoiseGeneratorOctaves(rand, 16);
	    noiseGen3 = new NoiseGeneratorOctaves(rand, 8);
	    noiseGen4 = new NoiseGeneratorOctaves(rand, 4);
	    noiseGen5 = new NoiseGeneratorOctaves(rand, 10);
	    noiseGen6 = new NoiseGeneratorOctaves(rand, 16);
	    mobSpawnerNoise = new NoiseGeneratorOctaves(rand, 8);
	    
	    if(dimension == Dimension.nether)
	    {
	    	genNetherBridge = (MapGenNetherBridge) TerrainGen.getModdedMapGen(genNetherBridge, NETHER_BRIDGE);
	    }
	    
	}
	
	public void generateTerrain(int x, int z, Block blocks[], TerrainLayer layer)
	{
	    byte byte0 = 4;
	    int byte1 = layer.terrainUpperLimit / 8;//16
		int byte2 = layer.seaLevel;
		int i = byte0 + 1;
		int byte3 = layer.terrainUpperLimit / 8 + 1;//17;
		int j = byte0 + 1;
		noiseArray = initializeNoiseField(noiseArray, x * byte0, 0, z * byte0, i, byte3, j, layer);
		
		for (int k = 0; k < byte0; k++)
		{
		    for (int l = 0; l < byte0; l++)
		    {
		        for (int i1 = 0; i1 < byte1; i1++)
		        {
		            double d = 0.125D;
		            double d1 = noiseArray[((k + 0) * j + (l + 0)) * byte3 + (i1 + 0)];
		            double d2 = noiseArray[((k + 0) * j + (l + 1)) * byte3 + (i1 + 0)];
		            double d3 = noiseArray[((k + 1) * j + (l + 0)) * byte3 + (i1 + 0)];
		            double d4 = noiseArray[((k + 1) * j + (l + 1)) * byte3 + (i1 + 0)];
		            double d5 = (noiseArray[((k + 0) * j + (l + 0)) * byte3 + (i1 + 1)] - d1) * d;
		            double d6 = (noiseArray[((k + 0) * j + (l + 1)) * byte3 + (i1 + 1)] - d2) * d;
		            double d7 = (noiseArray[((k + 1) * j + (l + 0)) * byte3 + (i1 + 1)] - d3) * d;
		            double d8 = (noiseArray[((k + 1) * j + (l + 1)) * byte3 + (i1 + 1)] - d4) * d;
		
		            for (int j1 = 0; j1 < 8; j1++)
		            {
		                double d9 = 0.25D;
		                double d10 = d1;
		                double d11 = d2;
		                double d12 = (d3 - d1) * d9;
		                double d13 = (d4 - d2) * d9;
		
		                for (int k1 = 0; k1 < 4; k1++)
		                {
		                    int offset = layer.terrainLowerLimit;
		                    //int l1 = (i1) * 8 + j1 + offset << 8 | 0 + l * 4 << 4 | k1 + k * 4 << xShift;
	                        int l1 = k1 + k * 4 << layer.xShift | 0 + l * 4 << layer.heightShift | i1 * 8 + j1 + offset;
	                        int c = 1 << layer.heightShift;
	                        l1 -= c;
	                        double d14 = 0.25D;
	                        double d15 = d10;
	                        double d16 = (d11 - d10) * d14;
	                        d15 -= d16;
	                        for (int i2 = 0; i2 < 4; i2++)
	                        {
	                            boolean flag1 = (d15 += d16) > 0.0D;
	                            l1 += c;
	                            if(l1 >= 0 && /*i1 * j1 < dimension.heightLimit &&*/ blocks[l1] == Blocks.air)
	                            {
	                                if (flag1 && (i1 * 8 + j1 < dimension.heightLimit - (layer.noiseOffset * 8)))
	                                {
	                                    blocks[l1] = layer.defaultBlock;
	                                    continue;
	                                }
	                                if (i1 * 8 + j1 < byte2)
	                                {
	                                    blocks[l1] = layer.seaBlock;
	                                }
	                                else
	                                {
	                                    blocks[l1] = Blocks.air;
	                                }
	                            }
	                        }
	
	                        d10 += d12;
	                        d11 += d13;
	                    }
	
	                    d1 += d5;
	                    d2 += d6;
	                    d3 += d7;
	                    d4 += d8;
	                }
	            }
	        }
	    }
	}
	
	public void replaceBlocksForBiome(int x, int z, Block[] blocks, byte[] metadata, BiomeGenBase biomesInChunk[], TerrainLayer layer)
	{
		int seaLevel = layer.seaLevel;
		double d = 0.03125D;
		stoneNoise = noiseGen4.generateNoiseOctaves(stoneNoise, x * 16, z * 16, 0, 16, 16, 1, d * 2D, d * 2D, d * 2D);
		
		for (int i = 0; i < 16; i++)
		{
		    for (int j = 0; j < 16; j++)
		    {
		        BiomeGenMainDimension biomegenbase = getBiome(biomesInChunk[j + i * 16]);
		        float f = biomegenbase.temperature;//1.7.3
		        int k = (int)(stoneNoise[i + j * 16] / 3D + 3D + rand.nextDouble() * 0.25D);
		        int l = -1;
		        Block byte1 = biomegenbase.getTopBlock(layer);
		        Block byte2 = biomegenbase.getFillerBlock(layer);
		        Block stone = biomegenbase.getStoneBlock(layer);
		        byte meta1 = biomegenbase.getTopMetadata(layer);
		        byte meta2 = biomegenbase.getFillerMetadata(layer);
		        byte stoneMeta = biomegenbase.getStoneMetadata(layer);
		
		        for (int i1 = dimension.heightLimit - 1; i1 >= 0; i1--)
		        {
		            int j1 = (j * 16 + i) * dimension.heightLimit + i1;
		
		            if (layer.generateBedrock && (i1 <= 0 + rand.nextInt(5) || i1 >= 255 - rand.nextInt(5)))
		            {
		                blocks[j1] = Blocks.bedrock;
		                continue;
		            }
		
		            Block byte3 = blocks[j1];
		
		            if (byte3 == Blocks.air)
		            {
		                l = -1;
		                continue;
		            }
		
		            if (byte3 != layer.defaultBlock)
		            {
		                continue;
		            } else
		            {
		            	blocks[j1] = layer.defaultBlock;
		            }
		            	
		            if (l == -1)
		            {
		                if (k <= 0)
		                {
		                    byte1 = Blocks.air;
		                    byte2 = biomegenbase.getStoneBlock(layer);
		                    meta1 = 0;
		                    meta2 = biomegenbase.getStoneMetadata(layer);
		                }
		                else if (i1 >= seaLevel - 4 && i1 <= seaLevel + 1)
		                {
		                    byte1 = biomegenbase.getTopBlock(layer);
		                    byte2 = biomegenbase.getFillerBlock(layer);
		                    meta1 = biomegenbase.getTopMetadata(layer);
		                    meta2 = biomegenbase.getFillerMetadata(layer);
		                }
		
		                if (i1 < seaLevel && byte1 == Blocks.air)
		                {
		                    /*if (f < 0.15F)
		                    {
		                        byte1 = (byte)Block.ice.blockID;
		                    }
		                    else*/
		                    {
		                        byte1 = layer.seaBlock;
		                    }
		                }
		
		                l = k;
		
		                if (i1 >= seaLevel - 1)
		                {
		                    blocks[j1] = byte1;
		                    metadata[j1] = meta1;
		                }
		                else
		                {
		                    blocks[j1] = byte2;
		                    metadata[j1] = meta2;
		                }
		
		                continue;
		            }
		            
		            if(blocks[j1] == layer.defaultBlock)
		            {
		                blocks[j1] = stone;
		                metadata[j1] = stoneMeta;
		            }
		
		            if (l <= 0)
		            {
		                continue;
		            }
		
		            l--;
		            blocks[j1] = byte2;
		            metadata[j1] = meta2;
		            
	            }
	        }
	    }
	}
	
	@Override
	public Chunk loadChunk(int x, int z)
	{
	    return provideChunk(x, z);
	}
	
	@Override
	public Chunk provideChunk(int x, int z)
	{
	    rand.setSeed((long)x * 0x4f9939f508L + (long)z * 0x1ef1565bd5L);
	    Block blocks[] = new Block[16 * dimension.heightLimit * 16];
	    Arrays.fill(blocks, Blocks.air);
	    byte meta[] = new byte[16 * dimension.heightLimit * 16];
	    
		biomesForGeneration = worldObj.getWorldChunkManager().getBiomesForGeneration(biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
		
		for(TerrainLayer layer : dimension.terrainLayers)
	    {
	    	generateTerrain(x, z, blocks, layer);
	    }
	    
	    biomesForGeneration = worldObj.getWorldChunkManager().loadBlockGeneratorData(biomesForGeneration, x * 16, z * 16, 16, 16);
	    for(TerrainLayer layer : dimension.terrainLayers)
	    {
	    	replaceBlocksForBiome(x, z, blocks, meta, biomesForGeneration, layer);
	    }
	    //caveGenerator.generate(this, worldObj, x, z, ids);
		//ravineGenerator.generate(this, worldObj, x, z, ids);
		Chunk chunk = new Chunk(worldObj, blocks, meta, x, z);
		    
		byte[] biomes = chunk.getBiomeArray();
	
	    for (int k = 0; k < biomes.length; ++k)
	    {
	        biomes[k] = (byte)this.biomesForGeneration[k].biomeID;
	    }
		
		chunk.generateSkylightMap();
		if(dimension == Dimension.nether)
		{
			chunk.resetRelightChecks();
		}
	    return chunk;
	}
	
	private double[] initializeNoiseField(double noiseArrayRet[], int x, int y, int z, int sizeX, int sizeY, int sizeZ, TerrainLayer layer)
	{
	    if (noiseArrayRet == null)
	    {
	        noiseArrayRet = new double[sizeX * sizeY * sizeZ];
	    }
	
	    if (parabolicField == null)
	    {
	        parabolicField = new float[25];
	
	        for (int i = -2; i <= 2; i++)
	        {
	            for (int j = -2; j <= 2; j++)
	            {
	                float f = 10F / MathHelper.sqrt_float((float)(i * i + j * j) + 0.2F);
	                parabolicField[i + 2 + (j + 2) * 5] = f;
	            }
	        }
	    }
	
		//x = y = 0;
		int k = 0;
		int l = 0;
		
		for (int i1 = 0; i1 < sizeX; i1++)
		{
		    for (int j1 = 0; j1 < sizeZ; j1++)
		    {
		        BiomeGenMainDimension biomegenbase = getBiome(biomesForGeneration[i1 + 2 + (j1 + 2) * (sizeX + 5)]);
		        double d = biomegenbase.getTerrainNoise(layer, 0);
		        double d1 = biomegenbase.getTerrainNoise(layer, 1);
		        int noiseX = x + i1;//((double)i1 * 0.25D);
		        int noiseZ = z + j1;//((double)j1 * 0.25D);
		        noise5 = noiseGen5.generateNoiseOctaves(noise5, noiseX, noiseZ, 1, 1, 1.121D, 1.121D, 0.5D);
		        noise6 = noiseGen6.generateNoiseOctaves(noise6, noiseX, noiseZ, 1, 1, 200D, 200D, 0.5D);
		        noise3 = noiseGen3.generateNoiseOctaves(noise3, noiseX, y + layer.noiseOffset, noiseZ, 1, sizeY + layer.noiseOffset, 1, d / 80D, d1 / 160D, d / 80D);
		        noise1 = noiseGen1.generateNoiseOctaves(noise1, noiseX, y + layer.noiseOffset, noiseZ, 1, sizeY + layer.noiseOffset, 1, d, d1, d);
		        noise2 = noiseGen2.generateNoiseOctaves(noise2, noiseX, y + layer.noiseOffset, noiseZ, 1, sizeY + layer.noiseOffset, 1, d, d1, d);
		        
		        float f1 = 0.0F;
		        float f2 = 0.0F;
		        float f3 = 0.0F;
		        byte byte0 = 2;
		
		        for (int k1 = -byte0; k1 <= byte0; k1++)
		        {
		            for (int l1 = -byte0; l1 <= byte0; l1++)
		            {
		                BiomeGenMainDimension biomegenbase1 = getBiome(biomesForGeneration[i1 + k1 + 2 + (j1 + l1 + 2) * (sizeX + 5)]);
		                float minHeight = biomegenbase1.getMinHeight(layer);
		                float maxHeight = biomegenbase1.getMaxHeight(layer);
		                float f4 = parabolicField[k1 + 2 + (l1 + 2) * 5] / (minHeight + 2.0F);
		                if (minHeight > biomegenbase.getMinHeight(layer))
		                {
		                        f4 /= 2.0F;
		                }
		                f1 += maxHeight * f4;
		                f2 += minHeight * f4;
		                f3 += f4;
		            }
		        }
		
		        f1 /= f3;
		        f2 /= f3;
		        f1 = f1 * 0.9F + 0.1F;
		        f2 = (f2 * 4F - 1.0F) / 8F;
		        double d2 = noise6[0] / 8000D;
		
		        if (d2 < 0.0D)
		        {
		            d2 = -d2 * 0.3D;
		        }
		
		        d2 = d2 * 3D - 2D;
		
		        if (d2 < 0.0D)
		        {
		            d2 /= 2D;
		
		            if (d2 < -1D)
		            {
		                d2 = -1D;
		            }
		            
		            //d2 -= 7D;//zenith
		
		            d2 /= 1.4D;
		            d2 /= 2D;
		        }
		        else
		        {
		            if (d2 > 1.0D)
		            {
		                d2 = 1.0D;
		            }
		            
		            //d2 -= 10D;//zenith
		
		            d2 /= 8D;
		        }
		
		        l++;
		
		        for (int i2 = layer.noiseOffset; i2 < sizeY + layer.noiseOffset; i2++)
		        {
		            double d3 = f2;
		            double d4 = f1;
		            d3 += d2 * 0.2D;
		            d3 = (d3 * (double)sizeY) / 16D;
		            double d5 = (double)sizeY / 2D + d3 * 4D;
		            double d6 = 0.0D;
		            double d7 = (((double)i2 - d5) * 12D * 128D) / (double)dimension.heightLimit / d4;
		            if (d7 < 0.0D)
		            {
		            	d7 *= biomegenbase.getLowerTerrainOrientation(layer);//lower makes mountain islands shallower
		            } else
		            {
		            	d7 *= biomegenbase.getUpperTerrainOrientation(layer);
		            }
	            
	                double d8 = noise1[i2 - layer.noiseOffset] / biomegenbase.getTerrainNoise(layer, 2);
	                double d9 = noise2[i2 - layer.noiseOffset] / biomegenbase.getTerrainNoise(layer, 3);
	                double d10 = (noise3[i2 - layer.noiseOffset] / 10D + 1.0D) / 2D;
	
	                if(layer != Dimension.zenith.cloudLayer)
	                {
		                if (d10 < 0.0D)
		                {
		                    d6 = d8;
		                }
		                else if (d10 > 1.0D)
		                {
		                    d6 = d9;
		                }
		                else
		                {
		                    d6 = d8 + (d9 - d8) * d10;
		                }
	                } else
	                {
		                if (d10 < (-3.0D - 0.5D) * 24.0D)
	                    {
	                        d6 = d8;
	                    }
	                    else if (d10 > (0.5D - (-2.0D)) * 24.0D)
	                    {
	                        d6 = d9;
	                    }
	                    else
	                    {
	                        d6 = d8 + (d9 - d8) * d10;
	                    }
	                }
	                
	                d6 -= d7;
	
	                if (i2 > sizeY - 4)
	                {
	                    double d11 = (float)(i2 - (sizeY - 4)) / 3F;
	                    d6 = d6 * (1.0D - d11) + -10D * d11;
	                }
	
	                noiseArrayRet[k] = d6;
	                k++;
	            }
	        }
	    }
	
	    return noiseArrayRet;
	}
	
	@Override
	public boolean chunkExists(int x, int y)
	{
	    return true;
	}
	
	@Override
	public void populate(IChunkProvider ichunkprovider, int chunkX, int chunkZ)
	{
		BlockSand.fallInstantly = true;
		int i = chunkX * 16;
		int j = chunkZ * 16;
		
		if(dimension == Dimension.nether)
		{
			//this.genNetherBridge.generateStructuresInChunk(worldObj, rand, chunkX, chunkZ);
		}
		
		BiomeGenBase biomegenbase = worldObj.getBiomeGenForCoords(i + 16, j + 16);
		rand.setSeed(worldObj.getSeed());
		long l = (rand.nextLong() / 2L) * 2L + 1L;
		long l1 = (rand.nextLong() / 2L) * 2L + 1L;
		rand.setSeed((long)chunkX * l + (long)chunkZ * l1 ^ worldObj.getSeed());
		boolean flag = false;
		
		if(!(biomegenbase instanceof BiomeGenMainDimension))
		{
		        System.out.println("Illegal biome generated at " + i + ", " + j);
		        BlockSand.fallInstantly = false;
		        return;
		}
		BiomeGenMainDimension biome = (BiomeGenMainDimension)biomegenbase;
		Iterator<Map.Entry<WorldGenerator, Integer>> iterator;
		WorldGenerator worldgenerator;
		int count;
		Map.Entry entry;
		int x;
		int y;
		int z;
		for(HashMap<WorldGenerator, Integer> map : biome.worldGen)
		{
		        iterator = map.entrySet().iterator();
		        while(iterator.hasNext())
		        {
		                entry = iterator.next();
		                worldgenerator = (WorldGenerator)entry.getKey();
		                count = (Integer)entry.getValue();
		                if(worldgenerator != null && count != 0)
		                {
		                        if(count < 0)
		                        {
		                                if(rand.nextInt(-count) == 0)
		                                {
		                                        x = i + rand.nextInt(16) + 8;
		                                        y = rand.nextInt(dimension.heightLimit);
		                                        z = j + rand.nextInt(16) + 8;
		                                        worldgenerator.generate(worldObj, rand, x, y, z);
		                                }
		                        } else
		                        {
		                                for(int count1 = 0; count1 < count; count1++)
		                                {
		                                        x = i + rand.nextInt(16) + 8;
		                                        y = rand.nextInt(dimension.heightLimit);
		                                        z = j + rand.nextInt(16) + 8;
		                                        worldgenerator.generate(worldObj, rand, x, y, z);
		                                }
		                        }
		                }
		        }
		}
		
		SpawnerAnimals.performWorldGenSpawning(worldObj, biomegenbase, i + 8, j + 8, 16, 16, rand);
		i += 8;
		j += 8;
		
	    BlockSand.fallInstantly = false;
	}
	
	private BiomeGenMainDimension getBiome(BiomeGenBase biomegenbase)
	{
		if(biomegenbase instanceof BiomeGenMainDimension)
		{
			return (BiomeGenMainDimension)biomegenbase;
		}
		return dimension.defaultBiome;
	}
	
	private BiomeGenBase[] fillBiomeArray(BiomeGenBase[] biomeArray, int i, int j, int k, int l, boolean blockGeneratorData)
    {
        if(blockGeneratorData)
        {
            biomeArray = worldObj.getWorldChunkManager().loadBlockGeneratorData(biomeArray, i, j, k, l);
        } else
        {
            biomeArray = worldObj.getWorldChunkManager().getBiomesForGeneration(biomeArray, i, j, k, l);
        }
        for(int index = 0; index < biomeArray.length; index++)
        {
            if(!(biomeArray[index] instanceof BiomeGenMainDimension))
            {
                biomeArray[index] = dimension.defaultBiome;
            }
        }
        return biomeArray;
    }
	
	@Override
	public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate)
	{
	    return true;
	}
	
	@Override
	public boolean canSave()
	{
	    return true;
	}
	
	@Override
	public String makeString()
	{
	    return dimension.name + "RandomLevelSource";
	}
	
	@Override
	public List getPossibleCreatures(EnumCreatureType enumcreaturetype, int x, int y, int z)
	{
	    BiomeGenBase biomegenbase = worldObj.getBiomeGenForCoords(x, z);
	
	    if (biomegenbase == null)
	    {
	        return null;
	    } else
	    {
	        return biomegenbase.getSpawnableList(enumcreaturetype);
	    }
	}
	
	@Override
	public int getLoadedChunkCount()
	{
	    return 0;
	}
	
	@Override
	public void recreateStructures(int x, int y)
	{
	}
	
	@Override
	public boolean unloadQueuedChunks()
	{
	    return false;
	}
	    
	@Override
	public void saveExtraData()
	{
	}
	
	@Override
	public ChunkPosition func_147416_a(World world, String s, int x, int y, int z)
	{
		return null;
	}
	    
	/*public int heightShift = 8;//7;
	public int xShift = heightShift + 4;
	public int worldHeight = 1 << heightShift;
	public int worldMaxY = worldHeight - 1;
	public int seaLevel = worldHeight / 4;
	*/
	public Dimension dimension;
	private Random rand;
	private NoiseGeneratorOctaves noiseGen1;
	private NoiseGeneratorOctaves noiseGen2;
	private NoiseGeneratorOctaves noiseGen3;
	private NoiseGeneratorOctaves noiseGen4;
	private NoiseGeneratorOctaves noiseGen5;
	private NoiseGeneratorOctaves noiseGen6;
	private NoiseGeneratorOctaves mobSpawnerNoise;
	private World worldObj;
	private double noiseArray[];
	private double stoneNoise[];
	private BiomeGenBase biomesForGeneration[];
	private double noise3[];
	private double noise1[];
	private double noise2[];
	private double noise5[];
	private double noise6[];
	private float parabolicField[];
	public MapGenNetherBridge genNetherBridge;
	
	
}
