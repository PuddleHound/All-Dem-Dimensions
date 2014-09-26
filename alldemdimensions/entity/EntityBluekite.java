package alldemdimensions.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

public class EntityBluekite extends EntityAnimal
{

	public EntityBluekite(World world)
	{
		super(world);
        setSize(1.0F, 0.5F);
	}
	
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16D);
    }

    @Override
	public EntityAgeable createChild(EntityAgeable entityageable)
    {
        return null;
    }
	
	public static final byte ADULT = 0;
	public static final byte UNTAMED = 1;
	public static final byte TAMED = 2;
	public static final byte SADDLED = 3;

}
