package com.example.user.scanner_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity
{
    private IntentIntegrator scanIntegrator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onbuttonclick(View v)
    {
        View button1 = (View) findViewById(R.id.button);

        scanIntegrator = new IntentIntegrator(MainActivity.this);
        scanIntegrator.setPrompt("請掃描");
        scanIntegrator.setTimeout(300000);
        scanIntegrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null)
        {
            if(scanningResult.getContents() != null)
            {
                String scanContent = scanningResult.getContents();
                if (!scanContent.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"掃描內容: "+scanContent.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, intent);
            Toast.makeText(getApplicationContext(),"發生錯誤",Toast.LENGTH_LONG).show();
        }
    }
}