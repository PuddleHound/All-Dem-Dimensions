package alldemdimensions.util;

import net.minecraft.nbt.NBTTagCompound;

public interface IDataHandler
{

	public abstract void saveData(NBTTagCompound nbttagcompound);
	
	public abstract void loadData(NBTTagCompound nbttagcompound);
}
