package com.notit.bank.bankapp.User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.plus.Plus;
import com.notit.bank.bankapp.*;
import com.google.android.gms.common.api.GoogleApiClient;
import com.notit.bank.bankapp.User.*;

/**
 * Created by Beaster on 11/5/2015.
 */
public class UserFragment extends MainActivity.PlaceholderFragment {
    private static final String ARG_USR_ID = "usrId";

    public final class Constants {
        public static final int SUCCESS_RESULT = 0;
        public static final int FAILURE_RESULT = 1;
        public static final String PACKAGE_NAME =
                "com.google.android.gms.location.sample.locationaddress";
        public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
        public static final String RESULT_DATA_KEY = PACKAGE_NAME +
                ".RESULT_DATA_KEY";
        public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME +
                ".LOCATION_DATA_EXTRA";
    }

    public static UserFragment newInstance(int sectionNumber, long userId) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putLong(ARG_USR_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout root;
        TextView textView;
        DBController dbController = DBController.getInstance(getActivity());
        Bundle args = getArguments();
        User user = dbController.getUser((int) args.getLong(ARG_USR_ID));

        root = (LinearLayout) inflater.inflate(R.layout.fragment_employee_info, container, false);
        if (user != null) {
            textView = (TextView) inflater.inflate(R.layout.text_view, root, false);
            textView.setText(String.format("%s: %s %s\n\n%s: %s",
                    getString(R.string.user), user.getFirst(), user.getLast(),
                    getString(R.string.type), user.getRoleTitle()
            ));
            root.addView(textView);
        }

        return root;
    }
}
