package com.example.shalu.taxcalculator;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IncomeActivity extends AppCompatActivity {

    Button  do1;
    EditText ed;
    TextView taxt1,taxt2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        do1 = (Button) findViewById(R.id.btn3);
        ed = (EditText) findViewById(R.id.edt1);
        taxt1 = (TextView) findViewById(R.id.tx1);
        taxt2 = (TextView) findViewById(R.id.tx2);

        do1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                taxcalculator();
            }
        });
    }
        public void taxcalculator(){
            int TotalTax = 0;
            int Tax=0;
            int n =Integer.parseInt(ed.getText().toString());


            if (n < 250000){
               Tax =0;
               TotalTax= 0;
            }

            else if ((n >= 250000) && (n < 500000))
            {
                Tax =(n *5 )/100;
                TotalTax = n +Tax;
            }
            else if ((n >= 500000)  && (n <1000000)){

            Tax =(n *20)/100;
            TotalTax = n+Tax;
        }
            else if (n >=1000000){
                Tax = (n * 30) / 100;
                TotalTax = n + Tax;
            }

            taxt1.setText("Tax on your income  = " + Tax+ "" );
            taxt2.setText(" Total Income (Inclusion of Tax)  = " + TotalTax+"");



            }


            }





