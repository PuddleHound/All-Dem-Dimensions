package alldemdimensions.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWindBlower extends Item
{
    public ItemWindBlower()
    {
        super();
		setMaxDamage(250);
    }
	
    @Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if(!world.isRemote)
        {
			for(byte b = 0; b < 16; b++)
			{
                //FIX WIND
				//world.spawnEntityInWorld(new alldemdimensions.EntityWind(world, entityplayer));
			}
			//entityplayer.addVelocity(-0.1D, -0.1D, -0.1D);
			//entityplayer.setMoveForward(-1);
			//entityplayer.moveEntity(-1.0D, -1.0D, -1.0D);
			itemstack.damageItem(1, entityplayer);
		}
        return itemstack;
	}
}
