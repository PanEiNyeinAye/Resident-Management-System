package com.java.beans;

public class Receiver {
	private int receiverId;
	private int confirm;
	private int senderId;
	private int wardASId;
	private WardAS wardAS;
	private Sender sender;
	
	public Receiver() {}
	
	public Receiver(WardAS wardAS) {
		this.wardAS = wardAS;
	}
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	public int getConfirm() {
		return confirm;
	}
	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getWardASId() {
		return wardASId;
	}
	public void setWardASId(int wardASId) {
		this.wardASId = wardASId;
	}
	public WardAS getWardAS() {
		return wardAS;
	}
	public void setWardAS(WardAS wardAS) {
		this.wardAS = wardAS;
	}
	public Sender getSender() {
		return sender;
	}
	public void setSender(Sender sender) {
		this.sender = sender;
	}
}
