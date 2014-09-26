package alldemdimensions.world;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class TerrainLayer
{
	
	public TerrainLayer setUpperLimit(int i)
	{
		terrainUpperLimit = i;
		heightShift = i == 256 ? 8 : 7;
		xShift = heightShift + 4;
		return this;
	}
	
	public TerrainLayer setLowerLimit(int i)
	{
		terrainLowerLimit = i;
		return this;
	}
	
	public TerrainLayer setSeaLevel(int i)
	{
		seaLevel = i;
		return this;
	}
	
	public TerrainLayer setDefaultStoneBlock(Block block)
	{
		defaultBlock = block;
		return this;
	}
	
	public TerrainLayer setDefaultOceanBlock(Block block)
	{
		seaBlock = block;
		return this;
	}
	
	public TerrainLayer setGenerateBedrock(boolean flag)
	{
		generateBedrock = flag;
		return this;
	}
	
	public TerrainLayer setTerrainValue(float f)
	{
		terrainValue = f;
		return this;
	}
	
	public TerrainLayer setNoiseOffset(int i)
	{
		noiseOffset = i / 8;
		return this;
	}
	
	public TerrainLayer setUseOnlyStoneBlock(boolean flag)
	{
		useOnlyStoneBlock = flag;
		return this;
	}
	
	public int terrainUpperLimit = 128;
	public int terrainLowerLimit = 0;
	public int seaLevel = 64;
	public Block defaultBlock = Blocks.stone;
	public Block seaBlock = Blocks.water;
	public boolean generateBedrock = true;
	public int xShift = 11;
	public int heightShift = 7;
	public double terrainValue = 4D;
	public int noiseOffset = 0;
	public boolean useOnlyStoneBlock;
	
}
