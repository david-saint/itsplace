package com.mincoms.book.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
public class InitApplication  implements ServletContextListener {

	
	  public static final String AppMode = "AppMode";

	  private  final String PATH = "/Init.properties";

	  private final Logger logger = Logger.getLogger(getClass().getName());

	  public void contextInitialized(ServletContextEvent event) {
	    String mode = getKey();
	    event.getServletContext().setAttribute(AppMode, mode);
	    if(mode.equals("Development")){
	    	 System.out.println(mode);
	 	    
	    }
	    
	  }

	  protected String getKey() {
	    InputStream stream = Thread.currentThread().getContextClassLoader()
	        .getResourceAsStream(PATH);
	    if (stream == null) {
	      throw new IllegalStateException("Could not find file " + PATH +
	          " on web resources)");
	    }
	    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
	    try {
	      String key = reader.readLine();
	      	
	      return key;
	    } catch (IOException e) {
	      throw new RuntimeException("Could not read file " + PATH, e);
	    } finally {
	      try {
	        reader.close();
	      } catch (IOException e) {
	        logger.log(Level.WARNING, "Exception closing " + PATH, e);
	      }
	    }
	  }

	  public void contextDestroyed(ServletContextEvent event) {
	  }

	}
