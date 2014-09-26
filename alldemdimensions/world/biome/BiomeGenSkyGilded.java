package alldemdimensions.world.biome;

public class BiomeGenSkyGilded extends BiomeGenSkyBase
{
    public BiomeGenSkyGilded(int i)
    {
        super(i);
		worldGen[GEN_TREE].put(chestnutTree, 256);
		worldGen[GEN_TREE].put(arrowTree, 24);
		worldGen[GEN_TREE].put(willowTree, 8);
		worldGen[GEN_TREE].put(plumTree, 8);
		worldGen[GEN_TREE].put(cherryTree, 8);
		worldGen[GEN_TREE].put(bonsaiTree, 24);
		worldGen[GEN_PLANT].put(flowerGrass, 2);
		worldGen[GEN_PLANT].put(delphinium, -4);
		worldGen[GEN_PLANT].put(honeysuckle, 32);
		worldGen[GEN_ORE].put(lavafall, 50);
		worldGen[GEN_MISC].put(lavaPond, -25);
                
        //minMtnHeight = -0.2F;//-2.0F;
		//maxMtnHeight = 1.5F;//1.5F;
        worldGen[GEN_ORE].remove(honeyfall);//honey and water mixing produces StackOverflowError
        worldGen[GEN_ORE].remove(waterfall);
        worldGen[GEN_MISC].remove(pond);
        
        minSnowHeight = 256;
        setSkyColor(1F, 0.8F, 0.5F);
        overrideFoliageColor = true;
    }
    
    @Override 
    public int getSkyColorByTemp(float f)
    {
        return 0xffbb00;
    }
	
    @Override
	public int getBiomeFoliageColor(int i, int j, int k)
    {
		return 0xffaa00;
    }
	
    @Override
	public int getBiomeGrassColor(int i, int j, int k)
    {
		return 0xffaa00;
	}
}
