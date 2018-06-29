package net.engining.gateway.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 简易授信申请消息(队列).
 */
public class DemoMessage implements Serializable {

	private static final long serialVersionUID = 1827318462748214L;

	// 消息ID.
	private String messageId;
	// 交易状态
	private String tranStatus;
	// 交易状态描述
	private String tranStatusMessage;
	// 消息提交时间
	private Date postedTime;

	public DemoMessage() {
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getTranStatus() {
		return tranStatus;
	}

	public void setTranStatus(String tranStatus) {
		this.tranStatus = tranStatus;
	}

	public String getTranStatusMessage() {
		return tranStatusMessage;
	}

	public void setTranStatusMessage(String tranStatusMessage) {
		this.tranStatusMessage = tranStatusMessage;
	}

	public Date getPostedTime() {
		return postedTime;
	}

	public void setPostedTime(Date postedTime) {
		this.postedTime = postedTime;
	}

	@Override
	public String toString() {
		return "简易授信申请队列中的消息{" + "messageId='" + messageId + '\'' + ", tranStatus='" + tranStatus + '\''
				+ ", postedTime=" + postedTime + '}';
	}
}
