package com.example.epubfoliotest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogLogin extends AppCompatDialogFragment {
    private EditText editUsername;
    private EditText editPassword;
    private loginListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.login_dialog, null);

        builder.setView(view)
                .setTitle("Login")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = editUsername.getText().toString();
                        String password = editPassword.getText().toString();
                        listener.applytexts(username, password);
                    }
                })
                .setNeutralButton("sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.signIn();
                    }
                });

        editUsername = view.findViewById(R.id.username);
        editPassword = view.findViewById(R.id.password);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (loginListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement loginListener");
        }
    }

    public interface loginListener{
        void applytexts(String username, String password);

        void signIn();
    }
}
