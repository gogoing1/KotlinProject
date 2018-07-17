package com.example.kotlin.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.example.kotlin.KotlinApplication;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;


/**
 * Created by chenqi on 2018/7/16 10:23
 * Email:cq_816102@163.com
 * Tips:
 */
public class NetActionApi {
    private Context mContext;
    private MyOkHttp myOkHttp;

    public NetActionApi(Context context) {
        this.mContext = context;
        this.myOkHttp = KotlinApplication.getMyOkHttp();
    }

    public <T> void postToApi(String[] keys, String[] values, String url, final NetCallBackListener<T> callBackListener) {
        if (!hasNetWorkConnect(mContext)) {//网络异常
            callBackListener.onFailure(NetCode.ERROR_NET_CONNECT,"网络异常");
            return;
        }

        if (myOkHttp != null) {
            if (TextUtils.isEmpty(url)) {
                throw new NullPointerException("http request url is null.");
            }
            myOkHttp.post().jsonParams(getRequestParam(keys,values)).url(url).enqueue(new GsonResponseHandler<T>() {

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    callBackListener.onFailure(statusCode, error_msg);
                }

                @Override
                public void onSuccess(int statusCode, T response) {
                    callBackListener.onSuccess(statusCode, response);
                }
            });

        } else {
            throw new NullPointerException("myOkHttp is null.");
        }
    }

    private String getRequestParam(String[] keys, String[] values) {
        if (keys != null && values != null&& keys.length==values.length) {
            StringBuilder builder = new StringBuilder();
            for(int i=0;i<keys.length;i++){
                builder.append(keys[i]).append("=").append(values[i]);
                builder.append("&");
            }
           return builder.toString();
        }
        return "";
    }

    /**
     * 判断当前是否有网络连接
     *
     * @return 有连接返回true
     */
    public static boolean hasNetWorkConnect(Context context) {
        NetworkInfo info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return info != null && info.isAvailable();
    }
}
