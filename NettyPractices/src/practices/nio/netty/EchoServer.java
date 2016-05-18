package practices.nio.netty;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
/**
 * 
 * @author benson.wang
 *
 */
public class EchoServer {
  private final int port;

  public EchoServer(int port) {
    this.port = port;
  }

  public void start() throws Exception {
    EventLoopGroup group = new NioEventLoopGroup();
    try {
      ServerBootstrap b = new ServerBootstrap(); // #1 Bootstraps the server
      b.group(group) // #2 Specifies NIO transport, local socket address
          .channel(NioServerSocketChannel.class) // #2
          .localAddress(new InetSocketAddress(port)) // #2
          .childHandler(new ChannelInitializer<SocketChannel>() { // #3 Adds handler to channel pipeline
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
              ch.pipeline().addLast(new EchoServerHandler()); // #4 Binds server, waits for server to close, and releases resources
            }
          });
      ChannelFuture f = b.bind().sync(); // #5
      System.out.println(EchoServer.class.getName() + //#6
         "started and listen on" + f.channel().localAddress());//#7
      f.channel().closeFuture().sync(); // #8
    } finally { // #9
      group.shutdownGracefully().sync(); // #10
    }
  }

  public static void main(String[] args) throws Exception {
//    if (args.length != 1) {
//      System.err.println("Usage:"+EchoServer.class.getSimpleName()+"<port>");
//    }
    int port = 1234;//Integer.parseInt(args[0]);
    new EchoServer(port).start();
  }
}
