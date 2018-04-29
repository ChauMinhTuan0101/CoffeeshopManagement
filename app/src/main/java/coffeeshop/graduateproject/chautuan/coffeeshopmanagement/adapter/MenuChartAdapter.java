package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.adapter;

import android.content.Intent;
import android.content.SharedPreferences;
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

import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiClient;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API.ApiInterface;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.ActivityStastic.BestTableActivity;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.ActivityStastic.BestWaiterActivity;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.ActivityStastic.StaticActivity;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.ActivityStastic.ViewAsQuarterActivity;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.R;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.bus.MessageEvent;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.OrderDetail;
import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.ResponseInfomation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class MenuChartAdapter extends RecyclerView.Adapter<MenuChartAdapter.MyViewHolder> {
    List<String> listChartItem = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvChartItem;
        private ItemClickListener itemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvChartItem= itemView.findViewById(R.id.tvChartItem);
            itemView.setOnClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);

        }
    }
    public MenuChartAdapter(List<String> listItem) {
        this.listChartItem = listItem;
    }
    @NonNull
    @Override
    public MenuChartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_menuchart_listview,parent,false);
        return new MenuChartAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MenuChartAdapter.MyViewHolder holder, final int position) {

        final String item = listChartItem.get(position);
        holder.tvChartItem.setText(item);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                switch (position)
                {
                    case 0:
                        Intent intent1 = new Intent(view.getContext(), StaticActivity.class);
                        view.getContext().startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(view.getContext(), ViewAsQuarterActivity.class);
                        view.getContext().startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(view.getContext(), BestWaiterActivity.class);
                        view.getContext().startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent(view.getContext(),BestTableActivity.class);
                        view.getContext().startActivity(intent4);
                        break;
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return listChartItem.size();
    }

}

