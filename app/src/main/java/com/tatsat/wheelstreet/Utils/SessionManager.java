package com.tatsat.wheelstreet.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Rahul on 27/02/2015.
 */
public class SessionManager {

    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_FLAG = "user_flag";
    public static final String KEY_CONTACT_NO = "contact_no";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_DOB = "dob";
    public static final String KEY_USER_IMAGE = "image";

    public static final String KEY_USER_NAME = "username";
    public static final String KEY_USER_FULL_NAME = "fullname";
    public static final String KEY_USER_PROFILE_PICTURE = "user_profile_picture";
    public static final String KEY_SOCIAL_ID = "social_id";

    public static final String KEY_FOLLOWER_COUNT = "follower_count";
    public static final String KEY_FOLLOWING_COUNT = "following_count";
    public static final String KEY_SHOUTS_COUNT = "shouts_count";


    public static final String KEY_USER_EMAIL = "email";

    public static final String KEY_LOGIN = "check_login";


    public static final String KEY_BIO = "bio";
    public static final String KEY_WEBSITE = "website";
    public static final String KEY_PHONE = "phone";


    public static final String KEY_LOGIN_FLAG = "flag";






    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferencesFCM;


    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public SessionManager(Context context, String fcm) {
        this.context = context;
        sharedPreferencesFCM = context.getSharedPreferences(Constants.PREFERENCE_NAME_FCM, Context.MODE_PRIVATE);
    }



    /**
     * Print All Session Values
     */
    public void printAllValuesOfSession() {

        Map<String, String> map = new HashMap<>();

        map.put("KEY_USER_ID", getKeyUserId() + "");


        Logger.error("SESSION :: ", map.toString());

    }

    //TODO: Getting All Value using Shared Prefrence
    public String getKeyUserId() {
        return sharedPreferences.getString(KEY_USER_ID, "");
    }


    //TODO:Setting All User Values In Shared Prefrence
    public void setKeyUserId(String userId) {
        sharedPreferences.edit().putString(KEY_USER_ID, userId).apply();
    }


    public String getKeyUserFlag() {
        return sharedPreferences.getString(KEY_USER_FLAG, "");
    }


    public void setKeyUserFlag(String KEY_USER_FLAG) {
        sharedPreferences.edit().putString(SessionManager.KEY_USER_FLAG, KEY_USER_FLAG).apply();
    }

    public String getKeyContactNo() {
        return sharedPreferences.getString(KEY_CONTACT_NO, "");
    }


    public void setKeyContactNo(String KEY_CONTACT_NO) {
        sharedPreferences.edit().putString(SessionManager.KEY_CONTACT_NO, KEY_CONTACT_NO).apply();
    }

    public String getKeyGender() {
        return sharedPreferences.getString(KEY_GENDER, "");
    }


    public void setKeyGender(String KEY_GENDER) {
        sharedPreferences.edit().putString(SessionManager.KEY_GENDER, KEY_GENDER).apply();
    }


    public String getKeyDob() {
        return sharedPreferences.getString(KEY_DOB, "");
    }

    public void setKeyDob(String KEY_DOB) {
        sharedPreferences.edit().putString(SessionManager.KEY_DOB, KEY_DOB).apply();
    }

    public String getKeyUserImage() {
        return sharedPreferences.getString(KEY_USER_IMAGE, "");
    }


    public void setKeyUserImage(String KEY_USER_IMAGE) {
        sharedPreferences.edit().putString(SessionManager.KEY_USER_IMAGE, KEY_USER_IMAGE).apply();
    }


    public String getUserName() {
        return sharedPreferences.getString(KEY_USER_NAME, "");
    }


    public void setKeyUserName(String KEY_USER_NAME) {
        sharedPreferences.edit().putString(KEY_USER_NAME, KEY_USER_NAME).apply();
    }

    public String getKeyUserEmail() {
        return sharedPreferences.getString(KEY_USER_EMAIL, "");
    }


    public void setKeyUserEmail(String KEY_USER_NAME) {
        sharedPreferences.edit().putString(KEY_USER_EMAIL, KEY_USER_EMAIL).apply();
    }






    public boolean isLoggedin() {
        return sharedPreferences.getBoolean(KEY_LOGIN, false);
    }

    /**
     * For Logout ANy Loggedin User
     */
    public void logoutUser() {
        sharedPreferences.edit().clear().apply();
    }


    public void saveStringVValue(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public void saveStringVValueFCM(String key, String value) {
        sharedPreferencesFCM.edit().putString(key, value).apply();
    }

    public void saveBooleanValuefrom(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public boolean retrieveValueBooleanKey(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public String retrieveValuefromStringKey(String key) {
        return sharedPreferences.getString(key, "");
    }

    public String retrieveValuefromStringKeyFCM(String key) {
        return sharedPreferencesFCM.getString(key, "");
    }


    public void saveBooleanValue(String key, boolean value) {
        sharedPreferencesFCM.edit().putBoolean(key, value).apply();
    }


    public boolean retrieveValuefromBooleanKey(String key) {
        return sharedPreferencesFCM.getBoolean(key, false);
    }


    public void saveIntegerValue(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }




    public String getKeyBIO() {
        return sharedPreferences.getString(KEY_BIO, "");
    }

    public String getKeyWEBSITE() {
        return sharedPreferences.getString(KEY_WEBSITE, "");
    }

    public String getKeyPHONE() {
        return sharedPreferences.getString(KEY_PHONE, "");
    }

    public String getKeyFullName() {
        return sharedPreferences.getString(KEY_USER_FULL_NAME, "");
    }









    public int retrieveValuefromIntegerKey(String key) {
        int value = sharedPreferences.getInt(key, -1);
        if (key.equalsIgnoreCase(KEY_FOLLOWER_COUNT)) {
            if (value == -1) {
                return 0;
            }
        }

        if (key.equalsIgnoreCase(KEY_FOLLOWING_COUNT)) {
            if (value == -1) {
                return 0;
            }
        }
        if (key.equalsIgnoreCase(KEY_SHOUTS_COUNT)) {
            if (value == -1) {
                return 0;
            }
        }
        return value;
    }

    public void removeAllUSerData() {
        sharedPreferences.edit().clear().apply();
    }

    public void removeValueFromKey(String key) {
        sharedPreferences.edit().remove(key).apply();
    }



    public String getKEY_LOGIN_FLAG() {
        return sharedPreferences.getString(KEY_LOGIN_FLAG, "");
    }


    public void saveNotificationONOFFSettings(String key,int soundNotificationOnOffValue) {
        sharedPreferences.edit().putInt(key,soundNotificationOnOffValue).apply();
    }






}
