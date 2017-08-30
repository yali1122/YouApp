package com.example.ahsan.youtube;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ahsan.youtube.adap.AppConfig;
import com.example.ahsan.youtube.adap.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    EditText
            f_name,
            l_name,
            email,
            password;
    Spinner
            date,month,year;

    Button
            register;
    TextView
            tv_login;
    RadioGroup
            gender_group;
    RadioButton
            gender_button;
    String
            name1,name2,email1,pass,gender,dofb;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        f_name=(EditText)findViewById(R.id.fname);
        l_name=(EditText)findViewById(R.id.lname);
        email=(EditText)findViewById(R.id.in_email);
        date=(Spinner) findViewById(R.id.date);
        month=(Spinner) findViewById(R.id.month);
        year=(Spinner) findViewById(R.id.year);
        password=(EditText)findViewById(R.id.in_password);
        gender_group=(RadioGroup)findViewById(R.id.gender);
        register=(Button)findViewById(R.id.btn_register);
        tv_login=(TextView)findViewById(R.id.tv_login);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Please Wait!!!");



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                name1 = f_name.getText().toString();
                name2 = l_name.getText().toString();
                email1 = email.getText().toString();
                dofb = date.getSelectedItem().toString()+" "+month.getSelectedItem().toString()+" "+year.getSelectedItem().toString();
                pass = password.getText().toString();

                int selectedId = gender_group.getCheckedRadioButtonId();
                gender_button = (RadioButton) findViewById(selectedId);
                gender = gender_button.getText().toString();


                String tag_string_req = "reg_login";
                showDialog();

                StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_register, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        Log.i("responseTest", response);

                        try {
                            JSONObject jObj = new JSONObject(response);
                            String error_msg = jObj.getString("error_msg");
                            boolean error = jObj.getBoolean("error");
                            if (!error) {
                                //JSONObject user = jObj.getJSONObject("user");
                                // String email = user.getString("password");
                                Toast.makeText(getApplicationContext(),error_msg, Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(getApplicationContext(), search.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),error_msg, Toast.LENGTH_LONG).show();

                            }


                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }


                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(),
                                "Server is down", Toast.LENGTH_LONG).show();
                        hideDialog();
                    }

                }) {

                    @Override
                    protected Map<String, String> getParams() {
                        // Posting parameters to login url
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("action", "register");
                        params.put("name", name1);
                        params.put("lastName", name2);
                        params.put("email", email1);
                        params.put("password", pass);
                        params.put("gender", gender);
                        params.put("dob", dofb);
                        Log.i("parameters", params.toString());
                        return params;
                    }

                };
                AppController.getInstance().addToRequestQueue(strReq, tag_string_req);


            }

            private void showDialog() {
                if (!pDialog.isShowing())
                    pDialog.show();
            }

            private void hideDialog() {
                if (pDialog.isShowing())
                    pDialog.dismiss();
            }

        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });

    }
}
