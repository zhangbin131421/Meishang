package com.mobile.meishang.ui.user;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.MyWordsListviewAdapter;

public class MyWordsActivity extends MActivity {

	private ListView listview;;
	private MyWordsListviewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_shared);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("分享");
		listview = (ListView) findViewById(R.id.listview);
		adapter = new MyWordsListviewAdapter(this);
		listview.setAdapter(adapter);

	}
}
