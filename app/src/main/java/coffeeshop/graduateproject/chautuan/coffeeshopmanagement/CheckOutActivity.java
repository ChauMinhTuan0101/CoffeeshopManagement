package coffeeshop.graduateproject.chautuan.coffeeshopmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
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
import butterknife.OnClick;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiClient;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiInterface;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.adapter.ListOrderedAdapter;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.bus.MessageEvent;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.MenuItem;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.OrderDetail;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.ResponseInfomation;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.Table;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chautuan on 4/7/18.
 */

public class CheckOutActivity extends AppCompatActivity {
    private List<OrderDetail> listOrderDetail = new ArrayList<>();
    private String api_key;
    private ApiInterface apiService;
    private int servingTableNumber;
    private int currentOrder;
    private long checkOutPrice;
    private int orderid;
    private ListOrderedAdapter listOrderedAdapter;
    @BindView(R.id.recyclerViewOrdered)
    RecyclerView mRecyclerView;

    @BindView(R.id.btnCheckOut)
    Button btnCheckOut;

    @BindView(R.id.edtTotalPriceCheckout)
    EditText edtTotalPriceCheckout;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        setContentView(R.layout.list_ordered_main);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        SharedPreferences servingTablePreference = getSharedPreferences("servingtablenumber", MODE_PRIVATE);
        SharedPreferences orderoftable = getSharedPreferences("orderoftable",MODE_PRIVATE);

        servingTableNumber = servingTablePreference.getInt("servingtablenumber",0);
        currentOrder = orderoftable.getInt(String.valueOf(servingTableNumber),1);
        Log.e("table num", "service tbale "+ String.valueOf(servingTableNumber));
        Log.e("table num", "current order  "+ String.valueOf(currentOrder));
        SharedPreferences infosave = getSharedPreferences("my_data", MODE_PRIVATE);

        api_key = infosave.getString("api", "");
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<OrderDetail>> call = apiService.getOrderDetailByID(api_key,currentOrder);
        call.enqueue(new Callback<List<OrderDetail>>() {
            @Override
            public void onResponse(Call<List<OrderDetail>> call, Response<List<OrderDetail>> response) {
                    List<OrderDetail> tmp = response.body();
                    listOrderedAdapter.notifyDataSetChanged();
                    for(OrderDetail i : tmp)
                    {
                        OrderDetail od = i;
                        orderid= od.getOrderID();
                        listOrderDetail.add(od);
                        System.out.println(od.getItemName());
                    }
            }
            @Override
            public void onFailure(Call<List<OrderDetail>> call, Throwable t) {
                Log.e("Error CheckOut: ",t.getLocalizedMessage());

            }
        });
        System.out.println("listorder size: " + listOrderDetail.size());

        listOrderedAdapter = new ListOrderedAdapter(listOrderDetail);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(listOrderedAdapter);



    }
    @OnClick(R.id.btnCheckOut)
    public void CheckOut()
    {
        Call<ResponseInfomation> callChangeStatus = apiService.changeTableStatus(api_key,0,servingTableNumber,0);
        callChangeStatus.enqueue(new Callback<ResponseInfomation>() {
            @Override
            public void onResponse(Call<ResponseInfomation> call, Response<ResponseInfomation> response) {

                Call<ResponseInfomation> checkout = apiService.updateServing(api_key,0,orderid);
                checkout.enqueue(new Callback<ResponseInfomation>() {
                    @Override
                    public void onResponse(Call<ResponseInfomation> call, Response<ResponseInfomation> response) {
                        Toast.makeText(CheckOutActivity.this, "Checkout Complete ", Toast.LENGTH_SHORT).show();
                    }



                    @Override
                    public void onFailure(Call<ResponseInfomation> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<ResponseInfomation> call, Throwable t) {
                Log.i("Error Response:", t.getLocalizedMessage());
            }
        });
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event)
    {
        checkOutPrice = event.ad;
        edtTotalPriceCheckout.setText("Total Price: " + String.valueOf(listOrderedAdapter.checkOutPrice));

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
