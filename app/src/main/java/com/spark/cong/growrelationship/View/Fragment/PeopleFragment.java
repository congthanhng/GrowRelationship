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
import com.spark.cong.growrelationship.View.Activity.GroupPeopleActivity;
import com.spark.cong.growrelationship.View.Activity.PeopleActivity;
import com.spark.cong.growrelationship.View.Adapter.PeopleRecyclerAdapter;
import com.spark.cong.growrelationship.View.Dialog.AddGroupDialog;
import com.spark.cong.growrelationship.View.Dummy.DummyContent.DummyItem;

import java.util.List;

import static com.spark.cong.growrelationship.Commons.Constant.INTENT_TO_PEOPLE;
import static com.spark.cong.growrelationship.Commons.Constant.ITEM_SPACING;
import static com.spark.cong.growrelationship.Commons.Constant.REQUEST_CODE_PEOPLE;
import static com.spark.cong.growrelationship.Commons.Constant.TAG_ITEM_PEOPLE;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PeopleFragment extends Fragment implements View.OnClickListener, ItemClickListener, ItemLongClickListener,AddGroupDialog.EditNameGroupListener {

    private CallView callView = CallViewImpl.getInstance();

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private CommonImpl mCommon = CommonImpl.getInstance();
//    private Button button;
//    private TextInputEditText editText;
    private PeopleViewModel mViewModel;
    private RecyclerView recyclerView;
    private PeopleRecyclerAdapter adapter;
    private List<People> mLstPeople;

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

        // Set the adapter
        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_people);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        //item spacing
        recyclerView.addItemDecoration(new ItemSpacingDecorator(ITEM_SPACING,1));
        adapter = new PeopleRecyclerAdapter(view.getContext(),this,this);

        //recyclerView.setAdapter(new PeopleRecyclerAdapter(DummyContent.ITEMS, mListener));
        recyclerView.setAdapter(adapter);

//        button = (Button)view.findViewById(R.id.button_test_people);
//        editText= (TextInputEditText) view.findViewById(R.id.edt_test_people);
//        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PeopleViewModel.class);

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
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

    }

    //item click listener. Go to people activity
    @Override
    public void onItemClick(View view, int position) {
    //Toast.makeText(getContext(),"item"+position,Toast.LENGTH_SHORT).show();
        switch (view.getId()){
            case R.id.action_open:{
                callPeopleActivity(position);
            }break;
            default: {
                callPeopleActivity(position);
            }
        }
    }

    @Override
    public void onItemLongClick(View view, int position) {
//        Toast.makeText(getContext(),"longItem"+position,Toast.LENGTH_SHORT).show();
        callView.callBottomSheet(getActivity().getSupportFragmentManager(),TAG_ITEM_PEOPLE,position,true,this);
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
//        bottomSheetFragment.show(getActivity().getSupportFragmentManager(),"people_bottom_sheet");

    }

    @Override
    public void onFinishEditDialog(String inputText) {
        People people = new People(inputText);
        mViewModel.insertPeople(people);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }

    public void addNewPeople(){
        AddGroupDialog addGroupDialog = new AddGroupDialog(this);
        addGroupDialog.show(getActivity().getSupportFragmentManager(), "people");
    }

    public void callPeopleActivity(int position){
        int peopleId = mLstPeople.get(position).getPeopleId();
        Intent intent = new Intent(getActivity(), PeopleActivity.class);
        intent.putExtra(INTENT_TO_PEOPLE,peopleId);
        startActivityForResult(intent,REQUEST_CODE_PEOPLE);
    }
}
