package alldemdimensions.world.biome;

public class BiomeGenSkySunSwamp extends BiomeGenSkyBase
{
    
    public BiomeGenSkySunSwamp(int i)
    {
        super(i);
        setSkyColor(0.9F, 1F, 0.4F);
        
		worldGen[GEN_TREE].put(bonsaiTree, 8);
		worldGen[GEN_PLANT].put(flowerGrass, 4);
		worldGen[GEN_PLANT].put(honeysuckle, 96);
        worldGen[GEN_ORE].put(waterfall, 200);
        worldGen[GEN_TREE].put(willowTree, 48);
        worldGen[GEN_MISC].put(pond, -16);
        worldGen[GEN_MISC].put(lotus, -6);
                
        //minMtnHeight = -0.2F;//-2.0F;
        //maxMtnHeight = 1.5F;//1.5F;
        worldGen[GEN_MISC].remove(crystal);
        worldGen[GEN_ORE].remove(honeyfall);//honey and water mixing produces StackOverflowError
        
        sunSize = 100F;
    }
    
    @Override
    public int getSkyColorByTemp(float f)
    {
        return 0xb0ff00;
    }
    
    @Override
	public int getBiomeFoliageColor(int i, int j, int k)
    {
		return 0xb0ff00;
    }
	
    @Override
	public int getBiomeGrassColor(int i, int j, int k)
    {
		return 0xb0ff00;
	}
    
}