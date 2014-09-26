package alldemdimensions.block; import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alldemdimensions.util.TextureLibrary;

public class BlockWaxGrass extends Block
{
    public BlockWaxGrass()
    {
        super(Material.grass);
        setTickRandomly(true);
		setCreativeTab(CreativeTabs.tabBlock);
	}
    
    @Override
	public void updateTick(World world, int i, int j, int k, Random random)
    {
        if (world.isRemote)
        {
            return;
        }
        if (world.getBlockLightValue(i, j + 1, k) < 4 && world.getBlock(i, j + 1, k).getLightOpacity(world, i, j, k) > 2)
        {
            world.setBlock(i, j, k, Blocks.dirt);
        }
        else if (world.getBlockLightValue(i, j + 1, k) >= 9)
        {
            for (int l = 0; l < 4; l++)
            {
                int i1 = (i + random.nextInt(3)) - 1;
                int j1 = (j + random.nextInt(5)) - 3;
                int k1 = (k + random.nextInt(3)) - 1;
                Block l1 = world.getBlock(i1, j1 + 1, k1);
                if (world.getBlock(i1, j1, k1) == Blocks.dirt && world.getBlockLightValue(i1, j1 + 1, k1) >= 4 && l1.getLightOpacity(world, i1, j1 + 1, k1) <= 2)
                {
                    world.setBlock(i1, j1, k1, this);
                }
            }
        }
    }

    @Override
    public Item getItemDropped(int i, Random random, int j)
    {
        return Blocks.dirt.getItemDropped(0, random, j);
    }
	
    @Override
	public IIcon getIcon(int i, int j)
    {
		if(i == 0)
		{
			return Blocks.dirt.getBlockTextureFromSide(0);
		}
		if(i == 1)
		{
			return textureTop;
		}
		return blockIcon;
	}
	
    @Override
	public IIcon getIcon(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
		if(iblockaccess.getBlock(i, j + 1, k) == Blocks.snow && l > 1)
		{
			return Blocks.grass.getIcon(iblockaccess, i, j, k, l);
		} else
		{
			return getIcon(l, iblockaccess.getBlockMetadata(i, j, k));
		}
	}
	
    @Override
    public void registerBlockIcons(IIconRegister iconregister)
    {
        blockIcon = TextureLibrary.getBlockTexture("zenith/waxGrassSide");
        textureTop = TextureLibrary.getBlockTexture("zenith/waxGrassTop");
    }
        
    public IIcon textureTop;
}
