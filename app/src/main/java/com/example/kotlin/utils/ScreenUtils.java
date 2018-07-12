package com.example.kotlin.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by chenqi on 2018/7/12 9:01
 * Email:cq_816102@163.com
 * Tips: 屏幕工具类
 * Android中使用的长度单位：
 * dp : 最常用但也最难理解的尺寸单位 == dip，它与“像素密度”密切相关。比如：一个屏幕是1.5英寸 * 2英寸的手机，分辨率是240*320，那么它的像素密度是 240/1.5 == 320/2 == 160dpi(每英寸的像素数量)
 * sp : 与缩放无关的抽象像素，用于字体大小单位
 * in ：英寸 1（in） = 2.54 厘米
 * pt : 1pt = 1/72 英寸 = 0.035厘米
 * mm ：毫米
 */
public class ScreenUtils {
    private static ScreenUtils utils;
    private static DisplayMetrics dm;

    private ScreenUtils(Context context) {
        if (context == null) {
            throw new NullPointerException("context is null.");
        } else {
            dm = context.getResources().getDisplayMetrics();
        }
    }

    /**
     * 初始化
     * @param context
     */
    public static void init(Context context) {
        if (utils == null) {
            utils = new ScreenUtils(context);
        }
    }

    /**
     * dp 转 px
     * @param dpi
     * @return
     */
    public static float dpi2Px(float dpi) {
        return dpi * dm.density + 0.5F;
    }

    /**
     * px 转 dp
     * @param context
     * @param px
     * @return
     */
    public static int px2Dpi(Context context, int px) {
        if (dm == null) {
            dm = context.getResources().getDisplayMetrics();
        }

        return (int)((float)px / dm.density + 0.5F);
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    public static int getScreenWidth() {
        return dm == null ? 0 : dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     * @return
     */
    public static int getScreenHeight() {
        return dm == null ? 0 : dm.heightPixels;
    }

    /**
     * pt 转 px
     * @param context
     * @param pt
     * @return
     */
    public static int pt2Px(Context context, int pt) {
        if (dm == null) {
            dm = context.getResources().getDisplayMetrics();
        }
        return TypedValue.complexToDimensionPixelSize(pt, dm);
    }

    /**
     * pt 转 dp
     * @param context
     * @param pt
     * @return
     */
    public static int pt2Dpi(Context context, int pt) {
        if (dm == null) {
            dm = context.getResources().getDisplayMetrics();
        }
        return px2Dpi(context, TypedValue.complexToDimensionPixelSize(pt, dm));
    }

    /**
     * pt 转 sp
     * @param context
     * @param pt
     * @return
     */
    public static int pt2Sp(Context context, int pt) {
        if (dm == null) {
            dm = context.getResources().getDisplayMetrics();
        }
        return px2Sp(context, (float)TypedValue.complexToDimensionPixelSize(pt, dm));
    }

    /**
     * px 转 sp
     * @param context
     * @param px
     * @return
     */
    public static int px2Sp(Context context, float px) {
        if (dm == null) {
            dm = context.getResources().getDisplayMetrics();
        }
        return (int)(px / dm.scaledDensity + 0.5F);
    }
}
