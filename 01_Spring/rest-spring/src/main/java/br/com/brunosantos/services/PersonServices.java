package br.com.brunosantos.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brunosantos.data.vo.v1.PersonVO;
import br.com.brunosantos.data.vo.v2.PersonVOV2;
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
		return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
	}
	public PersonVO findById(Long id) {
		logger.info("Procurando uma pessoa!");
		var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada!"));
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO create(PersonVO personVo) {
		logger.info("Creando uma pessoa!");
		var entity = DozerMapper.parseObject(personVo, Person.class);
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 personVoV2) {
		logger.info("Creando uma pessoa!");
		var entity = personMapper.convertVOToEntity(personVoV2);
		var vo = personMapper.convertEntityToVo(personRepository.save(entity));
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Atualizando uma	 pessoa!");
		var entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada!"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deletando uma pessoa!");
		var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada!"));
		personRepository.delete(entity);
	}
}
