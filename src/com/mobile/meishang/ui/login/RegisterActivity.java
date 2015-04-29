package com.mobile.meishang.ui.login;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.GetCodeRequest;
import com.mobile.meishang.core.request.RegisterRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Head;
import com.mobile.meishang.utils.FunctionUtil;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

/**
 * 注册一
 * 
 * @author Administrator
 * 
 */
public class RegisterActivity extends MActivity implements ExceptionHandler,
		LoadEvent {
	private LoadingView mLoadingView;
	private EditText etext_telephone;
	private TextView tv_get_verification_code;
	private EditText etext_verification_code;
	private EditText etext_password;
	private Bundle mBundle = new Bundle();
	private int recLen = 120;
	private Handler handler = new Handler();
	private String mMobile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		// findViewById(R.id.top_layout_back).setVisibility(View.VISIBLE);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("加入美商");
		title.setVisibility(View.VISIBLE);
		mLoadingView = (LoadingView) findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		etext_telephone = (EditText) findViewById(R.id.etext_telephone);
		tv_get_verification_code = (TextView) findViewById(R.id.tv_get_verification_code);
		etext_verification_code = (EditText) findViewById(R.id.etext_verification_code);
		etext_password = (EditText) findViewById(R.id.etext_password);
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	public void onclick(View v) {
		super.onclick(v);
		switch (v.getId()) {
		case R.id.tv_get_verification_code:
			mMobile = etext_telephone.getText().toString().trim();
			if (TextUtils.isEmpty(mMobile)) {
				showToast("请输入手机号");
				break;
			}
			if (!mMobile.startsWith("1") || mMobile.length() != 11
					|| FunctionUtil.hasSpecialChar(mMobile)) {
				showToast("请输入正确的手机号码");
				break;
			}
			mBundle.putString("telephone", mMobile);
			getSupportLoaderManager().restartLoader(RequestDistribute.GET_CODE,
					mBundle, new GetCodeRequest(this));
			break;
		case R.id.btn_finish:
			String mobile = etext_telephone.getText().toString().trim();
			String password = etext_password.getText().toString().trim();
			mBundle.putString("telephone", mobile);
			// mBundle.putString("password", FunctionUtil.MD5(password));
			mBundle.putString("password", password);
			mLoadingView.setVisibility(View.VISIBLE);
			getSupportLoaderManager().restartLoader(RequestDistribute.REGISTER,
					mBundle, new RegisterRequest(this));

			break;

		default:
			break;
		}
	}

	@Override
	public void updateUI(int identity, Object data) {
		// super.updateUI(identity, data);
		mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.GET_CODE:
			refreshTime();
			break;
		case RequestDistribute.REGISTER:
			Head head = (Head) data;
			if (head.isSuccess()) {
//				goActivity(LoginActivity.class, null);
				finish();
			} else {
				showToast(head.getMessage());
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void handleException(final int identity, final Exception e) {
		super.handleException(identity, e);
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (identity == RequestDistribute.REGISTER) {
					mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void retryAgain(View v) {
		getSupportLoaderManager().restartLoader(RequestDistribute.REGISTER,
				mBundle, new RegisterRequest(this));
	}

	private void refreshTime() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (tv_get_verification_code != null) {
					tv_get_verification_code.setText("" + recLen);
					recLen--;
					if (recLen > 0) {
						tv_get_verification_code.setClickable(false);
						if (handler != null) {
							handler.postDelayed(this, 1000);
						}
					} else {
						recLen = 120;
						tv_get_verification_code.setClickable(true);
						tv_get_verification_code.setText("获取验证码");
					}
				}

			}
		});
	}

}
