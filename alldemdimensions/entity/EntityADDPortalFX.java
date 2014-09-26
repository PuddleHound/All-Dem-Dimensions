package alldemdimensions.entity;

import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.world.World;

public class EntityADDPortalFX extends EntityPortalFX
{
    public EntityADDPortalFX(World world, float red, float green, float blue, double d, double d1, double d2, double d3, double d4, double d5)
    {
        super(world, d, d1, d2, d3, d4, d5);
        particleRed = red;
        particleGreen = green;
        particleBlue = blue;
    }

}
