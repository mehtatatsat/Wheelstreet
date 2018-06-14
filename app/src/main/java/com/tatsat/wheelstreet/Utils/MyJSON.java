package com.tatsat.wheelstreet.Utils;

import android.content.Context;
import android.util.Log;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by abc on 23-05-2016.
 */
public class MyJSON {


    // Home Screen JSON Files
    public static final String FILENAME_REDDIT_LIST = "Question_LIST";


    public static void saveData(Context context, String mJsonResponse, String fileName) {
        try {

            Logger.error("SavePath", context.getFilesDir().getPath() + "/" + fileName);

            FileWriter file = new FileWriter(context.getFilesDir().getPath() + "/" + fileName);
            file.write(mJsonResponse);
            file.flush();
            file.close();
        } catch (IOException e) {
            Log.e("TAG", "Error in Writing: " + e.getLocalizedMessage());
        } catch (Exception e) {
            Log.e("TAG", "Error in Func: " + e.getLocalizedMessage());
        }
    }

    public static String getData(Context context, String fileName) {
        try {

            Logger.error("getPath", context.getFilesDir().getPath() + "/" + fileName);

            File f = new File(context.getFilesDir().getPath() + "/" + fileName);
            //check whether file exists
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);

            is.close();
            return new String(buffer);
        } catch (IOException e) {

            Log.e("TAG", "Error in Reading: " + e.getLocalizedMessage());
            return null;

        } catch (Exception e) {

            Log.e("TAG", "Error in Func: " + e.getLocalizedMessage());
            return null;

        }
    }


    /**
     * Delete all JSON files
     *
     * @param context
     */
    public static void deleteAllJSONFiles(Context context) {

        File f = new File(context.getFilesDir().getPath());

        try {

            FileUtils.cleanDirectory(f);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
