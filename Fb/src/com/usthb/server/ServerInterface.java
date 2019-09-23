package com.usthb.server;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ServerInterface extends Thread {
	public class Interface extends JFrame {
		JPanel p=new JPanel();
		Interface () {
			setSize(150,70);
			setVisible(true);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent arg0) {
					System.exit(0);
				}
			});	
			while(true) refresh();
		}
		private void refresh() {
			p.removeAll();
			p.add(new JLabel("Cliens connectés:  "+(java.lang.Thread.activeCount()-3)));
			add(p);
			p.revalidate(); p.repaint();
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
	public void run() {
		new Interface();
	}
}
