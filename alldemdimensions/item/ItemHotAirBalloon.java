package alldemdimensions.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import alldemdimensions.entity.EntityHotAirBalloon;

public class ItemHotAirBalloon extends Item
{
    public ItemHotAirBalloon()
    {
        super();
		setMaxStackSize(1);
    }
	
    @Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float f, float f1, float f2)
    {
		if(world.isRemote)
		{
			return true;
		}
		EntityHotAirBalloon entity = new EntityHotAirBalloon(world);
		entity.setLocationAndAngles(i, j + 2, k, 0F, 0F);
		world.spawnEntityInWorld(entity);
		itemstack.stackSize--;
		return true;
	}
}
