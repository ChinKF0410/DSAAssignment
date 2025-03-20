/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import ADT.ListInterface;
import ADT.SortedArrayList;
import entity.Table;
import entity.OrderItem;

/**
 *
 * @author kahfui
 */
public class Orders implements Comparable<Orders> {

    private String OrderID;
    private Table table;
    private ListInterface<OrderItem> ItemList = new SortedArrayList<>();
    private String OrderDate;
    private String OrderTime;
    private String Status;
    private static int NextID = 1000;

    //constructor
    public Orders(Table table, ListInterface<OrderItem> ItemList, String OrderDate, String OrderTime) {
        this.table = table;
        this.ItemList = ItemList;
        this.OrderDate = OrderDate;
        this.OrderTime = OrderTime;
        this.Status = "Pending";
        this.OrderID = "O" + NextID;
        NextID++;
    }

    public Orders(Table table, ListInterface<OrderItem> ItemList, String OrderDate, String OrderTime, String status) {
        this.table = table;
        this.ItemList = ItemList;
        this.OrderDate = OrderDate;
        this.OrderTime = OrderTime;
        this.Status = status;
        this.OrderID = "O" + NextID;
        NextID++;
    }

    //getter and setter
    public String getOrderID() {
        return OrderID;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public ListInterface<OrderItem> getItemList() {
        return ItemList;
    }

    public void setItemList(ListInterface<OrderItem> ItemList) {
        this.ItemList = ItemList;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String OrderDate) {
        this.OrderDate = OrderDate;
    }

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String OrderTime) {
        this.OrderTime = OrderTime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    //toString
    @Override
    public String toString() {
        return "Orders{" + "OrderID=" + OrderID + ", table=" + table.getTableNo() + ", ItemList=" + ItemList + ", OrderDate=" + OrderDate + ", OrderTime=" + OrderTime + ", Status=" + Status + '}';
    }

    @Override
    public int compareTo(Orders other) {
        int status = 0;
        if (this.Status.equalsIgnoreCase("Pending") && other.Status.equalsIgnoreCase("Complete")) {
            status = -1; // Pending comes before Complete
        } else if (this.Status.equalsIgnoreCase("Complete") && other.Status.equalsIgnoreCase("Pending")) {
            status = 1; // Complete comes after Pending
        }
        // If status are same, compare order time
        if (status == 0) {
            return this.OrderTime.compareTo(other.OrderTime);
        }

        // else, use the status comparison result
        return status;
    }
}
