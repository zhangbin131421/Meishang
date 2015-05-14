package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class AddressList extends Head {
	private List<Address> list;

	public AddressList() {
	}

	public AddressList(JSONObject json) throws JSONException {
		list = new ArrayList<Address>();
		JSONArray jsonArray = getJsonArray(json, "list");
		if (jsonArray != null) {
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				list.add(new Address(jsonArray.getJSONObject(i)));
			}
		}

	}

	public List<Address> getList() {
		return list;
	}

}
