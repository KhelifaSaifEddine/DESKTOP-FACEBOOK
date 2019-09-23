package com.usthb.client;

import javax.swing.JFrame;
import javax.swing.JTable;

import com.usthb.modeles.Abonné;

@SuppressWarnings("serial")
public class ProfileInformation extends JFrame {
	JTable informations;
	public ProfileInformation(Abonné abonné) {
		String[] c= {"",""};
		Object[][] data={{"Nom",abonné.getNom()},{"Prénom",abonné.getPrénom()},{"Date de naissance",abonné.getDate_naiss()},{"Sexe",abonné.isGender()?"Homme":"Femme"},{"Spécialité",abonné.getSpecialité()},{"Fonction",abonné.getFonction()},{"Niveau d'études",abonné.getNiveauEduc()}};
		informations=new JTable(data,c);
		informations.setEnabled(false);
		setResizable(false);
		add(informations);
		setSize(400, 140);
		setTitle(abonné.getUsername());
		setVisible(true);
	}
}
