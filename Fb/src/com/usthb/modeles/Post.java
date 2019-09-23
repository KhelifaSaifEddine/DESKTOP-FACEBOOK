package com.usthb.modeles;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Post implements Serializable {
	private static final long serialVersionUID = -4406447425631262410L;
	private HashMap<Integer,ArrayList<String>> commentaires;
	private String contenu;
	private boolean �pingl�e;
	private int id;
	private int nombrePartage;
	private ArrayList<R�action> r�actions;
	private Visibilit� visibilit�;
	public Visibilit� getVisibilit�() {
		return visibilit�;
	}
	public void setVisibilit�(Visibilit� visibilit�) {
		this.visibilit� = visibilit�;
	}
	public HashMap<Integer,ArrayList<String>> getCommentaires() {
		return commentaires;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public ArrayList<R�action> getR�actions() {
		return r�actions;
	}
	public boolean is�pingl�e() {
		return �pingl�e;
	}
	public void set�pingl�e(boolean �pingl�e) {
		this.�pingl�e = �pingl�e;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNombrePartage() {
		return nombrePartage;
	}
	public void setNombrePartage(int nombrePartage) {
		this.nombrePartage = nombrePartage;
	}
	private static int counter=-1;
	public Post(String contenu, Visibilit� visibilit�) {
		counter++;
		id=counter;
		r�actions=new ArrayList<>();
		commentaires=new HashMap<>();
		this.contenu = contenu;
		this.visibilit�= visibilit�;
	}
}

