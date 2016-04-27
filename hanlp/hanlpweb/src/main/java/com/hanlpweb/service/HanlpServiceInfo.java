package com.hanlpweb.service;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class HanlpServiceInfo extends ServiceInfo {
	
	private int port;
	private String host;
	private String uri;

	@Autowired
	public HanlpServiceInfo(@Value("10.11.161.41") String host,
							@Value("23456") int port){
		this.port = port;
		this.host = host;
	}
	
	@Override
	public String getHost(){
		return this.host;
	}

	public void setHost(String host){
		this.host = host;
	}
	
	@Override
	public int getPort(){
		return this.port;
	}

	public void setPort(int port){
		this.port = port;
	}

	@Override
	public String getUri(){
		return this.uri;
	}

	public void setUri(String uri){
		this.uri = uri;
	}
	
	public URI getURI(){
		try{
			return new URI(PROTO,CLIENT,host,port,uri,null,null);
		}catch(URISyntaxException e){
			return null;
		}	
	}
	
}
