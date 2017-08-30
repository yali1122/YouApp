package com.example.ahsan.youtube;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class login extends AppCompatActivity {

    EditText email, pass;
    Button sign_in;
    TextView tv_register;
    ProgressDialog pDialog;
    String email1,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.in_email);
        pass = (EditText) findViewById(R.id.in_password);
        sign_in = (Button) findViewById(R.id.btn_login);
        tv_register=(TextView)findViewById(R.id.tv_register);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Please Wait!!!");


        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email1 = email.getText().toString();
                password = pass.getText().toString();

                if (  ( !email1.equals("")) && ( !password.equals("")) )
                {

                }
                else if ( ( !email1.equals("")) )
                {
                    Toast.makeText(getApplicationContext(),"Password field empty", Toast.LENGTH_SHORT).show();
                }
                else if ( ( !password.equals("")) )
                {
                    Toast.makeText(getApplicationContext(),"Email field empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Email and Password field are empty", Toast.LENGTH_SHORT).show();
                }



                String tag_string_req = "reg_login";
                showDialog();

                StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_login, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        Log.i("responseTest", response);

                        try {
                            JSONObject jObj = new JSONObject(response);
                            String error_msg=jObj.getString("error_msg");
                            boolean error= jObj.getBoolean("error");
                            if(!error){
                               // JSONObject user= jObj.getJSONObject("user");
                               // String email=user.getString("password");
                                Toast.makeText(getApplicationContext(),error_msg,Toast.LENGTH_LONG).show();

                                Intent intent=new Intent(getApplicationContext(),search.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),error_msg,Toast.LENGTH_LONG).show();
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
                        params.put("action","login");
                        params.put("email",email1);
                        params.put("pass",password);
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

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(getApplicationContext(),register.class);
                startActivity(intent);

            }
        });

    }

}
