package com.mobile.meishang.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;

public class BusinessCardNoActivity extends MActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_business_card_info_add);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("美商云讯");

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
