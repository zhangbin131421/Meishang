package com.mobile.meishang.core.content;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.mobile.meishang.core.loader.HttpRequestLoader;
import com.mobile.meishang.core.network.ZLNetworkException;
import com.mobile.meishang.core.network.ZLNetworkRequest;
import com.mobile.meishang.model.SignRuleData;

public class MSignRuleLoader extends HttpRequestLoader<SignRuleData> {

	public MSignRuleLoader(Context context, ZLNetworkRequest _HttpRequest) {
		super(context, _HttpRequest);
	}

	@Override
	public SignRuleData handle(String content) throws ZLNetworkException {
		try {
			JSONObject jsonObject = new JSONObject(content);
			JSONObject data = jsonObject.getJSONObject("data");
			return new SignRuleData(data);
		} catch (JSONException e) {
			throw new ZLNetworkException(ZLNetworkException.ERROR_JSONPARSER);
		}
	}
}
