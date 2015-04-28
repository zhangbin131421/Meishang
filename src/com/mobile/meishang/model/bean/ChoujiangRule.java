package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class ChoujiangRule extends Head {
	// {
	// "RESULT": {
	// "status": 2,
	// "createTime": null,
	// "updateTime": null,
	// "remark": "抽公报或者规则",
	// "ruleId": 2,
	// "ruleText": "抽公报或者规则"
	// }
	// }
	private String ruleText;

	public ChoujiangRule() {
	}

	public ChoujiangRule(JSONObject json) throws JSONException {
		JSONObject jsonObject = getJsonObject(json, "RESULT");
		ruleText = getJsonStrValue(jsonObject, "ruleText");
	}

	public String getRuleText() {
		return ruleText;
	}

}
