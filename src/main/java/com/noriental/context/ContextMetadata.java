package com.noriental.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Utility class for fetch memory dict set in the application context
 *
 * @author Wang Beichen
 * @date 2014-1-16
 * @version 1.0
 */
public class ContextMetadata {

	public static Map<?, ?> getAsMap(String key) {
		Map<?, ?> map = (Map<?, ?>) SystemContextListener.getMetadata();
		Object o = map.get(key);
		if( o != null ) {
			return (Map<?, ?>) o;
		}
		return null;
	}
	
	public static List<?> getAsList(String key) {
		List<?> list = new ArrayList<Object>();
		Map<?, ?> map = (Map<?, ?>) SystemContextListener.getMetadata();
		Object o = map.get(key);
		if(o != null) {
			list = (List<?>) o;
		}
		return list;
	}
}
