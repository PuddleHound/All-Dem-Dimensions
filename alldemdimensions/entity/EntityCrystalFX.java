package alldemdimensions.entity;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;
import alldemdimensions.util.TextureLibrary;

public class EntityCrystalFX extends EntityFX
{
	
    public EntityCrystalFX(World world, double x, double y, double z, float f, float f1, float f2)
    {
        this(world, x, y, z, 1.0F, f, f1, f2);
    }

    public EntityCrystalFX(World world, double x, double y, double z, float f, float f1, float f2, float f3)
    {
        super(world, x, y, z, 0.0D, 0.0D, 0.0D);
        setParticleIcon(TextureLibrary.getBlockTexture("particles/sparkle"));
        this.motionX = 0D;
        this.motionY = 0D;
        this.motionZ = 0D;

        this.particleRed = 0.8F;
        this.particleGreen = 0.8F;
        this.particleBlue = 0.9F;
        this.particleScale *= 0.5F;
        this.particleScale *= f;
        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
        this.particleMaxAge = (int)((float)this.particleMaxAge * f);
        this.noClip = false;
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }

        this.moveEntity(this.motionX, this.motionY, this.motionZ);

        if (this.posY == this.prevPosY)
        {
            this.motionX *= 1.1D;
            this.motionZ *= 1.1D;
        }

        this.motionX *= 0.9599999785423279D;
        this.motionY *= 0.9599999785423279D;
        this.motionZ *= 0.9599999785423279D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
		
		if(particleRed < 1F)
		{
			particleRed += 0.01F;
		}
		if(particleGreen < 1F)
		{
			particleGreen += 0.01F;
		}
		if(particleBlue < 1F)
		{
			particleBlue += 0.01F;
		}
		
    }
	
    @Override
	public float getBrightness(float f)
    {
        return 1.0F;
    }
        
    @Override
    public int getFXLayer()
    {
        return 1;
    }
}
