package com.spark.cong.growrelationship;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
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
import com.spark.cong.growrelationship.Commons.GroupListener;
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


public class MainActivity extends AppCompatActivity implements  EditGroupDialog.EditGroupListener, PeopleFragment.OnListFragmentInteractionListener, GroupListener {
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
//                tab.setIcon(tabUnSelected[position]);
            }
        }).attach();


    }
    /**
     * init, set listener of Views
     */
    public void setListener() {

        //set listener when item tab selected
        mViewpaper2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //set icon when tab is selected or not+
                for (int i = 0; i < TAB_SIZE; i++){
                    if(i == position){
                        mTabs.getTabAt(i).setIcon(tabSelected[i]);
                    }else {
                        mTabs.getTabAt(i).setIcon(tabUnSelected[i]);
                    }
                }
            }
        });

    }

    /**
     * add fragments to viewPaper
     */
    public void addFragmentToViewPaper(){
        tabAdapter.addFragment(InteractiveFragment.newInstance("ok","nothing"));
        tabAdapter.addFragment(PeopleFragment.newInstance(1));
        tabAdapter.addFragment(GroupFragment.newInstance());
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
//                    AddGroupDialog addGroupDialog = new AddGroupDialog(this);
//                    addGroupDialog.show(getSupportFragmentManager(), "group");
                }
                break;
            }
        }
    };

    //data from dialog addGroup
//    @Override
//    public void onFinishEditDialog(String inputText) {
//        Toast.makeText(getApplicationContext(), inputText, Toast.LENGTH_SHORT).show();
//        Group group = new Group(inputText);
//        groupViewModel.insertGroup(group);
//    }

//    @Override
//    public void onClick(View view, final int position) {
//        switch (view.getId()) {
//            case R.id.btn_delete_group: {
////
//                new AlertDialog.Builder(this)
//                        .setMessage(R.string.confirm_delete_group)
//                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                groupViewModel.deleteGroupById(listGroup.get(position).getGroupId());
//                            }
//                        }).setNegativeButton(R.string.no, null).show();
////                GroupViewModel.deleteGroupById(listGroup.get(position).getId());
//            }
//            break;
//            case R.id.btn_edit_group: {
//
////                Toast.makeText(getApplicationContext(), "do you want to dedit?", Toast.LENGTH_SHORT).show();
//                Group group = listGroup.get(position);
//                EditGroupDialog editGroupDialog = new EditGroupDialog();
//                Bundle editGroupBundle = new Bundle();
//                editGroupBundle.putSerializable("edit_group",group);
//                editGroupDialog.setArguments(editGroupBundle);
//                editGroupDialog.show(getSupportFragmentManager(),"edit_group");
//            }
//            break;
//            default: {
//                Log.d("TEST", "onClick: clicked" + position);
//                Intent intent = new Intent(this, GroupPeopleActivity.class);
//                Bundle bundle = new Bundle();
//                intent.putExtra(INTENT_MAIN_TO_PEOPLE,bundle);
//                bundle.putInt(BUNDLE_MAIN_TO_PEOPLE,listGroup.get(position).getGroupId());
//                startActivityForResult(intent, REQUEST_CODE_PEOPLE);
//            }
//        }
//
//        /*Log.d("TEST", "onClick: clicked" + position);
//        Intent intent = new Intent(this, PeopleActivity.class);
//        startActivityForResult(intent, REQUEST_CODE_PEOPLE);*/
//    }

//    @Override
//    public void onLongClick(View view, int position) {
//
//        Log.d("TEST", "onLongClick: clicked" + position);
//
//    }

    @Override
    public void onFinnishEdit(Group group, String change) {
        group.setGroupName(change);
        groupViewModel.updateGroup(group);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Toast.makeText(getApplicationContext(),""+item.content.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickGroupListener(View v) {
        switch (v.getId()){
            case R.id.btn_add_group:{
                Toast.makeText(getApplicationContext(), "you want to add", Toast.LENGTH_SHORT).show();
                Log.i("TESTLISTENER", "onClickGroupListener: hfghfghhf");
//                AddGroupDialog addGroupDialog = new AddGroupDialog();
//                addGroupDialog.show(getSupportFragmentManager(), "group");
            }break;
        }

    }
}
