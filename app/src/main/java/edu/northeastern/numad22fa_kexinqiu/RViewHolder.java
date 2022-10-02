package edu.northeastern.numad22fa_kexinqiu;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RViewHolder extends RecyclerView.ViewHolder {
    public TextView itemName;
    public TextView itemURL;

    public RViewHolder(View itemView) {
        super(itemView);
        itemName = itemView.findViewById(R.id.item_name);
        itemURL = itemView.findViewById(R.id.item_link);

        itemURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = itemURL.getText().toString();

                url = "http://" + url;

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                view.getContext().startActivity(intent);
            }
        });
    }
}