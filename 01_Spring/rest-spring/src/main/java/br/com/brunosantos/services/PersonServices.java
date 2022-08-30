package br.com.brunosantos.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brunosantos.exceptions.ResourceNotFoundException;
import br.com.brunosantos.model.Person;
import br.com.brunosantos.repositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository personRepository;
	public List<Person> findAll(){
		return personRepository.findAll();
	}
//	private Person mockPerson(int i) {
//		logger.info("Procurando todas as pessoas!");
//		Person person = new Person();
//		person.setId(counter.incrementAndGet());
//		person.setFirstName("Bruno" + i);
//		person.setLastName("Santos" + i);
//		person.setAdress("Morin" + i);
//		person.setGender("M" + i);
//		return person;
//	}
	public Person findById(Long id) {
		logger.info("Procurando uma pessoa!");
//		Person person = new Person();
//		person.setId(counter.incrementAndGet());
//		person.setFirstName("Bruno");
//		person.setLastName("Santos");
//		person.setAdress("Morin");
//		person.setGender("M");
		return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada!"));
	}
	
	public Person create(Person person) {
		logger.info("Creando uma pessoa!");
		return personRepository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Atualizando uma	 pessoa!");
		Person entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada!"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		return personRepository.save(entity);
	}
	
	public void delete(Long id) {
		logger.info("Deletando uma pessoa!");
		Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada!"));
		personRepository.delete(entity);
	}
}
