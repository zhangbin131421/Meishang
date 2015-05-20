package com.mobile.meishang.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;
import com.mobile.meishang.model.bean.User;

public class BidMyPublish extends Head {
	private String count;
	private Bid bidding;
	private List<User> users;

	public BidMyPublish() {

	}

	public BidMyPublish(JSONObject json) throws JSONException {
		count = getJsonStrValue(json, "count");
		bidding = new Bid(json.getJSONObject("bidding"));
		users = new ArrayList<User>();
		JSONArray jsonArray = getJsonArray(json, "userList");
		if (jsonArray != null) {
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				users.add(new User(jsonArray.getJSONObject(i)));
			}
		}

	}

	public String getCount() {
		return count;
	}

	public Bid getBidding() {
		return bidding;
	}

	public List<User> getUsers() {
		return users;
	}

}
