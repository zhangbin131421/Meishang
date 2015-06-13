package com.mobile.meishang.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;

public class BusinessCardInfoActivity extends MActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_business_card_info);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("个人资料");

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
