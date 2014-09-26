package alldemdimensions.block;

import java.util.Random;

import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.util.TextureLibrary;

public class BlockADDFlowingLiquid extends BlockDynamicLiquid
{
	
    public BlockADDFlowingLiquid(Material material)
    {
        super(material);
        float f = 0.0F;
        float f1 = 0.0F;
        setBlockBounds(0.0F + f1, 0.0F + f, 0.0F + f1, 1.0F + f1, 1.0F + f, 1.0F + f1);
        setTickRandomly(true);
    }
	
    @Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
		if(world.getBlock(i, j, k).getMaterial() == AllDemDimensions.honeyMaterial)
		{
			entity.motionX *= 0.2D;
			entity.motionY *= 0.5D;
			entity.motionZ *= 0.2D;
		}
		if(world.getBlock(i, j, k).getMaterial() == AllDemDimensions.nectarMaterial)
		{
			entity.motionX *= 0.7D;
            entity.motionY *= 0.7D;
			entity.motionZ *= 0.7D;
			if(entity instanceof EntityLiving && world.rand.nextInt(10) == 0)
			{
				((EntityLiving)entity).heal(1);
			}
		}
        if(entity instanceof EntityLiving)
        {
            entity.setAir(entity.getAir() - 1);
        }
    }

    @Override
    public boolean getBlocksMovement(IBlockAccess iblockaccess, int x, int y, int z)
    {
        return this.blockMaterial != AllDemDimensions.honeyMaterial;
    }
    
    @Override
    public int getBlockColor()
    {
        return 16777215;
    }
	
    @Override
    public IIcon getIcon(int i, int j)
    {
        return blockMaterial == AllDemDimensions.honeyMaterial ? TextureLibrary.getBlockTexture("zenith/honey") : TextureLibrary.getBlockTexture("zenith/nectar");
    }

    @Override
    public int tickRate(World world)
    {
        if(blockMaterial == AllDemDimensions.honeyMaterial)
        {
            return 50;
        } else
        if(blockMaterial == AllDemDimensions.nectarMaterial)
        {
            return 10;
        }
        return super.tickRate(world);
    }

    @Override
    public int getRenderBlockPass()
    {
        return 1;
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
    }
	
}
