package com.bismus.FifthLabSpringHibernateCRUD.repository.person;



import com.bismus.FifthLabSpringHibernateCRUD.model.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> getAllPerson();
    Person getPersonById(int id);

    List<Person> getPersonByName(String name);


    void insertPerson(String name, int age);

    void updatePerson(int id, String name, int age);

    void deletePerson(int id);
}
