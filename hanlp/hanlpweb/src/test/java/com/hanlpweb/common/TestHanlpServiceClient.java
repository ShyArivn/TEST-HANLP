package com.hanlpweb.common;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/hanlpweb-servlet.xml")
public class TestHanlpServiceClient {

	@Autowired
	private HanlpServiceClient client;

	@Test
	public void testPost(){
		String uri = "/HanlpDependency/parser";
		client.setParam("text","北京欢迎你，为你开天辟地");
		client.setParam("format","plain");
		client.setParam("parser","neural");
		JSONObject json = client.post(uri);
		System.out.println(json.toString());
		assertEquals("OK",json.getString("state"));
	}
}
