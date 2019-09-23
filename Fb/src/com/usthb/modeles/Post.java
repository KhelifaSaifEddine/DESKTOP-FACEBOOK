package com.usthb.modeles;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Post implements Serializable {
	private static final long serialVersionUID = -4406447425631262410L;
	private HashMap<Integer,ArrayList<String>> commentaires;
	private String contenu;
	private boolean épinglée;
	private int id;
	private int nombrePartage;
	private ArrayList<Réaction> réactions;
	private Visibilité visibilité;
	public Visibilité getVisibilité() {
		return visibilité;
	}
	public void setVisibilité(Visibilité visibilité) {
		this.visibilité = visibilité;
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
	public ArrayList<Réaction> getRéactions() {
		return réactions;
	}
	public boolean isÉpinglée() {
		return épinglée;
	}
	public void setÉpinglée(boolean épinglée) {
		this.épinglée = épinglée;
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
	public Post(String contenu, Visibilité visibilité) {
		counter++;
		id=counter;
		réactions=new ArrayList<>();
		commentaires=new HashMap<>();
		this.contenu = contenu;
		this.visibilité= visibilité;
	}
}

