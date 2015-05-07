package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.core.content.LehuigoHomeLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.LehuigoHomeData;

public class LehuigoHomeRequest implements
		LoaderManager.LoaderCallbacks<LehuigoHomeData> {

	private MFragment mFragment;
	private MActivity mActivity;

	public LehuigoHomeRequest(MActivity activity) {
		this.mActivity = activity;
	}

	public LehuigoHomeRequest(MFragment fragment) {
		this.mFragment = fragment;
		mActivity = (MActivity) fragment.getActivity();
	}

	@Override
	public Loader<LehuigoHomeData> onCreateLoader(int arg0, Bundle arg1) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("purchased/list.htm");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
//		mHttpRequest.addPostParameter("page", "1");
		LehuigoHomeLoader loader = new LehuigoHomeLoader(mActivity,
				mHttpRequest);
		if (mFragment == null) {

			loader.setExceptionHandler(mActivity);
		} else {

			loader.setExceptionHandler(mFragment);
		}
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<LehuigoHomeData> arg0,
			LehuigoHomeData arg1) {
		if (arg1 != null) {
			if (mFragment == null) {
				mActivity.updateUI(arg0.getId(), arg1);
			} else {
				mFragment.updateUI(arg0.getId(), arg1);
			}

		}
	}

	@Override
	public void onLoaderReset(Loader<LehuigoHomeData> arg0) {
	}

}
