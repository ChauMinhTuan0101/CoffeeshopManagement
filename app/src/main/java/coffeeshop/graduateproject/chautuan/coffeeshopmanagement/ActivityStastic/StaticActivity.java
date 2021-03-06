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
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.ChartObjectData.StasticData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaticActivity extends AppCompatActivity {
    private List<StasticData> listOrder = new ArrayList<>();
    String api_key;
    public ApiInterface apiService;
    SharedPreferences infosave;
    PieChart pieChart;
    int i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.pie_chart_layout);
        super.onCreate(savedInstanceState);

        infosave = getSharedPreferences("my_data", MODE_PRIVATE);
        api_key = infosave.getString("api", "");
        apiService = ApiClient.getClient().create(ApiInterface.class);
        pieChart = findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);



        Call<List<StasticData>> call = apiService.getStastic(api_key);
        call.enqueue(new Callback<List<StasticData>>() {
            @Override
            public void onResponse(Call<List<StasticData>> call, Response<List<StasticData>> response) {
                List<StasticData> listTmp = response.body();
                listOrder.clear();
                for (StasticData item : listTmp) {
                    StasticData i = item;
                    if(!listOrder.contains(i)){
                        listOrder.add(i);
                    }


                    Log.i("table: ", String.valueOf(item.getItemName()));
                }
                Log.i("item count activity: ", String.valueOf(listOrder.size()));
                System.out.println(String.valueOf(listOrder.size()));
                ArrayList<Entry> yValues = new ArrayList<Entry>();
                ArrayList<String> xValues = new ArrayList<String>() ;
                i=0;
                for (StasticData item: listOrder) {
                    yValues.add(new Entry(item.getTotal(),i));
                    xValues.add(item.getItemName());
                    i++;
                }
                PieDataSet dataSet =new PieDataSet(yValues,"Stastic");
                PieData pieData = new PieData(xValues,dataSet);
                pieData.setValueFormatter(new PercentFormatter());
                dataSet.setColors(ColorTemplate.PASTEL_COLORS);

                pieChart.setData(pieData);
                pieChart.animateXY(1400, 1400);
            }
            @Override
            public void onFailure(Call<List<StasticData>> call, Throwable t) {
                Log.e("get table error", t.getLocalizedMessage());
            }
        });



    }

}
