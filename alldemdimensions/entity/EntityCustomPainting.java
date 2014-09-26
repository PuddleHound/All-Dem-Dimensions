package alldemdimensions.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;

public class EntityCustomPainting extends EntityPainting
{

    public EntityCustomPainting(World world)
    {
        super(world);
		if(pixels == null)
		{
			int i = -16777216 | (255 << 16) | (255 << 8) | 255;
			pixels = new int[512 * 512];
			for(int j = 0; j < pixels.length; j++)
			{
				pixels[j] = i;
			}
		}
    }

    public EntityCustomPainting(World world, int x, int y, int z, int dir, int[] ai)
    {
		super(world, x, y, z, dir);
        this.setDirection(dir);
		if(ai != null)
		{
			pixels = ai;
		} else
		{
			int i = -16777216 | (255 << 16) | (255 << 8) | 255;
			pixels = new int[512 * 512];
			for(int j = 0; j < pixels.length; j++)
			{
				pixels[j] = i;
			}
		}
    }
	
    @Override
	public void onUpdate()
	{
		super.onUpdate();
		sendSpawnPacket();
	}
	
    boolean spawned = false;
	private void sendSpawnPacket()
	{
		if(worldObj.isRemote || spawned)
		{
			return;
		}
		/*ByteArrayOutputStream stream = new ByteArrayOutputStream(pixels.length + 20);
		DataOutputStream stream1 = new DataOutputStream(stream);
		try 
		{
			stream1.writeInt(entityId);
			stream1.writeInt(hangingDirection);
			stream1.writeInt(xPosition);
			stream1.writeInt(yPosition);
			stream1.writeInt(zPosition);
			for(int i : pixels)//npe
			{
                //System.out.println(i);
				stream1.writeInt(i);
			}
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "ADD|PaintSpawn";
		packet.data = stream.toByteArray();
		packet.length = stream.size();
		PacketDispatcher.sendPacketToAllInDimension(packet, worldObj.provider.dimensionId);*/
        
        spawned = true;
	}

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setIntArray("pixels", pixels);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
		pixels = nbttagcompound.getIntArray("pixels");
    }

    //@Override
    public void dropItemStack()
    {
		ItemStack itemstack = new ItemStack(AllDemDimensions.canvas);
		itemstack.setTagCompound(new NBTTagCompound());
		itemstack.getTagCompound().setIntArray("pixels", pixels);
        this.entityDropItem(itemstack, 0.0F);
    }
	
    @Override
	public int getWidthPixels()
	{
		return sizeX;
	}

    @Override
    public int getHeightPixels()
	{
		return sizeY;
	}
    
    @Override
    public void onBroken(Entity entity)
    {
        dropItemStack();
    }
	
	public int sizeX = 16;
	public int sizeY = 16;
    public int resolution = 64;
	public int[] pixels;
	public String title = "Painting";
	public String author = "Author";
	public boolean editable = true;
    public int id;

}
