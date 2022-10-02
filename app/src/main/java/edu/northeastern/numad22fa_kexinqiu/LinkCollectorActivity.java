package edu.northeastern.numad22fa_kexinqiu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class LinkCollectorActivity extends AppCompatActivity implements AddLinkItemFragment.AddLinkItemListener {
    private ArrayList<LinkItem> itemList = new ArrayList<>();
    private FloatingActionButton addItem_btn;

    private RecyclerView recyclerView;
    private RViewAdapter rViewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;

    private static final String ITEM_KEY = "ITEM_KEY";
    private static final String ITEMS_SIZE = "ITEMS_SIZE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        initialItemData(savedInstanceState);
        createRecyclerView();

        addItem_btn = findViewById(R.id.add_linkitem_btn);
        addItem_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLinkItemDialog();
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Snackbar.make(recyclerView, "Removed Link",
                                Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                int position = viewHolder.getLayoutPosition();
                itemList.remove(position);

                rViewAdapter.notifyItemRemoved(position);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public void addLinkItemDialog() {
        AddLinkItemFragment addLinkItemFragment = new AddLinkItemFragment();
        addLinkItemFragment.show(getSupportFragmentManager(), "Enter Name and URL:");
    }

    @Override
    public void setInput(String name, String url) {
        if (isValidURL(url)) {
            addItem(0, name, url);
        } else {
            Snackbar.make(recyclerView, "Invalid URL.",
                            Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    private boolean isValidURL(String url) {
        return Patterns.WEB_URL.matcher(url).matches();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        int size = itemList == null ? 0 : itemList.size();
        outState.putInt(ITEMS_SIZE, size);

        for (int i = 0; i < size; i++) {
            outState.putString(ITEM_KEY + i + "1", itemList.get(i).getItemName());
            outState.putString(ITEM_KEY + i + "2", itemList.get(i).getItemURl());
        }
        super.onSaveInstanceState(outState);
    }

    private void initialItemData(Bundle savedInstanceState) {

        if (savedInstanceState != null && savedInstanceState.containsKey(ITEMS_SIZE)) {
            if (itemList == null || itemList.size() == 0) {

                int size = savedInstanceState.getInt(ITEMS_SIZE);

                for (int i = 0; i < size; i++) {

                    String itemName = savedInstanceState.getString(ITEM_KEY + i + "1");
                    String itemURL= savedInstanceState.getString(ITEM_KEY + i + "2");

                    LinkItem linkItem = new LinkItem(itemName, itemURL);

                    itemList.add(linkItem);
                }
            }
        }
    }

    private void createRecyclerView() {

        rLayoutManger = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.link_recycler_view);
        recyclerView.setHasFixedSize(true);

        rViewAdapter = new RViewAdapter(itemList);

        recyclerView.setAdapter(rViewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);
    }

    private void addItem(int position, String name, String url) {
        itemList.add(position, new LinkItem(name, url));
        Snackbar.make(recyclerView, "Link " + name + " added successfully!",
                        Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        rViewAdapter.notifyItemInserted(position);
    }
}