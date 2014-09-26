package alldemdimensions.block; import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import alldemdimensions.util.TextureLibrary;

public class BlockSkyLimestone extends Block
{
    public BlockSkyLimestone()
    {
        super(Material.rock);
		setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
	public IIcon getIcon(int i, int j)
	{
        if(j == LIMESTONE)
        {
            return TextureLibrary.getBlockTexture("zenith/limestone");
        }
        if(j == LIMESTONE_BRICK)
        {
            return TextureLibrary.getBlockTexture("zenith/limestoneBrick");
        }
        if(j == LIMESTONE_BRICK_IVIED)
        {
            return TextureLibrary.getBlockTexture("zenith/limestoneBrickIvy");
        }
        if(j == LIMESTONE_BRICK_CRACKED)
        {
            return TextureLibrary.getBlockTexture("zenith/limestoneBrickCracked");
        }
        if(j == LIMESTONE_CHISELED)
        {
            return TextureLibrary.getBlockTexture("zenith/limestoneChiseled");
        }
        if(j == MARBLE)
        {
            return TextureLibrary.getBlockTexture("zenith/marble");
        }
        if(j == MARBLE_BRICK)
        {
            return TextureLibrary.getBlockTexture("zenith/marbleBrick");
        }
        if(j == MARBLE_BRICK_IVIED)
        {
            return TextureLibrary.getBlockTexture("zenith/marbleBrickIvy");
        }
        if(j == MARBLE_BRICK_CRACKED)
        {
            return TextureLibrary.getBlockTexture("zenith/marbleBrickCracked");
        }
        if(j == MARBLE_CHISELED)
        {
            return TextureLibrary.getBlockTexture("zenith/marbleChiseled");
        }
        return null;
	}
	
    @Override
	public void getSubBlocks(Item i, CreativeTabs creativetabs, List list)
    {
        list.add(new ItemStack(i, 1, LIMESTONE));
		list.add(new ItemStack(i, 1, LIMESTONE_BRICK));
		list.add(new ItemStack(i, 1, LIMESTONE_BRICK_IVIED));
		list.add(new ItemStack(i, 1, LIMESTONE_BRICK_CRACKED));
		list.add(new ItemStack(i, 1, LIMESTONE_CHISELED));
		list.add(new ItemStack(i, 1, MARBLE));
		list.add(new ItemStack(i, 1, MARBLE_BRICK));
		list.add(new ItemStack(i, 1, MARBLE_BRICK_IVIED));
		list.add(new ItemStack(i, 1, MARBLE_BRICK_CRACKED));
		list.add(new ItemStack(i, 1, MARBLE_CHISELED));
	}
	
    @Override
	public int damageDropped(int i)
    {
        return i;
    }
	
	public static final byte LIMESTONE = 0;
	public static final byte LIMESTONE_BRICK = 1;
	public static final byte LIMESTONE_BRICK_IVIED = 2;
	public static final byte LIMESTONE_BRICK_CRACKED = 3;
	public static final byte LIMESTONE_CHISELED = 4;
	
	public static final byte MARBLE = 8;
	public static final byte MARBLE_BRICK = 9;
	public static final byte MARBLE_BRICK_IVIED = 10;
	public static final byte MARBLE_BRICK_CRACKED = 11;
	public static final byte MARBLE_CHISELED = 12;
}
