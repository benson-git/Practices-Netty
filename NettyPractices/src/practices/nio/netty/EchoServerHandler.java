package practices.nio.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerAdapter;

@Sharable // #1 Annotate with @Sharable to share between channels
public class EchoServerHandler extends ChannelHandlerAdapter {
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    System.out.println("Server received:" + msg);
    ctx.write(msg); // #2 Write the received messages back . 
                    // Be aware that this will not flush the messages to the remote peer yet.
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) {
 // #3  Flush all previous written messages (that are pending) to the remote peer, and close the channel
 //   after the operation is complete
    ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE); 
                                                                                       
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();  // #4 Log exception
    ctx.close(); // #5 Close channel on exception
  }
}