package ca.mcgill.ecse321.eventregistration.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.eventregistration.dto.PersonListDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonResponseDto;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.service.PersonService;

@RestController
public class PersonController {
    @Autowired
    private PersonService service;

    @GetMapping("/people")
    public PersonListDto getAllPeople() {
        List<PersonResponseDto> people = new ArrayList<PersonResponseDto>();
        for (Person p : service.getAllPeople()) {
            people.add(new PersonResponseDto(p));
        }
        return new PersonListDto(people);
    }

    @GetMapping("/people/{pid}")
    public PersonResponseDto getPerson(@PathVariable int pid) {
        Person person = service.getPerson(pid);
        return new PersonResponseDto(person);
    }

    @PostMapping("/people")
    public PersonResponseDto createPerson(@RequestBody PersonRequestDto person) {
        Person createdPerson = service.createPerson(person.getName(), person.getEmail(), person.getPassword());
        return new PersonResponseDto(createdPerson);
    }
}
