package com.example.ruslan.contacts;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Ruslan on 18.10.2016.
 */

public class ContactInfoFragment extends Fragment implements View.OnClickListener {

    private String contactName;
    private String contactNumber;
    private TextView tvName;
    private TextView tvNumber;
    private Button btnSMS;
    private Button btnCall;

    public static ContactInfoFragment newInstance(String name, String number) {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("number", number);
        ContactInfoFragment fragment = new ContactInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_info_fragment, container, false);
        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvName = (TextView) view.findViewById(R.id.frg_info_contact_name);
        tvNumber = (TextView) view.findViewById(R.id.frg_info_contact_number);

        btnSMS = (Button) view.findViewById(R.id.frg_btn_sms);
        btnSMS.setOnClickListener(this);

        btnCall = (Button) view.findViewById(R.id.frg_btn_call);
        btnCall.setOnClickListener(this);

        if (getArguments() != null) {
            contactName = getArguments().getString("name");
            contactNumber = getArguments().getString("number");

            tvNumber.setText(contactNumber);
            tvName.setText(contactName);
        }
    }

    @Override
    public void onClick(View v) {
        CallAndSMSManager manager = new CallAndSMSManager(getActivity());
        switch (v.getId()) {
            case R.id.frg_btn_call:
                manager.makeCall(contactNumber);
                break;

            case R.id.frg_btn_sms:
                manager.sendSMS(contactNumber);
                break;
        }
    }
}
