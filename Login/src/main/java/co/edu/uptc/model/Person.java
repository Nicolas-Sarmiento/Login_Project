package co.edu.uptc.model;

import java.util.Objects;

/**
 * Person Class is for manage and simulate a person
 * @Author Edwin Martinez
 * @Author Samuel Gonzalez Zambrano
 * @Author Nicolas Sarmiento
 */
public class Person {
    private String id;
    private String name;
    private String lastname;
    private Account account;


    public Person(String id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }


    public Person() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(lastname, person.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname);
    }

    public String toString(){

        return "Name: " + this.getName() + " Last Name: " + this.getLastname() + " Id: " + this.getId() + " Data Acount: " + account.toString();

    }



}

