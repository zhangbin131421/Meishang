package com.mobile.meishang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.bean.GoodsItem;

public class MySharedListviewAdapter extends BaseCacheListAdapter<GoodsItem> {

	public MySharedListviewAdapter(Context context) {
		super(context);
	}

	@Override
	public int getCount() {
		// return super.getCount();
		return 10;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater
					.inflate(R.layout.item_lview_my_shared, null);
			holder.img_avatar = (ImageView) convertView
					.findViewById(R.id.img_avatar);
			holder.tv_item_name = (TextView) convertView
					.findViewById(R.id.tv_item_name);
			holder.tv_item_date = (TextView) convertView
					.findViewById(R.id.tv_item_date);
			holder.tv_item_content = (TextView) convertView
					.findViewById(R.id.tv_item_content);
			holder.image_item = (ImageView) convertView
					.findViewById(R.id.image_item);
			holder.tv_item_title = (TextView) convertView
					.findViewById(R.id.tv_item_title);
			holder.tv_item_price = (TextView) convertView
					.findViewById(R.id.tv_item_price);
			holder.tv_item_price_b = (TextView) convertView
					.findViewById(R.id.tv_item_price_b);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		return convertView;
	}

	@Override
	public void refreshImageView(AbsListView view) {

	}

	static class Holder {
		ImageView img_avatar;
		TextView tv_item_name;
		TextView tv_item_date;
		TextView tv_item_content;
		ImageView image_item;
		TextView tv_item_title;
		TextView tv_item_price;
		TextView tv_item_price_b;
	}

}
