package com.zecho.countmatic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SaveDialog extends AppCompatDialogFragment {
    EditText et;
    View inflateee;
    NoticeDialogListener listener;

    public interface NoticeDialogListener {

        public void onDialogPositiveClick(String name);

    }


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

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        inflateee = inflater.inflate(R.layout.fragment_save_dialog, null);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflateee)
                .setPositiveButton("Save",null)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        SaveDialog.this.dismiss();

                    }
                });

        AlertDialog dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {

                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);

                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        et = inflateee.findViewById(R.id.et_name);

                        if (TextUtils.isEmpty(et.getText().toString())) {

                            et.setError("Can't be empty");

                        } else {

                            listener.onDialogPositiveClick(et.getText().toString());

                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        return dialog;
    }
}