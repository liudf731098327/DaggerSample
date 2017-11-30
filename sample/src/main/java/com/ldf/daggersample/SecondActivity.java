package com.ldf.daggersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ldf.daggersample.bean.User;
import com.ldf.daggersample.component.DaggerSecondComponent;

import javax.inject.Inject;

public class SecondActivity extends AppCompatActivity {

    Button mBtnGet;
    TextView mTvResult;

    @Inject
    User user;

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        DaggerSecondComponent.builder().userComponent(((BaseApplication) getApplication()).getUserComponent())
                .build().inject(this);

        mBtnGet = findViewById(R.id.second_btn_get);
        mTvResult = findViewById(R.id.second_tv_result);

        mBtnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvResult.setText(user + "");
                Log.d(TAG, "user=" + user);
            }
        });
    }
}
