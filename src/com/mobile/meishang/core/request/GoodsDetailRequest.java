package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.content.GoodsDetailLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.LehuigoDetail;

public class GoodsDetailRequest implements LoaderManager.LoaderCallbacks<LehuigoDetail> {

	private MActivity mLeShiHuiActivity;

	public GoodsDetailRequest(MActivity leShiHuiActivity) {
		this.mLeShiHuiActivity = leShiHuiActivity;
	}

	@Override
	public Loader<LehuigoDetail> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("purchased/load.htm");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("purchasedid",
				bundle.getString("purchasedid"));
		GoodsDetailLoader loader = new GoodsDetailLoader(mLeShiHuiActivity,
				mHttpRequest);
		loader.setExceptionHandler(mLeShiHuiActivity);
		loader.setIdentit(arg0);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<LehuigoDetail> arg0, LehuigoDetail arg1) {
		if (arg1 != null) {
			mLeShiHuiActivity.updateUI(arg0.getId(), arg1);
		}
	}

	@Override
	public void onLoaderReset(Loader<LehuigoDetail> arg0) {
	}

}
