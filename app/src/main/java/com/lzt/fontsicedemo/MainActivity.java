package com.lzt.fontsicedemo;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private int fontSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Change the App's fontsice action must before the method:setContentView()!
        initFont();
        setContentView(R.layout.activity_main);


        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        ((RadioButton)rg.getChildAt(fontSize==SPUtil.FONT_NORMAL?1:(fontSize==SPUtil.FONT_SMALL? 0 : 2))).setChecked(true);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setSpFontSice(checkedId);
            }
        });
        findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartApp();
            }
        });
    }


    private void setSpFontSice(int checkedId) {
        if(checkedId==R.id.rb_small){
            SPUtil.setInt(getApplicationContext(),SPUtil.APP_FONT_SIZE,SPUtil.FONT_SMALL);
        }else if(checkedId==R.id.rb_normal){
            SPUtil.setInt(getApplicationContext(),SPUtil.APP_FONT_SIZE,SPUtil.FONT_NORMAL);
        }else if(checkedId==R.id.rb_big){
            SPUtil.setInt(getApplicationContext(),SPUtil.APP_FONT_SIZE,SPUtil.FONT_BIG);
        }
    }

    //This is key;
    private void initFont() {
        fontSize = SPUtil.getInt(getApplicationContext(),SPUtil.APP_FONT_SIZE,SPUtil.FONT_NORMAL);
        if(fontSize==SPUtil.FONT_SMALL) {
            setTheme(R.style.AppThemeSmall);
        }else if(fontSize==SPUtil.FONT_BIG){
            setTheme(R.style.AppThemeBig);
        }
    }

    private void restartApp() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ECLAIR_MR1){
            Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext()
                    .getPackageName());
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,
                    PendingIntent.FLAG_ONE_SHOT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis()+250, pendingIntent);
            finish();
            System.exit(0);
        }else {
            ActivityManager am = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
            am.restartPackage(getPackageName());
        }
    }
}
