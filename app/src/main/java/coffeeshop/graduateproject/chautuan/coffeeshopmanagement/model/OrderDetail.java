package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by chautuan on 3/17/18.
 */

public class OrderDetail {
    @SerializedName("OrderID")
    @Expose
    private Integer orderID;
    @SerializedName("ItemID")
    @Expose
    private Integer itemID;
    @SerializedName("ItemPrice")
    @Expose
    private Integer itemPrice;
    @SerializedName("Quantity")
    @Expose
    private Integer quantity;

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
}
