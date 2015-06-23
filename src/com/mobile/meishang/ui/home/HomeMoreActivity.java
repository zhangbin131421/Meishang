package com.mobile.meishang.ui.home;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (mAdapter.getItem(arg2).getFlag() == 0) {
					mAdapter.getItem(arg2).setFlag(1);
					update("" + arg2, 1);
				} else {
					mAdapter.getItem(arg2).setFlag(0);
					update("" + arg2, 0);
				}
				mAdapter.notifyDataSetChanged();
			}
		});
		mAdapter = new HomeMoreListviewAdapter(this);
		listview.setAdapter(mAdapter);
		requestLocal();
		mAdapter.clear();
		mAdapter.addAll(mDataItems);
		mAdapter.notifyDataSetChanged();

	}

	@Override
	public void onclick(View v) {
		switch (v.getId()) {
		case R.id.top_layout_back:
			setResult(RESULT_OK);
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			setResult(RESULT_OK);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void update(String temp, int flag) {
		String[] whereArgs = { DBConstants.Home_model.HOME_MODEL_POSITION + "" };
		String[] whereArgsValues = { temp };
		ContentValues contentValues = new ContentValues();
		contentValues.put(DBConstants.Home_model.HOME_MODEL_FLAG, flag);
		LocalDataManager.getInstance().doUpdate(
				DBConstants.DB_TABLE.TABLE_HOME_MODEl, contentValues,
				whereArgs, whereArgsValues);
	}

	private void requestLocal() {
		mDataItems = new ArrayList<HomeFragmentTemplateDataItem>();
		Cursor cursor = null;
		String sql = "select * from " + DBConstants.DB_TABLE.TABLE_HOME_MODEl;
		HomeFragmentTemplateDataItem dataItem;
		cursor = LocalDataManager.getInstance().doQuery(sql);
		if (cursor != null && cursor.moveToFirst()) {
			dataItem = new HomeFragmentTemplateDataItem();
			dataItem.setModulename(cursor.getString(cursor
					.getColumnIndex(DBConstants.Home_model.HOME_MODEL_NAME)));
			dataItem.setImage(cursor.getInt(cursor
					.getColumnIndex(DBConstants.Home_model.HOME_MODEL_IMAGE)));
			dataItem.setFlag(cursor.getInt(cursor
					.getColumnIndex(DBConstants.Home_model.HOME_MODEL_FLAG)));
			mDataItems.add(dataItem);
			while (cursor.moveToNext()) {
				dataItem = new HomeFragmentTemplateDataItem();
				dataItem.setModulename(cursor.getString(cursor
						.getColumnIndex(DBConstants.Home_model.HOME_MODEL_NAME)));
				dataItem.setImage(cursor.getInt(cursor
						.getColumnIndex(DBConstants.Home_model.HOME_MODEL_IMAGE)));
				dataItem.setFlag(cursor.getInt(cursor
						.getColumnIndex(DBConstants.Home_model.HOME_MODEL_FLAG)));
				mDataItems.add(dataItem);
			}

		}
		if (null != cursor) {
			cursor.close();
		}
	}
}
