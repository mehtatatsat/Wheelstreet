package com.tatsat.wheelstreet.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.tatsat.wheelstreet.MainActivity;
import com.tatsat.wheelstreet.R;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit.mime.TypedString;


/**
 * Created by Rahul-PC on 8/1/2016.
 */

public class Helper {


    public static Dialog dialog;

    //public static CustomSimpleMessageDialog1 customSimpleMessageDialog1;


    /**
     * Show Simple Dialog with message and listener
     *
     * @param context
     * @param simpleDialogOnClickListener
     * @param dialog_title
     * @param dialog_message
     * @param flag_for_purpose
     */
 /*   public static void showCustomSimpleDialog1(Context context, CustomSimpleMessageDialog1.SimpleDialogOnClickListener1 simpleDialogOnClickListener, String dialog_title, String dialog_message, boolean flag_for_purpose) {

        customSimpleMessageDialog1 = new CustomSimpleMessageDialog1(context, simpleDialogOnClickListener, dialog_title, dialog_message, flag_for_purpose);

        customSimpleMessageDialog1.show();

    }*/


    /**
     * changeStatusBarColor Default
     *
     * @param context
     */


    public static void changeStatusBarColorDefault(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((Activity) context).getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(context.getResources().getColor(R.color.blue_btn_bg_color));
        }
    }

    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    public static DisplayMetrics getScreenWidthHieght(Context context) {

        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) (context)).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        Logger.error("height", height + "");
        Logger.error("width", width + "");

        return displaymetrics;
    }


    /**
     * changeStatusBarColor Black
     *
     * @param context
     */


    /**
     * changeStatusBarColor Black
     *
     * @param context
     */
    public static void changeStatusBarColorBlack(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((Activity) context).getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(context.getResources().getColor(R.color.black));
        }
    }

    public static void setupFont(Context context, TextView textView, String fontName) {

        if (fontName == null) {
            return;
        }
        Typeface tf = FontCache.get(fontName, context);
        if (tf != null) {
            textView.setTypeface(tf);
        }

        // Typeface font = Typeface.createFromAsset(context.getAssets(), fontName);
        // textView.setTypeface(font);

    }

    public static void setupFont(Context context, Button button, String fontName) {

        if (fontName == null) {
            return;
        }
        Typeface tf = FontCache.get(fontName, context);
        if (tf != null) {
            button.setTypeface(tf);
        }

        //   Typeface font = Typeface.createFromAsset(context.getAssets(), fontName);
        //   button.setTypeface(font);
    }

    public static void setupFont(Context context, EditText editText, String fontName) {

        if (fontName == null) {
            return;
        }
        Typeface tf = FontCache.get(fontName, context);
        if (tf != null) {
            editText.setTypeface(tf);
        }

        // Typeface font = Typeface.createFromAsset(context.getAssets(), fontName);
        // editText.setTypeface(font);
    }


    public static void setupFont(Context context, CheckBox checkBox, String fontName) {

        if (fontName == null) {
            return;
        }
        Typeface tf = FontCache.get(fontName, context);
        if (tf != null) {
            checkBox.setTypeface(tf);
        }

        // Typeface font = Typeface.createFromAsset(context.getAssets(), fontName);
        // editText.setTypeface(font);
    }

    public static void showMessageDialog(Context context, String toastDetails) {

        new SweetAlertDialog(context)
                .setConfirmText(context.getString(R.string.ok))
                .setTitleText(toastDetails)
                .show();


    }

    public static void showMessageDialog(final Context context, String toastDetails, final boolean flag_go_back) {

        new SweetAlertDialog(context)
                .setTitleText(toastDetails)
                .setConfirmText(context.getString(R.string.ok))
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        if (flag_go_back) {

                            ((Activity) context).onBackPressed();

                        } else {

                            sweetAlertDialog.dismiss();

                        }
                    }
                })
                .show();
    }

    public static void ShowDialog(final Activity context, String strmessage) {

        new SweetAlertDialog(context)
                .setConfirmText(context.getString(R.string.ok))
                .setTitleText(strmessage)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent i = new Intent(context, MainActivity.class);
                        context.startActivity(i);
                        context.finish();

                    }
                })
                .show();
    }


    public static void showMessageDialogforFrag(final Context context, String toastDetails, final boolean flag_go_back) {

        new SweetAlertDialog(context)
                .setTitleText(toastDetails)
                .setConfirmText(context.getString(R.string.ok))
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        if (flag_go_back) {

                            ((Activity) context).finish();

                        } else {

                            sweetAlertDialog.dismiss();

                        }
                    }
                })
                .show();
    }


    /**
     * @param value
     * @return
     */
    public static String getTrimmedString(String value) {

        if (value == null) {
            return "";
        } else if (value.equals("null") || value.equals("Null") || value.equals("NULL")) {

            return "";
        } else if (value.equals("")) {

            return "";
        } else {
            return value;
        }
    }


    /**
     * Check file exist or not
     *
     * @param file
     * @return
     */
    public static boolean checkFileExistsOrNot(java.io.File file) {
        boolean fileExists = false;
        if (file != null) {
            if (!file.exists() && file.length() == 0) {
                fileExists = false;
            } else {
                fileExists = true;
            }
        }
        return fileExists;
    }

    /**
     * Hide Keyboard
     *
     * @param context
     */
   /* public static void hideSoftKeyboard(Context context) {

        ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }*/

    /**
     * Soft Keyboard
     *
     * @param context
     */
    public static void showSoftKeyboard(Context context) {

        //Show soft-keyboard:
        ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

    }


    /**
     * @param context
     * @return
     */
    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {

            NetworkInfo[] info = connectivity.getAllNetworkInfo();

            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    /**
     * @param context
     */

    public static void showProgressDialog(Context context) {

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.web_view_widget);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.setCanceledOnTouchOutside(false);

        // progressWithoutBg = (CircleProgressBar) dialog.findViewById(R.id.progress1);

        // progressWithoutBg.setColorSchemeResources(R.color.color_blue);

        ImageView img = (ImageView) dialog.findViewById(R.id.progress_bar);

        //img.setBackgroundResource(R.drawable.animation_loading);

        // Get the background, which has been compiled to an AnimationDrawable object.
        final AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        // Start the animation (looped playback by default).
        frameAnimation.start();

       /* ProgressBar mProgressBar = (ProgressBar) dialog.findViewById(R.id.google_progress);

        Resources res = context.getResources();
        AnimationDrawable drawable = (AnimationDrawable) res.getDrawable(R.drawable.progress_drawable_smiley);

       *//* Drawable progressDrawable = new FoldingCirclesDrawable.Builder(context)
                .build();*//*

        mProgressBar.setIndeterminateDrawable(drawable);*/

        if (!((Activity) context).isFinishing()) {
            //show dialog
            if (dialog.isShowing()) {
                return;
            }
            dialog.show();
        }

    }

   /* public static void showProgressDialog(Context context) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progressbar_dialog_custom);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.setCanceledOnTouchOutside(false);

        progressWithoutBg = (CircleProgressBar) dialog.findViewById(R.id.progress1);

        progressWithoutBg.setColorSchemeResources(R.color.color_blue);

       *//* ProgressBar mProgressBar = (ProgressBar) dialog.findViewById(R.id.google_progress);

        Resources res = context.getResources();
        AnimationDrawable drawable = (AnimationDrawable) res.getDrawable(R.drawable.progress_drawable_smiley);

       *//**//* Drawable progressDrawable = new FoldingCirclesDrawable.Builder(context)
                .build();*//**//*

        mProgressBar.setIndeterminateDrawable(drawable);*//*

        if (!((Activity) context).isFinishing()) {
            //show dialog
            if (dialog.isShowing()) {
                return;
            }
            dialog.show();
        }


    }*/

    /**
     * To Hide Dialog
     */
   /* public static void hideDialog() {

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (dialog != null && dialog.isShowing())
                    dialog.dismiss();
            }
        }, 2000);

    }*/

    /**
     * To Hide Dialog
     */
    public static void hideDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    /**
     * Get Version Name
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(
                    context.getPackageName(), 0);
            String version = info.versionName;

            return version;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public static void showToast(Context context, String s) {

        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();

    }

    public static void hideSoftKeyboard(Context context) {
        if (((Activity) context).getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) ((Activity) context).getSystemService(((Activity) context).INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), 0);
        }
    }


    public static void hideSoftKeyboardOut(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }


    /**
     * Get RequestBody From String
     *
     * @param requestText
     * @return
     */
  /*  public static TypedString getTypedBodyFromString(String requestText) {
        try {
            return new TypedString(requestText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

    /**
     * get Unix Time
     *
     * @return
     */

    public static long getUnixTime() {

//        long timeMillis = System.currentTimeMillis();
//        long timeSeconds = TimeUnit.MILLISECONDS.toSeconds(timeMillis);
//        return timeSeconds * 10000000;

        return System.currentTimeMillis();
    }

//////////////////////////new data adde


    public static CircleProgressBar progressWithoutBg;


    /**
     * Get RequestBody From String
     *
     * @param requestText
     * @return
     */
    public static TypedString getTypedBodyFromString(String requestText) {
        try {
            return new TypedString(requestText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get Unix Time
     *
     * @return
     */

    public static String changetoSign(String str) {

        String chnagedStr = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            chnagedStr = Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY) + " ";
        } else {
            chnagedStr = Html.fromHtml(str) + " ";
        }
        return chnagedStr;
    }

    public static String getZeroStringIfBlank(String string) {

        if (string == null)
            return "0";

        if (string.trim().equalsIgnoreCase("")) {
            return "0";
        }

        return string;
    }

}
