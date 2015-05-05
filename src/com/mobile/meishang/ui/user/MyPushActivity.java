package com.mobile.meishang.ui.user;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.MyPushListviewAdapter;

public class MyPushActivity extends MActivity {
	private TextView tv_top_right;
	private ListView listview;;
	private MyPushListviewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_shared);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("我的推送");
		tv_top_right = (TextView) findViewById(R.id.tv_top_right);
		tv_top_right.setText("编辑");
		tv_top_right.setVisibility(View.VISIBLE);
		listview = (ListView) findViewById(R.id.listview);
		adapter = new MyPushListviewAdapter(this);
		listview.setAdapter(adapter);

	}

	@Override
	public void onclick(View v) {
		super.onclick(v);
		switch (v.getId()) {
		case R.id.tv_top_right:
			if ("关闭".equals(tv_top_right.getText().toString())) {
				tv_top_right.setText("编辑");
			} else {
				tv_top_right.setText("关闭");
			}
			break;

		default:
			break;
		}
	}
}
