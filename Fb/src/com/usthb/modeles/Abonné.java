package com.usthb.modeles;

import java.io.Serializable;
import java.util.ArrayList;

public class Abonné implements Serializable {
	private static final long serialVersionUID = 834079609603324074L;
	private ArrayList<String> amis;
	private boolean gender,online;
	public ArrayList<String> getAmis() {
		return amis;
	}
	private long id;
	private ArrayList<Invitation> invitations;
	private ArrayList<Notification> notifications;
	private ArrayList<Post> publications;
	private String username,password,nom,prénom,date_naiss,specialité,fonction,niveauEduc;
	public String getDate_naiss() {
		return date_naiss;
	}
	public void setDate_naiss(String date_naiss) {
		this.date_naiss = date_naiss;
	}
	private static long counter=-1;
	public String getFonction() {
		return fonction;
	}
	public long getId() {
		return id;
	}
	public String getNiveauEduc() {
		return niveauEduc;
	}
	public String getNom() {
		return nom;
	}
	public String getPassword() {
		return password;
	}
	public String getPrénom() {
		return prénom;
	}
	public String getSpecialité() {
		return specialité;
	}
	public String getUsername() {
		return username;
	}
	public boolean isGender() {
		return gender;
	}
	public boolean isOnline() {
		return online;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setNiveauEduc(String niveauEduc) {
		this.niveauEduc = niveauEduc;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPrénom(String prénom) {
		this.prénom = prénom;
	}
	public void setSpecialité(String specialité) {
		this.specialité = specialité;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Abonné(boolean gender, String username, String password, String nom, String prénom, String specialité, String fonction, String niveauEduc) {
		amis=new ArrayList<>();
		invitations=new ArrayList<>();
		publications=new ArrayList<>();
		notifications=new ArrayList<>();
		counter++;
		id=counter;
		this.gender = gender;
		this.username = username;
		this.password = password;
		this.nom = nom;
		this.prénom = prénom;
		this.specialité = specialité;
		this.fonction = fonction;
		this.niveauEduc = niveauEduc;
	}
	public void addPost(Post post) {
		publications.add(0,post);
	}
	public void removePost(Post post) {
		publications.remove(post);
	}
	public void addNotification(Notification notification) {
		notifications.add(notification);
	}
	public void removeNotification(Notification notification) {
		notifications.remove(notification);
	}
	public void addInvitation(Invitation invitation) {
		invitations.add(0,invitation);
	}
	public void removeInvitation(Invitation invitation) {
		invitations.remove(invitation);
	}
	public void addAbonné(String abonné) {
		amis.add(abonné);
	}
	public ArrayList<Invitation> getInvitations() {
		return invitations;
	}
	public ArrayList<Notification> getNotifications() {
		return notifications;
	}
	public ArrayList<Post> getPublications() {
		return publications;
	}
	public void removeAbonné(String abonné) {
		amis.remove(abonné);
	}
}
