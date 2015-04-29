package com.mobile.meishang.ui.home;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.AdvertisingGalleryAdapter;
import com.mobile.meishang.adapter.DiscoverListviewAdapter;
import com.mobile.meishang.adapter.HomeGridviewAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.GoodsListRequest;
import com.mobile.meishang.core.request.InsideActivityRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.AdvertisingGalleryItem;
import com.mobile.meishang.model.bean.InsideActivityData;
import com.mobile.meishang.model.bean.HomeFragmentTemplateDataItem;
import com.mobile.meishang.ui.ad.AdvertisingListActivity;
import com.mobile.meishang.utils.view.AdGallery;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.mobile.meishang.utils.view.pulltorefresh.XListView;
import com.umeng.analytics.MobclickAgent;

public class InsideActivity extends MActivity implements
		XListView.IXListViewListener, ExceptionHandler, LoadEvent {
	private RefreshAdvRun mRefreshAdvRun;
	private ImageView img_temp;
	private AdGallery mAdGallery;
	private LinearLayout mAdDotLayout;
	private ImageView[] dotHolder;
	private AdvertisingGalleryAdapter mAdvertisingAdapter;
	private List<AdvertisingGalleryItem> mAdvertisings;
	private final int ADVREFRESH = 1;
	private int selectedPosition = 0;
	private int realPosition = 0;
	private int galleryImgNum;
	private int refreshTime = 2000;
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
	private GridView mGridView;
	private HomeGridviewAdapter mGridviewAdapter;
	private LoadingView mLoadingView;
	private RelativeLayout mNoDataRLayout;
	private TextView tvNoData;
	private XListView mListView;
	private DiscoverListviewAdapter mListviewAdapter;

	private Bundle mBundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inside);
		View headView = LayoutInflater.from(this).inflate(
				R.layout.layout_inside_hview, null);
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
				goActivity(AdvertisingListActivity.class, bundle);
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
		mGridView = (GridView) headView.findViewById(R.id.gridview);
		mAdvertisingAdapter = new AdvertisingGalleryAdapter(this);
		mAdGallery.setAdapter(mAdvertisingAdapter);
		mGridviewAdapter = new HomeGridviewAdapter(this);
		mGridView.setAdapter(mGridviewAdapter);

		mBundle = getIntent().getBundleExtra("bundle");
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("美商云讯");
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		mLoadingView.setVisibility(View.GONE);
		mNoDataRLayout = (RelativeLayout) findViewById(R.id.no_data);
		tvNoData = (TextView) findViewById(R.id.face_tv);
		tvNoData.setText("很抱歉，没有发现商品");
		mListView = (XListView) findViewById(R.id.mlistview);
		mListView.setPullRefreshEnable(true);
		mListView.setPullLoadEnable(false);
		mListView.setXListViewListener(this);
		mListView.setRefreshTime(getTime());
		mListView.addHeaderView(headView);
		mListviewAdapter = new DiscoverListviewAdapter(this);
		mListView.setAdapter(mListviewAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// Bundle bundle = new Bundle();
				// bundle.putString("id", mGoodsListing.get(--position)
				// .getGoodsid());
				// goActivity(GoodsDetailActivity.class, bundle);
			}
		});
		getSupportLoaderManager().initLoader(RequestDistribute.INSIDE_ACTIVITY,
				mBundle, new InsideActivityRequest(this));
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	public void onRefresh() {
		onLoad();
	}

	@Override
	public void onLoadMore() {
		// onLoad();
	}

	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime(getTime());
	}

	@Override
	public void updateUI(int identity, Object data) {
		// super.updateUI(identity, data);
		mNoDataRLayout.setVisibility(View.GONE);
		mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.INSIDE_ACTIVITY:
			InsideActivityData insideActivityData = (InsideActivityData) data;
			mAdvertisings = insideActivityData.getAdvertisingGallery().getList();
			initEightPicture();
//			mGridviewAdapter.addAll(list);
//			mGridviewAdapter.notifyDataSetChanged();
			break;

		default:
			break;
		}

	}

	@Override
	public void handleException(final int identity, final Exception e) {
		super.handleException(identity, e);
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (identity == RequestDistribute.INSIDE_ACTIVITY) {
					mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void retryAgain(View v) {
		getSupportLoaderManager().restartLoader(
				RequestDistribute.INSIDE_ACTIVITY, null,
				new GoodsListRequest(this));

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
				dotHolder[i] = new ImageView(this);
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
}
