package com.mobile.meishang.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.meishang.R;
import com.mobile.meishang.model.BusinessCard;
import com.mobile.meishang.ui.home.BusinessCardListActivity;

public class BusinessCardListviewAdapter extends
		BaseCacheListAdapter<BusinessCard> {
	private BusinessCardListActivity businessCardListActivity;

	public BusinessCardListviewAdapter(BusinessCardListActivity context) {
		super(context);
		businessCardListActivity = context;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (null == convertView) {
			holder = new Holder();
			convertView = mInflater.inflate(
					R.layout.item_listview_business_card, null);
			holder.item_image = (ImageView) convertView
					.findViewById(R.id.item_image);
			holder.item_tv_name = (TextView) convertView
					.findViewById(R.id.item_tv_name);
			holder.tv_describel = (TextView) convertView
					.findViewById(R.id.tv_describel);
			holder.tv_look = (TextView) convertView.findViewById(R.id.tv_look);
			holder.tv_look.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					switch (getItem(position).getStatus()) {
					case 1:// 1、我没有名片不可以发交换名片请求
						businessCardListActivity.goCardAdd();
						break;
					case 2:// 2、我有名片未交换名片
						businessCardListActivity.goExChangeCard(position);
						break;
					case 3:// 3、交换名片成功 并保存到人脉圈中去、已经是人脉圈中的如果有可以直接查看对方名片
						businessCardListActivity.goCardInfo();
						break;

					default:
						break;
					}

				}
			});
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		switch (getItem(position).getStatus()) {
		case 3:
			holder.tv_look.setText("查看名片");
			break;

		default:
			holder.tv_look.setText("交换名片");
			break;
		}
		holder.item_tv_name.setText(getItem(position).getName());
		holder.tv_describel.setText(getItem(position).getPosition());
		setCacheImage(holder.item_image, getItem(position).getHeaderpath(),
				R.drawable.loading_bg_img_item);
		return convertView;
	}

	@Override
	public void refreshImageView(AbsListView view) {

	}

	static class Holder {
		ImageView item_image;
		TextView item_tv_name;
		TextView tv_describel;
		TextView tv_look;
	}

}
