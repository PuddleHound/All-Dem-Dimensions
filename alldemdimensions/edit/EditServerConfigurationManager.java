package alldemdimensions.edit;

import java.lang.reflect.Field;

import net.minecraft.server.management.ServerConfigurationManager;

import com.mojang.authlib.GameProfile;

public class EditServerConfigurationManager extends EditBase
{

	public EditServerConfigurationManager()
	{
		super("net.minecraft.server.management.ServerConfigurationManager", "");
	}
	
	 @Override
    public void init()
    {
		 field_ServerConfigurationManager_mcServer = ReflectionManager.accessField(ServerConfigurationManager.class, "mcServer", "");
    }
	
    @Insert(memberName = "createPlayerForUser", memberNameObf = "", memberDesc = "(Lcom/mojang/authlib/GameProfile;)Lnet/minecraft/entity/player/EntityPlayerMP", instrOpcodes = {-1, -1}, /*replaceInstr = {false, false, true},*/ insertOffset = 0, paramIndices = {1})
	public static void /*EntityPlayerMP*/ createPlayerForUser(/*ServerConfigurationManager configManager, */GameProfile gameprofile)
    {
    	System.out.println("creating player for user");
    	
    	/*MinecraftServer server = (MinecraftServer)ReflectionManager.getFieldValue(configManager, field_ServerConfigurationManager_mcServer);
    	
        Object object;

        int dimensionId = -82;
        if (server.isDemo())
        {
            object = new DemoWorldManager(server.worldServerForDimension(dimensionId));
        }
        else
        {
            object = new ItemInWorldManager(server.worldServerForDimension(dimensionId));
        }

        return new EntityPlayerMP(server, server.worldServerForDimension(dimensionId), gameprofile, (ItemInWorldManager)object);*/
    }
    
    private static Field field_ServerConfigurationManager_mcServer;

}
