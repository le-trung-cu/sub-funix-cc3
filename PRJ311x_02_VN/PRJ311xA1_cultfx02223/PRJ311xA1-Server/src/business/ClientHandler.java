//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package business;

import com.entity.Client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import controller.ChatBoxController;
import controller.ServerBoxController;
import javafx.scene.control.TextArea;

public class ClientHandler implements Runnable {
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;
    private Client client;

    // content chat
    private TextArea txtContent;

    public TextArea getTxtContent() {
        return this.txtContent;
    }

    public void setTxtContent(TextArea txtContent) {
        this.txtContent = txtContent;
    }

    public ClientHandler(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;
    }

    public void run() {
        try {
            this.dis = new DataInputStream(this.socket.getInputStream());
            this.dos = new DataOutputStream(this.socket.getOutputStream());

            while(true) {
                String line;
                do {
                    line = this.dis.readUTF();
                } while(line != null);

                this.txtContent.appendText("\n" + this.client.getUsername() + ":" + line);
                System.out.println("username " + this.client.getUsername() + " just send: " + line);
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }finally {
            try {
                // Handle when client disconnect.
                System.out.println("Client disconnected...");
                this.socket.close();
                ServerBoxController.removeUser(client.getUsername());
                ChatBoxController.clientNameSet.remove(client.getUsername());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(Object line) throws Exception {
        this.dos.writeUTF(line.toString());
        this.txtContent.appendText("\nMe:" + line);
    }
}
