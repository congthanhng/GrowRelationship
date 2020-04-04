package com.spark.cong.growrelationship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.spark.cong.growrelationship.Adapter.GroupPeopleRecyclerViewAdapter;
import com.spark.cong.growrelationship.Architecture.Entity.GroupPeole;
import com.spark.cong.growrelationship.Architecture.ViewModel.GroupPeopleViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GroupPeopleViewModel groupPeopleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewGroupPeople);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        final GroupPeopleRecyclerViewAdapter adapter = new GroupPeopleRecyclerViewAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.hasFixedSize();

        groupPeopleViewModel = new ViewModelProvider(this).get(GroupPeopleViewModel.class);

        groupPeopleViewModel.getAllGroupPeople().observe(this, new Observer<List<GroupPeole>>() {
            @Override
            public void onChanged(List<GroupPeole> groupPeoles) {
                adapter.setData(groupPeoles);
            }
        });

    }
}
