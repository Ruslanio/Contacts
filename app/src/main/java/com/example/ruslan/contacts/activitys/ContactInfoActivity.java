package com.example.ruslan.contacts.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ruslan.contacts.supportClasses.CallAndSMSManager;
import com.example.ruslan.contacts.R;

public class ContactInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private String contactName;
    private String contactNumber;
    private TextView tvName;
    private TextView tvNumber;
    private Button btnSMS;
    private Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_lnfo);

        tvName = (TextView) findViewById(R.id.info_contact_name);
        tvNumber = (TextView) findViewById(R.id.info_contact_number);

        btnSMS = (Button) findViewById(R.id.btn_sms);
        btnSMS.setOnClickListener(this);

        btnCall = (Button) findViewById(R.id.btn_call);
        btnCall.setOnClickListener(this);

        Intent intent = getIntent();

            contactName = intent.getStringExtra("name");
            contactNumber = intent.getStringExtra("number");

            tvNumber.setText(contactNumber);
            tvName.setText(contactName);

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
