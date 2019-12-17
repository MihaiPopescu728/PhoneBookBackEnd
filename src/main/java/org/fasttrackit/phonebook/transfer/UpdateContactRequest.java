package org.fasttrackit.phonebook.transfer;

import org.fasttrackit.phonebook.persistance.ContactRepository;
import org.fasttrackit.phonebook.Domain.Contact;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UpdateContactRequest {

    private ContactRepository contactRepository = new ContactRepository();

    String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "UpdateContactRequest{" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public void updateToDoItem(long id, UpdateContactRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Update contact" + request);
        contactRepository.updateContact(id, request.getPhoneNumber());
    }

    public void deleteToDoItem(long id) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting item" + id);
        contactRepository.deleteContact(id);
    }

    public List<Contact> getContact() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retrieving contacts ... ");
        return contactRepository.getContacts();
    }
}
