package alldemdimensions.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import alldemdimensions.AllDemDimensions;

public class ItemBlockADDSlab extends ItemSlab
{

    public ItemBlockADDSlab(Block block)
    {
        super(block, AllDemDimensions.stoneSlab, AllDemDimensions.stoneSlabDouble, block == AllDemDimensions.stoneSlabDouble);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public IIcon getIconFromDamage(int metadata)
    {
        return field_150939_a.getIcon(2, metadata);
    }

    @Override
    public int getMetadata(int metadata)
    {
        return metadata;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        return "tile.addslab.name";//temporary
    }

}
