package com.mobile.meishang.ui.user;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.MyWordsListviewAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.MySharedRequest;
import com.mobile.meishang.core.request.MyWordsRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;

public class MyWordsActivity extends MActivity implements ExceptionHandler,
		LoadEvent {
	private TextView tv_top_right;
	private LoadingView mLoadingView;
	private ListView listview;;
	private MyWordsListviewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_shared);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("留言");
		tv_top_right = (TextView) findViewById(R.id.tv_top_right);
		tv_top_right.setText("编辑");
		tv_top_right.setVisibility(View.VISIBLE);
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		mLoadingView.setVisibility(View.GONE);
		listview = (ListView) findViewById(R.id.listview);
		adapter = new MyWordsListviewAdapter(this);
		listview.setAdapter(adapter);
		net();

	}

	private void net() {
		getSupportLoaderManager().restartLoader(RequestDistribute.MY_WORDS,
				null, new MyWordsRequest(this));
	}

	@Override
	public void updateUI(int identity, Object data) {
		// super.updateUI(identity, data);
		mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.MY_WORDS:
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
				if (identity == RequestDistribute.MY_WORDS) {
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
		switch (v.getId()) {
		case R.id.tv_top_right:
			if ("关闭".equals(tv_top_right.getText().toString())) {
				tv_top_right.setText("编辑");
			} else {
				tv_top_right.setText("关闭");
			}
			break;

		default:
			break;
		}
	}
}
