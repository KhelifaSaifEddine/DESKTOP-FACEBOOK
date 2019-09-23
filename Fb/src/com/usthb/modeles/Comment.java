package com.usthb.modeles;

public class Comment {
	private String username;
	private StringBuilder comment;
	public Comment(String username, StringBuilder comment) {
		this.username = username;
		this.comment = comment;
	}
	public String getUsername() {
		return username;
	}
	public StringBuilder getComment() {
		return comment;
	}
}
