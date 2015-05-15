package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class InfomationList extends Head {
	private List<Infomation> list;

	public InfomationList() {
	}

	public InfomationList(JSONObject json) throws JSONException {
		list = new ArrayList<Infomation>();
		JSONArray jsonArray = getJsonArray(json, "list");
		if (jsonArray != null) {
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				list.add(new Infomation(jsonArray.getJSONObject(i)));
			}
		}
	}

	public List<Infomation> getList() {
		return list;
	}

}
