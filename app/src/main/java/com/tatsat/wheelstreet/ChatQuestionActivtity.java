package com.tatsat.wheelstreet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.tatsat.wheelstreet.Utils.Constants;
import com.tatsat.wheelstreet.Utils.Helper;
import com.tatsat.wheelstreet.Utils.MyJSON;
import com.tatsat.wheelstreet.Utils.SessionManager;
import com.tatsat.wheelstreet.Utils.UIUtil;
import com.tatsat.wheelstreet.adapters.AdapterHomeList;
import com.tatsat.wheelstreet.adapters.AdapterQuestionListrecycler;
import com.tatsat.wheelstreet.database.BazarPriveeDataSource;
import com.tatsat.wheelstreet.models.Questions;
import com.tatsat.wheelstreet.rest.APIHandler;
import com.tatsat.wheelstreet.rest.CancelableCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.tatsat.wheelstreet.Utils.MyJSON.FILENAME_REDDIT_LIST;

public class ChatQuestionActivtity extends AppCompatActivity {

    Context context;
    ListView list_question;
    SessionManager sessionManager;
    List<Questions.Datum> getQuestions;
    List<Questions.Datum> getQuestionssecond;
    AdapterHomeList adapter;
    AdapterQuestionListrecycler adapterQuestionListrecycler;
    RecyclerView recyclerView;
    private static RecyclerView.LayoutManager layoutManager1;
    ImageView iv_send,iv_profile;
    int ipos;
    EditText edt_ans;
    LinearLayout ll_send;
    TextView txt_signup;
    BazarPriveeDataSource bazarPriveeDataSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_question);

        initialization();

        idMapping();

        setOnclicklistnear();
    }

    private void setOnclicklistnear() {

        iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ProfilePageActivity.class);
                intent.putExtra("asd","123");
                startActivity(intent);
                finish();
            }
        });

        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject obj = null;
                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < getQuestions.size(); i++) {
                    obj = new JSONObject();
                    try {
                        obj.put("id", getQuestions.get(i).getId());
                        if (getQuestions.get(i).getDataType().equalsIgnoreCase("integer")){
                            if (getQuestions.get(i).getQuestion().contains("mobile")){
                                obj.put("answer", Long.valueOf(getQuestions.get(i).getGivenANs()));
                            }else{
                                obj.put("answer", Integer.parseInt(getQuestions.get(i).getGivenANs()));
                            }

                        }else if (getQuestions.get(i).getDataType().equalsIgnoreCase("string")){
                            obj.put("answer", getQuestions.get(i).getGivenANs());
                        }else if (getQuestions.get(i).getDataType().equalsIgnoreCase("boolean")){
                            obj.put("answer", Boolean.parseBoolean(getQuestions.get(i).getGivenANs()));
                        }else if (getQuestions.get(i).getDataType().equalsIgnoreCase("float")){
                            obj.put("answer", Float.parseFloat(getQuestions.get(i).getGivenANs()));
                        }

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    jsonArray.put(obj);
                }
                
                JSONObject finalobject = new JSONObject();
                try {
                    bazarPriveeDataSource.open();

                    finalobject.put("id",""+sessionManager.retrieveValuefromStringKey(Constants.KEY_USER_ID));
                    finalobject.put("name",""+bazarPriveeDataSource.gettingAllCartDetails().get(0).getItemName());
                    finalobject.put("fbUserName",""+bazarPriveeDataSource.gettingAllCartDetails().get(0).getItemName());
                    finalobject.put("mobile",""+bazarPriveeDataSource.gettingAllCartDetails().get(0).getItemMobileNumber());
                    finalobject.put("gender",""+bazarPriveeDataSource.gettingAllCartDetails().get(0).getItemGender());
                    finalobject.put("age",""+bazarPriveeDataSource.gettingAllCartDetails().get(0).getItemAge());
                    finalobject.put("email",""+bazarPriveeDataSource.gettingAllCartDetails().get(0).getItemEmail());
                    bazarPriveeDataSource.close();
                    finalobject.put("questions", jsonArray);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                callApiforSubmit(finalobject.toString());
                Log.e("123",""+finalobject.toString());
            }
        });


        iv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edt_ans.getText().toString().equalsIgnoreCase("")){
                    Helper.showMessageDialog(context, "Please Enter Answer", false);
                }else{

                    if (getQuestions.get(ipos).getDataType().equalsIgnoreCase("integer")){

                        if (getQuestions.get(ipos).getQuestion().contains("mobile")){
                            if (edt_ans.getText().toString().length() != 10) {

                                Helper.showMessageDialog(context, "Please enter valid Phone Number");
                                return;

                            }else{
                                try {
                                    Long num = Long.valueOf(edt_ans.getText().toString());
                                    Log.i("123",num+" is a number");
                                } catch (NumberFormatException e) {
                                    Log.i("123",edt_ans.getText().toString()+" is not a number");
                                    Helper.showMessageDialog(context,"Please enter Valid Integer Number");
                                    return;
                                }
                            }

                        }else{
                            try {
                                int num = Integer.parseInt(edt_ans.getText().toString());
                                Log.i("123",num+" is a number");
                            } catch (NumberFormatException e) {
                                Log.i("123",edt_ans.getText().toString()+" is not a number");
                                Helper.showMessageDialog(context,"Please enter Valid Integer Number");
                                return;
                            }
                        }




                    }else if (getQuestions.get(ipos).getDataType().equalsIgnoreCase("string")){



                    }else if (getQuestions.get(ipos).getDataType().equalsIgnoreCase("boolean")){

                        if (edt_ans.getText().toString().equalsIgnoreCase("true") || edt_ans.getText().toString().equalsIgnoreCase("false")){

                        }else{
                            Helper.showMessageDialog(context,"Please enter True OR False");
                            return;
                        }

                    }else if (getQuestions.get(ipos).getDataType().equalsIgnoreCase("float")){
                        try {
                            Float num = Float.parseFloat(edt_ans.getText().toString());
                            Log.i("123",num+" is a float");
                        } catch (NumberFormatException e) {
                            Helper.showMessageDialog(context,"Please enter Valid Float Number");
                            Log.i("123",edt_ans.getText().toString()+" is not a float");
                            return;
                        }
                    }
                    getQuestions.get(ipos).setGivenANs(edt_ans.getText().toString());
                    getQuestions.get(ipos).setAnswerQ("yes");
                    if (ipos < (getQuestions.size()-1)){
                        getQuestions.get(ipos+1).setAnsShown("yes");
                    }

                    getQuestionssecond.get(ipos).setGivenANs(edt_ans.getText().toString());
                    int iupdate = sessionManager.retrieveValuefromIntegerKey(Constants.KEY_TEMP_PINCODE_ID);
                    sessionManager.saveIntegerValue(Constants.KEY_TEMP_PINCODE_ID,(iupdate+1));
                    edt_ans.setText("");
                    //getQuestionssecond.add(getQuestions.get(ipos+1));
                    Gson gson = new Gson();
                    String stringjson = gson.toJson(getQuestions);
                    MyJSON.saveData(context, stringjson, FILENAME_REDDIT_LIST);
                    refresh();
                }



            }
        });
    }

    private void idMapping() {
        list_question = findViewById(R.id.list_question);
        recyclerView = findViewById(R.id.recyclerView);
        iv_send = findViewById(R.id.iv_send);
        iv_profile = findViewById(R.id.iv_profile);
        edt_ans = findViewById(R.id.edt_ans);
        ll_send = findViewById(R.id.ll_send);
        txt_signup = findViewById(R.id.txt_signup);
        txt_signup.setVisibility(View.GONE);

        layoutManager1 = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager1);

        getQuestions = new ArrayList<>();

        Gson gson = new Gson();
        getQuestions = gson.fromJson(MyJSON.getData(context, FILENAME_REDDIT_LIST), new TypeToken<ArrayList<Questions.Datum>>() {
        }.getType());
        /*adapter = new AdapterHomeList(context, getQuestions);
        list_question.setAdapter(adapter);*/
        getQuestionssecond = new ArrayList<>();


        if (getQuestions.get(0).getAnsShown().equalsIgnoreCase("no")){
            refresh();
        }else{
            for (int i = 0;i<getQuestions.size();i++){
                if (getQuestions.get(i).getAnsShown().equalsIgnoreCase("yes")){
                    getQuestionssecond.add(getQuestions.get(i));
                    ipos = i;
                    if (ipos == (getQuestions.size()-1)){
                        ll_send.setVisibility(View.GONE);
                        txt_signup.setVisibility(View.VISIBLE);
                    }
                }

            }
            adapterQuestionListrecycler = new AdapterQuestionListrecycler(context, getQuestionssecond);
            recyclerView.setAdapter(adapterQuestionListrecycler);
        }




    }

    private void refresh() {

        for (int i = 0; i < getQuestions.size(); i++) {

            Log.e("abc",""+sessionManager.retrieveValuefromIntegerKey(Constants.KEY_TEMP_PINCODE_ID));
            if (sessionManager.retrieveValuefromIntegerKey(Constants.KEY_TEMP_PINCODE_ID) == getQuestions.get(i).getId()){
                getQuestions.get(i).setAnsShown("yes");
                getQuestionssecond.add(getQuestions.get(i));
                ipos = i;
                break;

            }

        }

        adapterQuestionListrecycler = new AdapterQuestionListrecycler(context, getQuestionssecond);
        recyclerView.setAdapter(adapterQuestionListrecycler);
        recyclerView.scrollToPosition(adapterQuestionListrecycler.getItemCount()-1);

        if (sessionManager.retrieveValuefromIntegerKey(Constants.KEY_TEMP_PINCODE_ID) == 6){
            ll_send.setVisibility(View.GONE);
            txt_signup.setVisibility(View.VISIBLE);
        }
    }

    private void initialization() {
        context = this;
        bazarPriveeDataSource = new BazarPriveeDataSource(context);
        sessionManager = new SessionManager(context);

    }


    private void callApiforSubmit(String data) {

        //Helper.showProgressDialog(context);

        APIHandler.getApiService().api_get_address_list(getFieldMapForHomeData(data), new CancelableCallback<Response>() {
            @Override
            public void onSuccess(Response responseRetrofit, Response response) {
               // Helper.hideDialog();
                String responseJsonString = Helper.getTrimmedString(UIUtil.gettingResponseInString(response));
                if (responseJsonString != null && !responseJsonString.equalsIgnoreCase("")) {



                    try {
                        sessionManager.removeAllUSerData();
                        bazarPriveeDataSource.open();
                        bazarPriveeDataSource.deleteAllOrderItem();
                        bazarPriveeDataSource.close();
                        JSONObject job = new JSONObject(responseJsonString);


                        if (job.getInt("status") == 0) {
                            Helper.ShowDialog(ChatQuestionActivtity.this, job.getString("data"));
                        } else {
                            Helper.ShowDialog(ChatQuestionActivtity.this, job.getString("data"));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(RetrofitError error) {
                //Helper.hideDialog();

                error.printStackTrace();

                error.getMessage();

            }
        });


    }

    private Map<String, String> getFieldMapForHomeData(String data) {

        Map<String, String> map = new HashMap<>();
        map.put("data ",data);

        return map;

    }
}
