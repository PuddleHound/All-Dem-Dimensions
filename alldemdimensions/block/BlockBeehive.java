package alldemdimensions.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import alldemdimensions.entity.EntityBee;
import alldemdimensions.util.TextureLibrary;

public class BlockBeehive extends Block
{
    public BlockBeehive()
    {
        super(Material.gourd);
    }
	
    @Override
	public int damageDropped(int i)
    {
		if(i == WAX || i == HONEYCOMB)
		{
			return i;
		}
        return 0;
    }
	
    @Override
	public IIcon getIcon(int i, int j)
	{
		if((i == 0 && j == HONEYCOMB_SIDE_0) || (i == 1 && j == HONEYCOMB_SIDE_1) ||
			(i == 2 && j == HONEYCOMB_SIDE_2) || (i == 3 && j == HONEYCOMB_SIDE_3) ||
			(i == 4 && j == HONEYCOMB_SIDE_4) || (i == 5 && j == HONEYCOMB_SIDE_5) ||
			j == HONEYCOMB || (i != 1 && j == ROYAL_JELLY))
		{
			return TextureLibrary.getBlockTexture("zenith/beehiveInterior");
		} else
		if(j == WAX)
		{
			return TextureLibrary.getBlockTexture("zenith/waxGrassTop");
		} else
        if(i == 1 && j == ROYAL_JELLY)
        {
            return TextureLibrary.getBlockTexture("zenith/royalJelly");
        }
		return TextureLibrary.getBlockTexture("zenith/beehiveExterior");
	}
	
    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemstack)
    {
        if(world.getBlockMetadata(i, j, k) != 0)
        {
        	return;
        }
        int metadata = 0;
        boolean set = false;
        if(MathHelper.abs((float)entityliving.posX - (float)i) < 2.0F && MathHelper.abs((float)entityliving.posZ - (float)k) < 2.0F)
        {
            double d = entityliving.posY + 1.82D - (double)entityliving.yOffset;
            if (d - (double)j > 2.0D)
            {
                metadata = 1;
                set = true;
            }
            if ((double)j - d > 0.0D)
            {
                metadata = 0;
                set = true;
            }
        }
        if(!set)
        {
            int l = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            if(l == 0)
            {
                    metadata = 2;
            }
            if(l == 1)
            {
                    metadata = 5;
            }
            if(l == 2)
            {
                    metadata = 3;
            }
            if(l == 3)
            {
                    metadata = 4;
            }
        }
        world.setBlockMetadataWithNotify(i, j, k, metadata, 2);
    }
	
    @Override
	public void updateTick(World world, int i, int j, int k, Random random)
    {
		//spawn bees?
    }
	
    @Override
	public boolean removedByPlayer(World world, EntityPlayer entityplayer, int i, int j, int k) 
    {
		int metadata = world.getBlockMetadata(i, j, k);
		if(metadata <= ROYAL_JELLY || metadata == HONEYCOMB)
		{
			EntityBee.alertBeesOfAttack(world, entityplayer);
		}
		return super.removedByPlayer(world, entityplayer, i, j, k);
	}
	
    @Override
	public void getSubBlocks(Item item, CreativeTabs creativetabs, List list)
    {
        list.add(new ItemStack(item, 1, HONEYCOMB_SIDE_0));
        list.add(new ItemStack(item, 1, ROYAL_JELLY));
        list.add(new ItemStack(item, 1, WAX));
        list.add(new ItemStack(item, 1, HONEYCOMB));
    }
	
	public static final byte HONEYCOMB_SIDE_0 = 0;
	public static final byte HONEYCOMB_SIDE_1 = 1;
	public static final byte HONEYCOMB_SIDE_2 = 2;
	public static final byte HONEYCOMB_SIDE_3 = 3;
	public static final byte HONEYCOMB_SIDE_4 = 4;
	public static final byte HONEYCOMB_SIDE_5 = 5;
	public static final byte ROYAL_JELLY = 6;
	public static final byte WAX = 8;
	public static final byte HONEYCOMB = 9;
	
}
