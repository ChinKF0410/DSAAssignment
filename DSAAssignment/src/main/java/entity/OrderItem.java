/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import entity.Items;

/**
 *
 * @author kahfui
 */
public class OrderItem  implements Comparable<OrderItem> {

    private Items item;
    private int quantity;

    public OrderItem(Items item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
 
    @Override
    public int compareTo(OrderItem other) {
        return this.item.compareTo(other.item);
    }
}
