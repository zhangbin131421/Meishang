package com.mobile.meishang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.Discover;
import com.mobile.meishang.model.LehuigoHomeDataItem;

public class SignInListviewAdapter extends BaseCacheListAdapter<Discover> {

	public SignInListviewAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(R.layout.item_lview_sign_in, null);
			holder.item_image = (ImageView) convertView
					.findViewById(R.id.item_image);
			holder.item_tv_name = (TextView) convertView
					.findViewById(R.id.item_tv_name);
			holder.item_tv_describe = (TextView) convertView
					.findViewById(R.id.item_tv_describe);
			holder.item_tv_price = (TextView) convertView
					.findViewById(R.id.item_tv_price);
			holder.item_tv_award = (TextView) convertView
					.findViewById(R.id.item_tv_award);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.item_tv_name.setText(getItem(position).getTitle());
		holder.item_tv_describe.setText(getItem(position).getIntroduction());
		holder.item_tv_price.setText(getItem(position).getMiddlen());
		holder.item_tv_award.setText("奖励"+getItem(position).getIntegral()+"积分");
		setCacheImage(holder.item_image, getItem(position).getPicpath(),
				R.drawable.loading_bg_img_item);

		return convertView;
	}

	@Override
	public void refreshImageView(AbsListView view) {

	}

	static class Holder {
		ImageView item_image;
		TextView item_tv_name;
		TextView item_tv_describe;
		TextView item_tv_price;
		TextView item_tv_award;
	}

}
