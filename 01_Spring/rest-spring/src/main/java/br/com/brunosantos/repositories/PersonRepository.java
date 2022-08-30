package br.com.brunosantos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brunosantos.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{}
