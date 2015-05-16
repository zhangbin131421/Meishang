package com.mobile.meishang.ui.home.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.SignInListviewAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.SignInFragmentRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.SignInFragmentData;
import com.mobile.meishang.ui.home.SignDetailActivity;
import com.mobile.meishang.ui.home.SignRuleActivity;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

public class SignInFragment extends MFragment implements OnClickListener,
		ExceptionHandler, LoadEvent {
	private LoadingView mLoadingView;
	private TextView tv_count;
	private TextView tv_integral;
	private Button btn_sign;
	private ListView listview;
	private SignInListviewAdapter adapter;
	private Bundle mBundle;
	private LinearLayout llayout;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_sign_in, null);
		view.findViewById(R.id.top_layout_back).setVisibility(View.GONE);
		TextView title = (TextView) view.findViewById(R.id.top_name);
		title.setText("每日签到");
		mLoadingView = (LoadingView) view.findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		mLoadingView.setVisibility(View.GONE);
		listview = (ListView) view.findViewById(R.id.listview);
		View headView = LayoutInflater.from(mContext).inflate(
				R.layout.layout_sign_in_head, null);
		tv_count = (TextView) headView.findViewById(R.id.tv_count);
		tv_integral = (TextView) headView.findViewById(R.id.tv_integral);
		headView.findViewById(R.id.tv_go_shopping).setOnClickListener(this);
		btn_sign = (Button) headView.findViewById(R.id.btn_sign);
		btn_sign.setOnClickListener(this);
		headView.findViewById(R.id.tv_rule).setOnClickListener(this);
		headView.findViewById(R.id.img_delete).setOnClickListener(this);
		llayout = (LinearLayout) headView.findViewById(R.id.llayout);
		listview.addHeaderView(headView);
		adapter = new SignInListviewAdapter(mContext);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				showToast("缺少projectid");
				position -= 1;
				Bundle bundle = new Bundle();
				bundle.putString("projectid", adapter.getItem(position)
						.getPurchasedid());
				goActivity(SignDetailActivity.class, bundle);
			}
		});
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// mBundle = new Bundle();
		// mBundle.putString("id", "");
		// mBundle.putString("range", "");
		getLoaderManager().restartLoader(RequestDistribute.SIGNIN_FRAGMENT,
				mBundle, new SignInFragmentRequest(this));
		tv_integral.setText(MApplication.getInstance().getLogin().getIntegral()
				+ "积分");
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(mContext);
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(mContext);
	}

	@Override
	public void handleException(int identity, Exception e) {

	}

	@Override
	public void retryAgain(View v) {

	}

	@Override
	public void updateUI(int identity, Object data) {
		mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.SIGNIN_FRAGMENT:
			SignInFragmentData signInFragmentData = (SignInFragmentData) data;
			adapter.clear();
			adapter.addAll(signInFragmentData.getmList());
			adapter.notifyDataSetChanged();
			tv_count.setText("已有" + signInFragmentData.getCount() + "签到");
			tv_integral.setText(MApplication.getInstance().getLogin()
					.getIntegral()
					+ "积分");
			break;

		default:
			break;
		}
	}

	@Override
	public void resetUI(int identity, Object data) {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_go_shopping:
			showToast("积分商城");
			break;
		case R.id.btn_sign:
			showToast("签到");
			break;
		case R.id.tv_rule:
			goActivity(SignRuleActivity.class, null);
			break;
		case R.id.img_delete:
			llayout.setVisibility(View.GONE);
			break;

		default:
			break;
		}
	}

}
