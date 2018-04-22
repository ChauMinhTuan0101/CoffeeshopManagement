package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API;

import java.util.List;

import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.Bartender;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.LatestOrder;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.Order;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.ResponseInfomation;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.LoginUser;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.MenuItem;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.OrderDetail;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.StasticData;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.Table;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by chautuan on 3/1/18.
 */

public interface ApiInterface {

    @GET("getstatisbyitem")
    Call<List<StasticData>> getStastic(@Header("Authorization") String authToken);

    @FormUrlEncoded
    @POST("updateprocessing")
    Call<ResponseInfomation> updateProcess(@Header("Authorization") String authToken,
                                           @Field("processing") int processing,
                                           @Field("orderid")int orderid);

    @FormUrlEncoded
    @POST("updateserving")
    Call<ResponseInfomation> updateServing(@Header("Authorization")String authToken,
                                           @Field("serving") int serving,
                                           @Field("orderid") int orderid);

    @FormUrlEncoded
    @POST("delete")
    Call<ResponseInfomation> deleteOrderDetail(@Header("Authorization") String authToken,
                                               @Field("orderdetailid") int orderDetailID);

    @FormUrlEncoded
    @POST("changestatus")
    Call<ResponseInfomation> changeTableStatus(@Header("Authorization") String authToken,
                                               @Field("status") int status,
                                               @Field("id") int id,
                                               @Field("currentorder") int currentOrder);

    @GET("getservingtable")
    Call<List<Table>> getServingTable(@Header("Authorization")String authToken);

    @GET("gettable")
    Call<List<Table>> getTable();

    @FormUrlEncoded
    @POST("addmenuitem")
    Call<ResponseInfomation> addMenuItem(@Header("Authorization") String authToken,
                                         @Field("itemname") String itemName,
                                         @Field("itemtype") int itemType,
                                         @Field("itemprice") int itemPrice,
                                         @Field("itemdesc")String desc );

    @FormUrlEncoded
    @POST("createorder")
    Call<ResponseInfomation> createOrder(@Header("Authorization") String authToken,
                                         @Field("idphucvu") int idPhucVu,
                                         @Field("idbartender") int idBartender,
                                         @Field("tablenumber") int tableNumber,
                                         @Field("noticeinfo")String info,
                                         @Field("serving") int serving,
                                         @Field("processing")int processing);

    @FormUrlEncoded
    @POST("addorderdetail")
    Call<OrderDetail> createOrderDetail(@Header("Authorization") String authToken,
                                        @Field("orderid") int orderID,
                                        @Field("itemid") int itemid,
                                        @Field("itemname") String itemName,
                                        @Field("itemprice") int itemprice,
                                        @Field("quantity") int quantity);
    @GET("getservingorder")
    Call<List<Order>> getServingOrder(@Header("Authorization") String authToken);

    @GET("getallorderdetail")
    Call<OrderDetail> getAllOrderDetail(@Header("Authorization") String authToken);

    @FormUrlEncoded
    @POST("getorderdetailbyid")
    Call<List<OrderDetail>> getOrderDetailByID(@Header("Authorization") String authToken, @Field("orderid") int orderID);

    @GET("getlistorder")
    Call<ResponseInfomation> getListOrder(@Header("Authorization") String authToken);

    @GET("getmenuitems")
    Call<List<MenuItem>> getMenuItems(@Header("Authorization") String authToken);

    @GET("getlatestorderid")
    Call<LatestOrder> getLatestOrderID(@Header("Authorization") String authToken);

    @FormUrlEncoded
    @POST("login")
    Call<LoginUser> login(@Field("UserName") String UserName, @Field("password") String password);

    @GET("bartenders")
    Call<List<Bartender>> getBartenders ();
}
