package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.core.content.FavoritesListLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.FavoritesList;

public class FavoritesListRequest implements
		LoaderManager.LoaderCallbacks<FavoritesList> {

	private MActivity mActivity;
	private MFragment mFragment;

	public FavoritesListRequest(MActivity activity) {
		this.mActivity = activity;
	}

	public FavoritesListRequest(MFragment fragment) {
		this.mFragment = fragment;
		mActivity = (MActivity) fragment.getActivity();
	}

	@Override
	public Loader<FavoritesList> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("collection/user/list.htm");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("userid", MApplication.getInstance()
				.getLogin().getUserId());
		FavoritesListLoader loader = new FavoritesListLoader(mActivity,
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
	public void onLoadFinished(Loader<FavoritesList> arg0, FavoritesList arg1) {
		if (arg1 != null) {
			if (mFragment == null) {
				mActivity.updateUI(arg0.getId(), arg1);
			} else {
				mFragment.updateUI(arg0.getId(), arg1);
			}
		}
	}

	@Override
	public void onLoaderReset(Loader<FavoritesList> arg0) {
	}

}
