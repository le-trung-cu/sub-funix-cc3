//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package controller;

import com.entity.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ServerBoxController {
    public static ObservableList<Client> lstClients = FXCollections.observableArrayList();
    @FXML
    private ListView clients;

    @FXML
    void initialize() {
        assert this.clients != null : "fx:id=\"listView\" was not injected: check your FXML file 'CustomList.fxml'.";

        this.clients.setItems(lstClients);
    }

    public ServerBoxController() {
    }

    public static void removeUser(String username) {
        for(int i = 0; i < lstClients.size(); ++i) {
            Client x = (Client)lstClients.get(i);
            if (x.getUsername().equalsIgnoreCase(username)) {
                lstClients.remove(i);
            }
        }

    }

    public void lstClientsMouseClicked(MouseEvent evt) {
        try {
            if (evt.getClickCount() == 2) {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../ui/ChatBox.fxml"));
                Parent root = (Parent)loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                String clientName = ((Client)this.clients.getSelectionModel().getSelectedItem()).getUsername();
                stage.setTitle("Chat with " + clientName);
                stage.show();
                ChatBoxController controller = (ChatBoxController)loader.getController();
                stage.setOnHidden(e -> {
                    controller.shutdown();
                });
                controller.setUsername(clientName);

            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }
}
