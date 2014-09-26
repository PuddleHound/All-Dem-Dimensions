package alldemdimensions.world.biome;

import java.util.ArrayList;

import net.minecraft.world.gen.layer.GenLayer;
import alldemdimensions.world.Dimension;

public class BiomeGenerator
{
	
	public int getMainBiome(GenLayerMajorBiome genlayer)
	{
		return mainBiomes.get(genlayer.nextInt(mainBiomes.size())).biomeId;
	}
		
	public int getSubBiome(GenLayerMinorBiome genlayer, int baseBiomeId)
	{
		for(BiomeEntry entry : subBiomes)
		{
			if(entry.baseBiomeId == baseBiomeId && (entry.rarity <= 1 || genlayer.nextInt(entry.rarity) == 0))
			{
				return entry.biomeId;
			}
		}
		return baseBiomeId;
	}
	
	public int getBaseRiverBiome()
	{
		return defaultRiverBiome.biomeID;
	}
	
	public int getSpecializedRiverBiome(GenLayerRiverBiome genlayer, int baseBiomeId)
	{
		for(BiomeEntry entry : riverBiomes)
		{
			if((entry.baseBiomeId <= 0 || entry.baseBiomeId == baseBiomeId) && (entry.rarity <= 1 || genlayer.nextInt(entry.rarity) == 0))
			{
				return entry.biomeId;
			}
		}
		return getBaseRiverBiome();
	}
	
	public int getRareBiome(GenLayerMiscBiome genlayer, int baseBiomeId)
	{
		for(BiomeEntry entry : miscBiomes)
		{
			if((entry.baseBiomeId <= 0 || entry.baseBiomeId == baseBiomeId) && (entry.rarity <= 1 || genlayer.nextInt(entry.rarity) == 0))
			{
				return entry.biomeId;
			}
		}
		return baseBiomeId;
	}
	
	public int getEdgeBiome(GenLayerBorderBiome genlayer, int baseBiomeId, int... surroundingBiomeIds)
	{
		for(BiomeEntry entry : borderBiomes)
		{
			if((entry.baseBiomeId <= 0 || entry.baseBiomeId == baseBiomeId) && (entry.baseBiomeId1 <= 0 || arrayContainsId(surroundingBiomeIds, entry.baseBiomeId1)) && (entry.rarity <= 1 || genlayer.nextInt(entry.rarity) == 0))
			{
				return entry.biomeId;
			}
		}
		return baseBiomeId;
	}
	
	public int getBaseOceanBiome()
	{
		return defaultOceanBiome.biomeID;
	}
	
	public int getSpecializedOceanBiome(GenLayer genlayer, int baseBiomeId)//oceans currently unimplemented
	{
		/*for(BiomeEntry entry : oceanBiomes)
		{
			if((entry.baseBiomeId <= 0 || entry.baseBiomeId == baseBiomeId) && (entry.rarity <= 1 || genlayer.nextInt(entry.rarity) == 0))
			{
				return entry.biomeId;
			}
		}*/
		return getBaseOceanBiome();
	}
	
	private static boolean arrayContainsId(int[] array, int... ids)
	{
		for(int i : array)
		{
			for(int j : ids)
			{
				if(i == j)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/*public static boolean isOcean(int biomeId)
	{
		return biomeId == ocean.biomeID || biomeId == deepOcean.biomeID;
	}*/
	
	public ArrayList<BiomeEntry> mainBiomes = new ArrayList<BiomeEntry>();
	public ArrayList<BiomeEntry> subBiomes = new ArrayList<BiomeEntry>();
	public ArrayList<BiomeEntry> riverBiomes = new ArrayList<BiomeEntry>();
	public BiomeGenMainDimension defaultRiverBiome;
	public ArrayList<BiomeEntry> miscBiomes = new ArrayList<BiomeEntry>();
	public ArrayList<BiomeEntry> oceanBiomes = new ArrayList<BiomeEntry>();
	public BiomeGenMainDimension defaultOceanBiome;
	public ArrayList<BiomeEntry> borderBiomes = new ArrayList<BiomeEntry>();
	public Dimension dimension;
	
	/*
	 * biome types:
	 * main
	 * sub (base)
	 * river (optional base)
	 * ocean (optional base)
	 * border (base, optional base)
	 * misc/rare (optional base)
	 */

}
