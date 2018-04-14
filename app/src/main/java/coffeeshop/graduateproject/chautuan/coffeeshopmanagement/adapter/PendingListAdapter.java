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
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiClient;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiInterface;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.R;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.bus.MessageEvent;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.Order;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.OrderDetail;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.ResponseInfomation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by chautuan on 4/14/18.
 */

public class PendingListAdapter extends RecyclerView.Adapter<PendingListAdapter.MyViewHolder> {
    List<Order> listOrder = new ArrayList<>();
    private ApiInterface apiService;
    public int checkOutPrice;


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tvPendingTable;
        public TextView tvPendingOrderID;
        public Button btnPendingChoose;


        public MyViewHolder(View itemView) {
            super(itemView);
            tvPendingTable= itemView.findViewById(R.id.tvPendingTable);
            tvPendingOrderID = itemView.findViewById(R.id.tvPendingOrderID);
            btnPendingChoose = itemView.findViewById(R.id.btnPendingChoose);
        }
    }

    public PendingListAdapter(List<Order> listOrder) {
        this.listOrder = listOrder;
    }

    @NonNull
    @Override
    public PendingListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pending_listview,parent,false);
        return new PendingListAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(PendingListAdapter.MyViewHolder holder, final int position) {

        final Order order = listOrder.get(position);
        final List<OrderDetail> listOrderDetail = new ArrayList<>();

        holder.tvPendingOrderID.setText("Order ID: " +String.valueOf(order.getOrderID()));
        holder.tvPendingTable.setText("Table Number: " +String.valueOf(order.getTableNumber()));
        holder.btnPendingChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                apiService = ApiClient.getClient().create(ApiInterface.class);
                SharedPreferences infosave = v.getContext().getSharedPreferences("my_data", MODE_PRIVATE);
                String api_key = infosave.getString("api", "");
                Call<List<OrderDetail>> getodByID = apiService.getOrderDetailByID(api_key,order.getOrderID());
                getodByID.enqueue(new Callback<List<OrderDetail>>() {
                    @Override
                    public void onResponse(Call<List<OrderDetail>> call, Response<List<OrderDetail>> response) {
                        List<OrderDetail> tmp = response.body();
                        for(OrderDetail i : tmp)
                        {
                            OrderDetail od = i;
                            listOrderDetail.add(od);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<OrderDetail>> call, Throwable t) {

                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

}
