package com.usthb.server;

import java.io.*;
import java.net.*;
import java.util.*;

import com.usthb.modeles.*;
import com.usthb.modeles.R�action.Type;

public class ServeurMiniFaceBook {
	static int numberOfClients=0;
	private final static HashMap<String,Abonn�> baseAbonn�s=new HashMap<String,Abonn�>();
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
	public static boolean inscrireAbonn�(Abonn� abonn�) {
		if(baseAbonn�s.containsKey(abonn�.getUsername())) return false; {baseAbonn�s.put(abonn�.getUsername(), abonn�); return true;}
	}
	public static boolean supprimerAbonn�(String username) {
		if(baseAbonn�s.remove(username)==null) return false; return true;
	}
	public static boolean seConnecter(String username,String password) {
		Abonn� a=baseAbonn�s.get(username);
		if(a!=null&&a.getPassword().equals(password)) {
			a.setOnline(true);
			return true;
		}
		return false;
	}
	public static boolean seD�connecter(String username) {
		Abonn� a=baseAbonn�s.get(username);
		if(a==null) return false;
		a.setOnline(false);
		return true;
	}
	public static Abonn� rechercher(String username) {
		return baseAbonn�s.get(username); //#########
	}
	public static boolean publier(String username,String contenu, Visibilit� v/*###*/) {
		Abonn� a=baseAbonn�s.get(username);
		if(a!=null) {
			Post p=new Post(contenu,v);
			a.addPost(p);
			Notification notification=new Notification(p,a);
			for(String ami:a.getAmis()) rechercher(ami).addNotification(notification);
			return true;
		}
		return false;
	}
	public static void �pingler(Post post) {
		post.set�pingl�e(true);
	}
	public static void d�s�pingler(Post post) {
		post.set�pingl�e(false);
	}
	public static void r�agir(Abonn� abonn�,Post post,String username,Type type) {
		R�action r=new R�action(username,type);
		post.getR�actions().add(r);
		if(!abonn�.getUsername().equals(username)) abonn�.addNotification(new Notification(r,rechercher(username)));
	}
	public static void rajouterCommentaire(String username,int publication,String usernameOrpassword,StringBuilder comment) {
		ArrayList<String> l=new ArrayList<String>();
		l.add(usernameOrpassword); l.add(new String(comment));
		Abonn� abonn�=rechercher(username);
		abonn�.getPublications().get(publication).getCommentaires().put(abonn�.getPublications().get(publication).getCommentaires().size(),l);
		if(!abonn�.getUsername().equals(username)) abonn�.getNotifications().add(0,new Notification(new Comment(username,comment),rechercher(username)));
	}
	public static boolean partager(String username,Post post, Visibilit� v) {
		post.setNombrePartage(post.getNombrePartage()+1);
		return publier(username,post.getContenu(),v);
	}
}
