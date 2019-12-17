package org.fasttrackit.phonebook.service;

import org.fasttrackit.phonebook.Domain.Contact;
import org.fasttrackit.phonebook.persistance.ContactRepository;
import org.fasttrackit.phonebook.transfer.CreateContactRequest;
import org.fasttrackit.phonebook.transfer.UpdateContactRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ContactService {
    private ContactRepository contactRepository = new ContactRepository();

    public void createContact(CreateContactRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Create contact" + request);
        contactRepository.createContact(request);
    }

    public void updateContact(long id, UpdateContactRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Updating contact" + request);
        contactRepository.updateContact(id, request.getPhoneNumber());
    }

    public void deleteContact(long id) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting item " + id);
        contactRepository.deleteContact(id);
    }

    public List<Contact> getContacts() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retrieving to-do-items... ");
        return contactRepository.getContacts();
    }


}
