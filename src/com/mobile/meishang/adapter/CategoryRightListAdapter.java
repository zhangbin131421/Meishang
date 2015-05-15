package com.mobile.meishang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.ModuleChild;

public class CategoryRightListAdapter extends BaseListAdapter<ModuleChild> {

	private Context mContext;
	private int mPosition = -1;

	public CategoryRightListAdapter(Context context) {
		super();
		mContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_filter_right_listview, null);
			holder.name = (TextView) convertView
					.findViewById(R.id.tv_name_item);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.name.setText(getItem(position).getName());
		if (mPosition == position) {
			holder.name.setSelected(true);
		} else {
			holder.name.setSelected(false);
		}
		return convertView;
	}

	static class Holder {
		// ImageView imageView;
		TextView name;
	}

	public int getmPosition() {
		return mPosition;
	}

	public void setmPosition(int mPosition) {
		this.mPosition = mPosition;
	}

}
