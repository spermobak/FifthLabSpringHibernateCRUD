package com.bismus.FifthLabSpringHibernateCRUD.repository.pet;

import com.bismus.FifthLabSpringHibernateCRUD.model.Pet;

import java.util.List;

public interface PetRepository {
    List<Pet> getAllPets();
    Pet getPetById(int id);

    List<Pet> getPetByName(String name);

    void insertPet(String name, int age, int personId);

    void updatePet(int id, String name, int age);

    void deletePet(int id);

    void resetOwner(int petId, int personId);
}