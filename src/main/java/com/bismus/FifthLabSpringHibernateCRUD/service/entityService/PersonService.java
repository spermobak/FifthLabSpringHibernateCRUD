package com.bismus.FifthLabSpringHibernateCRUD.service.entityService;


import com.bismus.FifthLabSpringHibernateCRUD.exception.PersonByIdNotFoundException;
import com.bismus.FifthLabSpringHibernateCRUD.exception.PersonByNameNotFoundException;
import com.bismus.FifthLabSpringHibernateCRUD.exception.PersonTableIsEmptyException;
import com.bismus.FifthLabSpringHibernateCRUD.model.Person;
import com.bismus.FifthLabSpringHibernateCRUD.repository.person.PersonRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepositoryImpl personRepository;

    public List<Person> findAll(){
        if (personRepository.getAllPerson().isEmpty()){
            throw new PersonTableIsEmptyException();
        }
        return personRepository.getAllPerson();
    }

    public Person findById(int id){
        if (personRepository.getPersonById(id) == null){
            throw new PersonByIdNotFoundException(id);
        }
        return personRepository.getPersonById(id);
    }

    public List<Person> findByName(String name){
        if (personRepository.getPersonByName(name).isEmpty()){
            throw new PersonByNameNotFoundException(name);
        }
        return personRepository.getPersonByName(name);
    }

    public void addNewPerson(String name, int age){
        personRepository.insertPerson(name, age);
    }

    public void updatePersonParam(int id, String name, int age){
        personRepository.updatePerson(id, name, age);
    }

    public void deletePerson(int id){
        personRepository.deletePerson(id);
    }
}
