package alldemdimensions.item;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Sets;

public class ItemADDPickaxe extends ItemADDTool
{
	
	public ItemADDPickaxe(EnumToolMaterialADD toolmaterial)
    {
        super(2, toolmaterial, blocksEffectiveAgainst);
    }

	@Override
    public boolean func_150897_b(Block block)//canHarvestBlock
    {
        return block == Blocks.obsidian ? this.material.getHarvestLevel() == 3 : (block != Blocks.diamond_block && block != Blocks.diamond_ore ? (block != Blocks.emerald_ore && block != Blocks.emerald_block ? (block != Blocks.gold_block && block != Blocks.gold_ore ? (block != Blocks.iron_block && block != Blocks.iron_ore ? (block != Blocks.lapis_block && block != Blocks.lapis_ore ? (block != Blocks.redstone_ore && block != Blocks.lit_redstone_ore ? (block.getMaterial() == Material.rock ? true : (block.getMaterial() == Material.iron ? true : block.getMaterial() == Material.anvil)) : this.material.getHarvestLevel() >= 2) : this.material.getHarvestLevel() >= 1) : this.material.getHarvestLevel() >= 1) : this.material.getHarvestLevel() >= 2) : this.material.getHarvestLevel() >= 2) : this.material.getHarvestLevel() >= 2);
    }

    @Override
    public float getDigSpeed(ItemStack itemstack, Block block, int meta)
    {
        return block != null && (block.getMaterial() == Material.iron || block.getMaterial() == Material.anvil || block.getMaterial() == Material.rock) ? this.efficiencyOnProperMaterial : super.getDigSpeed(itemstack, block, meta);
    }
	
	public static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[] {Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail});
}
