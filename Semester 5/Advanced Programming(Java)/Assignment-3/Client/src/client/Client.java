package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] args)
    {
        String MultiIP = "225.4.5.6";
        int mport = 9876;
        int sport;
        
        String msg;
        ServerSocket s;
        Socket RecSock;
        DataInputStream Din;
        DataOutputStream Dout;
        Scanner input = new Scanner(System.in);
    
        try{
            s = new ServerSocket(0);
            sport = s.getLocalPort();
            //System.out.println("the port im sending is: " + sport);
            
            InetAddress ClientIP = InetAddress.getLocalHost();
            InetAddress mGroup = InetAddress.getByName(MultiIP);
            
            MulticastSocket Multsc = new MulticastSocket();
            Multsc.joinGroup(mGroup);
            
            byte[] info = (ClientIP.getHostAddress() + " " + sport + "s").getBytes();
            DatagramPacket DP = new DatagramPacket(info, info.length, mGroup, mport);
            Multsc.send(DP);
            
            RecSock = s.accept();
            ClientListener C = new ClientListener(RecSock);    //RecSock.getInetAddress().getHostAddress(), RecSock.getLocalPort()
            
            C.start();
            System.out.println("Connected!");
            
            Dout = new DataOutputStream(RecSock.getOutputStream());
            
            //System.out.print("You: ");
            msg = input.nextLine();
            //Dout.writeUTF(msg);
            C.SendMsgToClient(msg);
            
            while (!msg.equalsIgnoreCase("end"))
            {
                //System.out.print("You: ");
                msg = input.nextLine();
                C.SendMsgToClient(msg);
            }
            
            //Receiver.stop();
            
            Thread.sleep(2000);
            
        }catch(Exception e)
        {
            System.out.println("Some error occured :c");
        }
    }
    
}
