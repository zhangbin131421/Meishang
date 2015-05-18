package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class SignInFragmentData extends Head {

	private int count;
	private List<Discover> mList;

	public SignInFragmentData() {
	}

	public SignInFragmentData(JSONObject json) throws JSONException {
		count = getJsonIntValue(json, "count");
		JSONArray jsonArray = getJsonArray(json, "list");
		mList = new ArrayList<Discover>();
		if (jsonArray != null) {
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				mList.add(new Discover(jsonArray.getJSONObject(i)));
			}

		}
	}

	public int getCount() {
		return count;
	}

	public List<Discover> getmList() {
		return mList;
	}

}
