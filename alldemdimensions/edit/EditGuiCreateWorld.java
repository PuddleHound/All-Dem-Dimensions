package alldemdimensions.edit;

import static org.objectweb.asm.Opcodes.INVOKEINTERFACE;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import alldemdimensions.AllDemDimensionsClient;
import alldemdimensions.world.Dimension;

public class EditGuiCreateWorld extends EditBase
{
    
    public EditGuiCreateWorld()
    {
        super("net.minecraft.client.gui.GuiCreateWorld", "auy");
    }
    
    @Override
    public void init()
    {
        field_buttonList = ReflectionManager.accessField(net.minecraft.client.gui.GuiScreen.class, "buttonList", "i");
        field_moreOptions = ReflectionManager.accessField(GuiCreateWorld.class, "field_146344_y", "v");
    }
   
    @Insert(memberName = "initGui", memberNameObf = "", memberDesc = "()V", instrNames = "clear", instrNamesObf = "", instrOpcodes = INVOKEINTERFACE)
    public static void initGui()
    {
        AllDemDimensionsClient.selectedSpawnDimension = 0;
        GuiCreateWorld gui = (GuiCreateWorld)Minecraft.getMinecraft().currentScreen;
        List buttonList = (List)ReflectionManager.getFieldValue(gui, field_buttonList);
        buttonList.add(buttonSpawnDimension = new GuiButton(9, gui.width / 2 - 75, 164, 150, 20, "Starting Dimension: Overworld"));
    }
    
    @Insert(memberName = "updateScreen", memberNameObf = "", memberDesc = "()V", instrNames = "updateCursorCounter", instrNamesObf = "", instrOpcodes = INVOKEVIRTUAL)
    public static void updateScreen()
    {
        String s = "Starting Dimension: " + Dimension.getDimensionForId_ADD(AllDemDimensionsClient.selectedSpawnDimension).displayName;
        buttonSpawnDimension.displayString = s;
        buttonSpawnDimension.visible/*drawButton*/ = !(Boolean)ReflectionManager.getFieldValue((GuiCreateWorld)Minecraft.getMinecraft().currentScreen, field_moreOptions);
    }
    
    @Insert(memberName = "actionPerformed", memberNameObf = "", memberDesc = "(Lnet/minecraft/client/gui/GuiButton;)V", instrOpcodes = {-1, -1}, paramIndices = 1)
    public static void actionPerformed(GuiButton guibutton)
    {
        if(!buttonSpawnDimension.enabled)
        {
            return;
        }
        if(guibutton.id == buttonSpawnDimension.id)
        {
        	int currentSelection;
        	for(currentSelection = AllDemDimensionsClient.selectedSpawnDimension + 1; Dimension.getDimensionForId_ADD(currentSelection) == null; currentSelection++)
        	{
        		System.out.println(currentSelection);
        		if(currentSelection >= 4)
        		{
        			currentSelection = -1;
        		}
        	}
            AllDemDimensionsClient.selectedSpawnDimension = currentSelection;
        }
    }
    
    private static GuiButton buttonSpawnDimension;
    private static Field field_buttonList;
    private static Field field_moreOptions;

}
