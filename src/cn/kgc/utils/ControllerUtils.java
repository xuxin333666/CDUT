package cn.kgc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import cn.kgc.controller.CoreController;


public class ControllerUtils {
	private static final String PROPERTIES_URL = "./controller.properties";
	
	public static Properties p;
	
	static {
		InputStream is = ControllerUtils.class.getClassLoader().getResourceAsStream(PROPERTIES_URL);
		p = new Properties();
		try {
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static CoreController getController(String Name) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String controllerStr = p.getProperty(Name);
		return (CoreController) Class.forName(controllerStr).newInstance();
	}

}
