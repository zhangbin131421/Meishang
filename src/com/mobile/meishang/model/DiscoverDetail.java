package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class DiscoverDetail extends Head {

	private Discover discover;
	private List<Discover> list;

	public DiscoverDetail() {

	}

	public DiscoverDetail(JSONObject json) throws JSONException {
		discover=new Discover(getJsonObject(json, "data"));
		JSONArray jsonArray = getJsonArray(json, "list");
		list = new ArrayList<Discover>();
		int length = jsonArray.length();
		for (int i = 0; i < length; i++) {
			list.add(new Discover(jsonArray.getJSONObject(i)));
		}
	}

	public Discover getDiscover() {
		return discover;
	}

	public List<Discover> getList() {
		return list;
	}

}
