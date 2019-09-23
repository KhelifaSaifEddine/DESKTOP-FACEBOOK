package com.usthb.client;

import java.io.Serializable;
import java.util.ArrayList;

import com.usthb.modeles.Abonné;
import com.usthb.modeles.Invitation;
import com.usthb.modeles.Post;
import com.usthb.modeles.Visibilité;
import com.usthb.server.ServeurMiniFaceBook;

public class RequestObject implements Serializable {
	private static final long serialVersionUID = 3446611345613137548L;
	int code;
	Abonné abonné;
	String username;
	String usernameOrPassword;
	Invitation invitation;
	String text;
	Visibilité visibilité;
	int publication;
	public final static int INSCRIRE_ABONNE=0;
	public final static int SE_CONNECTER=1;
	public final static int RECHERCHER=2;
	public final static int SE_DECONNECTER=3;
	public final static int PUBLIER=4;
	public final static int ANNULER_INVITATION=5;
	public final static int ACCEPTER_INVITATION=6;
	public final static int INVITER=7;
	public final static int MARK_LUE_INV=8;
	public final static int COMMENTER=9;
	public final static int SUPPRIMER_PUB=10;
	public RequestObject(int code, String username, Invitation invitation) {
		this.code = code;
		this.username = username;
		this.invitation = invitation;
	}
	public RequestObject(int code, String username) { //Recherche_
		this.code = code;
		this.username = username;
	}
	public RequestObject(int code,String username, String usernameOrPassword) { //Se_connecter
		this.code=code;
		this.username = username;
		this.usernameOrPassword = usernameOrPassword;
	}
	public RequestObject(int code, Abonné abonné) { //Inscrire_Abonné
		this.code = code;
		this.abonné = abonné;
	}

	public RequestObject(int code, String username, String text, Visibilité visibilité) {
		this.code=code;
		this.username=username;
		this.text=text;
		this.visibilité=visibilité;
	}
	public RequestObject(int code, String username, int publication, String usernameOrPassword, StringBuilder text) {
		this.code=code;
		this.username=username;
		this.publication=publication;
		this.usernameOrPassword=usernameOrPassword;
		this.text=new String(text);
	}
	public RequestObject(int code, String username, int publication) {
		super();
		this.code = code;
		this.username = username;
		this.publication = publication;
	}
	public AnswerObject answer() {
		switch(code) {
			case INSCRIRE_ABONNE: return new AnswerObject(ServeurMiniFaceBook.inscrireAbonné(abonné));
			case SE_CONNECTER: return new AnswerObject(ServeurMiniFaceBook.seConnecter(username, usernameOrPassword));
			case RECHERCHER: return new AnswerObject(ServeurMiniFaceBook.rechercher(username));
			case SE_DECONNECTER: return new AnswerObject(ServeurMiniFaceBook.seDéconnecter(username));
			case PUBLIER: ServeurMiniFaceBook.publier(username, text, visibilité); return null;
			case ANNULER_INVITATION: for(Invitation i:ServeurMiniFaceBook.rechercher(username).getInvitations()) if(i.getUsername().equals(usernameOrPassword)) {ServeurMiniFaceBook.rechercher(username).getInvitations().remove(i); break;} return null;
			case ACCEPTER_INVITATION: Abonné a=ServeurMiniFaceBook.rechercher(username),b=ServeurMiniFaceBook.rechercher(usernameOrPassword); a.addAbonné(usernameOrPassword); b.addAbonné(username); for(Invitation i:a.getInvitations()) if(i.getUsername().equals(usernameOrPassword)) {a.getInvitations().remove(i); break;} System.out.println(a.getAmis().size()+" "+b.getAmis().size()); return null;
			case INVITER: ServeurMiniFaceBook.rechercher(username).addInvitation(invitation); return null;
			case MARK_LUE_INV: return null;
			case COMMENTER: for(Post p:ServeurMiniFaceBook.rechercher(username).getPublications()) if(p.getId()==publication) ServeurMiniFaceBook.rajouterCommentaire(username, ServeurMiniFaceBook.rechercher(username).getPublications().indexOf(p), usernameOrPassword, new StringBuilder(text)); return null;
			case SUPPRIMER_PUB: ArrayList<Post> ab=ServeurMiniFaceBook.rechercher(username).getPublications(); for(Post p:ab) if(p.getId()==publication) {ab.remove(p); break;} return null;
			default: return null;
		}
	}
}
