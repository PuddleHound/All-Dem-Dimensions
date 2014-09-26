package alldemdimensions.block;

import java.util.Arrays;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRedstoneValve extends TileEntity
{
    public TileEntityRedstoneValve()
    {
		state = new byte[6];
		Arrays.fill(state, OPEN);
    }
	
    @Override
	public void updateEntity()
    {
    }
	
    @Override
	public void readFromNBT(NBTTagCompound nbttagcompound)
    {
		super.readFromNBT(nbttagcompound);
        state = nbttagcompound.getByteArray("State");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
		super.writeToNBT(nbttagcompound);
        nbttagcompound.setByteArray("State", state);
    }
	
	public byte[] state;
	public static final byte OPEN = 0;
	public static final byte CLOSED = 1;
}
