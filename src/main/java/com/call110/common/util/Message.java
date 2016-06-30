package com.call110.common.util;

public class Message {

	private String title;
	private int code;
	private String msg;
	private boolean successful;
	
	
	public Message() {
		// TODO Auto-generated constructor stub
	}
	public Message(String title,int code,String msg ,boolean successful) {
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
