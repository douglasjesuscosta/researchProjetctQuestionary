package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFactory {
	
	private static PropertiesFactory propertiesFactory;
	
	private PropertiesFactory(){
		
	}
	
	public static PropertiesFactory getAnInstance(){
		if(propertiesFactory == null){
			propertiesFactory = new PropertiesFactory();
		}
		return propertiesFactory;
	}
	
	public Properties getPropertie(String propertieName) throws IOException {
		Properties props = new Properties();
		try (final InputStream stream = this.getClass().getResourceAsStream("program.properties")) {
		    props.load(stream);
		}
		return props;
		
	}
	
}
