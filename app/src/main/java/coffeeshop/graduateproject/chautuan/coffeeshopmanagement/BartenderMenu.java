package coffeeshop.graduateproject.chautuan.coffeeshopmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiInterface;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.Bartender;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.StasticData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chautuan on 4/14/18.
 */

public class BartenderMenu extends AppCompatActivity {
    private List<StasticData> listOrder = new ArrayList<>();
    String api_key;
    List<StasticData> listTmp;
    public ApiInterface apiService;
    SharedPreferences infosave;
    @BindView(R.id.btnServingOrder)
    Button btnServingOrder;
    @BindView(R.id.btnStatical)
    Button btnStatical;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.bartender_menu);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.btnServingOrder)
    public void chooseServingOrder()
    {
        Intent intent = new Intent(BartenderMenu.this,PendingActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.btnStatical)
    public void chooseStatical()
    {
        //executeGetPending();
        Intent intent = new Intent(BartenderMenu.this,ChartActivity.class);
        startActivity(intent);
        finish();
    }

//    private void executeGetPending() {
//        Call<List<StasticData>> call = apiService.getStastic(api_key);
//        call.enqueue(new Callback<List<StasticData>>() {
//            @Override
//            public void onResponse(Call<List<StasticData>> call, Response<List<StasticData>> response) {
//                listTmp = response.body();
//                for (StasticData item : listTmp) {
//                    StasticData i = item;
//                    if(!listOrder.contains(i)){
//                        listOrder.add(i);
//                        //pendingListAdapter.notifyDataSetChanged();
//                    }
//
//                    Log.i("table: ", String.valueOf(item.getItemName()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<StasticData>> call, Throwable t) {
//                Log.e("get table error", t.getLocalizedMessage());
//            }
//        });
//        Log.i("item count activity: ", String.valueOf(listOrder.size()));
//        System.out.println("mememememmemme:" + String.valueOf(listOrder.size()));
//    }


}
