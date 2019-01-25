/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SerClients extends Thread {
    
    Socket sock;
    DataOutputStream Dout;
    DataInputStream Din;
    String ClMsg, ClientName;
    static int NumOfClients = 1;
    private SerClients(){};
    String Cip;
    int sport;
    

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String ClientName) {
        this.ClientName = ClientName;
    }
    
    SerClients(String ip, int port)
    {
        this.Cip = ip;
        this.sport = port;
        ClientName = "Client " + NumOfClients;
        NumOfClients++;
    }
    
    public void SendMessageToClient(String msg)
    {
        try{
            Dout.writeUTF(msg);
            Dout.flush();
            //System.out.println("SendmsgtoCLient worked");
        }catch(Exception e)
        {
            //System.out.println("send msg client not working");
        }
    }
    
    @Override public void run() 
    {
        try{
            System.out.println(ClientName + " has joined!");
            
            sock = new Socket(this.Cip, this.sport);
            Server.LesClients.add(this);
            
            Din = new DataInputStream(sock.getInputStream());
            Dout = new DataOutputStream(sock.getOutputStream());
            
            while (true)
            {
                try{
                    
                    ClMsg = Din.readUTF();
                    //System.out.println("Serclient ip and port: " + this.Cip + " - " + this.sport);
                    
                }catch(IOException e)
                {
                    System.out.println("Client Disconnected >:(");
                    Server.RemoveClient(this.ClientName);
                    break;
                }
                
                if (ClMsg.equalsIgnoreCase("end"))
                {
                    break;
                }
                
                System.out.println(ClientName + ": " + ClMsg);
                Server.SendToEveryone(ClientName + ": " + ClMsg);
            }
            
            Din.close();
            Dout.close();
            sock.close();
            
            Server.RemoveClient(this.ClientName);
            Server.SendToEveryone(ClientName + " has left the chat..");
            
        }catch(Exception e)
        {
            Server.RemoveClient(this.ClientName);
            Server.SendToEveryone(ClientName + " has left the chat..");
        }finally{
            //System.out.println(ClientName + " has left....");
        }
        
    }
    
}
