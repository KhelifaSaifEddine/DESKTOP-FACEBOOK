package com.usthb.client;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;

import com.usthb.modeles.Abonn�;

public class WellcomeScreen extends UserSocket {
	private static final long serialVersionUID = -8618988640682391626L;
	JPanel Buttons=new JPanel();
	JButton connecter=new JButton("Connecter");
	JButton inscrire=new JButton("Inscrire");
	JTextField username= new JTextField("Username",15);
	JPasswordField password= new JPasswordField("Password",15);
	JTextField nom= new JTextField("Nom",15);
	JTextField pr�nom= new JTextField("Pr�nom",15);
	JTextField sp�cialit�= new JTextField("Sp�cialit�",15);
	JTextField fonction= new JTextField("Fonction",15);
	JTextField niveauEduc= new JTextField("Niveau d'Etude",15);
	JTextField incUsername= new JTextField("Username",15);
	JPasswordField incPassword= new JPasswordField("Password",15);
	public WellcomeScreen(Socket socket) {
		s=socket;
		inscrire.setActionCommand("inscrire");
		inscrire.setBackground(Color.blue);
		inscrire.setForeground(Color.white);
		connecter.setActionCommand("connecter");
		connecter.setBackground(Color.blue);
		connecter.setForeground(Color.white);
		inscrire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(answerOf(new RequestObject(RequestObject.INSCRIRE_ABONNE,new Abonn�(true, incUsername.getText(), new String(incPassword.getPassword()),nom.getText(), pr�nom.getText(), sp�cialit�.getText(), fonction.getText(), niveauEduc.getText()))).isAnswer())
						JOptionPane.showMessageDialog(null, "Incription avec succ�s", "Connected", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Username �xistant", "Error", JOptionPane.INFORMATION_MESSAGE);
				} catch (HeadlessException  e1) {
					e1.printStackTrace();
				}
			}
		});
		connecter.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				if(answerOf(new RequestObject(RequestObject.SE_CONNECTER,username.getText(), new String(incPassword.getPassword()))).isAnswer()) {
					new UserMenu(answerOf(new RequestObject(RequestObject.RECHERCHER, username.getText())).getAbonn�().getUsername(),s);
					dispose();
				}
			}
		});
		nom.addMouseListener(addML(nom));
		pr�nom.addMouseListener(addML(pr�nom));
		sp�cialit�.addMouseListener(addML(sp�cialit�));
		fonction.addMouseListener(addML(fonction));
		niveauEduc.addMouseListener(addML(niveauEduc));
		incPassword.addMouseListener(addML(incPassword));
		incUsername.addMouseListener(addML(incUsername));
		username.addMouseListener(addML(username));
		password.addMouseListener(addML(password));
		Buttons.add(nom);
		Buttons.add(pr�nom);
		Buttons.add(sp�cialit�);
		Buttons.add(fonction);
		Buttons.add(niveauEduc);
		Buttons.add(incUsername);
		Buttons.add(incPassword);
		Buttons.add(inscrire);
		Buttons.add(username);
		Buttons.add(password);
		Buttons.add(connecter);
		add(Buttons);
		setSize(200, 340);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});	
		setTitle("Se connecter");
		setVisible(true);
		((FlowLayout) Buttons.getLayout()).setHgap(20);;
		((FlowLayout) Buttons.getLayout()).setHgap(20);;
	}
	private MouseAdapter addML(JTextField nom) {
		return new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				nom.selectAll();
			}
		};
	}
}
