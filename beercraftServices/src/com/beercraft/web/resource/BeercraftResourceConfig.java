package com.beercraft.web.resource;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * ResourceConfig is a Jersey extension of the normal jaxrs ApplicationConfig.
 */
@ApplicationPath("/rest")
public class BeercraftResourceConfig extends ResourceConfig{
	
	public BeercraftResourceConfig(){
		packages("com.beercraft.web.resource");
	}

}
