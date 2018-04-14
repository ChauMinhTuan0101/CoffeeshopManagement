package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by chautuan on 3/30/18.
 */

public class Table {

    @SerializedName("tableID")
    @Expose
    private Integer tableID;
    @SerializedName("tableStatus")
    @Expose
    private Integer tableStatus;
    @SerializedName("currentOrder")
    @Expose
    private Integer currentOrder;

    public Integer getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Integer currentOrder) {
        this.currentOrder = currentOrder;
    }

    public Integer getTableID() {
        return tableID;
    }

    public void setTableID(Integer tableID) {
        this.tableID = tableID;
    }

    public Integer getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(Integer tableStatus) {
        this.tableStatus = tableStatus;
    }

}
