package com.vandit.samples.appcomponents.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.vandit.samples.appcomponents.R;
import com.vandit.samples.appcomponents.beans.PersonInfo;
import com.vandit.samples.appcomponents.constants.AppConstants;
import com.vandit.samples.appcomponents.customviews.CircleImageView;

import java.util.List;

/**
 * Created by vandi on 1/4/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PersonViewHolder> {

    List<PersonInfo> mPersonInfoList;
    Context mContext;
    int mSelectedView;
    int mLayoutId;

    public RecyclerViewAdapter(Context context, List<PersonInfo> personInfoList, int selectedView){
        this.mPersonInfoList = personInfoList;
        this.mContext = context;
        this.mSelectedView = selectedView;
        setLayoutId();
    }

    @Override
    public RecyclerViewAdapter.PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create new view
        View v = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        PersonViewHolder viewHolder = new PersonViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int position) {
        if(personViewHolder != null && !mPersonInfoList.isEmpty()){
            PersonInfo personInfo = mPersonInfoList.get(position);
            if(personInfo != null) {
//                Glide.with(mContext).load(R.mipmap.ic_launcher).into(personViewHolder.mPersonAvtar);
                if(mSelectedView == AppConstants.RECYCLER_VIEW_GRID){
                    personViewHolder.mPersonAvtar.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
                Picasso.with(mContext).load(personInfo.getmPersonAvtar()).into(personViewHolder.mPersonAvtar);
                personViewHolder.mPersonName.setText(personInfo.getmPersonName());
                personViewHolder.mPersonSong.setText(personInfo.getmPersonSong());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mPersonInfoList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    private void setLayoutId(){
        switch (mSelectedView){
            case AppConstants.RECYCLER_VIEW_GRID:
            case AppConstants.RECYCLER_VIEW_STAGGERED:
                mLayoutId = R.layout.recycler_view_item_grid;
                break;
            case AppConstants.RECYCLER_VIEW_LIST:
            default:
                mLayoutId = R.layout.recycler_view_item;
                break;
        }
    }
    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        public TextView mPersonName;
        public TextView mPersonSong;
        public ImageView mPersonAvtar;

        public PersonViewHolder(View itemView) {
            super(itemView);
            mPersonName = (TextView) itemView.findViewById(R.id.recycler_view_item_tv_person_name);
            mPersonSong = (TextView) itemView.findViewById(R.id.recycler_view_item_tv_person_popular_song);
            mPersonAvtar = (ImageView) itemView.findViewById(R.id.recycler_view_item_iv_person);
        }
    }
}
