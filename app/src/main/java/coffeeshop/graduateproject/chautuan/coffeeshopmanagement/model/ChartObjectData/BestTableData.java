package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.ChartObjectData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BestTableData {

    @SerializedName("TableNumber")
    @Expose
    private Integer tableNumber;
    @SerializedName("count")
    @Expose
    private Integer count;

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
