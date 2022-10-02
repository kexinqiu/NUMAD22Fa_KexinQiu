package edu.northeastern.numad22fa_kexinqiu;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RViewAdapter extends RecyclerView.Adapter<RViewHolder> {
    private List<LinkItem> list;
    //private LinkClickListener listener;

    public RViewAdapter(List<LinkItem> list) {
        this.list = list;
    }

    @Override
    public RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_link_dialog, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RViewHolder holder, int position) {
        // sets the name of the person to the name textview of the viewholder.
        holder.linkName.setText(list.get(position).getItemName());
        // sets the age of the person to the age textview of the viewholder.
        holder.linkURL.setText(String.valueOf(list.get(position).getItemLink()));
    }

    @Override
    public int getItemCount() {
        // Returns the size of the recyclerview that is the list of the arraylist.
        return list.size();
    }

}
