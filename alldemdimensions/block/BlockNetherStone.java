package alldemdimensions.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import alldemdimensions.util.TextureLibrary;

public class BlockNetherStone extends Block
{
    
    public BlockNetherStone()
    {
        super(Material.rock);
    }
    
    @Override
    public IIcon getIcon(int i, int j)
    {
        if(j == SOULSTONE)
        {
            return TextureLibrary.getBlockTexture("nether/soulstone");
        }
        return TextureLibrary.getBlockTexture("nether/basalt");
    }
    
    //outdated
    public static final byte SOULSTONE = 0;
    public static final byte SOULSTONE_BRICK = 1;
    public static final byte SOULSTONE_BRICK_CRACKED = 2;
    public static final byte SOULSTONE_BRICK_MOLDY = 3;
    public static final byte SOULSTONE_CHISELED = 4;
    public static final byte BASALT = 5;
    public static final byte BASALT_BRICK = 6;
    public static final byte BASALT_BRICK_CRACKED = 7;
    public static final byte BASALT_BRICK_MOLDY = 8;
    public static final byte BASALT_CHISELED = 9;
    //public static final byte BLAZESTONE = 10;
    public static final byte BLAZESTONE_BRICK = 10;
    public static final byte BLAZESTONE_BRICK_CRACKED = 11;
    public static final byte BLAZESTONE_BRICK_MOLDY = 12;
    public static final byte BLAZESTONE_CHISELED = 13;
    public static final byte NETHERBRICK_CRACKED = 14;
    public static final byte NETHERBRICK_MOLDY = 15;
}
