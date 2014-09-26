package alldemdimensions.world.biome; 

public class BiomeGenSkyBlossom extends BiomeGenSkyBase
{
    public BiomeGenSkyBlossom(int i)
    {
        super(i);
		temperature = 0.6F;
		rainfall = 0.8F;
		worldGen[GEN_TREE].put(plumTree, 24);
		worldGen[GEN_TREE].put(cherryTree, 24);
		worldGen[GEN_TREE].put(bonsaiTree, 24);
		worldGen[GEN_PLANT].put(delphinium, 2);
		worldGen[GEN_PLANT].put(anthurium, -2);
		worldGen[GEN_PLANT].put(flowerGrass, 8);
		worldGen[GEN_ORE].put(nectarfall, 5);
    }
	
    @Override
	public int getBiomeGrassColor(int i, int j, int k)
    {
        return 0x79d161;
    }

    @Override
    public int getBiomeFoliageColor(int i, int j, int k)
    {
		return 0x4ac94e;
	}
}
