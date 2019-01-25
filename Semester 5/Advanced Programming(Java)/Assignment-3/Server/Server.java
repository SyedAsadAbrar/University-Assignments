package Server;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;






public class Server {

	final static String INET_ADDR = "224.0.0.3";
    final static int PORT = 8888;
    List<ServerConnection> connections;
	
    public Server() throws Exception {
    	connections = new ArrayList<>();
        waitForClientConnectionRequest();

    }
    
    public void waitForClientConnectionRequest() throws Exception {
        // Get the address that we are going to connect to.
        InetAddress address = InetAddress.getByName(INET_ADDR);

        // Create a buffer of bytes, which will be used to store
        // the incoming bytes containing the information from the server.
        // Since the message is small here, 256 bytes should be enough.
        byte[] buf = new byte[256];
        byte[] buf1 = new byte[256];
        MulticastSocket clientSocket = new MulticastSocket(PORT);
        //Joint the Multicast group.
        clientSocket.joinGroup(address);

        while (true) {
            // Receive the information
            DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
            
            clientSocket.receive(msgPacket);

            String msg = new String(msgPacket.getData(), 0, msgPacket.getLength());
            
            System.out.println("Server received Connection Request");

            if (msg.equals("Please Connect")) {
                DatagramPacket msgPacket1 = new DatagramPacket(buf1, buf1.length);
                clientSocket.receive(msgPacket1);
                String msg1 = new String(msgPacket1.getData(),0, msgPacket1.getLength());
               
                int portNo = Integer.parseInt(msg1);
                
                Socket s = new Socket(msgPacket1.getAddress(),portNo);
                
                ServerConnection sc = new ServerConnection(s, this);
                connections.add(sc);
                sc.start();

            } else {
                System.out.println("Connection Request Was Corrupt");

            }
        }

    }
    
    public static void main(String[] args) throws Exception {
        
        new Server();
    }

     
    
}
