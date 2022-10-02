package edu.northeastern.numad22fa_kexinqiu;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LinkCollectorActivity extends AppCompatActivity {

    private ArrayList<LinkItem> list= new ArrayList<>();
    private FloatingActionButton add_fbtn;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ReviewAdapter reviewAdapter;

    private static final String LINK_KEY = "LINK_KEY";
    private static final String NUMBER_OF_LINKS = "NUMBER_OF_LINKS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        add_fbtn = findViewById(R.id.addItem_fbtn);
        add_fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLinkDialog();
            }
        });
    }

    public void addLinkDialog() {
        AddLinkFragment linkDialog = new AddLinkFragment();
        linkDialog.show(getSupportFragmentManager(), "add link dialog");
    }

    private void initialData(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_LINKS)) {
            if (list == null || list.size() == 0) {
                int size = savedInstanceState.getInt(NUMBER_OF_LINKS);

                for (int i = 0; i < size; i++) {
                    String linkName = savedInstanceState.getString(LINK_KEY + i + "1");
                    String linkURL = savedInstanceState.getString(LINK_KEY + i + "2");
                    list.add(new LinkItem(linkName, linkURL));
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

    }
}
