
package co.edu.uptc.test;

import co.edu.uptc.controller.ForumController;
import co.edu.uptc.controller.LoginController;
import co.edu.uptc.utilities.InputLibrary;

import java.util.ArrayList;

/**
 * This class is to show the menu with its functions so
 * that it can be instantiated without having to make changes in the logic.
 * @Author Edwin Martinez
 * @Author Samuel Gonzalez
 * @Author Nicolas Sarmiento
 */
public class LoginView {

    private final InputLibrary util;
    private  final LoginController loginController;
    private final String[] roles;
    private final String errorMessage;
    private final ForumController forumController;


    public LoginView(){
        this.util = new InputLibrary();
        this.loginController = new LoginController();
        this.forumController = new ForumController();
        this.roles = new String[]{"Student", "Professor", "Secretary", "Administrator"};
        this.errorMessage = "Invalid input. Try again";

    }
    /**
     *This is the main method that shows the menu
     */
    public static void main(String[] args) {
        LoginView portal = new LoginView();
        int decision;
        String message = """
                =============================
                |\t\t\tUPTC\t\t\t|
                |\t\t1.Login\t\t\t\t|
                |\t\t2.Exit\t\t\t\t|
                =============================""";
        String password = "",userName = "";
        
        do {
            System.out.println(message);
            decision = portal.util.inputInt("Selection --------> ", "Invalid input. Try again");
            switch (decision) {
                case 1 :
                    userName = portal.util.inputString("\tUsername: ", "Invalid Input. Try again");
                    password = portal.util.inputString("\tPassword: ", "Invalid input. Try again");
                    if (portal.loginController.login(userName, password)) {
                        portal.login();
                    } else {
                        System.out.println("User name or password incorrect");
                    }
                break;
                case 2 :
                    System.out.println("Coming out....");
                break;
                default:
                    System.out.println("Invalid option. Try again");
            }
        }while (decision!=2);
    }

    /**
     * This method shows the options after login
     */
    public void login(){
        System.out.println("Welcome :) " + this.loginController.getName());
        String role = loginController.showRol();

        if (role.equals("ADMINISTRATOR")) {
            this.optionsGeneralsLogin();
            return;
        }
        if (role.equals("STUDENT") || role.equals("PROFESSOR")){
            this.optionsBasic();
            return;
        }

        this.optionsBasicNoForum();
    }

    /**
     * This method shows the option for Administrator to show all accounts and register new accounts
     */
    public  void optionsGeneralsLogin(){
        String loginMessage = """
                ========================
                |\t1.Sing in accounts\t|
                |\t2.Delete account\t|
                |\t3.See accounts\t|
                |\t4.Change password\t\t\t|
                |\t5.Forum\t\t\t|
                |\t6.logout\t\t\t|
                ========================""";
        int decision,role;
        do{
            System.out.println(loginMessage);
            decision = this.util.inputInt("Selection -----> ", this.errorMessage);
            switch (decision){
                case 1 :
                    System.out.println(this.singInAccounts() ?  "you successfully registered\n" +
                            "your user name is: "+loginController.getUserName()+"\n your password is: "+loginController.getPassword(): "Error with your data");
                break;
                case 2:
                    System.out.println("Type the id of the account to delete");
                    System.out.println(loginController.deletePerson(util.inputString("Selection -----> ", "Invalid ID"))?"The account has been deleted ":"There is an error with the account id");
                    break;
                case 3:
                    System.out.println(loginController.showAccounts());
                break;
                case 4:
                    System.out.println(this.changePassword() ? "password change was successful":"Error");
                break;
                case 5:
                    forumMenu();
                    break;
                case 6:
                    System.out.println(this.logOut() ? "Logging out" : "Error. Try again");
                break;
                default:
                    System.out.println("Invalid option, try again.");
                break;
            }
        }while(decision!=6);
    }

    public boolean singInAccounts(){
        String names = "", lastNames = "", id = "";
        int roleIndex = 0;

        roleIndex = this.util.inputInt("\tType your role in the university\n\t1.Student\n\t2.Professor\n\t3.Secretary\n\t-> ", errorMessage, 1,3);
        do {
            names = this.util.inputStringUserName("\tType their names: ", this.errorMessage);
            if (names.equals(this.errorMessage)) System.out.println("\tThe name doesn't contain numbers or symbols and it must be not empty");
        }while (names.equals(this.errorMessage));

        do {
            lastNames = this.util.inputStringUserName("\tType your last name: ", errorMessage);
            if (lastNames.equals(this.errorMessage)) System.out.println("\tThe last name doesn't contain numbers or symbols and it must be not empty");
        }while (lastNames.equals(this.errorMessage));

        id = this.util.inputString("\tType your identification: ", errorMessage);

        return loginController.signin(names,lastNames,id,this.roles[roleIndex-1]);
    }

    public boolean changePassword(){
        String oldPassword = "", newPassword = "";
        oldPassword = this.util.inputString("\tType old password: ", errorMessage);
        newPassword = this.util.inputString("\t Your password will include 2 Uppercase characters, 2 Lowercase characters, 2 numbers. At least 6 characters.\n\tType new password: ", errorMessage);
        return this.loginController.changePassword(oldPassword, newPassword);
    }

    public boolean logOut(){
        return this.loginController.logout();
    }


    /**
     * This method shows the basic options for people who do not have special permissions
     */
    public  void optionsBasic(){
        String message = """
                ========================
                |\t1.change password\t|
                |\t2.Forums\t\t\t|
                |\t3.logout\t\t\t|
                ========================""";

        int decision = 0;
        do{
            System.out.println(message);
            decision = this.util.inputInt("Selection -----> ", this.errorMessage);
            switch (decision){
                case 1:
                    System.out.println(this.changePassword() ? "password change was successful": "Error");
                break;
                case 2:
                    forumMenu();
                    break;
                case 3:
                    System.out.println(this.logOut() ? "Logging out" : "Error. Try again");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
                break;
            }

        }while(decision!=3);
    }
    /**
     * This method shows the basic options for people who do not have special permissions for forums
     */

    public  void optionsBasicNoForum(){
        String message = """
                ========================
                |\t1.change password\t|
                |\t2.logout\t\t\t|
                ========================""";

        int decision = 0;
        do{
            System.out.println(message);
            decision = this.util.inputInt("Selection -----> ", this.errorMessage);
            switch (decision){
                case 1:
                    System.out.println(this.changePassword() ? "password change was successful": "Error");
                    break;
                case 2:
                    System.out.println(this.logOut() ? "Logging out" : "Error. Try again");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
                    break;
            }

        }while(decision!=2);
    }
    /**
     * main menu for forum
     */
    public void forumMenu(){
        int option = 0;
        do {
            System.out.println("+---------+ FORUMS+---------+");
            if (this.loginController.showRol().equals("PROFESSOR") || this.loginController.showRol().equals("ADMINISTRATOR")){
                this.manageForums();
            }
            System.out.println(this.forumController.seeTitles());
            option = this.util.inputInt("Press -1 to Exit\nSelect Forum -----> ", this.errorMessage);
            if (option == -1) break;

            if (this.forumController.selectForum(option-1)){
                this.forumActions();
            }else {
                System.out.println("Forum not found! Try again");
            }


        }while (true);
    }

    /**
     * show Options basics for forum
     */
    public void forumActions(){
        int option = 0;
        String forumOptions = """
                ========================
                |\t1.add Answer\t\t|
                |\t2.Delete Answer\t\t|
                |\t3.Exit\t\t\t\t|
                ========================
                """;
        String answer = "";
        ArrayList<String> ownAns = new ArrayList<>();
        do {
            System.out.println(this.forumController.seeForum());
            System.out.println(forumOptions);
            option = this.util.inputInt("Selection ----> ", this.errorMessage, 1, 3);

            switch (option){
                case 1:
                    answer = this.util.inputString("Enter your Answer: \n", "No empty answers");
                    this.forumController.addComment(answer,loginController.getLoggedPerson());
                    break;
                case 2:
                    ownAns = forumController.getOwnAnswers(this.loginController.getLoggedPerson());
                    if (ownAns.size() > 0){
                        for (int i = 0; i < ownAns.size(); i++){
                            System.out.println("+-------------------------+\n\t\t\t" + (i+1) + "\t\t\t\n"+ "+-------------------------+\n" + ownAns.get(i));
                        }
                        int comment = this.util.inputInt("Press 0 to cancel\nComment to delete: ", this.errorMessage, 0, ownAns.size());
                        if (comment == 0) break;
                        forumController.deleteComment(ownAns.get(comment-1), loginController.getLoggedPerson());
                    }else {
                        System.out.println("You don't have any answers. Create one :)");
                    }
                    break;
                case 3:
                    System.out.println("Closing Forum!");
                    break;
            }

            if (option == 3) break;
        }while (true);
    }

    /**
     * Options special for professor or administrator regard of forum
     */
    public void manageForums(){
        String forumOptions = """
                ========================
                |\t1.add Forum\t\t\t|
                |\t2.Delete Forum\t\t|
                |\t3.Continue\t\t\t|
                ========================
                """;
        String forumTitle = "";
        String description = "";
        int index = 0;
        int option = 0;
        do {
            System.out.println(forumOptions);
            option = this.util.inputInt("Selection ---->", this.errorMessage, 1, 3);

            switch (option){
                case 1:
                    System.out.println("+--------------+ NEW FORUM +--------------+");
                    forumTitle = this.util.inputString("Forum Title: ", "Title must be not blank");
                    description = this.util.inputString("Descritpion: ", "Description is necessary");
                    System.out.println(this.forumController.createdForum(forumTitle, description)? "Forum added successfully" : "Error :(");
                    break;
                case 2:
                    System.out.println(this.forumController.seeTitles());
                    index = this.util.inputInt("Select forum: ", this.errorMessage);
                    if (this.forumController.deleteForum(index-1)){
                        System.out.println("Deleting forum");
                    }else {
                        System.out.println("Forum not found! Try again");
                    }
                    break;
            }

            if (option == 3) break;
        }while (true);
    }
}
