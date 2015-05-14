package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.core.loader.HistoryBidLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.User;

public class HistoryBidRequest implements LoaderManager.LoaderCallbacks<User> {

	private MActivity mActivity;
	private MFragment mFragment;

	public HistoryBidRequest(MActivity activity) {
		this.mActivity = activity;
	}

	public HistoryBidRequest(MFragment fragment) {
		this.mFragment = fragment;
		mActivity = (MActivity) fragment.getActivity();
	}

	@Override
	public Loader<User> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("bidding/h/bidding.htm");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		HistoryBidLoader loader = new HistoryBidLoader(mActivity, mHttpRequest);
		if (mFragment == null) {
			loader.setExceptionHandler(mActivity);
		} else {
			loader.setExceptionHandler(mFragment);
		}
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<User> arg0, User arg1) {
		if (arg1 != null) {
			if (mFragment == null) {
				mActivity.updateUI(arg0.getId(), arg1);
			} else {
				mFragment.updateUI(arg0.getId(), arg1);
			}
		}
	}

	@Override
	public void onLoaderReset(Loader<User> arg0) {
	}

}
