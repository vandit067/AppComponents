package com.vandit.samples.appcomponents.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vandit.samples.appcomponents.R;
import com.vandit.samples.appcomponents.adapters.TabsFragmentPagerAdapter;
import com.vandit.samples.appcomponents.callbacks.OnFragmentInteractionListner;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListner} interface
 * to handle interaction events.
 * Use the {@link TabsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabsFragment extends Fragment implements TabsFragmentPagerAdapter.TabAdapterListner{

    private OnFragmentInteractionListner mListener;

    public TabsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TabsFragment.
     */
    public static TabsFragment newInstance() {
        TabsFragment fragment = new TabsFragment();
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
        return inflater.inflate(R.layout.fragment_tabs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(view != null){
            final ViewPager viewPager = (ViewPager) view.findViewById(R.id.fragment_tabs_viewpager);
            viewPager.setAdapter(new TabsFragmentPagerAdapter(getActivity().getSupportFragmentManager(), getActivity(), this));

            //Give the tablayout to viewpager
            TabLayout tabLayout = (TabLayout) view.findViewById(R.id.fragment_tabs_sliding);
            tabLayout.setupWithViewPager(viewPager, true);

            // Handle page change event of view pager
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListner) {
            mListener = (OnFragmentInteractionListner) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Return instance of {@link RecyclerViewFragment} and pass selected view to display data in recycler view
     * @param viewId Selected view to display in recycler view.
     * @param title Actionbar title.
     */
    @Override
    public Fragment getRecyclerViewFragmentBasedOnId(int viewId, String title) {
        return RecyclerViewFragment.newInstance(viewId, title);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
