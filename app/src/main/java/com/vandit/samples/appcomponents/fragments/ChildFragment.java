package com.vandit.samples.appcomponents.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vandit.samples.appcomponents.MainActivity;
import com.vandit.samples.appcomponents.R;

/**
 * Created by vandi on 1/2/2017.
 */

public class ChildFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(view != null){
            getActivity().setTitle(getString(R.string.title_child_fragment));
            ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
            ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((MainActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(null);
        }
    }

    public static ChildFragment newInstance(){
        ChildFragment childFragment = new ChildFragment();
        return childFragment;
    }


}
