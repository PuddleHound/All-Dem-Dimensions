package alldemdimensions.block; import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import alldemdimensions.util.TextureLibrary;

public class BlockBlazestone extends Block
{
    public BlockBlazestone()
    {
        super(Material.rock);
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconregister)
    {
        blockIcon = TextureLibrary.getBlockTexture("nether/blazeBrick");
    }
	
    @Override
    public Item getItemDropped(int i, Random random, int j)
    {
        if(random.nextInt(10) == 0)
        {
            return Items.blaze_powder;
        } else
        {
            return super.getItemDropped(i, random, j);
        }
    }
}
