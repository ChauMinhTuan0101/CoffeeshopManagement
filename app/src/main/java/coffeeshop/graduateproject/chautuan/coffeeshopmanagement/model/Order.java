package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chautuan on 3/1/18.
 */

public class Order {

    @SerializedName("OrderID") int orderID;
    @SerializedName("ItemID") int itemID;
    @SerializedName("ItemPrice") long itemPrice;
    @SerializedName("Quantity") int quantity;

    public Order(int orderID, int itemID, long itemPrice, int quantity) {
        this.orderID = orderID;
        this.itemID = itemID;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    public Order() {
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
