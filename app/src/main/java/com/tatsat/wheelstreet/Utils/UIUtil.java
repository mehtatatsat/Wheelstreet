package com.tatsat.wheelstreet.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit.client.Response;
import retrofit.mime.TypedInput;

/**
 * Created by Admin1 on 4/18/2016.
 */
public class UIUtil {




    private static final String BazarPrivee ="BazarPrivee" ;

    /*
        For Log
    **/
    public static void log(String message) {
        Log.e(BazarPrivee, message);
    }

    /*
    *   For Show Toast
    * */

    public static void toast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }




    public static void printKeyHash(Context context){
        // Add code to print out the key hash
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    "com.app.BazarPrivee",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }


    public static void setAlphaOnView(View view,float startAlpha,float endAlpha){

        AlphaAnimation alphaUp = new AlphaAnimation(startAlpha, endAlpha);
        alphaUp.setFillAfter(true);
        alphaUp.setDuration (0);
        view.startAnimation(alphaUp);

    }



    public static String gettingResponseInString(Response response) {
        TypedInput body = response.getBody();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(body.in()));
            StringBuilder out = new StringBuilder();
            String newLine = System.getProperty("line.separator");
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
                out.append(newLine);
            }

            // Prints the correct String representation of body.
            System.out.println(out);
            String result = out.toString();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }




}
