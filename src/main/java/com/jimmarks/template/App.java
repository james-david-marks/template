/* Copyright (C) 2020 James D. Marks. All Rights Reserved.                                                                   */
/* You may use, distribute and modify this code under the terms of the MIT License https://en.wikipedia.org/wiki/MIT_License */
package com.jimmarks.template;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//`import org.springframework.web.servlet.View;
import org.springframework.web.servlet.View;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class App extends SpringBootServletInitializer {
	
	public static final Logger applogger = getAppLogger(App.class, "template-app", false);
	public static final Logger debuglogger = getAppLogger(App.class, "template-debug", true);
	
    public static void main(String[] args) {
    	BasicConfigurator.configure();
        SpringApplication.run(App.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<App> applicationClass = App.class;
    
    @RestController
    @RequestMapping("api")
    public class AppController {
 
        @GetMapping("/")
        public String pingHandler() {
        	return "Api service online.";
        }
    }
    
	public static Logger getAppLogger(Class<?> classObject, String filename, boolean forDebugOnly) {
		final String catalina_base = System.getProperty("catalina.base");
		
        String conversionPattern = "[%d] %5p %m (%C::%M:%L)%n";
        Level consoleLevel = Level.OFF;
        Level errorLevel = Level.OFF;
        Level fileLevel = Level.OFF;
		if(forDebugOnly)
		{
			conversionPattern = "[%d] %5p %m%n";
			
			consoleLevel = Level.ALL;
			errorLevel = Level.OFF;
			fileLevel = Level.ALL;
		}
		else
		{
			consoleLevel = Level.ALL;
			errorLevel = Level.ERROR;
			fileLevel = Level.ALL;
		}
		
		String loggerName = forDebugOnly ? String.format("%s-debug", classObject.getName()) : classObject.getName();
		Logger logger = Logger.getLogger(loggerName);
		
        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern(conversionPattern);

        logger.setLevel(Level.ALL);
		
        ConsoleAppender consoleAppender_stdio = new ConsoleAppender();
        consoleAppender_stdio.setThreshold(consoleLevel);
        consoleAppender_stdio.setLayout(layout);
        consoleAppender_stdio.activateOptions();
		logger.addAppender(consoleAppender_stdio);

		ConsoleAppender consoleAppender_stderr = new ConsoleAppender();
		consoleAppender_stderr.setThreshold(errorLevel);
		consoleAppender_stderr.setLayout(layout);
		consoleAppender_stderr.activateOptions();
		logger.addAppender(consoleAppender_stderr);
		
		FileAppender fileAppender = new FileAppender();
        fileAppender.setThreshold(fileLevel);
        fileAppender.setFile(String.format("%s/logs/%s.log", catalina_base, filename));
        fileAppender.setLayout(layout);
        fileAppender.activateOptions();
        logger.addAppender(fileAppender);
               
		logger.info(String.format("Starting logger: %s", logger.getName()));
        return logger;
	}
}