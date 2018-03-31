package coffeeshop.graduateproject.chautuan.coffeeshopmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiInterface;

/**
 * Created by chautuan on 3/7/18.
 */

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.btnOrder)
    Button btnOrder;
    @BindView(R.id.btnCurrentOrder)
    Button btnCurrentOrder;
    @BindView(R.id.btnOrderHistory)
    Button btnOrderHistory;
    @BindView(R.id.btnLogOut)
    Button btnLogOut;
    @BindView(R.id.btnAddNewMenuItem)
    Button btnAddNewMenuItem;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_screen);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnOrder)
    public void Order()
    {
        Intent intent = new Intent(getApplicationContext(),ChooseTableActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnAddNewMenuItem)
    public void AddNewMenuItem()
    {
        Intent intent = new Intent(getApplicationContext(),AddMenuItemActitivy.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnLogOut)
    public void logOut(){
        SharedPreferences infosave = getSharedPreferences("my_data",MODE_PRIVATE);
        SharedPreferences.Editor editor = infosave.edit();
        editor.putString("username", "");
        editor.putString("api", "");
        editor.commit();
        this.finish();


    }

}
