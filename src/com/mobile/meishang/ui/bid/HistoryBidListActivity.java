package com.mobile.meishang.ui.bid;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.BidHistoryListviewAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.HistoryBidRequest;
import com.mobile.meishang.model.BidMyPublishList;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.mobile.meishang.utils.view.pulltorefresh.XListView;
import com.umeng.analytics.MobclickAgent;

public class HistoryBidListActivity extends MActivity implements
		XListView.IXListViewListener, ExceptionHandler, LoadEvent {
	// private Context mContext;
	private LoadingView mLoadingView;
	private RelativeLayout mNoDataRLayout;
	private TextView tvNoData;
	private XListView mListView;
	private BidHistoryListviewAdapter mListviewAdapter;
	private Bundle mBundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bid_notice_list);
		mBundle = getIntent().getBundleExtra("bundle");
		// mContext = this;
		// findViewById(R.id.top_layout_back).setVisibility(View.VISIBLE);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("历史竞标");
		title.setVisibility(View.VISIBLE);
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		
		mNoDataRLayout = (RelativeLayout) findViewById(R.id.no_data);
		tvNoData = (TextView) findViewById(R.id.face_tv);
		tvNoData.setText("很抱歉，没有发现商品");
		mListView = (XListView) findViewById(R.id.mlistview);
		mListView.setPullRefreshEnable(true);
		mListView.setPullLoadEnable(false);
		mListView.setXListViewListener(this);
		mListView.setRefreshTime(getTime());
		mListviewAdapter = new BidHistoryListviewAdapter(this);
		mListView.setAdapter(mListviewAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				Bundle bundle = new Bundle();
				 bundle.putString("biddingid", mListviewAdapter.getItem(--position)
				 .getBidding().getBiddingid());
				goActivity(BidDetailActivity.class, bundle);
			}
		});

		getSupportLoaderManager().restartLoader(RequestDistribute.HISTORY_BID,
				mBundle, new HistoryBidRequest(this));
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
		case RequestDistribute.HISTORY_BID:
			BidMyPublishList bidMyPublishList = (BidMyPublishList) data;
			mListviewAdapter.clear();
			mListviewAdapter.addAll(bidMyPublishList.getList());
			mListviewAdapter.notifyDataSetChanged();
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
				if (identity == RequestDistribute.GOODS_LIST) {
					mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void retryAgain(View v) {

	}

}
