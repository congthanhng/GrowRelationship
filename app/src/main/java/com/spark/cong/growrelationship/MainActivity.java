package com.spark.cong.growrelationship;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spark.cong.growrelationship.Adapter.GroupPeopleRecyclerAdapter;
import com.spark.cong.growrelationship.Architecture.Entity.GroupPeole;
import com.spark.cong.growrelationship.Architecture.ViewModel.GroupPeopleViewModel;
import com.spark.cong.growrelationship.Commons.ItemSpacingDecorator;

import java.util.List;

import static com.spark.cong.growrelationship.Commons.Constant.ITEM_SPACING;
import static com.spark.cong.growrelationship.Commons.Constant.SPAN_COUNT;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GroupPeopleViewModel groupPeopleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init and map view
        mapView();

    }

    public void mapView(){



        //RecyclerView init
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewGroupPeople);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,SPAN_COUNT);
        final GroupPeopleRecyclerAdapter adapter = new GroupPeopleRecyclerAdapter(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.hasFixedSize();
        recyclerView.addItemDecoration(new ItemSpacingDecorator(ITEM_SPACING,SPAN_COUNT)); // spacing between items

        //init viewModel
        groupPeopleViewModel = new ViewModelProvider(this).get(GroupPeopleViewModel.class);

        //LiveData
        groupPeopleViewModel.getAllGroupPeople().observe(this, new Observer<List<GroupPeole>>() {
            @Override
            public void onChanged(List<GroupPeole> groupPeoles) {
                adapter.setData(groupPeoles);
            }
        });
    }
}
