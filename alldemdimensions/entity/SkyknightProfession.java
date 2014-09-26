package alldemdimensions.entity;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import alldemdimensions.AllDemDimensions;

public class SkyknightProfession
{
    public SkyknightProfession(byte b, String s)
    {
		allProfessions[b] = this;
		id = b;
		texture = ("/alldemdimensions/entities/" + s + ".png");
    }
	
	public static SkyknightProfession getProfession(byte b)
	{
		if(b < 0 || b >= allProfessions.length)
		{
			return knight;
		}
		return allProfessions[b];
	}
	
	public Item getHeldItem()
	{
		if(this == archer)
		{
			return Items.bow;
		}
		if(this == knight)
		{
			return AllDemDimensions.silverSword;
		}
		return null;
	}
	
	public static SkyknightProfession[] allProfessions = new SkyknightProfession[7];
	public byte id;
	public String texture;
	public static final SkyknightProfession knight = new SkyknightProfession((byte)0, "skyKnight");
	public static final SkyknightProfession archer = new SkyknightProfession((byte)1, "skyArcher");
	public static final SkyknightProfession brawler = new SkyknightProfession((byte)2, "skyBrawler");
	public static final SkyknightProfession captain = new SkyknightProfession((byte)3, "skyCaptain");
	public static final SkyknightProfession scout = new SkyknightProfession((byte)4, "skyScout");
	public static final SkyknightProfession sorcerer = new SkyknightProfession((byte)5, "skySorcerer");
	public static final SkyknightProfession king = new SkyknightProfession((byte)6, "skyKing");
}
