package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class FavoritesList extends Head {
	private List<LehuigoDetailData> mList;

	public FavoritesList() {
	}

	public FavoritesList(JSONObject json) throws JSONException {
		mList = new ArrayList<LehuigoDetailData>();
		JSONArray jsonArray = getJsonArray(json, "inList");
		if (jsonArray != null) {
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				mList.add(new LehuigoDetailData(jsonArray.getJSONObject(i)));
			}
		}

	}

	public List<LehuigoDetailData> getmList() {
		return mList;
	}

}
