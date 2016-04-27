package hanlp.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;



public class RestfulHttpServer implements HttpServer{
	
	public int Port;	
	
	public RestfulHttpServer(int port){
		this.Port = port;
	}

	public void start() throws Exception {
	
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();		
		try{
			ServerBootstrap bp = new ServerBootstrap();			
			
			bp.option(ChannelOption.SO_BACKLOG, 1024);
			bp.group(bossGroup, workerGroup);
			bp.channel(NioServerSocketChannel.class);
			bp.handler(new LoggingHandler(LogLevel.INFO));
			bp.childHandler(new HttpServerInitializer());
			
			ChannelFuture cf = bp.bind(Port).sync();
			cf.channel().closeFuture().sync();
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}	
	}	
}
