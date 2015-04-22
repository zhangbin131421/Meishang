package com.mobile.meishang.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragmentTemplateData extends Head {
	private List<HomeFragmentTemplateDataItem> list;

	public HomeFragmentTemplateData(JSONObject json) throws JSONException {
		JSONArray jsonArray = getJsonArray(json, "TEMPLATE");
		int length = jsonArray.length();
		list = new ArrayList<HomeFragmentTemplateDataItem>();
		for (int i = 0; i < length; i++) {
			list.add(new HomeFragmentTemplateDataItem(jsonArray
					.getJSONObject(i)));
		}
	}

	public List<HomeFragmentTemplateDataItem> getList() {
		return list;
	}

}
