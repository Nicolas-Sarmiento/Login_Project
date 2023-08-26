package co.edu.uptc.model.persontypes;

import co.edu.uptc.model.Account;
import co.edu.uptc.model.Person;

public class Student extends Person {

    public Student(String id, String name, String lastname) {
        super(id, name, lastname);
    }
    public Student(String id, String name, String lastname, Account account) {
        super(id, name, lastname, account);
    }
	    

}
