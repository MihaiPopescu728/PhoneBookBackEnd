package org.fasttrackit.phonebook.persistance;

import org.fasttrackit.phonebook.Domain.Contact;
import org.fasttrackit.phonebook.transfer.CreateContactRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    public void createContact(CreateContactRequest request) throws SQLException, IOException, ClassNotFoundException {
        String sql = "INSERT INTO phonebook (firstName, lastName, phoneNumber) VALUES (?,?,?)";
        // try with resources
        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, (request.getLastName()));
            preparedStatement.setString(3, (request.getPhoneNumber()));
            preparedStatement.executeUpdate();

        }

    }

    public void updateContact(long id, String phoneNumber) throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE phonebook SET  phoneNumber = ? WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();

        }
    }

    public void deleteContact(long id) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM phonebook WHERE id=?";
        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        }
    }

    public List<Contact> getContactByLastName(String lastName) throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT id, firstName, lastName, phoneNumber FROM phonebook WHERE lastName=?";
        List<Contact> contacts = new ArrayList<>();
        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, lastName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Contact contact = new Contact();
                    contact.setId(resultSet.getLong("id"));
                    contact.setFirstName(resultSet.getString("firstName"));
                    contact.setLastName(resultSet.getString("lastName"));
                    contact.setPhoneNumber(resultSet.getString("phoneNumber"));

                    contacts.add(contact);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public List<Contact> getContacts() throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT id, firstName, lastName, phoneNumber FROM phonebook";

        List<Contact> contacts = new ArrayList<>();

        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getLong("id"));
                contact.setFirstName(resultSet.getString("firstName"));
                contact.setLastName(resultSet.getString("lastName"));
                contact.setPhoneNumber(resultSet.getString("phoneNumber"));

                contacts.add(contact);
            }
            return contacts;
        }
    }

    public void deleteContacts(long[] ids) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM phonebook WHERE id in (?)";
        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < ids.length; i++) {
                preparedStatement.setLong(1, ids[i]);
                preparedStatement.executeUpdate();
            }
        }
    }

}
