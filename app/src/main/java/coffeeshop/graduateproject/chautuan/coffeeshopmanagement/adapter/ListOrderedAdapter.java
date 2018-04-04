package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.OrderMenuActivity;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.R;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.OrderDetail;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.Table;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by chautuan on 4/4/18.
 */

public class ListOrderedAdapter extends RecyclerView.Adapter<ListOrderedAdapter.MyViewHolder> {
    List<OrderDetail> listOrderDetail = new ArrayList<>();




    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tvOrderedItemName;
        public TextView tvOrderedPrice;
        public Button btnDelete;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvOrderedItemName= itemView.findViewById(R.id.tvOrderedItem);
            tvOrderedPrice = itemView.findViewById(R.id.tvOrderedPrice);
            btnDelete = itemView.findViewById(R.id.btnDeleteOrderedItem);
        }
    }

    public ListOrderedAdapter(List<OrderDetail> listOrderDetail) {
        this.listOrderDetail = listOrderDetail;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ordered_listview,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final OrderDetail orderDetail = listOrderDetail.get(position);
//        holder.tvOrderedItemName.setText("Table: " + String.valueOf(orderDetail.get()));
//        if(table.getTableStatus() == 0)
//        {
//            holder.tvStatus.setTextColor(Color.GREEN);
//            holder.tvStatus.setText("Available");
//        }
//        if(table.getTableStatus() == 1)
//        {
//            holder.tvStatus.setTextColor(Color.RED);
//            holder.tvStatus.setText("Unvailable");
//        }
//        holder.btnChooseTable.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences saveTableNumber =    v.getContext().getSharedPreferences("tableNumber", MODE_PRIVATE);
//                SharedPreferences.Editor edit=saveTableNumber.edit();
//                edit.putString("numbertable",String.valueOf(table.getTableID()));
//                edit.commit();
//                Intent intent = new Intent(v.getContext(), OrderMenuActivity.class);
//                v.getContext().startActivity(intent);
//            }
//        });
//
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
