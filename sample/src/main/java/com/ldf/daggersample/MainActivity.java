package com.ldf.daggersample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ldf.daggersample.bean.User;
import com.ldf.daggersample.component.DaggerActivityComponent;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mBtnJump;
    Button mBtnGet;
    TextView mTvResult;

    @Inject
    User user;

    @Inject
    User user2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerActivityComponent.builder().userComponent(((BaseApplication) getApplication()).getUserComponent())
                .build().inject(this);

        mBtnGet = findViewById(R.id.main_btn_get);
        mBtnJump = findViewById(R.id.main_btn_jump);
        mTvResult = findViewById(R.id.main_tv_result);

        mBtnJump.setOnClickListener(this);
        mBtnGet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_get:
                mTvResult.setText(user.show() + "\n" + user + "\n" + user2);
                break;
            case R.id.main_btn_jump:
                startActivity(new Intent(this, SecondActivity.class));
                break;
        }
    }
}
