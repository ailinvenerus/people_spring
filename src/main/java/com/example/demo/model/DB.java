package com.example.demo.model;

import com.example.demo.utils.exceptions.PersonWithIdNotFound;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DB {

    private List<Person> people = new ArrayList<>();

    public List<Person> getPeople() {
        return people;
    }

    public DB() {
        loadPeople();
    }

    private void loadPeople() {
        Document pedroDoc = new Document("DNI", "33693857", "AR", LocalDate.of(1985, 6, 23));
        Document lauraDoc = new Document("DNI", "48820981", "AR", LocalDate.of(2006, 9, 5));
        Person juan = new Person("Juan", "Perez");
        Person pedro = new Person("Pedro", "Rodriguez", pedroDoc);
        Person laura = new Person("Laura", "Hernandez", lauraDoc);
        Person natalia = new Person("Natalia", "Ramirez");
        people.add(juan);
        people.add(pedro);
        people.add(laura);
        people.add(natalia);
    }

}
