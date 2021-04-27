package sample;

import javafx.scene.control.TextField;
import sample.datamodel.Contact;

public class ContactController {
    public TextField firstNameFiled;
    public TextField lastNameFiled;
    public TextField phoneNumberFiled;
    public TextField notesFiled;

    public Contact getNewContact() {
        Contact contact = new Contact(firstNameFiled.getText().trim(), lastNameFiled.getText().trim()
                , phoneNumberFiled.getText().trim(), notesFiled.getText().trim() );
        return contact;
    }

    public void editContact(Contact contact) {
        firstNameFiled.setText(contact.getFirstName());
        lastNameFiled.setText(contact.getLastName());
        phoneNumberFiled.setText(contact.getPhoneNumber());
        notesFiled.setText(contact.getNotes());
    }

    public void updateContact(Contact contact) {
        contact.setFirstName(firstNameFiled.getText().trim());
        contact.setLastName(lastNameFiled.getText().trim());
        contact.setPhoneNumber(phoneNumberFiled.getText().trim());
        contact.setNotes(notesFiled.getText().trim());
    }
}
