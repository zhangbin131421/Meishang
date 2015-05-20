package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.core.loader.LoginLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.Login;

public class SignGetIntegralRequest implements
		LoaderManager.LoaderCallbacks<Login> {

	private MActivity mActivity;
	private MFragment mFragment;

	public SignGetIntegralRequest(MActivity activity) {
		this.mActivity = activity;
	}

	public SignGetIntegralRequest(MFragment fragment) {
		this.mFragment = fragment;
		mActivity = (MActivity) fragment.getActivity();
	}

	// userid：用户编号 objectid:收藏对应编号 type：1是积分收藏 2项目收藏 3是资讯收藏

	@Override
	public Loader<Login> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("sign/sign.htm");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		// mHttpRequest.addPostParameter("integral",
		// bundle.getString("objectid"));
		// mHttpRequest.addPostParameter("type", bundle.getString("type"));

		mHttpRequest.addPostParameter("userid", MApplication.getInstance()
				.getLogin().getUserId());
		if (bundle != null) {
			mHttpRequest.addPostParameter("type", bundle.getString("type"));
			mHttpRequest.addPostParameter("projectid",
					bundle.getString("projectid"));
			mHttpRequest.addPostParameter("integral",
					bundle.getString("integral"));
		}
		LoginLoader loader = new LoginLoader(mActivity, mHttpRequest);
		if (mFragment == null) {

			loader.setExceptionHandler(mActivity);
		} else {

			loader.setExceptionHandler(mFragment);
		}
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Login> arg0, Login arg1) {
		if (arg1 != null) {
			if (mFragment == null) {
				mActivity.updateUI(arg0.getId(), arg1);
			} else {
				mFragment.updateUI(arg0.getId(), arg1);
			}
		}
	}

	@Override
	public void onLoaderReset(Loader<Login> arg0) {
	}

}
