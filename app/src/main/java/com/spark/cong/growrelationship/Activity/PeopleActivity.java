package com.spark.cong.growrelationship.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.spark.cong.growrelationship.Architecture.Entity.Group;
import com.spark.cong.growrelationship.R;

import java.util.List;

import static com.spark.cong.growrelationship.Commons.Constant.*;

public class PeopleActivity extends AppCompatActivity {

    private int groupId;
    private List<Group> lstPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(INTENT_MAIN_TO_PEOPLE);
        this.groupId = bundle.getInt(BUNDLE_MAIN_TO_PEOPLE);
        //set and map View
        setMapView();

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
    }
}
