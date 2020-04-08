package com.spark.cong.growrelationship;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spark.cong.growrelationship.Adapter.GroupPeopleRecyclerAdapter;
import com.spark.cong.growrelationship.Architecture.Entity.GroupPeole;
import com.spark.cong.growrelationship.Architecture.ViewModel.GroupPeopleViewModel;
import com.spark.cong.growrelationship.Commons.ItemSpacingDecorator;
import com.spark.cong.growrelationship.Dialog.AddGroupDialog;

import java.util.List;

import static com.spark.cong.growrelationship.Commons.Constant.ITEM_SPACING;
import static com.spark.cong.growrelationship.Commons.Constant.SPAN_COUNT;

public class MainActivity extends AppCompatActivity implements AddGroupDialog.EditNameGroupListener {
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

    /**
     * init, set and map view
     */
    public void mapView() {

        // button add
        btnAddGroup = (Button) findViewById(R.id.btn_add_group);

        //RecyclerView init
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewGroupPeople);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, SPAN_COUNT);
        final GroupPeopleRecyclerAdapter adapter = new GroupPeopleRecyclerAdapter(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.hasFixedSize();
        recyclerView.addItemDecoration(new ItemSpacingDecorator(ITEM_SPACING, SPAN_COUNT)); // spacing between items


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

    /**
     * init, set listener of Views
     */
    public void listenerEvent() {
        // add group button listener
        btnAddGroup.setOnClickListener(mOnClickListener);
    }

    /**
     * Init listener
     */
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_add_group: {
                    Toast.makeText(getApplicationContext(), "addGroup", Toast.LENGTH_SHORT).show();
//                    showDialog();
                    AddGroupDialog addGroupDialog = new AddGroupDialog();
                    addGroupDialog.show(getSupportFragmentManager(), "group");
                }
                break;
            }
        }
    };

    /**
     * show dialog flexiable with others screen size
     */
    public void showDialog() {
        boolean isLargeLayout = getResources().getBoolean(R.bool.large_layout);
        FragmentManager fragmentManager = getSupportFragmentManager();
        AddGroupDialog newFragment = new AddGroupDialog();

        if (isLargeLayout) {
            // The device is using a large layout, so show the fragment as a dialog
            newFragment.show(fragmentManager, "group");
        } else {
            // The device is smaller, so show the fragment fullscreen
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            // For a little polish, specify a transition animation
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // To make it fullscreen, use the 'content' root view as the container
            // for the fragment, which is always the root view for the activity
            transaction.add(android.R.id.content, newFragment)
                    .addToBackStack(null).commit();
        }

    }

    //data from dialog addGroup
    @Override
    public void onFinishEditDialog(String inputText) {
        Toast.makeText(getApplicationContext(), inputText,Toast.LENGTH_SHORT).show();
        GroupPeole groupPeole = new GroupPeole(inputText);
        groupPeopleViewModel.insertGroupPeople(groupPeole);
    }
}
