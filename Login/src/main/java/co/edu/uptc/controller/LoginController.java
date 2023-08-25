package co.edu.uptc.controller;
import co.edu.uptc.model.Account;
import co.edu.uptc.model.Person;
import co.edu.uptc.model.persontypes.Administrator;
import co.edu.uptc.model.persontypes.Professor;
import co.edu.uptc.model.persontypes.Student;


/**
     * The loginController class is responsible for
     * verifying the data entered, validates and saves
     * the user in the loggedAcount attribute.
     * and the person that the account belongs
     * on the atribute loggedPerson.
     * @author Samuel Gonzalez Zambrano
     * @version  1.1
     * 
 */
public class LoginController {

    Account loggedAcount;
    Person loggedPerson;

    AccountController acc;

    private final Person[] defaultPeople = {
            new Student("202210568","manuel","Martinez"),
            new Student("2021456", "Maria", "Rodriguez"),
            new Student("2020154","Juan", "Velandia Fonseca"),
            new Student("2022159", "Maria", "Rodriguez Vega"),
            new Professor("46389778", "Johana", "Torres Perez"),
            new Professor("10953483", "Ivan", "Mendoza"),
            new Administrator("47865421", "Laura","Castillo")
    };

    private final Account[] defaultAccounts = {
            new Account("202210568", "manuel.martinez", "Masfx83", Roles.STUDENT.name(), "manuel.martinez@uptc.edu.co"),
            new Account("2021456","maria.rodriguez","ho",Roles.STUDENT.name(), "maria.rodriguez@uptc.edu.co"),
            new Account("2020154", "juan.velandia","njksAPO293",Roles.STUDENT.name(), "juan.velandia@uptc.edu.co"),
            new Account("2022159", "maria.rodriguez01","HJAkjsf234",Roles.STUDENT.name(), "maria.rodriguez01@uptc.edu.co"),
            new Account("46389778", "johana.torres","JKJfsdhf334",Roles.PROFESSOR.name(), "johana.torres@uptc.edu.co"),
            new Account("10953483","ivan.mendoza","HJ23jkil",Roles.PROFESSOR.name(), "ivan.mendoza@uptc.edu.co"),
            new Account("47865421","laura.castillo","ABcd12",Roles.ADMINISTRATOR.name(), "laura.castillo@uptc.edu.co")
    };
    private PersonController personControler;
    public LoginController(){
        this.loggedAcount = new Account();
        this.loggedPerson = new Person();
        this.acc = new AccountController();
        this.personControler = new PersonController();
        this.assignDefaultAccounts();
        this.acc.loadAccounts(this.defaultAccounts);
        this.personControler.loadPeople(this.defaultPeople);
    }
    /**
     * Main method, check the name and count if it matches
     * any user, it brings it and saves it in loggedAcount.
     * also 
     * @param nameUser,password both entered by user
     * @return If the credentials entered are valid,
     * save the user and return true.
    * 
 */
    public  boolean login(String nameUser, String password){
        

        if(acc.findAccount(nameUser, password) != null){
            loggedAcount = acc.findAccount(nameUser, password);
            loggedPerson = personControler.findPersonById(loggedAcount.getId());
            return true;

        }

        return false;
    }
    /**
     * Allows the user who entered to change their password, only allows
     * it if they are already logged in. You must enter the first password
     * and then there if you proceed to the change if you enter the original
     * password wrong, the change is canceled
     * @param oldpassword,newpassword both entered by user
     * @return If the credentials entered are valid, change the password.
    * 
 */

    public boolean changePassword(String oldpassword, String newPassword){
        try {
            if(oldpassword.equals(newPassword)){
                return false;
            } else if(loggedAcount != null && loggedAcount.getPassword().equals(oldpassword)){
                boolean methodAnswer = acc.setNewPassword(loggedAcount.getUserName(),oldpassword, newPassword );
                loggedAcount = acc.findAccount(loggedAcount.getUserName(), newPassword);
                Person person = personControler.findPersonById(loggedAcount.getId());
                person.setAccount(loggedAcount);
                return methodAnswer;
            }
        } catch (Exception e) {}
       

        return false;
    }

    /**
     * Log out, therefore the loggedAcount attribute becomes null
     * @return boolean.
    * 
 */

    public boolean logout(){
        if(loggedAcount != null){
            loggedAcount = null;
            return true;
        }

        return false;
    }

    /**
     * User registration, requires all the base elements,
     * then they are validated, creates the people
     * @param name,lastname user's full name
     * @param id user document
     * @param role Specify the role of the user to create
     * @return control boolean, if something fails the process is canceled.
    * 
 */

    public boolean signin(String name, String lastName, String id, String role){
       
       if(personControler.addPerson(id, name, lastName, role)){
            Person person = personControler.findPersonById(id);
            if (acc.addAccount(person.getId(), person.getName(), person.getLastname(), role)) {
                personControler.assingAccount(person.getId(), acc.findAccount(acc.getUsername(), acc.getPassword()));
                return true;
            }
       }
        return false;
    }
      /**
       * getUserName returns the generated user
       * @return String with generated user
       */
    public String getUserName(){
        return acc.getUsername();
    }
    /**
     * getPassword returns the generate password
     * @return String with password
     */
    public String getPassword(){
        return acc.getPassword();
    }

    public String getName(){
        return this.loggedPerson.getName() + " " + this.loggedPerson.getLastname();
    }
    /**
     * Method to view the information of the account that is logged in
     * @return String with all the account information.
    * 
 */

    public String showInfoLoggedAcount(){
        if(loggedAcount != null){
            return loggedAcount.toString();
        }
        return "No ha inciado sesion";
    }
    
    /**
     *Method to view Role of person logging on
     * @return String the role of the account.
    * 
    */

    public String showRol(){
        if(loggedAcount != null){
            return loggedAcount.getRole();
        }
        return "No ha inciado sesion";
    }

      /**
       * This method displays all existing accounts so far
       * @return a String with all the accounts
       */

    public String showAccounts(){
        return acc.showAccounts();
    }

    public Person getLoggedPerson(){return this.loggedPerson;}

   private void assignDefaultAccounts(){
        for (int i = 0; i < this.defaultAccounts.length; i++){
            this.defaultPeople[i].setAccount(this.defaultAccounts[i]);
        }
   }




}
