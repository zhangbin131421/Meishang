package com.mobile.meishang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.Infomation;

public class InfoListviewAdapter extends BaseCacheListAdapter<Infomation> {

	public InfoListviewAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(R.layout.item_lview_info, null);
			holder.image_item = (ImageView) convertView
					.findViewById(R.id.image_item);
			holder.tv_item_name = (TextView) convertView
					.findViewById(R.id.tv_item_name);
			holder.tv_item_describe = (TextView) convertView
					.findViewById(R.id.tv_item_describe);
			holder.tv_item_type = (TextView) convertView
					.findViewById(R.id.tv_item_type);
			holder.tv_item_time = (TextView) convertView
					.findViewById(R.id.tv_item_time);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.tv_item_name.setText(getItem(position).getTitle());
		holder.tv_item_describe.setText(getItem(position).getContext());
		holder.tv_item_time.setText(getItem(position).getCreatetime());
		setCacheImage(holder.image_item, getItem(position).getPicpath(),
				R.drawable.loading_bg_img_item);

		return convertView;
	}

	@Override
	public void refreshImageView(AbsListView view) {

	}

	static class Holder {
		ImageView image_item;
		TextView tv_item_name;
		TextView tv_item_describe;
		TextView tv_item_type;
		TextView tv_item_time;
	}

}
