package co.edu.uptc.controller;

import co.edu.uptc.model.Account;
import co.edu.uptc.model.Person;
import co.edu.uptc.model.persontypes.*;
import co.edu.uptc.utilities.JsonStorageUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * This class is for person management.
 * The persons are saved in a HashSet
 * @Author Nicolas Sarmiento
 * @version 1.0.0
 */
public class PersonController {
    private HashSet<Person> personCollection;
    private JsonStorageUtilities jsonStorageUtilities;

    /**
     * Empty constructor initialize the HashSet
     */
    public PersonController(JsonStorageUtilities jsonStorageUtilities){
        this.personCollection = new HashSet<>();
        this.jsonStorageUtilities = jsonStorageUtilities;
    }

    public void loadPeople(){
        this.jsonStorageUtilities.readPersons("people");
        List<Person> people = this.jsonStorageUtilities.getExistingContentsPersons();
        this.personCollection.addAll(people);
    }



    public void updateInformationFile(){
        jsonStorageUtilities.saveDataToFilePerson(personCollection.stream().toList(), "people");
    }

    /**
     * This method creates a new Person and add to the Collection
     * @param id id of the person
     * @param name only names
     * @param lastName person lastname
     * @param role the institution role
     * @return true if the person was added to the collection. False if not.
     */
    public boolean addPerson(String id, String name, String lastName, String role){
        Person newPerson = this.createPersonByRole(id, name, lastName, role);
        for (Person per : this.personCollection){
            if (per.getId().equals(newPerson.getId())) return false;
        }
        if (newPerson == null) return false;
        if(this.personCollection.add(newPerson)){
            updateInformationFile();
            return true;
        }
        return false;
    }

    /**
     * This method allows to assing an account to a person by id.
     * @param id id of the person
     * @param acc account that the person own it.
     * @return true if the assignment was successfully
     */
    public boolean assingAccount(String id, Account acc){
        Person personToAssing = this.findPersonById(id);

        for (Person p : this.personCollection){
            if (/*p.equals(personToAssing*/p.getId().equals(personToAssing.getId())){
                //Person tempPerson = this.clonePerson(p);
                Person tempPerson = p;
                tempPerson.setAccount(acc);
                this.personCollection.remove(p);
                this.personCollection.add(tempPerson);
                return true;
            }
        }
        return false;
    }

    /**
     * This method allows to cast a string into a role.
     * @param role the role in string
     * @return a Roles type Object, null if the string isn't in the roles values.
     */
    public Roles getRole(String role){
        for (Roles r: Roles.values()){
            if (r.name().equalsIgnoreCase(role)) return r;
        }
        return null;
    }


    /**
     * This method allows to return a Person Object only passing the id
     * @param id person's id
     * @return a Person Object if the id was found.
     */
    public Person findPersonById(String id){
        for (Person person : this.personCollection){
            if (person.getId().equals(id)) {
                return person;
            }
        }
        return null;
    }


    /**
     * This method allows to replicate a Person Object
     * with the same properties
     * @param p The Person Object to clone
     * @return a Person Object with te
     */
    public Person clonePerson(Person p) {
        return this.createPersonByRole(p.getId(), p.getName(), p.getLastname(), p.getAccount().getRole());
    }

    /**
     * This method creates a Person Object by Role, that means that depending on the role
     * The method will create a Student, Professor, Admin or Secretary Object
     * @param id person's id
     * @param name person's name
     * @param lastName person's lastname
     * @param role role of the person in the institute
     * @return a person Object type (Student, Administrator, Secretary, Professor)
     */
    public Person createPersonByRole(String id, String name, String lastName,String role){
        Roles typePerson = this.getRole(role);
        if (typePerson == null) return null;
        Person newPerson = null;
        switch (typePerson){
            case STUDENT :
                newPerson = new Student(id, name, lastName);
                break;
            case PROFESSOR, DIRECTOR:
                newPerson = new Professor(id, name, lastName);
                break;
            case ADMINISTRATOR:
                newPerson = new Administrator(id, name, lastName);
                break;
            case SECRETARY:
                newPerson = new Secretary(id, name, lastName);
                break;
        }

        return newPerson;
    }

    public ArrayList<Account> getAccounts(){
        ArrayList<Account> accounts = new ArrayList<>();
        for (Person p : this.personCollection){
            accounts.add(p.getAccount());
        }
        return accounts;
    }

    public HashSet<Person> getPersonCollection() {
        return personCollection;
    }

}
