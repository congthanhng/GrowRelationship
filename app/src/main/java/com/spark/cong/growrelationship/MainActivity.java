package com.spark.cong.growrelationship;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.PopupMenu;
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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static com.spark.cong.growrelationship.Commons.Constant.*;


public class MainActivity extends AppCompatActivity implements  EditGroupDialog.EditGroupListener, PeopleFragment.OnListFragmentInteractionListener, PopupMenu.OnMenuItemClickListener {
    private RecyclerView recyclerView;
    private GroupViewModel groupViewModel;
    private Button btnAddGroup;
    private List<Group> listGroup;
    private TabLayout mTabs;
    private ViewPager2 mViewpaper2;
    private TabFragmentAdapter tabAdapter;
    private ImageView imgAvatarAccount;
    private FrameLayout layoutAvatarAccount;

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

    //show popupMenu when click avatar account (config listener in layout xml)
    public void showPopup(View v){
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu_avatar_account);

        //show icon with text item menu
        try {
            // Reflection apis to enforce show icon
            Field[] fields = popupMenu.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals(POPUP_CONSTANT)) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popupMenu);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod(POPUP_FORCE_SHOW_ICON, boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        popupMenu.show();

    }

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


    //menu item listener
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.account_profile:{
                Toast.makeText(getApplicationContext(),"Your profile",Toast.LENGTH_SHORT).show();
            }break;
            case R.id.settings:{
                Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
            }break;
            case R.id.support_help:{
                Toast.makeText(getApplicationContext(),"Help",Toast.LENGTH_SHORT).show();
            }break;
            case R.id.about_us:{
                Toast.makeText(getApplicationContext(),"About us",Toast.LENGTH_SHORT).show();
            }break;
            case R.id.account_logout:{
                Toast.makeText(getApplicationContext(),"Log out",Toast.LENGTH_SHORT).show();
            }break;
        }
        return false;
    }
}
