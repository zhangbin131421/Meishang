package com.mobile.meishang.ui.bid;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.MyBidPublishListviewAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.MyBidPublishListRequest;
import com.mobile.meishang.model.BidMyPublishList;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Goods;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.mobile.meishang.utils.view.pulltorefresh.XListView;
import com.umeng.analytics.MobclickAgent;

public class MyBidPublishListActivity extends MActivity implements
		XListView.IXListViewListener, ExceptionHandler, LoadEvent {
	// private Context mContext;
	private LoadingView mLoadingView;
	private RelativeLayout mNoDataRLayout;
	private TextView tvNoData;
	private XListView mListView;
	private MyBidPublishListviewAdapter mListviewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_bid_publish_list);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("我的竞标");
		title.setVisibility(View.VISIBLE);
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		mLoadingView.setVisibility(View.GONE);
		mNoDataRLayout = (RelativeLayout) findViewById(R.id.no_data);
		tvNoData = (TextView) findViewById(R.id.face_tv);
		tvNoData.setText("很抱歉，没有发现");
		mListView = (XListView) findViewById(R.id.mlistview);
		mListView.setPullRefreshEnable(true);
		mListView.setPullLoadEnable(false);
		mListView.setXListViewListener(this);
		mListView.setRefreshTime(getTime());
		mListviewAdapter = new MyBidPublishListviewAdapter(this);
		mListView.setAdapter(mListviewAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				Bundle bundle = new Bundle();
				// bundle.putString("id", mGoodsListing.get(--position)
				// .getGoodsid());
				goActivity(BidDetailActivity.class, bundle);
			}
		});
		getSupportLoaderManager().restartLoader(
				RequestDistribute.MY_BID_PUBLISH_LIST, null,
				new MyBidPublishListRequest(this));
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
		case RequestDistribute.MY_BID_PUBLISH_LIST:
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
				if (identity == RequestDistribute.MY_BID_PUBLISH_LIST) {
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
