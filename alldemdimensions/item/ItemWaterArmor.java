package alldemdimensions.item;

import net.minecraft.item.ItemArmor;

public class ItemWaterArmor extends ItemArmor
{
   
    public ItemWaterArmor(int i, EnumArmorMaterialADD enumarmormaterial, int j, int k)
    {
        super(ItemArmor.ArmorMaterial.IRON, j, k);
        material = enumarmormaterial;
        armorType = k;
        renderIndex = j;
        damageReduceAmount = enumarmormaterial.getDamageReductionAmount(k);
        setMaxDamage(enumarmormaterial.getDurability(k));
        maxStackSize = 1;
    }
	
	public float getAddedBrightness()
	{
		if(armorType == 0)
		{
			return material.getAddedBrightness();
		}
		return 0.0F;
	}
	
	public float getAddedClarity()
	{
		if(armorType == 0)
		{
			return material.getAddedClarity();
		}
		return 0.0F;
	}
	
	public int getAirRate()
	{
		return material.getAirRate();
	}
	
	public float getAddedSpeed()
	{
		return material.getAddedSpeed();
	}

    @Override
    public int getItemEnchantability()
    {
        return material.getEnchantability();
    }

    static int[] getMaxDamageArray()
    {
        return maxDamageArray;
    }
    
    private static final int maxDamageArray[] = {11, 16, 15, 13};//needs changed
    public final int armorType;
    public final int damageReduceAmount;
    public final int renderIndex;
    private final EnumArmorMaterialADD material;
}
