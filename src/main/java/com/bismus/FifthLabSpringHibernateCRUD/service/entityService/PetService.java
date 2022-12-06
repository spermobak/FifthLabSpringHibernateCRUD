package com.bismus.FifthLabSpringHibernateCRUD.service.entityService;

import com.bismus.FifthLabSpringHibernateCRUD.exception.PersonByIdNotFoundException;
import com.bismus.FifthLabSpringHibernateCRUD.exception.PersonByNameNotFoundException;
import com.bismus.FifthLabSpringHibernateCRUD.exception.PersonTableIsEmptyException;
import com.bismus.FifthLabSpringHibernateCRUD.model.Pet;
import com.bismus.FifthLabSpringHibernateCRUD.repository.pet.PetRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepositoryImpl petRepository;

    public List<Pet> findAll(){
        if (petRepository.getAllPets().isEmpty()){
            throw new PersonTableIsEmptyException();
        }
        return petRepository.getAllPets();
    }

    public Pet findById(int id){
        if (petRepository.getPetById(id) == null){
            throw new PersonByIdNotFoundException(id);
        }
        return petRepository.getPetById(id);
    }

    public List<Pet> findByName(String name){
        if (petRepository.getPetByName(name).isEmpty()){
            throw new PersonByNameNotFoundException(name);
        }
        return petRepository.getPetByName(name);
    }

    public void addNewPet(String name, int age, int personId){
        petRepository.insertPet(name, age, personId);
    }

    public void updatePetParam(int id, String name, int age){
        petRepository.updatePet(id, name, age);
    }

    public void resetOwner(int petId, int personId){petRepository.resetOwner(petId, personId);}

    public void deletePet(int id){
        petRepository.deletePet(id);
    }
}
