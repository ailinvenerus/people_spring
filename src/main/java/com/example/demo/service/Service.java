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

    public void updatePerson(Person person, String id) {
        Person editPerson = db.getPeople().get(id);
        editPerson.setFirstName(person.getFirstName());
        editPerson.setLastName(person.getLastName());
        editPerson.setDocument(person.getDocument());
        db.getPeople().put(id, editPerson);
    }

    public void addDocument(String id, Document document) {
        Person person = getPersonById(id);
        person.setDocument(document);
    }

    public void updateDocument(String id, Document document) {
        Person editPerson = db.getPeople().get(id);
        Document editDocument = editPerson.getDocument();
        editDocument.setType(document.getType());
        editDocument.setNumber(document.getNumber());
        editDocument.setCountryCode(document.getCountryCode());
        editDocument.setDateOfBirth(document.getDateOfBirth());
        editPerson.setDocument(editDocument);
        db.getPeople().put(id, editPerson);
    }
}
