package com.spark.cong.growrelationship.View.Dialog;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.spark.cong.growrelationship.R;

public class AddGroupDialog extends DialogFragment implements View.OnClickListener {
    private ImageButton btnCloseAddGroup;
    private Button btnSaveAddGroup;
    private EditText edtNameGroup;
    private Dialog dialog;
    private EditNameGroupListener listener;

    public AddGroupDialog(EditNameGroupListener listener){
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_group, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //set and map view
        btnCloseAddGroup = (ImageButton) view.findViewById(R.id.btn_close_add_group);
        btnSaveAddGroup = (Button) view.findViewById(R.id.btn_save_add_group);
        edtNameGroup = (EditText) view.findViewById(R.id.edt_input_name_group);


        //listener
        btnCloseAddGroup.setOnClickListener(this);
        btnSaveAddGroup.setOnClickListener(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        /*AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_add_group,null));*/

        dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //dialog.getContext().getTheme().applyStyle(R.style.MyAlertDialog, true);

        dialog.setCanceledOnTouchOutside(false);// prevent close dialog when touch outside

        //set the background of the dialog's root view.
        // because Android puts your dialog layout within a root view that hides the corners in your custom layout
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    // 1. Defines the listener interface with a method passing back data result.
    public interface EditNameGroupListener{
        void onFinishEditDialog(String inputText);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close_add_group: {
                dismiss();
            }
            break;
            case R.id.btn_save_add_group: {
//                Toast.makeText(getContext(), "save new item", Toast.LENGTH_SHORT).show();
                String nameGroup = edtNameGroup.getText().toString();
                if(!TextUtils.isEmpty(edtNameGroup.getText())){
                    // Return input text back to activity through the implemented listener
                    listener.onFinishEditDialog(nameGroup);
                }
                dismiss();
            }
            break;
        }
    }

    @Override
    public void onResume() {
        //user to set width and height of dialog
        int getScreenWidth = Resources.getSystem().getDisplayMetrics().widthPixels; //get sreen width
        Window window = getDialog().getWindow();
        if (window == null) return;
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = getScreenWidth - (getScreenWidth / 6);
        window.setAttributes(params);
        super.onResume();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("fragmentLy", "onCreate: ok");
    }
}
