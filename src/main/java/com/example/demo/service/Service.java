package com.example.demo.service;

import com.example.demo.model.DB;
import com.example.demo.model.Document;
import com.example.demo.model.Person;
import com.example.demo.utils.exceptions.PersonWithIdNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Service {

    @Autowired
    private DB db;

    public List<Person> getPeople() {
        return db.getPeople();
    }

    public Person getPersonById(String id) {
        return db.getPeople().stream().filter(p -> id.equals(p.getId())).findFirst().orElseThrow(() -> new PersonWithIdNotFound("Person with id " + id + " not found"));
    }

    public void addPerson(Person person) {
        db.getPeople().add(person);
    }

    public void deletePerson(String id) {
        Person personToDelete = getPersonById(id);
        db.getPeople().remove(personToDelete);
    }

    public void updatePerson(Person person, String id) {
        Person personId = getPersonById(id);
        Person newPerson = new Person(personId.getId(), person.getFirstName(), person.getLastName(), person.getDocument());
        this.deletePerson(id);
        this.addPerson(newPerson);
    }

    public void addDocument(String id, Document document) {
        Person person = getPersonById(id);
        person.setDocument(document);
    }
}
