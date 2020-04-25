package com.spark.cong.growrelationship.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.spark.cong.growrelationship.Commons.ItemClickListener;
import com.spark.cong.growrelationship.Commons.ItemLongClickListener;
import com.spark.cong.growrelationship.Commons.impl.CommonImpl;
import com.spark.cong.growrelationship.View.Adapter.PeopleRecyclerAdapter;
import com.spark.cong.growrelationship.Architecture.Entity.People;
import com.spark.cong.growrelationship.Architecture.ViewModel.PeopleViewModel;
import com.spark.cong.growrelationship.Commons.ItemSpacingDecorator;
import com.spark.cong.growrelationship.R;

import java.util.List;

import static com.spark.cong.growrelationship.Commons.Constant.*;

public class PeopleActivity extends AppCompatActivity {

    private CommonImpl mCommon = CommonImpl.getInstance();
    private int mPeopleId;
    private PeopleViewModel mViewModel;
    private People mPeople;

    //test
    private EditText editText;

//    private int groupId;
//    private List<Group> lstPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        //get data had been transfer from previous activity
        if(getIntent()!=null){
            Intent intent = getIntent();
            this.mPeopleId = intent.getIntExtra(INTENT_TO_PEOPLE,-1);
        }

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
        //back to main activity
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

        //viewModel
        mViewModel = new ViewModelProvider(this).get(PeopleViewModel.class);
        editText = (EditText)findViewById(R.id.edt_test_people);

        //get data of people
        if(mPeopleId >= 0){
            mPeople = mViewModel.getPeopleById(mPeopleId);
            if(mPeople != null){
                editText.setText(mPeople.getPeopleName());

            }else{
                Toast.makeText(getApplicationContext(),"null",Toast.LENGTH_SHORT).show();

            }
        }else{
            Toast.makeText(getApplicationContext(),"Please check your data transfer,",Toast.LENGTH_SHORT).show();
        }

        //test
    }

    //listener
    public void setListener(){
        //button

    }

}
