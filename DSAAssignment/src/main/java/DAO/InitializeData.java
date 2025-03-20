/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ADT.*;

import entity.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
//@author kahfui
 */
public class InitializeData {

    private static String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    //future add
    private static ListInterface<Table> TableList = new SortedArrayList<>();
    private static ListInterface<Items> ItemList = new SortedArrayList<>();
    private static ListInterface<Orders> OrderList = new SortedArrayList<>();

    //add table
    private static Table[] tables = {
        new Table(10),
        new Table(10),
        new Table(10),
        new Table(10),
        new Table(10),
        new Table(6),
        new Table(6),
        new Table(6),
        new Table(2),
        new Table(2),};
    //add f&b items
    private static Items[] items = {
        new Items("Fried Rice", 12.00),
        new Items("Chicken Chop", 15.50),
        new Items("Spaghetti Bolognese", 14.00),
        new Items("Grilled Salmon", 25.00),
        new Items("Caesar Salad", 10.50),
        new Items("Cappuccino", 8.00),
        new Items("Orange Juice", 6.50),
        new Items("Iced Lemon Tea", 5.50),
        new Items("Chocolate Milkshake", 9.00),
        new Items("Mineral Water", 2.50)
    };

    static {
        initializeData();
    }

    private static void initializeData() {

// Add order items to itemList1
        OrderItem orderItem1 = new OrderItem(items[1], 2);
        OrderItem orderItem2 = new OrderItem(items[2], 2);
        ListInterface<OrderItem> itemList1 = new SortedArrayList<>();
        itemList1.add(orderItem1);
        itemList1.add(orderItem2);

// Add order items to itemList2
        OrderItem orderItem3 = new OrderItem(items[3], 1); // Quantity is 1 (default)
        OrderItem orderItem4 = new OrderItem(items[4], 1);
        ListInterface<OrderItem> itemList2 = new SortedArrayList<>();
        itemList2.add(orderItem3);
        itemList2.add(orderItem4);

// Add order items to itemList3
        OrderItem orderItem5 = new OrderItem(items[5], 1);
        OrderItem orderItem6 = new OrderItem(items[6], 1);
        ListInterface<OrderItem> itemList3 = new SortedArrayList<>();
        itemList3.add(orderItem5);
        itemList3.add(orderItem6);

// Add order items to itemList4
        OrderItem orderItem7 = new OrderItem(items[7], 1);
        OrderItem orderItem8 = new OrderItem(items[8], 1);
        ListInterface<OrderItem> itemList4 = new SortedArrayList<>();
        itemList4.add(orderItem7);
        itemList4.add(orderItem8);

// Add order items to itemList5
        OrderItem orderItem9 = new OrderItem(items[9], 1);
        OrderItem orderItem10 = new OrderItem(items[0], 1);
        ListInterface<OrderItem> itemList5 = new SortedArrayList<>();
        itemList5.add(orderItem9);
        itemList5.add(orderItem10);

        // Adding "Pending" orders with the current date
        OrderList.add(new Orders(tables[0], itemList1, currentDate, "12:30"));
        OrderList.add(new Orders(tables[0], itemList2, currentDate, "13:00"));
        OrderList.add(new Orders(tables[1], itemList3, currentDate, "13:30"));
        OrderList.add(new Orders(tables[1], itemList4, currentDate, "14:00"));
        OrderList.add(new Orders(tables[1], itemList5, currentDate, "14:30"));

        // Adding "Complete" orders with the current date
        OrderList.add(new Orders(tables[2], itemList1, currentDate, "12:00", "Complete"));
        OrderList.add(new Orders(tables[2], itemList2, currentDate, "12:30", "Complete"));
        OrderList.add(new Orders(tables[4], itemList3, currentDate, "13:00", "Complete"));
        OrderList.add(new Orders(tables[5], itemList4, currentDate, "13:30", "Complete"));
        OrderList.add(new Orders(tables[6], itemList5, currentDate, "14:00", "Complete"));

        for (int i = 0; i < OrderList.getNumberOfEntries(); i++) {
            for (Table table : tables) {
                if (OrderList.get(i + 1).getTable().getTableNo().equalsIgnoreCase(table.getTableNo())) {
                    table.getOrderList().add(OrderList.get(i + 1));
                }
            }
        }

        for (Table table : tables) {
            TableList.add(table);
        }

        for (Items item : items) {
            ItemList.add(item);
        }
    }

    public static ListInterface<Table> getTable() {
        return TableList;
    }

    public static ListInterface<Items> getItems() {
        return ItemList;
    }

    public static ListInterface<Orders> getOrders() {
        return OrderList;
    }
}
