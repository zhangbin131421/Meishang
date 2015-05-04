package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragmentTemplateDataItem extends Head {
	// {
	// "moduleid": 1,
	// "modulename": "乐汇购",
	// "updatetime": "2015-4-29 18:10:56",
	// "createtime": "2015-4-29 18:10:59",
	// "state": 1
	// },
	// {
	// "moduleid": 2,
	// "modulename": "竞标",
	// "updatetime": "2015-4-29 18:10:51",
	// "createtime": "2015-4-29 18:10:57",
	// "state": 1
	// },

	private String modulepicpath;
	private String moduleid;
	private String modulename;


	public HomeFragmentTemplateDataItem() {
	}

	public HomeFragmentTemplateDataItem(JSONObject json) throws JSONException {
		modulepicpath = getJsonStrValue(json, "modulepicpath");
		moduleid = getJsonStrValue(json, "moduleid");
		modulename = getJsonStrValue(json, "modulename");
	}

	public String getPicPath() {
		return modulepicpath;
	}

	public String getTemplateId() {
		return moduleid;
	}

	public String getTitle() {
		return modulename;
	}

}
