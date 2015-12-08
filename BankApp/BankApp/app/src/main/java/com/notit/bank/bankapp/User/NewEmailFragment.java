package com.notit.bank.bankapp.User;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

import com.notit.bank.bankapp.R;
import com.notit.bank.bankapp.DBController;

/**
 * Created by Beaster on 11/11/2015.
 */
public class NewEmailFragment extends DialogFragment {
    private User user;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View root = getActivity().getLayoutInflater()
                .inflate(R.layout.fragment_new_email, null);
        final TextView email = (TextView) root.findViewById(R.id.old_email);
        final TextView nEmail = (TextView) root.findViewById(R.id.new_email);
        final TextView password = (TextView) root.findViewById(R.id.epassword);

        return new AlertDialog.Builder(getActivity())
                .setView(root)
                .setNeutralButton(R.string.cancel, null)
                .setPositiveButton(R.string.change_email, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBController dbController = DBController.getInstance(getActivity());
                        dbController.updateEmail(email.getText().toString(),
                                nEmail.getText().toString(), password.getText().toString());
                    }
                })
                .create();
    }

    public NewEmailFragment() {
        DBController dbController = DBController.getInstance(getActivity());
    }

    public static NewEmailFragment getInstance(Context context, long id) {
        DBController dbController = DBController.getInstance(context);
        NewEmailFragment fragment = new NewEmailFragment();
        return fragment;
    }

    public void setEmployee(User user) {
        this.user = user;
    }
}
