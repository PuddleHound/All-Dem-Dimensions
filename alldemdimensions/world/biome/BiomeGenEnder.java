package alldemdimensions.world.biome;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.entity.EntityEnderwraith;
import alldemdimensions.world.Dimension;
import alldemdimensions.world.gen.WorldGenEndBridge;
import alldemdimensions.world.gen.WorldGenEndTemple;

public class BiomeGenEnder extends BiomeGenMainDimension
{
    public BiomeGenEnder(int i)
    {
        super(i, Dimension.ender);
        spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 5, 4, 4));
        spawnableMonsterList.add(new SpawnListEntry(EntityEnderwraith.class, 5, 4, 4));
        topBlock = Blocks.end_stone;
        fillerBlock = Blocks.end_stone;
        worldGen[GEN_STRUCTURE].put(bridge, 6);
        worldGen[GEN_STRUCTURE].put(temple, -16);
    }

    @Override
    public int getSkyColorByTemp(float f)
    {
        return 0;
    }
    
    public WorldGenerator bridge = new WorldGenEndBridge();
    public WorldGenerator temple = new WorldGenEndTemple();
	public static final BiomeGenMainDimension ender = (new BiomeGenEnder(199)).setBiomeName("Ender");
}
