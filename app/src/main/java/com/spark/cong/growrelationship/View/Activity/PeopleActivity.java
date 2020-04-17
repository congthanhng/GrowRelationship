package com.spark.cong.growrelationship.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.spark.cong.growrelationship.View.Adapter.PeopleRecyclerAdapter;
import com.spark.cong.growrelationship.Architecture.Entity.People;
import com.spark.cong.growrelationship.Architecture.ViewModel.PeopleViewModel;
import com.spark.cong.growrelationship.Commons.ItemSpacingDecorator;
import com.spark.cong.growrelationship.R;

import java.util.List;

import static com.spark.cong.growrelationship.Commons.Constant.*;

public class PeopleActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnAddPeople;
    private TextInputEditText edtAddPeople;
    private PeopleViewModel peopleViewModel;
    private RecyclerView recyclerView;

//    private int groupId;
//    private List<Group> lstPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
//        Intent intent = getIntent();
//        Bundle bundle = intent.getBundleExtra(INTENT_MAIN_TO_PEOPLE);
//        this.groupId = bundle.getInt(BUNDLE_MAIN_TO_PEOPLE);
        //set and map View
        setMapView();
        setListener();
    }

    /**
     * listener when click back home
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * init, set and map view
     */
    public void setMapView(){
        //set action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_people);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        final PeopleRecyclerAdapter adapter = new PeopleRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new ItemSpacingDecorator(ITEM_SPACING,1));

        //button
        btnAddPeople = (Button)findViewById(R.id.button_test_people);
        edtAddPeople = (TextInputEditText) findViewById(R.id.edt_test_people);

        //viewModel
        peopleViewModel = new ViewModelProvider(this).get(PeopleViewModel.class);
        //observe data of ViewModel
        peopleViewModel.getAllPeople().observe(this, new Observer<List<People>>() {
            @Override
            public void onChanged(List<People> people) {
                adapter.setData(people);
            }
        });

    }


    //listener
    public void setListener(){
        //button
        btnAddPeople.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_test_people :{
                String textToInsert = edtAddPeople.getText().toString();
                if(!TextUtils.isEmpty(edtAddPeople.getText().toString())){
                    People people = new People(textToInsert);
                    peopleViewModel.insertPeople(people);
                    edtAddPeople.setText("");
                    closeKeyBoard();
                }else{
                    Toast.makeText(this,"Please input your text before add",Toast.LENGTH_SHORT).show();
                }
            }break;
        }
    }

    public void closeKeyBoard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}
