package com.kingston.logic.friend.message.res;

import com.kingston.logic.friend.FriendManager;
import com.kingston.net.message.AbstractPacket;
import com.kingston.net.message.PacketType;

import io.netty.buffer.ByteBuf;

/**
 * 好友登录
 * @author kingston
 */
public class ResFriendLoginPacket extends AbstractPacket {

	private long friendId;

	public long getFriendId() {
		return friendId;
	}

	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ResFriendLogin;
	}

	@Override
	public void execPacket() {
		FriendManager.getInstance().onFriendLogin(this.friendId);
	}

	@Override
	public void writeBody(ByteBuf buf) {
		buf.writeLong(friendId);
	}

	@Override
	public void readBody(ByteBuf buf) {
		this.friendId = buf.readLong();
	}

}
