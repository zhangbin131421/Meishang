package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class FavoritesList extends Head {
	private List<LehuigoDetailData> mList;
	private List<Discover> discovers;

	public FavoritesList() {
	}

	public FavoritesList(JSONObject json) throws JSONException {
		mList = new ArrayList<LehuigoDetailData>();
		discovers = new ArrayList<Discover>();
		JSONArray jsonArray = getJsonArray(json, "inList");
		if (jsonArray != null) {
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				mList.add(new LehuigoDetailData(jsonArray.getJSONObject(i)));
			}
		}
		JSONArray jsonArray2 = getJsonArray(json, "proList");
		if (jsonArray2 != null) {
			int length = jsonArray2.length();
			for (int i = 0; i < length; i++) {
				discovers.add(new Discover(jsonArray2.getJSONObject(i)));
			}
		}
	}

	public List<LehuigoDetailData> getmList() {
		return mList;
	}

	public List<Discover> getDiscovers() {
		return discovers;
	}

}
