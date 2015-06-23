package com.mobile.meishang.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;

public class BusinessCardNoActivity extends MActivity {
	// 没有名片
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_business_card_no);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("美商云讯");

	}

	@Override
	public void onclick(View v) {
		switch (v.getId()) {
		case R.id.tv_creat_bcard:
			goActivity(BusinessCardInfoAddActivity.class, null);
			break;
		default:
			break;
		}
	}

}
