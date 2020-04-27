package com.spark.cong.growrelationship.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spark.cong.growrelationship.Architecture.Entity.Group;
import com.spark.cong.growrelationship.Architecture.ViewModel.GroupViewModel;
import com.spark.cong.growrelationship.Commons.CallView;
import com.spark.cong.growrelationship.Commons.ItemClickListener;
import com.spark.cong.growrelationship.Commons.ItemLongClickListener;
import com.spark.cong.growrelationship.Commons.ItemSpacingDecorator;
import com.spark.cong.growrelationship.Commons.impl.CallViewImpl;
import com.spark.cong.growrelationship.R;
import com.spark.cong.growrelationship.View.Activity.GroupPeopleActivity;
import com.spark.cong.growrelationship.View.Adapter.GroupRecyclerAdapter;
import com.spark.cong.growrelationship.View.Dialog.AddGroupDialog;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.spark.cong.growrelationship.Commons.Constant.INTENT_TO_GROUP_PEOPLE;
import static com.spark.cong.growrelationship.Commons.Constant.ITEM_SPACING;
import static com.spark.cong.growrelationship.Commons.Constant.REQUEST_CODE_GROUP_PEOPLE;
import static com.spark.cong.growrelationship.Commons.Constant.SPAN_COUNT;
import static com.spark.cong.growrelationship.Commons.Constant.TAG_ITEM_GROUP;
import static com.spark.cong.growrelationship.Commons.ErrorMessage.NOT_FOUND_PEOPLE;

public class GroupFragment extends Fragment implements ItemClickListener, ItemLongClickListener, View.OnClickListener, AddGroupDialog.EditNameGroupListener {
    /*------------------------------------global common-----------------------------*/
    //callView
    private CallView callView = CallViewImpl.getInstance();

    /*------------------------------------ViewModel-----------------------------*/
    private GroupViewModel mViewModel;

    /*------------------------------------View-----------------------------*/
    private RecyclerView recyclerView;

    /*------------------------------------param-----------------------------*/
    private List<Group> listGroup;
    private GroupRecyclerAdapter adapter;
    private FragmentActivity myContext;

    /*------------------------------------RxJava-----------------------------*/
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    /*------------------------------------constructor-----------------------------*/
    public static GroupFragment newInstance() {
        GroupFragment fragment = new GroupFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);
        //recyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewGroup);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), SPAN_COUNT));
        adapter = new GroupRecyclerAdapter(view.getContext(), this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new ItemSpacingDecorator(ITEM_SPACING, SPAN_COUNT)); // spacing between items

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GroupViewModel.class);
        //LiveData
        mViewModel.getAllGroup().observe(getViewLifecycleOwner(), new Observer<List<Group>>() {
            @Override
            public void onChanged(List<Group> groups) {
                if (groups != null) {
                    adapter.setData(groups);
                    listGroup = groups;
                } else {
                    Toast.makeText(getContext(), "Please create a new group", Toast.LENGTH_SHORT).show();
                }

            }
        });
        // TODO: Use the ViewModel
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myContext = getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();

    }

    @Override
    public void onClick(View v) {
    }

    /*
    listener when click Save button in AddGroupDialog
    * */
    @Override
    public void onFinishEditDialog(String inputText) {
        //add new group
        final Group group = new Group(inputText);
        compositeDisposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mViewModel.insertGroup(group);
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        Toast.makeText(getContext(), "insert successfull", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), "insert fail", Toast.LENGTH_SHORT).show();

                    }
                }));
    }

    //item click listener
    @Override
    public void onItemClick(View view, int position) {
//        Toast.makeText(myContext,"itemClick"+position,Toast.LENGTH_SHORT).show();
        switch (view.getId()) {
            case R.id.action_open: {
                callGroupPeopleActivity(position);

            }
            break;
            case R.id.action_delete: {
                //delete a group
                deleteGroup(position);
            }
            break;
            default: {
                callGroupPeopleActivity(position);
            }
        }
    }

    //item long click listener
    @Override
    public void onItemLongClick(View view, int position) {
//        Toast.makeText(myContext,"itemLongClick"+position,Toast.LENGTH_SHORT).show();
        callView.callBottomSheet(getActivity().getSupportFragmentManager(), TAG_ITEM_GROUP, position, true, this);

    }

    //show dialog addgroup when click fab
    public void addNewGroup() {
        AddGroupDialog addGroupDialog = new AddGroupDialog(this);
        addGroupDialog.show(myContext.getSupportFragmentManager(), "group");
    }

    public void callGroupPeopleActivity(int position) {
        int groupId = listGroup.get(position).getGroupId();
        if (groupId >= 0) {
            Intent intent = new Intent(getActivity(), GroupPeopleActivity.class);
            intent.putExtra(INTENT_TO_GROUP_PEOPLE, groupId);
            startActivityForResult(intent, REQUEST_CODE_GROUP_PEOPLE);
        } else {
            throw new RuntimeException(NOT_FOUND_PEOPLE);
        }
    }

    private void deleteGroup(int position) {
        compositeDisposable.add(mViewModel.deleteGroup(listGroup.get(position))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Toast.makeText(getContext(), "Delete sucessfull", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }
}
