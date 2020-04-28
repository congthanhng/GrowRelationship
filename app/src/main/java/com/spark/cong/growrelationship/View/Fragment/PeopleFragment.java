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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spark.cong.growrelationship.Architecture.Entity.People;
import com.spark.cong.growrelationship.Architecture.ViewModel.PeopleViewModel;
import com.spark.cong.growrelationship.Commons.CallView;
import com.spark.cong.growrelationship.Commons.ItemClickListener;
import com.spark.cong.growrelationship.Commons.ItemLongClickListener;
import com.spark.cong.growrelationship.Commons.ItemSpacingDecorator;
import com.spark.cong.growrelationship.Commons.impl.CallViewImpl;
import com.spark.cong.growrelationship.Commons.impl.CommonImpl;
import com.spark.cong.growrelationship.R;
import com.spark.cong.growrelationship.View.Activity.PeopleActivity;
import com.spark.cong.growrelationship.View.Adapter.PeopleRecyclerAdapter;
import com.spark.cong.growrelationship.View.Dialog.AddGroupDialog;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.spark.cong.growrelationship.Commons.Constant.INTENT_TO_PEOPLE;
import static com.spark.cong.growrelationship.Commons.Constant.ITEM_SPACING;
import static com.spark.cong.growrelationship.Commons.Constant.REQUEST_CODE_PEOPLE;
import static com.spark.cong.growrelationship.Commons.Constant.TAG_ITEM_PEOPLE;


public class PeopleFragment extends Fragment implements View.OnClickListener, ItemClickListener, ItemLongClickListener, AddGroupDialog.EditNameGroupListener {

    private CallView callView = CallViewImpl.getInstance();

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private CommonImpl mCommon = CommonImpl.getInstance();
    private PeopleViewModel mViewModel;
    private RecyclerView recyclerView;
    private PeopleRecyclerAdapter adapter;
    private List<People> mLstPeople;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PeopleFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PeopleFragment newInstance(int columnCount) {
        PeopleFragment fragment = new PeopleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people, container, false);

        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.recycler_people);

        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        //item spacing
        recyclerView.addItemDecoration(new ItemSpacingDecorator(ITEM_SPACING, 1));
        adapter = new PeopleRecyclerAdapter(view.getContext(), this, this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //ViewModel
        mViewModel = new ViewModelProvider(this).get(PeopleViewModel.class);

        //getAllPeople
        mViewModel.getAllPeople().observe(getViewLifecycleOwner(), new Observer<List<People>>() {
            @Override
            public void onChanged(List<People> peoples) {
                if (peoples != null) {
                    adapter.setData(peoples);
                    mLstPeople = peoples;
                } else {
                    Toast.makeText(getContext(), "Please create a new ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    //item click listener of recycler and bottomSheet
    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {

            case R.id.action_delete: {
                deletePeople(position);
            }
            break;
            case R.id.action_open:
            default: {
                callPeopleActivity(position);
            }
        }
    }

    //func delete a people
    private void deletePeople(final int position) {
        final People people = mLstPeople.get(position);
        compositeDisposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mViewModel.deletePeople(people);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Toast.makeText(getContext(), "Delete successful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), "Delete fail", Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    @Override
    public void onItemLongClick(View view, int position) {
        callView.callBottomSheet(getActivity().getSupportFragmentManager(), TAG_ITEM_PEOPLE, position, true, this);
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
    }


    @Override
    public void onFinishEditDialog(String inputText) {
        final People people = new People(inputText);
        compositeDisposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mViewModel.insertPeople(people);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Toast.makeText(getContext(), "Insert successful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), "Insert fail", Toast.LENGTH_SHORT).show();

                    }
                }));
    }

    //show dialog add new people when fab in tab 2 click
    public void addNewPeople() {
        AddGroupDialog addGroupDialog = new AddGroupDialog(this);
        addGroupDialog.show(getActivity().getSupportFragmentManager(), "people");
    }

    //go to PeopleActivity when click a item
    public void callPeopleActivity(int position) {
        int peopleId = mLstPeople.get(position).getPeopleId();
        Intent intent = new Intent(getActivity(), PeopleActivity.class);
        intent.putExtra(INTENT_TO_PEOPLE, peopleId);
        startActivityForResult(intent, REQUEST_CODE_PEOPLE);
    }
}
