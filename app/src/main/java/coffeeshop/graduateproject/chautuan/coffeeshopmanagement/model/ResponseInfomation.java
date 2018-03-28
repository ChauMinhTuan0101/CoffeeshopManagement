package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by chautuan on 3/17/18.
 */

public class ResponseInfomation {
    //    @SerializedName("OrderID")
//    @Expose
//    private Integer orderID;
//    @SerializedName("DateCreate")
//    @Expose
//    private String dateCreate;
//    @SerializedName("IDBartender")
//    @Expose
//    private Integer iDBartender;
//    @SerializedName("IDPhucVu")
//    @Expose
//    private Integer iDPhucVu;
//    @SerializedName("TableNumber")
//    @Expose
//    private Integer tableNumber;
//    @SerializedName("NoticeInfo")
//    @Expose
//    private String noticeInfo;
//
////    public Integer getOrderID() {
////        return orderID;
////    }
////
////    public void setOrderID(Integer orderID) {
////        this.orderID = orderID;
////    }
////
////    public String getDateCreate() {
////        return dateCreate;
////    }
////
////    public void setDateCreate(String dateCreate) {
////        this.dateCreate = dateCreate;
////    }
//
//    public Integer getIDBartender() {
//        return iDBartender;
//    }
//
//    public void setIDBartender(Integer iDBartender) {
//        this.iDBartender = iDBartender;
//    }
//
//    public Integer getIDPhucVu() {
//        return iDPhucVu;
//    }
//
//    public void setIDPhucVu(Integer iDPhucVu) {
//        this.iDPhucVu = iDPhucVu;
//    }
//
//    public Integer getTableNumber() {
//        return tableNumber;
//    }
//
//    public void setTableNumber(Integer tableNumber) {
//        this.tableNumber = tableNumber;
//    }
//
//    public String getNoticeInfo() {
//        return noticeInfo;
//    }
//
//    public void setNoticeInfo(String noticeInfo) {
//        this.noticeInfo = noticeInfo;
//    }
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
