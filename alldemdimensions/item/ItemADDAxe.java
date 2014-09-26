package alldemdimensions.item; 

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Sets;

public class ItemADDAxe extends ItemADDTool
{
	
	public ItemADDAxe(EnumToolMaterialADD toolmaterial)
    {
        super(3, toolmaterial, blocksEffectiveAgainst);
    }
	
    @Override
	public float getDigSpeed(ItemStack itemstack, Block block, int meta)
    {
        return block != null && (block.getMaterial() == Material.wood || block.getMaterial() == Material.plants || block.getMaterial() == Material.vine) ? this.efficiencyOnProperMaterial : super.getDigSpeed(itemstack, block, meta);
    }
	
    public static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[] {Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.chest, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.pumpkin, Blocks.lit_pumpkin});
}
