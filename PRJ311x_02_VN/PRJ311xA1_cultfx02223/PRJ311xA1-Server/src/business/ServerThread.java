//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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
        System.out.println("Server listening connect from client...");
        try {
            while(true) {
                Socket socket = this.server.accept();

                System.out.println("New user connected...");
                // get user info connected.
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                String username = dis.readUTF();
                if (username != null) {

                    String x = username.substring(username.indexOf(":") + 1);
                    Client c = new Client(x);

                    System.out.println("username: " + x + "connected");

                    c.setSocket(socket);

                    ServerBoxController.lstClients.add(c);
                    ClientHandler ch = new ClientHandler(socket, c);

                    clients.put(x, ch);
                }
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }
    }
}
