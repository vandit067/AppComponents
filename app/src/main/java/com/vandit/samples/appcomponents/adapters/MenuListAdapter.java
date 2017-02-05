package com.vandit.samples.appcomponents.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vandit.samples.appcomponents.R;
import com.vandit.samples.appcomponents.enums.MenuActionItem;

/**
 * Created by vandi on 2/4/2017.
 */

public class MenuListAdapter extends ArrayAdapter<MenuActionItem> {
    int resource;
    Context mContext;

    public MenuListAdapter(Context context, int resource, MenuActionItem[] objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
            MenuItemViewHolder viewHolder = new MenuItemViewHolder();
            viewHolder.menuItemImageView = (ImageView) convertView.findViewById(R.id.menu_item_image_view);
            viewHolder.menuItemTextView = (TextView) convertView.findViewById(R.id.menu_item_text_view);
            convertView.setTag(viewHolder);
        }
        MenuItemViewHolder holder = (MenuItemViewHolder) convertView.getTag();
        if (position == MenuActionItem.ALARM.getPosition()) {
            holder.menuItemImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_access_alarm));
            holder.menuItemTextView.setText(R.string.alarm);
        } else if (position == MenuActionItem.APPS.getPosition()) {
            holder.menuItemImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_apps));
            holder.menuItemTextView.setText(R.string.apps);
        } else if (position == MenuActionItem.ATTACHMENT.getPosition()) {
            holder.menuItemImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_attachment));
            holder.menuItemTextView.setText(R.string.attachment);
        } else if (position == MenuActionItem.BATTERY.getPosition()) {
            holder.menuItemImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_battery));
            holder.menuItemTextView.setText(R.string.battery);
        } else if (position == MenuActionItem.BLUETOOTH.getPosition()) {
            holder.menuItemImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_bluetooth));
            holder.menuItemTextView.setText(R.string.bluetooth);
        } else if (position == MenuActionItem.BOOK.getPosition()) {
            holder.menuItemImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_book));
            holder.menuItemTextView.setText(R.string.book);
        } else if (position == MenuActionItem.BOOKMARK.getPosition()) {
            holder.menuItemImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_bookmark));
            holder.menuItemTextView.setText(R.string.bookmark);
        } else if (position == MenuActionItem.BRIGHTNESS.getPosition()) {
            holder.menuItemImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_brightness));
            holder.menuItemTextView.setText(R.string.brightness);
        } else if (position == MenuActionItem.CAST.getPosition()) {
            holder.menuItemImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_cast));
            holder.menuItemTextView.setText(R.string.cast);
        } else if (position == MenuActionItem.SEND.ordinal()) {
            holder.menuItemImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_send));
            holder.menuItemTextView.setText(R.string.send);
        } else if (position == MenuActionItem.STORAGE.getPosition()) {
            holder.menuItemImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_storage));
            holder.menuItemTextView.setText(R.string.storage);
        }
        return convertView;
    }

    private static class MenuItemViewHolder {
        public ImageView menuItemImageView;
        public TextView menuItemTextView;
    }
}
