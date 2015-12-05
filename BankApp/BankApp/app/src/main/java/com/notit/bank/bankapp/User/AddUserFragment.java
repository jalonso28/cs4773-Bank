package com.notit.bank.bankapp.User;

/**
 * Created by Beaster on 11/11/2015.
 */

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.notit.bank.bankapp.MainActivity;
import com.notit.bank.bankapp.R;
import com.notit.bank.bankapp.DBController;
import java.util.ArrayList;

public class AddUserFragment extends MainActivity.PlaceholderFragment {
private ListView listView;

public static AddUserFragment newInstance(int sectionNumber) {
        AddUserFragment fragment = new AddUserFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
        }

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_user, container, false);
        listView = (ListView) root.findViewById(R.id.list);

        listView.setAdapter(new SimpleListAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
@Override
public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        getChildFragmentManager().beginTransaction()
        .add(EditUserFragment.getInstance(getActivity(), l), null)
        .commit();
        }
        });
        root.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        getChildFragmentManager().beginTransaction()
        .add(EditUserFragment.getInstance(getActivity(), 0), null)
        .commit();
        }
        });
        return root;
        }

private class SimpleListAdapter implements ListAdapter {
    private ArrayList<User> users;

    public SimpleListAdapter() {
        DBController dbController = DBController.getInstance(getActivity());
        this.users = dbController.getAllUsers();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return users.get(i).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        if (view == null)
            view = inflater.inflate(R.layout.list_text_view, viewGroup, false);
        User user = users.get(i);
        ((TextView)view).setText(String.format("%s %s\n%s",
                user.getFirst(), user.getLast(), user.getRoleTitle()
        ));
        return view;
    }

    @Override
    public int getItemViewType(int i) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}

    public void refreshList() {
        listView.setAdapter(new SimpleListAdapter());
    }
}