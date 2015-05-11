package com.mobile.meishang.ui.home;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.DiscoverListviewAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.DiscoverFragmentRequest;
import com.mobile.meishang.model.DiscoverList;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.mobile.meishang.utils.view.pulltorefresh.XListView;

public class TypeActivity extends MActivity implements
		XListView.IXListViewListener, OnClickListener, ExceptionHandler,
		LoadEvent {
	private LoadingView mLoadingView;
	private RelativeLayout mNoDataRLayout;
	private TextView tvNoData;
	private XListView mListView;
	private DiscoverListviewAdapter mListviewAdapter;
	private Bundle mBundle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBundle = getIntent().getBundleExtra("bundle");
		setContentView(R.layout.fragment_discover_list);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText(mBundle.getString("title"));
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		mNoDataRLayout = (RelativeLayout) findViewById(R.id.no_data);
		mNoDataRLayout.setBackgroundColor(getResources()
				.getColor(R.color.white));
		tvNoData = (TextView) findViewById(R.id.face_tv);
		tvNoData.setText("很抱歉，没有发现商品");
		mListView = (XListView) findViewById(R.id.mlistview);
		mListView.setPullRefreshEnable(true);
		mListView.setPullLoadEnable(false);
		mListView.setXListViewListener(this);
		mListView.setRefreshTime(getTime());
		mListviewAdapter = new DiscoverListviewAdapter(this);
		mListView.setAdapter(mListviewAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				Bundle bundle = new Bundle();
				bundle.putString("projectid",
						mListviewAdapter.getItem(--position).getProjectid());
				goActivity(DiscoverDetailActivity.class, bundle);
			}
		});
		getSupportLoaderManager().restartLoader(
				RequestDistribute.DISCOVER_FRAGMENT, null,
				new DiscoverFragmentRequest(this));
	}

	@Override
	public void handleException(int identity, Exception e) {

	}

	@Override
	public void retryAgain(View v) {

	}

	@Override
	public void updateUI(int identity, Object data) {
		mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.DISCOVER_FRAGMENT:
			mListviewAdapter.clear();
			DiscoverList discoverList = (DiscoverList) data;
			mListviewAdapter.addAll(discoverList.getList());
			mListviewAdapter.notifyDataSetChanged();
			break;
		case RequestDistribute.CATEGORY:
			break;

		default:
			break;
		}
	}

	@Override
	public void resetUI(int identity, Object data) {

	}

	@Override
	public void onRefresh() {
		onLoad();
	}

	@Override
	public void onLoadMore() {
		onLoad();
	}

	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime(getTime());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// case R.id.category_flayout:
		// // mCategoryFLayout.setVisibility(View.GONE);
		// break;

		default:
			break;
		}
	}

}
