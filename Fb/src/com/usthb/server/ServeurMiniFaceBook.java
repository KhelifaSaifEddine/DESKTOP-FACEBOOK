package com.usthb.server;

import java.io.*;
import java.net.*;
import java.util.*;

import com.usthb.modeles.*;
import com.usthb.modeles.Réaction.Type;

public class ServeurMiniFaceBook {
	static int numberOfClients=0;
	private final static HashMap<String,Abonné> baseAbonnés=new HashMap<String,Abonné>();
	private static ServerSocket ss;
	static public void main(String argv[]) {
		try {
			ss = new ServerSocket(4830);
			(new ServerInterface()).start();
			while(true) new ClientSocket(ss.accept());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static boolean inscrireAbonné(Abonné abonné) {
		if(baseAbonnés.containsKey(abonné.getUsername())) return false; {baseAbonnés.put(abonné.getUsername(), abonné); return true;}
	}
	public static boolean supprimerAbonné(String username) {
		if(baseAbonnés.remove(username)==null) return false; return true;
	}
	public static boolean seConnecter(String username,String password) {
		Abonné a=baseAbonnés.get(username);
		if(a!=null&&a.getPassword().equals(password)) {
			a.setOnline(true);
			return true;
		}
		return false;
	}
	public static boolean seDéconnecter(String username) {
		Abonné a=baseAbonnés.get(username);
		if(a==null) return false;
		a.setOnline(false);
		return true;
	}
	public static Abonné rechercher(String username) {
		return baseAbonnés.get(username); //#########
	}
	public static boolean publier(String username,String contenu, Visibilité v/*###*/) {
		Abonné a=baseAbonnés.get(username);
		if(a!=null) {
			Post p=new Post(contenu,v);
			a.addPost(p);
			Notification notification=new Notification(p,a);
			for(String ami:a.getAmis()) rechercher(ami).addNotification(notification);
			return true;
		}
		return false;
	}
	public static void épingler(Post post) {
		post.setÉpinglée(true);
	}
	public static void désépingler(Post post) {
		post.setÉpinglée(false);
	}
	public static void réagir(Abonné abonné,Post post,String username,Type type) {
		Réaction r=new Réaction(username,type);
		post.getRéactions().add(r);
		if(!abonné.getUsername().equals(username)) abonné.addNotification(new Notification(r,rechercher(username)));
	}
	public static void rajouterCommentaire(String username,int publication,String usernameOrpassword,StringBuilder comment) {
		ArrayList<String> l=new ArrayList<String>();
		l.add(usernameOrpassword); l.add(new String(comment));
		Abonné abonné=rechercher(username);
		abonné.getPublications().get(publication).getCommentaires().put(abonné.getPublications().get(publication).getCommentaires().size(),l);
		if(!abonné.getUsername().equals(username)) abonné.getNotifications().add(0,new Notification(new Comment(username,comment),rechercher(username)));
	}
	public static boolean partager(String username,Post post, Visibilité v) {
		post.setNombrePartage(post.getNombrePartage()+1);
		return publier(username,post.getContenu(),v);
	}
}
