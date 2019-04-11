//package com.ltgames.yyjw.google;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.gnetop.ltgamecommon.impl.OnGoogleInitListener;
//import com.gnetop.ltgamecommon.impl.OnGooglePlayResultListener;
//import com.gnetop.ltgamecommon.impl.OnGoogleResultListener;
//import com.gnetop.ltgamegoogle.login.GoogleLoginManager;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class GooglePlayActivity extends AppCompatActivity {
//
//    Button mBtnSend;
//    TextView mTxtResult;
//    String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAz2MGvRWyaE5sKrj91NRItsRFGvuyu9EIghr4pjCUVlyq297KvUhr3+3dVrvRk8yAqo5nWNkHkhnuzmMjS7k4xhybweVHVG3rgLLaP9FT9UpUSVkAv6rzvYD3oeV1GL1/iGMWyd1t6CCedi4fjNgy8CB7R0VX1u8SsL502qw84XH2e+che4eotjJp9tAekQD3Bo9XjBD0Rmq8xVEU/kJRhfba/CLNOOS1g72x5RcPQdAsvAMbpkpdI2ZcHUyLXtM+qVZ0fusSUmQtZudagx3ZAnmywEWp8ovfOXO+mhAOa3tBgdsYHrj0maJ+w+15PkqzlDdbeWjP9vyLU+cF0w9Z7QIDAQAB";
//    String LTAppKey = "94W1ZVk9OCFP1NhQwebm1I9ZUTYM8TSd";
//    String LTAppID = "20000";
//    String packageName = "com.ltgames.yyjw.one";
//    private static final int selfRequestCode = 0x01;
//    String productID = "com.ltgamesyyjw.2750d";
//    boolean isSuccess = false;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_google_play);
//        initView();
//    }
//
//    private void initView() {
//        initGoogle();
//        mTxtResult = findViewById(R.id.txt_result);
//        mBtnSend = findViewById(R.id.btn_send);
//        mBtnSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                buy();
//            }
//        });
//
//
//    }
//
//    private void initGoogle() {
////        GooglePlayManager.getInstance().initGooglePlay(this,
////                base64EncodedPublicKey, new OnGoogleInitListener() {
////                    @Override
////                    public void onGoogleInitSuccess(String success) {
////                        Log.e("Google", "======initGooglePlay===" + success);
////                    }
////
////                    @Override
////                    public void onGoogleInitFailed(String result) {
////                        Log.e("Google", "======initGooglePlay===" + result);
////                    }
////                });
//        List<String> oldSkus = new ArrayList<>();
//        oldSkus.add("com.ltgamesyyjw.2750d");
//        oldSkus.add("com.ltgamesyyjw.5500d");
//        oldSkus.add("com.ltgamesyyjw.16500d");
//        oldSkus.add("com.ltgamesyyjw.27500d");
//        oldSkus.add("com.ltgamesyyjw.109000d");
//        oldSkus.add("com.ltgamesyyjw.121000d");
//        oldSkus.add("com.ltgamesyyjw.ptyk");
//        oldSkus.add("com.ltgamesyyjw.gjyk");
//        oldSkus.add("com.ltgamesyyjw.qhlb1");
//        oldSkus.add("com.ltgamesyyjw.qhlb2");
//        oldSkus.add("com.ltgamesyyjw.qhlb3");
//        oldSkus.add("com.ltgamesyyjw.lslb1");
//        oldSkus.add("com.ltgamesyyjw.lslb2");
//        oldSkus.add("com.ltgamesyyjw.lslb3");
//        oldSkus.add("com.ltgamesyyjw.lslb4");
//        oldSkus.add("com.ltgamesyyjw.lslb5");
//        oldSkus.add("com.ltgamesyyjw.lslb6");
//        oldSkus.add(" com.ltgamesyyjw.czjj");
//        Map<String, Object> map = new HashMap<>();
//        map.put("googleOrder", 123456789);
//        GooglePlayManagers.initGooglePlay(this, base64EncodedPublicKey, LTAppID, LTAppKey,"41",
//                packageName, map,productID,oldSkus, new OnGoogleInitListener() {
//                    @Override
//                    public void onGoogleInitSuccess(String success) {
//                        Log.e("Google", "======initGooglePlay===" + success);
//                    }
//
//                    @Override
//                    public void onGoogleInitFailed(String result) {
//                        Log.e("Google", "======initGooglePlay===" + result);
//                    }
//                });
//    }
//
//
//    @SuppressLint("SetTextI18n")
//    private void buy() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("googleOrder", 123456789);
//        List<String> oldSkus = new ArrayList<>();
//        oldSkus.add("com.ltgamesyyjw.2750d");
//        oldSkus.add("com.ltgamesyyjw.5500d");
//        oldSkus.add("com.ltgamesyyjw.16500d");
//        oldSkus.add("com.ltgamesyyjw.27500d");
//        oldSkus.add("com.ltgamesyyjw.109000d");
//        oldSkus.add("com.ltgamesyyjw.121000d");
//        oldSkus.add("com.ltgamesyyjw.ptyk");
//        oldSkus.add("com.ltgamesyyjw.gjyk");
//        oldSkus.add("com.ltgamesyyjw.qhlb1");
//        oldSkus.add("com.ltgamesyyjw.qhlb2");
//        oldSkus.add("com.ltgamesyyjw.qhlb3");
//        oldSkus.add("com.ltgamesyyjw.lslb1");
//        oldSkus.add("com.ltgamesyyjw.lslb2");
//        oldSkus.add("com.ltgamesyyjw.lslb3");
//        oldSkus.add("com.ltgamesyyjw.lslb4");
//        oldSkus.add("com.ltgamesyyjw.lslb5");
//        oldSkus.add("com.ltgamesyyjw.lslb6");
//        oldSkus.add(" com.ltgamesyyjw.czjj");
//
////        GooglePlayManager.getInstance().getRecharge(this, LTAppID, LTAppKey, packageName, "41",
////                map, oldSkus, productID, selfRequestCode, new OnGooglePlayResultListener() {
////                    @Override
////                    public void onPlaySuccess(String result) {
////                        mTxtResult.setText("==onPlaySuccess=" + result);
////                        Log.e("Google", "======getRecharge==onPlaySuccess=" + result);
////                    }
////
////                    @Override
////                    public void onPlayFailed(Throwable ex) {
////                        mTxtResult.setText("==onPlayFailed=" + ex.getMessage());
////                        Log.e("Google", "======getRecharge==onPlayFailed=" + ex.getMessage());
////                    }
////
////                    @Override
////                    public void onPlayComplete() {
////                        mTxtResult.setText("onPlayComplete");
////                        Log.e("Google", "======getRecharge==onPlayComplete=" + "onPlayComplete");
////                    }
////
////                    @Override
////                    public void onPlayError(String result) {
////                        mTxtResult.setText("==onPlayError=" + result);
////                        Log.e("Google", "======getRecharge===onPlayError==" + result);
////                        isSuccess = true;
////
////                    }
////                });
//        GooglePlayManagers.checkUnConsume(this, selfRequestCode, oldSkus, productID, new OnGooglePlayResultListener() {
//            @Override
//            public void onPlaySuccess(String result) {
//                Log.e("Google", "======getRecharge==onPlaySuccess=" + result);
//            }
//
//            @Override
//            public void onPlayFailed(Throwable ex) {
//                Log.e("Google", "======getRecharge==onPlayFailed=" + ex.getMessage());
//            }
//
//            @Override
//            public void onPlayComplete() {
//                Log.e("Google", "======getRecharge==onPlayComplete=" );
//            }
//
//            @Override
//            public void onPlayError(String result) {
//                Log.e("Google", "======getRecharge==onPlayError=" + result);
//            }
//        });
//    }
//
//
//    @SuppressLint("SetTextI18n")
//    @Override
//    protected void onActivityResult(final int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
////        GooglePlayManager.getInstance().onActivityResult(this, requestCode, resultCode, data, selfRequestCode, LTAppID,
////                LTAppKey, new OnGoogleResultListener() {
////                    @Override
////                    public void onResultSuccess(String result) {
////                        mTxtResult.setText("===onResultSuccess===" + result);
////                        Log.e("Google", "======onActivityResult==onResultSuccess=" + result);
////                    }
////
////
////                    @Override
////                    public void onResultError(Throwable ex) {
////                        mTxtResult.setText("===onResultError===" + ex.getMessage());
////                        Log.e("Google", "======onActivityResult==onResultError=" + ex.getMessage());
////                    }
////
////                    @Override
////                    public void onResultFailed(String failedMsg) {
////                        mTxtResult.setText("===onResultFailed===" + failedMsg);
////                        Log.e("Google", "======onActivityResult==onResultFailed=" + failedMsg);
////
////                    }
////                });
//        List<String> oldSkus = new ArrayList<>();
//        oldSkus.add("com.ltgamesyyjw.2750d");
//        oldSkus.add("com.ltgamesyyjw.5500d");
//        oldSkus.add("com.ltgamesyyjw.16500d");
//        oldSkus.add("com.ltgamesyyjw.27500d");
//        oldSkus.add("com.ltgamesyyjw.109000d");
//        oldSkus.add("com.ltgamesyyjw.121000d");
//        oldSkus.add("com.ltgamesyyjw.ptyk");
//        oldSkus.add("com.ltgamesyyjw.gjyk");
//        oldSkus.add("com.ltgamesyyjw.qhlb1");
//        oldSkus.add("com.ltgamesyyjw.qhlb2");
//        oldSkus.add("com.ltgamesyyjw.qhlb3");
//        oldSkus.add("com.ltgamesyyjw.lslb1");
//        oldSkus.add("com.ltgamesyyjw.lslb2");
//        oldSkus.add("com.ltgamesyyjw.lslb3");
//        oldSkus.add("com.ltgamesyyjw.lslb4");
//        oldSkus.add("com.ltgamesyyjw.lslb5");
//        oldSkus.add("com.ltgamesyyjw.lslb6");
//        oldSkus.add(" com.ltgamesyyjw.czjj");
//        GooglePlayManagers.onActivityResult(this,requestCode,resultCode, data, selfRequestCode, LTAppID, LTAppKey, oldSkus,productID,new OnGoogleResultListener() {
//            @Override
//            public void onResultSuccess(String result) {
//                Log.e("Google", "======onActivityResult==onResultSuccess=" + result);
//                //GooglePlayManagers.release();
//                mTxtResult.setText(result);
//            }
//
//            @Override
//            public void onResultError(Throwable ex) {
//                Log.e("Google", "======onActivityResult==onResultError=" + ex.getMessage());
//            }
//
//            @Override
//            public void onResultFailed(String failedMsg) {
//                Log.e("Google", "======onActivityResult==onResultFailed=" + failedMsg);
//                //GooglePlayManagers.release();
//            }
//        });
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        GooglePlayManager.getInstance().release();
//    }
//
//}
