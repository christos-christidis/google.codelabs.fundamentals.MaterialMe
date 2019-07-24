package com.fundamentals.materialme;

import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<Sport> mSports = new ArrayList<>();
    private SportsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        mAdapter = new SportsAdapter(this, mSports);
        recyclerView.setAdapter(mAdapter);

        initializeData();

        int dragDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        // SOS: swiping away when more than 1 columns is counter-intuitive!
        if (gridColumnCount == 1) {
            swipeDirs = 0;
        }
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                Collections.swap(mSports, fromPosition, toPosition);
                mAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                mSports.remove(position);
                mAdapter.notifyItemRemoved(position);
            }
        });

        helper.attachToRecyclerView(recyclerView);
    }

    private void initializeData() {
        String[] sportsTitles = getResources().getStringArray(R.array.sports_titles);
        String[] sportsInfo = getResources().getStringArray(R.array.sports_info);

        // SOS: I guess this is the equiv of getStringArray but for other types...
        TypedArray sportsResIds = getResources().obtainTypedArray(R.array.sports_images);

        mSports.clear();
        for (int i = 0; i < sportsTitles.length; i++) {
            mSports.add(new Sport(sportsTitles[i], sportsInfo[i], sportsResIds.getResourceId(i, 0)));
        }

        sportsResIds.recycle();

        mAdapter.notifyDataSetChanged();
    }

    public void resetSports(View view) {
        initializeData();
    }
}
