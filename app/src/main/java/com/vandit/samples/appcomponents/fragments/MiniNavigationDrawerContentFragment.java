package com.vandit.samples.appcomponents.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vandit.samples.appcomponents.R;
import com.vandit.samples.appcomponents.adapters.MenuListAdapter;
import com.vandit.samples.appcomponents.enums.MenuActionItem;

/**
 * A placeholder fragment containing a simple view.
 */
public class MiniNavigationDrawerContentFragment extends ListFragment {

    public MiniNavigationDrawerContentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_mini_navigartion_drawer, container, false);
        setListAdapter(new MenuListAdapter(getActivity(), R.layout.row_menu_action_item, MenuActionItem.values()));
        return view;
    }

}
