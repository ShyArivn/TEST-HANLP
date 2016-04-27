package com.hanlpweb.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/hanlp")
@Controller
public class HanlpTest {

    private static Logger logger = Logger.getLogger(HanlpTest.class);

	@RequestMapping("/test")
	public String testShow(){
		return "hanlpweb-test";
	}

	@RequestMapping("/doc/{filename}")
	@ResponseBody
	public FileSystemResource docDownload(@PathVariable("filename") String filename){
		return new FileSystemResource();
	}
}
