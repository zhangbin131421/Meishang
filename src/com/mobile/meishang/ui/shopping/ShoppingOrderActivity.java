package com.mobile.meishang.ui.shopping;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.ShoppingOrderListAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.GoodsDetailRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Goods;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

public class ShoppingOrderActivity extends MActivity implements
		ExceptionHandler, LoadEvent {
	private LoadingView mLoadingView;
	private LinearLayout mLinearLayout;
	private ListView mListView;
	private ShoppingOrderListAdapter mListAdapter;
	private List<Goods> list;
	private List<Integer> positions;
	private LinearLayout llayout_clearing;
	private FrameLayout flayout_delete;
	TextView item_tv_name;
	TextView item_tv_tel;
	TextView item_tv_address;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping_order);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("确认订单");
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		mLoadingView.setVisibility(View.GONE);
		View hView = LayoutInflater.from(this).inflate(
				R.layout.item_shopping_order_lh, null);
		item_tv_name = (TextView) hView.findViewById(R.id.item_tv_name);
		item_tv_tel = (TextView) hView.findViewById(R.id.item_tv_tel);
		item_tv_address = (TextView) hView.findViewById(R.id.item_tv_address);
		View fView = LayoutInflater.from(this).inflate(
				R.layout.item_shopping_order_lf, null);

		mListView = (ListView) findViewById(R.id.listview);
		mListAdapter = new ShoppingOrderListAdapter(this);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// if (!mListAdapter.getIsEdite()) {
				// Bundle bundle = new Bundle();
				// bundle.putString("id", list.get(position).getGoodsid());
				// goActivity(GoodsDetailActivity.class, bundle);
				// }

			}
		});
		mListView.addHeaderView(hView);
		mListView.addFooterView(fView);
		mListView.setAdapter(mListAdapter);
		// getSupportLoaderManager().restartLoader(
		// RequestDistribute.FAVORITES_LIST, null,
		// new FavoritesListRequest(this));

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
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
		if (arg1 == RESULT_OK) {
			switch (arg0) {
			case 0:
				Bundle bundle = arg2.getExtras();
				item_tv_name.setText("收货人：" + bundle.getString("name"));
				item_tv_tel.setText(bundle.getString("phone"));
				item_tv_address.setText("收货地址：" + bundle.getString("addresss"));
				break;

			default:
				break;
			}
		}
	}

	public void onclick(View v) {
		super.onclick(v);
		switch (v.getId()) {
		case R.id.llayout_head:
			goActivityForResult(ShippingAddressActivity.class, null, 0);
			break;
		case R.id.btn_clearing:
			break;
		default:
			break;
		}
	}

	@Override
	public void updateUI(int identity, Object data) {
		if (!mLinearLayout.isShown()) {
			mLinearLayout.setVisibility(View.VISIBLE);
		}
		switch (identity) {
		case RequestDistribute.FAVORITES_LIST:
			// mLoadingView.setVisibility(View.GONE);
			// FavoritesList favoritesList = (FavoritesList) data;
			// list = favoritesList.getmList();
			// String code = favoritesList.getCode();
			// if (TextUtils.isEmpty(code)) {
			// if (list.size() > 0) {
			// mListAdapter.addAll(list);
			// mListAdapter.notifyDataSetChanged();
			// } else {
			// // showToast("你还没有收藏的商品");
			// mNoDataRLayout.setVisibility(View.VISIBLE);
			// }
			// } else {
			// if ("1".equals(code)) {// 未登录
			// goActivity(LoginActivity.class, null);
			// } else {
			// showToast(favoritesList.getCodeMessage());
			// }
			// }

			break;
		case RequestDistribute.FAVORITES_LIST_DELETE:
			// RequestResponseInfo requestResponseInfo = (RequestResponseInfo)
			// data;
			// if ("".equals(requestResponseInfo.getErrorCode())) {
			// if (positions != null) {
			// int size = positions.size();
			// int positionArray[] = new int[size];
			// for (int i = 0; i < size; i++) {
			// positionArray[i] = positions.get(i);
			// }
			// Arrays.sort(positionArray);
			// int length = positionArray.length;
			// for (int i = length - 1; i >= 0; i--) {
			// int temp = positionArray[i];
			// mListAdapter.remove(temp);
			// list.remove(temp);
			// }
			// mListAdapter.getCheckPositions().clear();
			// mListAdapter.setIsEdite(false);
			// mDeleteBtn.setVisibility(View.GONE);
			// mListAdapter.notifyDataSetChanged();
			// if (list.size() == 0) {
			// mNoDataRLayout.setVisibility(View.VISIBLE);
			// }
			// }
			// // mLoadingView.setVisibility(View.VISIBLE);
			// // mDeleteBtn.setVisibility(View.GONE);
			// // mListAdapter.clear();
			// // showToast(requestResponseInfo.getState());
			// // getSupportLoaderManager().restartLoader(
			// // RequestDistribute.FAVORITES_LIST, null,
			// // new FavoritesListRequest(this));
			// } else {
			// showToast(requestResponseInfo.getErrorMessage());
			// }

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
				if (identity == RequestDistribute.FAVORITES_LIST) {
					mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void retryAgain(View v) {
		getSupportLoaderManager().restartLoader(
				RequestDistribute.FAVORITES_LIST, null,
				new GoodsDetailRequest(this));

	}
}
