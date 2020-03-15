package com.example.demo;

import com.example.demo.controller.Controller;
import com.example.demo.model.DB;
import com.example.demo.model.Document;
import com.example.demo.model.Person;
import com.example.demo.service.Service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.ok;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DemoApplication.class})
@WebAppConfiguration
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private WebApplicationContext wac;
	@InjectMocks
	private Controller controller;
	@Mock
	private Service service;
	@Mock
	private DB db;
	private List<Person> people = new ArrayList<>();

	@Before
	public void setup() throws Exception {
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

	@Test
	public void givenWac_whenServletContext_thenItProvidesGreetController() {
		ServletContext servletContext = wac.getServletContext();

		Assert.assertNotNull(servletContext);
		Assert.assertTrue(servletContext instanceof MockServletContext);
		Assert.assertNotNull(wac.getBean("controller"));
	}

	@Test
	public void getPeople_returnPersonJuanPerezWithoutDocument() {
		loadDB();
		when(controller.getPeople()).thenReturn(people);

		Assert.assertEquals("Juan", controller.getPeople().get(0).getFirstName());
		Assert.assertEquals("Perez", controller.getPeople().get(0).getLastName());
		Assert.assertNull(controller.getPeople().get(0).getDocument());
	}

	@Test
	public void getPeople_returnPersonPedroRodriguezWithDocument() {
		loadDB();
		when(controller.getPeople()).thenReturn(people);

		Assert.assertEquals("Pedro", controller.getPeople().get(1).getFirstName());
		Assert.assertEquals("Rodriguez", controller.getPeople().get(1).getLastName());
		Assert.assertNotNull(controller.getPeople().get(1).getDocument());
		Assert.assertEquals("AR", controller.getPeople().get(1).getDocument().getCountryCode());
	}

	@Test
	public void getPerson_returnPerson() {
		loadDB();
		when(controller.getPeople()).thenReturn(people);
		String id = controller.getPeople().get(0).getId();
		when(service.getPersonById(id)).thenReturn(people.stream().filter(p -> p.getId().equals(id)).findFirst().get());
		ResponseEntity expectedResponse = ok(service.getPersonById(id));

		Assert.assertEquals(expectedResponse, controller.getPerson(id));
	}

	@Test
	public void postPerson_returnPerson() {
		Person person = new Person("Natalia", "Ramirez");
		ResponseEntity expectedResponse = ok().build();

		Assert.assertEquals(expectedResponse, controller.postPerson(person));
	}


	private void loadDB() {
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