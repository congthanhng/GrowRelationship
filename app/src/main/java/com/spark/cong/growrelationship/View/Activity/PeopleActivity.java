package com.spark.cong.growrelationship.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.spark.cong.growrelationship.Architecture.Entity.People;
import com.spark.cong.growrelationship.Architecture.ViewModel.PeopleViewModel;
import com.spark.cong.growrelationship.Commons.impl.CommonImpl;
import com.spark.cong.growrelationship.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

import static com.spark.cong.growrelationship.Commons.Constant.INTENT_TO_PEOPLE;

public class PeopleActivity extends AppCompatActivity {

    private CommonImpl mCommon = CommonImpl.getInstance();
    private int mPeopleId;
    private PeopleViewModel mViewModel;
    private People mPeople;

    //test
    private EditText editText;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

//    private int groupId;
//    private List<Group> lstPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        //get data had been transfer from previous activity
        if (getIntent() != null) {
            Intent intent = getIntent();
            this.mPeopleId = intent.getIntExtra(INTENT_TO_PEOPLE, -1);
        }

        //set and map View
        setView();
        setListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    /**
     * listener when click back home
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //back to main activity
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * init, set and map view
     */
    public void setView() {
        //set action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //viewModel
        mViewModel = new ViewModelProvider(this).get(PeopleViewModel.class);
        editText =  findViewById(R.id.edt_test_people);

        //get data of people
        if (mPeopleId >= 0) {
            getPeopleById(mPeopleId);
            if (mPeople != null) {
                editText.setText(mPeople.getPeopleName());

            } else {
                Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(getApplicationContext(), "Please check your data transfer,", Toast.LENGTH_SHORT).show();
        }

        //test
    }

    private void getPeopleById(final int id) {
        compositeDisposable.add(mViewModel.getPeopleById(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSubscriber<People>() {
            @Override
            public void onNext(People people) {
                mPeople = people;
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        }));
    }

    //listener
    public void setListener() {
        //button

    }

}
