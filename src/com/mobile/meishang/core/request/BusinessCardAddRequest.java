package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.core.loader.HeadLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.Head;

public class BusinessCardAddRequest implements LoaderManager.LoaderCallbacks<Head> {

	private MActivity mActivity;
	private MFragment mFragment;

	public BusinessCardAddRequest(MActivity activity) {
		this.mActivity = activity;
	}

	public BusinessCardAddRequest(MFragment fragment) {
		this.mFragment = fragment;
		mActivity = (MActivity) fragment.getActivity();
	}

	@Override
	public Loader<Head> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("businesscard/save.htm");
		// name：收货人姓名phone：收货人手机号码post：邮编address：地址addresss：详细地址userid：用户编号
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("name", bundle.getString("name"));
		mHttpRequest.addPostParameter("tel", bundle.getString("tel"));
		mHttpRequest.addPostParameter("companyName", bundle.getString("companyName"));
		mHttpRequest.addPostParameter("position", bundle.getString("position"));
		mHttpRequest.addPostParameter("provinceId", bundle.getString("provinceId"));
		mHttpRequest.addPostParameter("moduleId", bundle.getString("moduleId"));
		mHttpRequest.addPostParameter("userId", MApplication.getInstance()
				.getLogin().getUserId());
		HeadLoader loader = new HeadLoader(mActivity, mHttpRequest);
		if (mFragment == null) {

			loader.setExceptionHandler(mActivity);
		} else {

			loader.setExceptionHandler(mFragment);
		}
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Head> arg0, Head arg1) {
		if (arg1 != null) {
			if (mFragment == null) {
				mActivity.updateUI(arg0.getId(), arg1);
			} else {
				mFragment.updateUI(arg0.getId(), arg1);
			}
		}
	}

	@Override
	public void onLoaderReset(Loader<Head> arg0) {
	}

}
