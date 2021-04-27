package business;

import com.entity.Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import javafx.scene.control.TextArea;

public class ClientThread implements Runnable, Serializable {
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;
    private Server server;
    private TextArea txtContent;

    public Socket getSocket() {
        return this.socket;
    }

    public ClientThread(Server server, TextArea txtContent) {
        try {
            this.txtContent = txtContent;
            this.server = server;
            this.socket = new Socket(server.getHost(), server.getPort());
            this.dis = new DataInputStream(this.socket.getInputStream());
            this.dos = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    public void run() {
        try {
            while(true) {
                Object line = this.dis.readUTF();
                if (line != null) {
                    this.txtContent.appendText("\n" + this.server.getHost() + ":" + line);
                }
            }
        } catch (Exception var2) {
        }
    }

    public void send(Object line) throws Exception {
        this.dos.writeUTF(line.toString());
        if (!line.toString().startsWith(":")) {
            this.txtContent.appendText("\nMe:" + line);
        }

    }
}
