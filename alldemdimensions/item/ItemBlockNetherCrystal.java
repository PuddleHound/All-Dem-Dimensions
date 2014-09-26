package alldemdimensions.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import alldemdimensions.AllDemDimensions;

public class ItemBlockNetherCrystal extends ItemBlock
{
    public ItemBlockNetherCrystal(Block block)
    {
        super(AllDemDimensions.netherCrystal);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
	
    @Override
    public int getMetadata(int metadata)
    {
		return metadata;
    }

    @Override
    public IIcon getIconFromDamage(int metadata)
    {
        return field_150939_a.getIcon(2, metadata);
    }

    public String getItemDisplayName(ItemStack itemstack)
    {
        int metadata = itemstack.getItemDamage();
		if(metadata == AllDemDimensions.netherCrystal.REDSTONE)
		{
			return "Redstone Crystal";
		}
		if(metadata == AllDemDimensions.netherCrystal.SULFUR)
		{
			return "Sulfur Crystal";
		}
		if(metadata == AllDemDimensions.netherCrystal.QUICKSILVER)
		{
			return "Quicksilver Crystal";
		}
		if(metadata == AllDemDimensions.netherCrystal.WATER)
		{
			return "Water Crystal";
		}
		if(metadata == AllDemDimensions.netherCrystal.SHADOW)
		{
			return "Shadow Crystal";
		}
		if(metadata == AllDemDimensions.netherCrystal.AIR)
		{
			return "Air Crystal";
		}
		if(metadata == AllDemDimensions.netherCrystal.LAPIS)
		{
			return "Lapis Crystal";
		}
		return "Nether Crystal";
    }
}