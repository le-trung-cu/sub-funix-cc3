//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package controller;

import business.ServerThread;
import com.entity.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public final String SERVER_NAME = "localhost";
    public final int PORT = 1234;

    public Main() {
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("../ui/ServerBox.fxml"));
        primaryStage.setTitle("Server Application");
        primaryStage.setScene(new Scene(root, 300.0D, 275.0D));
        primaryStage.show();

        try {
            Server server = new Server("localhost", 1234);
            ServerThread serverThread = new ServerThread(server);
            (new Thread(serverThread)).start();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
