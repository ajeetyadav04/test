package com.assign.test.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class BasicUtility {

    private static Toast mToast;
    private static Snackbar mSnackbar;

    private static String HMAC_HASHING_ALGO = "HmacSHA1";
    private static String HMAC_KEY = "MY_SECURE_KEY";
    private static final String HMAC_ERROR = "hmac_error";
    private static NotificationCompat.Builder builder;
    private static RemoteViews remoteViews;
    private static Notification notification;
    private static String hour;
    private static String min;
    private static Calendar calendar = Calendar.getInstance();
    private static boolean isDatePatternInMilis;

    private static NotificationManager notificationManager;

    //    private static String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}";
    private static String PASSWORD_REGEX = "(?=^.{8,16}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";

    public static void showToastLong(Context context, String message) {

        if (context != null && !TextUtils.isEmpty(message)) {
            if (mToast != null)
                mToast.cancel();
            mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            mToast.show();
        }
    }





    public static void showSnackBar(View view, String message) {
        if (view != null && !TextUtils.isEmpty(message)) {
            mSnackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
            mSnackbar.show();
        }
    }

    public static Intent doShare() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "");
        i.putExtra(Intent.EXTRA_TEXT, "" + " ");
        return i;
    }

    public static void showToastShort(Context context, String message) {

        if (context != null && !TextUtils.isEmpty(message)) {
            if (mToast != null)
                mToast.cancel();
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            mToast.show();
        }
    }








    public static boolean isEmptyString(String string) {
        if (TextUtils.isEmpty(string))
            return true;
        else {
            if (TextUtils.isEmpty(string.trim()))
                return true;
            else return false;
        }
    }






    public static void hideSoftKeyboard(Context context) {
        if (context != null) {
            InputMethodManager inputManager = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);

            // check if no view has focus:
            View v = ((Activity) context).getCurrentFocus();
            if (v == null)
                return;
            if (inputManager != null)
                inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public static String objectToJsonString(Object jsonObject) {
        Gson gson = new Gson();
        return gson.toJson(jsonObject);
    }

    public static Object jsonStringToObject(String data, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(data, type);
    }









  /*
  *  int[][] backgroundStates = new int[][]{
                new int[]{android.R.attr.state_checked}, // checked
                new int[]{-android.R.attr.state_checked}//unchecked
        };

        int[] backgroundColors = new int[]{
                Color.BLACK,
                Color.WHITE
        };

  * */





    public static String convertArrayInString(ArrayList<String> list) {

        StringBuilder inviteMember = new StringBuilder();
        int i = 0;
        inviteMember.append('[');
        for (String s : list) {
            i++;
            if (i == list.size())
                inviteMember.append('"' + s + '"');
            else
                inviteMember.append('"' + s + '"' + ',');

        }
        inviteMember.append(']');
        return inviteMember.toString();
    }



    public static boolean isSoftKeyOpen(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        if (imm.isAcceptingText()) {
            return true;
        } else {
            return false;
        }
    }

    public static String hmacThumbImage(String url) {
        String message = url;
        try {
            byte[] bytes = hmac(HMAC_HASHING_ALGO, HMAC_KEY.getBytes(), message.getBytes());
            String base64Hmac = Base64.encodeToString(bytes, Base64.URL_SAFE);
            return base64Hmac.replace("\n", "");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return HMAC_ERROR;
    }

    private static byte[] hmac(String algorithm, byte[] key, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(key, algorithm));
        return mac.doFinal(message);
    }

    public static String changeDateFormat(String date, String source, String target) {


        SimpleDateFormat dateFormat = new SimpleDateFormat(source);
        Date sourceDate = null;
        String targetDate = null;
        try {
            sourceDate = dateFormat.parse(date);
            SimpleDateFormat targetFormat = new SimpleDateFormat(target);
            targetDate = targetFormat.format(sourceDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return targetDate;
    }
    public static boolean toComapareDate(String date1,String date2){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date  dd1=null;
        Date  dd2=null;
        try {
            dd1= format.parse(date1);
            dd2 = format.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (dd1.compareTo(dd2) <= 0) {
            return false;
        }
        else
            return true;
    }

}
