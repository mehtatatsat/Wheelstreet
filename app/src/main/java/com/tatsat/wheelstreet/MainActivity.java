package com.tatsat.wheelstreet;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.tatsat.wheelstreet.Utils.Constants;
import com.tatsat.wheelstreet.Utils.Helper;
import com.tatsat.wheelstreet.Utils.SessionManager;
import com.tatsat.wheelstreet.database.BazarPriveeDataSource;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ImageView fb_button;
    Context context;
    String TAG = "TEstDemo";
    PackageInfo info;
    private static final int FACKBOOK_LOGIN_REQUEST = 64206;
    SessionManager sessionManager;
    BazarPriveeDataSource bazarPriveeDataSource;


    private CallbackManager mCallbackManager;
    private String sFacebookID, sFacebookUserName, sFacebookFirstName, sFacebookLastName, sFacebookGender="", sFacebookEmail="", sFacebookProfilePicture, sFacebookUserCity;
    private FacebookCallback<LoginResult> mFacebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Log.i(TAG, "profile onSuccess");

            // If login succeeds, the LoginResult parameter has the new AccessToken, and the most recently granted or declined permissions.
            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {

                            Constants.LOGIN_FLAG = Constants.FACEBOOK_LOGIN;
                            Log.v("LoginActivityResponse ", response.toString());
                            Log.i(TAG, "Profile Json Response" + object);
                            if (!object.optString("id").equalsIgnoreCase("")) {
                                sFacebookID = object.optString("id");
                                sessionManager.saveStringVValue(Constants.KEY_USER_ID, "" + sFacebookID);
                                Log.i(TAG, "Profile Facebook ID " + sFacebookID);
                            }
                            if (!object.optString("name").equalsIgnoreCase("")) {
                                sFacebookUserName = object.optString("name");
                                sessionManager.saveStringVValue(Constants.KEY_USER_NAME, "" + sFacebookUserName);
                                sessionManager.saveStringVValue(Constants.KEY_USER_AGE, "" );
                                sessionManager.saveStringVValue(Constants.KEY_USER_PHONE, "");
                                Log.i(TAG, "Profile Name " + sFacebookUserName);
                            }
                            if (!object.optString("first_name").equalsIgnoreCase("")) {
                                sFacebookFirstName = object.optString("first_name");
                                Log.i(TAG, "Profile First Name " + sFacebookFirstName);
                            }
                            if (!object.optString("last_name").equalsIgnoreCase("")) {
                                sFacebookLastName = object.optString("last_name");
                                Log.i(TAG, "Profile Last Name " + sFacebookLastName);
                            }
                            if (!object.optString("gender").equalsIgnoreCase("")) {
                                sFacebookGender = object.optString("gender");

                                Log.i(TAG, "Profile Gender " + sFacebookGender);
                            }

                            if (!object.optString("email").equalsIgnoreCase("")) {
                                sFacebookEmail = object.optString("email");
                                Log.i(TAG, "Profile in onSuccess " + sFacebookEmail);
                                sessionManager.saveStringVValue(Constants.KEY_USER_EMAIL, "" + sFacebookEmail);
                            } /*else {
                                sFacebookEmail = "0";
                                Log.i(TAG, "Profile in onSuccess " + sFacebookEmail);
                            }*/

                            if (!object.optJSONObject("picture").optJSONObject("data").optString("url").equalsIgnoreCase("")) {
                                sFacebookProfilePicture = object.optJSONObject("picture").optJSONObject("data").optString("url");
                                Log.i(TAG, "Profile Picture URL in Graph : " + sFacebookProfilePicture);
                                sessionManager.saveStringVValue(Constants.KEY_USER_PROFILE_PICTURE, "" + sFacebookProfilePicture);
                            }
                            sessionManager.saveStringVValue(Constants.KEY_PINCODE_ID,"0");

                            bazarPriveeDataSource.open();
                            bazarPriveeDataSource.create(sFacebookID,sFacebookUserName,"",sFacebookEmail,sFacebookGender,"");
                            bazarPriveeDataSource.close();

                            Intent intent = new Intent(MainActivity.this,ProfilePageActivity.class);
                            startActivity(intent);
                            finish();



                           /* if (!object.optJSONObject("location").optString("name").equalsIgnoreCase("")) {
                                sFacebookUserCity = object.optJSONObject("location").optString("name");
                                Log.i(TAG, "Profile City " + sFacebookUserCity);
                            }*/


                            // pass All Data in Api


                          //  callApiforSocialLogin();
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,first_name,last_name,name,email,gender,location,picture.width(256).height(256)");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {
            Log.i(TAG, "Profile onCancel");

        }

        @Override
        public void onError(FacebookException e) {
            Log.i(TAG, "Profile onError " + e);
            if (e instanceof FacebookAuthorizationException) {
                if (AccessToken.getCurrentAccessToken() != null) {
                    LoginManager.getInstance().logOut();
                }
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(MainActivity.this);
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);
        mCallbackManager = CallbackManager.Factory.create();
        initialization();
        idMapping();
        setOnclicklistnear();


            /* try {
            info = getPackageManager().getPackageInfo("com.tatsat.wheelstreet", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }*/
    }

    private void initialization() {
        context = this;
        sessionManager = new SessionManager(context);
        bazarPriveeDataSource = new BazarPriveeDataSource(context);
        if (!sessionManager.retrieveValuefromStringKey(Constants.KEY_USER_ID).equalsIgnoreCase("")){
            Intent intent = new Intent(MainActivity.this,ProfilePageActivity.class);
            startActivity(intent);
            finish();
        }
    }


    private void idMapping() {

        fb_button = (ImageView) findViewById(R.id.fb_button);
    }

    private void setOnclicklistnear() {

        fb_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Helper.isConnectingToInternet(context)) {
                    facebookLogin();

                } else {
                    Helper.showMessageDialog(context, "No Internet Connection");
                }
            }
        });
    }

    public void facebookLogin() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile" ,"email"));
        LoginManager.getInstance().registerCallback(mCallbackManager, mFacebookCallback);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                case FACKBOOK_LOGIN_REQUEST:
                    mCallbackManager.onActivityResult(requestCode, resultCode, data);
                   // UIUtil.log("Login Facebook On Activity result called");
                    break;


                default:
                    super.onActivityResult(requestCode, resultCode, data);
                    break;
            }
        }
    }


}
