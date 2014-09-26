package alldemdimensions.item;

import net.minecraft.item.Item;
import alldemdimensions.AllDemDimensions;

public enum EnumToolMaterialADD//can replace with EnumHelper.addToolMaterial?
{
	
	AMBER(2, 150, 5F, 3, 20, "Zenith"),
	SILVER(2, 350, 7F, 2, 9, "Zenith"),
	EMERALD(3, 700, 9F, 3, 16, "Zenith");

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiencyOnProperMaterial;
    private final int damageVsEntity;
    private final int enchantability;
	private final String dimension;

    private EnumToolMaterialADD(int i, int j, float k, int l, int i1, String s)
    {
        harvestLevel = i;
        maxUses = j;
        efficiencyOnProperMaterial = k;
        damageVsEntity = l;
        enchantability = i1;
		dimension = s;
    }
	
    public int getMaxUses()
    {
        return maxUses;
    }

    public float getEfficiencyOnProperMaterial()
    {
        return efficiencyOnProperMaterial;
    }

    public int getDamageVsEntity()
    {
        return damageVsEntity;
    }

    public int getHarvestLevel()
    {
        return harvestLevel;
    }

    public int getEnchantability()
    {
        return enchantability;
    }
	
	public String getDimension()
	{
		return dimension;
	}
	
	public Item getToolCraftingMaterial()
    {
		if(this == AMBER)
		{
			return AllDemDimensions.amber;
		}
		if(this == SILVER)
		{
			return AllDemDimensions.silver;
		}
		if(this == EMERALD)
		{
			return AllDemDimensions.emerald;
		}
		return null;
    }
}
