package com.mobile.meishang.ui.shopping;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.ShoppingOrderListAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.AddressGetRequest;
import com.mobile.meishang.core.request.GoodsDetailRequest;
import com.mobile.meishang.model.Address;
import com.mobile.meishang.model.AddressList;
import com.mobile.meishang.model.LehuigoDetailData;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

public class ShoppingOrderActivity extends MActivity implements
		ExceptionHandler, LoadEvent {
	private LoadingView mLoadingView;
	private ListView mListView;
	private ShoppingOrderListAdapter mListAdapter;
	private LinearLayout llayout_head;
	private Button btn_add_address;
	private TextView item_tv_name;
	private TextView item_tv_tel;
	private TextView item_tv_address;
	private List<LehuigoDetailData> shoppingCarGoods;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping_order);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("确认订单");
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		View hView = LayoutInflater.from(this).inflate(
				R.layout.item_shopping_order_lh, null);
		llayout_head = (LinearLayout) hView.findViewById(R.id.llayout_head);
		btn_add_address = (Button) hView.findViewById(R.id.btn_add_address);
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
		getSupportLoaderManager().restartLoader(
				RequestDistribute.SHIPPING_ADDRESS_GET, null,
				new AddressGetRequest(this));
		Bundle bundle = getIntent().getBundleExtra("bundle");
		LehuigoDetailData detailData = bundle
				.getParcelable("LehuigoDetailData");
		shoppingCarGoods = new ArrayList<LehuigoDetailData>();
		shoppingCarGoods.add(detailData);
		mListAdapter.clear();
		mListAdapter.addAll(shoppingCarGoods);
		mListAdapter.notifyDataSetChanged();

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
				llayout_head.setVisibility(View.VISIBLE);
				btn_add_address.setVisibility(View.GONE);
				Bundle bundle = arg2.getExtras();
				item_tv_name.setText("收货人：" + bundle.getString("name"));
				item_tv_tel.setText(bundle.getString("phone"));
				item_tv_address.setText("收货地址：" + bundle.getString("addresss"));
				break;

			default:
				break;
			}
		} else {
			switch (arg0) {
			case 0:
				llayout_head.setVisibility(View.GONE);
				btn_add_address.setVisibility(View.VISIBLE);
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
		case R.id.btn_add_address:
			goActivityForResult(ShippingAddressActivity.class, null, 0);
			break;
		default:
			break;
		}
	}

	@Override
	public void updateUI(int identity, Object data) {
		mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.SHIPPING_ADDRESS_GET:
			AddressList addresses = (AddressList) data;
			List<Address> list = addresses.getList();
			if (list.size() > 0) {
				llayout_head.setVisibility(View.VISIBLE);
				btn_add_address.setVisibility(View.GONE);
				Address address = list.get(0);
				item_tv_name.setText("收货人：" + address.getName());
				item_tv_tel.setText(address.getPhone());
				item_tv_address.setText("收货地址：" + address.getAddresss());
			} else {
				llayout_head.setVisibility(View.GONE);
				btn_add_address.setVisibility(View.VISIBLE);
			}
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
