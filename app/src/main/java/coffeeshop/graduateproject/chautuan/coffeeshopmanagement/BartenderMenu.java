package coffeeshop.graduateproject.chautuan.coffeeshopmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.Bartender;

/**
 * Created by chautuan on 4/14/18.
 */

public class BartenderMenu extends AppCompatActivity {
    @BindView(R.id.btnServingOrder)
    Button btnServingOrder;
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


}
