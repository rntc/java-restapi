package com.rntc.api.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * This class it's a customized exception when an error occurs in the request.
 * 
 * @author reis
 * @since 05/28/2017
 * @version 1.0
 */
public class APIException extends WebApplicationException {

	private static final long serialVersionUID = 1L;
	
	public APIException(int status, String message){
		super(Response.status(status).entity(new Error(status, message)).build());
	}
}
