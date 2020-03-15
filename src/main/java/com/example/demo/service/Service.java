package com.example.demo.service;

import com.example.demo.model.DB;
import com.example.demo.model.Document;
import com.example.demo.model.Person;
import com.example.demo.utils.exceptions.PersonWithIdNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Service {

    @Autowired
    private DB db;

    public Map<String, Person> getPeople() {
        return db.getPeople();
    }

    public Person getPersonById(String id) {
        return db.getPeople().get(id);
    }

    public String addPerson(Person person) {
        Person person1 = new Person(person.getFirstName(), person.getLastName(), person.getDocument());
        db.getPeople().put(person1.getId(), person1);
        return person1.getId();
    }

    public void deletePerson(String id) {
        Person personToDelete = getPersonById(id);
        db.getPeople().remove(personToDelete);
    }
//todo arreglar esto porque cambia el id y estoy borrando y volviendo a crear (idem con document)
    public void updatePerson(Person person, String id) {
        Person newPerson = new Person(id, person.getFirstName(), person.getLastName(), person.getDocument());
        db.getPeople().put(id, newPerson);
    }

    public void addDocument(String id, Document document) {
        Person person = getPersonById(id);
        person.setDocument(document);
    }
//todo idem arriba
    public void updateDocument(String id, Document document) {
        Document newDocument = new Document(document.getType(), document.getNumber(), document.getCountryCode(), document.getDateOfBirth());
        Person person = getPersonById(id);
        person.setDocument(newDocument);
    }
}
