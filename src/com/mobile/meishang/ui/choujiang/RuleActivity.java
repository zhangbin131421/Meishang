package com.mobile.meishang.ui.choujiang;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;

public class RuleActivity extends MActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rule);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("活动规则");
	}

	@Override
	public void onclick(View v) {
		super.onclick(v);
		switch (v.getId()) {
		case R.id.tv_rule:

			break;

		default:
			break;
		}
	}

}
