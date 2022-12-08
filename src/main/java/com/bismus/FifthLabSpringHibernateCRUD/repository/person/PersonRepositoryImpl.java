package com.bismus.FifthLabSpringHibernateCRUD.repository.person;


import com.bismus.FifthLabSpringHibernateCRUD.model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@EnableTransactionManagement
@Transactional
public class PersonRepositoryImpl implements PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Person> getAllPerson() {
        return entityManager.createQuery("from Person", Person.class).getResultList();
    }


    @Override
    public Person getPersonById(int id) {
        return entityManager.find(Person.class, id);
    }


    @Override
    public List<Person> getPersonByName(String name) {
        return entityManager.createQuery("select p from Person p where name =:name")
                .setParameter("name", name).getResultList();
    }


    @Override
    public void insertPerson(String name, int age) {
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        entityManager.persist(person);
    }


    @Override
    public void updatePerson(int id, String name, int age) {
        Person person = entityManager.find(Person.class, id);
        person.setName(name);
        person.setAge(age);
        entityManager.merge(person);
    }


    @Override
    public void deletePerson(int id) {
        entityManager.remove(entityManager.find(Person.class, id));

    }
}
