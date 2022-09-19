package br.com.brunosantos.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.brunosantos.controllers.PersonController;
import br.com.brunosantos.data.vo.v1.PersonVO;
import br.com.brunosantos.data.vo.v2.PersonVOV2;
import br.com.brunosantos.exceptions.RequiredObjectIsNullException;
import br.com.brunosantos.exceptions.ResourceNotFoundException;
import br.com.brunosantos.mapper.DozerMapper;
import br.com.brunosantos.mapper.custom.PersonMapper;
import br.com.brunosantos.model.Person;
import br.com.brunosantos.repositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository personRepository;
	@Autowired
	PersonMapper personMapper;
	
	
	public List<PersonVO> findAll(){
		var persons = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
		persons.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return persons;
	}
	public PersonVO findById(Long id) throws Exception {
		logger.info("Procurando uma pessoa!");
		var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada!"));
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public PersonVO create(PersonVO personVo) throws Exception {
		if(personVo == null) throw new RequiredObjectIsNullException(); 
		logger.info("Creando uma pessoa!");
		var entity = DozerMapper.parseObject(personVo, Person.class);
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 personVoV2) {
		logger.info("Creando uma pessoa!");
		var entity = personMapper.convertVOToEntity(personVoV2);
		var vo = personMapper.convertEntityToVo(personRepository.save(entity));		
		return vo;
	}
	
	public PersonVO update(PersonVO person) throws Exception {
		if(person == null) throw new RequiredObjectIsNullException(); 
		logger.info("Atualizando uma	 pessoa!");
		var entity = personRepository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada!"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deletando uma pessoa!");
		var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada!"));
		personRepository.delete(entity);
	}
}
