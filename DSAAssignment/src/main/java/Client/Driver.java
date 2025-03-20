/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Client;

import Boundary.OrderBoundary;
/**
 *
 * @author kahfui
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        OrderBoundary OrderProcessing = new OrderBoundary();
        OrderProcessing.SelectMenu();
    }
    
}
