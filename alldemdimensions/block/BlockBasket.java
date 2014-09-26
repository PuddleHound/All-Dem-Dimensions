package alldemdimensions.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.util.TextureLibrary;

public class BlockBasket extends Block implements IBlockRenderer
{
    public BlockBasket()
    {
        super(Material.wood);
        setBlockBounds(0F, 0F, 0F, 1F, 0.625F, 1F);
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconregister)
    {
        blockIcon = TextureLibrary.getBlockTexture("zenith/basket");
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
    public int getRenderType()
    {
        return AllDemDimensions.blockRenderId;
    }
    
    @Override
    public void render(BlockRenderer br)
    {        
        br.setUseRenderBlocks(true);
        br.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.125F, 0.875F);
        br.cuboid();
        br.setBlockBounds(0.0F, 0.0F, 0.125F, 0.125F, 0.625F, 0.875F);
        br.cuboid();
        br.setBlockBounds(0.875F, 0.0F, 0.125F, 1.0F, 0.625F, 0.875F);
        br.cuboid();
        br.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 0.125F);
        br.cuboid();
        br.setBlockBounds(0.0F, 0.0F, 0.875F, 1.0F, 0.625F, 1.0F);
        br.cuboid();
        //handle
        br.setBlockBounds(0.0F, 0.625F, 0.4375F, 0.125F, 1.0F, 0.5625F);
        br.cuboid();
        br.setBlockBounds(0.875F, 0.625F, 0.4375F, 1.0F, 1.0F, 0.5625F);
        br.cuboid();
        br.setBlockBounds(0.125F, 0.875F, 0.4375F, 0.875F, 1.0F, 0.5625F);
        br.cuboid();
    }
	
}
