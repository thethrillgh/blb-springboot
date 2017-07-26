package com.putnam.ServerMain;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.putnam.Config.AppConfig;

public class Starter {
	public static void main( final String[] args ) throws Exception {
		
 		int port_num = 9090;

 		if(args.length > 0) {
 			try {
 				port_num = Integer.parseInt(args[0]);
 			}catch (Exception e) {
 				e.printStackTrace();
 			}
 		}
		
		Server server = new Server( port_num );
		        
 		final ServletHolder servletHolder = new ServletHolder( new CXFServlet() );
 		final ServletContextHandler context = new ServletContextHandler(); 		
 		context.setContextPath( "/" );
 		context.addServlet( servletHolder, "/*" ); 	
 		context.addEventListener( new ContextLoaderListener() );
 		
 		context.setInitParameter( "contextClass", AnnotationConfigWebApplicationContext.class.getName() );
 		context.setInitParameter( "contextConfigLocation", AppConfig.class.getName() );
 		 		 		
        server.setHandler( context );
        server.start();
        server.join();	
	}
}

