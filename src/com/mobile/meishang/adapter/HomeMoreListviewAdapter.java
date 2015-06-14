package com.mobile.meishang.adapter;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.bean.HomeFragmentTemplateDataItem;

public class HomeMoreListviewAdapter extends
		BaseCacheListAdapter<HomeFragmentTemplateDataItem> {
	private Map<Integer, Integer> map;

	public HomeMoreListviewAdapter(Context context) {
		super(context);
		map = new HashMap<Integer, Integer>();
		int[] image = { R.drawable.ic_home_a, R.drawable.ic_home_b,
				R.drawable.ic_home_c, R.drawable.ic_home_d,
				R.drawable.ic_home_e, R.drawable.ic_home_f,
				R.drawable.ic_home_g, R.drawable.ic_home_h, R.drawable.ic_add };
		for (int i = 0; i < image.length; i++) {
			map.put(i, image[i]);
		}
	}

	@Override
	public int getCount() {
		return super.getCount() - 1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(R.layout.item_listview_home_more,
					null);
			holder.item_image = (ImageView) convertView
					.findViewById(R.id.item_image);
			holder.flayout = (FrameLayout) convertView
					.findViewById(R.id.flayout);
			holder.item_tv_name = (TextView) convertView
					.findViewById(R.id.item_tv_name);
			holder.item_tv_add = (TextView) convertView
					.findViewById(R.id.item_tv_add);
			holder.item_image_add = (ImageView) convertView
					.findViewById(R.id.item_image_add);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.item_tv_name.setText(getItem(position).getModulename());
		holder.item_image.setImageResource(map
				.get(getItem(position).getImage()));
		if (getItem(position).getFlag() == 0) {
			holder.item_tv_add.setVisibility(View.VISIBLE);
			holder.item_image_add.setVisibility(View.GONE);
		} else {
			holder.item_tv_add.setVisibility(View.GONE);
			holder.item_image_add.setVisibility(View.VISIBLE);

		}
		return convertView;
	}

	@Override
	public void refreshImageView(AbsListView view) {

	}

	static class Holder {
		ImageView item_image;
		TextView item_tv_name;
		TextView item_tv_add;
		ImageView item_image_add;
		FrameLayout flayout;
	}

}
