package com.mobile.meishang.model.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragmentTemplateDataItem extends Head {
//	{
//	    "moduleid": 1,
//	    "modulename": "乐汇购",
//	    "modulepicpath": "http://121.40.126.98:80/data/image/module/lgh.png",
//	    "moduleurl": "http://121.40.126.98:80/data/null",
//	    "updatetime": "2015-4-29 18:10:56",
//	    "createtime": "2015-4-29 18:10:59",
//	    "state": 1
//	}

	private String moduleid;
	private String modulename;
	private String modulepicpath;
	private String moduleurl;


	public HomeFragmentTemplateDataItem() {
	}

	public HomeFragmentTemplateDataItem(JSONObject json) throws JSONException {
		modulepicpath = getJsonStrValue(json, "modulepicpath");
		moduleid = getJsonStrValue(json, "moduleid");
		modulename = getJsonStrValue(json, "modulename");
		moduleurl = getJsonStrValue(json, "moduleurl");
	}

	public String getModuleid() {
		return moduleid;
	}

	public String getModulename() {
		return modulename;
	}

	public String getModulepicpath() {
		return modulepicpath;
	}

	public String getModuleurl() {
		return moduleurl;
	}


}
