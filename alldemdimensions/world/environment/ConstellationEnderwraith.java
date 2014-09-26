package alldemdimensions.world.environment;


public class ConstellationEnderwraith extends Constellation
{

	public ConstellationEnderwraith(byte b)
	{
		super(b, 7);
		addStar(140F, 325F);
		addStar(150F, 325F);
		addStar(140F, 315F);
		addStar(150F, 315F);
		addStar(137F, 290F);
		addStar(152F, 290F);
		addStar(145F, 0F, 320F, 1.25F, COLOR_WHITE);

		minX = 137F;
		minZ = 290F;
		maxX = 152F;
		maxZ = 325F;
		name = "Enderwraith";
	}

}
