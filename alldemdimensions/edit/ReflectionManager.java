package alldemdimensions.edit;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionManager
{

	public static Object getFieldValue(Object object, Field field)
	{
        if(field == null)
        {
            return null;
        }
		try
		{
			return field.get(object);
		}
		catch(Exception e)
		{
		}
		return null;
	}
	
	public static void setFieldValue(Object source, Field field, Object value)
	{
            if(field == null)
            {
                return;
            }
		try
		{
			field.set(source, value);
		}
		catch(Exception e)
		{
		}
	}
	
	public static Object invokeMethod(Object object, Method method, Object... params)
	{
		if(method == null)
		{
			return null;
		}
		try
		{
			Object ret = method.invoke(object, params);
			return ret;
		}
		catch(Exception e)
		{
		}
		return null;
	}
	
	public static Field accessField(Class c, String name, String obfuscatedName)
	{
		try
		{
			Field field = c.getDeclaredField(name);
			field.setAccessible(true);
			return field;
		}
		catch(Exception e)
		{
			try
			{
				Field field1 = c.getDeclaredField(obfuscatedName);
				field1.setAccessible(true);
				return field1;
			}
			catch(Exception e1)
			{
				System.out.println("[ADD] Failed to load field " + name + " from class " + c.getName());
			}
		}
		return null;
	}
	
	public static Method accessMethod(Class c, String name, String obfuscatedName, Class... params)
	{
		try
		{
			Method method = c.getDeclaredMethod(name, params);
			method.setAccessible(true);
			return method;
		}
		catch(Exception e)
		{
			try
			{
				Method method1 = c.getDeclaredMethod(obfuscatedName, params);
				method1.setAccessible(true);
				return method1;
			}
			catch(Exception e1)
			{
				System.out.println("[ADD] Failed to load method " + name + " from class " + c.getName());
			}
		}
		return null;
	}
	
	public static Class accessClass(String name, String obfuscatedName)
	{
		try
		{
			Class c = Class.forName(name);
			return c;
		}
		catch(Exception e)
		{
			try
			{
				Class c1 = Class.forName(obfuscatedName);
				return c1;
			}
			catch(Exception e1)
			{
				System.out.println("Failed to load class with name " + obfuscatedName);
			}
		}
		return null;
	}
	
}