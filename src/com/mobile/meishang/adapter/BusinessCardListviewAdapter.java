package com.mobile.meishang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.bean.BusinessCardItem;

public class BusinessCardListviewAdapter extends
		BaseCacheListAdapter<BusinessCardItem> {

	public BusinessCardListviewAdapter(Context context) {
		super(context);
	}

	@Override
	public int getCount() {
		// return super.getCount() - 1;

		return 10;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(
					R.layout.item_listview_business_card, null);
			holder.item_image = (ImageView) convertView
					.findViewById(R.id.item_image);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		// holder.item_tv_name.setText(getItem(position).getModulename());
		// holder.item_image.setImageResource(getItem(position).getImage());
		// if (getItem(position).getFlag() == 0) {
		// holder.item_tv_add.setVisibility(View.VISIBLE);
		// holder.item_image_add.setVisibility(View.GONE);
		// } else {
		// holder.item_tv_add.setVisibility(View.GONE);
		// holder.item_image_add.setVisibility(View.VISIBLE);
		//
		// }
		return convertView;
	}

	@Override
	public void refreshImageView(AbsListView view) {

	}

	static class Holder {
		ImageView item_image;
		TextView item_tv_name;
		TextView tv_look;
	}

}
