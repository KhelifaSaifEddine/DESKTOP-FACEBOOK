package com.usthb.client;

import javax.swing.JFrame;
import javax.swing.JTable;

import com.usthb.modeles.Abonn�;

@SuppressWarnings("serial")
public class ProfileInformation extends JFrame {
	JTable informations;
	public ProfileInformation(Abonn� abonn�) {
		String[] c= {"",""};
		Object[][] data={{"Nom",abonn�.getNom()},{"Pr�nom",abonn�.getPr�nom()},{"Date de naissance",abonn�.getDate_naiss()},{"Sexe",abonn�.isGender()?"Homme":"Femme"},{"Sp�cialit�",abonn�.getSpecialit�()},{"Fonction",abonn�.getFonction()},{"Niveau d'�tudes",abonn�.getNiveauEduc()}};
		informations=new JTable(data,c);
		informations.setEnabled(false);
		setResizable(false);
		add(informations);
		setSize(400, 140);
		setTitle(abonn�.getUsername());
		setVisible(true);
	}
}
