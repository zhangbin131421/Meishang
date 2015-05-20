package com.mobile.meishang.ui.home;

import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.PictureGalleryAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.DiscoverDetailRequest;
import com.mobile.meishang.core.request.FavoritesAddRequest;
import com.mobile.meishang.core.request.SignGetIntegralRequest;
import com.mobile.meishang.model.Discover;
import com.mobile.meishang.model.DiscoverDetail;
import com.mobile.meishang.model.Picture;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Head;
import com.mobile.meishang.model.bean.Login;
import com.mobile.meishang.ui.login.LoginActivity;
import com.mobile.meishang.utils.view.AdGallery;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

public class SignDetailActivity extends MActivity implements ExceptionHandler,
		LoadEvent {
	private RefreshAdvRun mRefreshAdvRun;
	private AdGallery mAdGallery;
	private LinearLayout mAdDotLayout;
	private ImageView[] dotHolder;
	private PictureGalleryAdapter mAdvertisingAdapter;
	private List<Picture> mAdvertisings;
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
	private TextView name;
	private TextView tv_middlen;
	private TextView tv_count;
	private TextView tv_project_introduce;
	private TextView tv_project_goodness;

	private LoadingView mLoadingView;
	private Bundle mBundle;
	Discover discover;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_detail);
		mBundle = getIntent().getBundleExtra("bundle");
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		mLoadingView.setVisibility(View.GONE);
		mAdGallery = (AdGallery) findViewById(R.id.ad_gallery);
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
				// AdvertisingGalleryItem advertising = mAdvertisings
				// .get(realPosition);
				// Bundle bundle = new Bundle();
				// // bundle.putString("name", advertising.getName());
				// // bundle.putString("actid", advertising.getActid());
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

		mAdDotLayout = (LinearLayout) findViewById(R.id.ad_dot_llayout);
		name = (TextView) findViewById(R.id.item_name);
		tv_middlen = (TextView) findViewById(R.id.tv_middlen);
		tv_count = (TextView) findViewById(R.id.tv_count);
		tv_project_introduce = (TextView) findViewById(R.id.tv_project_introduce);
		tv_project_goodness = (TextView) findViewById(R.id.tv_project_goodness);

		mAdvertisingAdapter = new PictureGalleryAdapter(this);
		mAdGallery.setAdapter(mAdvertisingAdapter);
		getSupportLoaderManager().restartLoader(
				RequestDistribute.DISCOVER_DETAIL, mBundle,
				new DiscoverDetailRequest(this));
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		mLoadingView.setVisibility(View.VISIBLE);
		getSupportLoaderManager().restartLoader(
				RequestDistribute.DISCOVER_DETAIL, mBundle,
				new DiscoverDetailRequest(this));
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
	public void updateUI(int identity, Object data) {
		// super.updateUI(identity, data);
		mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.DISCOVER_DETAIL:
			DiscoverDetail discoverDetail = (DiscoverDetail) data;
			discover = discoverDetail.getDiscover();
			name.setText(discover.getTitle());
			tv_middlen.setText(discover.getMiddlen());
			tv_count.setText(discover.getCount());
			tv_project_introduce.setText(discover.getIntroduction());
			tv_project_goodness.setText(discover.getGoodness());
			mAdvertisings = discoverDetail.getPictures();
			initEightPicture();
			break;
		case RequestDistribute.FAVORITES_ADD:
			Head head = (Head) data;
			// if (head.isSuccess()) {
			// }
			showToast(head.getMessage());
			break;
		case RequestDistribute.SIGN_GET_INTEGRAL:
			Login login = (Login) data;
			if (login.isSuccess()) {
				MApplication.getInstance().setLogin(login.getUser());
			}
			showToast(login.getMessage());
			break;
		default:
			break;
		}

	}

	public void onclick(View v) {
		super.onclick(v);
		Bundle bundle = new Bundle();
		switch (v.getId()) {
		case R.id.flayout_voice_introduce:
			showToast("语音介绍");
			break;
		case R.id.flayout_call:
			Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
					+ discover.getPhonenum()));
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			break;
		case R.id.flayout_leave_words:
			showToast("留言给她");
			break;
		case R.id.tv_share:
			showToast("分享");
			break;
		case R.id.tv_favorites:

			if (MApplication.getInstance().checkLogin()) {
				bundle.putString("objectid", "1");
				bundle.putString("type", "1");
				getSupportLoaderManager().restartLoader(
						RequestDistribute.FAVORITES_ADD, bundle,
						new FavoritesAddRequest(this));
			} else {
				goActivity(LoginActivity.class, null);
			}
			break;
		case R.id.tv_get_integral:
			bundle.putString("type", "1");
			bundle.putString("projectid", discover.getProjectid());
			bundle.putString("integral", discover.getIntegral());
			getSupportLoaderManager().restartLoader(
					RequestDistribute.SIGN_GET_INTEGRAL, bundle,
					new SignGetIntegralRequest(this));
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
				if (identity == RequestDistribute.DISCOVER_DETAIL) {
					mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void retryAgain(View v) {
		// getSupportLoaderManager().restartLoader(RequestDistribute.GOODS_LIST,
		// mBundle, new GoodsListRequest(this));
	}

	private void initEightPicture() {
		if (mAdvertisings != null && mAdvertisings.size() > 0) {
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

		}
	}

	private class RefreshAdvRun implements Runnable {

		@Override
		public void run() {
			mHandler.sendEmptyMessage(ADVREFRESH);
		}
	}
}
