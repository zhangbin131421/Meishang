package com.mobile.meishang.ui.home.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.R;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Head;
import com.mobile.meishang.model.bean.User;
import com.mobile.meishang.ui.favorites.FavoritesActivity;
import com.mobile.meishang.ui.login.LoginActivity;
import com.mobile.meishang.ui.login.RegisterActivity;
import com.mobile.meishang.ui.shopping.ShoppingCarListActivity;
import com.mobile.meishang.ui.user.MyPushActivity;
import com.mobile.meishang.ui.user.MySharedActivity;
import com.mobile.meishang.ui.user.MyWordsActivity;
import com.umeng.analytics.MobclickAgent;

public class MeFragment extends MFragment implements OnClickListener {
	private TextView tv_login_status;
	private LinearLayout llayout_login;
	private TextView tv_favorites_quantity;
	private TextView tv_attention_quantity;
	private TextView tv_msg_quantity;

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
		View view = inflater.inflate(R.layout.fragment_me, null);
		view.findViewById(R.id.top_layout_back).setVisibility(View.GONE);
		TextView title = (TextView) view.findViewById(R.id.top_name);
		title.setText("我的");
		view.findViewById(R.id.tv_login).setOnClickListener(this);
		view.findViewById(R.id.tv_register).setOnClickListener(this);
		view.findViewById(R.id.llayout_favorites).setOnClickListener(this);
		view.findViewById(R.id.llayout_attention).setOnClickListener(this);
		view.findViewById(R.id.llayout_shopping_car).setOnClickListener(this);
		tv_login_status = (TextView) view.findViewById(R.id.tv_login_status);
		llayout_login = (LinearLayout) view.findViewById(R.id.llayout_login);
		tv_favorites_quantity = (TextView) view
				.findViewById(R.id.tv_favorites_quantity);
		tv_attention_quantity = (TextView) view
				.findViewById(R.id.tv_attention_quantity);
		view.findViewById(R.id.llayout_msg).setOnClickListener(this);
		tv_msg_quantity = (TextView) view.findViewById(R.id.tv_msg_quantity);
		view.findViewById(R.id.tv_my_shared).setOnClickListener(this);
		view.findViewById(R.id.tv_my_push).setOnClickListener(this);
		view.findViewById(R.id.tv_wait_comment).setOnClickListener(this);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onResume() {
		super.onResume();
		User login = MApplication.getInstance().getLogin();
		if (login == null) {
			tv_login_status.setText("未登录");
			llayout_login.setVisibility(View.VISIBLE);
		} else {
			tv_login_status.setText(login.getNickname());
			llayout_login.setVisibility(View.GONE);
		}
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
	public void updateUI(int identity, Object data) {

		switch (identity) {
		case RequestDistribute.LOGOUT:
			Head requestResponseInfo = (Head) data;

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
		case R.id.tv_login:
			goActivity(LoginActivity.class, null);
			break;
		case R.id.tv_register:
			goActivity(RegisterActivity.class, null);
			break;
		case R.id.llayout_favorites:
			if (MApplication.getInstance().checkLogin()) {
				goActivity(FavoritesActivity.class, null);

			} else {
				goActivity(LoginActivity.class, null);
			}
			break;
		case R.id.llayout_attention:
			showToast("功能尚在开发中");
			// goActivity(LoginActivity.class, null);
			break;
		case R.id.llayout_shopping_car:
			goActivity(ShoppingCarListActivity.class, null);
			break;
		case R.id.tv_my_shared:
			// if (MApplication.getInstance().checkLogin()) {
			// goActivity(MyVoucherActivity.class, null);
			// } else {
			// goActivityForResult(LoginActivity.class, null,
			// RequestDistribute.LOGIN);
			// }
			goActivity(MySharedActivity.class, null);
			break;
		case R.id.llayout_msg:
			if (MApplication.getInstance().checkLogin()) {
				// goActivity(MyVoucherActivity.class, null);
			} else {
				goActivityForResult(LoginActivity.class, null,
						RequestDistribute.LOGIN);
			}
			goActivity(MyWordsActivity.class, null);
			break;
		case R.id.tv_my_push:
			goActivity(MyPushActivity.class, null);
			break;
		case R.id.tv_wait_comment:
			showToast("功能尚在开发中");
			// goActivity(LoginActivity.class, null);
			break;
		default:
			break;
		}

	}

}
