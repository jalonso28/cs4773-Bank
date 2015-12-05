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
public class DeleteUserFragment extends DialogFragment {
    private User user;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View root = getActivity().getLayoutInflater()
                .inflate(R.layout.fragment_delete_user, null);
        final TextView email = (TextView) root.findViewById(R.id.Demail);
        final TextView password = (TextView) root.findViewById(R.id.Dpassword);
        final TextView token = (TextView) root.findViewById(R.id.token);

        return new AlertDialog.Builder(getActivity())
                .setView(root)
                .setNeutralButton(R.string.cancel, null)
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBController dbController = DBController.getInstance(getActivity());
                        dbController.deleteUser(email.getText().toString(),
                                password.getText().toString(), token.getText().toString());
                    }
                })
                .create();
    }

    public DeleteUserFragment() {
        DBController dbController = DBController.getInstance(getActivity());
    }

    public static DeleteUserFragment getInstance(Context context, long id) {
        DBController dbController = DBController.getInstance(context);
        DeleteUserFragment fragment = new DeleteUserFragment();
        fragment.setEmployee(dbController.getUser((int)id));
        return fragment;
    }

    public void setEmployee(User user) {
        this.user = user;
    }
}
