//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package controller;

import business.ClientHandler;
import business.ServerThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.HashSet;
import java.util.Set;

public class ChatBoxController {
    @FXML
    private TextArea txtContent;
    @FXML
    private Button btnSend;
    @FXML
    private TextField txtMessage;
    private ClientHandler cs;
    private ServerBoxController server;
    private String username;
    private Thread clientThread;

    // với mỗi kêt nối server sẽ tạo 1 Thread
    // clientNameSet để quản lý thread cho client.
    public final static Set<String> clientNameSet = new HashSet<>();
    public ChatBoxController() {
    }

    public void setUsername(String username) {
        this.username = username;
        this.cs = (ClientHandler)ServerThread.clients.get(username);

        if(this.cs != null && this.cs.getTxtContent() != null)
            this.txtContent.appendText(this.cs.getTxtContent().getText());

        if(this.cs != null)
            this.cs.setTxtContent(this.txtContent);

        // Nếu thread chưa được tạo cho client này thì ta tạo 1 thread
        // Nếu thread đã được tạo rồi thì không cần tạo thread mới
        if(!clientNameSet.contains(username)){
            clientNameSet.add(username);
            this.clientThread = new Thread(this.cs);
            this.clientThread.start();
        }
    }

    public void btnSendActionPerformed(ActionEvent evt) {
        try {
            this.cs.send(this.txtMessage.getText());
        } catch (Exception var3) {
            System.out.println(var3);
        }

    }
}
