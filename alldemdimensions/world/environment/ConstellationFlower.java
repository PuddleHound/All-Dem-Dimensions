package alldemdimensions.world.environment;


public class ConstellationFlower extends Constellation
{

	public ConstellationFlower(byte b)
	{
		super(b, 8);
		addStar(80F, 55F);
		addStar(70F, 65F);
		addStar(90F, 63F);
		addStar(82F, 75F);
		addStar(79F, 90F);
		addStar(83F, 102F);
		addStar(94F, 85F);
		addStar(67F, 83F);
		
		minX = 67F;
		minZ = 55F;
		maxX = 94F;
		maxZ = 102F;
		name = "Flower";
	}

}
