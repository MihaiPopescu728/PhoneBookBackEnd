package org.fasttrackit.phonebook.web;

import org.fasttrackit.phonebook.Domain.Contact;
import org.fasttrackit.phonebook.config.ObjectMapperConfiguration;
import org.fasttrackit.phonebook.service.ContactService;
import org.fasttrackit.phonebook.transfer.CreateContactRequest;
import org.fasttrackit.phonebook.transfer.UpdateContactRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/phonebook")

public class ContactServlet extends HttpServlet {

    private ContactService contactService = new ContactService();

    // endpoint
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        CreateContactRequest request = ObjectMapperConfiguration.getObjectMapper().readValue(req.getReader(),
                CreateContactRequest.class);

        try {
            contactService.createContact(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error:" + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);
        String id = req.getParameter("id");

        //Long wrapper class for primitive data type long

        try {
            contactService.deleteContact(Long.parseLong(id));
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error:" + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);
        String id = req.getParameter("id");
        ObjectMapperConfiguration.getObjectMapper().readValue(req.getReader(), CreateContactRequest.class);
        UpdateContactRequest request = ObjectMapperConfiguration.getObjectMapper().readValue(req.getReader(),
                UpdateContactRequest.class);
        try {
            contactService.updateContact(Long.parseLong(id), request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error:" + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);
        try {
            contactService.getContacts();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            List<Contact> toDoItems = contactService.getContacts();
            String response = ObjectMapperConfiguration.getObjectMapper().writeValueAsString(toDoItems);
            resp.getWriter().print(response);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error:" + e.getMessage());
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
    }
}