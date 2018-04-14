package coffeeshop.graduateproject.chautuan.coffeeshopmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiClient;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiInterface;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.Bartender;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.LoginUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chautuan on 3/5/18.
 */

public class LoginActivity extends AppCompatActivity {
    public ApiInterface apiService;

    @BindView(R.id.edtUsername)
    EditText edtUserName;
    @BindView(R.id.edtPassword)
    EditText edtPassWord;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        avi.hide();



    }

    @OnClick(R.id.btnLogin)
    public void Login(){
        avi.show();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginUser> call = apiService.login(edtUserName.getText().toString(),edtPassWord.getText().toString());
        call.enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                avi.hide();
                LoginUser res = response.body();
                if (res.getError() == false) {
                    Toast.makeText(LoginActivity.this, "Login Successfully.", Toast.LENGTH_SHORT).show();
                    SharedPreferences infosave = getSharedPreferences("my_data",MODE_PRIVATE);
                    SharedPreferences.Editor editor = infosave.edit();
                    editor.putString("username", res.getName());
                    editor.putString("api", res.getApiKey());
                    editor.putInt("userid", res.getId()); // get user id
                    editor.commit();

                    if(res.getType() == 1)// phục vụ
                    {
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                    }
                    if(res.getType() == 2)
                    {
                        Intent intent = new Intent(getApplicationContext(),BartenderMenu.class);
                        startActivity(intent);
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this, "Login Failed, Please Check Your Account Again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login Failed, Please Check Your Account Again.", Toast.LENGTH_SHORT).show();
                Log.e("Error",t.getLocalizedMessage());
            }
        });


    }
}
