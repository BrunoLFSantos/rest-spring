package br.com.brunosantos.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.brunosantos.data.vo.v1.PersonVO;
import br.com.brunosantos.exceptions.RequiredObjectIsNullException;
import br.com.brunosantos.model.Person;
import br.com.brunosantos.repositories.PersonRepository;
import br.com.brunosantos.services.PersonServices;
import br.com.brunosantos.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

	MockPerson input;
	
	@InjectMocks
	private PersonServices personServices;
	
	@Mock
	private PersonRepository personRepository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(input);
	}
	
	@Test
	void testFindById() throws Exception {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
		var result = personServices.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAdress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testFindAll() throws Exception {
		List<Person> entityList = input.mockEntityList();		
		when(personRepository.findAll()).thenReturn(entityList);
		var people = personServices.findAll();
		assertNotNull(people);
		assertEquals(14, people.size());
		var personOne = people.get(1);
		assertNotNull(personOne);
		assertNotNull(personOne.getKey());
		assertNotNull(personOne.getLinks());
		assertTrue(personOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", personOne.getAdress());
		assertEquals("First Name Test1", personOne.getFirstName());
		assertEquals("Last Name Test1", personOne.getLastName());
		assertEquals("Female", personOne.getGender());
		
	}

	@Test
	void testCreate() throws Exception {
		Person entity = input.mockEntity(1);
		Person persisted = entity;
		persisted.setId(1L);
		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);
		when(personRepository.save(entity)).thenReturn(persisted);
		var result = personServices.create(vo);
		if(result != null) {
			assertNotNull(result);
			assertNotNull(result.getKey());
			assertNotNull(result.getLinks());
			assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
			assertEquals("Addres Test1", result.getAdress());
			assertEquals("First Name Test1", result.getFirstName());
			assertEquals("Last Name Test1", result.getLastName());
			assertEquals("Female", result.getGender());
		}
	}
	
	@Test
	void testCreateWithNullPerson() throws Exception {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			personServices.create(null);
		});
		String expectedMessage = "It is not allowed to persist a null object";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

//	@Test
//	void testCreateV2() {
//		fail("Not yet implemented");
//	}

	@Test
	void testUpdate() throws Exception {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		Person persisted = entity;
		persisted.setId(1L);
		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);
		when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
		when(personRepository.save(entity)).thenReturn(persisted);
		var result = personServices.update(vo);
		if(result != null) {
			assertNotNull(result);
			assertNotNull(result.getKey());
			assertNotNull(result.getLinks());
			assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
			assertEquals("Addres Test1", result.getAdress());
			assertEquals("First Name Test1", result.getFirstName());
			assertEquals("Last Name Test1", result.getLastName());
			assertEquals("Female", result.getGender());
		}
	}
	
	@Test
	void testUpdateWithNullPerson() throws Exception {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			personServices.update(null);
		});
		String expectedMessage = "It is not allowed to persist a null object";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testDelete() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
		personServices.delete(1L);
	}

}
