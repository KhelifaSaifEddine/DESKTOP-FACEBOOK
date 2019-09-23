package com.usthb.modeles;

import java.io.Serializable;

public class Notification implements Serializable {
	private static final long serialVersionUID = -393513874292283396L;
	private Abonné abonné;
	private int type;
	private StringBuilder information=new StringBuilder("");
	private Object info;
	private boolean state;
	public Notification(Object info,Abonné abonné) {
		this.state=false;
		this.abonné=abonné;
		this.info=info;
		if(info instanceof Post) {type=0; information.append(abonné.getNom()+" "+abonné.getPrénom()+" a publié sur son mur");}
		if(info instanceof Comment) {type=1; information.append(abonné.getNom()+" "+abonné.getPrénom()+" a réagi votre publication");}
		if(info instanceof Réaction) {type=2; information.append(abonné.getNom()+" "+abonné.getPrénom()+" a commenté votre publication");}
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
	public Abonné getAbonné() {
		return abonné;
	}
}
