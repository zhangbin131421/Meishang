package com.mobile.meishang.core.content;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.mobile.meishang.core.loader.HttpRequestLoader;
import com.mobile.meishang.core.network.ZLNetworkException;
import com.mobile.meishang.core.network.ZLNetworkRequest;
import com.mobile.meishang.model.InfomationDetail;

public class InfoDetailLoader extends HttpRequestLoader<InfomationDetail> {

	public InfoDetailLoader(Context context, ZLNetworkRequest _HttpRequest) {
		super(context, _HttpRequest);
	}

	@Override
	public InfomationDetail handle(String content) throws ZLNetworkException {
		try {
			JSONObject jsonObject = new JSONObject(content);
			InfomationDetail data = new InfomationDetail(jsonObject);
			return data;
		} catch (JSONException e) {
			throw new ZLNetworkException(ZLNetworkException.ERROR_JSONPARSER);
		}
	}

}
