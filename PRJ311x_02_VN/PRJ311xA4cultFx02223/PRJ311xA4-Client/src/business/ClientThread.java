/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import com.entity.Server;
import javafx.scene.control.TextArea;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThread implements Runnable, Serializable {

    //for I/O
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;
    private Server server;
    private TextArea txtContent;

    /*provide setter and getter here*/
    public ClientThread(Server server, TextArea txtContent) {
        try {
            this.txtContent = txtContent;
            this.server = server;
            socket = new Socket(server.getHost(), server.getPort());
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true){
                Object line = dis.readUTF();
                if(line != null){
                    txtContent.appendText("\n" + server.getHost()+":"+line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(Object line) throws Exception {
        /*send a message line to server*/
        dos.writeUTF(line.toString());
        if(!line.toString().startsWith(":")){
            txtContent.appendText("\nMe:" + line);
        }
    }
}
