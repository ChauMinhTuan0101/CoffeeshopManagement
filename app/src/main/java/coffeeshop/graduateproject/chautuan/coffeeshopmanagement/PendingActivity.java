package coffeeshop.graduateproject.chautuan.coffeeshopmanagement;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiClient;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiInterface;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.adapter.PendingListAdapter;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.adapter.TableItemAdapter;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.Order;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.Table;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chautuan on 4/14/18.
 */

public class PendingActivity extends AppCompatActivity {
    private List<Order> listOrder = new ArrayList<>();
    private PendingListAdapter pendingListAdapter;
    String api_key;
    public ApiInterface apiService;
    Handler handler;
    Runnable runnable;
    SharedPreferences infosave;
    @BindView(R.id.recyclerViewPending)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pending_main);
        infosave = getSharedPreferences("my_data", MODE_PRIVATE);
        api_key = infosave.getString("api", "");
        ButterKnife.bind(this);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        pendingListAdapter = new PendingListAdapter(listOrder);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                executeGetPending();
                pendingListAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(pendingListAdapter);
                handler.postDelayed(runnable, 5000);
            }
        };
        runnable.run();
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(pendingListAdapter);

    }

    private void executeGetPending() {
        Call<List<Order>> call = apiService.getServingOrder(api_key);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                List<Order> listTmp = response.body();
                listOrder.clear();
                pendingListAdapter.notifyDataSetChanged();
                for (Order item : listTmp) {
                    Order i = item;
                    if(!listOrder.contains(i)){
                    listOrder.add(i);
                    }

                    Log.i("table: ", String.valueOf(item.getOrderID()));
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.e("get table error", t.getLocalizedMessage());
            }
        });

        Log.i("item count activity: ", String.valueOf(listOrder.size()));
        System.out.println("mememememmemme:" + String.valueOf(listOrder.size()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}


