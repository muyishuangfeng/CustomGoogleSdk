//package com.ltgames.yyjw.google;
//
//import android.annotation.SuppressLint;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.gnetop.ltgamecommon.base.BaseResult;
//import com.gnetop.ltgamecommon.model.Event;
//import com.gnetop.ltgamecommon.model.ResultData;
//import com.gnetop.ltgamecommon.util.EventUtils;
//import com.gnetop.ltgameui.base.BaseAppActivity;
//import com.gnetop.ltgameui.impl.OnReLoginInListener;
//import com.gnetop.ltgameui.manager.LoginUIManager;
//
//import org.greenrobot.eventbus.Subscribe;
//
//
//public class UIActivity extends BaseAppActivity {
//
//    Button mBtnLogin, mBtnLoginOut;
//    TextView mTxtResult;
//    String LTAppID = "20000";
//    String LTAppKey = "94W1ZVk9OCFP1NhQwebm1I9ZUTYM8TSd";
//    String clientID = "182767183123-v2l0sd2cs67ob9bet6bql80cuel09445.apps.googleusercontent.com";
//    String mAgreementUrl = "http://www.baidu.com";
//    String mProvacyUrl = "http://www.baidu.com";
//
//
//
//    @Override
//    protected int getViewId() {
//        return R.layout.activity_ui;
//    }
//
//    @Override
//    protected void initView() {
//        EventUtils.register(this);
//        mTxtResult = findViewById(R.id.txt_result);
//        mBtnLogin = findViewById(R.id.btn_login);
//        mBtnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                login();
//            }
//        });
//        mBtnLoginOut = findViewById(R.id.btn_loginOut);
//        mBtnLoginOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loginOut();
//            }
//        });
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//
//    private void login() {
//        LoginUIManager.getInstance().loginIn(this, mAgreementUrl, mProvacyUrl, clientID,
//                LTAppID, LTAppKey, new OnReLoginInListener() {
//                    @SuppressLint("SetTextI18n")
//                    @Override
//                    public void OnLoginResult(ResultData result) {
//                        mTxtResult.setText("=====登录成功:\n" + result.getLt_uid_token() + "===\n"
//                                + result.getLt_uid());
//                    }
//                });
//    }
//
//    private void loginOut() {
//        LoginUIManager.getInstance().loginOut(this, mAgreementUrl, mProvacyUrl, clientID,
//                LTAppID, LTAppKey);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        EventUtils.unregister(this);
//    }
//
//
//
//    @SuppressLint("SetTextI18n")
//    @Subscribe
//    public void receiveEvent(Event event) {
//        switch (event.getCode()) {
//            case BaseResult.MSG_RESULT_GOOGLE_SUCCESS: {
//                ResultData result = (ResultData) event.getData();
//                mTxtResult.setText("=====登录成功:\n" + result.getLt_uid_token() + "===\n"
//                        + result.getLt_uid());
//                break;
//            }
//            case BaseResult.MSG_RESULT_FACEBOOK_SUCCESS: {
//                ResultData result = (ResultData) event.getData();
//                mTxtResult.setText("=====登录成功:\n" + result.getLt_uid_token() + "===\n"
//                        + result.getLt_uid());
//                break;
//            }
//        }
//    }
//
//}
