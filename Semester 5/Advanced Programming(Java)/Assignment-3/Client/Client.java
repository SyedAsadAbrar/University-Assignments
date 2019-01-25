/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;





/**
 *
 * @author wmall
 */
public class Client {

	/**
	 * @param args
	 *            the command line arguments
	 */
	final static String INET_ADDR = "224.0.0.3";
	final static int PORT = 8888;
	private ClientConnection cc;

	public Client() throws Exception {

		DatagramSocket datagramSocket = new DatagramSocket();
		SendConnectionRequestToServer(datagramSocket);
		waitForServerConnection(datagramSocket);

		listenForInput();

	}

	public static void main(String[] args) {

		try {
			new Client();
		} catch (Exception ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void waitForServerConnection(DatagramSocket datagramSocket) throws Exception {

		InetAddress addr = InetAddress.getByName(INET_ADDR);
		ServerSocket ss = new ServerSocket(0);
		String portNo = Integer.toString(ss.getLocalPort());

		// send dynamically allocated port number to server
		DatagramPacket msgPacket1 = new DatagramPacket(portNo.getBytes(), portNo.getBytes().length, addr, PORT);

		datagramSocket.send(msgPacket1);
		System.out.println("port number sent to server" + portNo);

		Socket s = ss.accept(); // wait for server to connect
		cc = new ClientConnection(s);		
		cc.start();
		

	}

	public void listenForInput() throws Exception {

		Scanner console = new Scanner(System.in);
		while (true) {
			while (!console.hasNextLine()) {

				Thread.sleep(1);

			}
			String input = console.nextLine();

			cc.sendStringToServer(input);

			if (input.equalsIgnoreCase("quit")) {
				break;
			}

		}

		console.close();

	}

	private void SendConnectionRequestToServer(DatagramSocket datagramSocket) throws Exception {

		// Get the address that we are going to connect to.
		InetAddress addr = InetAddress.getByName(INET_ADDR);

		String msg = "Please Connect";

		// Create a packet that will contain the data

		// (in the form of bytes) and send it.
		DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, addr, PORT);
		datagramSocket.send(msgPacket);

	}

}
