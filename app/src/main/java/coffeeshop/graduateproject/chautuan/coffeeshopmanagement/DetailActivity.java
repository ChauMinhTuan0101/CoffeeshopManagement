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
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiClient;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiInterface;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.adapter.DetailAdapter;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.OrderDetail;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.ResponseInfomation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chautuan on 4/15/18.
 */

public class DetailActivity extends AppCompatActivity {
    private List<OrderDetail> listOrderDetail = new ArrayList<>();
    private DetailAdapter detailAdapter;
    public ApiInterface apiService;
    private String api_key;
    private int orderID;

    @BindView(R.id.recyclerDetail)
    RecyclerView mRecyclerView;
    @BindView(R.id.btnDetailComplete)
    Button btnDetailComplete;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_detail_main);
        SharedPreferences infosave = getSharedPreferences("my_data", MODE_PRIVATE);
        api_key = infosave.getString("api", "");
        apiService = ApiClient.getClient().create(ApiInterface.class);
        ButterKnife.bind(this);
        orderID = getIntent().getIntExtra("orderid",0);
        System.out.print(orderID);
        Call<List<OrderDetail>> getListOrderDetail = apiService.getOrderDetailByID(api_key,orderID);
        getListOrderDetail.enqueue(new Callback<List<OrderDetail>>() {
            @Override
            public void onResponse(Call<List<OrderDetail>> call, Response<List<OrderDetail>> response) {
                List<OrderDetail> temp = response.body();
                detailAdapter.notifyDataSetChanged();
                for(OrderDetail item: temp)
                {
                    OrderDetail i = item;
                    listOrderDetail.add(i);
                    System.out.println(i.getItemName());
                }
            }

            @Override
            public void onFailure(Call<List<OrderDetail>> call, Throwable t) {

            }
        });

        System.out.println(listOrderDetail.size());

        detailAdapter = new DetailAdapter(listOrderDetail);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(detailAdapter);
    }
    @OnClick(R.id.btnDetailComplete)
    public void CompleteMakingOrder()
    {
        Call<ResponseInfomation> completeProcessing = apiService.updateProcess(api_key,0,orderID);
        completeProcessing.enqueue(new Callback<ResponseInfomation>() {
            @Override
            public void onResponse(Call<ResponseInfomation> call, Response<ResponseInfomation> response) {
                ResponseInfomation rp = response.body();
                Toast.makeText(DetailActivity.this, "Processing Update Complete", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseInfomation> call, Throwable t) {

            }
        });
    }

}
