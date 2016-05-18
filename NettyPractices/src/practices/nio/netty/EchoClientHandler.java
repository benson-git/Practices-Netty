package practices.nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@Sharable // #1 Annotate with @Sharable as it can be shared between channels
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

  /**
   * This method is called once the connection is established.
   */
  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    ctx.write(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));// #2 Write message now
                                                                          // that channel is
                                                                          // connected
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, // #5 Log exception and close channel
      Throwable cause) {
    cause.printStackTrace();
    ctx.close();
  }

  /**
   * The method is called once data is received.
   * Note that the bytes may be fragmented, which means that if the server writes 5 bytes its not 
    guaranteed that all 5 bytes will be received at once. For 5 bytes, the channelRead0()method 
    could be called twice, for example. The first time it may be called with a ByteBuf that holds 3 
    bytes and  the second  time with a ByteBuf  that holds 2 bytes. The only guarantee  is  that  the 
    bytes will be received in the same order as theyre sent. But this is only true for TCP or other 
    stream-orientated protocols.  
   */
  @Override
  protected void messageReceived(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
    // #3 Log received message as hexdump
    System.out
        .println("Client received:" + ByteBufUtil.hexDump(msg.readBytes(msg.readableBytes())));

  }

}
