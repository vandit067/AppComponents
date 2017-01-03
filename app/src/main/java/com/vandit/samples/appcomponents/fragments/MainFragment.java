package com.vandit.samples.appcomponents.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vandit.samples.appcomponents.MainActivity;
import com.vandit.samples.appcomponents.R;

/**
 * Created by vandi on 1/2/2017.
 */

public class MainFragment extends Fragment implements View.OnClickListener {

    private Button btnOpenNextFragment;

    public static MainFragment newInstance() {
        MainFragment mainFragment = new MainFragment();
        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout with this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view != null) {
            // Initialize components
            btnOpenNextFragment = (Button) view.findViewById(R.id.fragment_main_btn_open);
            btnOpenNextFragment.setOnClickListener(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_main_btn_open:
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                ChildFragment childFragment = ChildFragment.newInstance();
                fragmentTransaction.add(R.id.activity_main_fl_content, childFragment);
                fragmentTransaction.hide(this);
                fragmentTransaction.addToBackStack(this.getClass().getSimpleName());
                fragmentTransaction.commit();
                break;
            default:
                break;
        }
    }
}
