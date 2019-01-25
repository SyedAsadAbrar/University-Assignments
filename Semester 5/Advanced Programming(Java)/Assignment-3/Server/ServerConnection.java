package Server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerConnection extends Thread {

    Socket socket;
    Server server;
    DataInputStream din;
    DataOutputStream dout;
    boolean shouldRun = true;

    public ServerConnection(Socket socket, Server server) {
        super("ServerConnectionThread");
        this.socket = socket;
        this.server = server;
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

                String textIn = din.readUTF();
                sendStringToAllClients(textIn);
                System.out.println(textIn);
            }

            din.close();
            dout.close();
            socket.close();

        } catch (Exception e) {
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

    public void sendStringToAllClients(String text) {

       for (int i = 0; i < server.connections.size(); i++) {
           server.connections.get(i).sendStringToClient(text);;

       }
   }

}
