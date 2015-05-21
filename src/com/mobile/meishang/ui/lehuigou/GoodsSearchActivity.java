package com.mobile.meishang.ui.lehuigou;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.FilterListAdapter;
import com.mobile.meishang.adapter.GoodsSearchListviewAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.LehuigoSearchRequest;
import com.mobile.meishang.model.LehuigoHomeData;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.CategoryFilter;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

public class GoodsSearchActivity extends MActivity implements ExceptionHandler,
		LoadEvent {
	// private Context mContext;
	private TextView tv_category_left;
	private TextView tv_category_right;
	// private LoadingView mLoadingView;
	// private RelativeLayout mNoDataRLayout;
	// private TextView tvNoData;
	private ListView listview;
	private LinearLayout llayout_listview;
	private ListView listview_left;
	private ListView listview_right;
	private GoodsSearchListviewAdapter mListviewAdapter;
	private Bundle mBundle;
	private FilterListAdapter filterLeftAdapter;
	private FilterListAdapter filterRightAdapter;
	private List<CategoryFilter> filterLeft;
	private List<CategoryFilter> filterRight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_list);
		mBundle = getIntent().getBundleExtra("bundle");
		findViewById(R.id.top_layout_back).setVisibility(View.VISIBLE);
		// LinearLayout layout = (LinearLayout)
		// findViewById(R.id.title_choice_llayout);
		// layout.setVisibility(View.VISIBLE);
		tv_category_left = (TextView) findViewById(R.id.tv_category_left);
		tv_category_right = (TextView) findViewById(R.id.tv_category_right);
		// mLoadingView = (LoadingView) findViewById(R.id.loading);
		// mLoadingView.setLoadEvent(this);
		// mNoDataRLayout = (RelativeLayout) findViewById(R.id.no_data);
		// tvNoData = (TextView) findViewById(R.id.face_tv);
		// tvNoData.setText("很抱歉，没有发现商品");
		llayout_listview = (LinearLayout) findViewById(R.id.llayout_listview);
		listview = (ListView) findViewById(R.id.listview);
		listview_left = (ListView) findViewById(R.id.listview_left);
		listview_left.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				tv_category_left.setText(filterLeft.get(position).getName());
				llayout_listview.setVisibility(View.GONE);
				switch (position) {
				case 0:
					mBundle.putString("integral1", "0");
					mBundle.putString("integral2", "999");
					break;
				case 1:
					mBundle.putString("integral1", "1000");
					mBundle.putString("integral2", "1999");
					break;
				case 2:
					mBundle.putString("integral1", "2000");
					mBundle.putString("integral2", "2999");
					break;
				case 3:
					mBundle.putString("integral1", "3000");
					mBundle.putString("integral2", "3999");
					break;
				case 4:
					mBundle.putString("integral1", "4000");
					mBundle.putString("integral2", "4999");
					break;

				default:
					break;
				}
				net();
			}
		});
		// Title:标题 integral1：积分1 integral2：积分2 moduleid：大模块编号 smoduleid：小
		listview_right = (ListView) findViewById(R.id.listview_right);
		listview_right.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				tv_category_right.setText(filterRight.get(position).getName());
				llayout_listview.setVisibility(View.GONE);
				mBundle.putString("moduleid", filterRight.get(position).getId());
				mBundle.putString("smoduleid", "");
				net();
			}
		});
		mListviewAdapter = new GoodsSearchListviewAdapter(this);
		listview.setAdapter(mListviewAdapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// Bundle bundle = new Bundle();
				// bundle.putString("id",
				// mGoodsListing.get(--position).getId());
				// goActivity(GoodsDetailActivity.class, bundle);
				goActivity(IntegralGoodsDetailActivity.class, null);
			}
		});
		// mBundle.putString("integral1", "0");
		// mBundle.putString("integral2", "999");
		// mBundle.putString("moduleid", "3");
		// mBundle.putString("smoduleid", "");
		net();
	}

	private void net() {
		getSupportLoaderManager().restartLoader(
				RequestDistribute.LEHUIGOU_SEARCH, mBundle,
				new LehuigoSearchRequest(this));
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
		// mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.LEHUIGOU_SEARCH:
			LehuigoHomeData lehuigoHomeData = (LehuigoHomeData) data;
			mListviewAdapter.clear();
			mListviewAdapter.addAll(lehuigoHomeData.getmList());
			mListviewAdapter.notifyDataSetChanged();
			break;

		default:
			break;
		}

	}

	public void onclick(View v) {
		super.onclick(v);
		switch (v.getId()) {
		case R.id.flayout_category_left:
			llayout_listview.setVisibility(View.VISIBLE);
			if (filterLeftAdapter == null) {
				filterLeftAdapter = new FilterListAdapter(this);
				listview_left.setAdapter(filterLeftAdapter);
				filterLeftAdapter.addAll(getFiterDataLeft());
				filterLeftAdapter.notifyDataSetChanged();
			}
			break;
		case R.id.flayout_category_right:
			llayout_listview.setVisibility(View.VISIBLE);
			if (filterRightAdapter == null) {
				filterRightAdapter = new FilterListAdapter(this);
				listview_right.setAdapter(filterRightAdapter);
				filterRightAdapter.addAll(getFiterDataRight());
				filterRightAdapter.notifyDataSetChanged();
			}
			break;

		default:
			break;
		}
		;
	}

	@Override
	public void handleException(final int identity, final Exception e) {
		super.handleException(identity, e);
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (identity == RequestDistribute.LEHUIGOU_SEARCH) {
					// mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void retryAgain(View v) {
		net();
	}

	private List<CategoryFilter> getFiterDataLeft() {
		if (filterLeft == null) {
			filterLeft = new ArrayList<CategoryFilter>();
			filterLeft.add(new CategoryFilter("0-999", ""));
			filterLeft.add(new CategoryFilter("1000-1999", ""));
			filterLeft.add(new CategoryFilter("2000-2999", ""));
			filterLeft.add(new CategoryFilter("3000-3999", ""));
			filterLeft.add(new CategoryFilter("4000-4999", ""));
		}
		return filterLeft;

	}

	private List<CategoryFilter> getFiterDataRight() {
		if (filterRight == null) {
			filterRight = new ArrayList<CategoryFilter>();
			filterRight.add(new CategoryFilter("美容", "3"));
			filterRight.add(new CategoryFilter("内页", "4"));
			filterRight.add(new CategoryFilter("车饰", "5"));
			filterRight.add(new CategoryFilter("灯饰", "6"));
			filterRight.add(new CategoryFilter("日化", "7"));

		}
		return filterRight;
	}
}
