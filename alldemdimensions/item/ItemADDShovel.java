package alldemdimensions.item;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import com.google.common.collect.Sets;

public class ItemADDShovel extends ItemADDTool
{
	
	public ItemADDShovel(EnumToolMaterialADD toolmaterial)
    {
        super(1, toolmaterial, blocksEffectiveAgainst);
    }
	
        @Override
	public boolean func_150897_b(Block block)//canHarvestBlock
    {
        return block == Blocks.snow_layer ? true : block == Blocks.snow;
    }
	
    public static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[] {Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow, Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium});
}
