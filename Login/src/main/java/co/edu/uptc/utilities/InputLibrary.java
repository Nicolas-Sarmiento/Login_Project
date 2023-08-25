package co.edu.uptc.utilities;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Input Library class is a utility class for
 * manage the inputs, the methods can provide a good form to
 * get String and int from the user. This class uses Scanner
 * to get the inputs.
 * @Author  Nicolas Sarmiento
 */
public class InputLibrary {
    private Scanner inputLine;

    /**
     * Constructor without any param
     * Only instances a Scanner Object
     */
    public InputLibrary(){
        this.inputLine = new Scanner(System.in);
    }

    /**
     * This method receive a message for display and
     * another string for display if there is a InputMismatch Exception.
     * The method returns an integer value from the user input.
     * @param message String for display before input integer
     * @param errorMessage String for display if an InputMismatch Exception is thrown
     * @return an integer value from the user input
     */
    public int inputInt(String message, String errorMessage){
        boolean isCorrect = false;
        int returnValue = 0;
        while (!isCorrect){
            try {
                System.out.print(message);
                returnValue = inputLine.nextInt();
                isCorrect = true;
            }catch (InputMismatchException e){
                System.out.println(errorMessage);
            }
            inputLine.nextLine();

        }
        return returnValue;
    }

    /**
     * This method returns an integer from the interval that begins from
     * floor value to top value. This method display a message and requieres a
     * error message in case of InputMismatch Exception. The method is inclusive
     * in the limits.
     * @param message String for displaying before the input
     * @param errorMessage String displayed if InputMismatch is thrown
     * @param floor low limit for the input interval
     * @param top max limite for the input interval
     * @return int that is more or equal than floor and less or equal than top
     */
    public int inputInt(String message, String errorMessage, int floor, int top){
        boolean isCorrect = false;
        int returnValue = 0;
        while (!isCorrect){
            try {
                System.out.print(message);
                returnValue = inputLine.nextInt();
                if(returnValue >= floor && returnValue <= top){
                    isCorrect = true;
                }else {
                    System.out.println(errorMessage);
                }
            }catch (InputMismatchException e){
                System.out.println(errorMessage);
            }
            inputLine.nextLine();
        }
        return returnValue;
    }

    /**
     * This method returns a String from the user input. The string must be not empty
     * @param message Message for display before the input
     * @param errorMessage Message if an Exception is thrown
     * @return An user input String
     */

    public String inputString(String message, String errorMessage){
        boolean isCorrect = false;
        String returnValue = "";
        String valueNoSpaces = "";
        while (!isCorrect){
            try {
                System.out.print(message);
                returnValue = inputLine.nextLine();
                valueNoSpaces = returnValue.replaceAll(" ", "");
                isCorrect = !valueNoSpaces.equals("");
            }catch (Exception e){
                System.out.println(errorMessage);
            }
        }
        return returnValue;

    }

    public String inputStringUserName(String message, String errorMessage){
        boolean isCorrect = false;
        String returnValue = "";
        String valueNoSpaces = "";
        String noNums = "";
        while (!isCorrect){
            try {
                System.out.print(message);
                returnValue = inputLine.nextLine();
                noNums = returnValue.replaceAll("[0-9]", "");
                if ( noNums.length() < returnValue.length()) return errorMessage;
                if (!this.containsSpecialCharacters(returnValue)) return errorMessage;
                valueNoSpaces = returnValue.replaceAll(" ", "");
                isCorrect = !valueNoSpaces.equals("");
            }catch (Exception e){
                System.out.println(errorMessage);
            }
        }
        return returnValue;

    }

    private boolean containsSpecialCharacters(String str){
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) == 32) continue;
            if (str.charAt(i) < 65) return false;
            if (str.charAt(i) > 90 && str.charAt(i) < 97) return false;
            if (str.charAt(i) > 122) return false;
        }

        return true;
    }
}
