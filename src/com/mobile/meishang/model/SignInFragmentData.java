package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class SignInFragmentData extends Head {

	private List<LehuigoHomeDataItem> mList;

	public SignInFragmentData() {
	}

	public SignInFragmentData(JSONObject json) throws JSONException {

		JSONArray jsonArray = getJsonArray(json, "list");
		mList = new ArrayList<LehuigoHomeDataItem>();
		if (jsonArray != null) {
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				mList.add(new LehuigoHomeDataItem(jsonArray.getJSONObject(i)));
			}

		}
	}

	public List<LehuigoHomeDataItem> getmList() {
		return mList;
	}

}
