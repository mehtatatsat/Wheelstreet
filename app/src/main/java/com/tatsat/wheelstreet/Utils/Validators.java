package com.tatsat.wheelstreet.Utils;

import android.util.Patterns;

/**
 * Created by Rahul on 23/4/15.
 */
public class Validators {

    /**
     * Check Mail Is Valid Or Not
     *
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
        // TODO Auto-generated method stub

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }

        return false;
    }

    /**
     * Check Phone Is Valid Or Not
     *
     * @param phone
     * @return
     */
    public static boolean isValidPhone(String phone) {
        // TODO Auto-generated method stub

        if (Patterns.PHONE.matcher(phone).matches()) {
            return true;
        }

        return false;
    }

    /**
     * Check data is empty or not
     *
     * @param data
     * @return
     */
    public static boolean isEmpty(String data) {
        // TODO Auto-generated method stub

        if (data == null) {

            return true;
        }

        if (data.trim().equals("")) {
            return true;
        }

        return false;
    }


    /**
     * Check length
     *
     * @param data
     * @return
     */
    public static boolean isLengthMatch(String data, int required_length) {
        // TODO Auto-generated method stub

        if (data.length() == required_length) {

            return true;
        }

        return false;
    }

    /**
     * Check length
     *
     * @param data
     * @return
     */
    public static boolean isLengthMatch(String data, int min_length, int max_length) {
        // TODO Auto-generated method stub

        if (data.length() >= min_length && data.length() <= max_length) {

            return true;
        }

        return false;
    }

    /**
     * Check Only Digits
     *
     * @param data
     * @return
     */
    public static boolean checkOnlyDigits(String data) {

        if (data.matches("[0-9]+")) {

            return true;

        }

        return false;
    }

    /**
     * Check Only Alphabates
     *
     * @param data
     * @return
     */
    public static boolean checkOnlyAlphabets(String data) {

        if (data.matches("[A-Za-z]+")) {

            return true;

        }

        return false;
    }


}
