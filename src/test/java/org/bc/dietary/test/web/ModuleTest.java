package org.bc.dietary.test.web;

import org.bc.dietary.web.ModuleManager;
import org.junit.Test;

public class ModuleTest {

	@Test
	public void testModuleManager(){
		ModuleManager.add("org.bc.dietary");
		ModuleManager.getHandler(null);
	}
	
}
