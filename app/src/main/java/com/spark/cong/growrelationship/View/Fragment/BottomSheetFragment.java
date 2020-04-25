package com.spark.cong.growrelationship.View.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.spark.cong.growrelationship.Commons.ItemClickListener;
import com.spark.cong.growrelationship.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BottomSheetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    private LinearLayout mOpen,mEdit,mDelete;
    private ItemClickListener mItemListener;
    private boolean mIsFragment = false; // default false if is activity, true is fragment
    private int mPositon;

    /*-----------------------constructor---------------------------------*/
    //emty
    public BottomSheetFragment() {
        // Required empty public constructor
    }

    //constructor created by activity
    public BottomSheetFragment(int positon) {
        this.mPositon = positon;
        // Required empty public constructor
    }

    //constructor created by fragment with isFragment alway is true
    public BottomSheetFragment(int position, boolean isFragment, ItemClickListener listener) {
        this.mPositon = position;
        this.mIsFragment = isFragment;
        this.mItemListener = listener;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GroupBottomSheetFragement.
     */
    // TODO: Rename and change types and number of parameters
//    public static GroupBottomSheetFragement newInstance(String param1, String param2) {
//        GroupBottomSheetFragement fragment = new GroupBottomSheetFragement();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText(R.string.hello_blank_fragment);
        View v = inflater.inflate(R.layout.fragment_bottom_sheet_dialog_group,container,false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setView(view);
        setListener();
    }

    public void setView(View view){
        mOpen = (LinearLayout) view.findViewById(R.id.action_open);
        mEdit = (LinearLayout) view.findViewById(R.id.action_edit);
        mDelete=(LinearLayout)view.findViewById(R.id.action_delete);
    }

    public void setListener(){
        mOpen.setOnClickListener(this);
        mEdit.setOnClickListener(this);
        mDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_delete:{
            }
            case R.id.action_open: {
                //if not fragment contain this bottomSheet (activity contain this)
                //then you should get a cast to same type listener of activity(activity implement listener of this)
                if(!mIsFragment){
                    mItemListener = (ItemClickListener)getActivity();
                    mItemListener.onItemClick(v,mPositon);
                }else{
                    mItemListener.onItemClick(v,mPositon);
                }
                dismiss();
            }break;

            case R.id.action_edit:{
                Toast.makeText(getContext(),"Edit",Toast.LENGTH_SHORT).show();
                dismiss();

            }break;

        }
    }
}
