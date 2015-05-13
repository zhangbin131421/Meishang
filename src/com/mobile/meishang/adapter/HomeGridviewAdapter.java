package com.mobile.meishang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.bean.HomeFragmentTemplateDataItem;

public class HomeGridviewAdapter extends
		BaseCacheListAdapter<HomeFragmentTemplateDataItem> {

	public HomeGridviewAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(R.layout.item_gridview_home, null);
			holder.item_image = (ImageView) convertView
					.findViewById(R.id.item_image);
			holder.item_name = (TextView) convertView
					.findViewById(R.id.item_name);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.item_name.setText(getItem(position).getModulename());
		// setCacheImage(holder.item_image,
		// getItem(position).getModulepicpath(),
		// R.drawable.loading_bg_img_item);
		holder.item_image.setImageResource(getItem(position).getImage());
		return convertView;
	}

	static class Holder {
		ImageView item_image;
		TextView item_name;

	}

	@Override
	public void refreshImageView(AbsListView view) {
		// TODO Auto-generated method stub

	}

}
