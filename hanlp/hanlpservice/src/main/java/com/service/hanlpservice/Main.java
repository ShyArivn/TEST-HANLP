package com.service.hanlpservice;

import hanlp.server.HttpServer;
import hanlp.server.RestfulHttpServer;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import java.util.*;

public class Main
{
    public static void main( String[] args )
    {
		try{
			HttpServer server = new RestfulHttpServer(23456);
			server.start();
			System.out.println( "Hello World!" );
		}catch(Exception e){
			e.printStackTrace();
		}
    }
}
