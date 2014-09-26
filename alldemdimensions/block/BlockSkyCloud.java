package alldemdimensions.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import alldemdimensions.util.TextureLibrary;

public class BlockSkyCloud extends Block
{

    public BlockSkyCloud()
    {
        super(Material.sponge);
		setCreativeTab(CreativeTabs.tabBlock);
    }
        
    /*@Override
    public void updateTick(World world, int i, int j, int k, Random random)
    {
        int metadata = world.getBlockMetadata(i, j, k);
        if(metadata == STORM && random.nextInt(5000) == 0)
        {
            world.addWeatherEffect(new EntityLightningBolt(world, (double)i, (double)j, (double)k));//move to Zenith world tick
        }
    }*/
    
    @Override
    public void onFallenUpon(World world, int i, int j, int k, Entity entity, float f)
    {
    	int meta = world.getBlockMetadata(i, j, k);
    	if(meta != ICE && meta != LOTUS_PAD)
        {
            entity.fallDistance = entity.fallDistance >= 4 ? entity.fallDistance - 4 : 0;
        }
    }
	
    @Override
	public void onEntityWalking(World world, int i, int j, int k, Entity entity)
    {
		if(world.getBlockMetadata(i, j, k) == ICE && world.rand.nextInt(16) == 0)
		{
			breakCloud(world, i, j, k, entity);
		}
    }
	
    @Override
    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
    	int meta = world.getBlockMetadata(i, j, k);
    	if(meta == RAIN || meta == STORM)
    	{
    		//slows movement?
    	}
    }
    
	private void breakCloud(World world, int i, int j, int k, Entity entity)
	{
		if(!world.isRemote)
		{
			world.setBlockToAir(i, j, k);
			world.spawnParticle("snowballpoof", (double)i, (double)j, (double)k, 0.0D, 0.0D, 0.0D);
			world.playSoundAtEntity(entity, "mob.chickenplop", 1.0F, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1.0F);
		}
	}
	    
    @Override
	public IIcon getIcon(int i, int j)
	{
		if(j == RAIN)
		{
			return TextureLibrary.getBlockTexture("zenith/cloudRain");
		}
		if(j == ICE)
		{
			return TextureLibrary.getBlockTexture("zenith/cloudSnow");
		}
		if(j == STORM)
		{
			return TextureLibrary.getBlockTexture("zenith/cloudStorm");
		}
		if(j == LOTUS_PAD)
		{
			return TextureLibrary.getBlockTexture("zenith/lotusPad");
		}
		return TextureLibrary.getBlockTexture("zenith/cloud");
	}
	
    @Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
    	int meta = world.getBlockMetadata(x, y, z);
		if(meta == DEFAULT || meta == LOTUS_PAD)
		{
			return;
		}
		String particle = meta == ICE ? "snowshovel" : "dripWater";

        for (int i = 0; i < 4; i++)
        {
            double d = (float)x + random.nextFloat();
            double d1 = (float)y + random.nextFloat();
            double d2 = (float)z + random.nextFloat();
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            int j = random.nextInt(2) * 2 - 1;
            d3 = ((double)random.nextFloat() - 0.5D) * 0.5D;
            d4 = ((double)random.nextFloat() - 0.5D) * 0.5D;
            d5 = ((double)random.nextFloat() - 0.5D) * 0.5D;

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

            world.spawnParticle(particle, d, d1, d2, d3, d4, d5);
        }
    }
	
    @Override
	public void getSubBlocks(Item i, CreativeTabs creativetabs, List list)
    {
        list.add(new ItemStack(i, 1, DEFAULT));
        list.add(new ItemStack(i, 1, RAIN));
        list.add(new ItemStack(i, 1, ICE));
        list.add(new ItemStack(i, 1, STORM));
    }
	
    @Override
	public int damageDropped(int i)
    {
        return i;
    }
	
	public static final byte DEFAULT = 0;
	public static final byte RAIN = 1;
	public static final byte ICE = 2;
	public static final byte STORM = 3;
    public static final byte LOTUS_PAD = 4;
}
