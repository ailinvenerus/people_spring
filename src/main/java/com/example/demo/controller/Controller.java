package com.example.demo.controller;

import com.example.demo.model.Document;
import com.example.demo.model.Person;
import com.example.demo.service.Service;
import com.example.demo.utils.exceptions.PersonWithIdNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
public class Controller {

    @Autowired
    private Service service;

    @GetMapping("/people")
    public List<Person> getPersons() {
        return service.getPersons();
    }

    @GetMapping("/people/{id}")
    public ResponseEntity getPerson(@PathVariable String id) {
        try {
            return ok(service.getPersonById(id));
        } catch (PersonWithIdNotFound e) {
            return ((ResponseEntity.BodyBuilder) notFound()).body(e.getMessage());
        }
    }

    @PostMapping("/people")
    public ResponseEntity postPerson(@RequestBody Person person) {
        Person person1 = new Person(person.getFirstName(), person.getLastName(), person.getDocument());
        service.addPerson(person1);
        return ok(service.getPersonById(person1.getId()));
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity deletePerson(@PathVariable String id) {
        service.deletePerson(id);
        return noContent().build();
    }

    @PutMapping("/people/{id}")
    public ResponseEntity updatePerson(@PathVariable String id, @RequestBody Person person) {
        service.updatePerson(person, id);
        return noContent().build();
    }

    @PostMapping("people/{id}")
    public ResponseEntity addDocument(@PathVariable String id, @RequestBody Document document) {
        service.addDocument(id, document);
        return noContent().build();
    }

}
