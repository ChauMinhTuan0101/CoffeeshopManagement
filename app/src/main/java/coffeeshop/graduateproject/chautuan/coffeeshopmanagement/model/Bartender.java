package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model;

/**
 * Created by chautuan on 2/26/18.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by chautuan on 2/26/18.
 */

public class Bartender {
    @SerializedName("UserID")
    @Expose
    private Integer userID;
    @SerializedName("DateJoin")
    @Expose
    private String dateJoin;
    @SerializedName("UserLevel")
    @Expose
    private Integer userLevel;
    @SerializedName("BartenderName")
    @Expose
    private String bartenderName;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getDateJoin() {
        return dateJoin;
    }

    public void setDateJoin(String dateJoin) {
        this.dateJoin = dateJoin;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getBartenderName() {
        return bartenderName;
    }

    public void setBartenderName(String bartenderName) {
        this.bartenderName = bartenderName;
    }

}


