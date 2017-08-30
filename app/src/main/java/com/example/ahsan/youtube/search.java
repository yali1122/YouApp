package com.example.ahsan.youtube;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class search extends AppCompatActivity {

    EditText search,value;
    ImageButton btn_search;
    String SEARCH,VALUE;

    private static final int logout = 0;
    private static final int update = 1;
    private static final int about = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search = (EditText) findViewById(R.id.search);
        value = (EditText) findViewById(R.id.value);
        btn_search = (ImageButton) findViewById(R.id.btn_search);


        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SEARCH = search.getText().toString();
                VALUE=value.getText().toString();
                if(VALUE.equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter VALUE at least 1",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = (new Intent(getApplicationContext(), MainActivity.class));
                    intent.putExtra("send", SEARCH);
                    intent.putExtra("value", VALUE);
                    startActivity(intent);
                }

            }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        MenuItem it1 = menu.add(Menu.NONE, logout, Menu.NONE, "logout");
        MenuItem it2 = menu.add(Menu.NONE, update, Menu.NONE, "Profile");
        MenuItem it3 = menu.add(Menu.NONE, about, Menu.NONE, "About");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case logout:
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
                finish();
                Toast.makeText(getBaseContext(), "You account logout successfully", Toast.LENGTH_SHORT).show();
                return true;
            case update:
                Toast.makeText(getBaseContext(), "You account ", Toast.LENGTH_SHORT).show();
                return true;
            case about:
                Toast.makeText(getBaseContext(), "About this app ", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }
}
