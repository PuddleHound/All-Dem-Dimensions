package alldemdimensions.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import alldemdimensions.AllDemDimensions;

public class ItemBlockNetherStone extends ItemBlock
{
    public ItemBlockNetherStone(Block block)
    {
        super(AllDemDimensions.netherStone);
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

    //outdated values
    public String getItemDisplayName(ItemStack itemstack)
    {
        int metadata = itemstack.getItemDamage();
		if(metadata == AllDemDimensions.netherStone.SOULSTONE_BRICK)
		{
			return "Soulstone Brick";
		}
		if(metadata == AllDemDimensions.netherStone.SOULSTONE_BRICK_MOLDY)
		{
			return "Moldy Soulstone Brick";
		}
		if(metadata == AllDemDimensions.netherStone.SOULSTONE_BRICK_CRACKED)
		{
			return "Cracked Soulstone Brick";
		}
		if(metadata == AllDemDimensions.netherStone.SOULSTONE_CHISELED)
		{
			return "Chiseled Soulstone";
		}
		if(metadata == AllDemDimensions.netherStone.BASALT)
		{
			return "Basalt";
		}
		if(metadata == AllDemDimensions.netherStone.BASALT_BRICK)
		{
			return "Basalt Brick";
		}
		if(metadata == AllDemDimensions.netherStone.BASALT_BRICK_MOLDY)
		{
			return "Moldy Basalt Brick";
		}
		if(metadata == AllDemDimensions.netherStone.BASALT_BRICK_CRACKED)
		{
			return "Cracked Basalt Brick";
		}
		if(metadata == AllDemDimensions.netherStone.BASALT_CHISELED)
		{
			return "Chiseled Basalt";
		}
		if(metadata == AllDemDimensions.netherStone.BLAZESTONE_BRICK)
		{
			return "Blazestone Brick";
		}
		if(metadata == AllDemDimensions.netherStone.BLAZESTONE_BRICK_MOLDY)
		{
			return "Moldy Blazestone Brick";
		}
		if(metadata == AllDemDimensions.netherStone.BLAZESTONE_BRICK_CRACKED)
		{
			return "Cracked Blazestone Brick";
		}
		if(metadata == AllDemDimensions.netherStone.BLAZESTONE_CHISELED)
		{
			return "Chiseled Blazestone";
		}
        if(metadata == AllDemDimensions.netherStone.NETHERBRICK_CRACKED)
		{
			return "Cracked Nether Brick";
		}
		if(metadata == AllDemDimensions.netherStone.NETHERBRICK_MOLDY)
		{
			return "Moldy Nether Brick";
		}
		return "Soulstone";
    }
}
