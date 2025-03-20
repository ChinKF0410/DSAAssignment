/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entity.*;
import ADT.*;
import DAO.InitializeData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author kahfui
 */
public class OrderProcessingControl {

    private ListInterface<Orders> orders = InitializeData.getOrders();
    private ListInterface<Table> tables = InitializeData.getTable();
    private ListInterface<Items> items = InitializeData.getItems();

    public void displayTables() {
        System.out.println("\n\n+----------------------------------------------------------+");
        System.out.println("|                         Table Status                     |");
        System.out.println("+----------------------------------------------------------+");
        System.out.printf("| %-10s | %-10s | %-30s |\n", "Table No", "Capacity", "Orders");
        System.out.println("+----------------------------------------------------------+");
        for (int i = 0; i < tables.getNumberOfEntries(); i++) {

            // Format order information
            StringBuilder orderDetails = new StringBuilder();
            for (int j = 0; j < tables.get(i + 1).getOrderList().getNumberOfEntries(); j++) {
                Orders order = tables.get(i + 1).getOrderList().get(j + 1);
                orderDetails.append(order.getOrderID());
                if (j < tables.get(i + 1).getOrderList().getNumberOfEntries() - 1) {
                    orderDetails.append(", "); // Add a comma between orders
                }
            }

            // Display table and orders
            System.out.printf("| %-10s | %-10d | %-30s |\n", tables.get(i + 1).getTableNo(), tables.get(i + 1).getNoPax(),
                    orderDetails.length() > 0 ? orderDetails.toString() : "No Orders");
        }
        System.out.println("+----------------------------------------------------------+");
    }

    public void displayPendingTables() {
        System.out.println("\n\n+----------------------------------------------------------+");
        System.out.println("|                         Table Status                     |");
        System.out.println("+----------------------------------------------------------+");
        System.out.printf("| %-10s | %-10s | %-30s |\n", "Table No", "Capacity", "Orders");
        System.out.println("+----------------------------------------------------------+");
        for (int i = 0; i < tables.getNumberOfEntries(); i++) {

            // Format order information
            StringBuilder orderDetails = new StringBuilder();
            for (int j = 0; j < tables.get(i + 1).getOrderList().getNumberOfEntries(); j++) {
                Orders order = tables.get(i + 1).getOrderList().get(j + 1);
                if (!order.getStatus().equalsIgnoreCase("Pending")) {
                    continue;
                }
                orderDetails.append(order.getOrderID());
                if (j < tables.get(i + 1).getOrderList().getNumberOfEntries() - 1) {
                    orderDetails.append(", "); // Add a comma between orders
                }
            }

            // Display table and orders
            System.out.printf("| %-10s | %-10d | %-30s |\n", tables.get(i + 1).getTableNo(), tables.get(i + 1).getNoPax(),
                    orderDetails.length() > 0 ? orderDetails.toString() : "No Pending Orders");
        }
        System.out.println("+----------------------------------------------------------+");
    }

    public void displayItems() {
        System.out.println("\n\n+-----------------------------------------------+");
        System.out.println("|                   Items List                  |");
        System.out.println("+-----------------------------------------------+");
        System.out.printf("| %-10s | %-20s | %-10s |\n", "Item ID", "Item Name", "Price");
        System.out.println("+-----------------------------------------------+");

        for (int i = 1; i <= items.getNumberOfEntries(); i++) {
            Items item = items.get(i);
            System.out.printf("| %-10s | %-20s | %-10.2f |\n",
                    item.getItemID(),
                    item.getItemDetails(),
                    item.getPrice());
        }

        System.out.println("+-----------------------------------------------+");
    }

    public void displayOrders() {
        System.out.println("\n\n+---------------------------------------------------------------------+");
        System.out.println("|                       Order Details                                 |");
        System.out.println("+---------------------------------------------------------------------+");
        System.out.printf("| %-10s | %-10s | %-15s | %-10s | %-10s |\n",
                "Order ID", "Table No", "Order Date", "Order Time", "Status");
        System.out.println("+---------------------------------------------------------------------+");

        for (int i = 1; i <= orders.getNumberOfEntries(); i++) {
            Orders order = orders.get(i);
            System.out.printf("| %-10s | %-10s | %-15s | %-10s | %-10s |\n",
                    order.getOrderID(),
                    order.getTable().getTableNo(),
                    order.getOrderDate(),
                    order.getOrderTime(),
                    order.getStatus());
        }
        System.out.println("+---------------------------------------------------------------------+");
    }

    public boolean removeOrderListForTable(String tableNo) {
        boolean tableFound = false;
        boolean orderRemoved = false;

        // Reset the table's order list if the table number matches
        for (int i = 0; i < tables.getNumberOfEntries(); i++) {
            if (tables.get(i + 1).getTableNo().equalsIgnoreCase(tableNo)) {
                tables.get(i + 1).setOrderList(new SortedArrayList()); // Reset order list
                tableFound = true;
                break;
            }
        }

        if (!tableFound) {
            System.out.println("Table not found.");
            return false;
        }

        // Remove orders same with the table no
        for (int j = 0; j < orders.getNumberOfEntries(); j++) {
            if (orders.get(j + 1).getTable().getTableNo().equalsIgnoreCase(tableNo)) {
                orders.removeNo(j + 1);
                orderRemoved = true;
                j--; // Adjust index after removal
            }
        }

        if (!orderRemoved) {
            System.out.println("No orders associated with the specified table were found.");
        }

        return tableFound && orderRemoved;
    }

    //clear all the orders in the table list and order list
    public void clearAllList() {
        orders.clear();

        for (int i = 0; i < tables.getNumberOfEntries(); i++) {
            tables.get(i + 1).setOrderList(new SortedArrayList());
        }
    }

    public void displayOrderDetails(String OrderID) {
        Orders order = null;
        boolean orderFound = false;

        for (int i = 0; i < orders.getNumberOfEntries(); i++) {
            if (OrderID.equalsIgnoreCase(orders.get(i + 1).getOrderID())) {
                order = orders.get(i + 1);
                orderFound = true;
                break;
            }
        } //set the order with the orderid 

        //return when the order id not found
        if (!orderFound || order == null) {
            System.out.print("Order ID not Found !!! ");
            return;
        }

        System.out.println("\n\n+----------------------------------------------+");
        System.out.println("|                Order Details                 |");
        System.out.println("+----------------------------------------------+");
        System.out.printf("| %-15s : %-26s |\n", "Order ID", order.getOrderID());
        System.out.printf("| %-15s : %-26s |\n", "Table No", order.getTable().getTableNo());
        System.out.printf("| %-15s : %-26s |\n", "Capacity", order.getTable().getNoPax());
        System.out.printf("| %-15s : %-26s |\n", "Order Date", order.getOrderDate());
        System.out.printf("| %-15s : %-26s |\n", "Order Time", order.getOrderTime());
        System.out.printf("| %-15s : %-26s |\n", "Status", order.getStatus());
        System.out.println("+----------------------------------------------+");
        System.out.println("|                 Item List                    |");
        System.out.println("+----------------------------------------------+");
        System.out.printf("| %-10s | %-20s | %-8s |\n", "Item ID", "Item Details", "Quantity");
        System.out.println("+----------------------------------------------+");

        // Display each item in the order
        for (int i = 0; i < order.getItemList().getNumberOfEntries(); i++) {
            System.out.printf("| %-10s | %-20s | %-8d |\n",
                    order.getItemList().get(i + 1).getItem().getItemID(),
                    order.getItemList().get(i + 1).getItem().getItemDetails(),
                    order.getItemList().get(i + 1).getQuantity());
        }
        System.out.println("+----------------------------------------------+");
    }

    public boolean SearchItem(String ItemID) {
        for (int i = 0; i < items.getNumberOfEntries(); i++) {
            if (items.get(i + 1).getItemID().equalsIgnoreCase(ItemID)) {
                return true;
            }
        }

        return false;
    }

    public boolean SearchTable(String tableID) {

        for (int i = 0; i < tables.getNumberOfEntries(); i++) {
            if (tableID.equalsIgnoreCase(tables.get(i + 1).getTableNo())) {

                return true;
            }
        }

        return false;

    }

    public boolean SearchItem(String OrderID, String ItemID) {
        Orders order = null;
        for (int i = 0; i < orders.getNumberOfEntries(); i++) {
            if (OrderID.equalsIgnoreCase(orders.get(i + 1).getOrderID())) {
                order = orders.get(i + 1);
                break;
            }
        }
        if (order == null) {
            return false;
        }
        for (int i = 0; i < order.getItemList().getNumberOfEntries(); i++) {
            if (order.getItemList().get(i + 1).getItem().getItemID().equalsIgnoreCase(ItemID)) {
                return true;
            }
        }

        return false;
    }

    public boolean SearchOrder(String OrderID) {

        for (int i = 0; i < orders.getNumberOfEntries(); i++) {
            if (OrderID.equalsIgnoreCase(orders.get(i + 1).getOrderID())) {
                return true;
            }
        }

        return false;
    }

    public boolean addOrder(String tableID, String[][] itemsWithQuantities) {
        // Date and time setup
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String currentDate = now.format(dateFormatter);
        String currentTime = now.format(timeFormatter);

        int tableCount = 0;
        boolean tableFound = false;

        // Locate the table
        for (int i = 0; i < tables.getNumberOfEntries(); i++) {
            if (tableID.equalsIgnoreCase(tables.get(i + 1).getTableNo())) {
                tableCount = i + 1;
                tableFound = true;
                break;
            }
        }

        if (!tableFound) {
            System.out.println("\n\nError - Table not found!");
            return false;
        }

        // Create the list to store OrderItem (item and quantity)
        ListInterface<OrderItem> orderItems = new SortedArrayList<>();

        for (String[] itemWithQuantity : itemsWithQuantities) {
            if (itemWithQuantity == null || itemWithQuantity[0] == null) {
                continue; // Skip null entries
            }

            String itemID = itemWithQuantity[0];
            int quantity;

            try {
                quantity = Integer.parseInt(itemWithQuantity[1]);
            } catch (NumberFormatException e) {
                System.out.println("\n\nError - Invalid quantity for item " + itemID);
                continue; // Skip invalid quantities
            }

            if (quantity <= 0) {
                System.out.println("\n\nError - Quantity must be greater than zero for item " + itemID);
                continue;
            }

            // Find the item by ID
            boolean itemFound = false;
            for (int j = 0; j < items.getNumberOfEntries(); j++) {
                Items item = items.get(j + 1);
                if (item.getItemID().equalsIgnoreCase(itemID)) {
                    orderItems.add(new OrderItem(item, quantity));
                    itemFound = true;
                    break;
                }
            }

            if (!itemFound) {
                System.out.println("\n\nError - Item " + itemID + " not found in the menu.");
            }
        }

        if (orderItems.getNumberOfEntries() == 0) {
            System.out.println("\n\nError - No valid items were added to the order!");
            return false;
        }

        // Create the new order
        Orders newOrder = new Orders(tables.get(tableCount), orderItems, currentDate, currentTime);

        // Add the order to the orders list and the corresponding table
        orders.add(newOrder);
        tables.get(tableCount).getOrderList().add(newOrder);

        System.out.println("\n\nOrder " + newOrder.getOrderID() + " has been added successfully!");
        return true;
    }

    public boolean updateOrder(String OrderID, String ItemID, int Quantity) {
        Orders order = null;
        Items item = null;
        OrderItem oldOrderItem = null;

        for (int i = 0; i < orders.getNumberOfEntries(); i++) {
            if (OrderID.equalsIgnoreCase(orders.get(i + 1).getOrderID())) {
                order = orders.get(i + 1);

                break;
            }
        }

        if (order == null) {
            return false;
        }

        if (order.getStatus().equalsIgnoreCase("Complete")) {
            System.out.println("Order is in Completed State. It cannot be modified");
            return false;
        }

        for (int i = 0; i < order.getItemList().getNumberOfEntries(); i++) {
            if (order.getItemList().get(i + 1).getItem().getItemID().equalsIgnoreCase(ItemID)) {
                item = order.getItemList().get(i + 1).getItem();
                oldOrderItem = order.getItemList().get(i + 1);

                break;
            }
        }

        if (item == null || oldOrderItem == null) {
            return false;
        }
        if (Quantity > 0) {
            OrderItem newOrderItem = new OrderItem(item, Quantity);

            if (order.getItemList().replace(oldOrderItem, newOrderItem)) {
                return true;
            }
        } else if (order.getItemList().remove(oldOrderItem)) {
            if (order.getItemList().getNumberOfEntries() == 0) {
                System.out.println("No Item List in this Order. The Order will be Removed !!");
                boolean check = removeOrder(order.getOrderID());
                if (check) {
                    return true;
                } else {
                    return false;
                }

            }

            return true;
        }
        return false;
    }

    public boolean updateStatus(String OrderID) {
        for (int i = 0; i < orders.getNumberOfEntries(); i++) {
            if (OrderID.equalsIgnoreCase(orders.get(i + 1).getOrderID())) {
                if (orders.get(i + 1).getStatus().equalsIgnoreCase("Pending")) {
                    orders.get(i + 1).setStatus("Complete");
                    System.out.println("The Order is Update Successfully.");
                    return true;
                } else {
                    System.out.println("Order is in Completed State. It cannot be modified");
                    return false;
                }

            }
        }
        System.out.println("Order ID: " + OrderID + " is Not Found");
        return false;
    }

    public boolean removeOrder(String OrderID) {
        boolean tableRemoved = false;
        boolean orderRemoved = false;
        Table table = null;

        // Remove ordersID
        for (int j = 0; j < orders.getNumberOfEntries(); j++) {
            if (orders.get(j + 1).getOrderID().equalsIgnoreCase(OrderID)) {
                table = orders.get(j + 1).getTable();
                orders.removeNo(j + 1);
                orderRemoved = true;
                break;
            }
        }

        if (table == null) {
            return false;
        }

        for (int i = 0; i < table.getOrderList().getNumberOfEntries(); i++) {
            if (table.getOrderList().get(i + 1).getOrderID().equalsIgnoreCase(OrderID)) {
                table.getOrderList().removeNo(i + 1);
                tableRemoved = true;
                break;
            }
        }

        if (!orderRemoved || !tableRemoved) {
            System.out.println("Orders ID Not Found");
        }

        return orderRemoved && tableRemoved;
    }
}
