package br.com.brunosantos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.brunosantos.model.Person;

@Service
public class PersonServices {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	public List<Person> findAll(){

		List<Person> persons = new ArrayList<>();
		for(int i = 0; i<8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		return persons;
	}
	private Person mockPerson(int i) {
		logger.info("Procurando todas as pessoas!");
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Bruno" + i);
		person.setLastName("Santos" + i);
		person.setAdress("Morin" + i);
		person.setGender("M" + i);
		return person;
	}
	public Person findById(String id) {
		logger.info("Procurando uma pessoa!");
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Bruno");
		person.setLastName("Santos");
		person.setAdress("Morin");
		person.setGender("M");
		return person;
	}
	
	public Person create(Person person) {
		logger.info("Creando uma pessoa!");
		return person;
	}
	
	public Person update(Person person) {
		logger.info("Atualizando uma pessoa!");
		return person;
	}
	
	public void delete(String id) {
		logger.info("Deletando uma pessoa!");
	}
}
