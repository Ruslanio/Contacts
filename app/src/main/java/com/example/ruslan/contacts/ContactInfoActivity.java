package com.example.ruslan.contacts;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ContactInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private String contactName;
    private String contactNumber;
    private TextView tvName;
    private TextView tvNumber;
    private Button btnSMS;
    private Button btnCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        contactName = getIntent().getStringExtra("name");
        contactNumber = getIntent().getStringExtra("number");

        setUpTheElements();
    }

    private void setUpTheElements() {
        tvName = (TextView) findViewById(R.id.info_contact_name);
        tvName.setText(contactName);

        tvNumber = (TextView) findViewById(R.id.info_contact_number);
        tvNumber.setText(contactNumber);

        btnSMS = (Button) findViewById(R.id.btn_sms);
        btnSMS.setOnClickListener(this);

        btnCall = (Button) findViewById(R.id.btn_call);
        btnCall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        CallAndSMSManager manager = new CallAndSMSManager(this);
        switch (v.getId()) {
            case R.id.btn_call:
                manager.makeCall(contactNumber);
                break;

            case R.id.btn_sms:
                manager.sendSMS(contactNumber);
                break;
        }

    }


}
