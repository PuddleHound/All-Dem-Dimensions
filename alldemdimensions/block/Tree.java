package alldemdimensions.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import alldemdimensions.util.TextureLibrary;
import alldemdimensions.world.biome.BiomeGenMainDimension;
import alldemdimensions.world.gen.WorldGenSkyAncientTree;
import alldemdimensions.world.gen.WorldGenSkyBonsaiTree;
import alldemdimensions.world.gen.WorldGenSkyCherryTree;
import alldemdimensions.world.gen.WorldGenSkyChestnutTree;
import alldemdimensions.world.gen.WorldGenSkyCorkscrewTree;
import alldemdimensions.world.gen.WorldGenSkyCrystalTree;
import alldemdimensions.world.gen.WorldGenSkyHugeTree;
import alldemdimensions.world.gen.WorldGenSkyIceTree;
import alldemdimensions.world.gen.WorldGenSkyJungleTree;
import alldemdimensions.world.gen.WorldGenSkyPlumTree;
import alldemdimensions.world.gen.WorldGenSkyTree;
import alldemdimensions.world.gen.WorldGenSkyWillow;
import alldemdimensions.world.gen.WorldGenTreeBase;


public class Tree
{
	
	public Tree(String s, String s1, Class<? extends WorldGenTreeBase> class1)
	{
		name = s;
		dimension = s1;
		
		logBlock = (BlockSkyLog)(new BlockSkyLog(this).setHardness(2.0F).setStepSound(Block.soundTypeWood).setBlockName(dimension + "/" + name + "Log").setBlockTextureName(dimension + "/log" + name + "Side").setTickRandomly(true).setCreativeTab(CreativeTabs.tabBlock));
		leavesBlock = (BlockSkyLeaves)(new BlockSkyLeaves(this).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName(dimension + "/" + name + "Leaves").setBlockTextureName(dimension + "/leaves" + name + "Fancy").setTickRandomly(true).setCreativeTab(CreativeTabs.tabDecorations));
		saplingBlock = (BlockSkySapling)(new BlockSkySapling(this).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName(dimension + "/" + name + "Sapling").setBlockTextureName(dimension + "/sapling" + name).setTickRandomly(true).setCreativeTab(CreativeTabs.tabDecorations));
		
		try
		{
			generator = class1.newInstance();
		}
		catch(IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch(InstantiationException e1)
		{
			e1.printStackTrace();
		}
		if(generator != null)
		{
			generator.treeType = this;
			generator.logBlock = logBlock;
			generator.leavesBlock = leavesBlock;
		}
		
		treeList.add(this);
	}
	
	public void loadIcons()
	{
		logSideTexture = TextureLibrary.getBlockTexture(dimension + "/log" + name + "Side");
		logTopTexture = TextureLibrary.getBlockTexture(dimension + "/log" + name + "Top");
		leavesFastTexture = TextureLibrary.getBlockTexture(dimension + "/leaves" + name + "Fast");
		leavesFancyTexture = TextureLibrary.getBlockTexture(dimension + "/leaves" + name + "Fancy");
		saplingTexture = TextureLibrary.getBlockTexture(dimension + "/sapling" + name);
	}
	
	public Tree setSaplingDropChance(int chance)
	{
		saplingDropChance = chance;
		return this;
	}
	
	public int getLeavesColorInWorld(IBlockAccess world, int x, int y, int z)
	{
		BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
		boolean biomeColorOverride = false;
		if(biome instanceof BiomeGenMainDimension)
		{
			BiomeGenMainDimension biome1 = (BiomeGenMainDimension)biome;
			if(biome1.overrideFoliageColor)
			{
				biomeColorOverride = true;
			}
		}
		if(!biomeColorOverride && biomeToColor != null)
		{
			Integer color = biomeToColor.get(biome);
			if(color != null)
			{
				return color;
			}
			color = biomeToColor.get(null);
			if(color != null)
			{
				return color;
			}
		}
		if(biomeColorOverride || coloredByBiome)
		{
			int red = 0;
            int green = 0;
            int blue = 0;

            for (int z1 = -1; z1 <= 1; ++z1)
            {
                for (int x1 = -1; x1 <= 1; ++x1)
                {
                    int color = world.getBiomeGenForCoords(x + x1, z + z1).getBiomeFoliageColor(x + x1, y, z + z1);
                    red += (color & 16711680) >> 16;
                    green += (color & 65280) >> 8;
                    blue += color & 255;
                }
            }
            return (red / 9 & 255) << 16 | (green / 9 & 255) << 8 | blue / 9 & 255;
		}
		return 16777215;
	}
	
	public int getLeavesColorInInventory(int metadata)
	{
		if(biomeToColor != null)
		{
			Integer color = biomeToColor.get(null);
			if(color != null)
			{
				return color;
			}
		}
		if(coloredByBiome)
		{
			return ColorizerFoliage.getFoliageColorBasic();
		}
		return 16777215;
	}
	
	public Tree setColoredByBiome(boolean flag)
	{
		coloredByBiome = flag;
		return this;
	}
	
	public Tree setColorPerBiome(BiomeGenBase biome, int color)
	{
		coloredByBiome = true;
		if(biomeToColor == null)
		{
			biomeToColor = new HashMap<BiomeGenBase, Integer>();
		}
		biomeToColor.put(biome, color);
		return this;
	}
	
	public int leaves_quantityDropped(Random random)
	{
		return random.nextInt(saplingDropChance) == 0 ? 1 : 0;
	}
	
	public Item leaves_itemDropped(int metadata, Random random, int fortune)
	{
		return Item.getItemFromBlock(saplingBlock);
	}
	
	public void growTreeFromSapling(World world, int x, int y, int z, Random random)
	{
		if(generator == null)
		{
			return;
		}
        world.setBlockToAir(x, y, z);
        if (!generator.generate(world, random, x, y, z))
        {
            world.setBlock(x, y, z, saplingBlock, world.getBlockMetadata(x, y, z), 2);
        }
	}
	
	public static final ArrayList<Tree> treeList = new ArrayList<Tree>();
	public String name;
	public String dimension;
	public IIcon logSideTexture;
	public IIcon logTopTexture;
	public IIcon leavesFastTexture;
	public IIcon leavesFancyTexture;
	public IIcon saplingTexture;
	public int saplingDropChance = 20;
	public boolean coloredByBiome = true;
	public HashMap<BiomeGenBase, Integer> biomeToColor;
	public BlockSkyLog logBlock;
	public BlockSkyLeaves leavesBlock;
	public BlockSkySapling saplingBlock;
	public WorldGenTreeBase generator;
	
	public static final byte ANCIENT = 0;
	public static final byte ARROW = 1;
	public static final byte BONSAI = 2;
	public static final byte CHERRY = 3;
	
	public static final byte CORKSCREW = 0;
	public static final byte HUGE = 1;
	public static final byte ICE = 2;
	public static final byte JUNGLE = 3;
	
	public static final byte PLUM = 0;
	public static final byte CHESTNUT = 1;
	public static final byte WILLOW = 2;
	
	public static final Tree zenith_ancient = new Tree("Ancient", "zenith", WorldGenSkyAncientTree.class);
	public static final Tree zenith_arrow = new Tree("Arrow", "zenith", WorldGenSkyTree.class).setColorPerBiome(null, 0x00ff00);
	public static final Tree zenith_bonsai = new Tree("Bonsai", "zenith", WorldGenSkyBonsaiTree.class).setColorPerBiome(null/*BiomeGenSkyBase.skylands*/, 0xff00e1);
	public static final Tree zenith_cherry = new Tree("Cherry", "zenith", WorldGenSkyCherryTree.class).setColoredByBiome(false);
	
	@Deprecated public static final Tree zenith_corkscrew = new Tree("Corkscrew", "zenith", WorldGenSkyCorkscrewTree.class);
	public static final Tree zenith_huge = new Tree("Huge", "zenith", WorldGenSkyHugeTree.class);
	public static final Tree zenith_ice = new Tree("Ice", "zenith", WorldGenSkyIceTree.class).setColoredByBiome(false);
	public static final Tree zenith_jungle = new Tree("Jungle", "zenith", WorldGenSkyJungleTree.class);
	
	public static final Tree zenith_plum = new Tree("Plum", "zenith", WorldGenSkyPlumTree.class).setColoredByBiome(false);
	public static final Tree zenith_chestnut = new Tree("Chestnut", "zenith", WorldGenSkyChestnutTree.class).setColorPerBiome(null, 0x00a838);//=dark green, 0xffb600=golden, 0x00ff55=light green
	public static final Tree zenith_willow = new Tree("Willow", "zenith", WorldGenSkyWillow.class);//0x00e5ff
	public static final Tree zenith_crystal = new Tree("Crystal", "zenith", WorldGenSkyCrystalTree.class);
	
}
