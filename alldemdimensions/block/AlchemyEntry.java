package alldemdimensions.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class AlchemyEntry
{

	public AlchemyEntry(Object i, int j, int k, byte b, boolean flag, boolean flag1)
	{
		if(!(i instanceof Block) && !(i instanceof Item))
		{
			System.err.println("Error: Alchemy entry " + i.toString() + " is not a block or item instance.");
		}
		itemId = i;
		itemMeta = j;
		value = k;
		dimension = b;
		isBlock = flag;
		isOrganic = flag1;
        rarity = 1;
        exclusive = false;
	}
        
    public AlchemyEntry setRarity(int i)
    {
        rarity = i;
        return this;
    }
    
    public AlchemyEntry setExclusive()//exempt from transmutation except when base item modifier is used.
    {
        exclusive = true;
        return this;
    }

	public Object itemId;//can be a block or item
	public int itemMeta;//set to -1 for any metadata
	public int value;
	public byte dimension;
	public boolean isBlock;
	public boolean isOrganic;
	public int rarity;//USE
    public boolean exclusive;
	
	public static final byte NONE = 0;
	public static final byte OVERWORLD = 1;
	public static final byte ZENITH = 2;
	public static final byte KYTHER = 3;
	public static final byte NETHER = 4;
	public static final byte ENDER = 5;
}
