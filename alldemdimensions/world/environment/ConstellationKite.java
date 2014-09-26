package alldemdimensions.world.environment;


public class ConstellationKite extends Constellation
{

	public ConstellationKite(byte b)
	{
		super(b, 11);
		addStar(120F, 0F, 70F, 1F, COLOR_RED);
		addStar(130F, 0F, 60F, 1.25F, COLOR_ORANGE);
		addStar(130F, 0F, 80F, 0.5F, COLOR_YELLOW);
		addStar(140F, 0F, 70F, 0.75F, COLOR_BLUE);
		
		addStar(160F, 0F, 70F, 1F, COLOR_RED);
		addStar(155F, 0F, 100F, 1.25F, COLOR_ORANGE);
		addStar(155F, 0F, 40F, 0.5F, COLOR_YELLOW);
		addStar(190F, 0F, 70F, 0.75F, COLOR_BLUE);
		
		addStar(205F, 0F, 85F, 1F, COLOR_RED);
		addStar(210F, 0F, 70F, 1.25F, COLOR_ORANGE);
		addStar(155F, 0F, 55F, 0.5F, COLOR_YELLOW);
		
		minX = 120F;
		minZ = 40F;
		maxX = 205F;
		maxZ = 100F;
		name = "Blue Kite";
	}

}
