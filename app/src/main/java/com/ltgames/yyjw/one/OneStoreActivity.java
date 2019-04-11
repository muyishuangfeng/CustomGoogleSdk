package com.ltgames.yyjw.one;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.gnetop.ltgamecommon.impl.OnCreateOrderFailedListener;
import com.gnetop.ltgamecommon.impl.onOneStoreSupportListener;
import com.gnetop.ltgamecommon.impl.onOneStoreUploadListener;
import com.gnetop.ltgamecommon.model.OneStoreResult;

import java.util.Map;
import java.util.WeakHashMap;

public class OneStoreActivity extends AppCompatActivity {

    Button mBtnSend;
    TextView mTxtResult;
    String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCu9RPDbvVqM8XWqVc75JXccIXN1VS8XViRZzATUq62kkFIXCeo52LKzBCh3iWFQIvX3jqDhim4ESqHMezEx8CxaTq8NpNoQXutBNmOEl+/7HTUsZxI93wgn9+7pFMyoFlasqmVjCcM7zbbAx5G0bySsm98TFxTu16OGmO01JGonQIDAQAB";
    String LTAppKey = "94W1ZVk9OCFP1NhQwebm1I9ZUTYM8TSd";
    String LTAppID = "20000";
    String packageName = "com.ltgames.yyjw.one";
    private static final int selfRequestCode = 0x01;
    String productID = "com.ltgamesyyjw.5500d";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onestore);
        initView();
        init();
    }

    private void initView() {
        mTxtResult = findViewById(R.id.txt_result);
        mBtnSend = findViewById(R.id.btn_send);
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OneStorePlayManager
                        .getProduct(OneStoreActivity.this,
                                LTAppID, LTAppKey,
                                selfRequestCode,
                                productID,
                                productID,
                                new onOneStoreUploadListener() {
                                    @Override
                                    public void onOneStoreUploadSuccess(int result) {
                                        Log.e("oneStore", result + "==onOneStoreUploadSuccess=");
                                        if (result==200){
                                            mTxtResult.setText("亲：你购买成功了");
                                        }
                                    }

                                    @Override
                                    public void onOneStoreUploadFailed(Throwable error) {
                                        Log.e("oneStore", error.getMessage());
                                    }
                                },
                                new onOneStoreSupportListener() {
                                    @Override
                                    public void onOneStoreClientFailed(String failedMsg) {
                                        Log.e("oneStore", failedMsg);
                                    }

                                    @Override
                                    public void onOneStoreFailed(OneStoreResult result) {
                                        Log.e("oneStore", result.getCode() + "==" + result.getDescription());
                                    }

                                    @Override
                                    public void onOneStoreError(String result) {
                                        Log.e("oneStore", result);
                                    }

                                    @Override
                                    public void onOneStoreSuccess(OneStoreResult result) {
                                        Log.e("oneStore", result.getCode() + "==" + result.getDescription());
                                    }

                                    @Override
                                    public void onOneStoreConnected() {
                                        Log.e("oneStore", "onOneStoreConnected");
                                    }

                                    @Override
                                    public void onOneStoreDisConnected() {
                                        Log.e("oneStore", "onOneStoreDisConnected");
                                    }
                                });
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        OneStorePlayManager.onActivityResult(requestCode, resultCode, data,
                selfRequestCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OneStorePlayManager.release();
    }

    private void init() {
        Map<String, Object> params = new WeakHashMap<>();
        params.put("id", "123");
        params.put("name", "456");
        OneStorePlayManager
                .initOneStore(this, PUBLIC_KEY, LTAppID,
                        LTAppKey, packageName, "26", params,
                        new onOneStoreSupportListener() {
                            @Override
                            public void onOneStoreClientFailed(String failedMsg) {
                                Log.e("oneStoreINIT", failedMsg);
                            }

                            @Override
                            public void onOneStoreFailed(OneStoreResult result) {
                                Log.e("oneStoreINIT", result.getCode() + "==" + result.getDescription());
                            }

                            @Override
                            public void onOneStoreError(String result) {
                                Log.e("oneStoreINIT", result);
                            }

                            @Override
                            public void onOneStoreSuccess(OneStoreResult result) {
                                Log.e("oneStoreINIT", result.getCode() + "==" + result.getDescription());
                            }

                            @Override
                            public void onOneStoreConnected() {
                                Log.e("oneStoreINIT", "onOneStoreConnected");
                            }

                            @Override
                            public void onOneStoreDisConnected() {
                                Log.e("oneStoreINIT", "onOneStoreDisConnected");
                            }

                        }, new OnCreateOrderFailedListener() {
                            @Override
                            public void onCreateOrderFailed(String failed) {
                            }

                            @Override
                            public void onCreateOrderError(String errorMsg) {

                            }
                        }, new onOneStoreUploadListener() {
                            @Override
                            public void onOneStoreUploadSuccess(int result) {
                                Log.e("oneStoreINIT", result + "====");
                            }

                            @Override
                            public void onOneStoreUploadFailed(Throwable error) {

                            }
                        });
    }
}
