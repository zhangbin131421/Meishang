package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class LehuigoHomeData extends Head {

	private List<LehuigoHomeDataItem> toplist;
	private List<LehuigoHomeDataItem> endlist;

	public LehuigoHomeData() {
	}

	public LehuigoHomeData(JSONObject json) throws JSONException {

		JSONArray topArray = getJsonArray(json, "toplist");
		toplist = new ArrayList<LehuigoHomeDataItem>();
		if (topArray != null) {
			int topLength = topArray.length();
			for (int i = 0; i < topLength; i++) {
				toplist.add(new LehuigoHomeDataItem(topArray.getJSONObject(i)));
			}

		}
		JSONArray endArray = getJsonArray(json, "endlist");
		endlist = new ArrayList<LehuigoHomeDataItem>();
		if (endArray != null) {
			int length = endArray.length();
			for (int i = 0; i < length; i++) {
				endlist.add(new LehuigoHomeDataItem(topArray.getJSONObject(i)));
			}

		}
	}

	public List<LehuigoHomeDataItem> getToplist() {
		return toplist;
	}

	public List<LehuigoHomeDataItem> getEndlist() {
		return endlist;
	}

}
