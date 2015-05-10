package com.mobile.meishang.ui.home;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.SignRuleRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.SignRuleData;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

public class SignRuleActivity extends MActivity implements ExceptionHandler,
		LoadEvent {
	private LoadingView mLoadingView;
	private TextView tv_title;
	private TextView tv_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_rule);
		// findViewById(R.id.top_layout_back).setVisibility(View.VISIBLE);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("活动规则");
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_content = (TextView) findViewById(R.id.tv_content);
		getSupportLoaderManager().restartLoader(RequestDistribute.SIGN_RULE,
				null, new SignRuleRequest(this));
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
		case RequestDistribute.SIGN_RULE:
			SignRuleData signRuleData = (SignRuleData) data;
			tv_title.setText(signRuleData.getTitle());
			tv_content.setText(Html.fromHtml(signRuleData.getContent()));
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
				if (identity == RequestDistribute.SIGN_RULE) {
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

}
