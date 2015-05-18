package com.mobile.meishang.ui.infomation;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.R;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.FavoritesAddRequest;
import com.mobile.meishang.core.request.InfoDetailRequest;
import com.mobile.meishang.imagecache.ImageFetcher;
import com.mobile.meishang.imagecache.ImageWorker;
import com.mobile.meishang.model.Infomation;
import com.mobile.meishang.model.InfomationDetail;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Head;
import com.mobile.meishang.ui.login.LoginActivity;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

public class InfoDetailActivity extends MActivity implements ExceptionHandler,
		LoadEvent {
	private LoadingView mLoadingView;
	private TextView tv_title;
	private TextView tv_time;
	private TextView tv_count;
	private ImageView image;
	private TextView tv_content;
	private Bundle mBundle;
	private ImageWorker mImageWorker;
	Infomation infomation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_detail);
		mImageWorker = new ImageFetcher(this, MApplication.getInstance()
				.getLongest());
		mImageWorker.setImageCache(MApplication.getImageLruCache());
		mBundle = getIntent().getBundleExtra("bundle");
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("资讯");
		title.setVisibility(View.VISIBLE);
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_time = (TextView) findViewById(R.id.tv_time);
		tv_count = (TextView) findViewById(R.id.tv_count);
		image = (ImageView) findViewById(R.id.image);
		tv_content = (TextView) findViewById(R.id.tv_content);
		net();
	}

	private void net() {
		getSupportLoaderManager().restartLoader(RequestDistribute.INFO_DETAIL,
				mBundle, new InfoDetailRequest(this));
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
		mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.INFO_DETAIL:
			InfomationDetail infomationDetail = (InfomationDetail) data;
			infomation = infomationDetail.getInfomation();
			tv_title.setText(infomation.getTitle());
			tv_time.setText(infomation.getCreatetime());
			tv_count.setText(infomation.getCount());
			tv_content.setText(infomation.getContext());
			mImageWorker.setLoadingImage(R.drawable.loading_bg_img245);
			mImageWorker.loadImage(infomation.getPicpath(), image);
			break;
		case RequestDistribute.FAVORITES_ADD:
			Head head = (Head) data;
			// if (head.isSuccess()) {
			// }
			showToast(head.getMessage());
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
				if (identity == RequestDistribute.INFO_DETAIL) {
					mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void retryAgain(View v) {
		net();
	}

	@Override
	public void onclick(View v) {
		super.onclick(v);
		Bundle bundle = new Bundle();
		switch (v.getId()) {
		case R.id.img_favorites:
			if (MApplication.getInstance().checkLogin()) {
				bundle.putString("objectid", infomation.getInfoid());
				bundle.putString("type", "3");
				getSupportLoaderManager().restartLoader(
						RequestDistribute.FAVORITES_ADD, bundle,
						new FavoritesAddRequest(this));
			} else {
				goActivity(LoginActivity.class, null);
			}
			break;
		case R.id.flayout_evaluate:
			bundle.putString("infoid", infomation.getInfoid());
			goActivity(EvaluateListActivity.class, bundle);
			break;

		default:
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mImageWorker != null) {
			mImageWorker.getImageCache().clearCaches();
			mImageWorker.setImageCache(null);
			mImageWorker = null;
		}
	}
}
