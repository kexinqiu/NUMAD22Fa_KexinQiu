package edu.northeastern.numad22fa_kexinqiu;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RViewHolder extends RecyclerView.ViewHolder {
    public EditText linkName;
    public EditText linkURL;

    public RViewHolder(View v) {
        super(v);

        linkName = v.findViewById(R.id.dialog_name_text);
        linkURL = v.findViewById(R.id.dialog_url_text);

        linkURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = linkURL.toString();
                if (!url.startsWith("http://")) {
                    url = "http://" + url;
                }

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                v.getContext().startActivity(intent);
            }
        });
    }
}
