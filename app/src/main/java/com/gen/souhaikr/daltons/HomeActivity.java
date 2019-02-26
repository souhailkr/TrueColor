package com.gen.souhaikr.daltons;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;

public class HomeActivity extends AppCompatActivity {
    TextView firstnameTxt;
    ImageView imageView ;
    ImageView startTest ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (toolbar != null) {
            getSupportActionBar().setTitle("");
        }

        imageView = findViewById(R.id.imageView) ;
        startTest = findViewById(R.id.startTest) ;


        String data = getIntent().getExtras().getString("first_name");
        String user_id = getIntent().getExtras().getString("user_id");

        firstnameTxt = findViewById(R.id.firstnameTxt) ;
        firstnameTxt.setText("Hello "+data+" !");

        try {
            URL imageURL = new URL("https://graph.facebook.com/" +user_id+ "/picture?type=large");

            Picasso.get().load(String.valueOf(imageURL)).into(imageView);



        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                startActivity(intent);

            }
        });

        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sign_out: {


                LoginManager.getInstance().logOut();


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                break;
            }

}
        return true;
    }

}
