package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.ActivityStastic;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiClient;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiInterface;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.R;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.ChartObjectData.BestWaiterData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BestWaiterActivity extends AppCompatActivity {

    private List<BestWaiterData> listWaiter = new ArrayList<>();
    String api_key;
    public ApiInterface apiService;
    SharedPreferences infosave;
    PieChart pieChart;
    int i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie_chart_layout);

        infosave = getSharedPreferences("my_data", MODE_PRIVATE);
        api_key = infosave.getString("api", "");
        apiService = ApiClient.getClient().create(ApiInterface.class);
        pieChart = findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);



        Call<List<BestWaiterData>> call = apiService.getBestWaiter(api_key);
        call.enqueue(new Callback<List<BestWaiterData>>() {
            @Override
            public void onResponse(Call<List<BestWaiterData>> call, Response<List<BestWaiterData>> response) {
                List<BestWaiterData> listTmp = response.body();
                listWaiter.clear();
                for (BestWaiterData item : listTmp) {
                    BestWaiterData i = item;
                    if(!listWaiter.contains(i)){
                        listWaiter.add(i);
                    }
                }
                Log.i("item count activity: ", String.valueOf(listWaiter.size()));
                System.out.println(String.valueOf(listWaiter.size()));
                ArrayList<Entry> yValues = new ArrayList<Entry>();
                ArrayList<String> xValues = new ArrayList<String>() ;
                i=0;
                for (BestWaiterData item: listWaiter) {
                    yValues.add(new Entry(item.getCount(),i));
                    xValues.add(String.valueOf(item.getIDPhucVu()));
                    i++;
                }
                PieDataSet dataSet =new PieDataSet(yValues,"Waiter Performance");
                PieData pieData = new PieData(xValues,dataSet);
                pieData.setValueFormatter(new PercentFormatter());
                dataSet.setColors(ColorTemplate.PASTEL_COLORS);
                pieChart.setData(pieData);
                pieChart.animateXY(1400, 1400);
            }
            @Override
            public void onFailure(Call<List<BestWaiterData>> call, Throwable t) {
                Log.e("get table error", t.getLocalizedMessage());
            }
        });
    }

}
