/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author kahfui
 */
public class Items implements Comparable<Items> {
    private String ItemID;
    private String ItemDetails;
    private double Price;
    private static int NextID =100;

    //constructor
    public Items(String ItemDetails, double Price) {
        this.ItemDetails = ItemDetails;
        this.Price = Price;
        this.ItemID="I"+ NextID;
        NextID ++;
    }
    
    //getter and setter

    public String getItemID() {
        return ItemID;
    }

    public String getItemDetails() {
        return ItemDetails;
    }

    public void setItemDetails(String ItemDetails) {
        this.ItemDetails = ItemDetails;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    @Override
    public String toString() {
        return "Items{" + "ItemID=" + ItemID + ", ItemDetails=" + ItemDetails + ", Price=" + Price + '}';
    }
    
    
    
    @Override
    public int compareTo(Items other) {
        return this.ItemID.compareTo(other.ItemID);
    }
    
}
