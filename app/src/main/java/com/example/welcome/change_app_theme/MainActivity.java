package com.example.welcome.change_app_theme;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;

import java.lang.reflect.Field;
import java.util.ArrayList;

/*
Firstly decide what things u want to change when you change the theme
*/
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button b1, b2, b3, b4, b5, b6;
//    ActionBar bar = getActionBar();
    public static String theme_name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);
        setUpActionBar();

        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);
        b6 = (Button) findViewById(R.id.button6);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);

    }

    private void setUpActionBar() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (!theme_name.equals("")){
                actionBar.setTitle(Html.fromHtml(theme_name));
                actionBar.setBackgroundDrawable(new ColorDrawable(getResources()
                        .getColor(R.color.red)));
            }
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }


    @Override
    public void onClick(View v) {


        // TODO Auto-generated method stub
        switch (v.getId())
        {
            case R.id.button:
                theme_name = "Red";
                Utils.changeToTheme(this, Utils.THEME_RED);
//                setUpActionBar();
//                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
//                        .getColor(R.color.red)));
                break;
            case R.id.button2:
                theme_name = "BLUE";
                Utils.changeToTheme(this, Utils.THEME_BLUE);
                break;
            case R.id.button3:
                theme_name = "GREEN";
                Utils.changeToTheme(this, Utils.THEME_GREEN);
                break;
            case R.id.button4:
                theme_name = "YELLOW";
                Utils.changeToTheme(this, Utils.THEME_YELLOW);
                break;
            case R.id.button5:
                theme_name = "VOILET";
                Utils.changeToTheme(this, Utils.THEME_VOILET);
                break;
            case R.id.button6:
                theme_name = "PINK";
                Utils.changeToTheme(this, Utils.THEME_PINK);
                break;
        }
    }
}
