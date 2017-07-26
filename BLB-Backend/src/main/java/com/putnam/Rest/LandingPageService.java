package com.putnam.Rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class LandingPageService {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	
	public InputStream serveLandingPage() throws Exception {
		File f = new File("");
		return new FileInputStream(f);
	}
}
