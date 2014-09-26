package alldemdimensions.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import alldemdimensions.block.BlockSkyLeaves;

public class ItemBlockSkyLeaves extends ItemBlock
{
    public ItemBlockSkyLeaves(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
	
    @Override
    public int getMetadata(int meta)
    {
		int metadata = meta | 4;
        return metadata;
    }

    @Override
    public IIcon getIconFromDamage(int metadata)
    {
        return field_150939_a.getIcon(0, metadata);
    }

    @Override
    public int getColorFromItemStack(ItemStack itemstack, int i)
    {
        int metadata = itemstack.getItemDamage();
        return ((BlockSkyLeaves)field_150939_a).treeType.getLeavesColorInInventory(metadata);
    }

}
