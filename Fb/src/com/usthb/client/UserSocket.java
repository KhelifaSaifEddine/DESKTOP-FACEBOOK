package com.usthb.client;

import java.io.*;
import javax.swing.JFrame;

public class UserSocket extends JFrame {
	private static final long serialVersionUID = -4313282754854278913L;
	java.net.Socket s;
	public AnswerObject answerOf(RequestObject request) {
		try {
			(new ObjectOutputStream(s.getOutputStream())).writeObject(request);
			return (AnswerObject) (new ObjectInputStream(s.getInputStream())).readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
