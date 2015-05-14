package com.mobile.meishang.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class Infomation extends Head {
	private String introduction;

	public Infomation() {

	}

	public Infomation(JSONObject json) throws JSONException {
		introduction = getJsonStrValue(json, "introduction");

	}

	public String getIntroduction() {
		return introduction;
	}

}
