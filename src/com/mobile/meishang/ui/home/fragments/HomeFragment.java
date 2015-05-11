package com.mobile.meishang.ui.home.fragments;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.AdvertisingGalleryAdapter;
import com.mobile.meishang.adapter.HomeGridviewAdapter;
import com.mobile.meishang.config.Constants;
import com.mobile.meishang.core.request.HomeFragmentRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.AdvertisingGalleryItem;
import com.mobile.meishang.model.bean.HomeFragmentData;
import com.mobile.meishang.model.bean.HomeFragmentTemplateDataItem;
import com.mobile.meishang.ui.bid.IWantBidActivity;
import com.mobile.meishang.ui.home.InsideActivity;
import com.mobile.meishang.ui.infomation.InfoListActivity;
import com.mobile.meishang.ui.lehuigou.LehuigoHomeActvity;
import com.mobile.meishang.ui.widget.GridViewWithHeaderAndFooter;
import com.mobile.meishang.utils.view.AdGallery;
import com.umeng.analytics.MobclickAgent;

public class HomeFragment extends MFragment implements OnClickListener {
	private LinearLayout mCityLayout;
	private TextView mCityTextView;
	private final int ADVREFRESH = 1;
	private int selectedPosition = 0;
	private int realPosition = 0;
	private int galleryImgNum;
	private int refreshTime = 2000;
	private RefreshAdvRun mRefreshAdvRun;
	private GridViewWithHeaderAndFooter mGridView;
	private HomeGridviewAdapter mGridviewAdapter;
	private View headView;
	private ImageView img_temp;
	private AdGallery mAdGallery;
	private LinearLayout mAdDotLayout;
	private ImageView[] dotHolder;
	private AdvertisingGalleryAdapter mAdvertisingAdapter;
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case ADVREFRESH:
				mAdGallery.setSelection(selectedPosition++);
				mHandler.postDelayed(mRefreshAdvRun, refreshTime);
				break;
			default:
				break;
			}
		}
	};
	private List<AdvertisingGalleryItem> mAdvertisings;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, null);
		mCityLayout = (LinearLayout) view.findViewById(R.id.llayout_city);
		mCityTextView = (TextView) view.findViewById(R.id.tv_city_name);
		mGridView = (GridViewWithHeaderAndFooter) view
				.findViewById(R.id.gridview);
		headView = LayoutInflater.from(mContext).inflate(
				R.layout.layout_home_hview, null);
		if (null != headView) {
			img_temp = (ImageView) headView.findViewById(R.id.img_temp);
			mAdGallery = (AdGallery) headView.findViewById(R.id.ad_gallery);
			mAdGallery.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					realPosition = position % galleryImgNum;
					selectedPosition = position;
					for (int i = 0; i < galleryImgNum; i++) {
						if (i == realPosition) {
							dotHolder[i]
									.setBackgroundResource(R.drawable.banner_round_select);
						} else {
							dotHolder[i]
									.setBackgroundResource(R.drawable.banner_round_normal);
						}
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {

				}
			});

			mAdGallery.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> paramAdapterView,
						View paramView, int paramInt, long paramLong) {
					AdvertisingGalleryItem advertising = mAdvertisings
							.get(realPosition);
					Bundle bundle = new Bundle();
					// bundle.putString("name", advertising.getName());
					// bundle.putString("actid", advertising.getActid());
					// goActivity(AdvertisingListActivity.class, bundle);
					// goActivity(AdvertisingExpandbleActivity.class, bundle);
				}
			});
			mAdGallery.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						mHandler.removeCallbacks(mRefreshAdvRun);
						break;
					case MotionEvent.ACTION_UP:
						mHandler.postDelayed(mRefreshAdvRun, refreshTime);
						break;
					}
					return false;
				}
			});

			mAdDotLayout = (LinearLayout) headView
					.findViewById(R.id.ad_dot_llayout);

		}

		mGridView.addHeaderView(headView, null, false);
		mAdvertisingAdapter = new AdvertisingGalleryAdapter(mContext);
		mAdGallery.setAdapter(mAdvertisingAdapter);
		mGridviewAdapter = new HomeGridviewAdapter(mContext);
		mGridView.setAdapter(mGridviewAdapter);
		// mGridView.setOnItemLongClickListener(new OnItemLongClickListener() {
		//
		// @Override
		// public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
		// int arg2, long arg3) {
		// showToast("changan");
		// return false;
		// }
		// });
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				position -= 3;
//				showToast("p=" + position);
				Bundle bundle = new Bundle();
				bundle.putString("url", mGridviewAdapter.getItem(position)
						.getModuleurl());
				switch (position) {
				case 0:
					goActivity(LehuigoHomeActvity.class, null);
					break;
				case 1:
					goActivity(IWantBidActivity.class, null);
					break;
				case 2:
					goActivity(InsideActivity.class, bundle);
					break;
				case 3:
					goActivity(InsideActivity.class, bundle);
					break;
				case 4:
					goActivity(InsideActivity.class, bundle);
					break;
				case 5:
					goActivity(InsideActivity.class, bundle);
					break;
				case 6:
					goActivity(InsideActivity.class, bundle);
					break;
				case 7:
					goActivity(InsideActivity.class, bundle);
					break;
				case 8:
					goActivity(InfoListActivity.class, null);
					// goActivity(GoodsSearchActivity.class, null);
					break;

				default:
					break;
				}

			}
		});
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getLoaderManager().initLoader(RequestDistribute.HOME_FRAGMENT, null,
				new HomeFragmentRequest(this));
	}

	// @Override
	// public void onStart() {
	// super.onStart();
	// }
	//
	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(mContext);
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(mContext);
	}

	//
	// @Override
	// public void onStop() {
	// super.onStop();
	// }
	//
	// @Override
	// public void onDestroyView() {
	// super.onDestroyView();
	// }
	//
	// @Override
	// public void onDestroy() {
	// super.onDestroy();
	// }
	//
	// @Override
	// public void onDetach() {
	// super.onDetach();
	// }

	@Override
	public void handleException(final int identity, final Exception e) {
		// super.handleException(identity, e);
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (identity == RequestDistribute.HOME_FRAGMENT) {
					// mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void updateUI(int identity, Object data) {
		if (data != null) {
			switch (identity) {
			case RequestDistribute.HOME_FRAGMENT:
				mCityTextView.setText(MApplication
						.getInstance()
						.getmConfig()
						.getPreferencesVal(Constants.CITY_NAME,
								Constants.CITYNAME_DEFAULT));
				HomeFragmentData homeFragmentData = (HomeFragmentData) data;
				mAdvertisings = homeFragmentData.getAdvertisingGallery()
						.getList();
				initEightPicture();
				List<HomeFragmentTemplateDataItem> list = homeFragmentData
						.getTemplateData().getList();
				mGridviewAdapter.addAll(list);
				mGridviewAdapter.notifyDataSetChanged();
				break;

			default:
				break;
			}
		} else {
			// showToast("数据对象空");
		}
	}

	@Override
	public void resetUI(int identity, Object data) {

	}

	private void initEightPicture() {
		if (mAdvertisings != null && mAdvertisings.size() > 0) {
			img_temp.setVisibility(View.GONE);
			galleryImgNum = mAdvertisings.size();
			mAdvertisingAdapter.clear();
			mAdvertisingAdapter.addAll(mAdvertisings);
			mAdvertisingAdapter.setImagelength(galleryImgNum);
			mAdvertisingAdapter.notifyDataSetChanged();
			mAdGallery.setSelection(100 * galleryImgNum);
			selectedPosition = 100 * galleryImgNum;
			mAdDotLayout.removeAllViews();
			dotHolder = new ImageView[galleryImgNum];
			for (int i = 0; i < galleryImgNum; i++) {
				dotHolder[i] = new ImageView(mContext);
				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				layoutParams.setMargins(5, 0, 0, 0);
				if (i == 0) {
					dotHolder[i]
							.setBackgroundResource(R.drawable.banner_round_select);
				} else {
					dotHolder[i]
							.setBackgroundResource(R.drawable.banner_round_normal);
				}
				mAdDotLayout.addView(dotHolder[i], layoutParams);
			}

			if (mRefreshAdvRun == null) {
				mRefreshAdvRun = new RefreshAdvRun();
			} else {
				mHandler.removeCallbacks(mRefreshAdvRun);
			}
			mHandler.postDelayed(mRefreshAdvRun, refreshTime);

		} else {
			img_temp.setVisibility(View.VISIBLE);
		}
	}

	private class RefreshAdvRun implements Runnable {

		@Override
		public void run() {
			mHandler.sendEmptyMessage(ADVREFRESH);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// case R.id.llayout_gowhere:
		// // goActivity(GoWhereActivity.class, null);
		// break;
		// case R.id.llayout_write_notes:
		// goActivity(MyTravelNotesListActivity.class, null);
		// break;
		// case R.id.llayout_active:
		// // goActivity(InitiateActivityActivity.class, null);
		// goActivity(TravelNotesDetailActivity.class, null);
		// break;
		// case R.id.llayout_near:
		// break;

		default:
			break;
		}
	}

}
