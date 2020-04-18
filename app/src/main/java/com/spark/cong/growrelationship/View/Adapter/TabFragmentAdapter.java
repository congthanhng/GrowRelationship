package com.spark.cong.growrelationship.View.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabFragmentAdapter extends FragmentStateAdapter {

    private List<Fragment> lstFragment = new ArrayList<>();
    public TabFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public TabFragmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public TabFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return lstFragment.get(position);
    }

    @Override
    public int getItemCount() {
        return lstFragment.size();
    }

    public void addFragment(Fragment fragment){
        lstFragment.add(fragment);
    }

}
