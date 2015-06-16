package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class BusinessCardList extends Head {
	private int total;
	private List<BusinessCard> mList;

	public BusinessCardList() {

	}

	public BusinessCardList(JSONObject json) throws JSONException {
		total = getJsonIntValue(json, "total");
		JSONArray jsonArray = getJsonArray(json, "rows");
		mList = new ArrayList<BusinessCard>();
		int length = jsonArray.length();
		for (int i = 0; i < length; i++) {
			mList.add(new BusinessCard(jsonArray.getJSONObject(i)));
		}

	}

	public int getTotal() {
		return total;
	}

	public List<BusinessCard> getmList() {
		return mList;
	}

}
