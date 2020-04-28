package com.spark.cong.growrelationship;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.spark.cong.growrelationship.Architecture.Entity.Group;
import com.spark.cong.growrelationship.Architecture.ViewModel.GroupViewModel;
import com.spark.cong.growrelationship.View.Adapter.TabFragmentAdapter;
import com.spark.cong.growrelationship.View.Dialog.EditGroupDialog;
import com.spark.cong.growrelationship.View.Dummy.DummyContent;
import com.spark.cong.growrelationship.View.Fragment.GroupFragment;
import com.spark.cong.growrelationship.View.Fragment.InteractiveFragment;
import com.spark.cong.growrelationship.View.Fragment.PeopleFragment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static com.spark.cong.growrelationship.Commons.Constant.POPUP_CONSTANT;
import static com.spark.cong.growrelationship.Commons.Constant.POPUP_FORCE_SHOW_ICON;
import static com.spark.cong.growrelationship.Commons.Constant.TAB_SIZE;
import static com.spark.cong.growrelationship.Commons.Constant.fabIconArray;
import static com.spark.cong.growrelationship.Commons.Constant.tabSelected;
import static com.spark.cong.growrelationship.Commons.Constant.tabUnSelected;


public class MainActivity extends AppCompatActivity implements  PopupMenu.OnMenuItemClickListener, View.OnClickListener {
    private TabLayout mTabs;
    private ViewPager2 mViewpaper2;
    private TabFragmentAdapter tabAdapter;
    private FloatingActionButton fab;
    private int mPosition = 0;

    //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init, set and map view
        setView();

        //listener
        setListener();

    }

    /**
     * init, set and map view
     */
    public void setView() {

        //fab
        fab = (FloatingActionButton) findViewById(R.id.fab_add);

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
        //fab
        fab.setOnClickListener(this);

        //set listener when item tab selected
        mViewpaper2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mPosition = position;
                //set icon when tab is selected or not+
                for (int i = 0; i < TAB_SIZE; i++) {
                    if (i == position) {
                        mTabs.getTabAt(i).setIcon(tabSelected[i]);
                        animateFab(i);
                    } else {
                        mTabs.getTabAt(i).setIcon(tabUnSelected[i]);
                    }
                }
            }
        });

    }

    /**
     * add fragments to viewPaper
     */
    public void addFragmentToViewPaper() {
        InteractiveFragment interactiveFragment = InteractiveFragment.newInstance("ok", "nothing");
        PeopleFragment peopleFragment = PeopleFragment.newInstance(1);
        GroupFragment groupFragment = GroupFragment.newInstance();
        tabAdapter.addFragment(interactiveFragment);
        tabAdapter.addFragment(peopleFragment);
        tabAdapter.addFragment(groupFragment);
    }

    //show popupMenu when click avatar account (config listener in layout xml)
    public void showPopup(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
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

    //menu item listener
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account_profile: {
                Toast.makeText(getApplicationContext(), "Your profile", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.settings: {
                Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.support_help: {
                Toast.makeText(getApplicationContext(), "Help", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.about_us: {
                Toast.makeText(getApplicationContext(), "About us", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.account_logout: {
                Toast.makeText(getApplicationContext(), "Log out", Toast.LENGTH_SHORT).show();
            }
            break;
        }
        return false;
    }

    //animation change icon when tab change when selected
    protected void animateFab(final int position) {
        fab.clearAnimation();
        // Scale down animation
        ScaleAnimation shrink = new ScaleAnimation(1f, 0.2f, 1f, 0.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        shrink.setDuration(150);     // animation duration in milliseconds
        shrink.setInterpolator(new DecelerateInterpolator());
        shrink.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                // Change FAB color and icon
//                fab.setBackgroundTintList(getResources().getColorStateList(colorIntArray[position]));
                fab.setImageDrawable(getResources().getDrawable(fabIconArray[position], null));

                // Scale up animation
                ScaleAnimation expand = new ScaleAnimation(0.2f, 1f, 0.2f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                expand.setDuration(100);     // animation duration in milliseconds
                expand.setInterpolator(new AccelerateInterpolator());
                fab.startAnimation(expand);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        fab.startAnimation(shrink);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add: {
                switch (mPosition) {
                    case 0: {
                        Toast.makeText(getApplicationContext(), "tab" + (mPosition + 1), Toast.LENGTH_SHORT).show();
                    }
                    break;
                    case 1: {
                        Toast.makeText(getApplicationContext(), "tab" + (mPosition + 1), Toast.LENGTH_SHORT).show();
                        fabPeopleListener();
                    }
                    break;
                    case 2: {
                        Toast.makeText(getApplicationContext(), "tab" + (mPosition + 1), Toast.LENGTH_SHORT).show();
                        fabGroupListener();
                    }
                    break;
                }
            }
            break;
        }
    }

    public void fabGroupListener() {
        try {
            GroupFragment groupFragment = (GroupFragment) tabAdapter.getLstFragment().get(2);

            if (groupFragment != null) {
                groupFragment.addNewGroup();
            }
        } catch (Exception e) {
            Log.e("Exeption", "fabGroupListener: " + e);
        }

//        if(groupFragment !=null){
//            groupFragment.addNewGroup();
//        }
    }
    public void fabPeopleListener(){
        try {
            PeopleFragment fragment = (PeopleFragment) tabAdapter.getLstFragment().get(1);
            if(fragment !=null){
                fragment.addNewPeople();
            }
        }catch (Exception e){
            Log.e("Exeption", "fabPeopleListener: " + e);

        }
    }
}
