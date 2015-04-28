package com.mobile.meishang.ui.user;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.MySharedListviewAdapter;

public class MySharedActivity extends MActivity {

	private ListView listview;
	private MySharedListviewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_shared);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("分享");
		listview = (ListView) findViewById(R.id.listview);
		adapter = new MySharedListviewAdapter(this);
		listview.setAdapter(adapter);

	}
}
