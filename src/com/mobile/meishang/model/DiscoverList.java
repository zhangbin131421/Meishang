package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class DiscoverList extends Head {

	private List<Discover> list;

	public DiscoverList() {

	}

	public DiscoverList(JSONObject json) throws JSONException {
		JSONArray jsonArray = getJsonArray(json, "list");
		list = new ArrayList<Discover>();
		int length = jsonArray.length();
		for (int i = 0; i < length; i++) {
			list.add(new Discover(jsonArray.getJSONObject(i)));
		}

	}

	public List<Discover> getList() {
		return list;
	}

}
