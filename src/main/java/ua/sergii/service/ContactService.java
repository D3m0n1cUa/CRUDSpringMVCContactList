package ua.sergii.service;

import java.util.List;

import ua.sergii.model.Contact;

public interface ContactService {
    Contact findById(int id);

    void saveContact(Contact newContact);

    Contact updateContact(Contact contact);

    void deleteContact(Contact contact);

    List<Contact> findAllContacts();
}
