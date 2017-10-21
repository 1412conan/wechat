package com.kingston.logic.friend.vo;

import org.apache.commons.lang3.StringUtils;

import com.kingston.net.message.ByteBufBean;

import io.netty.buffer.ByteBuf;

public class FriendItemVo extends ByteBufBean {

	private long userId;

	private String userName;
	/** 备注 */
	private String remark;
	/** 个性签名　*/
	private String signature;

	private byte sex;
	/** 所属好友分组 */
	private int group;
	/** 分组备注 */
	private String groupName;

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remarks) {
		this.remark = remarks;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public byte getSex() {
		return sex;
	}
	public void setSex(byte sex) {
		this.sex = sex;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getFullName() {
		if (StringUtils.isEmpty(signature)) {
			return this.userName;
		}
		return this.userName + "(" + this.remark + ")";
	}

	@Override
	public void writeBody(ByteBuf buf) {
		buf.writeLong(userId);
		writeUTF8(buf, userName);
		writeUTF8(buf, remark);
		writeUTF8(buf, signature);
		buf.writeByte(sex);
		buf.writeInt(group);
		writeUTF8(buf, groupName);
	}

	@Override
	public void readBody(ByteBuf buf) {
		this.userId = buf.readLong();
		this.userName = readUTF8(buf);
		this.remark = readUTF8(buf);
		this.signature = readUTF8(buf);
		this.sex = buf.readByte();
		this.group = buf.readInt();
		this.groupName = readUTF8(buf);
	}

	@Override
	public String toString() {
		return "FriendItemVo [userId=" + userId + ", userName=" + userName + ", remark=" + remark + ", signature="
				+ signature + ", sex=" + sex + ", group=" + group + ", groupName=" + groupName + "]";
	}

}
