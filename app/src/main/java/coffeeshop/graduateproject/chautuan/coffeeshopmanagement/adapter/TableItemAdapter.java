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

import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.OrderMenuActivity;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.R;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.Table;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by chautuan on 3/30/18.
 */

public class TableItemAdapter extends RecyclerView.Adapter<TableItemAdapter.MyViewHolder> {
    private List<Table> listTable = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTableNumber;
        public TextView tvStatus;
        public Button btnChooseTable;


        public MyViewHolder(View itemView) {
            super(itemView);

            tvTableNumber = itemView.findViewById(R.id.tvTableNumber);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            btnChooseTable = itemView.findViewById(R.id.btnChooseTable);

        }

    }

    public TableItemAdapter(List<Table> listTable) {
        this.listTable = listTable;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_item_listview,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

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
                SharedPreferences saveTableNumber =    v.getContext().getSharedPreferences("tableNumber", MODE_PRIVATE);
                SharedPreferences.Editor edit=saveTableNumber.edit();
                edit.putString("numbertable",String.valueOf(table.getTableID()));
                edit.commit();
                Intent intent = new Intent(v.getContext(), OrderMenuActivity.class);
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
