package alldemdimensions.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import alldemdimensions.AllDemDimensions;

public final class AlchemyManager
{

    public static AlchemyManager getInstance()
    {
        return instance;
    }
	
	public static void init()
	{
        //overworld
		addTransmutation(new AlchemyEntry(Blocks.dirt, 0, 1, AlchemyEntry.OVERWORLD, true, false));
		addTransmutation(new AlchemyEntry(Blocks.cobblestone, 0, 4, AlchemyEntry.OVERWORLD, true, false));
		addTransmutation(new AlchemyEntry(Blocks.sand, 0, 3, AlchemyEntry.OVERWORLD, true, false));
		addTransmutation(new AlchemyEntry(Blocks.clay, 0, 16, AlchemyEntry.OVERWORLD, true, false));
		addTransmutation(new AlchemyEntry(Blocks.gravel, 0, 3, AlchemyEntry.OVERWORLD, true, false));
		addTransmutation(new AlchemyEntry(Blocks.sandstone, 0, 16, AlchemyEntry.OVERWORLD, true, false));
		addTransmutation(new AlchemyEntry(Blocks.stone, 0, 8, AlchemyEntry.OVERWORLD, true, false));
		addTransmutation(new AlchemyEntry(Blocks.coal_ore, 0, 40, AlchemyEntry.OVERWORLD, true, false));
		addTransmutation(new AlchemyEntry(Blocks.iron_ore, 0, 128, AlchemyEntry.OVERWORLD, true, false));
		addTransmutation(new AlchemyEntry(Blocks.gold_ore, 0, 224, AlchemyEntry.OVERWORLD, true, false));
		addTransmutation(new AlchemyEntry(Blocks.lapis_ore, 0, 192, AlchemyEntry.OVERWORLD, true, false));
		addTransmutation(new AlchemyEntry(Blocks.redstone_ore, 0, 160, AlchemyEntry.OVERWORLD, true, false));
		addTransmutation(new AlchemyEntry(Blocks.emerald_ore, 0, 512, AlchemyEntry.OVERWORLD, true, false));
        addTransmutation(new AlchemyEntry(Blocks.diamond_ore, 0, 1024, AlchemyEntry.OVERWORLD, true, false).setExclusive());
        //logs, leaves, web, tall grass, wool, flowers, mushrooms, pumpkin, melon, vine, water lily, cactus
        //apple, string, feather, seeds, wheat, pork, snowball, leather, reed, slime ball, egg, fish, bone, sugar, melon item, pumpkin seeds,
            //melon seeds, beef, chicken, rotten flesh, ghast tear, spider eye, magma cream, carrot, potato
        //zenith
		addTransmutation(new AlchemyEntry(AllDemDimensions.limestone, 0, 4, AlchemyEntry.ZENITH, true, false));
		addTransmutation(new AlchemyEntry(AllDemDimensions.limestone, AllDemDimensions.limestone.MARBLE, 8, AlchemyEntry.ZENITH, true, false));
        addTransmutation(new AlchemyEntry(AllDemDimensions.phosphorusOre, 0, 50, AlchemyEntry.ZENITH, true, false));
        addTransmutation(new AlchemyEntry(AllDemDimensions.silverOre, 0, 160, AlchemyEntry.ZENITH, true, false));
        addTransmutation(new AlchemyEntry(AllDemDimensions.emeraldOre, 0, 1024, AlchemyEntry.ZENITH, true, false).setExclusive());
        addTransmutation(new AlchemyEntry(AllDemDimensions.crystal, 0, 224, AlchemyEntry.ZENITH, true, false));
        addTransmutation(new AlchemyEntry(AllDemDimensions.cloud, AllDemDimensions.cloud.DEFAULT, 16, AlchemyEntry.ZENITH, true, false));
        addTransmutation(new AlchemyEntry(AllDemDimensions.cloud, AllDemDimensions.cloud.RAIN, 32, AlchemyEntry.ZENITH, true, false));
        addTransmutation(new AlchemyEntry(AllDemDimensions.cloud, AllDemDimensions.cloud.ICE, 32, AlchemyEntry.ZENITH, true, false));
        addTransmutation(new AlchemyEntry(AllDemDimensions.cloud, AllDemDimensions.cloud.STORM, 128, AlchemyEntry.ZENITH, true, false));
        addTransmutation(new AlchemyEntry(AllDemDimensions.honeysuckle, 0, 4, AlchemyEntry.ZENITH, true, true));
        //nether
		addTransmutation(new AlchemyEntry(Blocks.netherrack, 0, 4, AlchemyEntry.NETHER, true, false));
        addTransmutation(new AlchemyEntry(Blocks.soul_sand, 0, 24, AlchemyEntry.NETHER, true, false));
		addTransmutation(new AlchemyEntry(Blocks.glowstone, 0, 64, AlchemyEntry.NETHER, true, false));
        addTransmutation(new AlchemyEntry(Blocks.obsidian, 0, 256, AlchemyEntry.NETHER, true, false));
        addTransmutation(new AlchemyEntry(Blocks.quartz_ore, 0, 50, AlchemyEntry.NETHER, true, false));
        //ender
        addTransmutation(new AlchemyEntry(Blocks.end_stone, 0, 128, AlchemyEntry.ENDER, true, false));

		
		addTransmutation(new AlchemyEntry(Items.clay_ball, 0, 4, AlchemyEntry.OVERWORLD, false, false));
		addTransmutation(new AlchemyEntry(Items.coal, 0, 40, AlchemyEntry.OVERWORLD, false, false));
		addTransmutation(new AlchemyEntry(Items.iron_ingot, 0, 128, AlchemyEntry.OVERWORLD, false, false));
		addTransmutation(new AlchemyEntry(Items.gold_ingot, 0, 224, AlchemyEntry.OVERWORLD, false, false));
		addTransmutation(new AlchemyEntry(Items.dye, 4, 192, AlchemyEntry.OVERWORLD, false, false));
		addTransmutation(new AlchemyEntry(Items.redstone, 0, 160, AlchemyEntry.OVERWORLD, false, false));
		addTransmutation(new AlchemyEntry(Items.emerald, 0, 512, AlchemyEntry.OVERWORLD, false, false));
		addTransmutation(new AlchemyEntry(Items.gunpowder, 0, 80, AlchemyEntry.OVERWORLD, false, false));
		addTransmutation(new AlchemyEntry(Items.flint, 0, 32, AlchemyEntry.OVERWORLD, false, false));
		addTransmutation(new AlchemyEntry(Items.ender_pearl, 0, 512, AlchemyEntry.ENDER, false, false));
		addTransmutation(new AlchemyEntry(Items.blaze_rod, 0, 256, AlchemyEntry.NETHER, false, false));
		addTransmutation(new AlchemyEntry(Items.glowstone_dust, 0, 16, AlchemyEntry.NETHER, false, false));
		addTransmutation(new AlchemyEntry(Items.blaze_powder, 0, 128, AlchemyEntry.NETHER, false, false));

	/*
		addTransmutation(Blocks.sapling), 0, 6, AlchemyEntry.OVERWORLD, true, true, 1);
		addTransmutation(Blocks.wood), 0, 2, AlchemyEntry.OVERWORLD, true, true, 1);
		addTransmutation(Blocks.wood), 1, 8, AlchemyEntry.OVERWORLD, true, true, 4);
		addTransmutation(Blocks.wood), 2, 8, AlchemyEntry.OVERWORLD, true, true, 4);
		addTransmutation(Blocks.wood), 3, 8, AlchemyEntry.OVERWORLD, true, true, 4);
	*/
	
		addReconstruction(new ItemStack[]{new ItemStack(Items.stick, 4, 0)}, new ItemStack(Blocks.planks, 1, 0));
		addReconstruction(new ItemStack[]{new ItemStack(Blocks.cobblestone_wall, 2, 0)}, new ItemStack(Blocks.cobblestone, 1, 0));
		addReconstruction(new ItemStack[]{new ItemStack(Blocks.cobblestone_wall, 2, 1)}, new ItemStack(Blocks.mossy_cobblestone, 1, 0));
		addReconstruction(new ItemStack[]{new ItemStack(Blocks.glass_pane, 3, 0)}, new ItemStack(Blocks.glass, 1, 0));
		addReconstruction(new ItemStack[]{new ItemStack(Blocks.iron_bars, 3, 0)}, new ItemStack(Items.iron_ingot, 1, 0));
		for(byte metadata = 0; metadata < 4; metadata++)
		{
			addReconstruction(new ItemStack[]{new ItemStack(Blocks.planks, 4, metadata)}, new ItemStack(Blocks.log, 1, metadata));
		}
		addReconstruction(new ItemStack[]{new ItemStack(Items.dye, 3, 0)}, new ItemStack(Items.bone, 1, 0));
		
		//slabs and stairs
	
		addDeconstruction(new ItemStack(Blocks.chest, 1, 0), new ItemStack[]{new ItemStack(Blocks.planks, 8, 0)});
		addDeconstruction(new ItemStack(Blocks.bookshelf, 1, 0), new ItemStack[]{new ItemStack(Blocks.planks, 6, 0), new ItemStack(Items.book, 3, 0) });
		addDeconstruction(new ItemStack(Blocks.brick_block, 1, 0), new ItemStack[]{new ItemStack(Items.brick, 4, 0) });
		addDeconstruction(new ItemStack(Blocks.fence, 1, 0), new ItemStack[]{new ItemStack(Items.stick, 3, 0) });
		addDeconstruction(new ItemStack(Items.flower_pot, 1, 0), new ItemStack[]{new ItemStack(Items.brick, 3, 0) });
		addDeconstruction(new ItemStack(Blocks.ladder, 1, 0), new ItemStack[]{new ItemStack(Items.stick, 2, 0) });
		addDeconstruction(new ItemStack(Blocks.anvil, 1, 0), new ItemStack[]{new ItemStack(Blocks.iron_block, 3, 0), new ItemStack(Items.iron_ingot, 4, 0) });
		addDeconstruction(new ItemStack(Blocks.beacon, 1, 0), new ItemStack[]{new ItemStack(Blocks.obsidian, 3, 0), new ItemStack(Blocks.glass, 5, 0), new ItemStack(Items.nether_star, 1, 0) });
		addDeconstruction(new ItemStack(Items.bed, 1, 0), new ItemStack[]{new ItemStack(Blocks.planks, 3, 0), new ItemStack(Blocks.wool, 3, 0) });
		addDeconstruction(new ItemStack(Items.brewing_stand, 1, 0), new ItemStack[]{new ItemStack(Blocks.cobblestone, 3, 0), new ItemStack(Items.blaze_rod, 1, 0) });
		addDeconstruction(new ItemStack(Blocks.crafting_table, 1, 0), new ItemStack[]{new ItemStack(Blocks.planks, 4, 0) });
		addDeconstruction(new ItemStack(Blocks.anvil, 1, 0), new ItemStack[]{new ItemStack(Blocks.iron_block, 3, 0), new ItemStack(Items.iron_ingot, 4, 0) });
		addDeconstruction(new ItemStack(Blocks.ender_chest, 1, 0), new ItemStack[]{new ItemStack(Blocks.obsidian, 8, 0), new ItemStack(Items.ender_pearl, 1, 0) });
		addDeconstruction(new ItemStack(Blocks.enchanting_table, 1, 0), new ItemStack[]{new ItemStack(Blocks.obsidian, 4, 0), new ItemStack(Items.diamond, 2, 0), new ItemStack(Items.book, 1, 0) });
		addDeconstruction(new ItemStack(Blocks.tnt, 1, 0), new ItemStack[]{new ItemStack(Blocks.sand, 4, 0), new ItemStack(Items.gunpowder, 5, 0) });
		addDeconstruction(new ItemStack(Blocks.jukebox, 1, 0), new ItemStack[]{new ItemStack(Blocks.planks, 8, 0), new ItemStack(Items.diamond, 1, 0) });
		addDeconstruction(new ItemStack(Blocks.furnace, 1, 0), new ItemStack[]{new ItemStack(Blocks.cobblestone, 8, 0) });
		addDeconstruction(new ItemStack(Blocks.dispenser, 1, 0), new ItemStack[]{new ItemStack(Blocks.cobblestone, 7, 0), new ItemStack(Items.redstone, 1, 0), new ItemStack(Items.bow, 1, 0) });
		addDeconstruction(new ItemStack(Blocks.fence_gate, 1, 0), new ItemStack[]{new ItemStack(Blocks.planks, 2, 0), new ItemStack(Items.stick, 4, 0) });
		addDeconstruction(new ItemStack(Items.wooden_door, 1, 0), new ItemStack[]{new ItemStack(Blocks.planks, 6, 0) });
		addDeconstruction(new ItemStack(Items.iron_door, 1, 0), new ItemStack[]{new ItemStack(Items.iron_ingot, 6, 0) });
		addDeconstruction(new ItemStack(Blocks.lever, 1, 0), new ItemStack[]{new ItemStack(Blocks.cobblestone, 1, 0), new ItemStack(Items.stick, 1, 0) });
		addDeconstruction(new ItemStack(Blocks.noteblock, 1, 0), new ItemStack[]{new ItemStack(Blocks.planks, 8, 0), new ItemStack(Items.redstone, 1, 0) });
		addDeconstruction(new ItemStack(Blocks.piston, 1, 0), new ItemStack[]{new ItemStack(Blocks.cobblestone, 4, 0), new ItemStack(Blocks.planks, 3, 0), new ItemStack(Items.iron_ingot, 1, 0), new ItemStack(Items.redstone, 1, 0) });
		addDeconstruction(new ItemStack(Blocks.sticky_piston, 1, 0), new ItemStack[]{new ItemStack(Blocks.piston, 1, 0), new ItemStack(Items.slime_ball, 1, 0) });
		addDeconstruction(new ItemStack(Blocks.rail, 16, 0), new ItemStack[]{new ItemStack(Items.iron_ingot, 6, 0), new ItemStack(Items.stick, 1, 0) });
		addDeconstruction(new ItemStack(Blocks.golden_rail, 6, 0), new ItemStack[]{new ItemStack(Items.gold_ingot, 6, 0), new ItemStack(Items.stick, 1, 0), new ItemStack(Items.redstone, 1, 0) });
		addDeconstruction(new ItemStack(Blocks.detector_rail, 6, 0), new ItemStack[]{new ItemStack(Items.iron_ingot, 6, 0), new ItemStack(Blocks.stone_pressure_plate, 1, 0), new ItemStack(Items.redstone, 1, 0) });
		addDeconstruction(new ItemStack(Blocks.redstone_torch, 1, 0), new ItemStack[]{new ItemStack(Items.redstone, 1, 0), new ItemStack(Items.stick, 1, 0) });
		addDeconstruction(new ItemStack(Items.repeater, 1, 0), new ItemStack[]{new ItemStack(Blocks.stone, 3, 0), new ItemStack(Blocks.redstone_torch, 2, 0), new ItemStack(Items.redstone, 1, 0) });
		addDeconstruction(new ItemStack(Blocks.redstone_lamp, 1, 0), new ItemStack[]{new ItemStack(Blocks.glowstone, 1, 0), new ItemStack(Items.redstone, 4, 0) });
		addDeconstruction(new ItemStack(Blocks.nether_brick_fence, 1, 0), new ItemStack[]{new ItemStack(Blocks.nether_brick, 1, 0) });
		addDeconstruction(new ItemStack(Blocks.gravel, 10, 0), new ItemStack[]{new ItemStack(Items.flint, 1, 0) });
		addDeconstruction(new ItemStack(Items.brick, 1, 0), new ItemStack[]{new ItemStack(Items.clay_ball, 1, 0) });
		addDeconstruction(new ItemStack(Items.ender_eye, 1, 0), new ItemStack[]{new ItemStack(Items.ender_pearl, 1, 0), new ItemStack(Items.blaze_powder, 1, 0) });
		addDeconstruction(new ItemStack(Items.book, 1, 0), new ItemStack[]{new ItemStack(Items.paper, 3, 0), new ItemStack(Items.leather, 1, 0) });
		addDeconstruction(new ItemStack(Items.writable_book, 1, 0), new ItemStack[]{new ItemStack(Items.book, 1, 0), new ItemStack(Items.feather, 1, 0), new ItemStack(Items.dye, 1, 15) });
		addDeconstruction(new ItemStack(Items.speckled_melon, 1, 0), new ItemStack[]{new ItemStack(Items.melon, 1, 0), new ItemStack(Items.gold_nugget, 1, 0) });
		addDeconstruction(new ItemStack(Items.magma_cream, 1, 0), new ItemStack[]{new ItemStack(Items.blaze_powder, 1, 0), new ItemStack(Items.slime_ball, 1, 0) });
		addDeconstruction(new ItemStack(Items.bucket, 1, 0), new ItemStack[]{new ItemStack(Items.iron_ingot, 3, 0) });
		addDeconstruction(new ItemStack(Items.flint_and_steel, 1, 0), new ItemStack[]{new ItemStack(Items.iron_ingot, 1, 0), new ItemStack(Items.flint, 1, 0) });
		addDeconstruction(new ItemStack(Items.fishing_rod, 1, 0), new ItemStack[]{new ItemStack(Items.stick, 3, 0), new ItemStack(Items.string, 2, 0) });
		addDeconstruction(new ItemStack(Items.shears, 1, 0), new ItemStack[]{new ItemStack(Items.iron_ingot, 2, 0) });
		addDeconstruction(new ItemStack(Items.clock, 1, 0), new ItemStack[]{new ItemStack(Items.gold_ingot, 4, 0), new ItemStack(Items.redstone, 1, 0) });
		addDeconstruction(new ItemStack(Items.compass, 1, 0), new ItemStack[]{new ItemStack(Items.gold_ingot, 1, 0), new ItemStack(Items.redstone, 1, 0) });
		addDeconstruction(new ItemStack(Items.bow, 1, 0), new ItemStack[]{new ItemStack(Items.string, 3, 0), new ItemStack(Items.stick, 3, 0)});
		addDeconstruction(new ItemStack(Items.fire_charge, 3, 0), new ItemStack[]{new ItemStack(Items.coal, 1, 0), new ItemStack(Items.blaze_powder, 1, 0), new ItemStack(Items.gunpowder, 1, 0) });
		addDeconstruction(new ItemStack(Items.painting, 1, 0), new ItemStack[]{new ItemStack(Items.stick, 8, 0), new ItemStack(Blocks.wool, 1, 0) });
		addDeconstruction(new ItemStack(Items.boat, 1, 0), new ItemStack[]{new ItemStack(Blocks.planks, 5, 0) });
		addDeconstruction(new ItemStack(Items.minecart, 1, 0), new ItemStack[]{new ItemStack(Items.iron_ingot, 5, 0) });
		addDeconstruction(new ItemStack(Items.chest_minecart, 1, 0), new ItemStack[]{new ItemStack(Items.minecart, 1, 0), new ItemStack(Blocks.chest, 1, 0) });
		addDeconstruction(new ItemStack(Items.furnace_minecart, 1, 0), new ItemStack[]{new ItemStack(Items.minecart, 1, 0), new ItemStack(Blocks.furnace, 1, 0) });		
		addDeconstruction(new ItemStack(Items.iron_pickaxe, 1, 0), new ItemStack[]{new ItemStack(Items.iron_ingot, 2, 0), new ItemStack(Items.stick, 2, 0)});
		//more tools, swords, armor
		
		for(Block block : plantBlocks)
		{
			addCombination(new ItemStack[]{new ItemStack(Blocks.cobblestone, 1, 0), new ItemStack(block, 1, 0)}, new ItemStack(Blocks.mossy_cobblestone, 1, 0));
			addCombination(new ItemStack[]{new ItemStack(Blocks.stonebrick, 1, 0), new ItemStack(block, 1, 0)}, new ItemStack(Blocks.stonebrick, 1, 2));
			addCombination(new ItemStack[]{new ItemStack(Blocks.dirt, 1, 0), new ItemStack(block, 1, 0)}, new ItemStack(Blocks.grass, 1, 0));
		}
		for(Item item : plantItems)
		{
			addCombination(new ItemStack[]{new ItemStack(Blocks.cobblestone, 1, 0), new ItemStack(item, 1, 0)}, new ItemStack(Blocks.mossy_cobblestone, 1, 0));
			addCombination(new ItemStack[]{new ItemStack(Blocks.stonebrick, 1, 0), new ItemStack(item, 1, 0)}, new ItemStack(Blocks.stonebrick, 1, 2));
			addCombination(new ItemStack[]{new ItemStack(Blocks.dirt, 1, 0), new ItemStack(item, 1, 0)}, new ItemStack(Blocks.grass, 1, 0));
		}
		addCombination(new ItemStack[]{new ItemStack(Items.water_bucket, 1, 0), new ItemStack(Blocks.snow, 1, 0)}, new ItemStack(Blocks.ice, 1, 0));
		addCombination(new ItemStack[]{new ItemStack(Blocks.dirt, 1, 0), new ItemStack(Blocks.red_mushroom, 1, 0)}, new ItemStack(Blocks.mycelium, 1, 0));
		addCombination(new ItemStack[]{new ItemStack(Blocks.dirt, 1, 0), new ItemStack(Blocks.brown_mushroom, 1, 0)}, new ItemStack(Blocks.mycelium, 1, 0));
		addCombination(new ItemStack[]{new ItemStack(Blocks.dirt, 1, 0), new ItemStack(Items.water_bucket, 1, 0)}, new ItemStack(Blocks.soul_sand, 1, 0));
		
		//TOOLS
		//iron to diamond
		addCombination(new ItemStack[]{new ItemStack(Items.diamond, 2, 0), new ItemStack(Items.iron_pickaxe, 1, 0)}, new ItemStack(Items.diamond_pickaxe, 1, 520));
		addCombination(new ItemStack[]{new ItemStack(Items.diamond, 1, 0), new ItemStack(Items.iron_pickaxe, 1, 0)}, new ItemStack(Items.diamond_pickaxe, 1, 1051));
		addCombination(new ItemStack[]{new ItemStack(Items.diamond, 2, 0), new ItemStack(Items.iron_shovel, 1, 0)}, new ItemStack(Items.diamond_shovel, 1, 520));
		addCombination(new ItemStack[]{new ItemStack(Items.diamond, 1, 0), new ItemStack(Items.iron_shovel, 1, 0)}, new ItemStack(Items.diamond_shovel, 1, 1051));
		addCombination(new ItemStack[]{new ItemStack(Items.diamond, 2, 0), new ItemStack(Items.iron_axe, 1, 0)}, new ItemStack(Items.diamond_axe, 1, 520));
		addCombination(new ItemStack[]{new ItemStack(Items.diamond, 1, 0), new ItemStack(Items.iron_axe, 1, 0)}, new ItemStack(Items.diamond_axe, 1, 1051));
		addCombination(new ItemStack[]{new ItemStack(Items.diamond, 2, 0), new ItemStack(Items.iron_sword, 1, 0)}, new ItemStack(Items.diamond_sword, 1, 520));
		addCombination(new ItemStack[]{new ItemStack(Items.diamond, 1, 0), new ItemStack(Items.iron_sword, 1, 0)}, new ItemStack(Items.diamond_sword, 1, 1051));
		//stone to iron
		addCombination(new ItemStack[]{new ItemStack(Items.iron_ingot, 2, 0), new ItemStack(Items.stone_pickaxe, 1, 0)}, new ItemStack(Items.iron_pickaxe, 1, 83));
		addCombination(new ItemStack[]{new ItemStack(Items.iron_ingot, 1, 0), new ItemStack(Items.stone_pickaxe, 1, 0)}, new ItemStack(Items.iron_pickaxe, 1, 167));
		addCombination(new ItemStack[]{new ItemStack(Items.iron_ingot, 2, 0), new ItemStack(Items.stone_shovel, 1, 0)}, new ItemStack(Items.iron_shovel, 1, 83));
		addCombination(new ItemStack[]{new ItemStack(Items.iron_ingot, 1, 0), new ItemStack(Items.stone_shovel, 1, 0)}, new ItemStack(Items.iron_shovel, 1, 167));
		addCombination(new ItemStack[]{new ItemStack(Items.iron_ingot, 2, 0), new ItemStack(Items.stone_axe, 1, 0)}, new ItemStack(Items.iron_axe, 1, 83));
		addCombination(new ItemStack[]{new ItemStack(Items.iron_ingot, 1, 0), new ItemStack(Items.stone_axe, 1, 0)}, new ItemStack(Items.iron_axe, 1, 167));
		addCombination(new ItemStack[]{new ItemStack(Items.iron_ingot, 2, 0), new ItemStack(Items.stone_sword, 1, 0)}, new ItemStack(Items.iron_sword, 1, 83));
		addCombination(new ItemStack[]{new ItemStack(Items.iron_ingot, 1, 0), new ItemStack(Items.stone_sword, 1, 0)}, new ItemStack(Items.iron_sword, 1, 167));
		//stone to gold
		addCombination(new ItemStack[]{new ItemStack(Items.gold_ingot, 2, 0), new ItemStack(Items.stone_pickaxe, 1, 0)}, new ItemStack(Items.golden_pickaxe, 1, 11));
		addCombination(new ItemStack[]{new ItemStack(Items.gold_ingot, 1, 0), new ItemStack(Items.stone_pickaxe, 1, 0)}, new ItemStack(Items.golden_pickaxe, 1, 21));
		addCombination(new ItemStack[]{new ItemStack(Items.gold_ingot, 2, 0), new ItemStack(Items.stone_shovel, 1, 0)}, new ItemStack(Items.golden_shovel, 1, 11));
		addCombination(new ItemStack[]{new ItemStack(Items.gold_ingot, 1, 0), new ItemStack(Items.stone_shovel, 1, 0)}, new ItemStack(Items.golden_shovel, 1, 21));
		addCombination(new ItemStack[]{new ItemStack(Items.gold_ingot, 2, 0), new ItemStack(Items.stone_axe, 1, 0)}, new ItemStack(Items.golden_axe, 1, 11));
		addCombination(new ItemStack[]{new ItemStack(Items.gold_ingot, 1, 0), new ItemStack(Items.stone_axe, 1, 0)}, new ItemStack(Items.golden_axe, 1, 21));
		addCombination(new ItemStack[]{new ItemStack(Items.gold_ingot, 2, 0), new ItemStack(Items.stone_sword, 1, 0)}, new ItemStack(Items.golden_sword, 1, 11));
		addCombination(new ItemStack[]{new ItemStack(Items.gold_ingot, 1, 0), new ItemStack(Items.stone_sword, 1, 0)}, new ItemStack(Items.golden_sword, 1, 21));
		//wood to stone
		for(Block block : stoneBlocks)
		{
			addCombination(new ItemStack[]{new ItemStack(block, 2, 0), new ItemStack(Items.wooden_pickaxe, 1, 0)}, new ItemStack(Items.stone_pickaxe, 1, 44));
			addCombination(new ItemStack[]{new ItemStack(block, 1, 0), new ItemStack(Items.wooden_pickaxe, 1, 0)}, new ItemStack(Items.stone_pickaxe, 1, 87));
			addCombination(new ItemStack[]{new ItemStack(block, 2, 0), new ItemStack(Items.wooden_shovel, 1, 0)}, new ItemStack(Items.stone_shovel, 1, 44));
			addCombination(new ItemStack[]{new ItemStack(block, 1, 0), new ItemStack(Items.wooden_shovel, 1, 0)}, new ItemStack(Items.stone_shovel, 1, 87));
			addCombination(new ItemStack[]{new ItemStack(block, 2, 0), new ItemStack(Items.wooden_axe, 1, 0)}, new ItemStack(Items.stone_axe, 1, 44));
			addCombination(new ItemStack[]{new ItemStack(block, 1, 0), new ItemStack(Items.wooden_axe, 1, 0)}, new ItemStack(Items.stone_axe, 1, 87));
			addCombination(new ItemStack[]{new ItemStack(block, 2, 0), new ItemStack(Items.wooden_sword, 1, 0)}, new ItemStack(Items.stone_sword, 1, 44));
			addCombination(new ItemStack[]{new ItemStack(block, 1, 0), new ItemStack(Items.wooden_sword, 1, 0)}, new ItemStack(Items.stone_sword, 1, 87));
		}
	}
    
    public static void addTransmutation(AlchemyEntry entry)
    {
            transmutationResults.add(entry);
    }
	
	public static ArrayList<AlchemyEntry> getAllTransmutation()
	{
		return transmutationResults;
	}
	
	public static AlchemyEntry getTransmutationFromIdAndMetadata(Object id, int meta)//id can be block or item
	{
		for(AlchemyEntry entry : transmutationResults)
		{
			if(entry.itemId == id && entry.itemMeta == meta)
			{
				return entry;
			}
		}
		return null;
	}
	
	public static void addReconstruction(ItemStack[] ingredients, ItemStack result)
	{
		LinkedList<ItemStack> list = sortRecipe(ingredients);
		if(list != null)
		{
			reconstruction.put(list, result);
		}
	}
	
	public static HashMap<LinkedList<ItemStack>, ItemStack> getAllReconstruction()
	{
		return reconstruction;
	}
	
	public static LinkedList<ItemStack> sortRecipe(ItemStack[] ingredients)
	{
		LinkedList<ItemStack> list = new LinkedList<ItemStack>();
		ItemStack itemstack;
		ItemStack itemstack1;
		for(byte b = 0; b < ingredients.length; b++)
		{
			if(ingredients[b] != null)
			{
				for(byte b1 = 0; b1 < ingredients[b].stackSize; b1++)
				{
					itemstack = ingredients[b].copy();
					itemstack.stackSize = 1;
					list.add(itemstack);
				}
			}
		}
		for(byte b = 0; b < list.size(); b++)
		{
			itemstack = list.get(b);
			byte b1 = b;
			while(b1 > 0)
			{
				itemstack1 = list.get(b1 - 1);
				//if(itemstack.itemID < itemstack1.itemID)//??? 1.7.2
				if(Item.getIdFromItem(itemstack.getItem()) < Item.getIdFromItem(itemstack1.getItem()))
				{
					list.remove(itemstack);
					list.add(b1 - 1, itemstack);
					b1--;
				} else
				{
					b1 = -1;//break;
				}
			}
		}
		for(byte b = 0; b < list.size(); b++)
		{
			itemstack = list.get(b);
			byte b1 = b;
			while(b1 > 0)
			{
				itemstack1 = list.get(b1 - 1);
				if(itemstack.getItem() == itemstack1.getItem() && itemstack.getItemDamage() < itemstack1.getItemDamage())
				{
					list.remove(itemstack);
					list.add(b1 - 1, itemstack);
					b1--;
				} else
				{
					b1 = -1;//break;
				}
			}
		}
		return list;
	}
	
	public static void addDeconstruction(ItemStack ingredient, ItemStack[] results)
	{
		deconstruction.put(ingredient, results);
	}
	
	public static HashMap<ItemStack, ItemStack[]> getAllDeconstruction()
	{
		return deconstruction;
	}
	
	public static void addCombination(ItemStack[] ingredients, ItemStack result)
	{
		LinkedList<ItemStack> list = sortRecipe(ingredients);
		if(list != null)
		{
			combination.put(list, result);
		}
	}
	
	public static HashMap<LinkedList<ItemStack>, ItemStack> getAllCombination()
	{
		return combination;
	}
	
    private static final AlchemyManager instance = new AlchemyManager();
	private static ArrayList<AlchemyEntry> transmutationResults = new ArrayList<AlchemyEntry>();
	private static HashMap<LinkedList<ItemStack>, ItemStack> reconstruction = new HashMap<LinkedList<ItemStack>, ItemStack>();
	private static HashMap<ItemStack, ItemStack[]> deconstruction =  new HashMap<ItemStack, ItemStack[]>();
	private static HashMap<LinkedList<ItemStack>, ItemStack> combination = new HashMap<LinkedList<ItemStack>, ItemStack>();
	public static final Block[] stoneBlocks = new Block[]{Blocks.cobblestone, AllDemDimensions.limestone};
	public static final Block[] plantBlocks = new Block[]{Blocks.cactus, Blocks.leaves, Blocks.sapling, Blocks.tallgrass, Blocks.waterlily, Blocks.vine, Blocks.red_flower, Blocks.yellow_flower,
		AllDemDimensions.honeysuckle, /*AllDemDimensions.skyPlant, AllDemDimensions.skyLeaves0, AllDemDimensions.skyLeaves1, AllDemDimensions.skyLeaves2, AllDemDimensions.skySapling0,
		AllDemDimensions.skySapling1, AllDemDimensions.skySapling2*/};//1.7.2
	public static final Item[] plantItems = new Item[]{Items.wheat_seeds, Items.reeds, Items.wheat};
}
