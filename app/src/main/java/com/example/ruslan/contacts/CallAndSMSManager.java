package com.example.ruslan.contacts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Ruslan on 23.10.2016.
 */

public class CallAndSMSManager {
    private Uri number;
    private Context context;

    public CallAndSMSManager(Context context) {
        this.context = context;
    }

    public void makeCall(String contactNumber){
        number = Uri.parse("tel:" + contactNumber);
        Intent callIntent = new Intent(Intent.ACTION_CALL, number);
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (isIntentSafe(callIntent)) {
            context.startActivity(callIntent);
        } else {
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
        }
    }

    public void sendSMS(String contactNumber){
        number = Uri.parse("smsto:" + contactNumber);
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, number);
        smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (isIntentSafe(smsIntent)) {
            context.startActivity(smsIntent);
        } else {
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isIntentSafe(Intent intent) {
        List<ResolveInfo> activities = context.getPackageManager().queryIntentActivities(intent, 0);
        return activities.size() > 0;
    }
}
