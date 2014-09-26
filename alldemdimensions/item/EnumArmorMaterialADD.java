package alldemdimensions.item; 

public enum EnumArmorMaterialADD
{
    RUBBER(5, new int[] {
        1, 3, 2, 1
    }, 15, 2.0F, 0.3F, 10, 0.03F),
    COPPER(15, new int[] {
        2, 6, 5, 2
    }, 9, 3.0F, 0.2F, 30, 0.5F),
    AQUAMARINE(7, new int[] {
        2, 5, 3, 1
    }, 25, 1.0F, 0.05F, 250, 0.05F),
    SAPPHIRE(33, new int[] {
        3, 8, 6, 3
    }, 10, 4.0F, 0.1F, 100, 0.07F);

    private int maxDamageFactor;
    private int damageReductionAmountArray[];
    private int enchantability;
	private float brightness;
	private float clarity;
	private int airRate;
	private float addedSpeed;

    private EnumArmorMaterialADD(int i, int intArray[], int j, float f, float f1, int k, float f2)
    {
        maxDamageFactor = i;
        damageReductionAmountArray = intArray;
        enchantability = j;
		brightness = f;
		clarity = f1;
		airRate = k;
		addedSpeed = f2;
    }
	
	public float getAddedBrightness()
	{
		return brightness;
	}
	
	public float getAddedClarity()
	{
		return clarity;
	}
	
	public int getAirRate()
	{
		return airRate;
	}
	
	public float getAddedSpeed()
	{
		return addedSpeed;
	}

    public int getDurability(int i)
    {
		return 500;//replace
        ///return AllDemDimensions.getArmorMaxDamage(i) * maxDamageFactor;
		//ItemArmor.getMaxDamageArray()[i]
    }

    public int getDamageReductionAmount(int i)
    {
        return damageReductionAmountArray[i];
    }

    public int getEnchantability()
    {
        return enchantability;
    }
}
