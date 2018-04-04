package coffeeshop.graduateproject.chautuan.coffeeshopmanagement;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiClient;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiInterface;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.adapter.MenuItemsAdapter;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.bus.MessageEvent;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.LatestOrder;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.ResponseInfomation;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.MenuItem;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.OrderDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chautuan on 3/7/18.
 */

public class OrderMenuActivity extends AppCompatActivity {
    private List<MenuItem> lstMenuItem = new ArrayList<>();
    private MenuItemsAdapter menuItemsAdapter;
    private String api_key;
    public ApiInterface apiService;
    public int latestOrder;
    private  List<OrderDetail> lstOrder = new ArrayList<>();
    private static final int FINISH_ACTIVITY_CODE = 1;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.btnPlaceOrder)
    Button btnPlaceOrder;

    @BindView(R.id.edtTotalPrice)
    EditText edtTotalPrice;

    //Button btnAddInAdapter;
    long totalPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_main);
        SharedPreferences infosave = getSharedPreferences("my_data", MODE_PRIVATE);
        SharedPreferences tablenumber = getSharedPreferences("tableNumber", MODE_PRIVATE);
        String number = tablenumber.getString("numbertable","1");
        int usingTable = Integer.valueOf(number);
        ButterKnife.bind(this);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        api_key = infosave.getString("api", "");

        Call<ResponseInfomation> callNewOrder = apiService.createOrder(api_key,2,4,usingTable,"non");
        callNewOrder.enqueue(new Callback<ResponseInfomation>() {
            @Override
            public void onResponse(Call<ResponseInfomation> call, Response<ResponseInfomation> response) {
                ResponseInfomation ls = response.body();
                Log.i("Create Order Response: ",ls.getMessage() + ls.getError());
            }

            @Override
            public void onFailure(Call<ResponseInfomation> call, Throwable t) {
                Log.e("Create Order Error:", t.getLocalizedMessage());

            }
        });
        Call<LatestOrder> callLates = apiService.getLatestOrderID(api_key);
        callLates.enqueue(new Callback<LatestOrder>() {
            @Override
            public void onResponse(Call<LatestOrder> call, Response<LatestOrder> response) {
                LatestOrder latest = response.body();
                latestOrder = latest.getOrderID();
//                            Toast.makeText(OrderMenuActivity.this, String.valueOf(latestOrder), Toast.LENGTH_SHORT).show();
                Log.i("LatestOrder Get: ",String.valueOf(latestOrder));
            }

            @Override
            public void onFailure(Call<LatestOrder> call, Throwable t) {
                Log.e("LatestOrder Error:", t.getLocalizedMessage());

            }
        });
        Call<List<MenuItem>> call = apiService.getMenuItems(api_key);
        call.enqueue(new Callback<List<MenuItem>>() {
            @Override
            public void onResponse(Call<List<MenuItem>> call, Response<List<MenuItem>> response) {
                List<MenuItem> tmp = response.body();
                menuItemsAdapter.notifyDataSetChanged();
                for (MenuItem i : tmp) {
                    MenuItem item = i;
                    lstMenuItem.add(item);

                }
            }

            @Override
            public void onFailure(Call<List<MenuItem>> call, Throwable t) {
                Log.e("Error", t.getLocalizedMessage());

            }
        });


        menuItemsAdapter = new MenuItemsAdapter(lstMenuItem);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(menuItemsAdapter);
        lstOrder = menuItemsAdapter.selectedList;
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (OrderDetail od : lstOrder) {
                    od.setOrderID(latestOrder);
                    Call<OrderDetail> callOrderDetail = apiService.createOrderDetail(api_key,od.getOrderID(),od.getItemID(),od.getItemPrice(),od.getQuantity());
                    callOrderDetail.enqueue(new Callback<OrderDetail>() {
                        @Override
                        public void onResponse(Call<OrderDetail> call, Response<OrderDetail> response) {


                            Toast.makeText(OrderMenuActivity.this, "Order Complete", Toast.LENGTH_SHORT).show();
                            Log.i("Response From Server:", response.body().toString());
                        }

                        @Override
                        public void onFailure(Call<OrderDetail> call, Throwable t) {
                            Log.e("Error Response: ", t.getLocalizedMessage());

                        }
                    });
                }
                finishActivity(FINISH_ACTIVITY_CODE);


            }
        });




    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event)
    {
        totalPrice = event.ad;
        edtTotalPrice.setText("Total Price: " + String.valueOf(menuItemsAdapter.totalPrice));

    }

    /**
     * Start and Stop EventBus
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

    }
}
