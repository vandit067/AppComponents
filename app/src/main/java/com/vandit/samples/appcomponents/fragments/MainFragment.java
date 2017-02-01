package com.vandit.samples.appcomponents.fragments;

import android.content.Context;
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
import com.vandit.samples.appcomponents.callbacks.OnFragmentInteractionListner;

/**
 * Created by vandi on 1/2/2017.
 */

public class MainFragment extends Fragment implements View.OnClickListener {

    private Button btnOpenNextFragment;
    private OnFragmentInteractionListner mFragmentInteractionListner;

    /**
     * Use this factory method to create new instance of
     * this fragment using thr provider parameters.
     * @return A new instance of fragment {@link MainFragment}
     */
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
        if(getActivity() != null) {
            ((MainActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.title_parent_fragment));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_main_btn_open:
                if(mFragmentInteractionListner != null){
                    mFragmentInteractionListner.onFragmentInteraction(this);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListner){
            mFragmentInteractionListner = (OnFragmentInteractionListner) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListner");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mFragmentInteractionListner = null;
    }
}
