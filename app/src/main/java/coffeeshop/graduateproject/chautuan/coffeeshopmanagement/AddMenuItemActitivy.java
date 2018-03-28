package coffeeshop.graduateproject.chautuan.coffeeshopmanagement;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiClient;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiInterface;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.R;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.ResponseInfomation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chautuan on 3/27/18.
 */

public class AddMenuItemActitivy extends AppCompatActivity {

    @BindView(R.id.edtItemName)
    EditText edtItemName;
    @BindView(R.id.edtItemPrice)
    EditText edtItemPrice;
    @BindView(R.id.edtItemNotice)
    EditText edtItemNotice;
    @BindView(R.id.spItemType)
    Spinner spItemType;
    @BindView(R.id.btnAddMenuItem)
    Button btnAddMenuItem;

    ApiInterface apiService;
    String api_key;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);

        ButterKnife.bind(this);
        addItemTypeToSpinner();

        apiService = ApiClient.getClient().create(ApiInterface.class);

        SharedPreferences infosave = getSharedPreferences("my_data", MODE_PRIVATE);
        api_key = infosave.getString("api", "");


    }
    @OnClick(R.id.btnAddMenuItem)
    public void postAddMenuItem()
    {
        String numType = (spItemType.getSelectedItem().toString()).substring(0,1);
        int itemtype = Integer.parseInt(numType);
        Log.i("test value",edtItemName.getText().toString() );
        Call<ResponseInfomation> callAddMenuItem = apiService.addMenuItem(
                api_key,
                edtItemName.getText().toString(),
                itemtype,
                Integer.parseInt(edtItemPrice.getText().toString()),
                edtItemNotice.getText().toString());

        callAddMenuItem.enqueue(new Callback<ResponseInfomation>() {
            @Override
            public void onResponse(Call<ResponseInfomation> call, Response<ResponseInfomation> response) {
                ResponseInfomation ls = response.body();
                Log.i("Add New Menu Item: ",ls.getMessage() + ls.getError());
                if(ls.getError()== false)
                {
                    Toast.makeText(AddMenuItemActitivy.this, "Add Menu Item Succesfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInfomation> call, Throwable t) {
                Log.e("Add Menu Item Error; ",t.getLocalizedMessage());
            }
        });
    }

    private void addItemTypeToSpinner()
    {
        List<String> spinnerItem = new ArrayList<>();
        spinnerItem.add("1 - Drink");
        spinnerItem.add("2 - Desert");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item,spinnerItem);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spItemType.setAdapter(spinnerAdapter);


    }

    @OnFocusChange(R.id.edtItemNotice)
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
