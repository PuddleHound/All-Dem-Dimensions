package alldemdimensions.block; import java.util.Arrays;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySoundRecorder extends TileEntity
{
    public TileEntitySoundRecorder()
    {
		sounds = new byte[32768];
		Arrays.fill(sounds, (byte)-1);
		tickCounter = 0;
		playTickCounter = 0;
		isRecording = false;
		isPlaying = false;
    }
	
    @Override
	public void updateEntity()
	{
		if(isRecording)
		{
			tickCounter++;
			if(tickCounter % 100 == 0)
			{
				System.out.println("Recording tick");
			}
		} else
		if(isPlaying)
		{
			byte b = sounds[playTickCounter];
			if(b != -1)
			{
				byte b1 = (byte)(b % 24);//note
				byte b2 = (byte)((b - b1) / 24);//instrument
				System.out.println("Note played = " + b1 + ", " + b2);
				playNote(b2, b1);
			}
			playTickCounter++;
		} else
		{
			tickCounter = 0;
			playTickCounter = 0;
		}
	}
	
	public void recordSound(byte b, byte b1)//b = note (24); b1 = instrument (5)
	{
		System.out.println("Sound recorded");
		if(isRecording)
		{
			byte b2 = (byte)(b + (b1 * 24));
			sounds[tickCounter] = b2;
		}
	}
	
	public void playNote(byte b, byte b1)
    {
        float f = (float)Math.pow(2D, (double)(b1 - 12) / 12D);
        String s = "harp";

        if (b == 1)
        {
            s = "bd";
        }

        if (b == 2)
        {
            s = "snare";
        }

        if (b == 3)
        {
            s = "hat";
        }

        if (b == 4)
        {
            s = "bassattack";
        }

        worldObj.playSoundEffect((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D, (new StringBuilder()).append("note.").append(s).toString(), 3F, f);
        worldObj.spawnParticle("note", (double)xCoord + 0.5D, (double)yCoord + 1.2D, (double)zCoord + 0.5D, (double)b1 / 24D, 0.0D, 0.0D);
    }
	
    @Override
	public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
		nbttagcompound.setInteger("TickCounter", tickCounter);
        nbttagcompound.setByteArray("Sounds", sounds);
		//other values
    }
	
    @Override
	public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
		tickCounter = nbttagcompound.getInteger("TickCounter");
        sounds = nbttagcompound.getByteArray("Sounds");
	}
	
	public byte sounds[];
	public int tickCounter;
	public int playTickCounter;
	public boolean isRecording;
	public boolean isPlaying;
}
