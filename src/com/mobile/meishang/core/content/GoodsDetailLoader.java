package com.mobile.meishang.core.content;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.mobile.meishang.core.loader.HttpRequestLoader;
import com.mobile.meishang.core.network.ZLNetworkException;
import com.mobile.meishang.core.network.ZLNetworkRequest;
import com.mobile.meishang.model.LehuigoDetail;

public class GoodsDetailLoader extends HttpRequestLoader<LehuigoDetail> {

	public GoodsDetailLoader(Context context, ZLNetworkRequest _HttpRequest) {
		super(context, _HttpRequest);
	}

	@Override
	public LehuigoDetail handle(String content) throws ZLNetworkException {
		try {
			return new LehuigoDetail(new JSONObject(content));
		} catch (JSONException e) {
			throw new ZLNetworkException(ZLNetworkException.ERROR_JSONPARSER);
		}
	}

}
