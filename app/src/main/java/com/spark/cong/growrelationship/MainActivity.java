package com.spark.cong.growrelationship;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.spark.cong.growrelationship.View.Activity.GroupPeopleActivity;
import com.spark.cong.growrelationship.Architecture.Entity.Group;
import com.spark.cong.growrelationship.Architecture.ViewModel.GroupViewModel;
import com.spark.cong.growrelationship.Commons.GroupItemClickListener;
import com.spark.cong.growrelationship.View.Adapter.TabFragmentAdapter;
import com.spark.cong.growrelationship.View.Dialog.AddGroupDialog;
import com.spark.cong.growrelationship.View.Dialog.EditGroupDialog;
import com.spark.cong.growrelationship.View.Fragment.GroupFragment;
import com.spark.cong.growrelationship.View.Fragment.InteractiveFragment;
import com.spark.cong.growrelationship.View.Fragment.PeopleFragment;
import com.spark.cong.growrelationship.View.Dummy.DummyContent;

import java.util.List;

import static com.spark.cong.growrelationship.Commons.Constant.*;


public class MainActivity extends AppCompatActivity implements AddGroupDialog.EditNameGroupListener, GroupItemClickListener, EditGroupDialog.EditGroupListener, PeopleFragment.OnListFragmentInteractionListener {
    private RecyclerView recyclerView;
    private GroupViewModel groupViewModel;
    private Button btnAddGroup;
    private List<Group> listGroup;
    private TabLayout mTabs;
    private ViewPager2 mViewpaper2;
    private TabFragmentAdapter tabAdapter;

    //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init, set and map view
        setMapView();

        //listener
        setListener();

    }

    /**
     * init, set and map view
     */
    public void setMapView() {

        //tablayout
        mTabs = (TabLayout) findViewById(R.id.tab_main);
        //viewPaper
        mViewpaper2 = (ViewPager2) findViewById(R.id.viewpapter_of_tab);
        //init adapter for tab ViewPaper
        tabAdapter = new TabFragmentAdapter(this);

        //add Fragment to tab
        addFragmentToViewPaper();
        mViewpaper2.setAdapter(tabAdapter);
        //link Tablayout to ViewPaper2
        new TabLayoutMediator(mTabs, mViewpaper2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText("tab" + position);
            }
        }).attach();


        // button add
//        btnAddGroup = (Button) findViewById(R.id.btn_add_group);

        //RecyclerView init
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewGroup);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, SPAN_COUNT);
//        final GroupRecyclerAdapter adapter = new GroupRecyclerAdapter(this, this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
//        recyclerView.hasFixedSize();
//        recyclerView.addItemDecoration(new ItemSpacingDecorator(ITEM_SPACING, SPAN_COUNT)); // spacing between items

        //init viewModel
//        groupViewModel = new ViewModelProvider(this).get(GroupViewModel.class);

        //LiveData
//        groupViewModel.getAllGroup().observe(this, new Observer<List<Group>>() {
//            @Override
//            public void onChanged(List<Group> groups) {
//                adapter.setData(groups);
//                listGroup = groups;
//            }
//        });
    }

    /**
     * add fragments to viewPaper
     */
    public void addFragmentToViewPaper(){
        tabAdapter.addFragment(InteractiveFragment.newInstance("ok","nothing"));
        tabAdapter.addFragment(GroupFragment.newInstance());
        tabAdapter.addFragment(PeopleFragment.newInstance(1));
    }


    /**
     * init, set listener of Views
     */
    public void setListener() {
        // add group button listener
//        btnAddGroup.setOnClickListener(mOnClickListener);

        //set listener when item tab selected
        mViewpaper2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
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
        Toast.makeText(getApplicationContext(), inputText, Toast.LENGTH_SHORT).show();
        Group group = new Group(inputText);
        groupViewModel.insertGroup(group);
    }

    @Override
    public void onClick(View view, final int position) {
        switch (view.getId()) {
            case R.id.btn_delete_group: {
//                Toast.makeText(getApplicationContext(),"do you want to delete?",Toast.LENGTH_SHORT).show();
//                ConfirmDeleteGroupDialog confirmDeleteGroupDialog = new ConfirmDeleteGroupDialog();
//                confirmDeleteGroupDialog.show(getSupportFragmentManager(), "confirm_delete");
                new AlertDialog.Builder(this)
                        .setMessage(R.string.confirm_delete_group)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                groupViewModel.deleteGroupById(listGroup.get(position).getGroupId());
                            }
                        }).setNegativeButton(R.string.no, null).show();
//                GroupViewModel.deleteGroupById(listGroup.get(position).getId());
            }
            break;
            case R.id.btn_edit_group: {
                /*final Dialog dialog = new Dialog(getApplicationContext());
                dialog.setContentView(R.layout.dialog_add_group);
                Button btnSaveEdit = (Button) dialog.findViewById(R.id.btn_save_add_group);
                final EditText edtEditChange = (EditText)dialog.findViewById(R.id.edt_input_name_group);
                ImageButton btnCloseEdit = (ImageButton)dialog.findViewById(R.id.btn_close_add_group);
                dialog.show();
                btnSaveEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!TextUtils.isEmpty(edtEditChange.getText().toString())){
                            GroupPeole groupPeole = listGroup.get(position);
                            groupPeole.setName(edtEditChange.getText().toString());
                            GroupViewModel.updateGroup(groupPeole);
                        }else dialog.dismiss();
                    }
                });
                btnCloseEdit.setOnClickListener(new View.OnClickListener() {
                    @Override-
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });*/


//                Toast.makeText(getApplicationContext(), "do you want to dedit?", Toast.LENGTH_SHORT).show();
                Group group = listGroup.get(position);
                EditGroupDialog editGroupDialog = new EditGroupDialog();
                Bundle editGroupBundle = new Bundle();
                editGroupBundle.putSerializable("edit_group",group);
                editGroupDialog.setArguments(editGroupBundle);
                editGroupDialog.show(getSupportFragmentManager(),"edit_group");
            }
            break;
            default: {
                Log.d("TEST", "onClick: clicked" + position);
                Intent intent = new Intent(this, GroupPeopleActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra(INTENT_MAIN_TO_PEOPLE,bundle);
                bundle.putInt(BUNDLE_MAIN_TO_PEOPLE,listGroup.get(position).getGroupId());
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

    @Override
    public void onFinnishEdit(Group group, String change) {
        group.setGroupName(change);
        groupViewModel.updateGroup(group);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Toast.makeText(getApplicationContext(),""+item.content.toString(),Toast.LENGTH_SHORT).show();
    }
}
