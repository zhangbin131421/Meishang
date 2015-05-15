package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.core.content.MDiscoverFragmentLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.DiscoverList;

public class TypeActivityRequest implements
		LoaderManager.LoaderCallbacks<DiscoverList> {

	private MActivity mActivity;
	private MFragment mFragment;

	public TypeActivityRequest(MActivity activity) {
		this.mActivity = activity;
	}

	public TypeActivityRequest(MFragment fragment) {
		this.mFragment = fragment;
		mActivity = (MActivity) fragment.getActivity();
	}

	@Override
	public Loader<DiscoverList> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("project/list.htm");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("smoduleid",
				bundle.getString("smoduleid"));
		mHttpRequest.addPostParameter("moduleid", bundle.getString("moduleid"));
		MDiscoverFragmentLoader loader = new MDiscoverFragmentLoader(mActivity,
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
	public void onLoadFinished(Loader<DiscoverList> arg0, DiscoverList arg1) {
		if (arg1 != null) {
			if (mFragment == null) {
				mActivity.updateUI(arg0.getId(), arg1);
			} else {
				mFragment.updateUI(arg0.getId(), arg1);
			}
		}
	}

	@Override
	public void onLoaderReset(Loader<DiscoverList> arg0) {
	}

}
