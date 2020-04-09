package com.spark.cong.growrelationship;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spark.cong.growrelationship.Activity.PeopleActivity;
import com.spark.cong.growrelationship.Adapter.GroupPeopleRecyclerAdapter;
import com.spark.cong.growrelationship.Architecture.Entity.GroupPeole;
import com.spark.cong.growrelationship.Architecture.ViewModel.GroupPeopleViewModel;
import com.spark.cong.growrelationship.Commons.ItemClickListener;
import com.spark.cong.growrelationship.Commons.ItemSpacingDecorator;
import com.spark.cong.growrelationship.Dialog.AddGroupDialog;
import com.spark.cong.growrelationship.Dialog.ConfirmDeleteGroupDialog;

import java.util.List;

import static com.spark.cong.growrelationship.Commons.Constant.ITEM_SPACING;
import static com.spark.cong.growrelationship.Commons.Constant.REQUEST_CODE_PEOPLE;
import static com.spark.cong.growrelationship.Commons.Constant.SPAN_COUNT;

public class MainActivity extends AppCompatActivity implements AddGroupDialog.EditNameGroupListener, ItemClickListener {
    private RecyclerView recyclerView;
    private GroupPeopleViewModel groupPeopleViewModel;
    private Button btnAddGroup;
    private List<GroupPeole> listGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init and map view
        mapView();

        //listener
        mapListener();

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
        final GroupPeopleRecyclerAdapter adapter = new GroupPeopleRecyclerAdapter(this,this);
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
                listGroup = groupPeoles;
            }
        });
    }

    /**
     * init, set listener of Views
     */
    public void mapListener() {
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

    @Override
    public void onClick(View view,final int position) {
        switch (view.getId()){
            case R.id.btn_delete_group : {
//                Toast.makeText(getApplicationContext(),"do you want to delete?",Toast.LENGTH_SHORT).show();
//                ConfirmDeleteGroupDialog confirmDeleteGroupDialog = new ConfirmDeleteGroupDialog();
//                confirmDeleteGroupDialog.show(getSupportFragmentManager(), "confirm_delete");
                new AlertDialog.Builder(this)
                        .setMessage(R.string.confirm_delete_group)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                groupPeopleViewModel.deleteGroupById(listGroup.get(position).getId());
                            }
                        }).setNegativeButton(R.string.no,null).show();
//                groupPeopleViewModel.deleteGroupById(listGroup.get(position).getId());
            }break;
            case R.id.btn_edit_group:{
//                new Dialog(this)
//                        .setContentView(R.layout.dialog_add_group);
                Toast.makeText(getApplicationContext(),"do you want to dedit?",Toast.LENGTH_SHORT).show();
            }break;
            default: {
                Log.d("TEST", "onClick: clicked" + position);
                Intent intent = new Intent(this, PeopleActivity.class);
                startActivityForResult(intent, REQUEST_CODE_PEOPLE);
            }
        }

        /*Log.d("TEST", "onClick: clicked" + position);
        Intent intent = new Intent(this, PeopleActivity.class);
        startActivityForResult(intent, REQUEST_CODE_PEOPLE);*/
    }

    @Override
    public void onLongClick(View view, int position) {

        Log.d("TEST", "onLongClick: clicked" + position);

    }
}
