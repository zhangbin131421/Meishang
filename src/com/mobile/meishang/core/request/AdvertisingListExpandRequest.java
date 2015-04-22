//package com.mobile.meishang.core.request;
//
//import android.os.Bundle;
//import android.support.v4.app.LoaderManager;
//import android.support.v4.content.Loader;
//
//import com.mobile.meishang.MActivity;
//import com.mobile.meishang.MApplication;
//import com.mobile.meishang.core.content.HomeFragmentLoader;
//import com.mobile.meishang.core.network.DefaultNetworkRequest;
//import com.mobile.meishang.model.bean.AdvertisingGallery;
//
//public class AdvertisingListExpandRequest implements
//		LoaderManager.LoaderCallbacks<AdvertisingGallery> {
//
//	private MActivity mActivity;
//
//	public AdvertisingListExpandRequest(MActivity activity) {
//		this.mActivity = activity;
//	}
//
//	@Override
//	public Loader<AdvertisingGallery> onCreateLoader(int arg0, Bundle arg1) {
//		StringBuffer urlString = new StringBuffer(MApplication.getInstance()
//				.getmConfig().urlRootApi);
//		urlString.append("/limitBuy/actInfoList");
//		DefaultNetworkRequest mHttpRequest = new DefaultNetworkRequest(
//				urlString.toString());
//		HomeFragmentLoader loader = new HomeFragmentLoader(mActivity,
//				mHttpRequest);
//		loader.setExceptionHandler(mActivity);
//		loader.setIdentit(arg0);
//		return loader;
//	}
//
//	@Override
//	public void onLoadFinished(Loader<AdvertisingGallery> arg0,
//			AdvertisingGallery arg1) {
//		if (arg1 != null) {
//			mActivity.updateUI(arg0.getId(), arg1);
//		}
//	}
//
//	@Override
//	public void onLoaderReset(Loader<AdvertisingGallery> arg0) {
//	}
//
//}
