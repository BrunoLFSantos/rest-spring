package br.com.brunosantos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunosantos.data.vo.v1.PersonVO;
import br.com.brunosantos.data.vo.v2.PersonVOV2;
import br.com.brunosantos.services.PersonServices;
import br.com.brunosantos.util.MediaType;

@RestController
@RequestMapping("api/person/v1")
public class PersonController {

	@Autowired
	private PersonServices personServices;
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception{
		return personServices.findById(id);
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	public List<PersonVO> findAll() throws Exception{
		return personServices.findAll();
	}
	
	@PostMapping( 
			consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	public PersonVO create(@RequestBody PersonVO PersonVO) throws Exception{
		return personServices.create(PersonVO);
	}
	
	@PostMapping(value="/v2",
			consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	public PersonVOV2 createV2(@RequestBody PersonVOV2 PersonVOV2) throws Exception{
		return personServices.createV2(PersonVOV2);
	}
	
	@PutMapping( 
			consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
	public PersonVO update(@RequestBody PersonVO PersonVO) throws Exception{
		return personServices.update(PersonVO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception{
		personServices.delete(id);
		return ResponseEntity.noContent().build();
	}
}
