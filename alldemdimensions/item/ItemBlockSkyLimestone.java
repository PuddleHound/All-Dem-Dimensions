package alldemdimensions.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import alldemdimensions.AllDemDimensions;

public class ItemBlockSkyLimestone extends ItemBlock
{
    public ItemBlockSkyLimestone(Block block)
    {
        super(AllDemDimensions.limestone);
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
		if(metadata == AllDemDimensions.limestone.LIMESTONE_BRICK)
		{
			return "Limestone Brick";
		}
		if(metadata == AllDemDimensions.limestone.LIMESTONE_BRICK_IVIED)
		{
			return "Ivied Limestone Brick";
		}
		if(metadata == AllDemDimensions.limestone.LIMESTONE_BRICK_CRACKED)
		{
			return "Cracked Limestone Brick";
		}
		if(metadata == AllDemDimensions.limestone.LIMESTONE_CHISELED)
		{
			return "Chiseled Limestone";
		}
		if(metadata == AllDemDimensions.limestone.MARBLE)
		{
			return "Marble";
		}
		if(metadata == AllDemDimensions.limestone.MARBLE_BRICK)
		{
			return "Marble Brick";
		}
		if(metadata == AllDemDimensions.limestone.MARBLE_BRICK_IVIED)
		{
			return "Ivied Marble Brick";
		}
		if(metadata == AllDemDimensions.limestone.MARBLE_BRICK_CRACKED)
		{
			return "Cracked Marble Brick";
		}
		if(metadata == AllDemDimensions.limestone.MARBLE_CHISELED)
		{
			return "Chiseled Marble";
		}
		return "Limestone";
    }
}
