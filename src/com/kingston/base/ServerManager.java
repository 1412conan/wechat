package com.kingston.base;

import javafx.application.Platform;

import com.kingston.entry.StartClient;
import com.kingston.net.Packet;

public enum ServerManager {
	INSTANCE;

	public void sendServerRequest(Packet request){
		BaseDataPool.channelContext.writeAndFlush(request);
	}
	
	/**
	 * ������ת�Ƹ�fxapplication�߳��ӳ�ִ��
	 * @param task
	 */
	public void FXApplicationThreadExcute(Runnable task){
		Platform.runLater(task);
	}
}
