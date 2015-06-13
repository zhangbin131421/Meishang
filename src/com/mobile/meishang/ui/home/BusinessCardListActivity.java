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
import com.mobile.meishang.model.bean.BusinessCardItem;

public class BusinessCardListActivity extends MActivity {
	private ListView listview;;
	private BusinessCardListviewAdapter mAdapter;
	private List<BusinessCardItem> mDataItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_business_card_list);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("美商云讯");
		listview = (ListView) findViewById(R.id.listview);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
			}
		});
		mAdapter = new BusinessCardListviewAdapter(this);
		listview.setAdapter(mAdapter);
		mDataItems=new ArrayList<BusinessCardItem>();
		mAdapter.clear();
		mAdapter.addAll(mDataItems);
		mAdapter.notifyDataSetChanged();

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



}
