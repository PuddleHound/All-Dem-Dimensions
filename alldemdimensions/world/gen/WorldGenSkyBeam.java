package alldemdimensions.world.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSkyBeam extends WorldGenerator
{
    //temporary
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        if(j == 0 && world.canBlockSeeTheSky(i, j, k))
        {
            func_150515_a(world, i, j, k, Blocks.beacon);
            TileEntityBeacon beacon = new TileEntityBeacon();
            world.setTileEntity(i, j, k, beacon);
        }
        return true;
    }
}
