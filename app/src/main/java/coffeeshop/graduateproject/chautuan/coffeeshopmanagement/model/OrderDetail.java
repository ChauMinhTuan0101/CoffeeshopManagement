package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by chautuan on 3/17/18.
 */

public class OrderDetail {
    @SerializedName("OrderDetailID")
    @Expose
    private Integer orderDetailID;
    @SerializedName("OrderID")
    @Expose
    private Integer orderID;
    @SerializedName("ItemID")
    @Expose
    private Integer itemID;
    @SerializedName("ItemName")
    @Expose
    private String itemName;
    @SerializedName("ItemPrice")
    @Expose
    private Integer itemPrice;
    @SerializedName("Quantity")
    @Expose
    private Integer quantity;

    public Integer getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(Integer orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
