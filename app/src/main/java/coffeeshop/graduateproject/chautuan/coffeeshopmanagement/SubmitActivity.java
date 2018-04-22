package coffeeshop.graduateproject.chautuan.coffeeshopmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiClient;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiInterface;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.LoginUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chautuan on 4/8/18.
 */

public class SubmitActivity extends AppCompatActivity {
    @BindView(R.id.edtPasswordSubmit)
    EditText edtPasswordSubmit;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    ApiInterface apiService;
    String userName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.serv_submit);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        SharedPreferences infosave = getSharedPreferences("my_data",MODE_PRIVATE);
        userName = infosave.getString("username", "");



    }

    @OnClick(R.id.btnSubmit)
    public void submitServing()
    {
        Call<LoginUser> call = apiService.login(userName,edtPasswordSubmit.getText().toString());
        call.enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                LoginUser res = response.body();
                if (res.getError() == false) {
                    Toast.makeText(getApplicationContext(), "Order Complete", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(getApplicationContext(), "Login Failed, Please Check Your Account Again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {

            }

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SubmitActivity.this,MenuActivity.class));

    }
}
