package com.mobile.meishang.ui.choujiang;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.core.request.RuleActivityRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.ChoujiangRule;

public class RuleActivity extends MActivity {
	private TextView tv_rule;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rule);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("活动规则");
		tv_rule = (TextView) findViewById(R.id.tv_rule);
		getSupportLoaderManager().restartLoader(
				RequestDistribute.CHOUJIANG_RULE, null,
				new RuleActivityRequest(this));
	}

	@Override
	public void updateUI(int identity, Object data) {
		// mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.CHOUJIANG_RULE:
			ChoujiangRule rule = (ChoujiangRule) data;
			tv_rule.setText(rule.getRuleText());
			break;

		default:
			break;
		}
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
