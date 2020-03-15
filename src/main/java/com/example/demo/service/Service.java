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

    public List<Person> getPersons() {
        return db.getPersons();
    }

    public Person getPersonById(String id) {
        return db.getPersons().stream().filter(p -> id.equals(p.getId())).findFirst().orElseThrow(() -> new PersonWithIdNotFound("Person with id not found"));
    }

    public void addPerson(Person person) {
        db.getPersons().add(person);
    }

    public void deletePerson(String id) {
        Person personToDelete = getPersonById(id);
        db.getPersons().remove(personToDelete);
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
