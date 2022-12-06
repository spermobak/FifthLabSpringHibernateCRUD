package com.bismus.FifthLabSpringHibernateCRUD.appMenu;


import com.bismus.FifthLabSpringHibernateCRUD.exception.FailedSplitLineException;
import com.bismus.FifthLabSpringHibernateCRUD.service.entityService.PetService;
import com.bismus.FifthLabSpringHibernateCRUD.service.systemService.LanguageService;
import com.bismus.FifthLabSpringHibernateCRUD.service.systemService.MessageService;
import com.bismus.FifthLabSpringHibernateCRUD.service.entityService.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;


@ShellComponent
@RequiredArgsConstructor
public class AppMenu {
    private final PersonService personService;
    private final PetService petService;
    private final LanguageService languageService;
    private final MessageService messageService;

    private final Scanner scanner = new Scanner(System.in);

    private ResourceBundle message = ResourceBundle.getBundle("interfaceLanguage", new Locale("en"));


    //Person commands
    @ShellMethod("Find all persons")
    public void findAllPerson() {
        if (personService.findAll().isEmpty()) {
            messageService.printInterfaceMessage(message, "notFoundPersons");
        }
        messageService.printInterfaceMessage(message, "successAllPersons");
        messageService.printResultRequestMessage(personService.findAll());
    }


    @ShellMethod("Find person by id")
    public void findPersonById() {
        messageService.printInterfaceMessage(message, "findPersonById");
        int id = scanner.nextInt();

        if (personService.findById(id) == null) {
            messageService.printInterfaceMessage(message, "notFoundPerson");
        } else {
            messageService.printInterfaceMessage(message, "successPersonById");
            messageService.printResultRequestMessage(personService.findById(id));
        }
    }


    @ShellMethod("Find person by name")
    public void findPersonByName() {
        messageService.printInterfaceMessage(message, "findPersonByName");
        var name = scanner.next();

        if (personService.findByName(name) == null) {
            messageService.printInterfaceMessage(message, "notFoundPerson");
        } else {
            messageService.printInterfaceMessage(message, "successPersonByName");
            messageService.printResultRequestMessage(personService.findByName(name));
        }
    }


    @ShellMethod("Insert new person in database")
    public void insertNewPerson() {
        messageService.printInterfaceMessage(message, "addNewPerson");
        var line = scanner.nextLine();

        String[] parameters = parameterDistributor(splitLine(line));

        String name = parameters[0];
        int age = Integer.parseInt(parameters[1]);

        personService.addNewPerson(name, age);
        messageService.printInterfaceMessage(message,"successNewPerson");
    }


    @ShellMethod("Update person parameters")
    public void updatePerson() {
        messageService.printInterfaceMessage(message, "updatePersonParamWriteId");
        int id = scanner.nextInt();

        if (personService.findById(id) == null) {
            messageService.printInterfaceMessage(message, "notFoundPerson");
        } else {
            messageService.printInterfaceMessage(message, "updatePersonParamWriteNameAge");
            String line = scanner.nextLine();

            String[] parameters = parameterDistributor(splitLine(line));

            String name = parameters[0];
            int age = Integer.parseInt(parameters[1]);

            personService.updatePersonParam(id,name,age);
            messageService.printInterfaceMessage(message, "successUpdatedPerson");
        }
    }


    @ShellMethod("Delete person by id")
    public void deletePerson() {
        messageService.printInterfaceMessage(message, "deletePerson");
        int id = scanner.nextInt();

        if (personService.findById(id) == null) {
            messageService.printInterfaceMessage(message, "notFoundPerson");
        } else {
            personService.deletePerson(id);
            messageService.printInterfaceMessage(message, "successDeletedPerson");
        }
    }


    @ShellMethod("Show all pets of person")
    public void showAllPetsOfPerson() {
        messageService.printInterfaceMessage(message, "findPersonById");
        int id = scanner.nextInt();

        if (personService.findById(id) == null) {
            messageService.printInterfaceMessage(message, "notFoundPerson");
        } else {
            if (personService.findById(id).getPets().isEmpty()){
                messageService.printInterfaceMessage(message, "notFoundPetsOfPerson");
            }
            messageService.printInterfaceMessage(message, "successFindAllPetsOfPerson");
            messageService.printResultRequestMessage(personService.findById(id).getPets());
        }
    }


    //Pet command
    @ShellMethod("Find all pets")
    public void findAllPet() {
        if (petService.findAll().isEmpty()) {
            messageService.printInterfaceMessage(message, "notFoundPets");
        }
        messageService.printInterfaceMessage(message, "successAllPets");
        messageService.printResultRequestMessage(petService.findAll());
    }


    @ShellMethod("Find pet by id")
    public void findPetById() {
        messageService.printInterfaceMessage(message, "findPetById");
        int id = scanner.nextInt();

        if (petService.findById(id) == null) {
            messageService.printInterfaceMessage(message, "notFoundPet");
        } else {
            messageService.printInterfaceMessage(message, "successPetById");
            messageService.printResultRequestMessage(petService.findById(id));
        }
    }


    @ShellMethod("Find pet by name")
    public void findPetByName() {
        messageService.printInterfaceMessage(message, "findPetByName");
        var name = scanner.next();

        if (petService.findByName(name) == null) {
            messageService.printInterfaceMessage(message, "notFoundPet");
        } else {
            messageService.printInterfaceMessage(message, "successPetByName");
            messageService.printResultRequestMessage(petService.findByName(name));
        }
    }


    @ShellMethod("Insert new pet in database")
    public void insertNewPet() {
        messageService.printInterfaceMessage(message, "addNewPet");
        var line = scanner.nextLine();

        String[] parameters = parameterDistributor(splitLine(line));

        String name = parameters[0];
        int age = Integer.parseInt(parameters[1]);

        messageService.printInterfaceMessage(message, "writePersonId");
        var person_id = scanner.nextInt();

        if (personService.findById(person_id) == null){
            messageService.printInterfaceMessage(message, "notFoundPerson");
        } else {
            petService.addNewPet(name, age, person_id);
            messageService.printInterfaceMessage(message, "successNewPet");
        }
    }


    @ShellMethod("Update pet parameters")
    public void updatePet() {
        messageService.printInterfaceMessage(message, "updatePetParamWriteId");
        int id = scanner.nextInt();

        if (petService.findById(id) == null) {
            messageService.printInterfaceMessage(message, "notFoundPet");
        } else {
            messageService.printInterfaceMessage(message, "updatePetParamWriteNameAge");
            String line = scanner.nextLine();

            String[] parameters = parameterDistributor(splitLine(line));

            String name = parameters[0];
            int age = Integer.parseInt(parameters[1]);

            petService.updatePetParam(id,name,age);
            messageService.printInterfaceMessage(message, "successUpdatedPet");
        }
    }


    @ShellMethod("Delete pet by id")
    public void deletePet() {
        messageService.printInterfaceMessage(message, "deletePet");
        int id = scanner.nextInt();

        if (petService.findById(id) == null) {
            messageService.printInterfaceMessage(message, "notFoundPert");
        } else {
            petService.deletePet(id);
            messageService.printInterfaceMessage(message, "successDeletedPet");
        }
    }


    @ShellMethod("Show pet owner")
    public void showPetOwner() {
        messageService.printInterfaceMessage(message, "findPetById");
        int id = scanner.nextInt();

        if (petService.findById(id) == null) {
            messageService.printInterfaceMessage(message, "notFoundPet");
        } else {
            messageService.printInterfaceMessage(message, "successFindOwner");
            messageService.printResultRequestMessage(petService.findById(id).getPerson());
        }
    }


    @ShellMethod("Reset pet owner")
    public void resetPetOwner() {
        messageService.printInterfaceMessage(message, "findPetById");
        int pet_id = scanner.nextInt();

        if (petService.findById(pet_id) == null){
            messageService.printInterfaceMessage(message, "notFoundPet");
        } else {
            messageService.printInterfaceMessage(message, "findNewOwnerById");
            int person_id = scanner.nextInt();

            if (personService.findById(person_id) == null){
                messageService.printInterfaceMessage(message, "notFoundPerson");
            } else {
                petService.resetOwner(pet_id, person_id);
                messageService.printInterfaceMessage(message, "successResetOwner");
            }
        }
    }


    //System command
    @ShellMethod("Change language")
    public void changeLanguage() {
        messageService.printInterfaceMessage(message, "changeLanguage");
        languageService.printLanguageKey();
        var lang = scanner.next();
        message = languageService.setInterfaceLanguage(lang, message);
    }



    //support methods
    private static boolean isNumb(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String[] splitLine(String line) {
        String[] parameters = line.split(" ");
        if (parameters.length != 2) {
            throw new FailedSplitLineException();
        }
        return parameters;
    }

    private String[] parameterDistributor(String[] parameters) {
        if (isNumb(parameters[0])){
            return new String[]{parameters[1],parameters[0]};
        }else if (isNumb(parameters[1])){
            return parameters;
        } else
            throw new IllegalArgumentException("The entered string cannot be divided into name and age");
    }
}
