/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

import java.util.Scanner;
import utility.Utility;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import control.OrderProcessingControl;

/**
 *
 * @author kahfui
 */
public class OrderBoundary {

    private final Scanner input = new Scanner(System.in);
    private LocalDateTime currentDateTime = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String formattedDateTime = currentDateTime.format(formatter);
    private OrderProcessingControl orderControl = new OrderProcessingControl();

    public void MenuUI() {
        System.out.println("\n\nRestaurant Management System       ");
        System.out.println("[Ordering Processing Susbsystem]       ");
        System.out.println("==========================================");
        System.out.println("Current Date and Time: " + formattedDateTime);
        System.out.println("==========================================");
        System.out.println("1 : Add an new Order for a table");
        System.out.println("2 : Remove an Order for a table");
        System.out.println("3 : Update an Order for a table");
        System.out.println("4 : View Pending Order by table");
        System.out.println("5 : View All Orders by table");
        System.out.println("6 : View Full Order List by Order Time and Status");
        System.out.println("7 : Clear List For an Table");
        System.out.println("8 : Clear All Order List");
        System.out.println("0 : Exit");
        System.out.print("Please Enter Your Choice: ");
    }

    public void SelectMenu() {
        int select = -1;
        do {
            MenuUI();
            String choice = input.nextLine();
            if (Utility.IsNumeric(choice)) {
                select = Integer.parseInt(choice);

                if (select < 0 || select > 8) {
                    System.out.println("Invalid Option. Please Select Again !!!");
                    System.out.println();
                    select = -1;
                }

            } else {
                System.out.println("Invalid Option. Please Select Again !!!");
                System.out.println();
                System.out.println();
            }
            caseMenu(select);

        } while (select != 0);
    }

    private void caseMenu(int select) {
        String confirm = "N";
        String tableNo;
        String[] items;
        boolean check = false;

        switch (select) {
            case 0:
                ExitUI();
                break;

            case 1:
                orderControl.displayTables();
                tableNo = SelectTableNo();
                check = orderControl.SearchTable(tableNo);

                if (!check) {
                    System.out.println("Failed to Select Table \n\n");
                    break;
                }

                orderControl.displayItems();
                String[][] itemsWithQuantities = AddItem(); // Updated method call
                Utility.Confirmation();
                confirm = input.nextLine();

                if (!confirm.equalsIgnoreCase("Y")) {
                    System.out.println("Execution has been cancelled!\n\n");
                    break;
                }

                check = orderControl.addOrder(tableNo, itemsWithQuantities); // Updated to handle quantities

                if (check) {
                    System.out.println("Order has been created successfully!\n\n");
                } else {
                    System.out.println("Failed to add an order!\n\n");
                }
                break;

            case 2:
                orderControl.displayOrders();
                String OrderID = SelectOrderID();

                Utility.Confirmation();
                confirm = input.nextLine();

                if (!confirm.equalsIgnoreCase("Y")) {
                    System.out.println("Execution has been cancel !!!\n\n");
                    break;
                }

                check = orderControl.removeOrder(OrderID);

                if (check) {
                    System.out.println("OrderID " + OrderID + " has been remove !!!\n\n");
                } else {
                    System.out.println("Failed to remove OrderID " + OrderID + "\n\n");
                }

                break;

            case 3:
                orderControl.displayOrders();
                OrderID = SelectOrderID();
                check = orderControl.SearchOrder(OrderID);
                if (!check) {
                    System.out.println("Order ID: " + OrderID + " is Not Found");
                    break;
                }
                UpdateMenu(OrderID);
                break;

            case 4:
                orderControl.displayPendingTables();
                SearchOrderIDUI();
                break;

            case 5:
                orderControl.displayTables();
                SearchOrderIDUI();
                break;

            case 6:
                orderControl.displayOrders();
                SearchOrderIDUI();
                break;

            case 7:
                orderControl.displayTables();
                tableNo = SelectTableNo();

                Utility.Confirmation();
                confirm = input.nextLine();

                if (!confirm.equalsIgnoreCase("Y")) {
                    System.out.println("Execution has been cancel !!!\n\n");
                    break;
                }

                check = orderControl.removeOrderListForTable(tableNo);

                if (check) {
                    System.out.println("Order List for Table " + tableNo + " has been clear !!!\n\n");
                } else {
                    System.out.println("Failed to remove order list for " + tableNo + "\n\n");
                }

                break;

            case 8:
                Utility.Confirmation();
                confirm = input.nextLine();

                if (confirm.equalsIgnoreCase("Y")) {
                    orderControl.clearAllList();
                    System.out.println("All Order List has been clear !!!\n\n");
                } else {
                    System.out.println("Execution has been cancel !!!\n\n");
                }
                break;
        }

    }

    private void UpdateMenu(String OrderID) {
        String choice = null;
        do {
            System.out.println("\n\n1 : Update Item Quantity:");
            System.out.println("2 : Complete Order");
            System.out.println("3 : Back");
            System.out.print("Please Enter Your Choice: ");
            choice = input.nextLine();

            switch (choice) {
                case "1":
                    UpdateItem(OrderID);
                    break;

                case "2":
                    boolean check = orderControl.updateStatus(OrderID);
                    if (!check) {
                        System.out.print("Execution Failed ");
                    }
                    break;

                case "3":
                    break;

                default:
                    Utility.InvalidInput();
                    break;
            }
        } while (!choice.equals("3"));

    }

    private void UpdateItem(String OrderID) {
        String choice = null;
        do {
            orderControl.displayOrderDetails(OrderID);
            System.out.println("\n\n1 : Select an Item ID from Order:");
            System.out.println("2 : Back");
            System.out.print("Please Enter Your Choice: ");
            choice = input.nextLine();

            switch (choice) {
                case "1":
                    String itemID = SelectItem();
                    boolean checkItem = orderControl.SearchItem(OrderID, itemID);

                    if (checkItem) {
                        System.out.print("Enter Quantity for " + itemID + "(0 for remove): ");
                        String quantity = input.nextLine();

                        // Validate that quantity is a positive integer
                        if (!quantity.matches("\\d+") || Integer.parseInt(quantity) < 0) {
                            System.out.println("\nInvalid quantity. Please enter a positive number.");
                            break;
                        }
                        int Quantity = Integer.parseInt(quantity);

                        boolean check = orderControl.updateOrder(OrderID, itemID, Quantity);

                        if (check) {
                            System.out.println("\nItem " + itemID + " with quantity " + quantity + " updated successfully!");
                        } else {
                            System.out.println("\nFailed to update Item " + itemID + " with quantity " + quantity + " !");
                        }
                    } else {
                        System.out.println("\nInvalid Item ID. Please try again.");
                    }
                    break;

                case "2":

                    break;

                default:
                    Utility.InvalidInput();
                    break;
            }
        } while (!choice.equals("2"));

    }

    private String[][] AddItem() {
        int index = 0;
        String choice = null;
        String[][] itemsWithQuantities = new String[99][2]; // 2D array to store item ID and quantity

        do {
            if (index == 99) {
                System.out.println("\n\n1 : Reached Max Items in this order, try to add another order."); // Prevent out of range
                break;
            }

            System.out.println("\n\n1 : Select an Item ID from Menu:");
            System.out.println("2 : Confirm Order");
            System.out.print("Please Enter Your Choice: ");
            choice = input.nextLine();

            switch (choice) {
                case "1":
                    String itemID = SelectItem();
                    boolean checkItem = orderControl.SearchItem(itemID);

                    if (checkItem) {
                        System.out.print("Enter Quantity for " + itemID + ": ");
                        String quantity = input.nextLine();

                        // Validate that quantity is a positive integer
                        if (!quantity.matches("\\d+") || Integer.parseInt(quantity) <= 0) {
                            System.out.println("\nInvalid quantity. Please enter a positive number.");
                            break;
                        }

                        itemsWithQuantities[index][0] = itemID;
                        itemsWithQuantities[index][1] = quantity;

                        System.out.println("\nItem " + itemID + " with quantity " + quantity + " selected successfully!");
                        index++;
                    } else {
                        System.out.println("\nInvalid Item ID. Please try again.");
                    }
                    break;

                case "2":
                    // Validation: Ensure at least one item has been added
                    if (index == 0) {
                        System.out.println("\nNo items have been selected. Please add at least one item before confirming.");
                        choice = "1"; // Force the user to continue adding items
                    } else {
                        return itemsWithQuantities;
                    }
                    break;

                default:
                    Utility.InvalidInput();
                    break;
            }
        } while (true);

        return itemsWithQuantities;
    }

    private void SearchOrderIDUI() {

        String choice = null;
        do {
            System.out.println("\n\n1 : Search an Order by OrderID From Whole Order List :");
            System.out.println("2 : Back");
            System.out.print("Please Enter Your Choice: ");
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    String OrderID = SelectOrderID();
                    orderControl.displayOrderDetails(OrderID);
                    break;

                case "2":
                    return;

                default:
                    Utility.InvalidInput();
                    break;
            }
        } while (!choice.equals("2"));
    }

    private String SelectOrderID() {
        System.out.print("Select an Order ID :");
        String OrderID = input.nextLine();
        return OrderID;
    }

    private String SelectTableNo() {
        System.out.print("Select a Table No :");
        String tableNo = input.nextLine();
        return tableNo;
    }

    private String SelectItem() {
        System.out.print("Select a Item ID :");
        String ItemID = input.nextLine();
        return ItemID;
    }

    private void ExitUI() {
        System.out.println("Exit At: " + formattedDateTime);
        System.out.println("Have a Nice Day");
    }

}
