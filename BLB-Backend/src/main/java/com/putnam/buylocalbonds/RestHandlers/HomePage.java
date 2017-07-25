package com.putnam.buylocalbonds.RestHandlers;

import javax.json.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("blb")
public class HomePage {
	
	  @GET
	  //@Path("home")
	  @Produces(MediaType.TEXT_HTML)
	  public String helloWorld() {
		  
	    return "<h1>Our Server is working</h1>";
	  }
	  
	  @POST
	  @Path("/test")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.TEXT_HTML)
	  public String someMethod(JsonObject input) {
		  
		  if(!input.isNull("test")) {
			  System.out.println(input.getString("test"));
		  }
		  
		  return "worked";
	  }
}