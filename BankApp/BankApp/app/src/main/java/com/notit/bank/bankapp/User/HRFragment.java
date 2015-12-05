package com.notit.bank.bankapp.User;

/**
 * Created by Beaster on 11/11/2015.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.notit.bank.bankapp.DBController;
import com.notit.bank.bankapp.MainActivity;
import com.notit.bank.bankapp.R;

public class HRFragment extends MainActivity.PlaceholderFragment {


    public static HRFragment newInstance(int sectionNumber) {
        HRFragment fragment = new HRFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_hr, container, false);
        final TextView email = (TextView) root.findViewById(R.id.email);
        final TextView password = (TextView) root.findViewById(R.id.password);

        root.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getChildFragmentManager().beginTransaction()
                        .add(NewUserFragment.getInstance(getActivity(), 0), null)
                        .commit();
            }
        });
        root.findViewById(R.id.change_email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getChildFragmentManager().beginTransaction()
                        .add(NewEmailFragment.getInstance(getActivity(), 0), null)
                        .commit();
            }
        });
        root.findViewById(R.id.change_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getChildFragmentManager().beginTransaction()
                        .add(NewPasswordFragment.getInstance(getActivity(), 0), null)
                        .commit();
            }
        });
        root.findViewById(R.id.delete_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBController dbController = DBController.getInstance(getActivity());
                dbController.getToken(email.getText().toString(), password.getText().toString());
                getChildFragmentManager().beginTransaction()
                        .add(DeleteUserFragment.getInstance(getActivity(), 0), null)
                        .commit();
            }
        });





        return root;
}
}