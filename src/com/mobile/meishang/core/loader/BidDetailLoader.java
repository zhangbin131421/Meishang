package com.mobile.meishang.core.loader;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.mobile.meishang.core.network.ZLNetworkException;
import com.mobile.meishang.core.network.ZLNetworkRequest;
import com.mobile.meishang.model.BidMyPublish;

public class BidDetailLoader extends HttpRequestLoader<BidMyPublish> {

	public BidDetailLoader(Context context, ZLNetworkRequest _HttpRequest) {
		super(context, _HttpRequest);
	}

	@Override
	public BidMyPublish handle(String content) throws ZLNetworkException {
		try {
			JSONObject jsonObject = new JSONObject(content);
			BidMyPublish object = new BidMyPublish(jsonObject);
			return object;
		} catch (JSONException e) {
			throw new ZLNetworkException(ZLNetworkException.ERROR_JSONPARSER);
		}
	}

}
