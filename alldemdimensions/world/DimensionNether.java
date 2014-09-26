package alldemdimensions.world;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class DimensionNether extends Dimension
{

	public DimensionNether(int i, Block j, Block k, int l, int i1, String s, byte b)
	{
		super(i, j, k, l, i1, s, b);
		terrainLayers.add(floorLayer);
		terrainLayers.add(ceilingLayer);
		displayName = "Nether";
	}
	
	public static final TerrainLayer floorLayer = new TerrainLayer().setDefaultStoneBlock(Blocks.netherrack).setSeaLevel(32).setDefaultOceanBlock(Blocks.lava).setGenerateBedrock(true);
	public static final TerrainLayer ceilingLayer = new TerrainLayer().setDefaultStoneBlock(Blocks.netherrack).setSeaLevel(32).setDefaultOceanBlock(Blocks.lava).setGenerateBedrock(true);

}
