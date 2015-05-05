package com.mobile.meishang.model.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Head {
	// {
	// "message": "更新失败-手机号码或密码为空",
	// "RESULT": "0"
	// }
	private String message;
	private int result = -1;
	private boolean success;

	public Head() {

	}

	public Head(JSONObject jsonObject) throws JSONException {
		message = getJsonStrValue(jsonObject, "message");
		result = getJsonIntValue(jsonObject, "result");
		if (result == 1) {
			success = true;
		} else {
			success = false;
		}

	}

	public String getMessage() {
		return message;
	}

	public int getResult() {
		return result;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getJsonStrValue(JSONObject json, String name)
			throws JSONException {
		if (json != null && json.has(name)) {
			return json.getString(name);
		}
		return "";
	}

	public int getJsonIntValue(JSONObject json, String name)
			throws JSONException {
		if (json != null && json.has(name)) {
			return json.getInt(name);
		}
		return -1;
	}

	public long getJsonLongtValue(JSONObject json, String name)
			throws JSONException {
		if (json != null && json.has(name)) {
			return json.getLong(name);
		}
		return -1;
	}

	public JSONObject getJsonObject(JSONObject json, String name)
			throws JSONException {
		if (json != null && json.has(name)) {
			return json.getJSONObject(name);
		}
		return null;
	}

	public JSONArray getJsonArray(JSONObject jsonObject, String name)
			throws JSONException {
		if (jsonObject != null && jsonObject.has(name)) {
			return jsonObject.getJSONArray(name);
		}
		return null;
	}
}
