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

public class FavoritesDeleteRequest implements
		LoaderManager.LoaderCallbacks<Head> {

	private MActivity mActivity;
	private MFragment mFragment;

	public FavoritesDeleteRequest(MActivity activity) {
		this.mActivity = activity;
	}

	public FavoritesDeleteRequest(MFragment fragment) {
		this.mFragment = fragment;
		mActivity = (MActivity) fragment.getActivity();
	}

	// type：1是积分收藏 2项目收藏 3是资讯收藏 collectionids:收藏编号

	@Override
	public Loader<Head> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("collection/user/delete.htm");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("collectionids",
				bundle.getString("collectionids"));
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
