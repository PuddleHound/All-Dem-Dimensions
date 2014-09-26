package alldemdimensions.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import alldemdimensions.AllDemDimensions;

public class ItemBlockCrystal extends ItemBlock
{
    public ItemBlockCrystal(Block i)
    {
        super(AllDemDimensions.crystal);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int i)
    {
        return i;
    }

    @Override
    public IIcon getIconFromDamage(int i)
    {
        return AllDemDimensions.crystal.getIcon(0, i);
    }
	
	public String getItemDisplayName(ItemStack itemstack)
    {
            return "Crystal";
    }
}
