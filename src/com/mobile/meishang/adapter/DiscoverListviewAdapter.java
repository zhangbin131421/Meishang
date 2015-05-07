package com.mobile.meishang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.Discover;

public class DiscoverListviewAdapter extends BaseCacheListAdapter<Discover> {

	public DiscoverListviewAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(R.layout.item_listview_discover,
					null);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.item_image);
			holder.name = (TextView) convertView.findViewById(R.id.item_name);
			holder.describe = (TextView) convertView
					.findViewById(R.id.item_describe);
			holder.tv_middlen = (TextView) convertView
					.findViewById(R.id.tv_middlen);
			holder.tv_count = (TextView) convertView
					.findViewById(R.id.tv_count);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		setCacheImage(holder.imageView, getItem(position).getPicpath(),
				R.drawable.loading_bg_img_item);
		holder.name.setText(getItem(position).getTitle());
		holder.describe.setText(getItem(position).getIntroduction());
		holder.tv_middlen.setText(getItem(position).getMiddlen());
		holder.tv_count.setText(getItem(position).getCount());

		return convertView;
	}

	@Override
	public void refreshImageView(AbsListView view) {

	}

	static class Holder {
		ImageView imageView;
		TextView name;
		TextView describe;
		TextView tv_middlen;
		TextView tv_count;
	}

}
