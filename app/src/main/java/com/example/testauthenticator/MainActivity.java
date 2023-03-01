package com.example.testauthenticator;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;


public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 1;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        if (telephonyManager.getSimState() == TelephonyManager.SIM_STATE_ABSENT) {
            Log.i("MainActivity", "Sim not present");
            // SIM card is not present
        } else {
            Log.i("MainActivity", "Sim  present");

            // SIM card is present
        }


        // Check if the permission is already granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not yet granted, request it
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
        } else {
            // Permission is already granted, proceed with accessing the ICC serial number

            String iccSerialNumber = telephonyManager.getSimOperatorName();
            Log.i("check",iccSerialNumber);
            // Do something with the ICC serial number
        }


        try {
            String simSerialNumber = telephonyManager.getSimSerialNumber();
            String subscriberId = telephonyManager.getSubscriberId();
            //String line1Number = telephonyManager.getLine1Number();
            Log.d("MainActivity", "SIM Serial Number: " + simSerialNumber);
            Log.d("MainActivity", "Subscriber ID: " + subscriberId);
            //Log.d("MainActivity", "Line 1 Number: " + line1Number);
        }
        catch (Exception e){
            Log.e("Main", "error " +e);
        }

    }

    /*

    private static final int PERMISSION_REQUEST_CODE = 100;
    TextView textView;
    TelephonyManager telephonyManager;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
        telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, READ_SMS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{READ_SMS, READ_PHONE_NUMBERS, READ_PHONE_STATE}, PERMISSION_REQUEST_CODE);
        } else {
            textView.setText(""+telephonyManager.getSimSerialNumber());
            Log.i("Main", "sub id: " + telephonyManager.getSubscriberId());
            Log.i("Main","Imei: " + telephonyManager.getImei());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) !=
                        PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) !=
                                PackageManager.PERMISSION_GRANTED) {
                    return;
                } else {
                    textView.setText(""+telephonyManager.getSimSerialNumber());
                }
        }
    }


     */


}