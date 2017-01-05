package com.vandit.samples.appcomponents.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vandit.samples.appcomponents.R;
import com.vandit.samples.appcomponents.beans.PersonInfo;
import com.vandit.samples.appcomponents.util.CircleImageView;

import java.util.List;

/**
 * Created by vandi on 1/4/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PersonViewHolder> {

    List<PersonInfo> mPersonInfoList;
    Context mContext;

    public RecyclerViewAdapter(Context context, List<PersonInfo> personInfoList){
        this.mPersonInfoList = personInfoList;
        this.mContext = context;
    }

    @Override
    public RecyclerViewAdapter.PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        PersonViewHolder viewHolder = new PersonViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int position) {
        if(personViewHolder != null && !mPersonInfoList.isEmpty()){
            PersonInfo personInfo = mPersonInfoList.get(position);
            if(personInfo != null) {
                Glide.with(mContext).load(personInfo.getmPersonAvtar()).into(personViewHolder.mPersonAvtar);
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

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        public TextView mPersonName;
        public TextView mPersonSong;
        public CircleImageView mPersonAvtar;

        public PersonViewHolder(View itemView) {
            super(itemView);
            mPersonName = (TextView) itemView.findViewById(R.id.recycler_view_item_tv_person_name);
            mPersonSong = (TextView) itemView.findViewById(R.id.recycler_view_item_tv_person_popular_song);
            mPersonAvtar = (CircleImageView) itemView.findViewById(R.id.recycler_view_item_iv_person);
        }
    }
}
