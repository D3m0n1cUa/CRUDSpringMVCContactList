package ua.sergii.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ua.sergii.model.Contact;
import ua.sergii.service.ContactService;

@RestController
public class CRUDSpringRestController {

    private ContactService contactService;

    @Autowired
    public void setContactService(ContactService contactService) {
	this.contactService = contactService;
    }

    @RequestMapping(value = "/contact/", method = RequestMethod.GET)
    public ResponseEntity<List<Contact>> listAllContacts() {
	List<Contact> contacts = contactService.findAllContacts();
	return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
    }

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> getContact(@PathVariable("id") int id) {
	Contact contact = contactService.findById(id);
	if (contact == null) {
	    return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
	}

	return new ResponseEntity<Contact>(contact, HttpStatus.OK);
    }

    @RequestMapping(value = "/contact/", method = RequestMethod.POST)
    public ResponseEntity<Void> createContact(@RequestBody Contact contact, UriComponentsBuilder ucBuilder) {
	contactService.saveContact(contact);
	HttpHeaders httpHeaders = new HttpHeaders();
	httpHeaders.setLocation(ucBuilder.path("/contact/{id}").buildAndExpand(contact.getId()).toUri());
	return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Contact> updateContact(@PathVariable("id") int id, @RequestBody Contact contact) {
	Contact updatedContact = contactService.updateContact(contact);
	if (updatedContact == null) {
	    return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
	}

	return new ResponseEntity<Contact>(updatedContact, HttpStatus.OK);
    }

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Contact> deleteContact(@PathVariable("id") int id) {
	Contact contactToDelete = contactService.findById(id);
	if (contactToDelete == null) {
	    return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
	}

	contactService.deleteContact(contactToDelete);
	return new ResponseEntity<Contact>(HttpStatus.NO_CONTENT);
    }

}
