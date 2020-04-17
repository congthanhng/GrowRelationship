package com.spark.cong.growrelationship.View.Dialog;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.spark.cong.growrelationship.Architecture.Entity.Group;
import com.spark.cong.growrelationship.R;

public class EditGroupDialog extends DialogFragment implements View.OnClickListener{
    private ImageButton btnCloseEditGroup;
    private Button btnSaveEditGroup;
    private EditText edtChangeName;
    private Group group;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!= null){
            group = (Group) getArguments().getSerializable("edit_group");
            Log.i("TAG", "onCreate: getArgument"+ group.getGroupName());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_change_name_group,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //map view
        btnCloseEditGroup = (ImageButton) view.findViewById(R.id.btn_close_edit_group);
        btnSaveEditGroup = (Button) view.findViewById(R.id.btn_save_edit_group);
        edtChangeName = (EditText) view.findViewById(R.id.edt_edit_name_group);

        //init value
        edtChangeName.setText(group.getGroupName());

        //Listener
        btnSaveEditGroup.setOnClickListener(this);
        btnCloseEditGroup.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        //user to set width and height of dialog
        int getScreenWidth = Resources.getSystem().getDisplayMetrics().widthPixels; //get sreen width
        Window window = getDialog().getWindow();
        if (window == null) return;
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = getScreenWidth - (getScreenWidth / 6);
//        params.height = 600;
        window.setAttributes(params);
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_close_edit_group : { dismiss();}break;
            case R.id.btn_save_edit_group : {
                String change = edtChangeName.getText().toString();
                if(!TextUtils.isEmpty(edtChangeName.getText().toString())){
                    EditGroupListener listener = (EditGroupListener) getActivity();
                    listener.onFinnishEdit(group,change);
                    dismiss();
                }else{
                    Toast.makeText(getContext(),"Don't emty",Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            }break;
        }
    }

    public interface EditGroupListener {
        public void onFinnishEdit(Group group, String change);
    }
}
