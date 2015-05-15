package com.mobile.meishang.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class InfomationDetail extends Head {
	private Infomation infomation;

	public InfomationDetail() {
	}

	public InfomationDetail(JSONObject json) throws JSONException {
		infomation = new Infomation(getJsonObject(json, "data"));
	}

	public Infomation getInfomation() {
		return infomation;
	}

}
