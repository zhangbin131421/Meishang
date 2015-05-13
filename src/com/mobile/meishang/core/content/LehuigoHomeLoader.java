package com.mobile.meishang.core.content;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;

import com.mobile.meishang.core.loader.HttpRequestLoader;
import com.mobile.meishang.core.network.ZLNetworkException;
import com.mobile.meishang.core.network.ZLNetworkRequest;
import com.mobile.meishang.model.LehuigoHomeData;

public class LehuigoHomeLoader extends HttpRequestLoader<List<LehuigoHomeData>> {

	public LehuigoHomeLoader(Context context, ZLNetworkRequest _HttpRequest) {
		super(context, _HttpRequest);
	}

	@Override
	public List<LehuigoHomeData> handle(String content)
			throws ZLNetworkException {
		List<LehuigoHomeData> mLehuigoHomeDatas = new ArrayList<LehuigoHomeData>();
		try {
			JSONArray jsonArray = new JSONArray(content);
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				mLehuigoHomeDatas.add(new LehuigoHomeData(jsonArray
						.getJSONObject(i)));
			}
			return mLehuigoHomeDatas;
		} catch (JSONException e) {
			throw new ZLNetworkException(ZLNetworkException.ERROR_JSONPARSER);
		}
	}

}
