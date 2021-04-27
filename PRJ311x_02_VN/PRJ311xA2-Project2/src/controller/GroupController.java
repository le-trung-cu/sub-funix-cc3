package controller;


import dao.ContactDAO;
import dao.GroupDAO;
import entity.Contact;
import entity.Group;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


public class GroupController {
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnClose;
    @FXML
    private ListView<Group> tblGroup;
    @FXML
    private TextField search;
    @FXML
    private TextField groupName;
    private final String GROUP = "data/group.txt";
    GroupDAO groupDAO = new GroupDAO();
    List<Group> groups;
    ContactController contactController;

    public GroupController() {
    }


    public void setContactController(ContactController contactController) {
        this.contactController = contactController;
    }

    @FXML
    void initialize() {
        try {
            this.groups = this.groupDAO.loadGroup("data/group.txt");
            this.showGroup(this.groups);
            this.tblGroup.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            this.tblGroup.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Group>() {
                public void changed(ObservableValue<? extends Group> observable, Group oldValue, Group newValue) {
                    if (GroupController.this.tblGroup.getSelectionModel().getSelectedItem() != null) {
                        GroupController.this.groupName.setText(((Group)GroupController.this.tblGroup.getSelectionModel().getSelectedItem()).getName());
                    }

                }
            });
        } catch (Exception var3) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("" + var3);
        }
    }

    //search action
    public  void searchAction() {
        List<Group> g = this.groupDAO.search(this.groups, this.search.getText());
        this.showGroup(g);
    }

    //add new group action
    public  void addAction()throws Exception {
        String name = this.groupName.getText().trim();
        if (!name.isEmpty() && !name.equals("")) {
            Group g = new Group(name);
            System.out.println(g);
            int i = this.groupDAO.indexOf(this.groups, g);
            Alert alert;
            if (i != -1) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Group name exists already, choose another name");
                alert.showAndWait();
            } else {
                this.groupDAO.saveGroupToList(this.groups, g);
                this.groupDAO.saveGroupToFile(this.groups, "data/group.txt");
                this.showGroup(this.groups);
                this.contactController.showGroup(this.groups);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setContentText("A new group has been added");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Group name cannot be empty");
            alert.showAndWait();
        }
    }

    //update group name
    public  void updateAction() throws Exception {
        int i = this.tblGroup.getSelectionModel().getSelectedIndex();

        if (i < this.tblGroup.getItems().size() && i >= 0) {
            String oldName = this.tblGroup.getSelectionModel().getSelectedItem().getName();
            String newName = groupName.getText().trim();

            Alert alert;
            if(newName.isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information");
                alert.setContentText("Group name is not empty");
                alert.showAndWait();
            } else if(!this.groupDAO.updateGroup(this.groups, i, newName)) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information");
                alert.setContentText("Please select another newName for group");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setContentText("A Group has been updated");
                alert.showAndWait();

                this.groupDAO.saveGroupToFile(this.groups, "data/group.txt");
                List<Contact> contacts = this.contactController.contactDAO.loadContact("data/contact.txt");
                for (Contact contact: contacts) {
                    if(contact.getGroup().equalsIgnoreCase(oldName)){
                        contact.setGroup(newName);
                    }
                }

                this.showGroup(this.groups);
                this.contactController.showGroup(this.groups);
                this.contactController.showContact(contacts);
                this.contactController.contactDAO.saveToFile(contacts, "data/contact.txt");
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information");
            alert.setContentText("Select a Contact to update");
            alert.showAndWait();
        }
    }

    //delete a group, delete failed if there are some contact is in deleted one
    public  void deleteAction()throws Exception {
        int i = this.tblGroup.getSelectionModel().getSelectedIndex();
        if (i >= 0 && i < this.tblGroup.getItems().size()) {
            int size = ((Group)this.tblGroup.getItems().get(i)).contacts().size();
            Alert alert;
            if (size > 0) {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("WARNING");
                alert.setContentText("Group has some contacts, delete this group will delete all contact in group");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent() && result.get() == ButtonType.OK){
                    this.groups.remove(i);
                    this.showGroup(this.groups);
                    this.contactController.showGroup(this.groups);
                    this.groupDAO.saveGroupToFile(this.groups, "data/group.txt");

                    ContactDAO contactDAO = this.contactController.contactDAO;
                    List<Contact> contacts = contactDAO.loadContact("data/contact.txt");
                    List<Contact> contactsFilter = new ArrayList<>();
                    for (Contact contact: contacts) {
                        if(!contact.getGroup().equalsIgnoreCase(this.groupName.getText().trim())){
                            contactsFilter.add(contact);
                        }
                    }
                    this.contactController.showContact(contactsFilter);
                    contactDAO.saveToFile(contactsFilter, "data/contact.txt");
                }
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText("Do you wanna delete selected group?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    this.groups.remove(i);
                    this.showGroup(this.groups);
                    this.contactController.showGroup(this.groups);
                    this.groupDAO.saveGroupToFile(this.groups, "data/group.txt");
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Select a group to delete");
            alert.showAndWait();
        }
    }

    //operations on each button on window
    public  void groupAction(ActionEvent evt)throws Exception {
        if (evt.getSource() == this.btnSearch) {
            this.searchAction();
        } else if (evt.getSource() == this.btnAdd) {
            this.addAction();
        } else if (evt.getSource() == this.btnUpdate) {
            this.updateAction();
        } else if (evt.getSource() == this.btnDelete) {
            this.deleteAction();
        } else if (evt.getSource() == this.btnClose) {
            Node source = (Node)evt.getSource();
            Stage stage = (Stage)source.getScene().getWindow();
            stage.close();
        }
    }

    //output all groups to table view
    public  void showGroup(List<Group> groups) {
        if (this.tblGroup.getItems() != null) {
            this.tblGroup.getItems().clear();
            Iterator var2 = groups.iterator();

            while(var2.hasNext()) {
                Group g = (Group)var2.next();
                this.tblGroup.getItems().add(g);
            }
        }
    }
}
