package org.bc.dietary;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.bc.dietary.entity.Food;
import org.bc.dietary.web.ModuleManager;
import org.bc.sdak.SessionFactoryBuilder;
import org.hibernate.cfg.AvailableSettings;

public class StartUpListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		initDataSource();
		initModule();
	}

	private void initModule() {
		ModuleManager.add("org.bc.dietary");
	}

	public static void initDataSource(){
		Map<String,String> settings = new HashMap<String , String>();
//		settings.put(AvailableSettings.URL, "jdbc:mysql://localhost:3306/ihouse?characterEncoding=utf-8");
//		settings.put(AvailableSettings.USER, "root");
//		settings.put(AvailableSettings.PASS, "");
		settings.put(AvailableSettings.SHOW_SQL, "false");
		settings.put(AvailableSettings.DRIVER, "com.mysql.jdbc.Driver");
		settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
		settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
		settings.put(AvailableSettings.HBM2DDL_AUTO, "update");
		settings.put(AvailableSettings.POOL_SIZE, "1");
		
		settings.put(AvailableSettings.PROXOOL_XML, "proxool.xml");//相对目录为classes
		settings.put(AvailableSettings.PROXOOL_EXISTING_POOL, "false");
		settings.put(AvailableSettings.PROXOOL_POOL_ALIAS, "mySqlProxool");
		
		settings.put("annotated.packages", Food.class.getPackage().getName());
		SessionFactoryBuilder.applySettings(settings);
	}
}
