package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by chautuan on 2/5/18.
 */

public class MenuItem extends BaseObservable implements Serializable {

    @SerializedName("ItemID")
    @Expose
    private Integer itemID;
    @SerializedName("ItemName")
    @Expose
    private String itemName;
    @SerializedName("ItemType")
    @Expose
    private Integer itemType;
    @SerializedName("ItemPrice")
    @Expose
    private Integer itemPrice;
    @SerializedName("ItemDescription")
    @Expose
    private String itemDescription;

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    @Bindable
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    @Bindable
    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public MenuItem(Integer itemID, String itemName, Integer itemType, Integer itemPrice, String itemDescription) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
    }
}
