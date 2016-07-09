package com.kingston.net;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public enum PacketManager {

	INSTANCE;
	
	public  void execPacket(Packet pact){
		if(pact == null) return;
		try {
			Method m = pact.getClass().getMethod("execPacket");
			m.invoke(pact, null);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public  Packet createNewPacket(short packetType){
		Class<? extends Packet> packetClass = PacketType.getPacketClassBy(packetType);
		if(packetClass == null){
			throw new IllegalPacketException("����Ϊ"+packetType+"�İ����岻����");
		}
		Packet packet = null;
		try {
			packet = (Packet)packetClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalPacketException("����Ϊ"+packetType+"�İ�ʵ����ʧ��");
		}

		return packet;
	}
	
}
