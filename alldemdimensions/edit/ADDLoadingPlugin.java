package alldemdimensions.edit;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class ADDLoadingPlugin implements IFMLLoadingPlugin
{

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }

    @Override
    public String[] getASMTransformerClass()
    {
        return new String[] {Transformer.class.getName()};
    }

    @Override
    public String getModContainerClass()
    {
        return null;
    }

    @Override
    public String getSetupClass()
    {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data)
    {
    }

}
