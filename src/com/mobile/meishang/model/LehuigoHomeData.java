package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class LehuigoHomeData extends Head {
	// "text": "最新推荐商品",
	// "text2": "限时优惠",
	// "list":[]
	private String text;
	private String text2;
	private List<LehuigoHomeDataItem> mList;

	public LehuigoHomeData() {
	}

	public LehuigoHomeData(JSONObject json) throws JSONException {
		text = getJsonStrValue(json, "text");
		text2 = getJsonStrValue(json, "text2");
		JSONArray topArray = getJsonArray(json, "list");
		mList = new ArrayList<LehuigoHomeDataItem>();
		if (topArray != null) {
			int topLength = topArray.length();
			for (int i = 0; i < topLength; i++) {
				mList.add(new LehuigoHomeDataItem(topArray.getJSONObject(i)));
			}

		}
	}

	public String getText() {
		return text;
	}

	public String getText2() {
		return text2;
	}

	public List<LehuigoHomeDataItem> getmList() {
		return mList;
	}

}
