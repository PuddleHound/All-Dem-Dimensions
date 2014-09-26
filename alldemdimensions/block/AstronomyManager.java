package alldemdimensions.block;

import java.util.ArrayList;
import java.util.Arrays;

public class AstronomyManager
{

	public static final void init()
	{
		activeEffects = new boolean[allEffects.size()];
	}
	
	public static void addEffect(AstronomyEffect effect)
	{
		allEffects.add(effect);
	}
	
	public static boolean isEffectActive(AstronomyEffect effect)
	{
		return activeEffects[effect.id];
	}
	
	public static void activateEffect(AstronomyEffect effect)
	{
		activeEffects[effect.id] = true;
	}
	
	public static void deactivateEffect(AstronomyEffect effect)
	{
		activeEffects[effect.id] = false;
	}
	
	public static void clearAllEffects()
	{
		Arrays.fill(activeEffects, false);
	}
	
	public static float getModifiedToolEfficiency(float f)
	{
		if(isEffectActive(toolSpeed))
		{
			return f += 1.0F;
		}
		return f;
	}
	
	private static ArrayList<AstronomyEffect> allEffects = new ArrayList<AstronomyEffect>();
	private static boolean[] activeEffects;
	public static final AstronomyEffect toolSpeed = new AstronomyEffect(0);
	
}
