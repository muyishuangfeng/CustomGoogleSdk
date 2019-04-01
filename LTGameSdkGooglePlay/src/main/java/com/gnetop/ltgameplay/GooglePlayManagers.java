package com.gnetop.ltgameplay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.gnetop.ltgamecommon.impl.OnCreateOrderListener;
import com.gnetop.ltgamecommon.impl.OnGoogleInitListener;
import com.gnetop.ltgamecommon.impl.OnGooglePlayResultListener;
import com.gnetop.ltgamecommon.impl.OnGoogleResultListener;
import com.gnetop.ltgamecommon.login.LoginBackManager;
import com.gnetop.ltgamecommon.model.GoogleModel;
import com.gnetop.ltgameplay.util.IabHelper;
import com.gnetop.ltgameplay.util.IabResult;
import com.gnetop.ltgameplay.util.Inventory;
import com.gnetop.ltgameplay.util.Purchase;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class GooglePlayManagers {
    private static final String TAG = GooglePlayManagers.class.getSimpleName();
    @SuppressLint("StaticFieldLeak")
    private static IabHelper mHelper;
    private static String payload;
    private boolean isGetGold = false;
    private static GooglePlayManagers sInstance;
    private Purchase mPurchase;
    private boolean mIsHelperSetup = false;

    private GooglePlayManagers() {
    }

    /**
     * 单例
     *
     * @return
     */
    public static synchronized GooglePlayManagers getInstance() {
        if (sInstance == null) {
            synchronized (GooglePlayManagers.class) {
                if (sInstance == null) {
                    sInstance = new GooglePlayManagers();
                }
            }
        }
        return sInstance;
    }

    /**
     * 游戏登陆完成（用于继续进行未完成的内购交易）
     *
     */
    public void init(Context context, List<String> mProductListStr, String mPublicKey, final OnGoogleInitListener mListener) {
        try {
            //请求服务器继续未完成的订单
            if (mIsHelperSetup) {
                //查询用户拥有的商品
                mHelper.queryInventoryAsync(mGotInventoryListener);  //进行未完成订单验证
            } else {
                getPublishKey(context, mPublicKey, mListener);  //请求公钥
            }
            //请求订单列表
            getProductOrActivityList(context, mPublicKey, mProductListStr, mListener);
        } catch (IabHelper.IabAsyncInProgressException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取秘钥
     */
    private void getPublishKey(final Context context, final String mPublicKey, final OnGoogleInitListener mListener) {
        if (mIsHelperSetup) {
            return;
        }
        mHelper = new IabHelper(context, mPublicKey);
        mHelper.enableDebugLogging(true);
        //完成内购启动的准备工作
        if (mHelper!=null){
            mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
                public void onIabSetupFinished(IabResult result) {
                    if (!result.isSuccess()) {
                        mListener.onGoogleInitFailed(result.getMessage());
                        Log.e(TAG, "Problem setting up In-app Billing: " + result);
                    } else {
                        try {
                            mIsHelperSetup = true;
                            mHelper.queryInventoryAsync(mGotInventoryListener);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        mListener.onGoogleInitSuccess("init Success");
                    }

                }
            });
        }
    }


    /**
     * 查询用户所拥有商品的回调监听  -- 继续用户未完成的订单
     */
    private IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            if (mHelper == null) return;
            if (result.isFailure()) {
                return;
            }
            List<Purchase> list = inventory.getAllPurchases();
            for (int i = 0; i < list.size(); i++) {
                Purchase purchase = list.get(i);
                if (purchase != null) {
                    try {
                        mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        e.printStackTrace();
                    }
                }
            }
            Log.d(TAG, "Initial inventory query finished; enabling main UI.");
        }
    };


    /**
     * 开始购买
     *
     * @param context   上下文
     * @param LTAppID   乐推AppID
     * @param LTAppKey  乐推AppKey
     * @param packageId 包名
     * @param gid       服务器配置的商品ID
     * @param params    自定义的信息
     * @param mListener 支付结果回调
     */
    public void getRecharge(final Context context, final String LTAppID, final String LTAppKey,
                            final String packageId, final String gid, final Map<String, Object> params,
                            final String productID, final int REQUEST_CODE,
                            final OnGooglePlayResultListener mListener) {
        getLTOrderID(context, LTAppID, LTAppKey, packageId, gid, params,
                productID, REQUEST_CODE, mListener);
    }


    /**
     * 商品消耗完成的监听
     */
    private IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
        public void onConsumeFinished(Purchase purchase, IabResult result) {
            if (mHelper == null) return;
            if (result.isSuccess()) {
                Log.e(TAG, "Consumption successful. Provisioning.");
            } else {
                Log.e(TAG, "Error while consuming. Provisioning.");
            }
            Log.e(TAG, "End consumption flow.");
        }
    };

    /**
     * 产品获取
     *
     * @param context      上下文
     * @param REQUEST_CODE 请求码
     * @param SKU          产品唯一id, 填写你自己添加的商品id
     * @param payload      订单号
     * @param mListener    回调监听
     */
    private void getProduct(final Activity context, int REQUEST_CODE,
                            final String SKU, String payload, final OnGooglePlayResultListener mListener) {
        if (!TextUtils.isEmpty(payload)) {
            if (mIsHelperSetup) {
                try {
                    mHelper.launchPurchaseFlow(context, SKU, REQUEST_CODE,
                            new IabHelper.OnIabPurchaseFinishedListener() {
                                @Override
                                public void onIabPurchaseFinished(IabResult result, final Purchase purchase) {
                                    if (result.isFailure()) {
                                        Toast.makeText(context, "Purchase Failed", Toast.LENGTH_SHORT).show();
                                        mListener.onPlayError(result.getMessage());
                                    } else if (result.isSuccess()) {
                                        mListener.onPlaySuccess("Purchase successful");
                                    }
                                }
                            }, payload);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            mListener.onPlayError("Order creation failed");
            Toast.makeText(context, "Order creation failed", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 释放资源
     */
    public void release() {
        /*
         * 释放掉资源
         */
        if (mHelper != null) {
            mHelper.disposeWhenFinished();
            mIsHelperSetup = false;
        }
        mHelper = null;
    }


    public void onActivityResult( int requestCode, int resultCode, Intent data, int selfRequestCode,
                                  final String LTAppID, final String LTAppKey, OnGoogleResultListener
                                          mListener) {
        /*
         * 将回调交给帮助类来处理, 否则会出现支付正在进行的错误
         */
        if (mHelper != null) {
            mHelper.handleActivityResult(requestCode, resultCode, data);
            if (requestCode == selfRequestCode) {
                int responseCode = data.getIntExtra("RESPONSE_CODE", 0);
                //订单信息
                String purchaseData = data.getStringExtra("INAPP_PURCHASE_DATA");
                String dataSignature = data.getStringExtra("INAPP_DATA_SIGNATURE");
                if (!TextUtils.isEmpty(purchaseData) && !TextUtils.isEmpty(payload)) {
                    GoogleModel googleModel = new Gson().fromJson(purchaseData, GoogleModel.class);
                    Log.e(TAG, googleModel.getPurchaseToken());
                    Map<String, Object> params = new WeakHashMap<>();
                    params.put("purchase_token", googleModel.getPurchaseToken());
                    params.put("lt_order_id", payload);
                    uploadToServer( LTAppID, LTAppKey, params, mListener);
                }
            }
        }

    }

    /**
     * 获取乐推订单ID
     *
     * @param LTAppID   appID
     * @param LTAppKey  appKey
     * @param packageId 应用包名
     * @param gid       服务器配置商品的ID
     * @param params    集合
     */
    private void getLTOrderID(final Context context, final String LTAppID, final String LTAppKey,
                              final String packageId, final String gid, final Map<String, Object> params,
                              final String productID, final int REQUEST_CODE,
                              final OnGooglePlayResultListener mListener) {
        Map<String, Object> map = new WeakHashMap<>();
        map.put("package_id", packageId);
        map.put("gid", gid);
        map.put("custom", params);
        LoginBackManager.createOrder(LTAppID,
                LTAppKey, map, new OnCreateOrderListener() {
                    @Override
                    public void onOrderSuccess(String result) {
                        if (!TextUtils.isEmpty(result)) {
                            payload = result;
                            getProduct((Activity) context, REQUEST_CODE, productID, payload, mListener);
                        } else {
                            Log.e(TAG, "ltOrderID is null");
                        }
                    }

                    @Override
                    public void onOrderFailed(Throwable ex) {
                        Log.e(TAG, ex.getMessage());
                    }

                    @Override
                    public void onOrderError(String error) {
                        Log.e(TAG, error);
                    }
                });
    }

    /**
     * 上传到服务器
     *
     * @param LTAppID   乐推订单
     * @param LTAppKey  乐推APPkey
     * @param params
     * @param mListener 结果
     */
    private void uploadToServer(
            final String LTAppID,
            final String LTAppKey,
            Map<String, Object> params,
            final OnGoogleResultListener mListener) {
        LoginBackManager.googlePlay(
                LTAppID, LTAppKey, params
                , new OnGooglePlayResultListener() {
                    @Override
                    public void onPlaySuccess(String result) {
                        mListener.onResultSuccess(result);
                    }

                    @Override
                    public void onPlayFailed(Throwable ex) {
                        mListener.onResultError(ex);
                    }

                    @Override
                    public void onPlayComplete() {

                    }

                    @Override
                    public void onPlayError(String result) {
                        mListener.onResultFailed(result);
                    }
                });
    }

    /**
     * 请求服务器获取 商品/活动 列表
     */
    private void getProductOrActivityList(Context context, String mPublicKey, List<String> mProductListStr,
                                          final OnGoogleInitListener mListener) {
        try {
            //请求google，查询对应列表的详细信息（价格，商品名称等）
            if (mIsHelperSetup) {
                mHelper.queryInventoryAsync(true, mProductListStr, mProductListStr, mQueryFinishedListener);
            } else {
                getPublishKey(context, mPublicKey, mListener);
            }
        } catch (IabHelper.IabAsyncInProgressException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询商品列表的监听
     */
    private IabHelper.QueryInventoryFinishedListener mQueryFinishedListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            if (result.isFailure()) {
                return;
            }
            List priceList = new ArrayList();
        }
    };


}
