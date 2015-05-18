package com.mobile.meishang.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.meishang.R;
import com.mobile.meishang.model.LehuigoDetailData;

public class ShoppingOrderListAdapter extends
		BaseCacheListAdapter<LehuigoDetailData> {

	public ShoppingOrderListAdapter(Context context) {
		super(context);

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(
					R.layout.item_listview_shopping_order, null);
			holder.llayout_item = (LinearLayout) convertView
					.findViewById(R.id.llayout_item);
			holder.image_item = (ImageView) convertView
					.findViewById(R.id.image_item);
			holder.tv_item_name = (TextView) convertView
					.findViewById(R.id.tv_item_name);
			holder.tv_item_price = (TextView) convertView
					.findViewById(R.id.tv_item_price);
			holder.tv_item_price_b = (TextView) convertView
					.findViewById(R.id.tv_item_price_b);
			holder.tv_item_add = (TextView) convertView
					.findViewById(R.id.tv_item_add);
			holder.tv_item_quantity = (TextView) convertView
					.findViewById(R.id.tv_item_quantity);
			holder.tv_item_sub = (TextView) convertView
					.findViewById(R.id.tv_item_sub);
			holder.llayout_item.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// if (holder.image_item_check_box.isSelected()) {
					// holder.image_item_check_box.setSelected(false);
					// if (checkPositions.contains(position)) {
					// checkPositions.remove(checkPositions
					// .indexOf(position));
					// }
					// } else {
					// holder.image_item_check_box.setSelected(true);
					// checkPositions.add(position);
					//
					// }
				}
			});
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		holder.tv_item_name.setText(getItem(position).getTitle());
		holder.tv_item_price.setText("原价:¥" + getItem(position).getPrice());
		holder.tv_item_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		holder.tv_item_price_b.setText(getItem(position).getIntegral() + "积分");
		setCacheImage(holder.image_item, getItem(position).getPicpath(),
				R.drawable.loading_bg_img_item);
		holder.tv_item_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int quantity = Integer
						.parseInt( holder.tv_item_quantity.getText().toString()) + 1;
				holder.tv_item_quantity.setText(quantity + "");

			}
		});
		holder.tv_item_sub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int quantity = Integer
						.parseInt(holder.tv_item_quantity.getText().toString()) - 1;
				if (quantity > 0) {
					holder.tv_item_quantity.setText(quantity + "");
				} else {
					Toast.makeText(mContext, "不能小于0", Toast.LENGTH_SHORT)
							.show();
				}

			}
		});

		return convertView;
	}

	@Override
	public void refreshImageView(AbsListView view) {

	}

	static class Holder {
		LinearLayout llayout_item;
		ImageView image_item;
		TextView tv_item_name;
		TextView tv_item_price;
		TextView tv_item_price_b;
		TextView tv_item_add;
		TextView tv_item_quantity;
		TextView tv_item_sub;
	}

}
