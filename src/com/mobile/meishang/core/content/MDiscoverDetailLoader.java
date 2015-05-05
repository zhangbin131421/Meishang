package com.mobile.meishang.core.content;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.mobile.meishang.core.loader.HttpRequestLoader;
import com.mobile.meishang.core.network.ZLNetworkException;
import com.mobile.meishang.core.network.ZLNetworkRequest;
import com.mobile.meishang.model.DiscoverDetail;

public class MDiscoverDetailLoader extends HttpRequestLoader<DiscoverDetail> {

	public MDiscoverDetailLoader(Context context,
			ZLNetworkRequest _HttpRequest) {
		super(context, _HttpRequest);
	}

	@Override
	public DiscoverDetail handle(String content) throws ZLNetworkException {
		try {
			return new DiscoverDetail(new JSONObject(content) );
		} catch (JSONException e) {
			throw new ZLNetworkException(ZLNetworkException.ERROR_JSONPARSER);
		}
	}
}
