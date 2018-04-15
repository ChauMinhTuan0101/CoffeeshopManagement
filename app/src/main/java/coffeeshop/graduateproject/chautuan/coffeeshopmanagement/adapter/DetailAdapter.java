package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.adapter;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiInterface;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.R;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.OrderDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by chautuan on 4/15/18.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MyViewHolder> {

    public List<OrderDetail> listOrderDetail = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvItenmDetail;
        public TextView tvQuantityDetail;
        ApiInterface apiService;
        List<OrderDetail> listOrderDetail;


        public MyViewHolder(final View itemView) {

            super(itemView);
            SharedPreferences infosave = itemView.getContext().getSharedPreferences("my_data", MODE_PRIVATE);
            final String authToken = infosave.getString("api", "");
            tvItenmDetail = itemView.findViewById(R.id.tvItenmDetail);
            tvQuantityDetail = itemView.findViewById(R.id.tvQuantityDetail);

        }


    }
        public DetailAdapter(List<OrderDetail> listOrderDetail) {
        this.listOrderDetail = listOrderDetail;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_detail_listview, parent, false);
        return new DetailAdapter.MyViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final OrderDetail orderDetail = listOrderDetail.get(position);
        holder.tvItenmDetail.setText(orderDetail.getItemName());
        holder.tvQuantityDetail.setText(String.valueOf(orderDetail.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return listOrderDetail.size();
    }




    }

