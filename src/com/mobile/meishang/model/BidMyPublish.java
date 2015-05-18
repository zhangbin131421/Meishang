package com.mobile.meishang.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class BidMyPublish extends Head {
	private String count;
	private Bid bidding;

	public BidMyPublish() {

	}

	public BidMyPublish(JSONObject json) throws JSONException {
		count = getJsonStrValue(json, "count");
		bidding = new Bid(json.getJSONObject("bidding"));

	}

	public String getCount() {
		return count;
	}

	public Bid getBidding() {
		return bidding;
	}

}
