package com.jimmarks.template;

import org.apache.commons.configuration.PropertiesConfiguration;

public final class Constants {
	
	public static final String APPLICATION_PROPERTIES = "application.properties";
	public static final PropertiesConfiguration appconfig = getAppconfig();
	public static PropertiesConfiguration getAppconfig() {
		
		PropertiesConfiguration config = new PropertiesConfiguration();
		try
		{
			config.load(APPLICATION_PROPERTIES);
		}
		catch(Exception e)
		{
			App.applogger.error("application.properties file failure");
		}
		return config;
	}
	
	public static String getString(String propertyName)
	{
		String value = null;
		switch(propertyName)
		{
			default:
				value = appconfig.getString(propertyName);
				break;
		}
		return value;
	}
}
