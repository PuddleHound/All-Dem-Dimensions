package alldemdimensions.block;

public class AstronomyEffect
{

	public AstronomyEffect(int i)
	{
		id = i;
		AstronomyManager.addEffect(this);
	}

	public int id;
}
