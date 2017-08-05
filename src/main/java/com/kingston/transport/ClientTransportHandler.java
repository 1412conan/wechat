package com.kingston.transport;

import com.kingston.base.BaseDataPool;
import com.kingston.net.Packet;
import com.kingston.net.PacketManager;
import com.kingston.service.login.ServerLogin;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class ClientTransportHandler extends ChannelHandlerAdapter{


	public ClientTransportHandler(){

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx){
		BaseDataPool.channelContext = ctx;
		
		 ServerLogin loginPact = new ServerLogin();  
		 	loginPact.setUserId(1);
	        loginPact.setUserName("Netty爱好者");  
	        loginPact.setUserPwd("world");  
	        ctx.writeAndFlush(loginPact);  
	        System.err.println("向服务端发送登录请求");  
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception{
		Packet  packet = (Packet)msg;
		PacketManager.INSTANCE.execPacket(packet);
	}

	@Override
	public void close(ChannelHandlerContext ctx,ChannelPromise promise){
		System.err.println("TCP closed...");
		ctx.close(promise);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.err.println("客户端关闭1");
	}

	@Override
	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		ctx.disconnect(promise);
		System.err.println("客户端关闭2");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.err.println("客户端关闭3");
		//	        ctx.fireExceptionCaught(cause);
		Channel channel = ctx.channel();
		cause.printStackTrace();
		if(channel.isActive()){
			System.err.println("simpleclient"+channel.remoteAddress()+"异常");
		}
	}
}
