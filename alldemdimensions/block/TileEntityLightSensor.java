package alldemdimensions.block; import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import alldemdimensions.AllDemDimensions;

public class TileEntityLightSensor extends TileEntity
{
    public TileEntityLightSensor()
    {
    }
	
    @Override
	public void updateEntity()
    {
		if(!activated && worldObj.getFullBlockLightValue(xCoord, yCoord, zCoord) > getBlockMetadata())
		{
			activated = true;
			worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, AllDemDimensions.lightSensor);
		} else
		if(activated && worldObj.getFullBlockLightValue(xCoord, yCoord, zCoord) < getBlockMetadata())
		{
			activated = false;
			worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, AllDemDimensions.lightSensor);
		}
    }
	
    @Override
	public void readFromNBT(NBTTagCompound nbttagcompound)
    {
		super.readFromNBT(nbttagcompound);
        activated = nbttagcompound.getBoolean("Activated");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
		super.writeToNBT(nbttagcompound);
        nbttagcompound.setBoolean("Activated", activated);
    }
	
	public boolean activated = false;
}
