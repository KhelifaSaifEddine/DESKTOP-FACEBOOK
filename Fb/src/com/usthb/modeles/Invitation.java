package com.usthb.modeles;

import java.io.Serializable;

public class Invitation implements Serializable {
	private static final long serialVersionUID = -2687376720775700175L;
	private String username,message;
	private boolean state=false;

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public Invitation(String username, String message, boolean state) {
		this.username = username;
		this.message = message;
		this.state = state;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
