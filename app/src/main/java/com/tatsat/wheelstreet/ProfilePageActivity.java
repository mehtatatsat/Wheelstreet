package com.tatsat.wheelstreet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.tatsat.wheelstreet.Utils.Constants;
import com.tatsat.wheelstreet.Utils.Helper;
import com.tatsat.wheelstreet.Utils.MyJSON;
import com.tatsat.wheelstreet.Utils.SessionManager;
import com.tatsat.wheelstreet.Utils.Validators;
import com.tatsat.wheelstreet.database.BazarPriveeDataSource;
import com.tatsat.wheelstreet.models.Questions;
import com.tatsat.wheelstreet.rest.APIHandler;
import com.tatsat.wheelstreet.rest.CancelableCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.tatsat.wheelstreet.Utils.MyJSON.FILENAME_REDDIT_LIST;

public class ProfilePageActivity extends AppCompatActivity {

    SessionManager sessionManager;
    Context context;
    EditText edt_name, edt_age, edt_email, edt_mobile;
    ImageView iv_profile;
    List<Questions.Datum> getQuestions;
    TextView txt_signup;
    BazarPriveeDataSource bazarPriveeDataSource;
    String selectedGender = "male";
    RadioButton rd_male,rd_female;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        initialization();

        idMapping();

        setOnclicklistnear();

    }

    private void setOnclicklistnear() {


        rd_female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b){
                    selectedGender = "FeMale";
                }

            }
        });

        rd_male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b){
                    selectedGender = "Male";
                }

            }
        });


        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validationChecked()){
                    bazarPriveeDataSource.open();
                    bazarPriveeDataSource.create(sessionManager.retrieveValuefromStringKey(Constants.KEY_USER_ID),edt_name.getText().toString(),edt_age.getText().toString(),edt_email.getText().toString(),selectedGender,edt_mobile.getText().toString());
                    bazarPriveeDataSource.close();


                    if (!getIntent().hasExtra("asd")){
                        if (getQuestions != null) {
                            sessionManager.saveStringVValue(Constants.KEY_PINCODE_ID,"1");
                            Intent intent = new Intent(context, ChatQuestionActivtity.class);
                            startActivity(intent);
                            finish();
                        }
                    }else{
                        Intent intent = new Intent(context, ChatQuestionActivtity.class);
                        startActivity(intent);
                        finish();
                    }
                }

            }
        });
    }

    /**
     * Check Validation for Sign up
     *
     * @return
     */
    private boolean validationChecked() {


        if (Validators.isEmpty(edt_name.getText().toString())) {

            Helper.showMessageDialog(context, "Please enter Name");

            return false;

        }


        if (Validators.isEmpty(edt_age.getText().toString())) {

            Helper.showMessageDialog(context, "Please enter age");

            return false;

        }

        if (Validators.isEmpty(edt_email.getText().toString())) {

            Helper.showMessageDialog(context, "Please enter Email Address");

            return false;

        }

        if (Validators.isEmpty(edt_mobile.getText().toString())) {

            Helper.showMessageDialog(context, "Please enter Phone Number");

            return false;

        }

        if (edt_mobile.getText().toString().length() < 10) {

            Helper.showMessageDialog(context, "Please enter valid Phone Number");

            return false;

        }

        if (!Validators.isValidEmail(edt_email.getText().toString())) {

            Helper.showMessageDialog(context, "Please enter valid Email Address");

            return false;

        }

        if (!Validators.isValidEmail(edt_email.getText().toString())) {

            Helper.showMessageDialog(context, "Please enter valid Email Address");

            return false;

        }

        if (selectedGender.equalsIgnoreCase("")){
            Helper.showMessageDialog(context, "Please select gender.");
            return false;
        }
        return true;
    }



    private void idMapping() {
        edt_name = findViewById(R.id.edt_name);
        edt_age = findViewById(R.id.edt_age);
        edt_email = findViewById(R.id.edt_email);
        edt_mobile = findViewById(R.id.edt_mobile);
        iv_profile = findViewById(R.id.iv_profile);
        txt_signup = findViewById(R.id.txt_signup);
        rd_male = findViewById(R.id.rd_male);
        rd_female = findViewById(R.id.rd_female);


        edt_name.setText(sessionManager.retrieveValuefromStringKey(Constants.KEY_USER_NAME));
        edt_age.setText(sessionManager.retrieveValuefromStringKey(Constants.KEY_USER_AGE));
        edt_email.setText(sessionManager.retrieveValuefromStringKey(Constants.KEY_USER_EMAIL));
        edt_mobile.setText(sessionManager.retrieveValuefromStringKey(Constants.KEY_USER_PHONE));


        bazarPriveeDataSource.open();
        edt_name.setText(bazarPriveeDataSource.gettingAllCartDetails().get(0).getItemName());
        edt_age.setText(bazarPriveeDataSource.gettingAllCartDetails().get(0).getItemAge());
        edt_email.setText(bazarPriveeDataSource.gettingAllCartDetails().get(0).getItemEmail());
        edt_mobile.setText(bazarPriveeDataSource.gettingAllCartDetails().get(0).getItemMobileNumber());
        if (!bazarPriveeDataSource.gettingAllCartDetails().get(0).getItemGender().equalsIgnoreCase("")){
            selectedGender = bazarPriveeDataSource.gettingAllCartDetails().get(0).getItemGender();
        }
        bazarPriveeDataSource.close();

        if (selectedGender.equalsIgnoreCase("female")){
            rd_female.setChecked(true);
        }else{
            rd_male.setChecked(true);

        }



        Picasso.with(context).load(sessionManager.retrieveValuefromStringKey(Constants.KEY_USER_PROFILE_PICTURE)).placeholder(R.drawable.ic_action_profile).error(R.drawable.ic_action_profile).into(iv_profile);
    }

    private void initialization() {
        context = this;
        bazarPriveeDataSource = new BazarPriveeDataSource(context);
        sessionManager = new SessionManager(context);
        if (!sessionManager.retrieveValuefromStringKey(Constants.KEY_PINCODE_ID).equalsIgnoreCase("1")){
            if (Helper.isConnectingToInternet(context)) {
                callApiforHomePage();

            } else {
                Helper.showMessageDialog(context, "No Internet Connection");
            }
        }else{

            if (!getIntent().hasExtra("asd")){
                Intent intent = new Intent(context,ChatQuestionActivtity.class);
                startActivity(intent);
                finish();
            }


        }

    }

    private void callApiforHomePage() {

       // Helper.showProgressDialog(context);

        APIHandler.getApiService().api_homepage_apis( new CancelableCallback<Questions>() {
                    @Override
                    public void onSuccess(Questions allBroadcastList, Response response) {


                        Log.i("responseHome", "" + response);
                        if (allBroadcastList.getStatus().equals(0)) {

                             Helper.showMessageDialog(context, "No Data Found.", false);

                            //Helper.hideDialog();
                        } else {



                            sessionManager.saveIntegerValue(Constants.KEY_TEMP_PINCODE_ID,1);

                            getQuestions = new ArrayList<>();

                            getQuestions = allBroadcastList.getData();



                            for (int i = 0; i < getQuestions.size(); i++) {
                                    getQuestions.get(i).setGivenANs("");


                                    getQuestions.get(i).setAnswerQ("No");
                                    getQuestions.get(i).setAnsShown("No");


                            }

                            Gson gson = new Gson();
                            String stringjson = gson.toJson(getQuestions);
                            MyJSON.saveData(context, stringjson, FILENAME_REDDIT_LIST);


                           //getQuestions = new ArrayList<>();

                          //  Gson gson = new Gson();
                            /*getQuestions = gson.fromJson(MyJSON.getData(context, FILENAME_REDDIT_LIST), new TypeToken<ArrayList<Questions.Datum>>() {
                            }.getType());*/
                          //  adapter = new AdapterHomeList(context, getQuestions);
                           // lv_item.setAdapter(adapter);

                        }
                    }

                    @Override
                    public void onFailure(RetrofitError error) {
                       // Helper.hideDialog();

                        error.printStackTrace();

                        error.getMessage();
                    }
                }
        );

    }

    private Map<String, String> getFieldMapForHomeData() {

        Map<String, String> map = new HashMap<>();
        return map;

    }
}
