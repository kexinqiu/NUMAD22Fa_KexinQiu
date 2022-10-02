package edu.northeastern.numad22fa_kexinqiu;

import android.os.Bundle;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LinkCollectorActivity extends AppCompatActivity implements AddLinkFragment.AddItemFragmentListener {

    private ArrayList<LinkItem> list= new ArrayList<>();
    private FloatingActionButton add_fbtn;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RViewAdapter rviewAdapter;

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

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Snackbar.make(recyclerView, "Delete",
                        Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                int position = viewHolder.getLayoutPosition();
                list.remove(position);

                rviewAdapter.notifyItemRemoved(position);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public void addLinkDialog() {
        AddLinkFragment linkDialog = new AddLinkFragment();
        linkDialog.show(getSupportFragmentManager(), "add link dialog");
    }

    private void init(Bundle savedInstanceState) {
        initialData(savedInstanceState);
        createRecyclerView();
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
        int size = list == null ? 0 : list.size();
        outState.putInt(NUMBER_OF_LINKS, size);

        for (int i = 0; i < size; i++) {
            outState.putString(LINK_KEY + i + "1", list.get(i).getItemLink());
            outState.putString(LINK_KEY + i + "2", list.get(i).getItemLink());
        }

        super.onSaveInstanceState(outState);
    }

    private void createRecyclerView() {
        layoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.link_collector_rv);
        recyclerView.setHasFixedSize(true);

        rviewAdapter = new RViewAdapter(list);

        recyclerView.setAdapter(rviewAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void addItem(int pos, String name, String url) {
        list.add(pos, new LinkItem(name, url));
        Snackbar.make(recyclerView, "Url: " + url + " registered", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        rviewAdapter.notifyItemInserted(pos);
    }

    @Override
    public void saveInputLink(String name, String url){

    }
}
