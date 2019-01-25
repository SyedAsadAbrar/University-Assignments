package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection extends Thread {

    Socket socket;
    
    DataInputStream din;
    DataOutputStream dout;
    boolean shouldRun = true;

    public ClientConnection(Socket socket) {
        super("ClientConnectionThread");
        this.socket = socket;
        
    }

    @Override
    public void run() {
        try {
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());

            while (shouldRun) {

                while (din.available() == 0) {
                    Thread.sleep(1);
                }

                String reply = din.readUTF();
                System.out.println(reply);

            }
        } catch (Exception e) {
            close();
        }

    }

    public void sendStringToServer(String text) {
        try {
            dout.writeUTF(text);
            dout.flush();
        } catch (IOException e) {
            close();
            e.printStackTrace();
        }

    }

    public void close() {
        try {
            din.close();
            dout.close();
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    
    public void sendStringToClient(String text) {
        try {
            dout.writeUTF(text);
            dout.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
