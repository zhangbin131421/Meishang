package com.mobile.meishang.core.request;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MFragment;
import com.mobile.meishang.core.content.InsideActivityLoader;
import com.mobile.meishang.core.network.DefaultNetworkRequest;
import com.mobile.meishang.model.bean.InsideActivityData;

public class InsideActivityRequest implements
		LoaderManager.LoaderCallbacks<InsideActivityData> {

	private MFragment mFragment;
	private MActivity mActivity;

	public InsideActivityRequest(MActivity activity) {
		this.mActivity = activity;
	}

	public InsideActivityRequest(MFragment fragment) {
		this.mFragment = fragment;
		mActivity = (MActivity) fragment.getActivity();
	}

	@Override
	public Loader<InsideActivityData> onCreateLoader(int arg0, Bundle bundle) {
		// StringBuffer urlString = new StringBuffer(MApplication.getInstance()
		// .getmConfig().urlRootApi);
		// // urlString.append("/index/bidding.htm");// 竞标页面
		// urlString.append("/index/looks.htm");// 美容页面
		// // urlString.append("/index/wear.htm");//内衣页面
		// // urlString.append("/index/pipe.htm");//车饰页面
		// // urlString.append("/index/lamp.htm");//灯饰页面
		// // urlString.append("/index/daynews.htm");//日化页面
		// // urlString.append("/index/daily.htm");//会议页面
		// // urlString.append("/index/information.htm");//咨询页面
		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
				bundle.getString("url"));
//		mHttpRequest.addPostParameter("page", "1");
		InsideActivityLoader loader = new InsideActivityLoader(mActivity,
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
	public void onLoadFinished(Loader<InsideActivityData> arg0,
			InsideActivityData arg1) {
		if (arg1 != null) {
			if (mFragment == null) {
				mActivity.updateUI(arg0.getId(), arg1);
			} else {
				mFragment.updateUI(arg0.getId(), arg1);
			}

		}
	}

	@Override
	public void onLoaderReset(Loader<InsideActivityData> arg0) {
	}

}
