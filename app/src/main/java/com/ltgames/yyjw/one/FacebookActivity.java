//package com.ltgames.yyjw.google;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.gnetop.ltgamecommon.impl.OnLoginSuccessListener;
//import com.gnetop.ltgamecommon.model.BaseEntry;
//import com.gnetop.ltgamecommon.model.ResultData;
//import com.gnetop.ltgamefacebook.FaceBookLoginManager;
//
//
//public class FacebookActivity extends AppCompatActivity {
//
//    Button mBtnSend;
//    TextView mTxtResult;
//    String LTAppID = "20000";
//    String LTAppKey = "94W1ZVk9OCFP1NhQwebm1I9ZUTYM8TSd";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_facebook);
//        initFaceBook();
//        mTxtResult = findViewById(R.id.txt_result);
//        mBtnSend = findViewById(R.id.btn_send);
//        mBtnSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                facebookLogin();
//            }
//        });
//    }
//
//
//    /**
//     * 初始化facebook
//     */
//    private void initFaceBook() {
//        FaceBookLoginManager.getInstance().initFaceBook(this, LTAppID, LTAppKey, new OnLoginSuccessListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onSuccess(BaseEntry<ResultData> result) {
//                mTxtResult.setText(result.getCode() + "==\n" +
//                        result.getMsg()+"===\n"+
//                        result.getData().getLt_uid_token() + "==\n"
//                        + result.getData().getLt_uid());
//            }
//
//            @Override
//            public void onFailed(Throwable ex) {
//                mTxtResult.setText(ex.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//
//            @Override
//            public void onParameterError(String result) {
//                mTxtResult.setText(result);
//            }
//
//            @Override
//            public void onError(String error) {
//               mTxtResult.setText(error);
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        FaceBookLoginManager.getInstance().setOnActivityResult(requestCode, resultCode, data);
//
//    }
//
//    /**
//     * facebook登录
//     */
//    private void facebookLogin() {
//        FaceBookLoginManager.getInstance().faceBookLogin(this);
//    }
//
//}
