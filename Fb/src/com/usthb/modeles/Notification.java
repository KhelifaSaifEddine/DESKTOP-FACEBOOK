package com.usthb.modeles;

import java.io.Serializable;

public class Notification implements Serializable {
	private static final long serialVersionUID = -393513874292283396L;
	private Abonn� abonn�;
	private int type;
	private StringBuilder information=new StringBuilder("");
	private Object info;
	private boolean state;
	public Notification(Object info,Abonn� abonn�) {
		this.state=false;
		this.abonn�=abonn�;
		this.info=info;
		if(info instanceof Post) {type=0; information.append(abonn�.getNom()+" "+abonn�.getPr�nom()+" a publi� sur son mur");}
		if(info instanceof Comment) {type=1; information.append(abonn�.getNom()+" "+abonn�.getPr�nom()+" a r�agi votre publication");}
		if(info instanceof R�action) {type=2; information.append(abonn�.getNom()+" "+abonn�.getPr�nom()+" a comment� votre publication");}
	}
	public Object getInfo() {
		return info;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public int getType() {
		return type;
	}
	public StringBuilder getInformation() {
		return information;
	}
	public Abonn� getAbonn�() {
		return abonn�;
	}
}
