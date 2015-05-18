package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.core.loader.BidDetailLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.BidMyPublish;

public class BidDetailRequest implements
		LoaderManager.LoaderCallbacks<BidMyPublish> {

	private MActivity mActivity;
	private MFragment mFragment;

	public BidDetailRequest(MActivity activity) {
		this.mActivity = activity;
	}

	public BidDetailRequest(MFragment fragment) {
		this.mFragment = fragment;
		mActivity = (MActivity) fragment.getActivity();
	}

	@Override
	public Loader<BidMyPublish> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("bidding/load.htm");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("biddingid",
				bundle.getString("biddingid"));
		BidDetailLoader loader = new BidDetailLoader(mActivity, mHttpRequest);
		if (mFragment == null) {
			loader.setExceptionHandler(mActivity);
		} else {
			loader.setExceptionHandler(mFragment);
		}
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<BidMyPublish> arg0,
			BidMyPublish arg1) {
		if (arg1 != null) {
			if (mFragment == null) {
				mActivity.updateUI(arg0.getId(), arg1);
			} else {
				mFragment.updateUI(arg0.getId(), arg1);
			}
		}
	}

	@Override
	public void onLoaderReset(Loader<BidMyPublish> arg0) {
	}

}
