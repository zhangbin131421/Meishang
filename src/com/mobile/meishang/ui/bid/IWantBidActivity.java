package com.mobile.meishang.ui.bid;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.CategoryLeftListAdapter;
import com.mobile.meishang.adapter.CategoryRightListAdapter;
import com.mobile.meishang.core.request.BidPublishRequest;
import com.mobile.meishang.core.request.CategoryRequest;
import com.mobile.meishang.model.Module;
import com.mobile.meishang.model.ModuleList;
import com.mobile.meishang.model.RequestDistribute;
import com.umeng.analytics.MobclickAgent;

public class IWantBidActivity extends MActivity {
	private TextView tv_choose_category;
	private EditText etv_title;
	private EditText etv_phone;
	private EditText etv_address;
	private EditText etv_feature;
	private EditText etv_introduce;
	private LinearLayout llayout;
	private ListView listview_left;
	private ListView listview_right;
	private CategoryLeftListAdapter filterLeftAdapter;
	private CategoryRightListAdapter filterRightAdapter;
	// private List<CategoryFilter> filterLeft;
	// private List<CategoryFilter> filterRight;
	private List<Module> moduleList;
	private String moduleid;
	private String smoduleid;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_i_want_bid);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("我要竞标");
		TextView tv_top_right = (TextView) findViewById(R.id.tv_top_right);
		tv_top_right.setText("历史竞标");
		tv_top_right.setVisibility(View.VISIBLE);
		tv_choose_category = (TextView) findViewById(R.id.tv_choose_category);
		etv_title = (EditText) findViewById(R.id.etv_title);
		etv_phone = (EditText) findViewById(R.id.etv_phone);
		etv_address = (EditText) findViewById(R.id.etv_address);
		etv_feature = (EditText) findViewById(R.id.etv_feature);
		etv_introduce = (EditText) findViewById(R.id.etv_introduce);
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
				tv_choose_category.setText(filterRightAdapter.getItem(position)
						.getName());
				filterRightAdapter.setmPosition(position);

			}
		});
		getSupportLoaderManager().initLoader(RequestDistribute.CATEGORY, null,
				new CategoryRequest(this));
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
				if (identity == RequestDistribute.CATEGORY) {
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
			case RequestDistribute.CATEGORY:
				ModuleList modules = (ModuleList) data;
				moduleList = modules.getModuleList();
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

	private void netPublished() {
		// title 标题item：项目特色
		// prodesc项目介绍 proaddress项目地址,userid 用户编号
		// smoduleid小类型编号 phone:联系手机号码 moduleid 大类型编号
		String title = etv_title.getEditableText().toString();
		String phone = etv_phone.getEditableText().toString();
		String proaddress = etv_address.getEditableText().toString();
		String item = etv_feature.getEditableText().toString();
		String prodesc = etv_introduce.getEditableText().toString();

		Bundle bundle = new Bundle();
		bundle.putString("moduleid", moduleid);
		bundle.putString("smoduleid", smoduleid);
		bundle.putString("title", title);
		bundle.putString("phone", phone);
		bundle.putString("proaddress", proaddress);
		bundle.putString("item", item);
		bundle.putString("prodesc", prodesc);
		getSupportLoaderManager().restartLoader(RequestDistribute.PUBLISHED,
				bundle, new BidPublishRequest(this));
	}

}
