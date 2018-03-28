package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by chautuan on 2/26/18.
 */

public class    PhucVu {

    @SerializedName("UserID") private int userID;
    @SerializedName("DateJoin") private Date dateJoin;
    @SerializedName("UserLevel") private int userLeve;
    @SerializedName("PhucVuName") private String phucVuName;

    public PhucVu(int userID, Date dateJoin, int userLeve, String name) {
        this.userID = userID;
        this.dateJoin = dateJoin;
        this.userLeve = userLeve;
        this.phucVuName = name;
    }

    public PhucVu() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getDateJoin() {
        return dateJoin;
    }

    public void setDateJoin(Date dateJoin) {
        this.dateJoin = dateJoin;
    }

    public int getUserLeve() {
        return userLeve;
    }

    public void setUserLeve(int userLeve) {
        this.userLeve = userLeve;
    }

    public String getName() {
        return phucVuName;
    }

    public void setName(String name) {
        this.phucVuName = name;
    }
}


