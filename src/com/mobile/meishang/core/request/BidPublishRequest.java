package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.loader.HeadLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Head;

public class BidPublishRequest implements LoaderManager.LoaderCallbacks<Head> {

	private MActivity mActivity;

	public BidPublishRequest(MActivity activity) {
		this.mActivity = activity;
	}

	@Override
	public Loader<Head> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("bidding/save.htm");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("moduleid", bundle.getString("moduleid"));
		mHttpRequest.addPostParameter("smoduleid",
				bundle.getString("smoduleid"));
		mHttpRequest.addPostParameter("title", bundle.getString("title"));
		mHttpRequest.addPostParameter("phone", bundle.getString("phone"));
		mHttpRequest.addPostParameter("proaddress",
				bundle.getString("proaddress"));
		mHttpRequest.addPostParameter("item", bundle.getString("item"));
		mHttpRequest.addPostParameter("prodesc", bundle.getString("prodesc"));
		mHttpRequest.addPostParameter("userid", MApplication.getInstance()
				.getLogin().getUserId());
		HeadLoader loader = new HeadLoader(mActivity, mHttpRequest);
		loader.setExceptionHandler(mActivity);
		loader.setIdentit(RequestDistribute.REGISTER);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Head> arg0, Head arg1) {
		if (arg1 != null) {
			mActivity.updateUI(RequestDistribute.REGISTER, arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<Head> arg0) {
	}

}
