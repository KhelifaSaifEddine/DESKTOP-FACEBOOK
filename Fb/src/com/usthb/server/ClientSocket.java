package com.usthb.server;

import java.io.*;
import java.net.*;

import com.usthb.client.AnswerObject;
import com.usthb.client.RequestObject;

public class ClientSocket extends Thread {
	Socket s;
	public ClientSocket(Socket s) throws IOException, ClassNotFoundException {
		this.s=s;
		start();
	}
	public RequestObject read() throws ClassNotFoundException, IOException {
		return (RequestObject) (new ObjectInputStream(s.getInputStream())).readObject();
	}
	public void write(AnswerObject answer) throws IOException {
		(new ObjectOutputStream(s.getOutputStream())).writeObject(answer);
	}
	public void run() {
		while(true) {
			try {
				write(read().answer());
			} catch (IOException | ClassNotFoundException e) {
				return;
			}
		}
	}
}
