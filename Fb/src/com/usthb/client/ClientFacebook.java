package com.usthb.client;

import java.io.IOException;
import java.net.Socket;

public class ClientFacebook {
	public static void main(String[] args) throws IOException {
		new WellcomeScreen(new Socket("localhost",4830));
	}
}
