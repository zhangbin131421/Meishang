package com.mobile.meishang.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.R;
import com.mobile.meishang.imagecache.ImageFetcher;
import com.mobile.meishang.imagecache.ImageWorker;
import com.mobile.meishang.model.LehuigoHomeData;

public class LehuigouHomeExpandAdapter extends BaseExpandableListAdapter {
	private Context mContext;//
	private ImageWorker mImageWorker;
	private LayoutInflater mInflater;//
	private List<LehuigoHomeData> mGroups;

	public LehuigouHomeExpandAdapter() {
	}

	/**
	 * CategoryExpandAdapter
	 * 
	 * @param context
	 */
	public LehuigouHomeExpandAdapter(Context context) {
		mContext = context;
		mImageWorker = new ImageFetcher(context, MApplication.getInstance()
				.getLongest());
		mImageWorker.setImageCache(MApplication.getImageLruCache());
		mGroups = new ArrayList<LehuigoHomeData>();
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mGroups.get(groupPosition).getmList().get(childPosition);
	}

	/**
	 * getChildId
	 */
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	/**
	 * ViewHoder viewHoder; if (convertView == null) { viewHoder = new
	 * ViewHoder(); convertView = mInflater
	 * .inflate(R.layout.expand_item_category_child, null); viewHoder.name =
	 * (TextView) convertView.findViewById(R.id.name);
	 * convertView.setTag(viewHoder); } else { viewHoder = (ViewHoder)
	 * convertView.getTag(); }
	 * viewHoder.name.setText(mAllCategory.get(groupPosition)
	 * .getCategorySecondaryListings().get(childPosition).getName()); return
	 * convertView;
	 */
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		HolderChild holder;
		if (convertView == null) {
			holder = new HolderChild();
			convertView = mInflater.inflate(
					R.layout.item_expand_shopping_home_child, null);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.item_image);
			holder.title = (TextView) convertView
					.findViewById(R.id.item_tv_title);
			holder.describe = (TextView) convertView
					.findViewById(R.id.item_tv_describe);
			// holder.currentPrice = (TextView) convertView
			// .findViewById(R.id.item_tv_current_price);
			// holder.originalPrice = (TextView) convertView
			// .findViewById(R.id.item_tv_original_price);
			convertView.setTag(holder);
		} else {
			holder = (HolderChild) convertView.getTag();
		}
		// viewHoder.name.setText(Html.fromHtml(mGroups.get(groupPosition).getList()
		// .get(childPosition).getTitle()));
		setCacheImage(holder.imageView, mGroups.get(groupPosition).getmList()
				.get(childPosition).getPicpath(),
				R.drawable.loading_bg_img_item);
		holder.title.setText(mGroups.get(groupPosition).getmList()
				.get(childPosition).getTitle());
		holder.describe.setText(mGroups.get(groupPosition).getmList()
				.get(childPosition).getIntegral());
		// holder.currentPrice.setText("￥" + getItem(position).getPrice1());
		// holder.originalPrice.setText("￥" + getItem(position).getPrice());
		// holder.originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mGroups.get(groupPosition).getmList().size();
		// return 2;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return mGroups.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return mGroups.size();
		// return 2;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	/**
	 * if (convertView == null) { viewHoder = new ViewHoder(); convertView =
	 * mInflater.inflate( R.layout.expand_item_category_group, null);
	 * viewHoder.layout = (LinearLayout) convertView
	 * .findViewById(R.id.category_expand_llayout); viewHoder.name = (TextView)
	 * convertView.findViewById(R.id.name); convertView.setTag(viewHoder); }
	 * else { viewHoder = (ViewHoder) convertView.getTag(); } if (isExpanded) {
	 * viewHoder.layout .setBackgroundResource(R.color.orange_myebuy_user_text);
	 * viewHoder.name.setTextColor(mContext.getResources().getColor(
	 * R.color.white)); } else {
	 * viewHoder.layout.setBackgroundResource(R.color.transparent);
	 * viewHoder.name.setTextColor(mContext.getResources().getColor(
	 * R.color.black_black));
	 * 
	 * }
	 */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		ViewHoder viewHoder;
		if (convertView == null) {
			viewHoder = new ViewHoder();
			convertView = mInflater.inflate(
					R.layout.item_expand_shoppoing_home_group, null);
			// viewHoder.arraowImageButton = (ImageView) convertView
			// .findViewById(R.id.arrow_imag_btn);
			viewHoder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			viewHoder.tv_describel = (TextView) convertView.findViewById(R.id.tv_describel);
			convertView.setTag(viewHoder);
		} else {
			viewHoder = (ViewHoder) convertView.getTag();
		}
		// viewHoder.arraowImageButton.setSelected(isExpanded);
		// if (isExpanded)
		// {
		// viewHoder.layout
		// .setBackgroundResource(R.color.orange_myebuy_user_text);
		// viewHoder.name.setTextColor(mContext.getResources().getColor(
		// R.color.white));
		// }
		// else
		// {
		// viewHoder.layout.setBackgroundResource(R.color.transparent);
		// viewHoder.name.setTextColor(mContext.getResources().getColor(
		// R.color.black_black));
		//
		// }
		viewHoder.tv_name.setText(mGroups.get(groupPosition).getText());
		viewHoder.tv_describel.setText(mGroups.get(groupPosition).getText2());
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	/**
	 * isChildSelectable
	 */
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public void addAll(List<LehuigoHomeData> groups) {
		mGroups = groups;
	}

	public List<LehuigoHomeData> getmGroups() {
		return mGroups;
	}

	public void setCacheImage(final ImageView imageView, String imageUrl,
			final int resId) {
		mImageWorker.setLoadingImage(resId);
		mImageWorker.loadImage(imageUrl, imageView);
	}

	public void clear() {
		if (mGroups != null) {
			this.mGroups.clear();
		}
	}

	public void destory() {
		if (mImageWorker != null) {
			mImageWorker.getImageCache().clearCaches();
			mImageWorker.setImageCache(null);
			mImageWorker = null;
		}
		mGroups = null;
		mInflater = null;
	}

	private static class ViewHoder {
		ImageView arraowImageButton;
		TextView tv_name;
		TextView tv_describel;
	}

	private static class HolderChild {
		ImageView imageView;
		TextView title;
		TextView describe;
		// TextView currentPrice;
		// TextView originalPrice;
	}
}
