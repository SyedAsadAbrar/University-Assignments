


package server;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


public class ServerListener extends Thread{
    String MultiIP = "225.4.5.6";
    int mport = 9876;
    
    
    @Override public void run()
    {
        try{
            String ClientRequest;
            
            InetAddress mGroup = InetAddress.getByName(MultiIP);
            
            MulticastSocket Multsc = new MulticastSocket(mport);
            Multsc.joinGroup(mGroup);
            
            byte[] info = new byte[100];
            DatagramPacket d = new DatagramPacket(info, info.length);
            while (true)
            {
                Multsc.receive(d);
                ClientRequest = new String(d.toString());
                System.out.println(ClientRequest);
            }
            
        }catch(Exception e)
        {
            System.out.println("Some error occured.");
        }
    }
    
}
