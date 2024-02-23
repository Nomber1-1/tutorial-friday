package ca.mcgill.ecse321.eventregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.service.PersonService;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/people/{pid}")
    public Person findPersonById(@PathVariable int pid) {
        return personService.findPersonById(pid);
    }

    @GetMapping("/people")
    public Iterable<Person> findAllPeople() {
        return personService.findAllPeople();
    }

    @PostMapping("/people")
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person.getName(), person.getEmail(), person.getPassword());
    }
}
