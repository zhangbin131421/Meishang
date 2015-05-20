package com.mobile.meishang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.bean.User;

public class BidDetailGridviewAdapter extends BaseCacheListAdapter<User> {

	public BidDetailGridviewAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(R.layout.item_gridview_bid_detail, null);
			holder.item_image = (ImageView) convertView
					.findViewById(R.id.item_image);
			holder.item_name = (TextView) convertView
					.findViewById(R.id.item_name);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.item_name.setText(getItem(position).getNickname());
		setCacheImage(holder.item_image, getItem(position).getHeaderpath(),
				R.drawable.loading_bg_img_shop);
		return convertView;
	}

	static class Holder {
		ImageView item_image;
		TextView item_name;
	}

	@Override
	public void refreshImageView(AbsListView view) {

	}

}
