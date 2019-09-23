package com.usthb.client;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import com.usthb.modeles.*;
@SuppressWarnings("serial")
public class UserMenu extends UserSocket {
	String user;
	JMenuBar menuBar=new JMenuBar();
	JButton déconnection=new JButton("Déconnection");
	JPanel panel=new JPanel(new BorderLayout());
	JScrollPane messPost=new JScrollPane(new JTextArea("Ecrire une publication",3,20));
	Groupe publier;
	JMenu invitation=new JMenu("Invitations");
	JMenu notification=new JMenu("Notification");
	int x=1;
	GridBagConstraints a=new GridBagConstraints();
	public UserMenu(String user0, Socket s) {
		user=user0;
		this.s=s;
		Abonné abonné=answerOf(new RequestObject(RequestObject.RECHERCHER, user)).getAbonné();
		a.insets=new Insets(5,0,0,0);
		loadInvitations();
		loadNotifications();
		JButton aPropos=new JButton("A propos");
		aPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProfileInformation(abonné);
			}
		});
		JButton myProfile=new JButton();
		myProfile.setIcon(new ImageIcon("s-1.png"));
		myProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadProfile(user);
			}
		});
		menuBar.add(myProfile);
		panel.setLayout(new GridBagLayout());
		déconnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				answerOf(new RequestObject(RequestObject.SE_DECONNECTER, user));
				dispose();
				new WellcomeScreen(s);
			}
		});
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				answerOf(new RequestObject(RequestObject.SE_DECONNECTER, user));
				dispose();
			}
		});	
		déconnection.setBackground(Color.RED);
		déconnection.setForeground(Color.WHITE);
		JTextField searchField=new JTextField("Rechercher une personne");
		JButton searchButton=new JButton("Rechercher");
		menuBar.add(searchField);
		menuBar.add(searchButton);
		menuBar.add(aPropos);
		menuBar.add(notification);
		menuBar.add(invitation);
		menuBar.add(déconnection);
		setJMenuBar(menuBar);
		add(new JScrollPane(panel));
		setSize(600,700);
		setVisible(true);
		JButton BPublier=new JButton("publier");
		ArrayList<Component> LPublier=new ArrayList<Component>(); 
		BPublier.setBackground(Color.BLUE);
		BPublier.setForeground(Color.WHITE);
		LPublier.add(BPublier);
		String[] v= {"Privé","Amis","Publique"};
		LPublier.add(new JComboBox<String>(v));
		BPublier.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				answerOf(new RequestObject(RequestObject.PUBLIER, user,((JTextArea) messPost.getViewport().getView()).getText(),Visibilité.valueOf(((String) ((JComboBox<String>) LPublier.get(1)).getSelectedItem()).toLowerCase())));
				loadProfile(user);
			}
		});
		publier=new Groupe(LPublier);
		((JTextArea) messPost.getViewport().getView()).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				((JTextArea) messPost.getViewport().getView()).selectAll();
			}
		});
		searchField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				((JTextArea) messPost.getViewport().getView()).selectAll();
			}
		});
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Abonné a=answerOf(new RequestObject(RequestObject.RECHERCHER,searchField.getText())).getAbonné();
				if(a!=null) loadProfile(a.getUsername()); else JOptionPane.showMessageDialog(null, "Username inéxistant", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		loadProfile(user);
	}
	private void loadProfile(String abon) {
		loadInvitations();
		loadNotifications();
		Abonné abonné=answerOf(new RequestObject(RequestObject.RECHERCHER,abon)).getAbonné();
		Abonné user0=answerOf(new RequestObject(RequestObject.RECHERCHER,user)).getAbonné();
		panel.removeAll();
		ArrayList<GroupComments> mur=new ArrayList<GroupComments>();
		if(user.equals(abonné.getUsername())) {
			x=1;
			a.gridx=0; a.gridy=0;
			panel.add(messPost,a);
			a.gridx++;// a.gridy=x++;
			panel.add(publier,a);
			int i=0;
			for(Post p:abonné.getPublications()) {
				mur.add(new GroupComments(p,abon));
				a.gridx=0; a.gridy=x++;
				panel.add(mur.get(i++),a);
			}
		} else {
			boolean bool=true;
			for(String ab:user0.getAmis()) {
				if(ab.equals(abon)) {
					int i=0; x=0;
					for(Post p:abonné.getPublications())
						if(p.getVisibilité()!=Visibilité.privé) {
							mur.add(new GroupComments(p,abon));
							a.gridx=0; a.gridy=x++;
							panel.add(mur.get(i++),a);
						}
					bool=false; break;
				}
			}
			if(bool) {
				boolean c=false,b=false;
				for(Invitation i:user0.getInvitations()) {
					if(i.getUsername().equals(abon)) {c=true; break;}
				}
				for(Invitation i:abonné.getInvitations()) {
					if(i.getUsername().equals(user)) {b=true; break;}
				}
				JButton inviter=new JButton();
				if(b) {
					inviter.setText("Annuler l'invitaton");
					inviter.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							answerOf(new RequestObject(RequestObject.ANNULER_INVITATION, abon,user0.getUsername()));
							loadProfile(abon);
						}
					});
					panel.add(inviter);
				} else if(c) {
					JButton accepter=new JButton("Accepter l'invitation");
					inviter.setText("Refuser l'invitation");
					inviter.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							answerOf(new RequestObject(RequestObject.ANNULER_INVITATION,user, abon));
							loadInvitations();
							loadProfile(abon);
						}
					});
					accepter.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							answerOf(new RequestObject(RequestObject.ACCEPTER_INVITATION,user, abon));
							loadProfile(abon);
						}
					});
					ArrayList<Component> l=new ArrayList<Component>();
					l.add(accepter);
					l.add(inviter);
					panel.add(new Groupe(l));
				} else {
					JScrollPane mess=new JScrollPane(new JTextArea("Salut, ",3,10));
					inviter.setText("Inviter");
					inviter.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							answerOf(new RequestObject(RequestObject.INVITER,abon,new Invitation(user,((JTextArea) mess.getViewport().getView()).getText(),false)));
							loadProfile(abon);
						}
					});
					ArrayList<Component> l=new ArrayList<Component>();
					l.add(mess);
					l.add(inviter);
					panel.add(new Groupe(l));
				}
				x=1;
				int i=0;
				mur=new ArrayList<GroupComments>();
				for(Post p:abonné.getPublications())
					if(p.getVisibilité()==Visibilité.publique) {
						mur.add(new GroupComments(p,abon));
						a.gridx=0; a.gridy=x++;
						panel.add(mur.get(i++),a);
					}
			}
		}
		setTitle(abonné.getNom()+" "+abonné.getPrénom());
		panel.revalidate();
		panel.repaint();
	}
	public void loadInvitations() {
		Abonné user0=answerOf(new RequestObject(RequestObject.RECHERCHER,user)).getAbonné();
		invitation.removeAll();
		JMenu m;
		JMenuItem item;
		if(user0.getInvitations().isEmpty()) {
			item=new JMenuItem("Pas d'invitaion");
			invitation.add(item);
			item.setEnabled(false);
		} else
		for(Invitation i:user0.getInvitations()) {
			m=new JMenu(i.getUsername()+": "+i.getMessage());
			item=new JMenuItem("Accepter");
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					answerOf(new RequestObject(RequestObject.ACCEPTER_INVITATION,user0.getUsername(), i.getUsername()));
					loadInvitations();
				}
			});
			m.add(item);
			item=new JMenuItem("Refuser");
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					answerOf(new RequestObject(RequestObject.ANNULER_INVITATION,user0.getUsername(), i.getUsername()));
					loadInvitations();
				}
			});
			m.add(item);
			if(!i.isState()) {
				item=new JMenuItem("Marquer comme lue");
				item.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						answerOf(new RequestObject(RequestObject.MARK_LUE_INV,user0.getUsername(), i.getUsername()));
						loadInvitations();
					}
				});
				m.add(item);
			}
			invitation.add(m);
		}
		invitation.revalidate();
		invitation.repaint();
	}
	public void loadNotifications() {
		Abonné user0=answerOf(new RequestObject(RequestObject.RECHERCHER,user)).getAbonné();
		notification.removeAll();
		JMenu m;
		JMenuItem item;
		if(user0.getNotifications().isEmpty()) {
			item=new JMenuItem("Pas de notification");
			notification.add(item);
			item.setEnabled(false);
		} else
		for(Notification i:user0.getNotifications()) {
			m=new JMenu(new String(i.getInformation()));
			item=new JMenuItem("Accepter");
			m.add(item);
			item=new JMenuItem("Refuser");
			m.add(item);
			if(!i.isState()) {
				item=new JMenuItem("Marquer comme lue");
				m.add(item);
			}
			notification.add(m);
		}
		notification.revalidate();
		notification.repaint();
	}
	class Groupe extends JPanel {
		public Groupe(ArrayList<Component> comp) {
			FlowLayout f=new FlowLayout(FlowLayout.CENTER,30,10);
			setLayout(f);
			for(Component c:comp) {
				add(c);
				setLayout(f);
			}
			//setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		}
	}
	class GroupComments extends JPanel {
		GridBagConstraints a=new GridBagConstraints();  
		JTextArea text=new JTextArea("",4,17);
		JScrollPane pub=new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollPane commt=new JScrollPane(new JTextArea("Ecrire un commetaire...",4,20));
		JButton commenter=new JButton("Commenter");
		JButton supprimer=new JButton("Supprimer");
		public GroupComments (Post publication,String abonné) {
			a.insets.set(10, 10, 10, 10);
			setLayout(new GridBagLayout());
			text.setText(publication.getContenu());
			text.addFocusListener(new FocusListener() {
		        public void focusLost(FocusEvent e) {
		            text.setEditable(true);
		        }
		        public void focusGained(FocusEvent e) {
		            text.setEditable(false);
		        }
		    });
			add(pub,a); a.gridy++;
			commenter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					answerOf(new RequestObject(RequestObject.COMMENTER,abonné, publication.getId(), user,new StringBuilder(((JTextArea) commt.getViewport().getView()).getText())));
					loadProfile(abonné);
				}
			});
			supprimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					answerOf(new RequestObject(RequestObject.SUPPRIMER_PUB,abonné, publication.getId()));
					loadProfile(abonné);
				}
			}); add(commt,a); a.gridy++; a.ipadx=35;
			if(abonné.equals(user)) {
				JPanel pan=new JPanel();
				pan.setLayout(new GridLayout(0,1));
				pan.add(commenter); pan.add(supprimer);
				a.gridx=2; a.gridy=0;
				add(pan,a);
			}
			else add(commenter);
			a.ipadx=0; a.gridx=1; a.anchor=GridBagConstraints.LINE_START;
			for(Integer key:publication.getCommentaires().keySet()) {
				a.gridy++;
				add(new JLabel(publication.getCommentaires().get(key).get(0)+":  "+publication.getCommentaires().get(key).get(1)),a);
			}
			setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED),abonné));
		}
	}
}
