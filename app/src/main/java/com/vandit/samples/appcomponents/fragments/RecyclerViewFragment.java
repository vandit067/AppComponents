package com.vandit.samples.appcomponents.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vandit.samples.appcomponents.R;
import com.vandit.samples.appcomponents.adapters.RecyclerViewAdapter;
import com.vandit.samples.appcomponents.beans.PersonInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecyclerViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerViewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     ** @return A new instance of fragment RecyclerViewFragment.
     */
    public static RecyclerViewFragment newInstance() {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        // Set linear layout manager in recycler view.
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Set adapter in recycler view
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), getPersonDataList());
        mRecyclerView.setAdapter(recyclerViewAdapter);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * Get person inf list data
     * @return List<PersonInfo> person info list
     */
    private List<PersonInfo> getPersonDataList(){
        List<PersonInfo> personDataList = new ArrayList<>();
        personDataList.add(0, createPersonInfo("Taylor Swift", "Shake it off...", "https://goo.gl/images/6gxkut"));
        personDataList.add(1, createPersonInfo("Beyonce", "Hold up...", "https://goo.gl/images/P9zwj0"));
        personDataList.add(2, createPersonInfo("Eminem", "Rap god...", "https://goo.gl/images/NOo8os"));
        personDataList.add(3, createPersonInfo("Rihana", "work...", "https://goo.gl/images/50DYIL"));
        personDataList.add(4, createPersonInfo("Michael Jackson", "You rock my world...", "https://goo.gl/images/JuoqKv"));
        personDataList.add(5, createPersonInfo("Mariah Carey", "Bye bye...", "https://goo.gl/images/8Vb6TI"));
        personDataList.add(6, createPersonInfo("Lady Gaga", "Million reasons...", "https://goo.gl/images/cslzJe"));
        personDataList.add(7, createPersonInfo("Adele", "When we were young...", "https://goo.gl/images/Zqkqhh"));
        personDataList.add(8, createPersonInfo("Madona", "Bitch i'm madona...", "https://goo.gl/images/3bd28k"));
        personDataList.add(9, createPersonInfo("Britney Spears ", "Slumber party...", "https://goo.gl/images/dH8eWK"));
        return personDataList;
    }

    /**
     * Create person info
     * @param personName person name
     * @param personSong person song
     * @param personImageUrl person image url
     * @return PersonInfo object of {@link PersonInfo}
     */
    private PersonInfo createPersonInfo(String personName, String personSong, String personImageUrl){
         return new PersonInfo(personName, personSong, personImageUrl);
    }
}
