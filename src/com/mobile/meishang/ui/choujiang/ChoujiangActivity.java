package com.mobile.meishang.ui.choujiang;

import java.util.Random;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.utils.FunctionUtil;

public class ChoujiangActivity extends MActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choujiang);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("欢乐抽奖");
		FrameLayout layout = (FrameLayout) findViewById(R.id.Lottery);
		int screnWidth = getWindowManager().getDefaultDisplay().getWidth();
		/**
		 * 添加随机数，制定奖项数量为上限，一般抽奖中什么都是服务器返回的，可以在请求服务器接口成功在制定转盘转到那个奖项
		 */
		final Random random = new Random();
		final Circleview claert = new Circleview(this, screnWidth);
		layout.addView(claert, new LayoutParams(LayoutParams.MATCH_PARENT,
				FunctionUtil.dip2px(this, 300)));

		findViewById(R.id.begin_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int place = random.nextInt(7);
				Log.e("当前的位置", place + "");
				claert.setStopPlace(place);
				claert.setStopRoter(false);
			}
		});
		findViewById(R.id.end_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				claert.setStopRoter(true);
			}
		});
	}

	@Override
	public void onclick(View v) {
		super.onclick(v);
		switch (v.getId()) {
		case R.id.tv_rule:
			goActivity(RuleActivity.class, null);
			break;

		default:
			break;
		}
	}

}
