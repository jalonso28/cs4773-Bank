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
public class EditUserFragment extends DialogFragment {
    private User user;
    private ArrayList<String> roles;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View root = getActivity().getLayoutInflater()
                .inflate(R.layout.fragment_edit_user, null);
        final TextView email = (TextView) root.findViewById(R.id.email);
        final TextView password = (TextView) root.findViewById(R.id.password);
        final TextView first = (TextView) root.findViewById(R.id.first);
        final TextView last = (TextView) root.findViewById(R.id.last);
        final Spinner spinner = (Spinner) root.findViewById(R.id.role);

        spinner.setAdapter(new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                roles.toArray(new String[roles.size()])));

        if (user != null) {
            email.setText(user.getEmail());
            password.setText(user.getPassword());
            first.setText(user.getFirst());
            last.setText(user.getLast());
            spinner.setSelection((int) user.getRoleCode() - 1);
        }

        return new AlertDialog.Builder(getActivity())
                .setView(root)
                .setNeutralButton(R.string.cancel, null)
                .setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBController dbController = DBController.getInstance(getActivity());
                        if (user != null) {
                            dbController.deleteEmployee(user);
                            ((AddUserFragment) getParentFragment()).refreshList();
                        }
                    }
                })
                .setPositiveButton(R.string.edit_add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBController dbController = DBController.getInstance(getActivity());
                        User newUser = new User();

                        newUser.setEmail(email.getText().toString());
                        newUser.setPassword(password.getText().toString());
                        newUser.setFirst(first.getText().toString());
                        newUser.setLast(last.getText().toString());
                        newUser.setRoleCode(spinner.getSelectedItemId() + 1);

                        if (user == null) {
                            dbController.insertUser(newUser);
                        } else {
                            newUser.setId(user.getId());
                            dbController.updateUser(newUser);
                        }
                        ((AddUserFragment) getParentFragment()).refreshList();
                    }
                })
                .create();
    }

    public EditUserFragment() {
        DBController dbController = DBController.getInstance(getActivity());
        roles = dbController.getRoles();
    }

    public static EditUserFragment getInstance(Context context, long id) {
        DBController dbController = DBController.getInstance(context);
        EditUserFragment fragment = new EditUserFragment();
        fragment.setEmployee(dbController.getUser(id));
        return fragment;
    }

    public void setEmployee(User user) {
        this.user = user;
    }
}
