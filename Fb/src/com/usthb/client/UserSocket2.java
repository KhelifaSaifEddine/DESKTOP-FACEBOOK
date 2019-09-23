package com.usthb.client;

import java.io.*;
import javax.swing.JFrame;

public class UserSocket2 extends JFrame {
	private static final long serialVersionUID = -4313282754854278913L;
	java.net.Socket s;
	public AnswerObject read() throws ClassNotFoundException, IOException {
		return (AnswerObject) (new ObjectInputStream(s.getInputStream())).readObject();
	}
	public void write(RequestObject request) throws IOException {
		(new ObjectOutputStream(s.getOutputStream())).writeObject(request);
	}
}
