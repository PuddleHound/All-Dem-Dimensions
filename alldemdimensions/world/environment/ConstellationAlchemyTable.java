package alldemdimensions.world.environment;


public class ConstellationAlchemyTable extends Constellation
{

	public ConstellationAlchemyTable(byte b)
	{
		super(b, 4);
		addStar(320F, 202F);
		addStar(318F, 227F);
		addStar(345F, 204F);
		addStar(347F, 228F);
		
		minX = 318F;
		minZ = 202F;
		maxX = 347F;
		maxZ = 228F;
		name = "Alchemy Table";
	}

}
