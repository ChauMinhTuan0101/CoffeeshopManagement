package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.adapter;

import android.content.SharedPreferences;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiInterface;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.R;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.bus.MessageEvent;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.LatestOrder;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.MenuItem;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.OrderDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by chautuan on 3/9/18.
 */

public class MenuItemsAdapter extends RecyclerView.Adapter<MenuItemsAdapter.MyViewHolder> {
    private List<MenuItem> listMenuItem = new ArrayList<>();


    public List<OrderDetail> selectedList = new ArrayList<>();
    public long totalPrice;
    public Button btnAddInAdapter;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvItemName;
        public TextView tvItemPrice;
        public TextView tvQuantity;
        public Button btnPlus;
        public Button btnMinus;
        public Button btnAdd;
        int latestID;
        int itemID;
        ApiInterface apiService;
        List<OrderDetail> lstOrderDetail;


        public MyViewHolder(final View itemView) {

            super(itemView);
            //ButterKnife.bind(this, itemView);
            SharedPreferences infosave =    itemView.getContext().getSharedPreferences("my_data", MODE_PRIVATE);
            final String authToken = infosave.getString("api", "");
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnAdd = itemView.findViewById(R.id.btnAdd);
            btnAddInAdapter = btnAdd;
            lstOrderDetail = new ArrayList<>();
            btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int quantity  = Integer.parseInt(tvQuantity.getText().toString());
                    quantity++;
                    tvQuantity.setText(String.valueOf(quantity));
                }
            });

            btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int quantity  = Integer.parseInt(tvQuantity.getText().toString());
                    if(quantity>1) {
                        quantity--;
                        tvQuantity.setText(String.valueOf(quantity));
                    }
                }
            });
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Call<LatestOrder> call = apiService.getLatestOrderID(authToken);
                    call.enqueue(new Callback<LatestOrder>() {
                        @Override
                        public void onResponse(Call<LatestOrder> call, Response<LatestOrder> response) {
                            LatestOrder latest = response.body();

                            latestID = latest.getOrderID();
                            Log.e("Latest Adapter :",String.valueOf(latestID));

                        }
                        @Override
                        public void onFailure(Call<LatestOrder> call, Throwable t) {
                            Log.e("Latest Order Error :",t.getLocalizedMessage());
                        }
                    });
                }
            });
        }
    }

    public MenuItemsAdapter(List<MenuItem> menuItemList)
    {
        this.listMenuItem = menuItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_listview,parent,false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final MenuItem menuItem = listMenuItem.get(position);
        holder.tvItemName.setText(menuItem.getItemName().toString());
        holder.tvItemPrice.setText(menuItem.getItemPrice().toString());
        holder.itemID = menuItem.getItemID();
        holder.tvQuantity.setText("1");
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderDetail od = new OrderDetail();
                od.setOrderID(0);
                od.setItemID(menuItem.getItemID());
                od.setItemPrice(menuItem.getItemPrice());
                od.setQuantity(Integer.parseInt(holder.tvQuantity.getText().toString()));
                selectedList.add(od);

                totalPrice+= (menuItem.getItemPrice() * od.getQuantity());

                EventBus.getDefault().post(new MessageEvent(totalPrice));

                Log.i("total price", String.valueOf(totalPrice));


            }
        });
    }


    @Override
    public int getItemCount() {
        return listMenuItem.size();
    }




}
