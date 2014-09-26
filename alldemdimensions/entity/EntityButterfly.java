package alldemdimensions.entity;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.world.biome.BiomeGenSkyIce;
import alldemdimensions.world.biome.BiomeGenSkyMountain;
import alldemdimensions.world.environment.EnumZenithDay;

public class EntityButterfly extends EntityZenithFlying
{

	public EntityButterfly(World world)
	{
		super(world);
        setSize(1.0F, 0.5F);
	}
	
    @Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(17, new Byte((byte)0));
		byte b = getColor();
		if(b == 0)
		{
			BiomeGenBase biome = worldObj.getBiomeGenForCoords(MathHelper.floor_double(posX), MathHelper.floor_double(posZ));
			if(biome instanceof BiomeGenSkyIce || biome instanceof BiomeGenSkyMountain)
			{
				setColor(COLOR_LIGHT_BLUE);
			} else
			{
				setColor((byte)(worldObj.rand.nextInt(3) + 1));
			}
		}
	}
	
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8D);
    }
	
    @Override
	public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }
	
    @Override
	public boolean canSpawnDuring(EnumZenithDay enumzenithday)
	{
		if(enumzenithday != EnumZenithDay.NIGHT)
		{
			return true;
		}
		return false;
	}
	
    @Override
	public boolean isAttractedToFlowers()
	{
		return !isHostile();
	}
	
    @Override
	public boolean interact(EntityPlayer entityplayer)
	{
		ItemStack itemstack = entityplayer.inventory.getCurrentItem();
		if(nectarCooldown == 0 && itemstack != null && itemstack.getItem() == Items.bucket)
		{
			if(itemstack.stackSize-- <= 0)
            {
                entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, new ItemStack(AllDemDimensions.bucketNectar));
            } else 
			if(!entityplayer.inventory.addItemStackToInventory(new ItemStack(AllDemDimensions.bucketNectar)))
            {
                entityplayer.func_146097_a(new ItemStack(AllDemDimensions.bucketNectar), true, false);//1.7.2 - dropPlayerItem - booleans?
            }
			nectarCooldown = 4800;
			return true;
		}
		return false;
	}
	
    @Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		if(nectarCooldown > 0)
		{
			nectarCooldown--;
		}
	}
	
    @Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setShort("NectarCooldown", nectarCooldown);
		//nbttagcompound.setByte("ButterflyColor", color);
    }

        @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        nectarCooldown = nbttagcompound.getShort("NectarCooldown");
		//color = nbttagcompound.getByte("ButterflyColor");
    }
	
	public void setColor(byte b)
    {
        dataWatcher.updateObject(17, Byte.valueOf(b));
    }

    public byte getColor()
    {
        return dataWatcher.getWatchableObjectByte(17);
    }
	
    //@Override
	public String getTexture()
	{
		byte b = getColor();
		String s = "Cyan";
		if(b == COLOR_ORANGE)
		{
			s = "Orange";
		}
		if(b == COLOR_LIME)
		{
			s = "Lime";
		}
		if(b == COLOR_LIGHT_BLUE)
		{
			s = "LightBlue";
		}
		return "/alldemdimensions/entities/butterfly" + s + ".png";
	}
	
    @Override
	protected void dropFewItems(boolean flag, int i)
    {
		byte b = getColor();
		ItemStack drop = new ItemStack(Items.dye, 1, 6);
		if(b == COLOR_ORANGE)
		{
			drop.setItemDamage(14);
		}
		if(b == COLOR_LIME)
		{
			drop.setItemDamage(10);
		}
		if(b == COLOR_LIGHT_BLUE)
		{
			drop.setItemDamage(12);
		}
		entityDropItem(drop, 0.0F);
	}
	
	private short nectarCooldown = 0;
	public static final byte COLOR_CYAN = 1;
	public static final byte COLOR_ORANGE = 2;
	public static final byte COLOR_LIME = 3;
	public static final byte COLOR_LIGHT_BLUE = 4;
}
