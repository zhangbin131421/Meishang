package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.core.content.LehuigoSearchLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.LehuigoHomeData;

public class LehuigoSearchRequest implements
		LoaderManager.LoaderCallbacks<LehuigoHomeData> {

	private MFragment mFragment;
	private MActivity mActivity;

	public LehuigoSearchRequest(MActivity activity) {
		this.mActivity = activity;
	}

	public LehuigoSearchRequest(MFragment fragment) {
		this.mFragment = fragment;
		mActivity = (MActivity) fragment.getActivity();
	}

	@Override
	public Loader<LehuigoHomeData> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("purchased/list/more.htm");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		// Title:标题 integral1：积分1 integral2：积分2 moduleid：大模块编号 smoduleid：小

		mHttpRequest.addPostParameter("title", bundle.getString("title"));
		if (bundle.containsKey("integral1")) {

			mHttpRequest.addPostParameter("integral1",
					bundle.getString("integral1"));
		}
		if (bundle.containsKey("integral2")) {

			mHttpRequest.addPostParameter("integral2",
					bundle.getString("integral2"));
		}
		if (bundle.containsKey("moduleid")) {

			mHttpRequest.addPostParameter("moduleid",
					bundle.getString("moduleid"));
		}
		LehuigoSearchLoader loader = new LehuigoSearchLoader(mActivity,
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
