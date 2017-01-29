package com.vandit.samples.appcomponents.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vandit.samples.appcomponents.MainActivity;
import com.vandit.samples.appcomponents.R;
import com.vandit.samples.appcomponents.adapters.RecyclerViewAdapter;
import com.vandit.samples.appcomponents.beans.PersonInfo;
import com.vandit.samples.appcomponents.constants.AppConstants;
import com.vandit.samples.appcomponents.customviews.SpacesItemDecoration;
import com.vandit.samples.appcomponents.util.AppUtils;

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
    private static final String KEY_SELECTED_VIEW = "selected_view";
    private static final String KEY_ACTIONBAR_TITLE = "actionbar_title";
    private int selectedView;
    private String actionBarTitle;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param selectedView Selected view from previous section.
     * @param actionBarTitle Actionbar title.
     ** @return A new instance of fragment RecyclerViewFragment.
     */
    public static RecyclerViewFragment newInstance(int selectedView, String actionBarTitle) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_SELECTED_VIEW, selectedView);
        bundle.putString(KEY_ACTIONBAR_TITLE, actionBarTitle);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            parseIntentData(getArguments());
        }
        if(!TextUtils.isEmpty(actionBarTitle) && getActivity() != null) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(actionBarTitle);
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


        /*if(AppUtils.isTablet(getContext())){
            // Set grid layout manager in recycler view.
            mLayoutManager = new GridLayoutManager(getContext(), 2);
        } else {
            // Set linear layout manager in recycler view.
            mLayoutManager = new LinearLayoutManager(getContext());
        }*/

        setRecyclerViewLayoutManager();
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Add decoration of recycler view.
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(16));

        //Set adapter in recycler view
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), getPersonDataList(), selectedView);
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

    private void setRecyclerViewLayoutManager(){
        switch (selectedView){
            case AppConstants.RECYCLER_VIEW_GRID:
                // Set grid layout manager in recycler view.
                mLayoutManager = new GridLayoutManager(getContext(), 2);
                break;
            case AppConstants.RECYCLER_VIEW_STAGGERED:
                // Set staggered grid layout manager in recycler view.
                mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                break;
            case AppConstants.RECYCLER_VIEW_LIST:
            default:
                // Set linear layout manager in recycler view.
                mLayoutManager = new LinearLayoutManager(getContext());
                break;
        }
    }
    /**
     * Parse intent data.
     * @param bundle Bundle contains data which will be used by current screen.
     */
    private void parseIntentData(Bundle bundle){
        if(bundle != null){
            selectedView = bundle.getInt(KEY_SELECTED_VIEW);
            actionBarTitle = bundle.getString(KEY_ACTIONBAR_TITLE);
        }
    }

    /**
     * Get person inf list data
     * @return List<PersonInfo> person info list
     */
    private List<PersonInfo> getPersonDataList(){
        List<PersonInfo> personDataList = new ArrayList<>();
        personDataList.add(0, createPersonInfo("Taylor Swift", "Shake it off...", "http://skepchick.org/wp-content/uploads/2016/11/swift.jpg"));
        personDataList.add(1, createPersonInfo("Beyonce", "Hold up...", "http://akns-images.eonline.com/eol_images/Entire_Site/2015919/rs_634x950-151019094346-634.Beyonce-Beat-Magazine-4-Kf.101915.jpg"));
        personDataList.add(2, createPersonInfo("Eminem", "Rap god...", "http://www.haftasonu.com.tr/images/stories/haber/01-02-03-04/03/032012-eminem.jpg"));
        personDataList.add(3, createPersonInfo("Rihana", "work...", "https://static01.nyt.com/images/2015/10/12/t-magazine/12tmag-rihanna-add-t/12tmag-rihanna-add-t-blog427.jpg"));
        personDataList.add(4, createPersonInfo("Michael Jackson", "You rock my world...", "https://pbs.twimg.com/media/CC4xAqwUEAAHGLZ.jpg"));
        personDataList.add(5, createPersonInfo("Mariah Carey", "Bye bye...", "http://us.hellomagazine.com/imagenes/film/2015081226709/britney-spears-jane-the-virgin-mariah-carey-empire/0-134-296/mariah-carey--a.jpg"));
        personDataList.add(6, createPersonInfo("Lady Gaga", "Million reasons...", "http://www.stars-portraits.com/img/portraits/stars/l/lady-gaga/lady-gaga-1-by-Sanni[137961].jpg"));
        personDataList.add(7, createPersonInfo("Adele", "When we were young...", "http://www.billboard.com/files/styles/article_main_image/public/media/Adele-2015-press-Alasdair-McLellan-XL-billboard-650-2.jpg"));
        personDataList.add(8, createPersonInfo("Madona", "Bitch i'm madona...", "http://image.way2enjoy.com/pic/24/98/03/0/600full-madonna.jpg"));
        personDataList.add(9, createPersonInfo("Britney Spears ", "Slumber party...", "http://gazettereview.com/wp-content/uploads/2016/05/mgid-ao-image-mtv.jpg"));
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
