package com.jpknox.io.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by joaok on 04/11/2018.
 */
public class PropertyUtil {

	public Properties getPropertyFile(String propertyFileName) {
		Properties props = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
		if (inputStream != null) {
			try {
				props.load(inputStream);
			} catch (IOException e) {
				System.out.println("Property file " + propertyFileName + " not found on classpath.");
				e.printStackTrace();
			}
		}
		return props;
	}

}
