package com.mobile.meishang.ui.home;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.CategoryLeftListAdapter;
import com.mobile.meishang.adapter.CategoryRightListAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.BusinessCardAddRequest;
import com.mobile.meishang.core.request.CategoryRequest;
import com.mobile.meishang.model.Module;
import com.mobile.meishang.model.ModuleList;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Head;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;

public class BusinessCardInfoAddActivity extends MActivity implements
		ExceptionHandler, LoadEvent {
	private EditText etext_name;
	private EditText etext_telephone;
	private EditText etext_company;
	private EditText etext_position;
	private TextView tv_moduleId;
	private Bundle mBundle;
	private ListView listview_left;
	private ListView listview_right;
	private CategoryLeftListAdapter filterLeftAdapter;
	private CategoryRightListAdapter filterRightAdapter;
	private List<Module> moduleList;
	private String moduleid="1";
	private String smoduleid="1";
	private LinearLayout llayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_business_card_info_add);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("个人资料");
		etext_name = (EditText) findViewById(R.id.etext_name);
		etext_telephone = (EditText) findViewById(R.id.etext_telephone);
		etext_company = (EditText) findViewById(R.id.etext_company);
		etext_position = (EditText) findViewById(R.id.etext_position);
		tv_moduleId =  (TextView) findViewById(R.id.tv_moduleId);
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
				tv_moduleId.setText(filterRightAdapter.getItem(position)
						.getName());
				filterRightAdapter.setmPosition(position);
				llayout.setVisibility(View.GONE);

			}
		});
		getSupportLoaderManager().initLoader(RequestDistribute.CATEGORY, null,
				new CategoryRequest(this));

	}

	@Override
	public void updateUI(int identity, Object data) {
		switch (identity) {
		case RequestDistribute.BUSINESS_CARD_Add:
			Head h = (Head) data;
			if (h.isSuccess()) {

			}
			showToast(h.getMessage());
			break;
		case RequestDistribute.CATEGORY:
			ModuleList modules = (ModuleList) data;
			moduleList = modules.getModuleList();
			break;
		default:
			break;
		}
	}

	private void net() {
		mBundle = new Bundle();
		mBundle.putString("name", etext_name.getText().toString());
		mBundle.putString("tel", etext_telephone.getText().toString());
		mBundle.putString("companyName", etext_company.getText().toString());
		mBundle.putString("position", etext_position.getText().toString());
		mBundle.putString("provinceId", "3");
		mBundle.putString("moduleId", smoduleid);
		getSupportLoaderManager().restartLoader(
				RequestDistribute.BUSINESS_CARD_Add, mBundle,
				new BusinessCardAddRequest(this));
	}

	@Override
	public void onclick(View v) {
		super.onclick(v);
		switch (v.getId()) {
		case R.id.tv_save:
			net();
			break;
		case R.id.tv_moduleId:
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
			break;
		default:
			break;
		}
	}

	@Override
	public void retryAgain(View v) {

	}

}
