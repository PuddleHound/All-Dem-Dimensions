package alldemdimensions.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemPaintBottle extends Item
{
    public ItemPaintBottle()
    {
        super();
		setMaxStackSize(1);
		setMaxDamage(1000);
    }

    @Override
	public int getColorFromItemStack(ItemStack itemstack, int i)
    {
		if(itemstack.hasTagCompound())
		{
			int color = itemstack.getTagCompound().getInteger("paintColor");
			if(color != 0)
			{
				int red = color >> 16 & 255;
				int green = color >> 8 & 255;
				int blue = color & 255;
				return red << 16 | green << 8 | blue;
			}
		}
		return super.getColorFromItemStack(itemstack, i);
    }
}
