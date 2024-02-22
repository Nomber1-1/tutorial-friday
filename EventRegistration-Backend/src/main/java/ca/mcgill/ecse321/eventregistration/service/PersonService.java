package ca.mcgill.ecse321.eventregistration.service;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repo;

    public Iterable<Person> getAllPeople() {
        return repo.findAll();
    }

    public Person getPerson(int pid) {
        return repo.findPersonById(pid);
    }

    public Person createPerson(String name, String email, String password) {
        Date creationDate = Date.valueOf(LocalDate.now());
        Person person = new Person(name, email, email, creationDate);
        return repo.save(person);
    }
}
