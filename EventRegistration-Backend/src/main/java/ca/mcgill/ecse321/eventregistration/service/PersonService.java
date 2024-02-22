package ca.mcgill.ecse321.eventregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
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

    public Person createPerson(@NonNull Person person) {
        return repo.save(person);
    }
}
