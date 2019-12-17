package org.fasttrackit.phonebook;

import org.fasttrackit.phonebook.persistance.ContactRepository;
import org.fasttrackit.phonebook.transfer.CreateContactRequest;

import java.io.IOException;
import java.sql.SQLException;

public class App {



    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        CreateContactRequest request = new CreateContactRequest();
        request.setFirstName("Robert");
        request.setLastName("Ilin");
        request.setPhoneNumber("123455");

        ContactRepository contactRepository = new ContactRepository();
        // contactRepository.createContact(request);
        //contactRepository.updateContact(8, "55555555555");
        //contactRepository.deleteContact(8);
        //List<Contact> contacts = contactRepository.getContactByLastName("Ilin");
        contactRepository.deleteContacts(new long[]{82, 84});
        //System.out.println("fsdfsdfsdfdfsdfdsfsdfsd");
        //  System.out.println(contacts);
    }
}