package alldemdimensions.world;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class DimensionKyther extends Dimension
{
	
	public DimensionKyther(int i, Block j, Block k, int l, int i1, String s, byte b)
	{
		super(i, j, k, l, i1, s, b);
		//terrainLayers.add(seaFloorLayer);
		terrainLayers.add(crustLayer);
	}
	
	public static final TerrainLayer seaFloorLayer = new TerrainLayer().setDefaultStoneBlock(Blocks.stone).setSeaLevel(32).setDefaultOceanBlock(Blocks.lava).setGenerateBedrock(true);
	public static final TerrainLayer crustLayer = new TerrainLayer().setDefaultStoneBlock(Blocks.stone).setSeaLevel(112).setDefaultOceanBlock(Blocks.water).setGenerateBedrock(true);


}
