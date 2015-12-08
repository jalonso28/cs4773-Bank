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
public class NewPasswordFragment extends DialogFragment {
    private User user;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View root = getActivity().getLayoutInflater()
                .inflate(R.layout.fragment_new_password, null);
        final TextView email = (TextView) root.findViewById(R.id.pemail);
        final TextView password = (TextView) root.findViewById(R.id.old_password);
        final TextView nPassword = (TextView) root.findViewById(R.id.new_password);

        return new AlertDialog.Builder(getActivity())
                .setView(root)
                .setNeutralButton(R.string.cancel, null)
                .setPositiveButton(R.string.change_password, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBController dbController = DBController.getInstance(getActivity());
                        dbController.updatePassword(email.getText().toString(),
                                password.getText().toString(), nPassword.getText().toString());
                    }
                })
                .create();
    }

    public NewPasswordFragment() {
        DBController dbController = DBController.getInstance(getActivity());
    }

    public static NewPasswordFragment getInstance(Context context, long id) {
        DBController dbController = DBController.getInstance(context);
        NewPasswordFragment fragment = new NewPasswordFragment();
        return fragment;
    }

    public void setEmployee(User user) {
        this.user = user;
    }
}
