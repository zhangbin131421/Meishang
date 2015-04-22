package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class DataLable extends Head {
	// {
	// "lableId": 1,
	// "text": "推广标签",
	// "lableName": "推广"
	// }
	private int lableId;
	private String text;
	private String lableName;

	public DataLable(JSONObject json) throws JSONException {
		lableId = getJsonIntValue(json, "lableId");
		text = getJsonStrValue(json, "text");
		lableName = getJsonStrValue(json, "lableName");
	}

	public int getLableId() {
		return lableId;
	}

	public String getText() {
		return text;
	}

	public String getLableName() {
		return lableName;
	}

}
