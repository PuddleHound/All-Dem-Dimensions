package alldemdimensions.block; import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.util.TextureLibrary;

public class BlockThermal extends Block
{
    public BlockThermal()
    {
        super(Material.air);
        setCreativeTab(CreativeTabs.tabBlock);
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconregister)
    {
        blockIcon = TextureLibrary.getBlockTexture("transparent");
    }
	
    @Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
    @Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
	{
		return null;
	}
	
    @Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
		entity.motionY = 2.0D;
		entity.motionX = world.rand.nextDouble();
		entity.motionZ = world.rand.nextDouble();
	}
	
    @Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        for (int i = 0; i < 4; i++)
        {
            double d = (float)x + random.nextFloat();
            double d1 = (float)y + random.nextFloat();
            double d2 = (float)z + random.nextFloat();
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            int j = random.nextInt(2) * 2 - 1;
            d3 = ((double)random.nextFloat() - 0.5D) * 0.2D;
            d4 = ((double)random.nextFloat() - 0.5D) * 2.0D;
            d5 = ((double)random.nextFloat() - 0.5D) * 0.2D;

            if (world.getBlock(x - 1, y, z) == this || world.getBlock(x + 1, y, z) == this)
            {
                d2 = (double)z + 0.5D + 0.25D * (double)j;
                d5 = random.nextFloat() * 2.0F * (float)j;
            }
            else
            {
                d = (double)x + 0.5D + 0.25D * (double)j;
                d3 = random.nextFloat() * 2.0F * (float)j;
            }

            AllDemDimensions.proxyInstance.spawnPortalParticle("thermal", world, 0.8F, 0.8F, 1.0F, d, d1, d2, d3, d4, d5);
        }
    }

}
