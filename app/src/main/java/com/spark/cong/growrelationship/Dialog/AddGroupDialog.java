package com.spark.cong.growrelationship.Dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.spark.cong.growrelationship.MainActivity;
import com.spark.cong.growrelationship.R;

public class AddGroupDialog extends DialogFragment implements View.OnClickListener{
    private ImageButton btnCloseAddGroup;
    private Button btnSaveAddGroup;
    private EditText edtNameGroup;
    private Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_add_group,container,false);
        //set and map view
        btnCloseAddGroup = (ImageButton) v.findViewById(R.id.btn_close_add_group);
        btnSaveAddGroup = (Button) v.findViewById(R.id.btn_save_add_group);
        edtNameGroup = (EditText) v.findViewById(R.id.edt_input_name_group);

        //listener
        btnCloseAddGroup.setOnClickListener(this);
        btnSaveAddGroup.setOnClickListener(this);
        return v;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        /*AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_add_group,null));*/

        dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_close_add_group :{
                dialog.cancel();
            }break;
            case R.id.btn_save_add_group:{
                Toast.makeText(getContext(),"save new item",Toast.LENGTH_SHORT).show();
            }break;
        }
    }
}
