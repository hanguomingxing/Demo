package com.me.daydaystudy.utils;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.me.daydaystudy.app.MyApplication;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 普通的工具类
 */
public class CommonUtils {
    private static final String TAG = "CommonUtils";

    public static Context getContext() {
        return MyApplication.getContext();
    }

    // 获取资源文件夹操作
    public static Resources getResources() {
        return getContext().getResources();
    }

    // dip--->px, 1dp = 2px,定义一个控件的宽高 layoutParams(w,h)
    public static int dip2px(int dip) {
        // 获取dp和px的转换关系的变量
        float density = getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5);
    }

    // px---->dp
    public static int px2dip(int px) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

    public static View inflate(int layoutId) {
        return View.inflate(getContext(), layoutId, null);
    }

    // 将dimens中的dp转换成像素值
    public static int getDimens(int dimensId) {
        return getResources().getDimensionPixelSize(dimensId);
    }

    /**
     * 获取Color中的属性
     *
     * @param colorId
     * @return
     */
    public static int getResourseColor(int colorId) {
        int color = MyApplication.getContext().getResources().getColor(colorId);
        return color;
    }

    public static void showInfoDialog(Context context, String message) {
        showInfoDialog(context, message, "提示", "取消", "确定", null, null);
    }

    public static void showInfoDialog(Context context, String message,
                                      String titleStr, String positiveStr, String negativeStr,
                                      DialogInterface.OnClickListener positiveOnClickListener,
                                      DialogInterface.OnClickListener negativeOnClickListener) {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(context);
        localBuilder.setTitle(titleStr);
        localBuilder.setMessage(message);
        if (positiveOnClickListener == null)
            positiveOnClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            };
        localBuilder.setPositiveButton(positiveStr, positiveOnClickListener);
        localBuilder.setNegativeButton(negativeStr, negativeOnClickListener);
        localBuilder.show();
    }

    public static void showUpdateDialog(final Context context,
                                        final String mUpgradeUrl) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setMessage(
                "发现新版本，是否需要升级？").setNegativeButton("否",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }

                });
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 下载新的apk
                if (mUpgradeUrl != null) {
                    downLoadApk(context, mUpgradeUrl);
                }
                dialog.cancel();
            }

        }).show();
    }

    /*
     * 从服务器中下载APK
     */
    protected static void downLoadApk(Context context, final String mUpgradeUrl) {
        // Check if download manager is available:
        int state = context.getPackageManager().getApplicationEnabledSetting(
                "com.android.providers.downloads");
        if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
                || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED) {

            // Cannot download using download manager
            String packageName = "com.android.providers.downloads";
            try {
                // Open the specific App Info page:
                Intent settingIntent = new Intent(
                        android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                settingIntent.setData(Uri.parse("package:" + packageName));
                context.startActivity(settingIntent);

            } catch (ActivityNotFoundException e) {
                // Open the generic Apps page:
                Intent settingIntent = new Intent(
                        android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                context.startActivity(settingIntent);
            }
        } else {
            DownloadManager dManager = (DownloadManager) context
                    .getSystemService(Context.DOWNLOAD_SERVICE);
            Uri uri = Uri.parse(mUpgradeUrl);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            // 设置下载路径和文件名
            // 判断下权限
            File skRoot = Environment.getExternalStorageDirectory();
            String downloadPath = skRoot + "/Download";
            File fileDir = new File(downloadPath);
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                if (fileDir.exists() && fileDir.isDirectory()) {
                } else {
                    fileDir.mkdirs();
                }
            }
            request.setDestinationInExternalPublicDir(downloadPath, "每日学");
            request.setDescription("每日学新版本下载");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setMimeType("application/vnd.android.package-archive");
            // 设置为可被媒体扫描器找到
            request.allowScanningByMediaScanner();
            // 设置为可见和可管理
            request.setVisibleInDownloadsUi(true);
            long refernece = dManager.enqueue(request);
            // 把当前下载的ID保存起来
            SharedPreferencesUtils.saveString("plato", refernece + "");
        }
    }

    /**
     * 判断当前应用在桌面是否有桌面快捷方式
     *
     * @param cx
     */
    public static boolean hasShortcut(Context cx) {
        boolean result = false;
        String title = null;
        try {
            final PackageManager pm = cx.getPackageManager();
            title = pm.getApplicationLabel(
                    pm.getApplicationInfo(cx.getPackageName(),
                            PackageManager.GET_META_DATA)).toString();
        } catch (Exception e) {
        }

        final String uriStr;
        if (android.os.Build.VERSION.SDK_INT < 8) {
            uriStr = "content://com.android.launcher.settings/favorites?notify=true";
        } else {
            uriStr = "content://com.android.launcher2.settings/favorites?notify=true";
        }
        final Uri CONTENT_URI = Uri.parse(uriStr);
        final Cursor c = cx.getContentResolver().query(CONTENT_URI, null,
                "title=?", new String[]{title}, null);
        if (c != null && c.getCount() > 0) {
            result = true;
        }
        return result;
    }

    /*public static void showToast(Activity context, int resource, String text) {
        View view = context.getLayoutInflater().inflate(resource, null);
        TextView textView = (TextView) view.findViewById(R.id.textview_show);
        textView.setText(text);
        Toast mToastCollect = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        mToastCollect.setView(view);
        mToastCollect.setGravity(Gravity.CENTER, 0, 0);
        mToastCollect.show();
    }*/

    public static String md5(String paramString) {
        String returnStr;
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramString.getBytes());
            returnStr = byteToHexString(localMessageDigest.digest());
            return returnStr;
        } catch (Exception e) {
            return paramString;
        }
    }

    /**
     * 将指定byte数组转换成16进制字符串
     *
     * @param b
     * @return
     */
    public static String byteToHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }

    /**
     * 判断当前是否有可用的网络以及网络类型 0：无网络 1：WIFI 2：CMWAP 3：CMNET
     *
     * @param context
     * @return
     */
    public static int isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return 0;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        NetworkInfo netWorkInfo = info[i];
                        if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                            return 1;
                        } else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                            String extraInfo = netWorkInfo.getExtraInfo();
                            if ("cmwap".equalsIgnoreCase(extraInfo)
                                    || "cmwap:gsm".equalsIgnoreCase(extraInfo)) {
                                return 2;
                            }
                            return 3;
                        }
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 判断当前是否有可用的网络以及网络类型 0：无网络 1：WIFI 2：CMWAP 3：CMNET
     *
     * @param context
     * @return
     */
    public static String getNetworkType(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return "No-Connect";
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        NetworkInfo netWorkInfo = info[i];
                        if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                            return "WIFI";
                        } else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                            String extraInfo = netWorkInfo.getExtraInfo();
                            if ("cmwap".equalsIgnoreCase(extraInfo)
                                    || "cmwap:gsm".equalsIgnoreCase(extraInfo)) {
                                return "CMWAP";
                            }
                            return "CMNET";
                        }
                    }
                }
            }
        }
        return "No-Connect";
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd HH:mm:ss
     */

    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getScreenPicHeight(int screenWidth, Bitmap bitmap) {
        int picWidth = bitmap.getWidth();
        int picHeight = bitmap.getHeight();
        int picScreenHeight = 0;
        picScreenHeight = (screenWidth * picHeight) / picWidth;
        return picScreenHeight;
    }


    private static Drawable createDrawable(Drawable d, Paint p) {

        BitmapDrawable bd = (BitmapDrawable) d;
        Bitmap b = bd.getBitmap();
        Bitmap bitmap = Bitmap.createBitmap(bd.getIntrinsicWidth(),
                bd.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(b, 0, 0, p); // 关键代码，使用新的Paint画原图，

        return new BitmapDrawable(bitmap);
    }

    /**
     * 设置Selector。 本次只增加点击变暗的效果，注释的代码为更多的效果
     */
    public static StateListDrawable createSLD(Context context, Drawable drawable) {
        StateListDrawable bg = new StateListDrawable();
        int brightness = 50 - 127;
        ColorMatrix cMatrix = new ColorMatrix();
        cMatrix.set(new float[]{1, 0, 0, 0, brightness, 0, 1, 0, 0,
                brightness,// 改变亮度
                0, 0, 1, 0, brightness, 0, 0, 0, 1, 0});

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));

        Drawable normal = drawable;
        Drawable pressed = createDrawable(drawable, paint);
        bg.addState(new int[]{android.R.attr.state_pressed,}, pressed);
        bg.addState(new int[]{android.R.attr.state_focused,}, pressed);
        bg.addState(new int[]{android.R.attr.state_selected}, pressed);
        bg.addState(new int[]{}, normal);
        return bg;
    }

    public static Bitmap getImageFromAssetsFile(Context ct, String fileName) {
        Bitmap image = null;
        AssetManager am = ct.getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }

    // public static <Params, Progress, Result> void executeAsyncTask(
    // AsyncTask<Params, Progress, Result> task, Params... params) {
    // if (Build.VERSION.SDK_INT >= 11) {
    // task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
    // } else {
    // task.execute(params);
    // }
    // }

    public static String getUploadtime(long created) {
        StringBuffer when = new StringBuffer();
        int difference_seconds;
        int difference_minutes;
        int difference_hours;
        int difference_days;
        int difference_months;
        long curTime = System.currentTimeMillis();
        difference_months = (int) (((curTime / 2592000) % 12) - ((created / 2592000) % 12));
        if (difference_months > 0) {
            when.append(difference_months + "月");
        }

        difference_days = (int) (((curTime / 86400) % 30) - ((created / 86400) % 30));
        if (difference_days > 0) {
            when.append(difference_days + "天");
        }

        difference_hours = (int) (((curTime / 3600) % 24) - ((created / 3600) % 24));
        if (difference_hours > 0) {
            when.append(difference_hours + "小时");
        }

        difference_minutes = (int) (((curTime / 60) % 60) - ((created / 60) % 60));
        if (difference_minutes > 0) {
            when.append(difference_minutes + "分钟");
        }

        difference_seconds = (int) ((curTime % 60) - (created % 60));
        if (difference_seconds > 0) {
            when.append(difference_seconds + "秒");
        }

        return when.append("前").toString();
    }

    public static boolean checkNetworkState() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) MyApplication
                .getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        // 1.判断是否有网络连接
        if (networkInfo != null && networkInfo.isAvailable()
                && networkInfo.isConnected()) {
            return true;
        }
        return false;
        /*
         * // 2.获取当前网络连接的类型信息 int networkType = networkInfo.getType(); if
		 * (ConnectivityManager.TYPE_WIFI == networkType) { // 当前为wifi网络 } else
		 * if (ConnectivityManager.TYPE_MOBILE == networkType) { // 当前为mobile网络
		 * }
		 */
    }

    // public static boolean hasToken(Context ct) {
    // String token = SharePrefUtil.getString(ct, "token", "");
    // if (TextUtils.isEmpty(token)) {
    // return false;
    // } else {
    // return true;
    // }
    // }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    public static String formatDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(new Date());
    }


}
