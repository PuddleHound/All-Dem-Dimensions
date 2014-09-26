package alldemdimensions.entity;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityTornadoFX extends EntityFX
{
    public EntityTornadoFX(World world, double d, double d1, double d2, double d3, double d4, double d5)
    {
        super(world, d, d1, d2, d3, d4, d5);
        this.particleRed = 1.0F;
        this.particleGreen = 1.0F;
        this.particleBlue = 1.0F;
        this.setSize(0.02F, 0.02F);
        this.particleScale *= this.rand.nextFloat() * 0.6F + 0.2F;
        this.motionX = 0D;
        this.motionY = 0D;
        this.motionZ = 0D;
        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
		rotationOffset = rand.nextInt(360);
    }

    @Override
    public void onUpdate()
    {
		setParticleTextureIndex(224);
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY += 0.002D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX = Math.cos(worldObj.getWorldTime() + rotationOffset);
        this.motionY *= 0.8500000238418579D;
        this.motionZ = -Math.sin(worldObj.getWorldTime() + rotationOffset);

        if (!this.worldObj.isAirBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)))
        {
            this.setDead();
        }

        if (this.particleMaxAge-- <= 0)
        {
            this.setDead();
        }
    }
	
	private int rotationOffset;
}
