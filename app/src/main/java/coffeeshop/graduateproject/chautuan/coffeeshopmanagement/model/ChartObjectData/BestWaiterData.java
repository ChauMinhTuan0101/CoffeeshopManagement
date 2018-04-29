package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.ChartObjectData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BestWaiterData {
    @SerializedName("IDPhucVu")
    @Expose
    private Integer iDPhucVu;
    @SerializedName("count")
    @Expose
    private Integer count;

    public Integer getIDPhucVu() {
        return iDPhucVu;
    }

    public void setIDPhucVu(Integer iDPhucVu) {
        this.iDPhucVu = iDPhucVu;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
