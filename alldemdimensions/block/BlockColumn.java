package alldemdimensions.block; 

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import alldemdimensions.util.TextureLibrary;

public class BlockColumn extends Block
{
    public BlockColumn()
    {
        super(Material.rock);
		setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 1.0F, 0.875F);
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconregister)
    {
        blockIcon = TextureLibrary.getBlockTexture("nether/columnBlaze");
    }
    
    @Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
    @Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
	{
		return AxisAlignedBB.getBoundingBox(0.125F, 0.0F, 0.125F, 0.875F, 1.0F, 0.875F);
	}

}
