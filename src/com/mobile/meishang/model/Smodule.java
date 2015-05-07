package com.mobile.meishang.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.meishang.model.bean.Head;

public class Smodule extends Head {
	// {
	// "smoduleid": 16,
	// "moduleid": 5,
	// "name": "影音导航",
	// "updatetime": "2015-4-29 18:38:51",
	// "createtime": "2015-4-29 18:38:51"
	// },

	private String smoduleid;
	private String moduleid;
	private String name;

	public Smodule() {
	}

	public Smodule(JSONObject json) throws JSONException {
		smoduleid = getJsonStrValue(json, "smoduleid");
		moduleid = getJsonStrValue(json, "moduleid");
		name = getJsonStrValue(json, "name");
	}

	public String getSmoduleid() {
		return smoduleid;
	}

	public String getModuleid() {
		return moduleid;
	}

	public String getName() {
		return name;
	}

}
