package alldemdimensions.world.environment;


public class ConstellationKnight extends Constellation
{

	public ConstellationKnight(byte b)//broken
	{
		super(b, 15);
		//symbol
		addStar(354F, 0F);
		addStar(353F, 356F);
		addStar(355F, 352F);
		addStar(359F, 356F);
		addStar(349F, 356F);
		//body
		addStar(356F, 343F);
		addStar(355F, 331F);
		//legs
		addStar(341F, 322F);
		addStar(8F, 320F);
		//arm
		addStar(2F, 346F);
		addStar(11F, 344F);
		//arm with sword
		addStar(342F, 345F);
		addStar(332F, 343F);
		//sword
		addStar(328F, 359F);
		addStar(330F, 334F);
		
		minX = 328F;
		minZ = 320F;
		maxX = 11F;
		maxZ = 0F;
		name = "Sky Knight";
	}

}
