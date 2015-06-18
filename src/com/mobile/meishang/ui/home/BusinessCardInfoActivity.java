package com.mobile.meishang.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.model.BusinessCard;

public class BusinessCardInfoActivity extends MActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_business_card_info);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("个人资料");
		TextView tv_name = (TextView) findViewById(R.id.tv_name);
		TextView tv_tel = (TextView) findViewById(R.id.tv_tel);
		TextView tv_company = (TextView) findViewById(R.id.tv_company);
		TextView tv_position = (TextView) findViewById(R.id.tv_position);
		TextView tv_province = (TextView) findViewById(R.id.tv_province);
		TextView tv_module_name = (TextView) findViewById(R.id.tv_module_name);
		Bundle bundle = getIntent().getBundleExtra("bundle");
		BusinessCard businessCard = bundle.getParcelable("BusinessCard");

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
