/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author kahfui
 */
public class Utility {

    public static boolean IsNumeric(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static void InvalidInput() {
        System.out.println("Invalid Input. Please Try Again");
    }

    public static void Confirmation() {
        System.out.println("Are You Sure Want to Proceed? (Press Y to Confirm)");
        
    }
}
