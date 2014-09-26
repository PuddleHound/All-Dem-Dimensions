package alldemdimensions.block;

public class AlchemyEffect
{
	
	/**With the changes to alchemy, the contents of this and the other alchemy-related classes will likely be removed or changed substantially.**/
    
    public AlchemyEffect(int i)
    {
        id = i;
        allEffects[i] = this;
    }
    
    public AlchemyEffect setUpdateFrequency(int i)
    {
        updateFrequency = i;
        return this;
    }
    
    public int id;
    public int updateFrequency;
    public static AlchemyEffect[] allEffects = new AlchemyEffect[256];
    
    public static final AlchemyEffect explosionResistanceI = new AlchemyEffect(1);
    public static final AlchemyEffect explosionResistanceII = new AlchemyEffect(2);
    public static final AlchemyEffect melting = new AlchemyEffect(3).setUpdateFrequency(10);
    public static final AlchemyEffect acceleratedMovement = new AlchemyEffect(4);
    public static final AlchemyEffect translucency = new AlchemyEffect(5);
    public static final AlchemyEffect lightI = new AlchemyEffect(6);
    public static final AlchemyEffect lightII = new AlchemyEffect(7);
    public static final AlchemyEffect lightIII = new AlchemyEffect(8);
    
    public static final AlchemyEffect appearanceI = new AlchemyEffect(100);//extra data
    public static final AlchemyEffect appearanceII = new AlchemyEffect(101);//extra data
}
