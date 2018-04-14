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
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.OrderDetail;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.ResponseInfomation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by chautuan on 4/4/18.
 */

public class ListOrderedAdapter extends RecyclerView.Adapter<ListOrderedAdapter.MyViewHolder> {
    List<OrderDetail> listOrderDetail = new ArrayList<>();
    private ApiInterface apiService;
    public int checkOutPrice;


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
        for (OrderDetail item: listOrderDetail) {
            checkOutPrice += item.getItemPrice() * item.getQuantity();
        }
        System.out.print("checkout price at create: " + checkOutPrice);
        EventBus.getDefault().post(new MessageEvent(checkOutPrice));


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ordered_listview,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final OrderDetail orderDetail = listOrderDetail.get(position);

        holder.tvOrderedItemName.setText(orderDetail.getItemName());
        holder.tvOrderedPrice.setText(String.valueOf(orderDetail.getItemPrice()));
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                apiService = ApiClient.getClient().create(ApiInterface.class);
                SharedPreferences infosave = v.getContext().getSharedPreferences("my_data", MODE_PRIVATE);
                String api_key = infosave.getString("api", "");
                System.out.print("order detail id: " + orderDetail.getOrderDetailID());
                Call<ResponseInfomation> deleteOrderDetail = apiService.deleteOrderDetail(api_key,orderDetail.getOrderDetailID());
                deleteOrderDetail.enqueue(new Callback<ResponseInfomation>() {
                    @Override
                    public void onResponse(Call<ResponseInfomation> call, Response<ResponseInfomation> response) {
                        ResponseInfomation rp = response.body();
                        if(rp.getError() == false)
                        {
                            Toast.makeText(v.getContext(), "Delete Item Complete", Toast.LENGTH_SHORT).show();
                            checkOutPrice -= orderDetail.getItemPrice()*orderDetail.getQuantity();
                            EventBus.getDefault().post(new MessageEvent(checkOutPrice));
                        }

                    }
                    @Override
                    public void onFailure(Call<ResponseInfomation> call, Throwable t) {
                        Log.e("DeleteOrderDetail", t.getLocalizedMessage());

                    }
                });
                listOrderDetail.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,listOrderDetail.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return listOrderDetail.size();
    }

}
