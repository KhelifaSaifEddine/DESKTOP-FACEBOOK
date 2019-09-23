package com.usthb.modeles;

import java.io.Serializable;

public class R�action implements Serializable {
	public enum Type {j_aime, j_adore, gai, triste, en_col�re};
	private static final long serialVersionUID = -6856873016612794800L;
	private String username;
	private Type type;
	public R�action(String username,Type type) {
		//super();
		this.username = username;
		this.type=type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
}
