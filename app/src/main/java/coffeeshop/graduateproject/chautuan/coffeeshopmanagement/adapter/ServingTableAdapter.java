package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.CheckOutActivity;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.R;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.Table;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by chautuan on 4/7/18.
 */

public class ServingTableAdapter extends RecyclerView.Adapter<ServingTableAdapter.MyViewHolder> {
    private List<Table> listTable = new ArrayList<>();
    public String tableNumber;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTableNumber;
        public TextView tvStatus;
        public Button btnChooseTable;


        public MyViewHolder(View itemView) {
            super(itemView);

            tvTableNumber = itemView.findViewById(R.id.tvTableNumberServingTable);
            tvStatus = itemView.findViewById(R.id.tvStatusServingTable);
            btnChooseTable = itemView.findViewById(R.id.btnChooseServingTable);

        }

    }

    public ServingTableAdapter(List<Table> listTable) {
        this.listTable = listTable;
    }

    @Override
    public ServingTableAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_list_serving,parent,false);
        return new ServingTableAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ServingTableAdapter.MyViewHolder holder, int position) {

        final Table table = listTable.get(position);
        holder.tvTableNumber.setText("Table: " + String.valueOf(table.getTableID()));
        if(table.getTableStatus() == 0)
        {
            holder.tvStatus.setTextColor(Color.GREEN);
            holder.tvStatus.setText("Available");
        }
        if(table.getTableStatus() == 1)
        {
            holder.tvStatus.setTextColor(Color.RED);
            holder.tvStatus.setText("Unvailable");
        }
        holder.btnChooseTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences saveServingTableNumber =    v.getContext().getSharedPreferences("servingtablenumber", MODE_PRIVATE);
                SharedPreferences.Editor edit = saveServingTableNumber.edit();
                edit.putInt("servingtablenumber",table.getTableID());
                edit.commit();
                Intent intent = new Intent(v.getContext(), CheckOutActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.i("item count adapter", String.valueOf(listTable.size()));
        return listTable.size();
    }


}
