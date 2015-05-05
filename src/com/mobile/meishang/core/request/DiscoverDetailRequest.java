package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.core.content.MDiscoverDetailLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.DiscoverDetail;

public class DiscoverDetailRequest implements
		LoaderManager.LoaderCallbacks<DiscoverDetail> {

	private MActivity mActivity;
	private MFragment mFragment;

	public DiscoverDetailRequest(MActivity activity) {
		this.mActivity = activity;
	}

	public DiscoverDetailRequest(MFragment fragment) {
		this.mFragment = fragment;
		mActivity = (MActivity) fragment.getActivity();
	}

	@Override
	public Loader<DiscoverDetail> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("project/load.htm");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("projectid",
				bundle.getString("projectid"));
		MDiscoverDetailLoader loader = new MDiscoverDetailLoader(mActivity,
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
	public void onLoadFinished(Loader<DiscoverDetail> arg0, DiscoverDetail arg1) {
		if (arg1 != null) {
			if (mFragment == null) {
				mActivity.updateUI(arg0.getId(), arg1);
			} else {
				mFragment.updateUI(arg0.getId(), arg1);
			}
		}
	}

	@Override
	public void onLoaderReset(Loader<DiscoverDetail> arg0) {
	}

}
