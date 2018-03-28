package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API;

import android.util.Log;
import android.widget.Toast;

import java.util.List;

import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.Bartender;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by chautuan on 3/1/18.
 */

public class ApiClient {
    public static final String BASE_URL = "http://35.198.227.187/BackendAPI/v1/index.php/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }



}
