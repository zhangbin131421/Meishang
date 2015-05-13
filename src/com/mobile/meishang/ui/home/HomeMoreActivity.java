package com.mobile.meishang.ui.home;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.HomeMoreListviewAdapter;
import com.mobile.meishang.core.local.LocalDataManager;
import com.mobile.meishang.database.DBConstants;
import com.mobile.meishang.model.bean.HomeFragmentTemplateDataItem;

public class HomeMoreActivity extends MActivity {
	private ListView listview;;
	private HomeMoreListviewAdapter mAdapter;
	private List<HomeFragmentTemplateDataItem> mDataItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_more);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("更多");
		listview = (ListView) findViewById(R.id.listview);
		mAdapter = new HomeMoreListviewAdapter(this);
		listview.setAdapter(mAdapter);
		requestLocal();
		mAdapter.clear();
		mAdapter.addAll(mDataItems);
		mAdapter.notifyDataSetChanged();
	}

	private void requestLocal() {
		mDataItems = new ArrayList<HomeFragmentTemplateDataItem>();
		Cursor cursor = null;
		String sql = "select * from " + DBConstants.DB_TABLE.TABLE_HOME_MODEl
				+ " where " + DBConstants.Home_model.HOME_MODEL_FLAG + " =0";
		HomeFragmentTemplateDataItem dataItem;
		cursor = LocalDataManager.getInstance().doQuery(sql);
		if (cursor != null && cursor.moveToFirst()) {
			dataItem = new HomeFragmentTemplateDataItem();
			dataItem.setModulename(cursor.getString(cursor
					.getColumnIndex(DBConstants.Home_model.HOME_MODEL_NAME)));
			dataItem.setImage(cursor.getInt(cursor
					.getColumnIndex(DBConstants.Home_model.HOME_MODEL_IMAGE)));
			dataItem.setFlag(0);
			mDataItems.add(dataItem);
			while (cursor.moveToNext()) {
				dataItem = new HomeFragmentTemplateDataItem();
				dataItem.setModulename(cursor.getString(cursor
						.getColumnIndex(DBConstants.Home_model.HOME_MODEL_NAME)));
				dataItem.setImage(cursor.getInt(cursor
						.getColumnIndex(DBConstants.Home_model.HOME_MODEL_IMAGE)));
				dataItem.setFlag(0);
				mDataItems.add(dataItem);
			}

		}
		if (null != cursor) {
			cursor.close();
		}
	}
}
