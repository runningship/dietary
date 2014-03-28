package org.bc.dietary.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.bc.sdak.utils.ClassUtil;

public class ModuleManager {

	private static Map<String,Class<?>> modules = new HashMap<String,Class<?>>();
	
	public static void add(String packageName){
		List<Class<?>> list = ClassUtil.getClasssFromPackage(packageName);
		for(Class<?> clazz : list){
			Module xx = clazz.getAnnotation(Module.class);
			if(xx!=null){
				modules.put(StringUtils.removeEnd(xx.name(),"/"),clazz);
				System.out.println("loaded module "+clazz.getName());
			}
		}
	}
	
	public static Object getModuleInstance(String moduleKey){
		if(!modules.containsKey(moduleKey)){
			return null;
		}
		Class<?> clazz = modules.get(moduleKey);
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
