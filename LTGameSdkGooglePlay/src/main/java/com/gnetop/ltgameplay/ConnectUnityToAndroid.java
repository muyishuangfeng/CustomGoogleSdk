package com.gnetop.ltgameplay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;


import com.gnetop.ltgameplay.util.IabHelper;
import com.gnetop.ltgameplay.util.IabResult;
import com.gnetop.ltgameplay.util.Inventory;
import com.gnetop.ltgameplay.util.Purchase;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhubinfeng on 2018/1/29.
 * function:
 */

public class ConnectUnityToAndroid {


    public static String BILLING_KEY = "";
    //Google内购的帮助类
    public static IabHelper mHelper;
    public static Activity mActivity;
    private static final String TAG = "ConnectUnityToAndroid";
    //当前商品的ID
    private static String mProductId = "";
    //当前商品的Index
    private static int mProductIdIndex = 0;
    //进入游戏
    private static String mActionEnterGame = "EnterGame";
    //请求购买的编码
    private static final int BUY_REQUEST_CODE = 10002;
    //内购
    private static String mActionBuy = "NormalPurchase";
    //获取商品内购数据
    private static String mActionGetBuyPrice = "GetBuyPrice";
    //内购初始化是否完成
    private static Boolean mIsHelperSetup = false;


    /**
     * 游戏登陆完成（用于继续进行未完成的内购交易）
     *
     * @param context
     */
    public static void EnterGame(Activity context, final String mCreateOrder, List<String> mProductListStr) {
        try {
            mActivity = context;
            //请求服务器继续未完成的订单
            if (mIsHelperSetup) {
                //查询用户拥有的商品
                mHelper.queryInventoryAsync(mGotInventoryListener);  //进行未完成订单验证
            } else {
                getPublishKey(context, mActionEnterGame, mCreateOrder,mProductListStr);  //请求公钥
            }
            //请求订单列表
            getProductOrActivityList(mCreateOrder, mProductListStr);
        } catch (IabHelper.IabAsyncInProgressException e) {
            e.printStackTrace();
        }
    }




    /**
     * 获取秘钥
     *
     * @param context
     */
    private static void getPublishKey(final Activity context, final String flag, final String mCreateOrder
            , final List<String>mProductListStr) {

        // String str = new String(responseBody);
        BILLING_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArigIs6SHKREbvoCbrWXzuve/Cn653ULXnUGkZ1vLbA6aWb6EWr5MV7C5lJj5FFf9mJjxXpHFsaK+sLSdiV2xnUHGQyIm8sjtOkpJILhC4NzqBQJ45rKB9v40mMoea4kp5M/8G9zP8e41LOL2sTDg6T4euof39fODO4qdzPLC4whWp9VqZ88uMVLThShUoGPy2xRpLLXM20W55P4URYnOA+gnTENfkp8J/qWQrI2K1mAwNyYAW0eWKSpgd7bifH9BdNA92xJvJutOwOqoOnXxx2Al3g16lyzwGeOEi2/84uU8HhoiZ4kROoJPNJAcVgDUzhENMuyiL+vqqqbPiMyc8wIDAQAB";// str.substring(2, str.length() - 2);
        if (mIsHelperSetup) {
            return;
        }
        mHelper = new IabHelper(context, BILLING_KEY);
        mHelper.enableDebugLogging(true);
        //完成内购启动的准备工作
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    // Oh no, there was a problem.
                    Log.d(TAG, "Problem setting up In-app Billing: " + result);
                    return;
                }
                // Hooray, IAB is fully set up!
                try {
                    mIsHelperSetup = true;
                    if (mActionEnterGame.equals(flag)) {
                        mHelper.queryInventoryAsync(mGotInventoryListener);
                    } else if (mActionBuy.equals(flag)) {
                        mHelper.launchPurchaseFlow(context, mProductId, BUY_REQUEST_CODE,
                                mPurchaseFinishedListener, mCreateOrder);
                    } else if (mActionGetBuyPrice.equals(flag)) {
                        mHelper.queryInventoryAsync(true,
                                mProductListStr, mProductListStr, mQueryFinishedListener);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    //查询商品列表的监听
    static final IabHelper.QueryInventoryFinishedListener mQueryFinishedListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            if (result.isFailure()) {
                // handle error
                return;
            }
            List priceList = new ArrayList();

        }
    };
    // 商品消耗完成的监听
    static IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
        public void onConsumeFinished(Purchase purchase, IabResult result) {
            if (mHelper == null) return;
            if (result.isSuccess()) {
                Log.d(TAG, "Consumption successful. Provisioning.");
            } else {
                Log.d(TAG, "Error while consuming. Provisioning.");
            }
            Log.d(TAG, "End consumption flow.");
        }
    };

    // 查询用户所拥有商品的回调监听  -- 继续用户未完成的订单
    static IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            if (mHelper == null) return;
            if (result.isFailure()) {
                return;
            }
            List<Purchase> list = inventory.getAllPurchases();
            for (int i = 0; i < list.size(); i++) {
                Purchase purchase = list.get(i);
                if (purchase != null) {
                    checkToServer(purchase);
                }
            }
            Log.d(TAG, "Initial inventory query finished; enabling main UI.");
        }
    };

    //购买完成的监听
    static final IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            if (result.isFailure()) {
                Log.d(TAG, "Error purchasing: " + result);

                return;
            } else if (result.isSuccess()) {
                checkToServer(purchase);
            }
        }
    };

    /**
     * 内购
     *
     * @param context
     * @param productName 商品名称
     */
    public static void NormalPurchase(Activity context, final int productIndex,
                                      final String productName, final String mCreateOrder,List<String>mProductListStr) {
        mActivity = context;
        mProductIdIndex = productIndex;
        mProductId = productName;// String.valueOf(mProductList.get(productName).getId());
        SharedPreferences sp = context.getSharedPreferences(TAG, mActivity.MODE_PRIVATE);//安卓的轻量级持久化缓存
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("activity_id", 0); //存储活动ID
        editor.putInt("productIdIndex", mProductIdIndex); //存储活动ID
        editor.commit();

        if (mIsHelperSetup) {
            try {
                mHelper.launchPurchaseFlow(mActivity, mProductId, BUY_REQUEST_CODE,
                        mPurchaseFinishedListener, mCreateOrder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            getPublishKey(context, mActionBuy, mCreateOrder,mProductListStr);
        }
    }


    /**
     * 请求服务器校验支付结果
     *
     * @param purchase
     */
    public static void checkToServer(final Purchase purchase) {
        try {
            //服务端确认后，消耗掉商品，用户可以再次购买
            mHelper.consumeAsync(purchase, mConsumeFinishedListener);

        } catch (IabHelper.IabAsyncInProgressException e) {
            e.printStackTrace();
        }


    }

    /**
     * 请求服务器获取 商品/活动 列表
     */
    private static void getProductOrActivityList(String mCreateOrder, List<String> mProductListStr) {
        try {
            //请求google，查询对应列表的详细信息（价格，商品名称等）
            if (mIsHelperSetup) {
                mHelper.queryInventoryAsync(true, mProductListStr, mProductListStr, mQueryFinishedListener);
            } else {
                getPublishKey(mActivity, mActionGetBuyPrice, mCreateOrder,mProductListStr);
            }
        } catch (IabHelper.IabAsyncInProgressException e) {
            e.printStackTrace();
        }
    }


}
