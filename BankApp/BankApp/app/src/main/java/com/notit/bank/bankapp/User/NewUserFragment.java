package com.notit.bank.bankapp.User;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import com.notit.bank.bankapp.R;
import com.notit.bank.bankapp.DBController;

/**
 * Created by Beaster on 11/11/2015.
 */
public class NewUserFragment extends DialogFragment {
    private User user;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View root = getActivity().getLayoutInflater()
                .inflate(R.layout.fragment_new_user, null);
        final TextView email = (TextView) root.findViewById(R.id.email);
        final TextView first = (TextView) root.findViewById(R.id.first);
        final TextView last = (TextView) root.findViewById(R.id.last);
        final TextView ssn = (TextView) root.findViewById(R.id.ssn);
        final TextView id = (TextView) root.findViewById(R.id.id);

        return new AlertDialog.Builder(getActivity())
                .setView(root)
                .setNeutralButton(R.string.cancel, null)
                .setPositiveButton(R.string.add_user, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBController dbController = DBController.getInstance(getActivity());
                        User newUser = new User();
                        newUser.setEmail(email.getText().toString());
                        newUser.setFirst(first.getText().toString());
                        newUser.setLast(last.getText().toString());
                        newUser.setSSN(Integer.parseInt(ssn.getText().toString()));
                        newUser.setId(Integer.parseInt(id.getText().toString()));
                        dbController.insertUser(newUser);
                    }
                })
                .create();
    }

    public NewUserFragment() {
        DBController dbController = DBController.getInstance(getActivity());
    }

    public static NewUserFragment getInstance(Context context, long id) {
        DBController dbController = DBController.getInstance(context);
        NewUserFragment fragment = new NewUserFragment();
        fragment.setEmployee(dbController.getUser((int)id));
        return fragment;
    }

    public void setEmployee(User user) {
        this.user = user;
    }
}
