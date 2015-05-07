package com.mobile.meishang.core.content;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.mobile.meishang.core.loader.HttpRequestLoader;
import com.mobile.meishang.core.network.ZLNetworkException;
import com.mobile.meishang.core.network.ZLNetworkRequest;
import com.mobile.meishang.model.LehuigoHomeData;

public class LehuigoHomeLoader extends HttpRequestLoader<LehuigoHomeData> {

	public LehuigoHomeLoader(Context context, ZLNetworkRequest _HttpRequest) {
		super(context, _HttpRequest);
	}

	@Override
	public LehuigoHomeData handle(String content) throws ZLNetworkException {
		try {
			JSONObject jsonObject = new JSONObject(content);
			LehuigoHomeData data = new LehuigoHomeData(jsonObject);
			return data;
		} catch (JSONException e) {
			throw new ZLNetworkException(ZLNetworkException.ERROR_JSONPARSER);
		}
	}

}
