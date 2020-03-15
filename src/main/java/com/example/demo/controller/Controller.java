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
    public List<Person> getPeople() {
        return service.getPeople();
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
        String id = service.addPerson(person);
        return ok(service.getPersonById(id));
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

    @PostMapping("people/{id}/document")
    public ResponseEntity addDocument(@PathVariable String id, @RequestBody Document document) {
        service.addDocument(id, document);
        return noContent().build();
    }

    @GetMapping("people/{id}/document")
    public ResponseEntity getDocument(@PathVariable String id) {
        return ok(service.getPersonById(id).getDocument());
    }

}
