package alldemdimensions.world.biome;

public class BiomeGenSkyCrystal extends BiomeGenSkyBase
{
    public BiomeGenSkyCrystal(int i)
    {
        super(i);
        worldGen[GEN_FIRST].put(crystalCloud, -12);
        worldGen[GEN_MISC].put(crystal, 24);
        worldGen[GEN_LAST].put(beam, 8);
        worldGen[GEN_PLANT].put(flowerGrass, 10);
        worldGen[GEN_TREE].put(chestnutTree, 128);
        worldGen[GEN_TREE].put(crystalTree, 32);
                
        //minMtnHeight = -0.2F;//-2.0F;
		//maxMtnHeight = 1F;//1.5F;
                
        worldGen[GEN_ORE].remove(honeyfall);//honey and water mixing produces StackOverflowError
        
        minSnowHeight = 256;
        setSkyColor(0F, 0.77F, 1F);
        
        terrainNoiseMtn3 *= 9F;
        terrainNoiseMtn1 *= 3F;
        terrainNoiseMtn2 *= 3F;
        minHeight = -15.0F;
		maxHeight = 1.5F;
    }
    
    @Override 
    public int getSkyColorByTemp(float f)
    {
        return 0x000000;
    }
	
    @Override
	public int getBiomeFoliageColor(int i, int j, int k)
    {
		return 0x00ff37;
    }
	
    @Override
	public int getBiomeGrassColor(int i, int j, int k)
    {
		return 0x7fffff;
	}
}