package com.noriental.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil
{
    /**
     * 根据名称获取java class path下的properties文件
     * @param name
     * @return
     * @throws IOException 
     */
    public static Properties getPropertiesByNameUnderClassPath(String name) throws IOException
    {
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(name);
        Properties p = new Properties();
        p.load(in);
        return p;
    }
    
    public static String getValue(String filename, String key) {
    	InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(filename);
    	Properties p = null;
    	Properties prop  = null;
    	try {
    		p = new Properties();
    		p.load(in);
			prop = PropertiesUtil.getPropertiesByNameUnderClassPath(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return prop.getProperty(key);
    }
}
