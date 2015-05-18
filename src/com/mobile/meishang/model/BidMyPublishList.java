package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class BidMyPublishList extends Head {
	private List<BidMyPublish> list;

	public BidMyPublishList() {

	}

	public BidMyPublishList(JSONObject json) throws JSONException {
		list = new ArrayList<BidMyPublish>();
		JSONArray jsonArray = getJsonArray(json, "list");
		if (jsonArray != null) {
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				list.add(new BidMyPublish(jsonArray.getJSONObject(i)));
			}

		}

	}

	public List<BidMyPublish> getList() {
		return list;
	}

}
