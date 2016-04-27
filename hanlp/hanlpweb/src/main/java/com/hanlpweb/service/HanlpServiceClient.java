package com.hanlpweb.service;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.json.JSONObject;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.commons.io.IOUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component("hanlpserviceclient")
public class HanlpServiceClient {

	private CloseableHttpClient client;
	private ServiceInfo info;
	private JSONObject json;

	@Autowired
	public HanlpServiceClient(@Qualifier("hanlpServiceInfo") ServiceInfo info){
		this.info = info;
		this.client = HttpClients.createDefault();
		this.json = new JSONObject();
	}

	public JSONObject post(String uri){

		JSONObject result = new JSONObject();
		
		info.setUri(uri);
		HttpPost post = new HttpPost(info.getURI());
		BasicHttpEntity entity = new BasicHttpEntity();

		entity.setContentType(new BasicHeader("Content-Type","text/plain; charset=UTF-8"));
        entity.setContent(new ByteArrayInputStream(json.toString().getBytes()));
        post.setEntity(entity);

        try {
            HttpResponse response = client.execute(post);
            HttpEntity msg = response.getEntity();
            InputStream stream = msg.getContent();
            result.put("state","OK");
            result.put("data",IOUtils.toString(stream, "UTF-8"));
            return result;
        }catch(IOException e){
        	result.put("state","ERROR");
        	result.put("data",e.getMessage());
            return result;
        }finally {
            try{
                client.close();
            }catch(IOException e){
                result.put("state","ERROR");
        		result.put("data",e.getMessage());
            	return result;
            }
        }

	}

	public void setParam(String key, String val){
		json.put(key,val);
	}

	public String getParam(String key){
		return json.getString(key);
	}
	
}
