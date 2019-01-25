/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author Haider
 */
public class ClientListener extends Thread{
    
    private ClientListener(){}
    Socket sock;
    DataInputStream Din;
    String Rmsg;
    String IP;
    int Port;
    DataOutputStream Dout;
    
    ClientListener(String ip, int port)
    {
        this.IP = ip;
        this.Port = port;
    }
    
    ClientListener(Socket s)
    {
        this.sock = s;
    }
    
    public void SendMsgToClient(String msg)
    {
        try {
            Dout.writeUTF(msg);
            Dout.flush();
        }catch(Exception e)
        {
            
        }
    }
    
    public void run(){
        
        try{
            //sock = new Socket(this.IP, this.Port);
            Din = new DataInputStream(sock.getInputStream());
            Dout = new DataOutputStream(sock.getOutputStream());
            
            while (true)
            {
                
                Rmsg = Din.readUTF();
                
                if (Rmsg.equalsIgnoreCase("bye"))
                {
                    break;
                }
                System.out.println(Rmsg);
                
            }            
            Din.close();
        }catch(Exception e)
        {
            System.out.println("You left the chat");
        }

        
    }
    
}
