package com.example.ruslan.contacts;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Ruslan on 20.10.2016.
 */

public class DeleteDialog extends DialogFragment implements DialogInterface.OnClickListener{
    Context context;

    public DeleteDialog newInstance(Context context){
        DeleteDialog dialog = new DeleteDialog();
        dialog.context = context;
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Remove contact");
        builder.setMessage("Are you sure?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", this);
        builder.setNegativeButton("No", this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

//    @Override
//    public void onClick(DialogInterface dialog, int which) {
//        switch (which){
//            case Dialog.BUTTON_POSITIVE:
//                contacts.remove(position);
//                adapter.notifyDataSetChanged();
//                Toast.makeText(getApplicationContext(),"contact removed",Toast.LENGTH_SHORT).show();
//                break;
//            case Dialog.BUTTON_NEGATIVE:
//                break;
//        }
//    }
}
