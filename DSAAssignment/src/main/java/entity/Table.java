/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import entity.Orders;
import ADT.ListInterface;
import ADT.SortedArrayList;

/**
 *
 * @author kahfui
 */
public class Table implements Comparable<Table> {
    private String TableNo;
    private int NoPax;
    private ListInterface<Orders> OrderList = new SortedArrayList<>();
    private static int nextID=100;

    //constructor
    public Table(int NoPax) {
        this.TableNo = "T"+nextID;
        this.NoPax = NoPax;
        nextID ++;
    }

    //getter and setter
    public int getNoPax() {
        return NoPax;
    }

    public void setNoPax(int NoPax) {
        this.NoPax = NoPax;
    }
    

    public String getTableNo() {
        return TableNo;
    }

    public ListInterface<Orders> getOrderList() {
        return OrderList;
    }

    public void setOrderList(ListInterface<Orders> OrderList) {
        this.OrderList = OrderList;
    }
  
    
    @Override
    public int compareTo(Table other) {
        return this.TableNo.compareTo(other.TableNo);
    }

    @Override
    public String toString() {
        return "Table{" + "TableNo=" + TableNo + ", NoPax=" + NoPax + ", OrderList=" + OrderList + '}';
    }
    
}
