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

public class GetCodeRequest implements LoaderManager.LoaderCallbacks<Head> {

	private MActivity mActivity;

	public GetCodeRequest(MActivity activity) {
		this.mActivity = activity;
	}

	@Override
	public Loader<Head> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("/dataUser/sendCode.htm");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("telephone",
				bundle.getString("telephone"));
		HeadLoader loader = new HeadLoader(mActivity, mHttpRequest);
		loader.setExceptionHandler(mActivity);
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Head> arg0, Head arg1) {
		if (arg1 != null) {
			mActivity.updateUI(arg0.getId(), arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<Head> arg0) {
	}

}
