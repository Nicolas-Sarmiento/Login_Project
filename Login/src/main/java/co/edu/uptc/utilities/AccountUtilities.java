package co.edu.uptc.utilities;

import co.edu.uptc.controller.Roles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * @author:EdwinMartinez
 * {@code @version:14/07/2023}
 */
public class AccountUtilities{
    public AccountUtilities() {
    }
    /**
     * Method that returns whether the password meets the minimum requirements to be secure
     * @param password is the parameter to compare
     * @return whether or not he validates to be a secure password
     */
    public boolean validatePassword(String password){
        boolean flagUpper=false;
        boolean flagLower=false;
        int count=0;
        //Number of numbers in the password
        for(int i=0;i<password.length();i++){
            Character character=password.charAt(i);
            if(character.isDigit(character)){
                count++;
            }
        }
        //Password case
        for (int i = 0; i < password.length(); i++) {
            char character = password.charAt(i);
            if (Character.isLetter(character)) {
                if (Character.isUpperCase(character)) {
                    flagUpper = true;
                } else if (Character.isLowerCase(character)) {
                    flagLower = true;
                }
            }
        }
        //check containing all three parameters to be safe
        if(flagUpper && flagLower && count>=2 && password.length()>=6 && password.length()<=15){
            return true;
        }
        return false;
    }

    /**
     * Method to validate that the name entered is not empty
     * @param name name to verify
     * @return returns whether or not it is empty
     */
    public boolean validateName(String name){
        if(name!=null&&name!=" "){
            return true;
        }
        return false;
    }
    /**
     * Method to verify that the method entered exists in those defined in the login
     * @param roles role to compare
     * @return returns if the role is valid
     */
    public boolean validateRole(String roles){
        for (Roles s:Roles.values()) {
            if(s.name().equalsIgnoreCase(roles)){
                return true;
            }
        }
        return false;
    }

    /**
     * Method that verifies that the id entered is not empty
     * @param id identification to compare
     * @return returns whether or not it is empty
     */
    public boolean validateId(String id){
        if(id!=null&&id!=" "){
            return true;
        }
        return false;
    }
    /**
     * Method to generate the email of the person who registers
     * @param user is the username
     * @return return the email with the extension of the university
     */
    public String generateEmail(String user){
        return user+"@uptc.edu.co";
    }//cierre método

    /**
     * Method to generate the user of the person adding a number if it is repeated
     * @param names are the usernames
     * @param lastNames user's last name
     * @param userNames the list of registered users to compare
     * @return returns the user with a number if a similar user already exists
     */
    public String generateUser(String names,String lastNames,String[] userNames){
        try {
            String[] newUserNames = new String[userNames.length];
            String[] newName = names.split(" ");
            String[] newLastNames = lastNames.split(" ");
            String aux = newName[0] + newLastNames[0];
            int count = 0;
            for (int i = 0; i < userNames.length; i++) {
                newUserNames[i] = removeDigits(userNames[i]);
            }
            for (String s : newUserNames) {
                if (s.equals(aux)) {
                    count++;
                }
            }
            if (count != 0 && count < 10) {
                return newName[0] + "." + newLastNames[0] + "0" + count;
            } else if (count != 0) {
                return newName[0] + "." + newLastNames[0] + count;
            }
            return newName[0] + "." + newLastNames[0];
        }catch (ArrayIndexOutOfBoundsException e){
            return " ";
        }
    }
    /**
     * Method to eliminate the numbers and special characters of the users
     * @param userName is the username to remove numbers and special characters
     * @return returns a username without numbers and special characters
     */
    public String removeDigits(String userName){
        return userName.replaceAll("[^a-z]","");
    }
    /**
     * Method to put it in lower case and eliminate unnecessary spaces
     * @param name
     * @return name without spaces and in lowercase
     */
    public String cleanNames(String name){
        String cleanName =  name.toLowerCase();
        ArrayList<String> names = new ArrayList<>();
        String aux = "";
        for (int i = 0; i < cleanName.length(); i++){
            if (cleanName.charAt(i) == ' ' && !aux.equals("")){
                names.add(aux);
                aux = "";
                continue;
            }

            if (cleanName.charAt(i) != ' '){
                aux += cleanName.charAt(i);
            }


        }
        if (names.size() == 0) return aux;
        cleanName = names.get(0);
        for (int i = 1; i < names.size(); i++){
            cleanName += " " + names.get(i);
        }

        return cleanName;
    }

    /**
     * Generates random, non-repeating passwords Because it compares an array of existing passwords
     * @param passwords existing passwords
     * @return String, a unique password
     */
    public String genNewPassword(String[] passwords){
        String password = "";
        HashSet<String> listPasswords = new HashSet<>(Arrays.asList(passwords));
        do {
            password = this.randomPassword();
            if (!listPasswords.contains(password)) break;
        }while (true);
        return password;
    }
    /**
     * Generate random 6 character passwords 2 numbers, 2 uppercase and 2 lowercase
     * @return 6 character random password
     */
    private String randomPassword(){
        Random rand = new Random();
        String password = "";
        password += rand.nextInt(10) + "" + rand.nextInt(10) +
                (char)(rand.nextInt(25) + 65) +
                (char)(rand.nextInt(25) + 65) +
                (char)(rand.nextInt(25) + 65) +
                (char)(rand.nextInt(25) + 97) +
                (char)(rand.nextInt(25) + 97);
        return password;
    }
}
