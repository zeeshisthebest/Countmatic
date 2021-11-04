package com.zecho.countmatic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SaveDialog extends AppCompatDialogFragment {
    EditText et;
    View inflateee;

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(String name);
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener listener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (NoticeDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        inflateee = inflater.inflate(R.layout.fragment_save_dialog, null);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflateee)
                // Add action buttons
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText et = inflateee.findViewById(R.id.et_name);
                        if (TextUtils.isEmpty(et.getText().toString())) {
                            et.setError("Can't be empty");
                        } else {
//                            SharedPreferences sp = getActivity().getSharedPreferences(MainActivity2.PREFERENCE_KEY, 0);
//                            SharedPreferences.Editor ed = sp.edit();
//                            ed.putInt(et.getText().toString(), count);
//                            ed.apply();
                            listener.onDialogPositiveClick(et.getText().toString());
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SaveDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}