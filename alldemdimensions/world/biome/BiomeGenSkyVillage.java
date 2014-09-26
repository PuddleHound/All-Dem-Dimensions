package alldemdimensions.world.biome; 

public class BiomeGenSkyVillage extends BiomeGenSkyBase
{
    public BiomeGenSkyVillage(int i)
    {
        super(i);
		minHeight = -24.0F;
		maxHeight = 0.2F;
		minMtnHeight = -0.6F;
		maxMtnHeight = 0.8F;
		worldGen[GEN_STRUCTURE].put(village, 256);
		worldGen[GEN_TREE].put(chestnutTree, 128);
		worldGen[GEN_TREE].put(arrowTree, 24);
		worldGen[GEN_TREE].put(willowTree, 8);
		worldGen[GEN_TREE].put(plumTree, 8);
		worldGen[GEN_TREE].put(cherryTree, 8);
		worldGen[GEN_TREE].put(bonsaiTree, 24);
		worldGen[GEN_PLANT].put(flowerGrass, 2);
		worldGen[GEN_PLANT].put(delphinium, -4);
		worldGen[GEN_PLANT].put(cotton, -4);
		worldGen[GEN_PLANT].put(honeysuckle, 32);
    }
	
    @Override
	public int getBiomeFoliageColor(int i, int j, int k)
    {
		return 0x61ba3b;
    }
}
