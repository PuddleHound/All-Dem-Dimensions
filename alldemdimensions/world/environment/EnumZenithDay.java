package alldemdimensions.world.environment;

import net.minecraft.world.World;

public enum EnumZenithDay
{
	HIGH_DAY,
	DAY,
	NIGHT;

    private EnumZenithDay()
	{
    }
	
    public static EnumZenithDay getCurrentTime(World world)
	{
		int time = (int)(world.getWorldTime() % 48000);
		if(time >= 6000 && time < 18000)
		{
			return HIGH_DAY;
		}
		if(time >= 30000 && time < 42000)
		{
			return NIGHT;
		}
		return DAY;
	}
}
