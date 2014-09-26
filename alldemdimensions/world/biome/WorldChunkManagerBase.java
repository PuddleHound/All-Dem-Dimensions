package alldemdimensions.world.biome;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.*;
import alldemdimensions.edit.ReflectionManager;
import alldemdimensions.world.Dimension;

public class WorldChunkManagerBase extends WorldChunkManager
{

    public WorldChunkManagerBase(World world)
    {
    	super();
        dimension = Dimension.getDimensionForId_MC(world.provider.dimensionId);
        GenLayer[] agenlayer = initGenLayers(world.getSeed(), world.getWorldInfo().getTerrainType());
        ReflectionManager.setFieldValue(this, field_genBiomes, agenlayer[0]);
        ReflectionManager.setFieldValue(this, field_biomeIndexLayer, agenlayer[1]);
    }

    @Override
    public List getBiomesToSpawnIn()
    {
        return this.biomesToSpawnIn;
    }
    
    public GenLayer[] initGenLayers(long seed, WorldType worldType)
    {
        GenLayerStart genlayerstart = new GenLayerStart(1L, dimension);
        GenLayerFuzzyZoom genlayerfuzzyzoom = new GenLayerFuzzyZoom(2000L, genlayerstart);
        GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, genlayerfuzzyzoom);
        genlayerzoom = new GenLayerZoom(2002L, genlayerzoom);
        genlayerzoom = new GenLayerZoom(2003L, genlayerzoom);
        GenLayer genlayer3 = GenLayerZoom.magnify(1000L, genlayerzoom, 0);
        byte biomeSize = 5;

        GenLayer genlayer = GenLayerZoom.magnify(1000L, genlayer3, 0);
        GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, genlayer);
        GenLayer genlayer4 = new GenLayerMajorBiome(200L, genlayer3, worldType, dimension);
        genlayer4 = GenLayerZoom.magnify(1000L, genlayer4, 2);
        genlayer4 = new GenLayerBorderBiome(1000L, genlayer4, dimension);

        GenLayer genlayer1 = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        GenLayerMinorBiome genlayerminorbiome = new GenLayerMinorBiome(1000L, (GenLayer)genlayer4, genlayer1, dimension);
        genlayer = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        genlayer = GenLayerZoom.magnify(1000L, genlayer, biomeSize);
        GenLayerBasicRiver genlayerbasicriver = new GenLayerBasicRiver(1L, genlayer, dimension);
        GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayerbasicriver);
        genlayer4 = new GenLayerMiscBiome(1001L, genlayerminorbiome, dimension);
        //genlayer4 = genlayerminorbiome;
        
        for(int j = 0; j < biomeSize; ++j)
        {
            genlayer4 = new GenLayerZoom((long)(1000 + j), (GenLayer)genlayer4);
        }

        GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, (GenLayer)genlayer4);
        GenLayerRiverBiome genlayerrivermix = new GenLayerRiverBiome(100L, genlayersmooth1, genlayersmooth, dimension);
        GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, genlayerrivermix);
        genlayerrivermix.initWorldGenSeed(seed);
        genlayervoronoizoom.initWorldGenSeed(seed);
        return new GenLayer[] {genlayerrivermix, genlayervoronoizoom, genlayerrivermix};
    }
    
    public Dimension dimension;
    public static final Field field_genBiomes = ReflectionManager.accessField(WorldChunkManager.class, "genBiomes", "genBiomes");
    public static final Field field_biomeIndexLayer = ReflectionManager.accessField(WorldChunkManager.class, "biomeIndexLayer", "biomeIndexLayer");
    private List<BiomeGenBase> biomesToSpawnIn;
}