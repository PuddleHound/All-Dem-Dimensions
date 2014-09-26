package alldemdimensions.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import alldemdimensions.AllDemDimensions;

public class ItemBlockBeehive extends ItemBlock
{
    public ItemBlockBeehive(Block block)
    {
        super(AllDemDimensions.beehive);
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
		if(metadata == AllDemDimensions.beehive.WAX)
		{
			return "Block of Wax";
		}
		if(metadata == AllDemDimensions.beehive.HONEYCOMB)
		{
			return "Honeycomb";
		}
		return "Beehive Block";
    }
}
