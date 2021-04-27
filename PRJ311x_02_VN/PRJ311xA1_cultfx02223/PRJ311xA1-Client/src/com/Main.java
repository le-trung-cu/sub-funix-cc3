package com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    public final String SERVER_NAME = "localhost";
    public final int PORT = 1234;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../ui/ClientChat.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Client Chat");
        primaryStage.setScene(new Scene(root, 800, 375));
        ClientChatController controller = loader.getController();
        primaryStage.setOnHidden(e -> {
            controller.shutdown();
        });
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
