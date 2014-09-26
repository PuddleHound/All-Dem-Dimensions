package alldemdimensions.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntitySkyknight extends EntityCreature
{

	public EntitySkyknight(World world)
	{
		super(world);
	}
	
    @Override
	protected void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(17, new Byte((byte)0));
    }

	public SkyknightProfession getProfession()
	{
		return SkyknightProfession.allProfessions[dataWatcher.getWatchableObjectByte(17)];
	}
	
	public void setProfession(SkyknightProfession profession)
	{
		dataWatcher.updateObject(17, new Byte(profession.id));
		if(profession.getHeldItem() != null)
		{
			setCurrentItemOrArmor(0, new ItemStack(profession.getHeldItem()));
		}
	}
	
    @Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setByte("Profession", getProfession().id);
		nbttagcompound.setInteger("Village", villageId);
    }
	
    @Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
		byte id = nbttagcompound.getByte("Profession");
		setProfession(SkyknightProfession.allProfessions[id]);
		villageId = nbttagcompound.getInteger("Village");
    }
	
    @Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30D);
    }
	
	public EntityAgeable func_90011_a(EntityAgeable entityageable)
    {
        return null;
    }
	
	public int villageId;
}
