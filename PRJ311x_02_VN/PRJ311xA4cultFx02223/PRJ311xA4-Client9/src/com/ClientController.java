package com;
import business.ClientThread;
import com.entity.Client;
import com.entity.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

class ClientChatController {
    @FXML
    private TextArea txtContent;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtHostIP;
    @FXML
    private TextField txtPort;
    @FXML
    private TextField txtMessage;
    @FXML
    private Button btnSend;
    @FXML
    private Button btnConnect;
    @FXML
    private Label lblStatus;
    business.ClientThread clientThread = null;

    public ClientChatController(){
    }

    public TextArea getTextArea(){
        return this.txtContent;
    }

    /* Connect to Server (run when user press button Connect)*/
    public void btnConnectActionPerformed(ActionEvent evt) {
        if(this.clientThread == null){
            try {
                Client c = new Client(this.txtUsername.getText(), "");
                Server server = new Server(this.txtHostIP.getText(),
                        Integer.parseInt(this.txtPort.getText()));

                this.clientThread = new ClientThread(server, this.txtContent);
                Thread t = new Thread(this.clientThread);
                t.start();
                this.clientThread.send(":" + c.getUsername());
                this.txtContent.appendText("Connected to server");
                this.btnConnect.setDisable(true);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void btnSendActionPerformed(ActionEvent actionEvent) {
        try{
            this.clientThread.send(this.txtMessage.getText());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}