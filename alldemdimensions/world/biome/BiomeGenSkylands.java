package alldemdimensions.world.biome; 

public class BiomeGenSkylands extends BiomeGenSkyBase
{
    public BiomeGenSkylands(int i)
    {
        super(i);
		worldGen[GEN_TREE].put(chestnutTree, 256);
		worldGen[GEN_TREE].put(arrowTree, 24);
		worldGen[GEN_TREE].put(plumTree, 8);
		worldGen[GEN_TREE].put(cherryTree, 8);
		worldGen[GEN_TREE].put(bonsaiTree, 24);
		worldGen[GEN_PLANT].put(flowerGrass, 2);
		worldGen[GEN_PLANT].put(delphinium, -4);
		worldGen[GEN_PLANT].put(cotton, -4);
		worldGen[GEN_PLANT].put(honeysuckle, 32);
		worldGen[GEN_ORE].put(nectarfall, 5);
                
		//minMtnHeight = -0.2F;//-2.0F;
		//maxMtnHeight = 1.5F;//1.5F;
        worldGen[GEN_ORE].remove(honeyfall);//honey and water mixing produces StackOverflowError
                
        setSkyColor(0.7F, 0.8F, 1F);
    }
	
    @Override
	public int getBiomeFoliageColor(int i, int j, int k)
    {
		return 0x61ba3b;
    }
	
    @Override
	public int getBiomeGrassColor(int i, int j, int k)
    {
		return 0x5bc149;
	}
}
