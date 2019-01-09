package org.ipa.de.extraction.text;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHandler {

	public static String getPropertyValue(String propertyName) {
		Properties property = new Properties();
		
		//Fetching data from properties file.
		FileInputStream file = null;
		
		try {

			file = new FileInputStream(System.getProperty("user.dir")+"/src/resources/config.properties");

		    // load a properties file
		    property.load(file);
		    
		 // get the property value and print it 
//		    System.out.println("File location: "+property.getProperty(propertyName));
		    
		}catch (IOException ex) {
		    ex.printStackTrace();
		} finally {
		    if (file != null) {
		        try {
		        	file.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
		
		return property.getProperty(propertyName);
	}
}
