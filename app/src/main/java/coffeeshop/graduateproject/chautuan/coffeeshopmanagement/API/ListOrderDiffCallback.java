package coffeeshop.graduateproject.chautuan.coffeeshopmanagement.API;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

import coffeeshop.graduateproject.chautuan.coffeeshopmanagement.model.Order;

public class ListOrderDiffCallback extends DiffUtil.Callback {
    private final List<Order> oldList;
    private final List<Order> newList;
    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getOrderID() == newList.get(
                newItemPosition).getOrderID();    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Order oldOrder = oldList.get(oldItemPosition);
        final Order newOrder = newList.get(newItemPosition);

        return oldOrder.getDateCreate().equals(newOrder.getDateCreate());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }

    public ListOrderDiffCallback(List<Order> oldList, List<Order> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }
}
