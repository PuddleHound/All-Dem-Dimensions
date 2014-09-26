package alldemdimensions.world.environment;

import alldemdimensions.block.AstronomyManager;

public class ConstellationPickaxe extends Constellation
{

	public ConstellationPickaxe(byte b)
	{
		super(b, 5);
		addStar(190F, 250F);
		addStar(185F, 230F);
		addStar(180F, 205F);
		addStar(155F, 243F);
		addStar(210F, 238F);
		
		minX = 155F;
		minZ = 205F;
		maxX = 210F;
		maxZ = 250F;
		name = "Pickaxe";
	}
	
    @Override
	public void onPlanetCrossed(Planet planet)
	{
		if(planet == Planet.hermes)
		{
			AstronomyManager.activateEffect(AstronomyManager.toolSpeed);
		}
	}

}
