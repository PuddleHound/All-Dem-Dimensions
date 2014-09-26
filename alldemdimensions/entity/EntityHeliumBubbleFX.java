package alldemdimensions.entity;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import alldemdimensions.util.TextureLibrary;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityHeliumBubbleFX extends EntityFX
{
    public EntityHeliumBubbleFX(World world, double d, double d1, double d2, double d3, double d4, double d5)
    {
        super(world, d, d1, d2, d3, d4, d5);
        setParticleIcon(TextureLibrary.getBlockTexture("particles/heliumBubble"));
        this.particleRed = 1.0F;
        this.particleGreen = 1.0F;
        this.particleBlue = 1.0F;
        this.setSize(0.02F, 0.02F);
        this.particleScale *= this.rand.nextFloat() * 0.6F + 0.2F;
        this.motionX = d3 * 0.20000000298023224D + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.02F);
        this.motionY = d4 * 0.20000000298023224D + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.02F);
        this.motionZ = d5 * 0.20000000298023224D + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.02F);
        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
    }
    
    @Override
    public int getFXLayer()
    {
        return 1;
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY += 0.002D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.8500000238418579D;
        this.motionY *= 0.8500000238418579D;
        this.motionZ *= 0.8500000238418579D;

        if (!this.worldObj.isAirBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)))
        {
            this.setDead();
        }

        if (this.particleMaxAge-- <= 0)
        {
            this.setDead();
        }
    }
    
}
