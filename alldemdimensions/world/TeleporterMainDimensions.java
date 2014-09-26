package alldemdimensions.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterMainDimensions extends Teleporter
{
	
	public TeleporterMainDimensions(WorldServer worldserver, int i)
	{
		this(worldserver, 0, i);
	}
	
	public TeleporterMainDimensions(WorldServer worldserver, int i, int j)
	{
		super(worldserver);
		oldDimension = i;
		newDimension = j;
	}

    @Override
	public void placeInPortal(Entity entity, double d, double d1, double d2, float d3)
    {
        if(!this.placeInExistingPortal(entity, d, d1, d2, d3))
        {
            this.makePortal(entity);
            this.placeInExistingPortal(entity, d, d1, d2, d3);
		}
    }
	
	public int oldDimension;
	public int newDimension;
}
