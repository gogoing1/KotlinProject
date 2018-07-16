package com.example.kotlin;

import android.app.Application;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by chenqi on 2018/7/16 10:01
 * Email:cq_816102@163.com
 * Tips:
 */
public class KotlinApplication extends Application {

    private static MyOkHttp myOkHttp;

    @Override
    public void onCreate() {
        super.onCreate();
        initHttpConfig();
    }

    private void initHttpConfig() {
        //设置开启cookie
        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(this));
        //okhttp的配置
        OkHttpClient build = new OkHttpClient()
                .newBuilder()
                .cookieJar(cookieJar)
                .connectTimeout(15000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();
        myOkHttp = new MyOkHttp(build);
    }


    public static MyOkHttp getMyOkHttp(){
        return myOkHttp;
    }
}
