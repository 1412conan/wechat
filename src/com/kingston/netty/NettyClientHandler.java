package com.kingston.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

import com.kingston.base.BaseDataPool;
import com.kingston.net.Packet;
import com.kingston.net.PacketManager;
import com.kingston.service.login.ServerLogin;

public class NettyClientHandler extends ChannelHandlerAdapter{


	public NettyClientHandler(){

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx){
		ServerLogin loginPact = new ServerLogin();
		loginPact.setUserName("Netty������");
		loginPact.setUserPwd("world");
		loginPact.setUserId(1234);
		ctx.writeAndFlush(loginPact);
		System.err.println("�����˷��͵�¼����");
		BaseDataPool.channelContext = ctx;
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
		System.err.println("�ͻ��˹ر�1");
	}

	@Override
	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		ctx.disconnect(promise);
		System.err.println("�ͻ��˹ر�2");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.err.println("�ͻ��˹ر�3");
		//	        ctx.fireExceptionCaught(cause);
		Channel channel = ctx.channel();
		cause.printStackTrace();
		if(channel.isActive()){
			System.err.println("simpleclient"+channel.remoteAddress()+"�쳣");
			//			    ctx.close();
		}
	}
}
