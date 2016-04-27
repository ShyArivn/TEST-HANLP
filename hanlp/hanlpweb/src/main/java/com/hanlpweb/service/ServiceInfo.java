package com.hanlpweb.service;

import java.net.URI;

public abstract class ServiceInfo {
	
	//default 	
	int PORT = 8090;
	String PROTO = "http";
	String CLIENT = "jclient";

	String getHost(){
		return "localhost";
	}

	int getPort(){
		return PORT;
	}

	String getUri(){
		return "";
	}

	abstract public void setUri(String uri);
	abstract public URI getURI();
	
}
