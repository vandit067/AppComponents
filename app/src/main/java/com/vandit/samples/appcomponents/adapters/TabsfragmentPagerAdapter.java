package com.vandit.samples.appcomponents.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.vandit.samples.appcomponents.R;
import com.vandit.samples.appcomponents.constants.AppConstants;

/**
 * Created by vandi on 1/29/2017.
 */

public class TabsFragmentPagerAdapter extends FragmentStatePagerAdapter {

    final int mPageCount = 3;
    private String[] mTabsTitles = new String[]{"Grid","List", "Staggered"};
    private Context mContext;
    private TabAdapterListner mTabAdapterListner;
    public interface TabAdapterListner{
        Fragment getRecyclerViewFragmentBasedOnId(int viewId, String title);
    }

    public TabsFragmentPagerAdapter(FragmentManager fm, Context context, TabAdapterListner tabAdapterListner) {
        super(fm);
        this.mContext = context;
        this.mTabAdapterListner = tabAdapterListner;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        String title = mContext.getString(R.string.menu_tabs);
        switch (position){
            case 0:
                fragment = mTabAdapterListner.getRecyclerViewFragmentBasedOnId(AppConstants.RECYCLER_VIEW_GRID,
                        title);
                break;
            case 1:
                fragment = mTabAdapterListner.getRecyclerViewFragmentBasedOnId(AppConstants.RECYCLER_VIEW_LIST,
                        title);
                break;
            case 2:
                fragment = mTabAdapterListner.getRecyclerViewFragmentBasedOnId(AppConstants.RECYCLER_VIEW_STAGGERED,
                        title);
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mPageCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //Generate tabs title based on position
        return mTabsTitles[position];
    }
}
