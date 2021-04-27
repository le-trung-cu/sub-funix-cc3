package controller;

import dao.ContactDAO;
import dao.GroupDAO;
import entity.Contact;
import entity.Group;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;


public class ContactController {
    @FXML
    private TextField search;
    @FXML
    private ComboBox<Group> cbGroup;
    @FXML
    private TableView<Contact> tblContact;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnGroup;
    List<Contact> contacts;
    ContactDAO contactDAO = new ContactDAO();
    private final String GROUP = "data/group.txt";
    private final String CONTACT = "data/contact.txt";

    public ContactController() {
    }


    @FXML
    void initialize() {
        try {
            this.contacts = (new ContactDAO()).loadContact("data/contact.txt");
            this.showGroup((new GroupDAO()).loadGroup("data/group.txt"));
            this.showContact(this.contacts);
            this.tblContact.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        }catch (Exception var7) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("" + var7);
            alert.show();
        }
    }

    //output all contact to tblContact
    public  void showContact(List<Contact> c) {
        if(c.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("NO CONTACT");
            alert.setContentText("Have no contact found!");
            alert.showAndWait();
            return;
        }
        this.tblContact.getItems().clear();
        String group = (this.cbGroup.getSelectionModel().getSelectedItem()).getName();
        Iterator<Contact>  contactIterator = c.iterator();;
        Contact x;
        if (group.equals("All")) {
            while(contactIterator.hasNext()) {
                x = contactIterator.next();
                this.tblContact.getItems().add(x);
            }
        } else {
            while(contactIterator.hasNext()) {
                x = contactIterator.next();
                if (x.getGroup().equalsIgnoreCase(group)) {
                    this.tblContact.getItems().add(x);
                }
            }
        }
    }
    //output all groups to dropdownlist
    public  void showGroup(List<Group> g) {
        this.cbGroup.getItems().clear();
        this.cbGroup.getItems().add(new Group("All"));
        Iterator var2 = g.iterator();

        while(var2.hasNext()) {
            Group x = (Group)var2.next();
            this.cbGroup.getItems().add(x);
        }

        this.cbGroup.getSelectionModel().select(0);
    }
    //do corresponding actions for search, delete, update and add contact
    public void searchContact(ActionEvent evt) throws Exception{
        if (evt.getSource() == this.btnSearch) {
            String group = this.cbGroup.getSelectionModel().getSelectedItem().getName();
            List<Contact> c = this.contactDAO.search(this.contacts, group, this.search.getText());
            this.showContact(c);
        }
    }
    //manage the groups
    public void groupPanel(ActionEvent evt) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../ui/group.fxml"));
        Parent root = (Parent)loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Group a Management");
        stage.show();
        GroupController c = (GroupController)loader.getController();
        c.setContactController(this);
    }

    //update a contact
    public  void addContact(ActionEvent evt)throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../ui/addContact.fxml"));
        Parent root = (Parent)loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add a new Contact");
        stage.show();
        AddContactController c = (AddContactController)loader.getController();
        c.setContacts(this.contacts);
        c.setAddContactController(this);
    }
    //update a contact
    public  void updateContact(ActionEvent evt)throws Exception {
        int i = this.tblContact.getSelectionModel().getSelectedIndex();
        if (i < this.tblContact.getItems().size() && i >= 0) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../ui/updateContact.fxml"));
            Parent root = (Parent)loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Update a Contact");
            stage.show();
            UpdateContactController c = (UpdateContactController)loader.getController();
            c.setContacts(this.contacts);
            c.setContactController(this);
            c.setUpdatedContact((Contact)this.tblContact.getItems().get(i));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information");
            alert.setContentText("Select a Contact to update");
            alert.showAndWait();
        }
    }
    //delete a selected contact
    public  void deleteContact(ActionEvent evt)throws  Exception {
        int i = this.tblContact.getSelectionModel().getSelectedIndex();
        Alert alert;
        if (i < this.tblContact.getItems().size() && i >= 0) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Do you wanna delete selected contact?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                this.contacts.remove(i);
                this.showContact(this.contacts);
                this.contactDAO.saveToFile(this.contacts, "data/contact.txt");
            }
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information");
            alert.setContentText("Select a Contact to delete");
            alert.showAndWait();
        }
    }


}
