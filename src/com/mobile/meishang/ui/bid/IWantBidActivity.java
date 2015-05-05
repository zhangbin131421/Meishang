package com.mobile.meishang.ui.bid;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.FilterListAdapter;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.CategoryFilter;
import com.umeng.analytics.MobclickAgent;

public class IWantBidActivity extends MActivity {

	private LinearLayout llayout;
	private ListView listview_left;
	private ListView listview_right;
	private FilterListAdapter filterLeftAdapter;
	private FilterListAdapter filterRightAdapter;
	private List<CategoryFilter> filterLeft;
	private List<CategoryFilter> filterRight;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_i_want_bid);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("我要竞标");
		TextView tv_top_right = (TextView) findViewById(R.id.tv_top_right);
		tv_top_right.setText("历史竞标");
		tv_top_right.setVisibility(View.VISIBLE);
		llayout = (LinearLayout) findViewById(R.id.llayout);
		listview_left = (ListView) findViewById(R.id.listview_left);
		listview_right = (ListView) findViewById(R.id.listview_right);
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
	public void handleException(final int identity, final Exception e) {
		// super.handleException(identity, e);
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (identity == RequestDistribute.COUPON_GET) {
					// mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void updateUI(int identity, Object data) {
		if (data != null) {
			switch (identity) {
			case RequestDistribute.ADVERTISING_GALLERY_ONE_DAY:
				break;

			default:
				break;
			}
		} else {
			// showToast("数据对象空");
		}
	}

	@Override
	public void resetUI(int identity, Object data) {

	}

	@Override
	public void onclick(View v) {
		super.onclick(v);
		switch (v.getId()) {
		case R.id.tv_top_right:
			goActivity(HistoryBidListActivity.class, null);
			break;
		case R.id.tv_choose_category:
			llayout.setVisibility(View.VISIBLE);
			if (filterLeftAdapter == null) {
				filterLeftAdapter = new FilterListAdapter(this);
				listview_left.setAdapter(filterLeftAdapter);
				filterLeftAdapter.addAll(getFiterDataLeft());
				filterLeftAdapter.notifyDataSetChanged();
			}
			break;
		case R.id.llayout:
			llayout.setVisibility(View.GONE);
			break;
		case R.id.btn_publish:
			break;
		// case R.id.llayout_active:
		// // goActivity(InitiateActivityActivity.class, null);
		// goActivity(TravelNotesDetailActivity.class, null);
		// break;
		// case R.id.llayout_near:
		// break;

		default:
			break;
		}
	}

	private List<CategoryFilter> getFiterDataLeft() {
		if (filterLeft == null) {
			filterLeft = new ArrayList<CategoryFilter>();
			filterLeft.add(new CategoryFilter("美容", ""));
			filterLeft.add(new CategoryFilter("日化", ""));
			filterLeft.add(new CategoryFilter("内衣", ""));
			filterLeft.add(new CategoryFilter("车世界", ""));
			filterLeft.add(new CategoryFilter("灯饰", ""));
			filterLeft.add(new CategoryFilter("会讯", ""));
		}
		return filterLeft;

	}

}
