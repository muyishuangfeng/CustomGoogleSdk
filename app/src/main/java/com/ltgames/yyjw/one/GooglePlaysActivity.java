//package com.ltgames.yyjw.google;
//
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
//import com.gnetop.ltgameplay.GooglePlayManager;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class GooglePlaysActivity extends AppCompatActivity {
//
//    Button mBtnSend;
//    TextView mTxtResult;
//    String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAz2MGvRWyaE5sKrj91NRItsRFGvuyu9EIghr4pjCUVlyq297KvUhr3+3dVrvRk8yAqo5nWNkHkhnuzmMjS7k4xhybweVHVG3rgLLaP9FT9UpUSVkAv6rzvYD3oeV1GL1/iGMWyd1t6CCedi4fjNgy8CB7R0VX1u8SsL502qw84XH2e+che4eotjJp9tAekQD3Bo9XjBD0Rmq8xVEU/kJRhfba/CLNOOS1g72x5RcPQdAsvAMbpkpdI2ZcHUyLXtM+qVZ0fusSUmQtZudagx3ZAnmywEWp8ovfOXO+mhAOa3tBgdsYHrj0maJ+w+15PkqzlDdbeWjP9vyLU+cF0w9Z7QIDAQAB";
//    String LTAppKey = "94W1ZVk9OCFP1NhQwebm1I9ZUTYM8TSd";
//    String LTAppID = "20000";
//    String packageName = "com.ltgames.yyjw.one";
//    private static final int selfRequestCode = 0x01;
//    String productID = "com.ltgamesyyjw.2750d";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_google_plays);
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
//    }
//
//
//    private void initGoogle() {
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
//        GooglePlayManager.init(this, base64EncodedPublicKey, productID, oldSkus, new OnGoogleInitListener() {
//            @Override
//            public void onGoogleInitSuccess(String success) {
//                Log.e("Google", "======initGooglePlay==onGoogleInitSuccess=" + success);
//            }
//
//            @Override
//            public void onGoogleInitFailed(String result) {
//                Log.e("Google", "======initGooglePlay==onGoogleInitFailed=" + result);
//            }
//        });
//
//
//    }
//
//    private void buy() {
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
//        Map<String, Object> map = new HashMap<>();
//        map.put("googleOrder", 123456789);
//
//        GooglePlayManager.recharge(this, LTAppID, LTAppKey, "41", packageName,
//                map, selfRequestCode, oldSkus, productID,
//                new OnGooglePlayResultListener() {
//                    @Override
//                    public void onPlaySuccess(String result) {
//                        Log.e("Google", "======recharge==onPlaySuccess=" + result);
//                    }
//
//                    @Override
//                    public void onPlayFailed(Throwable ex) {
//                        Log.e("Google", "======recharge==onPlayFailed=" + ex.getMessage());
//                    }
//
//                    @Override
//                    public void onPlayComplete() {
//                        Log.e("Google", "======recharge==onPlayComplete=");
//                    }
//
//                    @Override
//                    public void onPlayError(String result) {
//                        Log.e("Google", "======recharge==onPlayError=" + result);
//                    }
//                });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
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
//        GooglePlayManager.onActivityResult(this, requestCode, resultCode, data, selfRequestCode,
//                LTAppID, LTAppKey, oldSkus, productID, new OnGoogleResultListener() {
//                    @Override
//                    public void onResultSuccess(String result) {
//                        Log.e("Google", "======onActivityResult==onResultSuccess=" + result);
//                    }
//
//                    @Override
//                    public void onResultError(Throwable ex) {
//                        Log.e("Google", "======onActivityResult==onResultError=" + ex.getMessage());
//                    }
//
//                    @Override
//                    public void onResultFailed(String failedMsg) {
//                        Log.e("Google", "======onActivityResult==onResultFailed=" + failedMsg);
//                    }
//                });
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        GooglePlayManager.release();
//    }
//}
