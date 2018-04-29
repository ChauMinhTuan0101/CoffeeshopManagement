package coffeeshop.graduateproject.chautuan.coffeeshopmanagement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.adapter.MenuChartAdapter;

public class MenuChartActivity extends AppCompatActivity {
    private MenuChartAdapter menuChartAdapter;
    @BindView(R.id.recyclerMenuChart)
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_menuchart_main);
        ButterKnife.bind(this);
        List<String> listItem = new ArrayList<>();
        listItem.add("View Stastic as Drinks");
        listItem.add("View Stastic as Quarter");
        listItem.add("View Waiter Performance");
        listItem.add("View Table Choices");

        menuChartAdapter = new MenuChartAdapter(listItem);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(menuChartAdapter);
    }
}
