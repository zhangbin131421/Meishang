package com.mobile.meishang.ui.lehuigou;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.LehuigouHomeExpandAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.LehuigoHomeRequest;
import com.mobile.meishang.model.LehuigoHomeData;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.mobile.meishang.utils.view.pulltorefresh.MExpandableListView;
import com.mobile.meishang.utils.view.pulltorefresh.MExpandableListView.MOnRefreshListener;

public class LehuigoHomeActvity extends MActivity implements
		MOnRefreshListener, OnClickListener, ExceptionHandler, LoadEvent {
	private LoadingView mLoadingView;
	private MExpandableListView mExpandableListView;
	private LehuigouHomeExpandAdapter mExpandAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lehuigou_home);
		EditText etv_search = (EditText) findViewById(R.id.etv_search);
		etv_search.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH
						|| (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
					goActivity(GoodsSearchActivity.class, null);
					// InputMethodManager imm =
					// (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
					// 隐藏软键盘
					// imm.hideSoftInputFromWindow(editView.getWindowToken(),
					// 0);
					// 显示软键盘
					// imm.showSoftInputFromInputMethod(editView.getWindowToken(),
					// 0);
					// 切换软键盘的显示与隐藏
					// imm.toggleSoftInputFromWindow(editView.getWindowToken(),
					// 0, InputMethodManager.HIDE_NOT_ALWAYS);
					return true;
				}
				return false;
			}
		});
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		mExpandableListView = (MExpandableListView) findViewById(R.id.expandabel_listview);
		mExpandableListView.setPullRefreshEnable(true);
		mExpandableListView.setPullLoadEnable(false);
		mExpandableListView.setXListViewListener(this);
		mExpandableListView.setRefreshTime(getTime());
		View headView = LayoutInflater.from(this).inflate(
				R.layout.layout_lehuigou_exh, mExpandableListView, false);
		mExpandAdapter = new LehuigouHomeExpandAdapter(this);
		mExpandableListView.setGroupIndicator(null);
		mExpandableListView.setAdapter(mExpandAdapter);
		mExpandableListView.addHeaderView(headView);
		// mExpandableListView
		// .setOnGroupExpandListener(new OnGroupExpandListener() {
		//
		// @Override
		// public void onGroupExpand(int groupPosition) {
		// for (int i = 0; i < 2; i++) {
		// if (groupPosition != i) {
		// mExpandableListView.collapseGroup(i);
		// }
		//
		// }
		// }
		//
		// });
		mExpandableListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// showToast("groupPosition=" + groupPosition);
				return true;
			}
		});
		mExpandableListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// showToast("groupPosition=" + groupPosition + "childPosition="
				// + childPosition);
				Bundle bundle = new Bundle();
				bundle.putString("purchasedid", mExpandAdapter.getmGroups()
						.get(groupPosition).getmList().get(childPosition)
						.getPurchasedid());
				goActivity(IntegralGoodsDetailActivity.class, bundle);
				return true;
			}
		});
		Bundle mBundle = new Bundle();
		getSupportLoaderManager().restartLoader(
				RequestDistribute.LEHUIGOU_HOME, mBundle,
				new LehuigoHomeRequest(this));
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
		case RequestDistribute.LEHUIGOU_HOME:
			List<LehuigoHomeData> listGroups = (List<LehuigoHomeData>) data;
			mExpandAdapter.clear();
			mExpandAdapter.addAll(listGroups);
			mExpandAdapter.notifyDataSetChanged();
			for (int i = 0; i < 2; i++) {
				mExpandableListView.expandGroup(i);
			}
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
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				mExpandableListView.stopRefresh();
				mExpandableListView.stopLoadMore();
			}
		}, 5000);
	}

	@Override
	public void onLoadMore() {
		// onLoad();
	}

	private void stopRefresh() {

		mExpandableListView.stopRefresh();
		mExpandableListView.stopLoadMore();
		mExpandableListView.setRefreshTime(getTime());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.category_flayout:
			break;
		case R.id.category_a_flayout:
			break;
		default:
			break;
		}
	}

}
