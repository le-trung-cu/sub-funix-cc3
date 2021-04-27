package controller;

import dao.GroupDAO;
import entity.Contact;
import entity.Group;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddContactController {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private DatePicker dob;
    @FXML
    private ComboBox<Group> cbGroup;

    @FXML
    private Label lblFirstName;
    @FXML
    private Label lblLastName;
    @FXML
    private Label lblPhone;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lbldob;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClose;
    private ContactController contactController;
    private List<Contact> contacts;

    public void  setAddContactController(ContactController contactController) {
        this.contactController = contactController;
    }

    public  void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @FXML
    void initialize()throws  Exception {
        this.lblFirstName.setText("");
        this.lblLastName.setText("");
        this.lblEmail.setText("");
        this.lblPhone.setText("");
        this.lbldob.setText("");
        this.cbGroup.getItems().clear();
        Iterator<Group> groupIterator = (new GroupDAO()).loadGroup("data/group.txt").iterator();

        while(groupIterator.hasNext()) {
            Group x = groupIterator.next();
            this.cbGroup.getItems().add(x);
        }

        this.cbGroup.getSelectionModel().select(0);
        this.dob.setValue(LocalDate.now());
    }

    public  void saveContact() throws Exception {
        this.lblFirstName.setText("");
        this.lblLastName.setText("");
        this.lblEmail.setText("");
        this.lblPhone.setText("");
        this.lbldob.setText("");

        String fname = this.firstName.getText().trim();
        if (fname.isEmpty()) {
            this.lblFirstName.setText("First Name can not be empty");
        } else {
            String lname = this.lastName.getText().trim();
            if (lname.isEmpty()) {
                this.lblLastName.setText("Last Name can not be empty");
            } else {
                String mobile = this.phone.getText().trim();
                if (!mobile.isEmpty() && mobile.matches("\\d+")) {
                    String mail = this.email.getText().trim();
                    Pattern emailNamePtrn = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                    Matcher mtch = emailNamePtrn.matcher(mail);
                    if (!mtch.matches()) {
                        this.lblEmail.setText("Email is invalid");
                    } else {
                        String birthdate = ((LocalDate)this.dob.getValue()).toString();
                        String group = ((Group)this.cbGroup.getSelectionModel().getSelectedItem()).getName();
                        Contact c = new Contact(fname, lname, mobile, mail, birthdate, group);
                        Alert alert;
                        if (this.contactController.contactDAO.indexOf(this.contacts, c) == -1) {
                            this.contactController.contactDAO.saveToList(this.contacts, c);
                            this.contactController.showContact(this.contacts);
                            this.contactController.contactDAO.saveToFile(this.contacts, "data/contact.txt");
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information");
                            alert.setContentText("New Contact has been added");
                            alert.showAndWait();
                        } else {
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Information");
                            alert.setContentText("Information of contact is existed");
                            alert.showAndWait();
                        }

                    }
                } else {
                    this.lblPhone.setText("Phone contains digit only");
                }
            }
        }
    }

    public void saveAction(ActionEvent actionEvent) throws Exception {
        this.saveContact();
    }

    public void closeAction(ActionEvent actionEvent) {
        Node source = (Node)actionEvent.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.close();
    }
}
