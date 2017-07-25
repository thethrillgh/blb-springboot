package com.putnam.buylocalbonds.ServerMain;

import java.util.ArrayList;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;


public class App 
{
    public static void main( String[] args )
    {
    	ResourceConfig config = new ResourceConfig();
    	
//    	ArrayList<String> restPackages = new ArrayList<String>();
//    	restPackages.add("com.putnam.buylocalbonds.RestHandlers");
//    	
//    	String packs = "";
//    	if(!restPackages.isEmpty()) {
//    		packs = restPackages.toString().substring(0, restPackages.size() - 1);
//        	System.out.print(packs);
//    	}
//    	else {
//    		System.out.println("<<<<< Classes not loaded as a resource >>>>>");
//    	}
    	
    	 config.packages("com.putnam.buylocalbonds.RestHandlers");
    	 ServletHolder servlet = new ServletHolder(new ServletContainer(config));

 		int port_num = 9090;

 		if(args.length > 0) {
 			try {
 				port_num = Integer.parseInt(args[0]);
 			}catch (Exception e) {
 				e.printStackTrace();
 			}
 		}

    	Server server = new Server(port_num);
    	ServletContextHandler context = new ServletContextHandler(server, "/*");
    	 context.addServlet(servlet, "/*");


    	try {
    	     server.start();
    	     server.join();
    	 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
    	     server.destroy();
    	 }
    }
}
