package alldemdimensions.util;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.block.Tree;
import alldemdimensions.edit.ReflectionManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class TextureLibrary
{
    
    public static IIcon getBlockTexture(String name)
    {
        return blockTextures.get(name);
    }
    
    public static IIcon getItemTexture(String name)
    {
        return itemTextures.get(name);
    }
    
    private void getTexturesInFolder(File folder, String type, HashMap<String, IIcon> hashmap, TextureMap map)
    {
    	if(!folder.exists())
    	{
    		return;
    	}
    	File[] files = folder.listFiles();
    	for(File file : files)
    	{
    		String entryName = file.getPath();
    		if(file.isDirectory())
    		{
    			getTexturesInFolder(file, type, hashmap, map);
    		} else
    		if(entryName.endsWith(".png"))
    		{
    			entryName = entryName.replaceAll("\\\\", "/").split("assets/alldemdimensions/textures/" + type + "s/")[1].replaceFirst(".png", "");
                hashmap.put(entryName, map.registerIcon("alldemdimensions:" + entryName));
                System.out.println("[ADD] Added " + type + " texture with name: " + entryName);
    		}
    		
    	}
    }
   
    @SubscribeEvent
    public void onTexturesStitched(TextureStitchEvent.Pre event)
    {
        TextureMap map = event.map;
        String type = "block";
        HashMap<String, IIcon> hashmap = blockTextures;
        int textureType = (Integer)ReflectionManager.getFieldValue(map, field_TextureMap_textureType);
        if(textureType == 1)
        {
            type = "item";
            hashmap = itemTextures;
        }
        try
        { 
        	ClassLoader loader = getClass().getClassLoader();
        	URL url = loader.getResource("assets/alldemdimensions/textures/" + type + "s/");        	
        	getTexturesInFolder(new File(url.toURI()), type, hashmap, map);
        }
        catch(Exception e)
        {
        	System.out.println("[ADD] Exception loading textures:");
        	e.printStackTrace();
        }
        texturesRegistered = true;        
        System.out.println("[ADD] Registered block and item textures.");
    }
   
   @SubscribeEvent
   public void postTexturesStitched(TextureStitchEvent.Post event)
   {
	   for(Tree tree : Tree.treeList)
	   {
		   tree.loadIcons();
	   }
   }
    
    public static final TextureLibrary instance = new TextureLibrary();
    public static final String domain = "AllDemDimensions" + AllDemDimensions.version;
    private static final HashMap<String, IIcon> blockTextures = new HashMap<String, IIcon>();
    private static final HashMap<String, IIcon> itemTextures = new HashMap<String, IIcon>();
    private static boolean texturesRegistered;
    private static final Field field_TextureMap_textureType = ReflectionManager.accessField(TextureMap.class, "textureType", "textureType");
}
