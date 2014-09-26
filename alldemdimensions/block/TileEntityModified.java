package alldemdimensions.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityModified extends TileEntity
{
    
    public TileEntityModified()
    {
        cpw.mods.fml.relauncher.Side side = cpw.mods.fml.common.FMLCommonHandler.instance().getEffectiveSide();
        if(side == cpw.mods.fml.relauncher.Side.SERVER)
		{
            Random random = new Random();
            while(baseBlock == null || baseBlock instanceof ITileEntityProvider || baseBlock instanceof BlockRailBase || baseBlock instanceof BlockBed || baseBlock instanceof BlockDoor || baseBlock.hasTileEntity(0))
            {
                baseBlock = Block.getBlockById(random.nextInt(160));
            }
        }
        //testing
        //modifiers.add(AlchemyEffect.appearanceI);
        modifiers.add(AlchemyEffect.translucency);
        modifiers.add(AlchemyEffect.explosionResistanceII);
        modifiers.add(AlchemyEffect.melting);
    }

    @Override
    public void updateEntity()
    {
    }
    
    @Override
    public boolean canUpdate()
    {
        if(baseBlock != null)
        {
            return baseBlock.getTickRandomly();
        }
        for(AlchemyEffect effect : modifiers)
        {
            if(effect.updateFrequency != 0)
            {
                return true;
            }
        }
        return false;
    }
    
    /*@Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        writeToNBT(nbttagcompound);
        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 3, nbttagcompound);
    }
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData packet)
    {
        readFromNBT(packet.data);
    }*///1.7.2
    
    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        baseBlock = Block.getBlockById(nbttagcompound.getInteger("BaseBlockId"));
        int[] modifierIds = nbttagcompound.getIntArray("Modifiers");
        modifiers.clear();
        for(int id : modifierIds)
        {
            modifiers.add(AlchemyEffect.allEffects[id]);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        if(baseBlock != null)
        {
            nbttagcompound.setInteger("BaseBlockId", Block.getIdFromBlock(baseBlock));
        }
        int[] modifierIds = new int[modifiers.size()];
        for(int i = 0; i < modifierIds.length; i++)
        {
            modifierIds[i] = modifiers.get(i).id;
        }
        nbttagcompound.setIntArray("Modifiers", modifierIds);
    }
    
    public ArrayList<AlchemyEffect> getModifiers()
    {
        return modifiers;
    }
    
    public Block getBaseBlock()
    {
        return baseBlock != null ? baseBlock : Blocks.stone;
    }
    
    private Block baseBlock;
    private ArrayList<AlchemyEffect> modifiers = new ArrayList<AlchemyEffect>();
    
}
