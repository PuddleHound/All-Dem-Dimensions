package alldemdimensions.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;

public class ItemBlockSkyLog extends ItemBlock
{
    public ItemBlockSkyLog(Block block)
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
        return field_150939_a.getIcon(2, metadata);
    }

}
