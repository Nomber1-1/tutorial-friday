package ca.mcgill.ecse321.eventregistration.dto;

import java.time.LocalDate;

import ca.mcgill.ecse321.eventregistration.model.Person;

public class PersonResponseDto {
    private int id;
    private String name;
    private String email;
    private LocalDate creationDate;

    public PersonResponseDto(Person model) {
        this.id = model.getId();
        this.name = model.getName();
        this.email = model.getEmail();
        this.creationDate = model.getCreationDate().toLocalDate();
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
