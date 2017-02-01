package com.vandit.samples.appcomponents.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vandit.samples.appcomponents.MainActivity;
import com.vandit.samples.appcomponents.R;
import com.vandit.samples.appcomponents.callbacks.OnFragmentInteractionListner;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListner} interface
 * to handle interaction events.
 * Use the {@link RecyclerViewMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerViewMainFragment extends Fragment implements View.OnClickListener{

    private OnFragmentInteractionListner mOnFragmentInteractionListner;

    public RecyclerViewMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecyclerViewMainFragment.
     */
    public static RecyclerViewMainFragment newInstance() {
        RecyclerViewMainFragment fragment = new RecyclerViewMainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(view != null){
            // Initialize views.
            Button btnRecyclerList = (Button) view.findViewById(R.id.fragment_recycler_view_main_btn_list);
            Button btnRecyclerGrid = (Button) view.findViewById(R.id.fragment_recycler_view_main_btn_grid);
            Button btnRecyclerStaggered = (Button) view.findViewById(R.id.fragment_recycler_view_main_btn_staggered);

            // Assign click listner to views
            btnRecyclerGrid.setOnClickListener(this);
            btnRecyclerList.setOnClickListener(this);
            btnRecyclerStaggered.setOnClickListener(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null){
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.menu_recycler_view));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListner) {
            mOnFragmentInteractionListner = (OnFragmentInteractionListner) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnFragmentInteractionListner = null;
    }

    @Override
    public void onClick(View v) {
        if(v != null){
            handleClickOfViews(v.getId());
        }
    }

    /**
     * Handle click event of views.
     * @param viewId Clicked view id.
     */
    private void handleClickOfViews(int viewId){
        if(mOnFragmentInteractionListner != null){
            mOnFragmentInteractionListner.onFragmentInteraction(this, viewId);
        }
    }
}
