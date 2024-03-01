package ca.mcgill.ecse321.eventregistration.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Person {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String email;
    private String password;
    private Date creationDate;

    // Hibernate needs a default constructor, but it doesn't need to be public
    @SuppressWarnings("unused")
    private Person() {
    }

    public Person(String aName, String aEmail, String aPassword, Date aCreationDate) {
        name = aName;
        email = aEmail;
        password = aPassword;
        creationDate = aCreationDate;
    }

    // ------------------------
    // INTERFACE
    // ------------------------

    public boolean setId(int aId) {
        boolean wasSet = false;
        id = aId;
        wasSet = true;
        return wasSet;
    }

    public boolean setName(String aName) {
        boolean wasSet = false;
        name = aName;
        wasSet = true;
        return wasSet;
    }

    public boolean setEmail(String aEmail) {
        boolean wasSet = false;
        email = aEmail;
        wasSet = true;
        return wasSet;
    }

    public boolean setPassword(String aPassword) {
        boolean wasSet = false;
        password = aPassword;
        wasSet = true;
        return wasSet;
    }

    public boolean setCreationDate(Date aCreationDate) {
        boolean wasSet = false;
        creationDate = aCreationDate;
        wasSet = true;
        return wasSet;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) {
            return false;
        }
        Person other = (Person) obj;
        return this.getId() == other.getId()
                && this.getName().equals(other.getName())
                && this.getEmail().equals(other.getEmail())
                && this.getPassword().equals(other.getPassword())
                && this.getCreationDate().equals(other.getCreationDate());
    }

    public String toString() {
        return super.toString() + "[" +
                "id" + ":" + getId() + "," +
                "name" + ":" + getName() + "," +
                "email" + ":" + getEmail() + "," +
                "password" + ":" + getPassword() + "]" + System.getProperties().getProperty("line.separator") +
                "  " + "creationDate" + "="
                + (getCreationDate() != null
                        ? !getCreationDate().equals(this) ? getCreationDate().toString().replaceAll("  ", "    ")
                                : "this"
                        : "null");
    }
}