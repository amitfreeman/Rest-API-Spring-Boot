package com.amitpar.restapi.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


@Path("injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@GET
	@Path("annotations")
	public String getParamUsingAnnotations(@MatrixParam("Param") String matrixParam,
			                               @HeaderParam("authSessionId") String header,
			                               @CookieParam("name") String cookie) {
		return "Matrix Param: "+matrixParam+" Header param: "+header+" Cookie param: "+cookie;
	}
	
	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders httpHeader) {
		String path=uriInfo.getAbsolutePath().toString();
		String cookies=httpHeader.getCookies().toString();
		String header=httpHeader.getRequestHeaders().toString();
		return "Path : "+path+" cookies : "+cookies+" header : "+header;
	}

}
