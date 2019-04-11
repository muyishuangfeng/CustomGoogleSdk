package com.ltgames.yyjw.one;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    Button mBtnGoogle, mBtnGooglePlay, mBtnUI, mBtnFacebook;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mBtnGoogle = findViewById(R.id.btn_google);
//        mBtnGoogle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //startActivity(new Intent(MainActivity.this, GoogleActivity.class));
//            }
//        });
        mBtnGooglePlay = findViewById(R.id.btn_google_play);
        mBtnGooglePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(MainActivity.this, OneStoreActivity.class));
            }
        });
//        mBtnFacebook = findViewById(R.id.btn_facebook);
//        mBtnFacebook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               // startActivity(new Intent(MainActivity.this, FacebookActivity.class));
//            }
//        });
//        mBtnUI = findViewById(R.id.btn_ui);
//        mBtnUI.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //startActivity(new Intent(MainActivity.this, UIActivity.class));
//            }
//        });

    }



}
