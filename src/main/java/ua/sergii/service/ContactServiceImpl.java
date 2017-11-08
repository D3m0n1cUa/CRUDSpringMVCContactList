package ua.sergii.service;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.sergii.hibernate.HibernateUtil;
import ua.sergii.model.Contact;

@Service("contactService")
@Transactional
public class ContactServiceImpl implements ContactService {

    final static Logger logger = Logger.getLogger(ContactServiceImpl.class);

    @Override
    public Contact findById(int id) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	Contact contact = null;

	Transaction transaction = session.beginTransaction();
	contact = session.get(Contact.class, id);
	transaction.commit();

	session.close();

	return contact;
    }

    @Override
    public List<Contact> findAllContacts() {
	Session session = HibernateUtil.getSessionFactory().openSession();

	Transaction transaction = session.beginTransaction();
	List<Contact> contacts = session.createCriteria(Contact.class).addOrder(Order.asc("id")).list();
	transaction.commit();
	session.close();

	if (contacts == null) {
	    contacts = Collections.EMPTY_LIST;
	}

	return contacts;
    }

    @Override
    public void saveContact(Contact contact) {
	Session session = HibernateUtil.getSessionFactory().openSession();

	Transaction transaction = session.beginTransaction();
	session.save(contact);
	transaction.commit();

	session.close();
    }

    @Override
    public Contact updateContact(Contact newContact) {
	Contact updatedContact = findById(newContact.getId());
	if (updatedContact == null) {
	    return null;
	}

	Session session = HibernateUtil.getSessionFactory().openSession();

	Transaction transaction = session.beginTransaction();

	updatedContact.setFirstName(newContact.getFirstName());
	updatedContact.setLastName(newContact.getLastName());
	updatedContact.setMobileNumber(newContact.getMobileNumber());
	updatedContact.setEmail(newContact.getEmail());
	newContact.getAddress().setId(updatedContact.getAddress().getId());
	updatedContact.setAddress(newContact.getAddress());

	session.update("Contact", updatedContact);
	transaction.commit();

	session.close();

	return updatedContact;

    }

    @Override
    public void deleteContact(Contact contact) {
	Session session = HibernateUtil.getSessionFactory().openSession();

	Transaction transaction = session.beginTransaction();
	session.delete(contact.getAddress());
	session.delete(contact);
	transaction.commit();

	session.close();
    }

}
