package com.spark.cong.growrelationship;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    private Button btnAddGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init and map view
        mapView();

        //listener
        listenerEvent();

    }

    public void mapView(){

        // button add
        btnAddGroup = (Button)findViewById(R.id.btn_add_group);

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

    public void listenerEvent(){
        // add button listener
        btnAddGroup.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_add_group: {
                    Toast.makeText(getApplicationContext(),"addGroup",Toast.LENGTH_SHORT).show();
                }break;
            }
        }
    };
}
