package com.mobile.meishang.ui.home;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.BusinessCardListviewAdapter;
import com.mobile.meishang.adapter.CategoryLeftListAdapter;
import com.mobile.meishang.adapter.CategoryRightListAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.BusinessCardExchangeRequest;
import com.mobile.meishang.core.request.BusinessCardRequest;
import com.mobile.meishang.core.request.CategoryRequest;
import com.mobile.meishang.core.request.ProvinceRequest;
import com.mobile.meishang.model.BusinessCard;
import com.mobile.meishang.model.BusinessCardList;
import com.mobile.meishang.model.Module;
import com.mobile.meishang.model.ModuleList;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Head;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.mobile.meishang.utils.view.pulltorefresh.XListView;

public class BusinessCardListActivity extends MActivity implements
		XListView.IXListViewListener, ExceptionHandler, LoadEvent {
	private LoadingView mLoadingView;
	private XListView listview;;
	private BusinessCardListviewAdapter mAdapter;
	private Bundle mBundle;
	private int currentPage = 1;
	private int totalPage = 1;
	private boolean isPullRequest = false;
	private TextView tv_category_left;
	private ListView listview_left;
	private ListView listview_right;
	private CategoryLeftListAdapter filterLeftAdapter;
	private CategoryRightListAdapter filterRightAdapter;
	private List<Module> moduleList;
	private String moduleid;
	private String smoduleid;
	private LinearLayout llayout;
	List<BusinessCard> mList;
	int mPositon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_business_card_list);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("美商云讯");
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		tv_category_left = (TextView) findViewById(R.id.tv_category_left);
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
		llayout = (LinearLayout) findViewById(R.id.llayout);
		listview_left = (ListView) findViewById(R.id.listview_left);
		listview_left.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				moduleid = filterLeftAdapter.getItem(arg2).getModuleid();
				filterLeftAdapter.setmPosition(arg2);
				filterLeftAdapter.notifyDataSetChanged();
				filterRightAdapter.clear();
				filterRightAdapter.addAll(moduleList.get(arg2).getList());
				filterRightAdapter.notifyDataSetChanged();
			}
		});
		listview_right = (ListView) findViewById(R.id.listview_right);
		listview_right.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				smoduleid = filterRightAdapter.getItem(position).getSmoduleid();
				tv_category_left.setText(filterRightAdapter.getItem(position)
						.getName());
				filterRightAdapter.setmPosition(position);
				llayout.setVisibility(View.GONE);

			}
		});
		getSupportLoaderManager().initLoader(RequestDistribute.CATEGORY, null,
				new CategoryRequest(this));
		getSupportLoaderManager().initLoader(RequestDistribute.PROVINCE, null,
				new ProvinceRequest(this));
		mBundle = new Bundle();
		mBundle.putString("provinceId", "3");
		mBundle.putString("moduleId", "3");
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
		case R.id.flayout_category_left:
			if (moduleList != null) {
				llayout.setVisibility(View.VISIBLE);
				if (filterLeftAdapter == null) {
					filterLeftAdapter = new CategoryLeftListAdapter(this);
					listview_left.setAdapter(filterLeftAdapter);
					filterLeftAdapter.addAll(moduleList);
					filterLeftAdapter.notifyDataSetChanged();
				}
				if (filterRightAdapter == null) {
					filterRightAdapter = new CategoryRightListAdapter(this);
					listview_right.setAdapter(filterRightAdapter);
					filterRightAdapter.addAll(moduleList.get(0).getList());
					filterRightAdapter.notifyDataSetChanged();
				}
			}
		case R.id.flayout_category_right:
			break;
		default:
			break;
		}
	}

	@Override
	public void updateUI(int identity, Object data) {
		mLoadingView.setVisibility(View.GONE);
		listview.stopRefresh();
		listview.stopLoadMore();
		switch (identity) {
		case RequestDistribute.BUSINESS_CARD_LIST:
			BusinessCardList businessCardList = (BusinessCardList) data;
			totalPage = businessCardList.getTotal();
			if (currentPage < totalPage) {
				listview.setPullLoadEnable(true);
			} else {
				// mListView.setPullRefreshEnable(false);
				listview.setPullLoadEnable(false);
			}
			if (isPullRequest) {
				mAdapter.clear();
			}
			mList = businessCardList.getmList();
			mAdapter.addAll(mList);
			mAdapter.notifyDataSetChanged();
			break;
		case RequestDistribute.CATEGORY:
			ModuleList modules = (ModuleList) data;
			moduleList = modules.getModuleList();
			break;
		case RequestDistribute.BUSINESS_CARD_EXCHANGE:
			Head h = (Head) data;
			if (h.isSuccess()) {
				goCardInfo();
			} else {
				showToast(h.getMessage());
			}
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

	public void goCardInfo() {
		Bundle bundle = new Bundle();
		bundle.putParcelable("BusinessCard", mList.get(mPositon));
		bundle.putString("userId", mList.get(mPositon).getUserId());
		goActivity(BusinessCardInfoActivity.class, bundle);
	}

	public void goExChangeCard(int position) {
		mPositon = position;
		String businesscardId = mList.get(position).getId();
		mBundle.putString("businesscardId", businesscardId);
		getSupportLoaderManager().restartLoader(
				RequestDistribute.BUSINESS_CARD_EXCHANGE, mBundle,
				new BusinessCardExchangeRequest(this));
	}

	public void goCardAdd() {
		goActivity(BusinessCardNoActivity.class, null);
	}
}
