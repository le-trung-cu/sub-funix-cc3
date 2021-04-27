package business;

import com.entity.Client;
import com.entity.Server;
import controller.ServerBoxController;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerThread implements Runnable {
    private ServerSocket server;
    private Server chatServer;
    private Socket socket;
    public static HashMap<String, ClientHandler> clients = new HashMap();

    public ServerThread(Server chatServer) {
        this.chatServer = chatServer;

        try {
            this.server = new ServerSocket(chatServer.getPort());
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public void run() {
        try {
            while(true) {
                this.socket = this.server.accept();
                DataInputStream dis = new DataInputStream(this.socket.getInputStream());
                String username = dis.readUTF();
                Client c = new Client();
                if (username != null) {
                    String x = username.substring(username.indexOf(":") + 1);
                    c.setUsername(x);
                    c.setSocket(this.socket);
                    System.out.println(c);
                    ServerBoxController.lstClients.add(c);
                    ClientHandler ch = new ClientHandler(this.socket, c);
                    clients.put(x, ch);
                }
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }
    }
}
