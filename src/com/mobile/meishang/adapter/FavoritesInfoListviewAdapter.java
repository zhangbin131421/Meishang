package com.mobile.meishang.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.meishang.R;
import com.mobile.meishang.model.Infomation;
import com.mobile.meishang.ui.home.ProjectDiscoverDetailActivity;
import com.mobile.meishang.ui.infomation.InfoDetailActivity;

public class FavoritesInfoListviewAdapter extends
		BaseCacheListAdapter<Infomation> {
	private boolean isEdit;
	private List<Integer> checkPositions;

	public FavoritesInfoListviewAdapter(Context context) {
		super(context);
		checkPositions = new ArrayList<Integer>();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(R.layout.item_lview_favorites_info,
					null);
			holder.llayout_item = (LinearLayout) convertView
					.findViewById(R.id.llayout_item);
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
			holder.flayout_item = (FrameLayout) convertView
					.findViewById(R.id.flayout_item);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.tv_item_name.setText(getItem(position).getTitle());
		if (isEdit) {
			if (checkPositions.contains(position)) {
				holder.flayout_item.setVisibility(View.VISIBLE);
			} else {
				holder.flayout_item.setVisibility(View.GONE);
			}
			holder.llayout_item.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(mContext, "编辑" + position,
							Toast.LENGTH_SHORT).show();
					holder.flayout_item.setVisibility(View.VISIBLE);
					checkPositions.add(position);

				}
			});
			holder.flayout_item.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (holder.flayout_item.isShown()) {
						holder.flayout_item.setVisibility(View.INVISIBLE);
						if (checkPositions.contains(position)) {
							checkPositions.remove(checkPositions
									.indexOf(position));
						}
					} else {
						holder.flayout_item.setVisibility(View.VISIBLE);
						checkPositions.add(position);

					}
				}
			});

		} else {
			holder.flayout_item.setVisibility(View.GONE);
			holder.llayout_item.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					Bundle bundle = new Bundle();
					bundle.putString("infoid", getItem(position).getInfoid());
					intent.putExtra("bundle", bundle);
					intent.setClass(mContext, InfoDetailActivity.class);
					mContext.startActivity(intent);

				}
			});
		}

		return convertView;
	}

	@Override
	public void refreshImageView(AbsListView view) {

	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public List<Integer> getCheckPositions() {
		return checkPositions;
	}

	static class Holder {
		LinearLayout llayout_item;
		ImageView image_item;
		TextView tv_item_name;
		TextView tv_item_describe;
		TextView tv_item_type;
		TextView tv_item_time;
		FrameLayout flayout_item;
	}

}
