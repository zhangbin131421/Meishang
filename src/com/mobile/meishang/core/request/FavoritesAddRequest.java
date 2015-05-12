package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.core.loader.HeadLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.Head;

public class FavoritesAddRequest implements LoaderManager.LoaderCallbacks<Head> {

	private MActivity mActivity;

	public FavoritesAddRequest(MActivity activity) {
		this.mActivity = activity;
	}

	// userid：用户编号 objectid:收藏对应编号 type：1是积分收藏 2项目收藏 3是资讯收藏

	@Override
	public Loader<Head> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("collection/save.htm");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("objectid", bundle.getString("objectid"));
		mHttpRequest.addPostParameter("type", bundle.getString("type"));
		mHttpRequest.addPostParameter("userid", MApplication.getInstance()
				.getLogin().getUserId());
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
