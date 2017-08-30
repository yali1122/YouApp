package com.example.ahsan.youtube;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.example.ahsan.youtube.Models.DataModel;
import com.example.ahsan.youtube.adap.AppConfig;
import com.example.ahsan.youtube.adap.AppController;
import com.example.ahsan.youtube.adapters.CustomAdapter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ProgressDialog pDialog;

    String[] Url,Title,Author,Duration,Views,Image;
    String SEARCH,VALUE;

    private static final int grid = 0;
    private static final int list = 1;

    private static RecyclerView recyclerView;
    private static RecyclerView.LayoutManager layoutManager;
    private static RecyclerView.LayoutManager gridManager;
    private static RecyclerView.Adapter adapter;
    private static ArrayList<DataModel> data;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=getIntent();
        SEARCH=intent.getStringExtra("send");
        VALUE=intent.getStringExtra("value");

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Please Wait!!!");


                String tag_string_req = "reg_login";
                showDialog();

                StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_PAK, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        Log.i("responseTest", response);


                        try {
                            JSONArray jObj = new JSONArray(response);

                            Url = new String[jObj.length()];
                            Title = new String[jObj.length()];
                            Author = new String[jObj.length()];
                            Views = new String[jObj.length()];
                            Duration = new String[jObj.length()];
                            Image = new String[jObj.length()];

                            for (int i = 0; i < jObj.length(); i++) {
                                JSONObject index = jObj.getJSONObject(i);

                                String url = index.getString("video_url");
                                String title = index.getString("title");
                                String author = index.getString("author_name");
                                String view = index.getString("views");
                                String duration = index.getString("published");
                                String image = index.getString("img_default");

                                Url[i] = url;
                                Title[i] = title;
                                Author[i] = author;
                                Views[i] = view;
                                Duration[i] = duration;
                                Image[i] = image;

                            }

                            data = new ArrayList<DataModel>();
                            for (int j = 0; j < Title.length; j++) {

                                data.add(new DataModel(

                                        Url[j],
                                        Title[j],
                                        Author[j],
                                        Views[j],
                                        Duration[j],
                                        Image[j]

                                ));
                            }

                            adapter=new CustomAdapter(data);
                            recyclerView.setAdapter(adapter);

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
                        params.put("what_to_do", "search_videos");
                        params.put("search",SEARCH);
                        params.put("max_result",VALUE);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        MenuItem it1 = menu.add(Menu.NONE, grid, Menu.NONE, "Grid");
        MenuItem it2 = menu.add(Menu.NONE, list, Menu.NONE, "List");


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case grid:
                gridManager=new GridLayoutManager(this,2);
                recyclerView.setLayoutManager(gridManager);
                Toast.makeText(getBaseContext(), "Show video in Grid view", Toast.LENGTH_SHORT).show();
                return true;
            case list:
                layoutManager=new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
                Toast.makeText(getBaseContext(), "Show video in Grid view", Toast.LENGTH_SHORT).show();
                return true;


            default:
                return false;
        }
    }


}
