package ua.sergii.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ADDRESSES")
public class Address implements Serializable {

    //private static final long serialVersionUID = -1439018116784828014L;

    private int id;
    private String street;
    private String city;
    private String zipCode;

    @JsonIgnore
    private Contact contact;

    public Address() {
    }

    public Address(String street, String city, String zipCode) {
	super();
	this.street = street;
	this.city = city;
	this.zipCode = zipCode;
    }

    public Address(String street, String city, String zipCode, Contact contact) {
	this.street = street;
	this.city = city;
	this.zipCode = zipCode;
	this.contact = contact;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ADDRESS", unique = true, nullable = false)
    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    @Column(name = "STREET", nullable = false, length = 50)
    public String getStreet() {
	return street;
    }

    public void setStreet(String street) {
	this.street = street;
    }

    @Column(name = "CITY", nullable = false, length = 30)
    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    @Column(name = "ZIP_CODE", nullable = false, length = 8)
    public String getZipCode() {
	return zipCode;
    }

    public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "address")
    public Contact getContact() {
	return contact;
    }

    public void setContact(Contact contact) {
	this.contact = contact;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((city == null) ? 0 : city.hashCode());
	result = prime * result + ((contact == null) ? 0 : contact.hashCode());
	result = prime * result + id;
	result = prime * result + ((street == null) ? 0 : street.hashCode());
	result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Address other = (Address) obj;
	if (city == null) {
	    if (other.city != null)
		return false;
	} else if (!city.equals(other.city))
	    return false;
	if (contact == null) {
	    if (other.contact != null)
		return false;
	} else if (!contact.equals(other.contact))
	    return false;
	if (id != other.id)
	    return false;
	if (street == null) {
	    if (other.street != null)
		return false;
	} else if (!street.equals(other.street))
	    return false;
	if (zipCode == null) {
	    if (other.zipCode != null)
		return false;
	} else if (!zipCode.equals(other.zipCode))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Address [id=" + id + ", street=" + street + ", city=" + city + ", zipCode=" + zipCode + "]";
    }
}
