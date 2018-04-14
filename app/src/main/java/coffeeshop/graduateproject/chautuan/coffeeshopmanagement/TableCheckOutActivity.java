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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiClient;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiInterface;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.adapter.ServingTableAdapter;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.adapter.TableItemAdapter;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.Table;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chautuan on 4/7/18.
 */

public class TableCheckOutActivity extends AppCompatActivity {
    private List<Table> listTable = new ArrayList<>();
    private ServingTableAdapter tableItemAdapter;
    public ApiInterface apiService;
    private String api_key;
    @BindView(R.id.recyclerViewTable)
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_main);
        SharedPreferences infosave = getSharedPreferences("my_data", MODE_PRIVATE);
        api_key = infosave.getString("api", "");


        ButterKnife.bind(this);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Table>> call = apiService.getServingTable(api_key);
        call.enqueue(new Callback<List<Table>>() {
            @Override
            public void onResponse(Call<List<Table>> call, Response<List<Table>> response) {
                List<Table> listTmp = response.body();
                tableItemAdapter.notifyDataSetChanged();
                for (Table item: listTmp) {
                    Table i = item;
                    listTable.add(i);
                    Log.i("table: ", String.valueOf(item.getTableID()));
                }
            }
            @Override
            public void onFailure(Call<List<Table>> call, Throwable t) {
                Log.e("get table error",t.getLocalizedMessage());
            }
        });

        Log.i("item count activity: ",String.valueOf(listTable.size()));
        tableItemAdapter = new ServingTableAdapter(listTable);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(tableItemAdapter);
    }
}
