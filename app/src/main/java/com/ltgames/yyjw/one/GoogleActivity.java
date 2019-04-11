//package com.ltgames.yyjw.google;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.gnetop.ltgamecommon.impl.OnLoginSuccessListener;
//import com.gnetop.ltgamecommon.model.BaseEntry;
//import com.gnetop.ltgamecommon.model.ResultData;
//import com.gnetop.ltgamegoogle.login.GoogleLoginManager;
//import com.gnetop.ltgamegoogle.login.OnGoogleSignOutListener;
//
//
//public class GoogleActivity extends AppCompatActivity {
//
//    Button mBtnLogin,mBtnLoginOut;
//    TextView mTxtResult;
//    private static final int REQUEST_CODE = 0x01;
//    String LTAppID = "20000";
//    String LTAppKey = "94W1ZVk9OCFP1NhQwebm1I9ZUTYM8TSd";
//    String TAG = "GooglePlayActivity";
//    String clientID = "182767183123-v2l0sd2cs67ob9bet6bql80cuel09445.apps.googleusercontent.com";
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_google);
//        initView();
//    }
//
//    private void initView() {
//        mTxtResult = findViewById(R.id.txt_result);
//        mBtnLogin = findViewById(R.id.btn_login);
//        mBtnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GoogleLoginManager.initGoogle(GoogleActivity.this, clientID, REQUEST_CODE);
//            }
//        });
//        mBtnLoginOut = findViewById(R.id.btn_loginOut);
//        mBtnLoginOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GoogleLoginManager.GoogleSingOut(GoogleActivity.this, clientID, new OnGoogleSignOutListener() {
//                    @Override
//                    public void onSignOutSuccess() {
//                        mTxtResult.setText("LoginOut Success");
//                    }
//                });
//            }
//        });
//    }
//
//
//    @Override
//    protected void onActivityResult(final int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        GoogleLoginManager.onActivityResult(requestCode, data, REQUEST_CODE,
//                this, LTAppID, LTAppKey, new OnLoginSuccessListener() {
//                    @SuppressLint("SetTextI18n")
//                    @Override
//                    public void onSuccess(BaseEntry<ResultData> result) {
//                        Log.e(TAG, result.getMsg() + "==" + result.getCode() + "==" + result.getData());
//                        mTxtResult.setText(result.getCode() + "==\n" +
//                                result.getMsg()+"===\n"+
//                                result.getData().getLt_uid_token() + "==\n"
//                                + result.getData().getLt_uid());
//                    }
//
//                    @Override
//                    public void onFailed(Throwable ex) {
//                        Log.e(TAG, ex.getMessage());
//                        mTxtResult.setText(ex.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.e(TAG, "onComplete");
//                    }
//
//                    @Override
//                    public void onParameterError(String result) {
//                        Log.e(TAG, result);
//                        mTxtResult.setText(result);
//                    }
//
//                    @Override
//                    public void onError(String error) {
//                        Log.e(TAG, error);
//                        mTxtResult.setText(error);
//                    }
//                });
//    }
//}
