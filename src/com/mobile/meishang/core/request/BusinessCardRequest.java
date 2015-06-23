package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.core.content.BusinessCardListLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.BusinessCardList;

public class BusinessCardRequest implements
		LoaderManager.LoaderCallbacks<BusinessCardList> {

	private MActivity mActivity;
	private MFragment mFragment;

	public BusinessCardRequest(MActivity activity) {
		this.mActivity = activity;
	}

	public BusinessCardRequest(MFragment fragment) {
		this.mFragment = fragment;
		mActivity = (MActivity) fragment.getActivity();
	}

	@Override
	public Loader<BusinessCardList> onCreateLoader(int arg0, Bundle bundle) {
		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
				.getmConfig().urlRootApi);
		urlString.append("businesscard/page.htm");
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				urlString.toString());
		mHttpRequest.addPostParameter("pageNumber", bundle.getInt("pageNumber")
				+ "");
		// mHttpRequest.addPostParameter("pageSize",
		// bundle.getString("pageSize"));
		mHttpRequest.addPostParameter("pageSize", "10");
		mHttpRequest.addPostParameter("userId", MApplication.getInstance()
				.getLogin().getUserId());
		mHttpRequest.addPostParameter("provinceId",
				bundle.getString("provinceId"));
		mHttpRequest.addPostParameter("moduleId", bundle.getString("moduleId"));
		BusinessCardListLoader loader = new BusinessCardListLoader(mActivity,
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
	public void onLoadFinished(Loader<BusinessCardList> arg0,
			BusinessCardList arg1) {
		if (arg1 != null) {
			if (mFragment == null) {
				mActivity.updateUI(arg0.getId(), arg1);
			} else {
				mFragment.updateUI(arg0.getId(), arg1);
			}
		}
	}

	@Override
	public void onLoaderReset(Loader<BusinessCardList> arg0) {
	}

}
