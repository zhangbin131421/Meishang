package com.mobile.meishang.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.meishang.R;
import com.mobile.meishang.model.Discover;
import com.mobile.meishang.ui.home.ProjectDiscoverDetailActivity;
import com.mobile.meishang.ui.lehuigou.IntegralGoodsDetailActivity;

public class FavoritesProjectListviewAdapter extends
		BaseCacheListAdapter<Discover> {
	private boolean isEdit;
	private List<Integer> checkPositions;

	public FavoritesProjectListviewAdapter(Context context) {
		super(context);
		checkPositions = new ArrayList<Integer>();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(
					R.layout.item_lview_favorites_project, null);
			holder.llayout_item = (LinearLayout) convertView
					.findViewById(R.id.llayout_item);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.item_image);
			holder.name = (TextView) convertView.findViewById(R.id.item_name);
			holder.describe = (TextView) convertView
					.findViewById(R.id.item_describe);
			holder.tv_middlen = (TextView) convertView
					.findViewById(R.id.tv_middlen);
			holder.tv_count = (TextView) convertView
					.findViewById(R.id.tv_count);
			holder.flayout_item = (FrameLayout) convertView
					.findViewById(R.id.flayout_item);
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
					bundle.putString("projectid", getItem(position)
							.getProjectid());
					intent.putExtra("bundle", bundle);
					intent.setClass(mContext,
							ProjectDiscoverDetailActivity.class);
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
		ImageView imageView;
		TextView name;
		TextView describe;
		TextView tv_middlen;
		TextView tv_count;
		FrameLayout flayout_item;
	}

}
