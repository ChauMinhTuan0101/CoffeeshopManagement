package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StasticData {

    @SerializedName("ItemName")
    @Expose
    private String itemName;
    @SerializedName("Total")
    @Expose
    private Integer total;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}

