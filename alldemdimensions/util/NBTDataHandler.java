package alldemdimensions.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveHandler;
import net.minecraftforge.event.world.WorldEvent;
import alldemdimensions.AllDemDimensions;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class NBTDataHandler
{

	public static void register(IDataHandler ispecialdata)
	{
		dataSources.add(ispecialdata);
	}
	
	@SubscribeEvent
	public void onWorldLoaded(WorldEvent.Load event)
	{
		//loadData(event.world);//1.7TEMP
	}
	
	@SubscribeEvent
	public void onWorldUnloaded(WorldEvent.Unload event)
	{
		//saveData(event.world);//1.7TEMP
	}
	
	@SubscribeEvent
	public void onWorldSaved(WorldEvent.Save event)
	{
		//saveData(event.world);//1.7TEMP
	}

	public static void saveData(World world)
    {
        try
        {
            File file = getWorldDirectory(world);
			if(file == null)
			{
				return;
			}
            File file1;
            NBTTagCompound nbttagcompound;
            for(Iterator iterator = dataSources.iterator(); iterator.hasNext(); CompressedStreamTools.writeCompressed(nbttagcompound, new FileOutputStream(file1)))
            {
                IDataHandler ispecialdata = (IDataHandler)iterator.next();
                file1 = new File(file, "AllDemDimensions.dat");
                if(!file1.exists())
                {
                    CompressedStreamTools.writeCompressed(new NBTTagCompound(), new FileOutputStream(file1));
                }
                nbttagcompound = CompressedStreamTools.readCompressed(new FileInputStream(file1));
                ispecialdata.saveData(nbttagcompound);
            }

        }
		catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }
	
	public static void loadData(World world)
    {
        try
        {
            File file = getWorldDirectory(world);
			if(file == null)
			{
				return;
			}
            IDataHandler ispecialdata;
            NBTTagCompound nbttagcompound;
            for(Iterator iterator = dataSources.iterator(); iterator.hasNext(); ispecialdata.loadData(nbttagcompound))
            {
                ispecialdata = (IDataHandler)iterator.next();
                File file1 = new File(file, "AllDemDimensions.dat");
                if(!file1.exists())
                {
                    CompressedStreamTools.writeCompressed(new NBTTagCompound(), new FileOutputStream(file1));
                }
                nbttagcompound = CompressedStreamTools.readCompressed(new FileInputStream(file1));
            }

        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

	public static File getWorldDirectory(World world)
	{
		ISaveHandler isavehandler = world.getSaveHandler();
		if(isavehandler instanceof SaveHandler)
		{
			return AllDemDimensions.getSaveDirectory((SaveHandler)world.getSaveHandler());
		}
		return null;
	}
	
	private static ArrayList<IDataHandler> dataSources = new ArrayList<IDataHandler>();
	
}
