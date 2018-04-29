package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.ChartObjectData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuarterData {
    @SerializedName("Quarter")
    @Expose
    private Integer quarter;
    @SerializedName("ItemName")
    @Expose
    private String itemName;
    @SerializedName("ItemQuantity")
    @Expose
    private String itemQuantity;

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
