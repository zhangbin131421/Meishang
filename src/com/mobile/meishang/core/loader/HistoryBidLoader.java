package com.mobile.meishang.core.loader;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.mobile.meishang.core.network.ZLNetworkException;
import com.mobile.meishang.core.network.ZLNetworkRequest;
import com.mobile.meishang.model.bean.User;

public class HistoryBidLoader extends HttpRequestLoader<User> {

	public HistoryBidLoader(Context context, ZLNetworkRequest _HttpRequest) {
		super(context, _HttpRequest);
	}

	@Override
	public User handle(String content) throws ZLNetworkException {
		try {
			JSONObject jsonObject = new JSONObject(content);
			User object = new User(jsonObject);
			return object;
		} catch (JSONException e) {
			throw new ZLNetworkException(ZLNetworkException.ERROR_JSONPARSER);
		}
	}

}
