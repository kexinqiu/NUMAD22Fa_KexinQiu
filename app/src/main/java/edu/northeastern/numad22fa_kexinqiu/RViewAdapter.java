package edu.northeastern.numad22fa_kexinqiu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RViewAdapter extends RecyclerView.Adapter<RViewHolder> {

    private final ArrayList<LinkItem> itemList;

    public RViewAdapter(ArrayList<LinkItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.link_item, parent, false);
        return new RViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RViewHolder holder, int position) {
        LinkItem currentItem = itemList.get(position);

        holder.itemName.setText(currentItem.getItemName());
        holder.itemURL.setText(currentItem.getItemURl());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}