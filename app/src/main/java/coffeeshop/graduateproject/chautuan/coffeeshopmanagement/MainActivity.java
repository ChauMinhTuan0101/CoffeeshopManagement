package coffeeshop.graduateproject.chautuan.coffeeshopmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiClient;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiInterface;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.Bartender;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    public ApiInterface apiService;
    TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtView = findViewById(R.id.txtView);
        apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Bartender>> call = apiService.getBartenders();
        call.enqueue(new Callback<List<Bartender>>() {
            @Override
            public void onResponse(Call<List<Bartender>> call, Response<List<Bartender>> response) {
                List<Bartender> lstBartender = response.body();
                for(Bartender b:lstBartender)
                {
                    Toast.makeText(MainActivity.this, b.getBartenderName(), Toast.LENGTH_LONG).show();
                    Log.i("Bartender Name",b.getBartenderName());

                }

            }

            @Override
            public void onFailure(Call<List<Bartender>> call, Throwable t) {

            }
        });


    }

}
