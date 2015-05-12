package com.mobile.meishang.ui.home.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobile.meishang.MFragment;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.DiscoverListviewAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.DiscoverFragmentRequest;
import com.mobile.meishang.model.DiscoverList;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.ui.home.DiscoverDetailActivity;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.mobile.meishang.utils.view.pulltorefresh.XListView;
import com.umeng.analytics.MobclickAgent;

public class DiscoverFragment extends MFragment implements
		XListView.IXListViewListener, OnClickListener, ExceptionHandler,
		LoadEvent {
	private LoadingView mLoadingView;
	private RelativeLayout mNoDataRLayout;
	private TextView tvNoData;
	private XListView mListView;
	private DiscoverListviewAdapter mListviewAdapter;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_discover_list, null);
		view.findViewById(R.id.top_layout_back).setVisibility(View.GONE);
		TextView title = (TextView) view.findViewById(R.id.top_name);
		title.setText("发现");
		mLoadingView = (LoadingView) view.findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		mNoDataRLayout = (RelativeLayout) view.findViewById(R.id.no_data);
		mNoDataRLayout.setBackgroundColor(getResources()
				.getColor(R.color.white));
		tvNoData = (TextView) view.findViewById(R.id.face_tv);
		tvNoData.setText("很抱歉，没有发现商品");
		mListView = (XListView) view.findViewById(R.id.mlistview);
		mListView.setPullRefreshEnable(true);
		mListView.setPullLoadEnable(false);
		mListView.setXListViewListener(this);
		mListView.setRefreshTime(getTime());
		mListviewAdapter = new DiscoverListviewAdapter(mContext);
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
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getLoaderManager().restartLoader(RequestDistribute.DISCOVER_FRAGMENT,
				null, new DiscoverFragmentRequest(this));
	}

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
