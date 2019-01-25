package server;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    
    static ArrayList<SerClients> LesClients = new ArrayList<>();
    
    public static void SendToEveryone(String msg)
    {
        for (SerClients s: LesClients)
        {
            s.SendMessageToClient(msg);
        }
    }
    
    public static void RemoveClient(String CName)
    {
        for (SerClients s: LesClients)
        {
            if (s.getName().equalsIgnoreCase(CName))
            {
                LesClients.remove(s);
            }
        }
    }
    
    
    
    public static void main(String[] args) {
        
        String MultiIP = "225.4.5.6";
        int mport = 9876;
        String ClientRequest;
        String CLIP, CLPort;
        Socket SerSock;
        Scanner in = new Scanner(System.in);
        
        try{
            InetAddress mGroup = InetAddress.getByName(MultiIP);
            
            MulticastSocket Multsc = new MulticastSocket(mport);
            Multsc.joinGroup(mGroup);
            
            byte[] info = new byte[100];
            DatagramPacket d = new DatagramPacket(info, info.length);
        
            System.out.println("Welcome to ReeeeeeChat!");
            
            while (true)
            {
                Multsc.receive(d);
                ClientRequest = new String(d.getData());

                CLIP = new String(ClientRequest.substring(0, ClientRequest.indexOf(' ')));
                CLPort = new String(ClientRequest.substring((ClientRequest.indexOf(' ') + 1), ClientRequest.indexOf('s')));
                int clport = Integer.parseInt(CLPort);
                
                SerClients NewCl = new SerClients(CLIP, clport); //SerSock
                
                //LesClients.add(NewCl);
                NewCl.start();
                
            }
            
        }catch(Exception e)
        {
            System.out.println("Some error occured :c");
            
        }
        
    }
    
}
