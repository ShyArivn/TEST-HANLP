package hanlp.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;


public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {


	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		
		ChannelPipeline p = ch.pipeline();
		
		p.addLast("decoder", new HttpRequestDecoder());
		p.addLast("encoder", new HttpResponseEncoder());
		p.addLast("aggregator", new HttpObjectAggregator(1048576));
		p.addLast("deflater", new HttpContentCompressor());
		p.addLast("handler", new HttpServerHandler());
	}
}
