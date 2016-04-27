package hanlp.server;

import java.io.UnsupportedEncodingException;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import io.netty.handler.codec.http.HttpHeaders.Values;
import static io.netty.handler.codec.http.HttpHeaders.Values;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpContent;

import io.netty.handler.codec.http.HttpResponseStatus;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.*;
import io.netty.buffer.ByteBuf;

import hanlp.route.RouteUri;
import hanlp.route.RouteDataFactory;
import hanlp.format.Result;
import hanlp.code.CommonCode;

public class HttpServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String rqContent = null;
		Result rpContent = null;

		boolean keepAlive = false;
		String Uri = null;
		HttpMethod method = null;

        if (msg instanceof HttpRequest) {

            HttpRequest req = (HttpRequest) msg;
            if (HttpHeaders.is100ContinueExpected(req)) {
                ctx.write(new DefaultFullHttpResponse(HTTP_1_1, CONTINUE));
            }

            keepAlive = HttpHeaders.isKeepAlive(req);
            //method = req.getMethod();
			Uri = req.getUri();

        }

		if (msg instanceof HttpContent) {
			
			HttpContent httpContent = (HttpContent)	msg;
			ByteBuf content = httpContent.content();  
            rqContent = getMessage(content);
			System.out.println(rqContent);
			
			rpContent = RouteUri.routeUri(Uri, RouteDataFactory.create(rqContent, "json"));	
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.valueOf(rpContent.getStatus()), Unpooled.wrappedBuffer(rpContent.toString().getBytes()));

            response.headers().set(CONTENT_TYPE, "application/json");
            response.headers().set(CONTENT_LENGTH, response.content().readableBytes());

            if (!keepAlive) {
               	ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
               	response.headers().set(CONNECTION, Values.KEEP_ALIVE);
               	ctx.write(response);
            }
		}
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
    
    private String getMessage(ByteBuf buf) {  
  
        byte[] con = new byte[buf.readableBytes()];  
        buf.readBytes(con);  
        try {  
            return new String(con, "UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            return null;  
        }  
    }  
}
