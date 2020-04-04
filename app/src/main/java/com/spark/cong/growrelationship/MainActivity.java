package com.spark.cong.growrelationship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.spark.cong.growrelationship.Adapter.RecyclerViewAdapter;
import com.spark.cong.growrelationship.Architecture.Entity.User;
import com.spark.cong.growrelationship.Architecture.ViewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserViewModel mUserViewModel;
    private RecyclerView recyclerView;
    private Button button,buttonDelete;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        buttonDelete= (Button) findViewById(R.id.buttonDelete);
        editText = (EditText) findViewById(R.id.editText);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_main);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter();
//        adapter.setLstName(lstName());
        recyclerView.setAdapter(adapter);

        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        mUserViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.setLstName(users);
            }
        });
        button.setOnClickListener(mOnClickListener);
        buttonDelete.setOnClickListener(mOnClickListener);
    }

    public View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button: {
                    String stringName = editText.getText().toString();
                    if (TextUtils.isEmpty(editText.getText())) {
                        Toast.makeText(getApplicationContext(), "Please input data before click add button", Toast.LENGTH_SHORT).show();
                    } else {
                        User user = new User(stringName);
                        mUserViewModel.insertUser(user);
                    }
                    editText.setText(null);
                } break;
                case R.id.buttonDelete: {
                    mUserViewModel.deleteAlluser();
                } break;
            }
        }
    };

    public ArrayList<String> lstName() {
        ArrayList<String> lstString = new ArrayList<>();
        lstString.add("Family");
        lstString.add("Friends");
        lstString.add("Works");
        lstString.add("Clubs");
        lstString.add("+");
        return lstString;
    }
}
