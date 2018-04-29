package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.ActivityStastic;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiClient;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiInterface;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.R;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.ChartObjectData.QuarterData;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.utils.ColorGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAsQuarterActivity extends AppCompatActivity{
    private List<QuarterData> listOrder = new ArrayList<>();
    private List<String> listItemName = new ArrayList<>();
    String api_key;
    public ApiInterface apiService;
    SharedPreferences infosave;
    LineChart lineChart;
    //int i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.line_chart_layout);
        super.onCreate(savedInstanceState);

        infosave = getSharedPreferences("my_data", MODE_PRIVATE);
        api_key = infosave.getString("api", "");
        apiService = ApiClient.getClient().create(ApiInterface.class);
        lineChart = findViewById(R.id.line_chart);


        Call<List<QuarterData>> call = apiService.getAllStasticQuarter(api_key);
        call.enqueue(new Callback<List<QuarterData>>() {
            @Override
            public void onResponse(Call<List<QuarterData>> call, Response<List<QuarterData>> response) {
                List<QuarterData> listTmp = response.body();
                listOrder.clear();
                for (QuarterData item : listTmp) {
                    QuarterData i = item;
                    if(listOrder.contains(i) == false){
                        listOrder.add(i);
                    }
                    Log.i("table: ", String.valueOf(item.getItemName()));
                }
                ///////// LAY TOAN BO  LIST DU LIEU CAC QUY

                for (QuarterData item: listOrder) { //quarter has Item Name
                    if(!listItemName.contains(item.getItemName())) {
                        listItemName.add(item.getItemName());
                    }
                }

                Log.i("item count activity: ", String.valueOf(listItemName.size()));
                System.out.println(String.valueOf(listItemName.size()));
                ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
                ArrayList<String> xValues = new ArrayList<String>() ;

                for (String item: listItemName) {
                    Log.i("Process for ", item);
                    ArrayList<Entry> yValues = new ArrayList<Entry>();
                    int i  =0;
                    for (QuarterData data: listOrder) {
                        if(item.equals(data.getItemName())) {
                            yValues.add(new Entry(Float.valueOf(data.getItemQuantity()), i));
                            i++;
                        }
                        if(!xValues.contains(String.valueOf(data.getQuarter()))) {
                            xValues.add(String.valueOf(data.getQuarter()));
                        }

                    }
                    LineDataSet set =new LineDataSet(yValues,item);
                    set.setColor(ColorTemplate.rgb(ColorGenerator.colorGenerator().toString()));
                    dataSets.add(set);
                    //lineChart.setData(lineData);
                }
                LineData lineData = new LineData(xValues,dataSets);
                lineChart.setData(lineData);
                lineChart.animateY(1400);

            }
            @Override
            public void onFailure(Call<List<QuarterData>> call, Throwable t) {
                Log.e("get table error", t.getLocalizedMessage());
            }
        });



    }

}

