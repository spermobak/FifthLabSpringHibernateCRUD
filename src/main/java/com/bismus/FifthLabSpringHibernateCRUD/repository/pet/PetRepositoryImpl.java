package com.bismus.FifthLabSpringHibernateCRUD.repository.pet;

import com.bismus.FifthLabSpringHibernateCRUD.model.Person;
import com.bismus.FifthLabSpringHibernateCRUD.model.Pet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class PetRepositoryImpl implements PetRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Pet> getAllPets() {
        return entityManager.createQuery("from Pet ", Pet.class).getResultList();
    }

    @Override
    public Pet getPetById(int id) {
        return entityManager.find(Pet.class, id);
    }

    @Override
    public List<Pet> getPetByName(String name) {
        return entityManager.createQuery("select p from Pet p where name =:name").
                setParameter("name", name).getResultList();
    }

    @Override
    public void insertPet(String name, int age, int personId) {
        Person person = entityManager.find(Person.class, personId);
        Pet pet = new Pet();
        pet.setName(name);
        pet.setAge(age);
        pet.setPerson(person);
        entityManager.persist(pet);
    }

    @Override
    public void updatePet(int id, String name, int age) {
        Pet pet = entityManager.find(Pet.class, id);
        pet.setName(name);
        pet.setAge(age);
        entityManager.merge(pet);
    }


    @Override
    public void deletePet(int id) {
        entityManager.remove(entityManager.find(Pet.class, id));
    }


    @Override
    public void resetOwner(int petId, int personId) {
        Pet pet = entityManager.find(Pet.class, petId);
        Person personNewOwner = entityManager.find(Person.class, personId);
        Person personFormerOwner = entityManager.find(Person.class,pet.getPerson().getId());

        personFormerOwner.getPets().remove(pet);
        pet.setPerson(personNewOwner);
        personNewOwner.setPets(Collections.singletonList(pet));

        entityManager.merge(pet);
        entityManager.merge(personNewOwner);
        entityManager.merge(personFormerOwner);
    }


}
