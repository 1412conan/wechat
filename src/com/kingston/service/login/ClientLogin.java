package com.kingston.service.login;

import io.netty.buffer.ByteBuf;

import com.kingston.net.Packet;
import com.kingston.net.PacketType;


public class ClientLogin extends Packet{

	private String alertMsg;
	private byte isValid;
	
	@Override
	public void writePacketMsg(ByteBuf buf) {
		writeUTF8(buf, alertMsg);
		buf.writeByte(isValid);
	}

	@Override
	public void readFromBuff(ByteBuf buf) {
		this.alertMsg = readUTF8(buf);
		this.isValid = buf.readByte();
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ClientLogin;
	}

	@Override
	public void execPacket() {
		System.err.println("�յ�����˵���֤��Ϣ��"+alertMsg);
		LoginManager.getInstance().receiveServerMsg(this);
	}

	public String getAlertMsg() {
		return alertMsg;
	}

	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}

	public byte getIsValid() {
		return isValid;
	}

	public void setIsValid(byte isValid) {
		this.isValid = isValid;
	}

}
