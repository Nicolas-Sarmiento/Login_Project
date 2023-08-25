package co.edu.uptc.model;

import java.util.Objects;

/**
 *  The Account class includes the attributes that make up an uptc account, it manages
 *  the attributes of code, user, password, role in the university and institutional mail
 *  @author Nicolas Sarmiento vargas
 *  @version 1.0.0
 */

public class Account {

    private String id;
    private String userName;
    private String password;
    private String role;
    private String email;

    /**
     * This constructor creates a completely Account object
     * with all properties
     * @param id
     * @param userName
     * @param password
     * @param role
     * @param email
     */

    public Account(String id, String userName, String password, String role, String email) {
        this.id=id;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    /**
     * Empty Constructor
     */
    public Account(){}

   

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * HashCode was override to use in Account Controller
     * Method
     * @return a hash code from the properties
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, password, role);
    }

    /**
     * Equals method was override to use in Account Controller HashSet
     * @param obj An object
     * @return true if obj is an instance of Account and it has the same properties false if obj isn't a instance of Account or its properties are different
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Account)) return false;

        Account objAccount = (Account) obj;

        return this.id.equals(objAccount.getId()) && this.password.equals(objAccount.getPassword()) &&
                this.role.equals(objAccount.getRole());
    }

    @Override
    public String toString() {
        return "Account [id= " + id + ", userName= " + userName +  ", role= " + role + ", email= "
                + email + "]";
    }

    
}