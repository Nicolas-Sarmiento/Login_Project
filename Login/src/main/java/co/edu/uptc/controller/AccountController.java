package co.edu.uptc.controller;

import co.edu.uptc.model.Account;
import co.edu.uptc.utilities.AccountUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * The Account Controller class allows to manage a collection of accounts
 * that will be available in the execution of the program, as well as
 * the creation, deletion and editing of passwords.
 * @author Nicolas Sarmiento : Nicolas-Sarmiento
 * @version  1.0.0
 */

public class AccountController {
    private HashSet<Account> accounts;
    private AccountUtilities utility;
    private String username = "";
    private String password = "";


    public AccountController(){
        this.utility = new AccountUtilities();
        this.accounts = new HashSet<>();
    }
    public boolean loadAccounts(Account[] accounts){
        this.accounts = new HashSet<>(List.of(accounts));
        return true;
    }

    /**
     * The AddAccount method allows the creation and addition
     * of an account to the collection of accounts, validating the input parameters.
     * @param id institution member code
     * @param name Institution member names
     * @param lastName Last name of the member of the institution
     * @param role Role of the member in the institution
     * @return true if the account was added successfully, false if it already exists or the parameters are invalid
     */
    public boolean addAccount(String id, String name, String lastName, String role){
        String email = "";

        id = id.toLowerCase();
        name = this.utility.cleanNames(name);
        lastName = this.utility.cleanNames(lastName);
        role = role.toUpperCase();
        this.username = this.utility.generateUser(name, lastName, this.getUsernames());
        if(this.username.equals(" ")) return false;
        email = this.utility.generateEmail(username);

        this.password = this.utility.genNewPassword(this.getAllpasswords());

        if (!this.utility.validateId(id) || !this.utility.validateName(name) || !this.utility.validateName(lastName) || !this.utility.validatePassword(this.password)
        || !this.utility.validateRole(role)) return false;

        Account newAccount = new Account(id, username, password, role, email);

        if (this.accounts.contains(newAccount)) return false;
        this.accounts.add(newAccount);

        return true;
    }

    /**
     * getUserName returns the generated user
     * @return String with generated user
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * getPassword returns the generate password
     * @return String with password
     */
    public String getPassword(){
        return this.password;
    }
    /**
     * findAccount allows you to search for an account based on username and password
     * @param username
     * @param password
     * @return Object of type Account, said object will be null, if it is not found in the collection
     */
    public Account findAccount(String username, String password){
        for (Account acc : this.accounts){
            if (acc.getUserName().equals(username) && acc.getPassword().equals(password)) return acc;
        }
        return null;
    }

    /**
     * Allows you to remove an account from the collection based on username and password
     * @param username
     * @param password
     * @return true if the account was deleted, false if it was not deleted or not found
     */
    public boolean removeAccount(String username, String password){
        Account accountToRemove = this.findAccount(username, password);

        if (accountToRemove == null) return false;

        return this.accounts.remove(accountToRemove);
    }

    /**
     * Allows changing an account password from username and password
     * @param username account user to change password
     * @param password old password
     * @param newPassword new password
     * @return true if the change was successful, false if the account was not found or the new password is invalid
     */
    public boolean setNewPassword(String username, String password, String newPassword){
        Account accountSetPassword = this.findAccount(username, password);
        if (accountSetPassword == null) return false;
        if (!this.utility.validatePassword(newPassword)) return false;

        for (Account acc : this.accounts){
            if (acc.equals(accountSetPassword)){
                Account tempAccount = this.cloneAccount(acc);
                tempAccount.setPassword(newPassword);
                this.accounts.remove(acc);
                this.accounts.add(tempAccount);
                return true;
            };
        }
        return false;
    }
    /**
     * Small utility that is used in changing the password,
     * because it requires creating a new object so that a new hash code
     * is generated in the collection
     * @param account account to be cloned
     * @return Account object with the attributes of the entered account
     */
    private Account cloneAccount(Account account){
        return new Account(account.getId(), account.getUserName(), account.getPassword(), account.getRole(), account.getEmail());
    }

    /**
     * Returns the users in an array, this method is used for user generation
     * @return String[] returns all users in the account collection
     * @Author  Nicolas Sarmiento : Nicolas-Sarmiento
     */
    public String[] getUsernames(){
        String[] usernames = new String[this.accounts.size()];
        ArrayList<Account> listAccount = new ArrayList<>(this.accounts);
        for (int i = 0; i < usernames.length; i++){
            usernames[i] = listAccount.get(i).getUserName();
        }
        return usernames;
    }

    public String showAccounts(){
        String accountsInfo = "{";
        for (Account acc : this.accounts){
            accountsInfo += acc.toString() + "\n";
        }
        accountsInfo += "}";
        return accountsInfo;
    }

    private String[] getAllpasswords(){
        String[] passwords = new String[this.accounts.size()];
        ArrayList<Account> listAccount = new ArrayList<>(this.accounts);
        for (int i = 0; i < passwords.length; i++){
            passwords[i] = listAccount.get(i).getPassword();
        }

        return passwords;
    }


}
