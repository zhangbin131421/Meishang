package com.mobile.meishang.ui.home;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.BusinessCardListviewAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.BusinessCardRequest;
import com.mobile.meishang.model.DiscoverList;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.BusinessCardItem;
import com.mobile.meishang.model.bean.BusinessCardListing;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.mobile.meishang.utils.view.pulltorefresh.XListView;

public class BusinessCardListActivity extends MActivity implements
		XListView.IXListViewListener, ExceptionHandler, LoadEvent {
	private LoadingView mLoadingView;
	private XListView listview;;
	private BusinessCardListviewAdapter mAdapter;
	private List<BusinessCardItem> mDataItems;
	private Bundle mBundle;
	private int currentPage = 1;
	private int totalPage = 1;
	private boolean isPullRequest = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_business_card_list);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("美商云讯");
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		listview = (XListView) findViewById(R.id.listview);
		listview.setPullRefreshEnable(true);
		listview.setPullLoadEnable(false);
		listview.setXListViewListener(this);
		listview.setRefreshTime(getTime());
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
			}
		});
		mAdapter = new BusinessCardListviewAdapter(this);
		listview.setAdapter(mAdapter);
		mDataItems = new ArrayList<BusinessCardItem>();
		mAdapter.clear();
		mAdapter.addAll(mDataItems);
		mAdapter.notifyDataSetChanged();
		mBundle = new Bundle();
		mBundle.putInt("pageNumber", currentPage);
		net();

	}

	private void net() {
		getSupportLoaderManager().restartLoader(
				RequestDistribute.BUSINESS_CARD_LIST, mBundle,
				new BusinessCardRequest(this));
	}

	@Override
	public void onclick(View v) {
		switch (v.getId()) {
		case R.id.top_layout_back:
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	public void updateUI(int identity, Object data) {
		mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.BUSINESS_CARD_LIST:
			mAdapter.clear();
			BusinessCardListing discoverList = (BusinessCardListing) data;
			mAdapter.addAll(discoverList.getList());
			mAdapter.notifyDataSetChanged();
			break;
		case RequestDistribute.CATEGORY:
			break;

		default:
			break;
		}
	}

	@Override
	public void retryAgain(View v) {
		net();
	}

	@Override
	public void onRefresh() {
		onLoad(true);
	}

	@Override
	public void onLoadMore() {
		onLoad(false);
	}

	private void onLoad(boolean temp) {
		listview.setRefreshTime(getTime());
		isPullRequest = temp;
		if (temp) {
			currentPage = 1;
			mBundle.putInt("pageNumber", currentPage);
		} else {
			mBundle.putInt("pageNumber", ++currentPage);
		}
		net();

	}
}
